import java.awt.Color;
import java.awt.Graphics2D;


public class Star extends Actor {

	public float velocity;
	public int brightness;
	public int size;
	
	@Override
	void update() {
		if (position.y >= Game.screenHeight)
			position.y = 0.0f;
		position = Vector2.add(position, new Vector2(0.0f, velocity));	
	}

	@Override 
	void draw(Graphics2D g) {
	    Color color = new Color(brightness, brightness, brightness);
		g.setColor(color);
		g.fillRect((int)position.x, (int)position.y, size, size);
	}

}
