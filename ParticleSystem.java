import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Vector;


public class ParticleSystem extends Actor{ 
	
	public float radiusAtten;
	public float colorAtten;
	public float velocityAtten;
	public Vector<Particle> particles;
	
	Random rand = new Random();
	
	public ParticleSystem(
			int quan,
			float minSpeed,
			float maxSpeed,
			float minRadius,
			float maxRadius,
			int minLife,
			int maxLife,
			Color minColor,
			Color maxColor)
	{
		particles = new Vector<Particle>();
		for (int i = 0; i < quan; i++) {
			Particle p = new Particle();
			p.position = new Vector2();
			p.ps = this;
			p.velocity = Vector2.mult(minSpeed + rand.nextFloat() * (maxSpeed - minSpeed),
					new Vector2 (rand.nextFloat() - 0.5f, rand.nextFloat() - 0.5f).normalisedCopy() );
		
			p.color = new Color(
				minColor.getRed() + rand.nextInt(maxColor.getRed() - minColor.getRed() ),
				minColor.getGreen() + rand.nextInt(maxColor.getGreen() - minColor.getGreen() ),
				minColor.getBlue() + rand.nextInt(maxColor.getBlue() - minColor.getBlue())
			);
			
			p.radius = minRadius + rand.nextFloat() * (maxRadius - minRadius);
			p.life = minLife + rand.nextInt() * (maxLife - minLife);		
			particles.add(p);
		}
	}
	
	@Override
	void update() {
		for (int i = particles.size() - 1; i >= 0; i--) {
			particles.get(i).update();
			if (particles.get(i).life <= 0)
				particles.remove(i);
		}
		if (particles.isEmpty()) {
			ActorManager.getSingleton().performDestruction(this);
		}
	}
	
	@Override
	void draw(Graphics2D g) {
		for (int i = 0 ; i < particles.size(); i++ ) {
				particles.get(i).draw(g);
			}
		}
}
