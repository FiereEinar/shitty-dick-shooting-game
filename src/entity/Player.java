package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		
		this.x = 500;
		this.y = 500;
		this.speed = 5;
		this.damage = 5;
		this.direction = "down";
		getPlayerImage();
	}
	
	public void update( ) {
		if (keyHandler.UP) {
			if (y >= 0) {
				direction = "up";
				y -= speed;				
			}
		}
		
		if (keyHandler.DOWN) {
			if (y <= gp.screenHeight - gp.tileSize) {
				direction = "down";
				y += speed;				
			}
		}
		
		if (keyHandler.LEFT) {
			if (x >= 0) {
				direction = "left";
				x -= speed;				
			}
		}
		
		if (keyHandler.RIGHT) {
			if (x <= gp.screenWidth - gp.tileSize) {
				direction = "right";
				x += speed;				
			}
		}
		
		this.setIsShooting(keyHandler.SHOOTING);

	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getPlayerSpriteByDirection();
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/player_left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/player_right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
