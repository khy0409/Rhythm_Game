package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rhythm_Game extends JFrame {
	
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonImage = new ImageIcon(Main.class.getResource("../images/easyButton.png"));
	private ImageIcon hardButtonImage = new ImageIcon(Main.class.getResource("../images/hardButton.png"));
	private ImageIcon backButtonImage = new ImageIcon(Main.class.getResource("../images/backButton.png"));
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.jpg")));
	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));

	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private JButton easyButton = new JButton(easyButtonImage);
	private JButton hardButton = new JButton(hardButtonImage);
	private JButton backButton = new JButton(backButtonImage);
	
	Music introMusic = new Music("sangil_song.mp3", true);
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	
	private int nowSelected = 0;
	
	int mouseX = 0;
	int mouseY = 0;
	
	boolean isMainScreen = false;
	boolean isGameScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<>();
	
	public static Game game;
	
	public Rhythm_Game() {

		trackList.add(new Track("sangil_title.png", "sangil_start.jpg", "sangil_game.jpg", 
				"sangil_song cut.mp3", "sangil_song.mp3", "School Song(SangilMedia ver.)"));
		trackList.add(new Track("Light It Up_title.png", "Light It Up_start.jpg", "Light It Up_game.jpg",
				"Light It Up cut.mp3", "Robin Hustin x TobiMorrow - Light It Up.mp3", "Robin Hustin x TobiMorrow - Light It Up"));
		trackList.add(new Track("Toxic vibration_title.png", "Toxic vibration_start.png", "Toxic vibration_game.jpg",
				"Toxic vibration cut.mp3", "BEMANI - TOXIC VIBRATION.mp3", "BEMANI - TOXIC VIBRATION"));
		
		introMusic.start();
		
		setUndecorated(true); // 맨 윗부분 데코 없애는 코드
		setTitle("Rhythm_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1920 x 1080
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // task kill
		setVisible(true);
		
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // absolute 컴포넌트 좌표지정해서 정렬하는 방식
		
		addKeyListener(new KeyListener());
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		exitButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		exitButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				System.exit(0);
			}
		});
		
		add(exitButton);
		
		startButton.setBounds(800, 200, 400, 100);
		startButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		startButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		startButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			
			
			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 시작 이벤트
				introMusic.close();
				
				enterMain();
			}
		});
		
		add(startButton);
		
		quitButton.setBounds(800, 330, 400, 100);
		quitButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		quitButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		quitButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 종료 이벤트
				System.exit(0);
			}
		});
		
		add(quitButton);
		
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		leftButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		leftButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		leftButton.setVisible(false);
		
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 곡 선택 - left
				selectLeft();
			}
		});
		
		add(leftButton);
		
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		rightButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		rightButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		rightButton.setVisible(false);
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 곡 선택 - right
				selectRight();
			}
		});
		
		add(rightButton);
		
		easyButton.setBounds(375, 550, 250, 67);
		easyButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		easyButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		easyButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		easyButton.setVisible(false);
		
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 난이도 선택 - easy
				gameStart(nowSelected, "Easy");
			}
		});
		
		add(easyButton);
		
		hardButton.setBounds(655, 550, 250, 67);
		hardButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		hardButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		hardButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		hardButton.setVisible(false);
		
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 난이도 선택 - easy
				gameStart(nowSelected, "Hard");
			}
		});
		
		add(hardButton);
		
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false); // 버튼에 마우스 포인터 올렸을 때 뜨는 테두리 삭제
		backButton.setContentAreaFilled(false); // 버튼 뒷배경 삭제
		backButton.setFocusPainted(false); // 버튼 자잘한 테두리 삭제
		backButton.setVisible(false);
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 포인터가 컴포넌트 안에 들어왔을 때
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 포인터가 컴포넌트 밖으로 나갔을 때
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스를 클릭했을 때
				// 메인 화면으로 Back
				backMain();
			}
		});
		
		add(backButton);
		
		menuBar.setBounds(0, 0, Main.SCREEN_WIDTH, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		
		add(menuBar);
		
		
	}
	
	public void paint(Graphics g) {
		// Scene
		// 씬 #01
		// 씬 #02
		// JAVA는 씬 개념이 없어
		
		// C언어에서 Scene 개념
		// 기존 화면을 지우고
		// 새 화면을 변수에서 불러온다
		
		// Java에서 Scene 만드는 방법
		// 기존 이미지를 지우고
		// 새 이미지를 변수에서 불러온다
		// 이미지를 자연스럽게 교체한다 (Buffer)
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
		//g.setFont(new Font("Arial", Font.BOLD, 40));
		//g.drawString("asdfadf", 500, 200);
	}

	private void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 70, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		
		if (isGameScreen) {
			game.screenDraw(g);
		}
		
		try {
			Thread.sleep(5);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		paintComponents(g);
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/" + 
		trackList.get(nowSelected).getGameImage())).getImage();
		
		isGameScreen = true;
		setFocusable(true);
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
	}
	
	public void backMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainbackground.jpg")).getImage();
		isMainScreen = true;
		isGameScreen = false;
		
		selectTrack(nowSelected);
		
		game.close();
	}
	
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainbackground.jpg")).getImage();
		isMainScreen = true;
		
		selectTrack(0);
	}
}
