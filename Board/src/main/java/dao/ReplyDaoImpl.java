package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Reply;

public class ReplyDaoImpl implements ReplyDao{
	
	public static Connection dbConn() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.104:3306/kopo41", "root", "kopo41");
		return conn;
	}
	
	@Override
	public List<Reply> getList(int bno) {
		List<Reply> replyList = new ArrayList<Reply>();

		try {
			Statement stmt = dbConn().createStatement();
			
			ResultSet rset = stmt.executeQuery("select * from reply where boardItemId="+bno+";");
			while (rset.next()) {
				Reply reply = new Reply(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getString(4));
				replyList.add(reply);
			}
			
			rset.close();
			stmt.close();
			dbConn().close(); 
		} catch(ClassNotFoundException e) {
			e.getStackTrace();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return replyList;
	}
	 
	@Override
	public void insert(Reply reply) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql="insert into reply(boardItemId ,replyId, date, content) values("+reply.getBoardItemId()+","+reply.getReplyId()+", date(now()), '"+reply.getContent()+"')";
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
	public void update(Reply reply) {
		Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Statement stmt = dbConn().createStatement();
			String sql = "update reply set date ='"+sd.format(date)+"', content='"+reply.getContent()+"' where boardItemId= "+reply.getBoardItemId()+" and replyId= "+reply.getReplyId()+";";
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
	public void delete(Reply reply) {
		try {
			Statement stmt = dbConn().createStatement();
			String sql="delete from reply where boardItemId="+reply.getBoardItemId()+" and replyId= "+reply.getReplyId()+";";
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
	public int getTotalCount(int bno) {
		int totalcount=0;
		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select count(*) from reply where boardItemId="+bno+";");
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
	public int getLastReplyId(int bno) {
		int LastReplyId=0;
		try {
			Statement stmt = dbConn().createStatement();
			ResultSet rset = stmt.executeQuery("select replyId from reply where boardItemId = "+bno+" order by replyId desc limit 1;");
			if(rset.next()) {
				LastReplyId = rset.getInt(1);   
			}
			rset.close();
			stmt.close();
			dbConn().close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LastReplyId;
	}
	
	

}
