package Game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			file = new File(Main.class.getResource("../musics/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			this.isLoop = isLoop;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTime() {
		if (player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play(); // 음악이 끝나기 전까진 이 위치에서 코드가 벗어나지 않는다.
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
