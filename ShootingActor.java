
public abstract class ShootingActor extends FlyingActor {
	long lastShootTime;
	long shootDelay;
	@Override
	public void update() {
		super.update();
	}
	
	public void shoot() {
		long curTime = System.currentTimeMillis();
		if (curTime > lastShootTime + shootDelay){
			lastShootTime = curTime;
			performShoot();
		}
	}
	
	public abstract void performShoot();
	
	
}
