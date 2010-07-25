
public abstract class FlyingActor extends Actor{

	public float hitPoints;
	public Vector2 velocity = new Vector2();
	int score;
	
	@Override
	void update() {
		position = Vector2.add(position, velocity);
	}
	
	public abstract void getHit(Bullet bullet);

}
