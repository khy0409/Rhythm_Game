package Game;

public class Track {
	// 제목 이미지
	// 게임 선택 창 표지 이미지
	// 인게임 이미지
	// 선택 창 음악
	// 인게임 음악
	
	private String titleImage;
	private String startImage;
	private String gameImage;
	private String startMusic;
	private String gameMusic;
	private String titleName;
	
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic,
			String titleName) {
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	
}
