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
		
		if(cookies != null){
			for(int i=0; i<cookies.length; i++){
				if(cookies[i].getValue().equals("abcde")){
					cookies[i].setMaxAge(0); //"abcde"����
					response.addCookie(cookies[i]); //��� ������ cookie ž��
				}
			}
		}
		//response.sendRedirect("login.html");
		response.sendRedirect("cookietest.jsp");
	%>
</body>
</html>