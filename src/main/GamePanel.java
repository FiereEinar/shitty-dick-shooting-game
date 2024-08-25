package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Bullets;
import entity.Enemy;
import entity.EntityManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 3587031526761099141L;
	
	final int originalTileSize = 16;
	final int scale = 4;
	
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 20;
	final int maxScreenRow = 12;
	
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHeight = maxScreenRow * tileSize;
	
	int FPS = 60;

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyHandler);
	EntityManager EM = new EntityManager(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this); 
		gameThread.run();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		
		player.update();
		if (player.isShooting) 
			EM.createBullets(player.x, player.y, player.direction);
		
		for (Enemy e: EM.entities) e.update();

		EM.removeDeadEnemies();
		EM.removeOutOfBoundsBullets();
		
		for (Bullets b: EM.bullets) {

			b.update();	
			
			for (Enemy e: EM.entities) {
				
				if (EM.isCollision(e, b)) {
					
					e.decreaseHealth(player.damage);
					b.destroy();
					
					if (e.health < 0) e.kill();
					
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		for (Enemy e: EM.entities) e.draw(g2);
		for (Bullets b: EM.bullets) b.draw(g2);		
		
		g2.dispose();
		
	}

}
