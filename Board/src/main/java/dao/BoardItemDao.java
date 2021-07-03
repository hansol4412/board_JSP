package dao;

import java.util.List;

import domain.BoardItem;

public interface BoardItemDao {
	public List<BoardItem> getList();
	public List<BoardItem> getListWithPaging(String pageno);
	public List<BoardItem> findListWithPaging(String pageno, String criteria, String findWord);
	public BoardItem read(int bno);
	public void insert(BoardItem board);
	public void update(BoardItem board);
	public void delete(BoardItem board);
	
	public int getTotalCount();
	public int findTotalCount(String criteria, String findWord);
	public void viewCnt(int bno, int viewCnt);
}
