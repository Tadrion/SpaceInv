import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ActorManager {
	private static ActorManager instance = null;
	
	public static ActorManager getSingleton() {
		if (instance == null) {
			instance = new ActorManager();
		}
		return instance;
	}
	Vector <Actor> actors;
	Vector <Actor> destructionQueue;
	Hashtable <String, BufferedImage> bitmaps;
	Ship player;
	AlienBlock alienBlock;
	Random rand;
	void init() {
		actors = new Vector<Actor>();
		destructionQueue = new Vector<Actor>();
		rand = new Random();
	
		if ( bitmaps == null) {
			bitmaps = new Hashtable<String, BufferedImage>();
		}
		
		for (int i = 0; i < 1000 ; i++) {
			createStar();
		}
	}
	
	void loadBitmaps(URL base) {
			bitmaps.put("ship", loadBitmap(base,"ship.png"));
			bitmaps.put("shipBullet", loadBitmap(base,"shipbullet.png"));
			bitmaps.put("alien", loadBitmap(base,"alien.png"));
			bitmaps.put("alienBullet", loadBitmap(base,"alienbullet.png"));
	}
	
	BufferedImage loadBitmap(URL base, String filename) {
		try {
			URL url = new URL(base, filename);
			return ImageIO.read(url);
			} catch (IOException e) {
				System.out.println("Nie ma obrazka " + filename + ".");
			}
			return null;
	}
	
	void updateActors() {
		alienBlock.update();
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).update();
		}
		
		for(int i = 0; i < destructionQueue.size(); i++) {
			actors.remove(destructionQueue.get(i));
			if (destructionQueue.get(i) instanceof Alien ) {
				Alien alien = (Alien)destructionQueue.get(i);
				alien.parentBlock.aliens.remove(alien);
				alien.parentBlock.findMostLeftRight();
			}
		}
		destructionQueue.clear();
		
		if (alienBlock.aliens.isEmpty()) {
			GameStateManager.getSingleton().gameState = GameStateManager.GameState.GS_WINNER;
		}
	}
	
	void drawActors(Graphics2D g) {
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).draw(g);
		}
	}
	
	public void createAlienBlock(int hor, int ver) {
		alienBlock = new AlienBlock();
		alienBlock.createAliens(hor, ver);
	}
	
	Ship createShip(Vector2 pos) {
		Ship ship = new Ship();
		ship.position = pos;
		ship.shootDelay = 500;
		ship.size = new Vector2(50,50);
		ship.hitPoints = 100.0f;
		ship.image = bitmaps.get("ship");
		actors.add(ship);
		return ship;
	}
	
	Alien createAlien(Vector2 pos) {
		Alien alien = new Alien();
		alien.position = pos;
		alien.shootDelay = 300;
		alien.size = new Vector2(50,50);
		alien.hitPoints = 2.0f;
		alien.image = bitmaps.get("alien");
		if (alien.image == null)
			System.exit(0);
		alien.role = Alien.AlienRole.DEFENSIVE;
		actors.add(alien);
		return alien;
	}
	
	ShipBullet createShipBullet(Vector2 pos) {
		ShipBullet shipBullet = new ShipBullet();
		shipBullet.size = new Vector2(6,15);
		shipBullet.position = pos;
		shipBullet.image = bitmaps.get("shipBullet");
		actors.add(shipBullet);
		return shipBullet;
	}
	
	AlienBullet createAlienBullet(Vector2 pos) {
		AlienBullet alienBullet = new AlienBullet();
		alienBullet.size = new Vector2(9,16);
		alienBullet.position = pos;
		alienBullet.image = bitmaps.get("alienBullet");
		actors.add(alienBullet);
		return alienBullet;
	}
	
	public void performDestruction(Actor actor) {
		for (int i = 0; i < actors.size(); i++){
			if (actors.get(i) == actor) {
				destructionQueue.add(actor);
				break;
			}
		}
	}
	
	ParticleSystem createParticleSystem(
			Vector2 pos,
			int quan,
			float minSpeed,
			float maxSpeed,
			float minRadius,
			float maxRadius,
			int minLife,
			int maxLife,
			Color minColor,
			Color maxColor) {
		ParticleSystem ps = new ParticleSystem(quan, minSpeed, maxSpeed, minRadius, maxRadius, minLife, maxLife,
					minColor, maxColor);
		ps.position = pos;
		actors.add(ps);
		return ps;
	}
	
	Star createStar() {
		Star star = new Star();
		star.position = new Vector2((float)rand.nextInt((int)Game.screenWidth), 
									(float)rand.nextInt((int)Game.screenHeight));
		star.brightness = 64 + rand.nextInt(64);
		star.velocity = 3.0f * rand.nextFloat();
		
		int x = rand.nextInt(100);
		star.size = (x < 70 ) ? 1 : ( (x < 97) ? 2 : 3 ) ;
		
		actors.add(star);
		return star;
	}

}
