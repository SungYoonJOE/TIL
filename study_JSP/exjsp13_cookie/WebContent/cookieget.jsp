<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	Cookie[] cookies = request.getCookies();
	for(int i=0; i<cookies.length; i++){
		String str = cookies[i].getName();
		if(str.equals("cookieN")){
			System.out.println("cookie["+i+"] name: "+cookies[i].getName()+"<br/>");
			System.out.println("cookie["+i+"] value: "+cookies[i].getValue()+"<br/>");
			System.out.println("=============================== <br/>");
		}
	}
	%>
	<a href="cookiedel.jsp">cookie delete</a>
</body>
</html>