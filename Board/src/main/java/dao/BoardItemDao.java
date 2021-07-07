package dao;

import java.util.List;

import domain.BoardItem;

public interface BoardItemDao {
	public List<BoardItem> getList(); // ������
	public List<BoardItem> getListWithPaging(String pageno, String boardId);
	public List<BoardItem> findListWithPaging(String pageno, String criteria, String findWord,  String boardId);
	public BoardItem read(int bno);
	public void insert(BoardItem board);
	public void update(BoardItem board);
	public void delete(BoardItem board);
	
	public int getTotalCount(String boardId);
	public int findTotalCount(String criteria, String findWord, String boardId);
	public void viewCnt(int bno, int viewCnt);
}
