public abstract class Bullet extends Actor {
	public float velocity;
	public float damage;
	@Override
	void update() {
		position.y += velocity;
		if (position.y- size.y / 2 < 0.0 || position.y + size.y/2 > Game.screenHeight) {
			ActorManager.getSingleton().performDestruction(this);
			return;
		}
		
		collide();
	}
	
	abstract void collide();
}
