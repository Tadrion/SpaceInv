import java.awt.Color;

public class Ship extends ShootingActor {

	@Override
	public void update() {
		if ( (position.x + size.x/2 + velocity.x > Game.screenWidth) || 
			( position.x - size.x/2 + velocity.x <= 0.0f) ){
			velocity.x = 0.0f;
		}
		super.update();
	}

	@Override
	public void performShoot() {
		ActorManager.getSingleton().createShipBullet(
				Vector2.add(position, new Vector2 (0,-size.y/2)));
		}

	@Override
	public void getHit(Bullet bullet) {
		hitPoints -= bullet.damage;
		
		ActorManager.getSingleton().performDestruction(bullet);

		ActorManager.getSingleton().performDestruction(bullet);
		ParticleSystem ps = ActorManager.getSingleton().createParticleSystem(bullet.position, 10, 0.5f, 
					3.0f, 2, 5, 20, 100, 
					new Color(0,100,100),
					new Color(1,255,255));
		ps.colorAtten = 0.98f;
		ps.radiusAtten = 0.98f;
		ps.velocityAtten = 1.02f;
		
		if (hitPoints <= 0) {
			GameStateManager.getSingleton().gameState = GameStateManager.GameState.GS_LOSER;
		}
		
	}
}
