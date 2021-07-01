package domain;

public class Reply {
	private int boardItemId;
	private int replyId;
	private String date;
	private String content;
	
	public Reply(int boardItemId, int replyId, String date, String content) {
		super();
		this.boardItemId = boardItemId;
		this.replyId = replyId;
		this.date = date;
		this.content = content;
	}
	
	public Reply(int boardItemId, int replyId, String content) {
		super();
		this.boardItemId = boardItemId;
		this.replyId = replyId;
		this.content = content;
	}

	public int getBoardItemId() {
		return boardItemId;
	}
	
	public void setBoardItemId(int boardItemId) {
		this.boardItemId = boardItemId;
	}
	
	public int getReplyId() {
		return replyId;
	}
	
	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
	
}
