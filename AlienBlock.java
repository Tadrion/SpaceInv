import java.util.Random;
import java.util.Vector;

public class AlienBlock {
	Vector<Alien> aliens;
	public Alien mostLeft;
	public Alien mostRight;
	public Alien mostBottom;
	Random rand = new Random();
	
	public void createAliens(int hor, int ver) {
		aliens = new Vector<Alien>();
	
		for (int i = 0; i < ver; i++)
			for(int j = 0; j < hor; j++) {
				Alien alien = ActorManager.getSingleton().createAlien(
						new Vector2(50 + j * 55, 70 + i * 60));
				alien.velocity.x = 2;
				alien.parentBlock = this;
				aliens.add(alien);
			}
		findMostLeftRight();
	}
	
	public void findMostLeftRight() {
		if (aliens.size() > 0) {
		mostLeft = aliens.get(0);
		mostRight = aliens.get(0);
		mostBottom = aliens.get(0);
		
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).role != Alien.AlienRole.DEFENSIVE)
				continue;
			if (aliens.get(i).position.x < mostLeft.position.x)
				mostLeft = aliens.get(i);
			if (aliens.get(i).position.x > mostRight.position.x)
				mostRight = aliens.get(i);
			if (aliens.get(i).position.y > mostBottom.position.y)
				mostBottom = aliens.get(i);
		}
		}
	}
	
	public void update() {
		if(aliens.size() > 0) {
		if (	
				(( mostRight.velocity.x > 0) && (mostRight.position.x + mostRight.size.x/2 + mostRight.velocity.x > Game.screenWidth)) || 
				(( mostLeft.velocity.x < 0) && (mostLeft.position.x - mostLeft.size.x/2 + mostLeft.velocity.x < 0))	 
				)
		{
			for (int i = 0; i < aliens.size(); i++ ) {
				if (aliens.get(i).role == Alien.AlienRole.DEFENSIVE) {
				aliens.get(i).position.y += 10;
				aliens.get(i).velocity.x = -1.02f*aliens.get(i).velocity.x;
				}
			}
			
			if (mostBottom.position.y + mostBottom.size.y/2 > Game.screenHeight - mostLeft.size.y) {
				GameStateManager.getSingleton().gameState = GameStateManager.GameState.GS_LOSER;
			}
			
		}
		int shootProb = rand.nextInt(10);
		if (shootProb == 0) {
			int shipIndex = rand.nextInt(aliens.size());
			aliens.get(shipIndex).shoot();
		}
		
		int attackProb = rand.nextInt(150);
		if (attackProb == 0) {
			int shipIndex = rand.nextInt(aliens.size());
			aliens.get(shipIndex).attack();
			this.findMostLeftRight();
		}
		}
	}
	
}
