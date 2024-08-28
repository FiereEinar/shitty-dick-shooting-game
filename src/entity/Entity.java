package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int worldX, worldY;
	public int speed;
	public int damage;
	
	public Boolean isDead = false;
	public Boolean isShooting = false;
	
	public BufferedImage up, down, left, right;
	public String direction;
	
	public BufferedImage getPlayerSpriteByDirection() {
		switch (direction) {
		case "up": return up;
		case "left": return left;
		case "right": return right;
		default: return down;
		}
	}
	
	public void kill() {
		this.isDead = true;
	}
	
	public void setIsShooting(Boolean s) {
		this.isShooting = s;
	}
}
