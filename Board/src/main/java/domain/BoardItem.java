package domain;


public class BoardItem {
	private int id;
	private String title;
	private String date;
	private String content;
	private int viewcnt;
	private int commentcnt;
	private int boardId;
	
	public BoardItem(String title, String date, String content) {
		this.title = title;
		this.date = date;
		this.content = content;
		this.viewcnt = 0;
		this.commentcnt = 0;
	}
	
	public BoardItem(int id, String title, String date, String content, int viewcnt, int boardId) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.viewcnt = viewcnt;
		this.boardId = boardId;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViewcnt() {
		return viewcnt;
	}
	
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getCommentcnt() {
		return commentcnt;
	}

	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
		
}

