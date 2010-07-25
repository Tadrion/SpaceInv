
public class PlayerController {
	private static PlayerController instance = null;
	
	public static PlayerController getSingleton() {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}
	
	Ship ship;
	public boolean leftDown = false;
	public boolean rightDown = false;
	public boolean spaceDown = false;
	
	public void update() {
		if(leftDown) {
			turnLeft();
		}
		else if(rightDown) {
			turnRight();
		}
		else {
			stopTurn();
		}
		
		if (spaceDown) {
			shoot();
		}
	}
	
	public void turnLeft() {
		ship.velocity = new Vector2(-3,0);
	}
	
	public void turnRight() {
		ship.velocity = new Vector2(3,0);	
	}

	public void stopTurn() {
		ship.velocity = new Vector2(0,0);
	}
	
	public void shoot() {
		ship.shoot();
	}
	
}
