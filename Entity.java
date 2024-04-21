package entity;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.Rectangle;
import main.UtilityTool;

public class Entity
{
	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,down1,left1,right1,left2,right2,left3,right3;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX , solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter =0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public Entity(GamePanel gp)
	{
		this.gp = gp;
	}
	public void setAction()
	{
		
	}
	
	public void speak()
	{
		if(dialogues[dialogueIndex] == null)
		{
			dialogueIndex = 6;
		}
		
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) 
		{
			case "up":
				direction = "down";
				break;
			case "down":
				direction = "up";
				break;
			case "right":
				direction = "left";
				break;
			case "left":
				direction = "right";
				break;		
		}
	}
	public void update()
	{
		setAction();
		collisionOn = false;
		gp.cCheck.checkTile(this);
		gp.cCheck.checkObject(this,false);
		gp.cCheck.checkPlayer(this);
		
		if(collisionOn == false)
			{
				switch(direction)
				{
					case "up": 
						worldY -= speed; 
						break;
					case "down": 
						worldY += speed; 
						break;
					case "left": 
						worldX -= speed; 
						break;
					case "right": 
						worldX += speed; 
						break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter >12)
			{
				if(spriteNum == 1)
				{
					spriteNum = 2;
				}
				else if(spriteNum == 2)
				{
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
	}
	public void draw(Graphics2D g2)
	{
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
			{
				switch(direction)
				{
					case "up":
					image = up1;
					break;
					case "down":
					image = down1;
					break;
					case "left":
					if(spriteNum == 1)
					{
						image = left1;
					}
					if(spriteNum == 2)
					{
						image = left2;
					}
					break;
					case "right":
					if(spriteNum == 1)
					{
						image = right1;
					}
					if(spriteNum == 2)
					{
						image = right2;
					}
					break;
				}
				g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			}
	}
	
	public BufferedImage setUp(String imagePath)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, gp.tileSize,gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
}