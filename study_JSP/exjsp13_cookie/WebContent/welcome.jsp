<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
	
		for(int i=0; i<cookies.length; i++){ //cookie 내용 조회
			String id = cookies[i].getValue();
			if(id.equals("abcde")){
				System.out.println(id+"님 안녕하세요."+"<br/>");
			}
		}
	%>
	<a href="logout.jsp">로그아웃</a>
</body>
</html>