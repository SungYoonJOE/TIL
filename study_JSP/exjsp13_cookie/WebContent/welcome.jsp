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
	
		for(int i=0; i<cookies.length; i++){ //cookie ���� ��ȸ
			String id = cookies[i].getValue();
			if(id.equals("abcde")){
				System.out.println(id+"�� �ȳ��ϼ���."+"<br/>");
			}
		}
	%>
	<a href="logout.jsp">�α׾ƿ�</a>
</body>
</html>