<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 작성</title>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<script>
function send(){
	var cmd = document.getElementById("action").value;
	if(cmd=="reply"){
		document.f.action="reply.do";
		document.f.submit();
	}else{
		document.f.action="write.do";
		document.f.submit();
	}
	
}
</script>
</head>
<body>
<h3 id="header">게시판 글 작성</h3>
<form  name="f" method='post' action="">
<input type='hidden' id="action" name='action' value="${param.action}">
<input type='hidden' name='rnum' value="${param.num}">
<table width='100%'>
<tr><td>제목</td><td><input type='text' name='title'></td></tr>
<tr><td>이름</td>
<td><input type='text' name='writer'></td></tr>
<tr><td>암호</td>
<td><input type='password' name='password'></td></tr>
<tr><td>내용</td><td>
<textarea cols='65' name='contents' rows='20'></textarea>
</td></tr>
<tr><td colspan='2' align='center'>
<a href="./list.do">글목록</a>
<input type='button' value="글쓰기 저장"  onclick="send()"> 
<input type='reset' value="글쓰기 취소" >
</td></tr>
</table>
</form>
</body>
</html>