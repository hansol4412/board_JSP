package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.BoardItem;
import domain.Page;

public class BoardDaoItemImpl implements BoardItemDao {
	
	public static Connection dbConn() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.23:3306/kopo41", "root", "kopo41");
		return conn;
	}

	@Override
	public List<BoardItem> getList() {
		List<BoardItem> boardList = new ArrayList<BoardItem>();
		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select id, title, date, content, viewcnt from boardItem order by id desc;");
			while (rset.next()) {
				BoardItem board = new BoardItem(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
				boardList.add(board);
			}
			rset.close();
			stmt.close();
			dbConn().close(); 

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return boardList;
	}
	
	@Override
	public List<BoardItem> getListWithPaging(String pageno){
		List<BoardItem> boardList = new ArrayList<BoardItem>();
		
		PageProcess pp = new PageProcess();
		Page pageProcess = pp.pagination(pageno, null, null);

		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select id, title, date, content, viewcnt from boardItem order by id desc limit "+((pageProcess.getPageno()-1)*10)+", 10;");
			while (rset.next()) {
				BoardItem board = new BoardItem(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
				boardList.add(board);
			}
			rset.close();
			stmt.close();
			dbConn().close();

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return boardList;
	}
	
	@Override
	public List<BoardItem> findListWithPaging(String pageno, String criteria, String findWord) {
		List<BoardItem> boardList = new ArrayList<BoardItem>();
		PageProcess pp = new PageProcess();
		Page pageProcess = pp.pagination(pageno, criteria, findWord);

		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select id, title, date, content, viewcnt from boardItem where "+criteria+" like '%"+findWord+"%' order by id desc limit "+((pageProcess.getPageno()-1)*10)+", 10;");
			while (rset.next()) {
				BoardItem board = new BoardItem(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
				boardList.add(board);
			}
			rset.close();
			stmt.close();
			dbConn().close();

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return boardList;
	}

	@Override
	public BoardItem read(int bno) {
		BoardItem board = null;
		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select * from boardItem where id="+bno+";");
			if (rset.next()) {
				board = new BoardItem(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
			}
			rset.close();
			stmt.close();
			dbConn().close(); 

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return board;
	}
	
	@Override
	public void insert(BoardItem board) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql="insert into boardItem(title, date, content, viewcnt) values('"+board.getTitle()+"', '"+board.getDate()+"', '"+board.getContent()+"',0)";
	        stmt.execute(sql); 
			stmt.close();
			dbConn().close(); 

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void update(BoardItem board) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql = "update boardItem set title='"+board.getTitle()+"', date ='"+board.getDate()+"', content='"+board.getContent()+"' where id="+board.getId()+";";
	        stmt.executeUpdate( sql );
			stmt.close();
			dbConn().close(); 
		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void delete(BoardItem board) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql="delete from boardItem where id = " + board.getId();
	        stmt.executeUpdate( sql ); 
			stmt.close();
			dbConn().close(); 

		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	@Override
	public int getTotalCount(){
		int totalcount=0;
			try {
				Statement stmt = dbConn().createStatement();
				ResultSet rset = stmt.executeQuery("select count(*) from boardItem;");
				if(rset.next()) {
					totalcount = rset.getInt(1);   
				}
				rset.close();
				stmt.close();
				dbConn().close();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return totalcount;
	}
	
	@Override
	public int findTotalCount(String criteria, String findWord) {
		int totalcount=0;
		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select count(*) from boardItem where "+criteria+" like '%"+findWord+"%' order by id desc;");
			if(rset.next()) {
				totalcount = rset.getInt(1); 
			}
			//System.out.println(totalcount);
			rset.close();
			stmt.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalcount;
	}

	@Override
	public void viewCnt(int bno, int viewCnt) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql = "update boardItem set viewcnt="+(viewCnt+1)+" where id="+bno+";";
	        stmt.executeUpdate( sql );
			stmt.close();
			dbConn().close(); 
		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
	
}
