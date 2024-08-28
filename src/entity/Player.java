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
	
	public final int screenY;
	public final int screenX;
	
	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		
		this.worldX = 500;
		this.worldY = 500;
		
		this.speed = 5;
		this.damage = 5;
		this.direction = "down";
		
		int offSet = gp.tileSize / 2;
		
		this.screenX = gp.screenWidth / 2 - offSet;
		this.screenY = gp.screenHeight / 2 - offSet;
		
		getPlayerImage();
	}
	
	public void update( ) {
		if (keyHandler.UP) {
			if (worldY >= 0) {
				direction = "up";
				worldY -= speed;				
			}
		}
		
		if (keyHandler.DOWN) {
			if (worldY <= gp.worldHeight - gp.tileSize) {
				direction = "down";
				worldY += speed;				
			}
		}
		
		if (keyHandler.LEFT) {
			if (worldX >= 0) {
				direction = "left";
				worldX -= speed;				
			}
		}
		
		if (keyHandler.RIGHT) {
			if (worldX <= gp.worldWidth - gp.tileSize) {
				direction = "right";
				worldX += speed;				
			}
		}
		
		this.setIsShooting(keyHandler.SHOOTING);

	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getPlayerSpriteByDirection();
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
