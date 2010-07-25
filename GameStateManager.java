import java.awt.Color;
import java.awt.Graphics2D;
import java.net.URL;


public class GameStateManager {
	private static GameStateManager instance = null;
	
	public static GameStateManager getSingleton() {
		if (instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}
	
	public enum GameState {
		GS_WAITING,
		GS_PLAYING,
		GS_LOSER,
		GS_WINNER
	}
	
	public GameState gameState;
	int level = 3;
	
	public void init(URL documentBase) {
		gameState = GameState.GS_WAITING;
		//level = 3;
		ActorManager.getSingleton().init();
		ActorManager.getSingleton().loadBitmaps(documentBase);
	
		PlayerController.getSingleton().ship = ActorManager.getSingleton().createShip(new Vector2 
				(Game.screenWidth/2 - 25.0f/2,
				Game.screenHeight - 25.0f));
		
		ActorManager.getSingleton().createAlienBlock(8, level);
	}
	
	public void gameStep(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, (int)Game.screenWidth, (int)Game.screenHeight);
		
		switch (gameState) {
		
		case GS_WAITING: 
			{
				graphics.setColor(Color.red);
				graphics.drawString("Nacisnij spacje by rozpoczac gre", 200, 400);
				
				if (PlayerController.getSingleton().spaceDown) {
					gameState = GameState.GS_PLAYING;
				}
				
				break;
			}
		case GS_PLAYING: 
			{
				PlayerController.getSingleton().update();
				ActorManager.getSingleton().updateActors();
				ActorManager.getSingleton().drawActors(graphics);
				
				graphics.setColor(Color.red);
				String lifeBar = "Zycie: ";
				for (int i = 0; i < (int)PlayerController.getSingleton().ship.hitPoints; i++) {
					lifeBar = lifeBar + "|";
				}
				
				graphics.drawString(lifeBar, 10, 30);
				break;
			}
			
		case GS_WINNER:
			{
				graphics.setColor(Color.red);
				graphics.drawString("Wygrales!", 250, 400);
				graphics.setColor(Color.red);
				graphics.drawString("Nacisnij spacje by przejsc do nast poziomu", 250, 500);
				
				if (PlayerController.getSingleton().spaceDown) {
					ActorManager.getSingleton().init();
					level++;
					PlayerController.getSingleton().ship = ActorManager.getSingleton().createShip(new Vector2 
							(Game.screenWidth/2 - 25.0f/2,
							Game.screenHeight - 25.0f));
					ActorManager.getSingleton().createAlienBlock(8, level);
					gameState = GameState.GS_PLAYING;
				}
				
				break;
			}
		case GS_LOSER:
			{
				graphics.setColor(Color.red);
				graphics.drawString("Przegrales!", 250, 400);
				
				graphics.setColor(Color.red);
				graphics.drawString("Nacisnij spacje by rozpoczac nowa gre", 250, 500);
				
				if (PlayerController.getSingleton().spaceDown) {
					ActorManager.getSingleton().init();
					PlayerController.getSingleton().ship = ActorManager.getSingleton().createShip(new Vector2 
							(Game.screenWidth/2 - 25.0f/2,
							Game.screenHeight - 25.0f));
					level = 3;
					ActorManager.getSingleton().createAlienBlock(8, level);
					gameState = GameState.GS_PLAYING;
				}
				
				break;
			}
		}
			
		}
						
	}

