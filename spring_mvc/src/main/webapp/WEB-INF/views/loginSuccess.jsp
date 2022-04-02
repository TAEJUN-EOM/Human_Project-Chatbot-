<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
<c:if test="${!empty user}">
<font color="blue">${user.userid}님 환영합니다.</font><br>
<a href="<c:url value='/view.do?userid=${user.userid}'/>">고객정보 수정</a><br>  
<a href="<c:url value='/list.do' />">고객  정보 리스트</a><br>  
<a href="<c:url value='/logout.do' />">로그 아웃</a><br> 
</c:if>
</body>
</html>