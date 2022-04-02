<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 수정</title>
<link href="mystyle.css" rel="stylesheet" type="text/css"> 
</head>
<body>
<h3 id="header">게시판 글 수정</h3>
<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>
<div id="main">
<form  name="f" method='post' action="update.do">
<input type='hidden' id="action" name='action' value="${param.action}">
<input type='hidden' name='num' value="${param.num}">
<table width='100%'>
<tr><td>제목</td><td><input type='text' name='title' value=${article.title }></td></tr>
<tr><td>이름</td>
<td><input type='text' name='writer' value=${article.writer}></td></tr>
<tr><td>암호</td>
<td><input type='password' name='password' value=${article.password}></td></tr>
<tr><td>내용</td><td>
<textarea cols='65' name='contents' rows='20'>${article.contents}</textarea>
</td></tr>
<tr><td colspan='2' align='center'>
 
<input type='submit' value="글 수정 저장"  > 
<input type='reset' value="글 수정 취소" >
</td></tr>
</table>
</form>
</div>
</body>
</html>