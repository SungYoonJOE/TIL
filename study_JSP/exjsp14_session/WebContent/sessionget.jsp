<%@page import="java.util.Enumeration"%>
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
		Object obj1 = session.getAttribute("mySessionName");
	//getAttribute()의 반환값은 무조건 Object 타입.
		String mySessionName = (String)obj1;
		out.println(mySessionName +"<br />");
		
		Object obj2 = session.getAttribute("myNum");
		Integer myNum = (Integer)obj2;
		out.println(myNum +"<br />");
		
		out.println("************************ <br />");
		
		String sName;
		String sValue;
		Enumeration enumeration = session.getAttributeNames();
		//모든 name을 다 받아올 수 있음
		while(enumeration.hasMoreElements()){
			sName = enumeration.nextElement().toString();
			sValue = session.getAttribute(sName).toString();
			out.println("sName : " + sName + "<br />");
			out.println("sValue : " + sValue + "<br />");
		}
		
		out.println("************************ <br />");
		
		String sessionID = session.getId();
		//하나의 브라우저당 생기는 고유의 세션 id
		out.println("sessionID : " + sessionID + "<br />");
		int sessionInter =  session.getMaxInactiveInterval();
		//세션 유효시간
		out.println("sessionInter : " + sessionInter + "<br />");
		
		out.println("************************ <br />");
		//세션 삭제
		session.removeAttribute("mySessionName"); 
		Enumeration enumeration1 = session.getAttributeNames();
		while(enumeration1.hasMoreElements()){
			sName = enumeration1.nextElement().toString();
			sValue = session.getAttribute(sName).toString();
			out.println("sName : " + sName + "<br />");
			out.println("sValue : " + sValue + "<br />");
		}
		
		out.println("************************ <br />");
		
		//invalidate() 세션에 있는 모든 데이터를 다 삭제
		session.invalidate();
		if(request.isRequestedSessionIdValid()) {
			out.println("session valid");
		} else {
			out.println("session invalid");
		}
	%>

</body>
</html>