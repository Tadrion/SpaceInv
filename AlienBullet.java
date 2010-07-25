
public class AlienBullet extends Bullet {
	public AlienBullet() {
		velocity = 3.0f;
		damage = 15.0f;
	}

	@Override
	void collide() {
		Ship player = PlayerController.getSingleton().ship;
		if ( (position.x > player.position.x - player.size.x / 2) &&
				   (position.x < player.position.x + player.size.x / 2) &&
				   (position.y > player.position.y - player.size.y / 2) &&
				   (position.y < player.position.y + player.size.y / 2) ) {
					player.getHit(this);
		}
	}

}
