<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*, javax.sql.*, java.net.*, java.io.*, java.text.*, java.util.Date" %>
<%@ page import="domain.*, service.*" %>
<html>
<head>

</head>
<body>
    <%
    String mode = request.getParameter( "key" ); 
                        String id = request.getParameter( "id" ); 
                        String title = request.getParameter( "title" ); 
                	    String titleHan = new String(title.getBytes("8859_1"), "utf-8");
                        String date = request.getParameter("date"); 
                        String content = request.getParameter( "content" ); 
                        String contentHan = new String(content.getBytes("8859_1"), "utf-8");
                        String boardId = request.getParameter( "boardId" ); 

                    	BoardItemService crud = new BoardItemServiceImpl();	
                   	 	BoardItem board = new BoardItem(Integer.parseInt(id), titleHan ,date ,contentHan, 0, Integer.parseInt(boardId));
                    
                    	if(mode.equals("INSERT")){
                           	crud.insert(board);

                        } else if(mode.equals("UPDATE")){
                	crud.update(board);

                        } else if(mode.equals("DELETE")){

                        	crud.delete(board);
                        }
      request.setAttribute("board", board);
    %>
    <c:redirect url="list.jsp?boardId=${board.boardId}" />
</body>
</html>