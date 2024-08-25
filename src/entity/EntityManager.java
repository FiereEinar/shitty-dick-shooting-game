package entity;

import java.util.ArrayList;

import main.GamePanel;

public class EntityManager {
	
	public ArrayList<Enemy> entities = new ArrayList<Enemy>();
	public ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	int bulletCounter = 0;
	
	GamePanel gp;
	int enemyAmount = 50;

	public EntityManager(GamePanel gp) {
		this.gp = gp;
		createEnemies();
	}
	
	private void createEnemies() {
		for (int i = 0; i < enemyAmount; i++) {
			entities.add(new Enemy(gp));
		}
	}
	
	public void createBullets(int x, int y, String d) {
		Bullets bullet = new Bullets(this.gp, x, y, d, bulletCounter);
		this.bullets.add(bullet);
		bulletCounter++;
	}
	
	public Boolean isCollision(Entity a, Entity b) {
		int objectSize = gp.tileSize - 20;
		
		if (a.x < b.x + objectSize && 
	        a.x + objectSize > b.x && 
	        a.y < b.y + objectSize && 
	        a.y + objectSize > b.y
        ) {
	        return true;
	    }
		
		return false;
	}
	
	public void removeOutOfBoundsBullets() {
		bullets.removeIf(b -> (b.destroy));		
	}
	
	public void removeDeadEnemies() {
		entities.removeIf(e -> (e.isDead));
	}

}
