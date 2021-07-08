package service;

import java.util.List;

import domain.Reply;

public interface ReplyService {
	public List<Reply> getList(int bno);
	public void insert(Reply reply);
	public void update(Reply reply);
	public void delete(Reply reply);
	public int getTotalCount(int bno);
	public int getLastReplyId(int bno);
}
