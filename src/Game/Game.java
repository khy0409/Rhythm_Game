package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	private Image noteRouteImage_5B = new ImageIcon(Main.class.getResource("../images/noteRoute_5B.png")).getImage();
	private Image noteBasicImage_5B = new ImageIcon(Main.class.getResource("../images/noteBasic_5B.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image judgeImage;

	private Image noteRouteImage_D = noteRouteImage_5B;
	private Image noteRouteImage_F = noteRouteImage_5B;
	private Image noteRouteImage_SPACE = noteRouteImage_5B;
	private Image noteRouteImage_J = noteRouteImage_5B;
	private Image noteRouteImage_K = noteRouteImage_5B;
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		gameMusic.start();
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteImage_D, 250, 30, null); // 1 == D
		g.drawImage(noteRouteImage_F, 411, 30, null); // 2
		g.drawImage(noteRouteImage_SPACE, 572, 30, null); // 3
		g.drawImage(noteRouteImage_J, 733, 30, null); // 4
		g.drawImage(noteRouteImage_K, 894, 30, null); // 5
		
		g.drawImage(noteRouteLineImage, 246, 30, null);
		g.drawImage(noteRouteLineImage, 407, 30, null);
		g.drawImage(noteRouteLineImage, 568, 30, null);
		g.drawImage(noteRouteLineImage, 729, 30, null);
		g.drawImage(noteRouteLineImage, 890, 30, null);
		g.drawImage(noteRouteLineImage, 1051, 30, null);
		
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if(note.gety() > 600) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/Miss.png")).getImage();
				judgeEvent("Miss");
			}
			
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}else {
			note.screenDraw(g);
			}
		}
		
		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		
		g.drawString("D", 250 + 70, 609);
		g.drawString("F", 411 + 70, 609);
		g.drawString("SPACE", 572 + 30, 609);
		g.drawString("J", 733 + 75, 609);
		g.drawString("K", 894 + 75, 609);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("000000", 575, 702);
		g.drawImage(judgeImage, 460, 420, null);
	}
	
	@Override
	public void run() {
		dropNotes();
	}
	
	public void pressD() {
		judge("D");
		noteRouteImage_D = new ImageIcon(Main.class.getResource("../images/noteRoutePressed_5B.png")).getImage();
	}
	
	public void releaseD() {
		noteRouteImage_D = noteRouteImage_5B;
	}
	
	public void pressF() {
		judge("F");
		noteRouteImage_F = new ImageIcon(Main.class.getResource("../images/noteRoutePressed_5B.png")).getImage();
	}
	
	public void releaseF() {
		noteRouteImage_F = noteRouteImage_5B;
	}
	
	public void pressSPACE() {
		judge("SPACE");
		noteRouteImage_SPACE = new ImageIcon(Main.class.getResource("../images/noteRoutePressed_5B.png")).getImage();
	}
	
	public void releaseSPACE() {
		noteRouteImage_SPACE = noteRouteImage_5B;
	}
	
	public void pressJ() {
		judge("J");
		noteRouteImage_J = new ImageIcon(Main.class.getResource("../images/noteRoutePressed_5B.png")).getImage();
	}
	
	public void releaseJ() {
		noteRouteImage_J = noteRouteImage_5B;
	}
	
	public void pressK() {
		judge("K");
		noteRouteImage_K = new ImageIcon(Main.class.getResource("../images/noteRoutePressed_5B.png")).getImage();
	}
	
	public void releaseK() {
		noteRouteImage_K = noteRouteImage_5B;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes() {
		Beat[] beats = null;
		int startTime = 1000;
		int gap = 0;
		
		if(titleName.equals("School Song(SangilMedia ver.)")) {
			startTime = 1000 - Main.REACH_TIME*1000;
			gap = 71;
			if(difficulty.equals("Easy")) {
				beats = new Beat[] {
						new Beat(startTime + (gap * 4), "D"),
						new Beat(startTime+ (gap * 8), "F"),
						new Beat(startTime+ (gap * 12), "SPACE"),
				};
			}else if(difficulty.equals("Hard")) {
				beats = new Beat[] {
						new Beat(startTime + (gap * 4), "D"),
						new Beat(startTime+ (gap * 8), "F"),
						new Beat(startTime+ (gap * 12), "SPACE"),
				};
			}
		}
		
		int i = 0;
		boolean dropped = false;
		
		while( beats != null&& (!isInterrupted()) && i<beats.length ) {
			dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}if(!dropped) {
				try {
					Thread.sleep(5);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}

	}
	
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/"+ judge +".png")).getImage();
		}
		
	}
	
}
