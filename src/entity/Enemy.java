package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity {
	GamePanel gp;
	public int health = 50;

	public Enemy(GamePanel gp) {
		this.gp = gp;
		this.x = 100;
		this.y = 100;
		this.speed = 5;
		this.direction = "up";
		getPlayerImage();
	}
	
	private String randomMove() {
		String choices[] = {"up", "down", "left", "right"};
		
		int choice = (int)Math.floor(Math.random() * choices.length);
		
		return choices[choice];
	}
	
	public void update( ) {
		
		this.direction = randomMove();

		switch (this.direction) {
		case "up": {
			if (this.y >= 0) this.y -= this.speed;
			break;
		}
		case "down": {
			if (this.y <= this.gp.screenHeight - this.gp.tileSize) this.y += this.speed;
			break;
		}
		case "left": {
			if (this.x >= 0) this.x -= this.speed;
			break;
		}
		case "right": {
			if (this.x <= this.gp.screenWidth - this.gp.tileSize) this.x += this.speed;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}

	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = getPlayerSpriteByDirection();
		
		g2.drawImage(image, this.x, this.y, this.gp.tileSize, this.gp.tileSize, null);
	}
	
	public void getPlayerImage() {
		try {
			this.up = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
			this.down = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
			this.left = ImageIO.read(getClass().getResourceAsStream("/player/player_left.png"));
			this.right = ImageIO.read(getClass().getResourceAsStream("/player/player_right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void decreaseHealth(int damage) {
		this.health -= damage;
	}

}
