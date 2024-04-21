package entity;

import main.GamePanel;
import java.util.*;

public class NPC_oldMan extends Entity 
{
	public NPC_oldMan(GamePanel gp){
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	public void getImage()
	{
		up1 = setUp("/NPC/oldman_up_1");
		down1 = setUp("/NPC/oldman_down_1");
		//up2 = setUp("/NPC/oldman_up_2");
		//down2 = setUp("/NPC/oldman_down_2");
		left1 = setUp("/NPC/oldman_left_1");
		right1 = setUp("/NPC/oldman_right_1");
		left2 = setUp("/NPC/oldman_left_2");
		right2 = setUp("/NPC/oldman_right_2");
	}
	
	public void setDialogue()
	{
		
		dialogues[0] = "HOLA, Welcome to Graph Thoery Concept's game";
		dialogues[1] = "Press enter each TIME to continue";
		dialogues[2] = "to know more ask NANA the wise man";
		dialogues[3] = "Help Milli reach her school ";
		dialogues[4] = "Meet her Friend, go to lib";
		dialogues[5] =	"Go to another's friend home for work";	
		dialogues[5] =	"Don't forget to grab a snack from Bakery";	
		dialogues[6] = "Tip: make sure to collect each key";
		dialogues[7] = "Continue, don't give up "; 
	}
	
	public void setAction()
	{
		actionLockCounter++;
		
		if(actionLockCounter == 120)
		{
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i<25)
			{
				direction = "up";
			}
			if(i>25 && i<=50)
			{
				direction = "down";
			}			
			if(i>50 && i<=75)
			{
				direction = "left";
			}
			if(i>75 && i<=100)
			{
				direction = "right";
			}
			actionLockCounter =0;
		}
	}
	public void speak() {
		
		super.speak();
	}
}