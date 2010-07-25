
public class ShipBullet extends Bullet{
	
	public ShipBullet() {
		velocity = -5.0f;
		damage = 1.0f;
	}

	@Override
	void collide() {
		for (int i = 0; i < ActorManager.getSingleton().actors.size(); i++) {
			Actor actor = ActorManager.getSingleton().actors.get(i);
			if (actor instanceof Alien) {
				Alien alien = (Alien)actor;
				if ( (position.x > alien.position.x - alien.size.x / 2) &&
				   (position.x < alien.position.x + alien.size.x / 2) &&
				   (position.y > alien.position.y - alien.size.y / 2) &&
				   (position.y < alien.position.y + alien.size.y / 2) ) {
					alien.getHit(this);
					break;
				}
				}
			}
		}
		
	}
