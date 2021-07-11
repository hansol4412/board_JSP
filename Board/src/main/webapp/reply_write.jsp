<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*, javax.sql.*, java.net.*, java.io.*,java.text.*, java.util.Date" %>
<%@ page import="domain.*, service.*" %>
<html>
<head> 
</head>
<body>
    <h1>reply write</h1>
    <head>
    
</head>

<body>
    <% 
        String mode = request.getParameter( "key" ); 
        String gongjiId = request.getParameter( "gongjiId" ); 
        String replyId = request.getParameter( "replyId" ); 
        String replyContent = request.getParameter( "replyContent" ); 
	    String replyContentHan = new String(replyContent.getBytes("8859_1"), "utf-8");
	    
	    ReplyService crudR = new ReplyServiceImpl();
	    Reply reply = new Reply(Integer.parseInt(gongjiId), Integer.parseInt(replyId) ,replyContentHan);
	    
        if(mode.equals("insert")){
        	crudR.insert(reply);
        } else if(mode.equals("update")){
        	crudR.update(reply);
        } else if(mode.equals("delete")){
        	crudR.delete(reply);
        }

        request.setAttribute("gongjiId", gongjiId);
    %>
   
	<c:redirect url="view.jsp" >
	    <c:param name="key" value="${gongjiId}" />
	</c:redirect>  
</body>
</html></html>