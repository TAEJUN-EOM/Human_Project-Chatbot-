<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 : 게시글 내용 보기 </title>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<script>
function show(cmd, url) {
	var d = document.getElementById('enter');
	d.style.display = 'block';
	var form = document.forms.my;	
	form.attributes.action.value = url;
	form.action.value = cmd;
	if(cmd == 'modify') {
		form.submit.value = '글 수정';
	} else if(cmd == 'delete' || cmd == 'delete_r') {
		form.submit.value = '글 삭제';
	}
}

function hide() {
	var d = document.getElementById('enter');
	d.style.display = 'none';  
  }


var init = false;
function edit() {
	var d = document.getElementById('comment');
	if(init == false) {
		d.contents.value='';
		init = true;
	}
}

function vote() {
  	var win = open('vote.html','w','width=200,height=200');
  }

function mydelete(id) {
  	var d = document.getElementById(id);
	d.style.display = 'block';
	var bttn = 'b' + id;
	var b = document.getElementById(bttn);
	b.style.display = 'none';
  }

</script>
</head>
<body>
<body><h3 id="header">게시판 : 게시글 내용 보기 </h3>
<div id="main">
<table width=100%> 
<!--게시물-->
	<tr><td>제목 :  ${article.title} </b><br>
	 작성자 : ${article.writer}<br>
	 작성일 : ${article.regdate} <br>
	 조회수 : ${article.rcount} &nbsp; &nbsp;
	 추천수 : ${article.vcount} &nbsp;
	 <span style='font-size:70%'>
	<c:if test='${not isReply}'>
	<a href=./vote.do?action=vote&num=${article.num}  onClick='vote()' target='w'>추천하기</a>
	 </c:if>		
	<c:if test='${isReply}'>
	<a href=./vote.do?action=vote_r&num=${article.num}  onClick='vote()' target='w'>추천하기</a>
	 </c:if>	</span>
	 <br>
	</td></tr>
	<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
	<tr><td colspan=2><br>
	${article.contents}	
	<br><br>
	</td></tr>
	<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
</table>
	
<!-- 버튼 메뉴 -->	
<table width='100%'> 	
<tr><td><div class='button'><a href="./list.do">목록보기</a></div></td>
<c:choose>
<c:when test='${empty isReply}'>
<td><div class='button'>
	<a href="javascript:show('modify', './modify.do?action=modify&num=${article.num}')">수정하기</a>
	</div></td>
	<c:if test='${article.size == 0}'>
	<td><div class='button'>
	<a href="javascript:show('delete','./delete.do')">삭제하기</a>
	</div></td>
	</c:if>		
	<td><div class='button'>
	<a href=./reply.do?action=reply&num=${article.num}>답글쓰기</a>
	</div></td>
	</c:when>
	<c:otherwise>
	<td><div class='button'>
	<a href="javascript:show('modify', './modify.do?action=modify_r&num=${article.num}')">수정하기</a>
	</div></td>
	<c:if test='${article.size == 0}'>
	<td><div class='button'>
	<a href="javascript:show('delete_r','./delete.do')">삭제하기</a>
	</div></td>
	</c:if>	
	<td><div class='button'>
	<a href=./reply.do?action=reply_r&num=${article.num}>답글쓰기</a>
	</div></td>	
	</c:otherwise>
	</c:choose>
	<td><div class='button'>
	<a href="./write.do">글쓰기</a>
	</div></td></tr>
</table>

<!-- 글 수정 및 삭제를 위한 암호 입력 -->
<div id='enter' style='display:none'>
<form method=post action='./board.do'  name='my'>
암호 <input type=password name=password size=5>
<input type=hidden  name='num' value='${article.num}'>
<input type=hidden  name='action' value=''>
<input type=submit name='submit' value='x'>
<input type=reset name='reset' value='숨기기' onClick='hide()'>
	</form>
</div>

<!-- 댓글 읽기 -->
<table width='99%'>
<c:forEach var="comment" items="${article.comments}">
    <tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
     <tr><td width=100>
	 <b>${comment.writer}</b><br>
     <span style='font-size:70%'>${comment.regdate}<br>
	 ${comment.ip} &nbsp; &nbsp;	
     <button id='b${comment.num}' 
	 	onClick='mydelete(${comment.num})'>x</button>	
	 </span>
     <!-- 댓글 삭제 폼 -->
	 <span id='${comment.num}' style='display:none'>
      <form method=post action=./delete_comment.do>
       암호 <input type=password name=password size=5>
		<input type=hidden name='num' value='${comment.num}'>
		<input type=hidden name='read_num' value='${article.num}'>
		<input type=submit value='삭제'>
     </form>	 
	 </span>
	 </td><td>${comment.contents}</td></tr>
	 </c:forEach>
<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
</table>

<!-- 댓글쓰기 -->
<form id='comment' action='./comment.do' method="post"> 
<div style='text-align:center'>
<table width='95%'>
	<tr><td align=center>
	<textarea name='contents' cols='65' rows='5' onFocus='edit()'>
댓글을 작성해주세요
	</textarea>
	</td></tr>
	<tr><td align=right>
	이름<input type='text' name='writer' size='10'> 
	암호<input type='password' name='password' size='10'>
	<input type='submit' value='저장'></td></tr>
</table>
</div>
<input type='hidden' name='action' value='comment'>
<c:choose>
	<c:when test='${empty isReply}'>
	<input type='hidden' name='num' value='${article.num}'>
	</c:when>
	<c:otherwise>
	<input type='hidden' name='num' value='-${article.num}'>
	</c:otherwise>
</c:choose>

</form>
</body>
</html>