import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends Applet implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	
	public static float screenWidth = 800;
	public static float screenHeight = 600;
	
	Image buffer;
	Graphics2D graphics;
	Thread animator;
	public void init() {
		buffer = createImage((int)screenWidth,(int)screenHeight);
		graphics = (Graphics2D) buffer.getGraphics();
		graphics.setFont(new Font("Arial",Font.PLAIN, 20));
		addKeyListener(this);
		
		GameStateManager.getSingleton().init(getDocumentBase());
		
		}
	
	public void start() {
		animator = new Thread(this);
		animator.start();
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			PlayerController.getSingleton().rightDown = true;
			break;
		case KeyEvent.VK_LEFT:
			PlayerController.getSingleton().leftDown = true;
			break;
		case KeyEvent.VK_SPACE:
			PlayerController.getSingleton().spaceDown = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			PlayerController.getSingleton().rightDown = false;
			break;
		case KeyEvent.VK_LEFT:
			PlayerController.getSingleton().leftDown = false;
			break;
		case KeyEvent.VK_SPACE:
			PlayerController.getSingleton().spaceDown = false;
			break;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(Thread.currentThread() == animator ) {
			GameStateManager.getSingleton().gameStep(graphics);
			repaint();
			
			try{
				Thread.sleep(10);
				} catch(InterruptedException e){
				break;
			}
		}
		
	}

}
