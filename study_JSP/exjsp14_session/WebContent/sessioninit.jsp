<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%
		session.setAttribute("mySessionName", "mySessionData");
	//session 내부객체 이용. setAttribute()로 속성지정
		session.setAttribute("myNum", 12345);
	%>
	
	<a href="sessionget.jsp">session get</a>

</body>
</html>