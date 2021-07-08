package service;

import java.util.List;

import dao.ReplyDao;
import dao.ReplyDaoImpl;
import domain.Reply;

public class ReplyServiceImpl implements ReplyService{
	ReplyDao replyDao = new ReplyDaoImpl();
	
	@Override
	public List<Reply> getList(int bno) {
		return replyDao.getList(bno);
	}

	@Override
	public void insert(Reply reply) {
		replyDao.insert(reply);
	}

	@Override
	public void update(Reply reply) {
		replyDao.update(reply);
	}

	@Override
	public void delete(Reply reply) {
		replyDao.delete(reply);
	}

	@Override
	public int getTotalCount(int bno) {
		return replyDao.getTotalCount(bno);
	}

	@Override
	public int getLastReplyId(int bno) {
		return replyDao.getLastReplyId(bno);
	}
	
}
