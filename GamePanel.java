package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.*;
import tile.TileManager;
import entity.Player;
import object.SuperObject;
//import tile.Map;
import entity.Entity;

public class GamePanel extends JPanel implements Runnable
{
	//SCREEN SETTINGS 
	final int originalTileSize = 16; // 16x16 tiles
	final int scale = 3;
	
	//private Player player;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tiles
	public final int maxScreenCol = 18;
	public final int maxScreenRow = 14;
	
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD MAP SETTINGS
	public int maxWorldCol ; //50
	public int maxWorldRow ; //60
	//public final int worldWidth = tileSize * maxWorldCol;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 120;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	
	public KeyHandler keyH = new KeyHandler(this);
	
	Sound music = new Sound();
	Sound se = new Sound();
	
	public CollisionChecker cCheck = new CollisionChecker(this);
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	public UI ui = new UI(this);
	
	//Map map = new Map(this);
	
	Thread gameThread;
	
	public Player player = new Player(this,keyH);
	
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int quizState = 4;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame()
	{
		aSetter.setObject();
		aSetter.setNpc();
		gameState = titleState;
	}
		
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run()
	{
		double drawInterval = 1000000000/FPS; //0.016666 sec
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while(gameThread != null)
		{
			//1.UPDATE. info updation like character positions
			//2.DRAW. draw screen as updated info
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000)
			{
				//System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update()
	{
		if(gameState == playState)
		{
			player.update();	
			
			for(int i=0;i<npc.length;i++)
			{
				if(npc[i]!=null)
				{
					npc[i].update();
				}
			}
		}
		if(gameState == pauseState)
		{
			//nothing
			ui.drawPauseScreen();
		} 
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true){
			drawStart = System.nanoTime();	
		}
		
		
		//long drawStart = 0;
		drawStart = System.nanoTime();
		if(titleState == gameState)
		{
			ui.draw(g2);
		}
		else
		{
			//TILE
			tileM.draw(g2);
			
			//OBJECT
			for(int i=0; i < obj.length; i++)
			{
				if(obj[i] != null)
				{
					obj[i].draw(g2,this);
				}
			}
			
			//NPC
			for(int i=0; i < obj.length; i++)
			{
				if(npc[i] != null)
				{
					npc[i].draw(g2);
				}
			}
			
			//PLAYER
			player.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		//DEBUG
		if(keyH.checkDrawTime == true){
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: "+passed, 10, 400);
			System.out.println("Draw Time: "+passed);
		
		}
		
		g2.dispose();
	}
	public void playMusic(int i)
	{
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	public void stopMusic()
	{
		music.stop();
		
	}
	public void playSE(int i)
	{
		se.setFile(i);
		se.play();
	}
}