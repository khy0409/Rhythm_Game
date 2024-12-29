package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (Rhythm_Game.game == null) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Rhythm_Game.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Rhythm_Game.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Rhythm_Game.game.pressSPACE();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Rhythm_Game.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Rhythm_Game.game.pressK();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (Rhythm_Game.game == null) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Rhythm_Game.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Rhythm_Game.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Rhythm_Game.game.releaseSPACE();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Rhythm_Game.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Rhythm_Game.game.releaseK();
		}
	}
	
}
