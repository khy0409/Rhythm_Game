package Game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic_5B.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;

	public int gety() {
		return y;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public String getNoteType() {
		return noteType;
	}
	
	public Note(String noteType) {
		if (noteType.equals("D")) {
			x = 250;
		} else if (noteType.equals("F")) {
			x = 411;
		} else if (noteType.equals("SPACE")) {
			x = 572;
		} else if (noteType.equals("J")) {
			x = 733;
		} else if (noteType.equals("K")) {
			x = 894;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 680) {
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void close() {
		proceeded = false;
	}
	public String judge() {
        if (y >= 660) {
            System.out.println("Late");
            close();
            return "Late";
        } else if (y >= 640) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (y >= 620) {
            System.out.println("Cool");
            close();
            return "Cool";
        } else if (y >= 560) {
            System.out.println("Perfect");
            close();
            return "Perfect";
        } else if (y >= 500) {
            System.out.println("Cool");
            close();
            return "Cool";
        } else if (y >= 460) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (y >= 420) {
            System.out.println("Early");
            close();
            return "Early";
        } else if (y >= 400) {
            System.out.println("Miss");
            close();
            return "Miss";
        }
        return "None";
    }

}
