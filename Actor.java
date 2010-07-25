import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Actor {
	Vector2 position;
	Vector2 size;
	BufferedImage image;
	
	abstract void update();
	void draw(Graphics2D g){
		g.drawImage(image, (int)(position.x - size.x / 2), (int)(position.y - size.y / 2), null);
	}	 
}
