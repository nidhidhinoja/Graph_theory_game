package main;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Color;
import main.GamePanel;
import java.awt.image.BufferedImage;
import object.OBJ_key;
import java.text.DecimalFormat;
import javax.swing.JRadioButton;
 import javax.swing.*;
 import java.awt.*;

//import font.MP16OSF;


public class UI extends JFrame
{
	GamePanel gp;
	Graphics2D g2;
	Font arial_40B,arial_90B,arial_40P,arial_20B;
	
//	Font maruM, purisaB;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public int commandNum=0;
	public int titleScreenState = 0; //0-1st scree, 1- 2nd screen
	
	public UI(GamePanel gp){
		this.gp = gp;
		arial_40B = new Font("Jokerman",Font.BOLD, 40);
		arial_20B = new Font("Jokerman",Font.PLAIN, 20);
		arial_90B = new Font("Jokerman",Font.BOLD, 90);
		arial_40P = new Font("Jokerman",Font.PLAIN, 40);
		
		//"C:\Users\Mansiba Gohil\Downloads\milli's way\font\MP16OSF.ttf"
		/*try{
			inputStream is = getClass().getResourceAsStream("/font/MP16OSF.ttf");
			maruM = Font.createFont(Font.TRUETYPE FONT, is);	
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE FONT, is);	
		} catch(FontFormatException e)
		{
			e.printStackTrace();
		} catch(IOException e) 
		{
			e.printStackTrace();
		}*/
		
		OBJ_key key = new OBJ_key(gp);
		keyImage = key.image;
	}
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
	}
	
	//PLAY STATE
	public void draw(Graphics2D g2)
	{
		this.g2 = g2;
		
		g2.setFont(arial_40B);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.titleState)
		{
			drawTitleScreen();
		}
		if(gp.gameState == gp.playState){
			//Do playState stuff later
			
		}
		
		//PAUSE STATE
		if(gp.gameState == gp.pauseState){
			drawPauseScreen();
		}
		
		//DIAOUGE STATE
		if(gp.gameState == gp.dialogueState)
		{
			drawDialogueScreen();
		}
 
		if(gameFinished == true)
		{
			titleScreenState = 1;
			drawTitleScreen();
		}
		/*if(gp.gameState == gp.quizState)
		{
			drawQuizScreen();
		}*/
		else if(gp.gameState == gp.playState)
		{
			g2.setFont(arial_40P);
			g2.setColor(Color.white);	
			g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
			g2.drawString("- "+gp.player.hasKey,74,64);
			
			//TIME
			playTime += (double)1/60;
			//g2.drawString("Time ="+ dFormat.format(playTime),gp.tileSize*11,65);
			
			
			if(messageOn == true)
			{
				g2.setFont(g2.getFont().deriveFont(20F));
				g2.drawString(message,gp.tileSize*7,gp.tileSize*13);
				
				messageCounter++;
				
				if(messageCounter >100)
				{
					messageCounter =0;
					messageOn = false;
				}
			}
		}
	}
	public void drawPauseScreen(){
		
		g2.setFont(arial_40P);
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		
		int y = gp.screenHeight/2;
		
		
		g2.drawString(text,x,y);
	}
	public void drawDialogueScreen()
	{
		//WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line: currentDialogue.split("\n"))
		{
			//System.out.println("entered");
			g2.drawString(line, x, y);
			y += 40;	
		}
		
	}
	public void drawSubWindow(int x, int y, int width, int height)
	{
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 30, 30);
	}
	public int getXforCenteredText(String text){
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public void drawTitleScreen()
	{
		if(titleScreenState == 0)
		{
			g2.setColor(new Color(0,139,139));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			g2.setFont(arial_90B);
			String text = "Milli's Way";
			int x = getXforCenteredText(text);
			int y= gp.tileSize*3;
			
			g2.setColor(Color.black);
			g2.drawString(text,x+6,y+6);
			
			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			
			g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
			
			//MENU
			g2.setFont(arial_40P);
			
			text = "Start Game";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text,x,y);
			if(commandNum==0)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text = "New Game";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text = "Quit";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
		}
		else if(titleScreenState == 1)
		{
				g2.setColor(new Color(0,139,139));
				g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
				
				String text;
				int textLength;
				int x;
				int y;
				
				
				g2.setFont(arial_90B);
				
				text = "Congratulations!!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y = gp.screenHeight/2 - (gp.tileSize*3);
				g2.setColor(Color.black);
				g2.drawString(text,x+6,y+6);
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				
				g2.setFont(arial_40B);
				g2.setColor(Color.black);	
				
				text = "You've reached the school!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
				x = gp.screenWidth/2 - textLength/2;
				y = gp.screenHeight/2 - (gp.tileSize*5);
				g2.drawString(text,x+5,y+5);
				
				g2.setColor(Color.white);	
				g2.setFont(arial_20B);
			
					text = "CONCEPT:";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 - (gp.tileSize*2);
					g2.drawString(text,x+6,y+6);
					
					text = "Currently you have followed Hamiltionian Graph's Condition";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 - (gp.tileSize);
					g2.drawString(text,x+6,y+6);
					
					text = "A connected graph G is called Hamiltonian graph if";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 ;
					g2.drawString(text,x+5,y+5);

					text = "there is a cycle which includes every vertex of G";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize);
					g2.drawString(text,x+4,y+4);
					
					text = "and the cycle is called Hamiltonian cycle";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize*2);
					g2.drawString(text,x+3,y+3);
					
					text = "Task  Completed:";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize*3);
					g2.drawString(text,x+3,y+3);
					
					text = "here we have taken milli's friend home, her assignment at friend's house";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize*4);
					g2.drawString(text,x+3,y+3);

					text = "lib and bakery as a vertice";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize*5);
					g2.drawString(text,x+3,y+3);

					text = "as we know if we didn't complete all the tasks it wouldn't satisfy the condition";
					textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
					x = gp.screenWidth/2 - textLength/2;
					y = gp.screenHeight/2 + (gp.tileSize*6);
					g2.drawString(text,x+3,y+3);
					
					gp.stopMusic();
					gp.playSE(4);
					gp.gameThread = null;

			/*text = "Your time is: "+ dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize);
			g2.drawString(text,x,y);*/
			/*
			text = "Concept";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text,x,y);
			if(commandNum==0)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text = "Next Level";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text = "Main Menu";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			gp.gameThread = null;*/
		}
	}
	/*public void drawQuizScreen()
	{
		g2.setColor(new Color(0,139,139));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			String text;
			int textLength;
			int x;
			int y;
				
			text = "Question 1";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*2);
			g2.drawString(text,x+6,y+6);
					
			text = "You can repeat vertex in Hamiltonian graph?";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize);
			g2.drawString(text,x+6,y+6);
			
			JRadioButton option1 = new JRadioButton("True");
			JRadioButton option2 = new JRadioButton("False");
			JRadioButton option3 = new JRadioButton("Can't say");

			ButtonGroup group = new ButtonGroup();
			group.add(option1);
			group.add(option2);
			group.add(option3);

			setLayout(new FlowLayout());

			add(option1);
			add(option2);
			add(option3);
	}*/
		public void drawTask()
		{
			String text;
			int textLength;
			int x;
			int y;
			
			g2.setFont(arial_40B);
			g2.setColor(Color.black);	
			
			text = "You need to complete the task: ";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*5);
			g2.drawString(text,x+5,y+5);
		}
}