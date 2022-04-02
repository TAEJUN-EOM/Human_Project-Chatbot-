<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
</head>
<body>
<c:if test="${empty user}">
<font color="blue">${userid}님 아이디가 존재하지 않거나, 비밀번호 오류입니다.</font><br>
<a href="<c:url value='/login.do' />">로그인 </a><br>  
<a href="<c:url value='/join.do' />">회원가입</a><br> 
</c:if>
</body>
</html>