package object;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
public class OBJ_tent extends SuperObject
{
	GamePanel gp;
	public OBJ_tent(GamePanel gp)
	{
		this.gp = gp;
		name = "tent";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/tent.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}