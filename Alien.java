import java.awt.Color;

public class Alien extends ShootingActor{

	public AlienBlock parentBlock;
	@Override
	public void update() {
		if (role == Alien.AlienRole.OFFENSIVE) {
			velocity.y = 3.5f;
			velocity.x = 
				(float) Math.sin(position.y/10) + 
				(PlayerController.getSingleton().ship.position.x - this.position.x) / 60.0f ;
		    
			if (Vector2.sub(PlayerController.getSingleton().ship.position, this.position).length() < this.size.x) {
				GameStateManager.getSingleton().gameState = GameStateManager.GameState.GS_LOSER;
			}
			
			if (position.y - size.y / 2 > Game.screenHeight) {
				ActorManager.getSingleton().performDestruction(this);
			}
			
		}
		super.update();
	}
	
	public void attack() {
		role = Alien.AlienRole.OFFENSIVE;
	}
	
	public enum AlienRole {OFFENSIVE,DEFENSIVE};
	public AlienRole role;
	
	@Override
	public void performShoot() {
		ActorManager.getSingleton().createAlienBullet(
				Vector2.add(position, new Vector2 (0,size.y/2)));
	}

	@Override
	public void getHit(Bullet bullet) {
		hitPoints -= bullet.damage;
		
		ActorManager.getSingleton().performDestruction(bullet);
		if (hitPoints <= 0) {
			ParticleSystem ps = ActorManager.getSingleton().createParticleSystem(position, 50, 
					0.5f, 3.0f, 5, 10, 20, 100,
					new Color(100, 50,0),
					new Color(255,128,1) );
			ps.colorAtten = 0.98f;
			ps.radiusAtten = 0.98f;
			ps.velocityAtten = 1.02f;
			ActorManager.getSingleton().performDestruction(this);
		}
	}	
}
