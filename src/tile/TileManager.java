package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tiles = new Tile[10];
	int[][] map;

	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		this.map = new int[gp.maxWorldRow][gp.maxWorldCol];
		getTileImage();
		loadMap();
	}
	
	private void getTileImage() {
		
		try {

			tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/terrain/grass.png")));
			tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/terrain/water.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadMap() {
		
		try {	
			InputStream is = getClass().getResourceAsStream("/map/world01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int row = 0;
			
			while (row < gp.maxWorldRow) {
				String line = br.readLine();
				String[] nums = line.split(" ");
				int col = 0;
				
				while (col < gp.maxWorldCol) {
					int num = Integer.parseInt(nums[col]);
					
					this.map[row][col] = num;
					col++;
				}
				
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int size = gp.tileSize;
		
		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[i].length; j++) {
				
				int worldX = j * size;
				int worldY = i * size;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				g2.drawImage(tiles[map[i][j]].image, screenX, screenY, size, size, null);
			}
		}		
	}
}
