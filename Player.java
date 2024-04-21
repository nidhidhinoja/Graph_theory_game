package entity;
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
import main.UI;

public class Player extends Entity
{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public int hasKey = 0;
	public int hasTrophy = 0;
	public Player(GamePanel gp,KeyHandler keyH)   //GamePanel gp,KeyHandler keyH
	{
		//gp = new GamePanel();
		//keyH = new KeyHandler();
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY= solidArea.y;
		solidArea.width = 16;
		solidArea.height = 16;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues()
	{
		worldX= gp.tileSize * 9;
		worldY= gp.tileSize * 4;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage()
	{
		up1 = setUp("/player/star_up");
		down1 = setUp("/player/star_down");
		left1 = setUp("/player/star_right");
		right1 = setUp("/player/star_left");
		left2 = setUp("/player/running_0R");
		right2 = setUp("/player/running_0L");
		left3 = setUp("/player/running_1R");
		right3 = setUp("/player/running_1L");
	}
	public void update()
	{
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
			if(keyH.upPressed == true)
			{
				direction="up";
			}
			else if(keyH.downPressed == true)
			{
				direction = "down";
			}
			else if(keyH.leftPressed == true)
			{
				direction = "left";
			}
			else if(keyH.rightPressed == true)
			{
				direction = "right";
			}
			
			collisionOn = false;
			gp.cCheck.checkTile(this);
			
			int objIndex = gp.cCheck.checkObject(this, true);
			pickUpObject(objIndex);
			
			int npcIndex = gp.cCheck.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			
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
	}
	public void pickUpObject(int i)
	{
		if(i != 999)
		{
			String objectName = gp.obj[i].name;
			
			switch(objectName)
			{
				case "key":
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("You got a key!!");
					break;
				case "door":
					gp.playSE(3);
					if(hasKey > 0 )
					{
						gp.obj[i] = null;
						hasKey--;
						gp.ui.showMessage("You unlocked a door!");
					}
					else
					{
						gp.ui.showMessage("You need a key!");
					}
					break;
				case "Boots":
					gp.playSE(2);
					speed += 1;
					gp.obj[i] = null;
					gp.ui.showMessage("speed up!!");
					break;
				case "chest":
					if(hasKey == 4)
					{
						gp.ui.gameFinished = true;
					}
					gp.ui.drawTask();
			}
			
		}
	}
	public void interactNPC(int i)
	{
		if(i != 999)
		{
			/*if(gp.keyH.enterPressed == true)
			{*/
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			//}
			
		}
		//gp.keyH.enterPressed = false;
	}
	public void draw(Graphics2D g1)
	{
		//g1.setColor(Color.white);
		//g1.fillRect(x, y, gp.tileSize, gp.tileSize); //x, y, Width, Height
		
		BufferedImage image = null;
		
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
				image = left3;
			}
			break;
			case "right":
			if(spriteNum == 1)
			{
				image = right1;
			}
			if(spriteNum == 2)
			{
				image = right3;
			}
			break;
		}
		g1.drawImage(image,screenX,screenY,null);
	}
}