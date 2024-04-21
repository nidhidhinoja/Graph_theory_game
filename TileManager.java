package tile;
import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.*;
import main.UtilityTool;
import java.util.ArrayList;

public class TileManager
{
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	ArrayList<String> filenames = new ArrayList<>(); 
	ArrayList<String> collisionStatus = new ArrayList<>(); 
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		InputStream is = getClass().getResourceAsStream("/maps/Ham_Tile_Data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line;
		
		try{
			while((line = br.readLine()) != null)
			{
				filenames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		tile = new Tile[filenames.size()];
		getTileImage();
		
		is = getClass().getResourceAsStream("/maps/01Hamiltonian_graph.txt");
		br = new BufferedReader(new InputStreamReader(is));
	
		try{
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			
			mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
			br.close();
		}catch(IOException e)
		{
			System.out.println("Excetion!");
		}
		
		loadMap("/maps/01Hamiltonian_graph.txt");
	}
	public void getTileImage()
	{
		for(int i =0;i < filenames.size();i++)
		{
			String filename;
			boolean collision;
			
			filename = filenames.get(i);
			
			if(collisionStatus.get(i).equals("true"))
			{
				collision = true;
			}
			else
			{
				collision = false;
			}
			setUp(i,filename,collision);
		}
	}
	public void setUp(int index , String imageName, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		try{
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/humri_tiles/"+imageName));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize , gp.tileSize);
			tile[index].collision = collision;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath)
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol )
				{
					col=0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e)
		{
			
		}
	}
	public void draw(Graphics2D g2)
	{
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
				{
					g2.drawImage(tile[tileNum].image,screenX,screenY,null);
				}
			worldCol++;
			
				if(worldCol == gp.maxWorldCol)
				{
					worldCol = 0;
					worldRow++;
				}
		}
	}
}