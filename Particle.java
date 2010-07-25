import java.awt.Color;
import java.awt.Graphics2D;


public class Particle {
	public ParticleSystem ps;
	public Vector2 position;
	public Vector2 velocity;
	public float radius;
	public Color color;
	public int life;
	
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillOval(
				(int)(ps.position.x + position.x - radius*0.5f),
				(int)(ps.position.y + position.y - radius*0.5f),
				(int)radius, (int)radius
				);
	}
	
	public void update() {
		position = Vector2.add(position,velocity);
		radius *= ps.radiusAtten;
		velocity = Vector2.mult(ps.velocityAtten,velocity);
		color = new Color (
				(int)Math.max(0.0f, Math.min((float)color.getRed() * ps.colorAtten, 255)),
				(int)Math.max(0.0f, Math.min((float)color.getGreen() * ps.colorAtten, 255)),
				(int)Math.max(0.0f, Math.min((float)color.getBlue() * ps.colorAtten, 255))
				);
		life--;
	}		
	
}