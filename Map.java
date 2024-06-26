package tile;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics2D;
/*
public class Map extends TileManager
{
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean minimapOn = false;
	
	public Map(GamePanel gp)
	{
		super(gp);
		this.gp = gp;
	}
	
	public void createWorldMap()
	{
		worldMap = new BufferedImage[gp.maxMap];
		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		
		for(int i=0;i <gp.maxMap;i++)
		{
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col=0;
			int row=0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				int TileNum = mapTileNum[i][col][row];
				int x = gp.tileSize*col;
				int y = gp.tileSize*row;
				g2.drawImage(tile[tileNum].image,x,y,null);
				
				col++;
				if(col== gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
		}
	}
	public void drawFullMapScreen(Graphics2D g2)
	{
		g2.setColor(Color.black);
		g2.fillRect(0 ,0 ,gp.screenWidth, gp.screenHeight);
		
		int width = 500;
		int height = 500;
		int x = gp.screenWidth/2 - width/2;
		int y = gp.screenHeight/2 - height/2;
		
		g2.drawImage(worldMap[gp.currentMap],x,y,width,height,null);
		
	}
}*/