package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.UI;

public class KeyHandler implements KeyListener
{
	GamePanel gp;
	public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed;
	
	// DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(gp.gameState == gp.titleState)
		{
			if(gp.ui.titleScreenState == 0)
			{
				if(code == KeyEvent.VK_UP)
				{
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 2)
					{
						gp.ui.commandNum = 0;
					}
				}
				if(code == KeyEvent.VK_DOWN)
				{
					gp.ui.commandNum++;
					if(gp.ui.commandNum < 0)
					{	
						gp.ui.commandNum = 2;
					}
				}
				if(code == KeyEvent.VK_ENTER)
				{
					if(gp.ui.commandNum == 0)
					{	
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if(gp.ui.commandNum == 1)
					{	
						//add later
					}
					if(gp.ui.commandNum == 2)
					{	
						System.exit(0);
					}
				}
			}
			if(gp.ui.titleScreenState == 1)
			{
				gp.ui.commandNum = 0;
				if(code == KeyEvent.VK_ENTER)
				{
					gp.gameState = gp.quizState;
				}
			}
		}
		
		
		//play STATE
		if(gp.gameState == gp.playState)
		{
			if(code == KeyEvent.VK_UP)
			{
				upPressed = true;
			}
			if(code == KeyEvent.VK_DOWN)
			{
				downPressed = true;
			}
			if(code == KeyEvent.VK_LEFT)
			{
				leftPressed = true;
			}
			if(code == KeyEvent.VK_RIGHT) //VK_RIGHT
			{
				rightPressed = true;
				//System.out.println("condition enteredr");
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		if(code == KeyEvent.VK_P) {
			
			
			if(gp.gameState == gp.playState){
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState){
				gp.gameState = gp.playState;
			}
		}
		
		if(code == KeyEvent.VK_T)
		{
			if(checkDrawTime == false){
				checkDrawTime = true;
			}
			else if(checkDrawTime == true){
				checkDrawTime = false;	
			}
		}
		
		// DIALOUGE STATE
		else if(gp.gameState == gp.dialogueState)
		{
			if(code == KeyEvent.VK_ENTER)
			{
				gp.gameState = gp.playState;
				//gp.gameState = gp.dialogueState;
			}	
		}
		
		// DEBUG
		
	}
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode(); 
		
		if(code == KeyEvent.VK_UP)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_DOWN)
		{
			downPressed = false;
		}
		if(code == KeyEvent.VK_LEFT)
		{
			leftPressed = false;
		}
		if(code == KeyEvent.VK_RIGHT)
		{
			rightPressed = false;
		}
	}
}
