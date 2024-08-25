package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Bullets extends Entity {
	
	public BufferedImage image;
	Boolean destroy = false;
	int size = 20;
	int id;
	GamePanel gp;

	public Bullets(GamePanel gp, int x, int y, String d, int id) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.direction = d;
		this.speed = 8;
		this.id = id;
		getBulletImage();
	}
	
	public void update( ) {
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
		
		if (this.x <= 0 || this.x >= this.gp.screenWidth - this.gp.tileSize || this.y <= 0 || this.y >= this.gp.screenHeight - this.gp.tileSize) { 
			destroy();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(this.image, this.x, this.y, this.gp.tileSize, this.gp.tileSize, null);
	}
	
	private void getBulletImage() {
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		this.destroy = true;
	}

}
