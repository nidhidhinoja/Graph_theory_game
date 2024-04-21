package main;

import object.OBJ_key;
import object.OBJ_Door;
import object.OBJ_Boots;
import object.OBJ_chest;
import entity.NPC_oldMan;
		
public class AssetSetter
{
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;	
	}
			
	public void setObject()
	{
		gp.obj[0] = new OBJ_key(gp);
		gp.obj[0].worldX = 35* gp.tileSize;
		gp.obj[0].worldY = 2* gp.tileSize;  //done
				
		gp.obj[1] = new OBJ_key(gp);
		gp.obj[1].worldX = 38* gp.tileSize;
		gp.obj[1].worldY = 26* gp.tileSize; //donee-lib
		
		gp.obj[2] = new OBJ_key(gp);
		gp.obj[2].worldX = 11* gp.tileSize; //35*2
		gp.obj[2].worldY = 22* gp.tileSize;  //done
		
		gp.obj[3] = new OBJ_key(gp);
		gp.obj[3].worldX = 2* gp.tileSize;
		gp.obj[3].worldY = 31* gp.tileSize;  //donee
		
		//gp.obj[4] = new OBJ_Door(gp);
		//gp.obj[4].worldX = 32* gp.tileSize;
		//gp.obj[4].worldY = 38* gp.tileSize;
		
		//gp.obj[5] = new OBJ_Door(gp);
		//gp.obj[5].worldX = 38* gp.tileSize;
		//gp.obj[5].worldY = 27* gp.tileSize;   //done
		
		//gp.obj[6] = new OBJ_Door(gp);
		//gp.obj[6].worldX = 40* gp.tileSize;
		//gp.obj[6].worldY = 29* gp.tileSize;
		
		//gp.obj[7] = new OBJ_Door(gp);
		//gp.obj[7].worldX = 30* gp.tileSize;
		//gp.obj[7].worldY = 48* gp.tileSize;  //done
		
		gp.obj[8] = new OBJ_chest(gp);
		gp.obj[8].worldX = 40* gp.tileSize;
		gp.obj[8].worldY = 48* gp.tileSize;
	}
	public void setNpc()
	{
		
		gp.npc[0] = new NPC_oldMan(gp);
		gp.npc[0].worldX = 9* gp.tileSize;
		gp.npc[0].worldY = 5* gp.tileSize;
	}

}