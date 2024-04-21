package object;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_chest extends SuperObject
{
	GamePanel gp;
	public OBJ_chest(GamePanel gp)
	{
		this.gp = gp;
		name = "chest";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/trophy.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		collision = true;
	}
}