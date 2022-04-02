<%@page import="lab.board.vo.Article"%>
<%@page import="lab.board.controller.BoardListServlet"%>
<%@page import="java.util.Vector"%> 
<%@ page contentType="text/html;charset=utf-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	int numPerPage = BoardListServlet.numPerPage;
	int numPerBlock = BoardListServlet.numPerBlock;
	
	void printReplies(JspWriter o, Article h, String rootNum, 
		int depth) throws java.io.IOException {
		Vector<Article>  v = h.getReplies();
		int n = v.size();
		for(int i=0; i < n; i++) {
			Article r =  v.elementAt(i);
			o.print("<tr id='" + rootNum + "_" + h.getNum() + 
			        "' style='display:none'>");
			o.print("<td>&nbsp;</td> <td>");
			for(int k =0; k < depth; k++) {
				o.print("<img src=./image/blank.gif>");
			}			
			if(r.getSize() > 0) {
				o.print("<a id=link" + h.getNum() + "_" + r.getNum() + 
				" href='javascript:show(\"" + h.getNum() + "_" + 
				r.getNum() + "\");'>+</a>");
			} else {
				o.print("&nbsp;");
			}
			o.print(" <a href=/list.do?action=read_r&num=" + r.getNum() + 
			        ">" + r.getTitle() + "</a></td>");
			o.print("<td>" + r.getWriter() + "</td>");
			o.print("<td>" + r.getRegdate() + "</td>");
			o.print("<td style='text-align:right'>" + 
			         r.getRcount() + "</td>");
			o.println("</tr>");
			printReplies(o, r, rootNum, depth+1);
		}
	}
%>
<html><head><title>게시판</title>
<script>
	function show(id) {
		var d = document.getElementsByName(id);
		var len = d.length;
		
		for(i = 0; i < len; i++) {
			d[i].style.display = 'block';
		}
		var link = 'link' + id;
		
		var a = document.getElementById('link' + id);
		a.childNodes[0].nodeValue = '-';
		a.href = "javascript:hide('" + id + "')";
	}
	
	function hide(id) {
		var d = document.getElementsByName(id);
		var len = d.length;
		
		for(i = 0; i < len; i++) {
			d[i].style.display = 'none';
		}
		var a = document.getElementById('link' + id);
		a.childNodes[0].nodeValue = '+';
		a.href = "javascript:show('" + id + "')";
	}
</script>
</head>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<body><h3 id="header">게시판</h3>

<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>

<div id="main">
<table width=100%>
	<tr><th>번호</th><th>제목</th><th>작성자</th>
		<th>작성일</th><th>조회수</th></tr>
<c:forEach var="row" items="${headers}">
	<tr><td colspan=5 height=1 background=./image/dotline.gif></td></tr>
	<tr><td>${row.num}</td><td>
		<c:if test='${row.size > 0}'>
		<a id='link${row.num}' 
		   href='javascript:show("${row.num}");' >+</a>
		</c:if>
		<a href=./read.do?action=read&num=${row.num}>${row.title}</a>
		</td>
		<td>${row.writer}</td>
		<td>${row.regdate}</td>
		<td style='text-align:right'>${row.rcount}</td>
		
<!-- 답글 --> 
 
<c:forEach var="reply" items="${row.replies}">
		<tr id='${row.num}' >
		<td>&nbsp;&nbsp;</td>
		<td>		    	   
			<img src=./image/blank.gif>
			<c:if test='${reply.size > 0}'>			
			<a id='link${row.num}_${reply.num}' 
			href='javascript:show("${row.num}_${reply.num}");' >+</a>
			</c:if>	
		<a href=./read.do?action=read_r&num=${reply.num}>
		    ${reply.title}</a>
		</td>
		<td>${reply.writer}</td>
		<td>${reply.regdate}</td>
		<td style='text-align:right'>${reply.rcount}</td>
		</tr> 
<%	Article  h=(Article)pageContext.getAttribute("reply");
	Article  c=(Article)pageContext.getAttribute("row");
	printReplies(out, h, String.valueOf(c.getNum()), 2); %>		
</c:forEach>
</c:forEach>
<tr><td colspan=5 height=1 background=./image/dotline.gif></td></tr>
</table>
<div style='text-align:right'><br><br>
	<a href=./write.do>글작성</a>
</div>
<!-- 페이지 번호 -->
	<div style="text-align:center">	
<%	
	Integer p = (Integer) request.getAttribute("pageNo");//현재페이지
	//ex) 34페이지인경우   페이지 bloc은 31~40   , 331~340글번호 목록
	int mypage = p.intValue();
    int currentBlock = (int)Math.ceil(mypage / (double)numPerBlock);
	Integer tp = (Integer) request.getAttribute("totalPage"); //전체 페이지수
	double totalPage = tp.intValue();
	int totalBlock = (int)Math.ceil(totalPage / numPerBlock);
	if(totalBlock > currentBlock) { 
		int togo = (currentBlock + 1) * numPerBlock;
		if(togo > totalPage)
			togo = (int) totalPage; %>
		<a href=./list.do?page=<%=togo%>> << </a>
<%	}
	for(int i = numPerBlock; i > 0; i--) {
		int pn = numPerBlock * (currentBlock-1) + i;
		if(pn > totalPage)
			continue;
		if(pn == mypage) { %>
		<a href=./list.do?page=<%=pn%>>
		<span style="text-decoration:underline"><%=pn%></span></a>&nbsp;
<%		} else { %>
		<a href=./list.do?page=<%=pn%>><%=pn%></a>&nbsp		
<%		}
	}
	if(currentBlock > 1) { %>
		<a href=./list.do?page=<%= (currentBlock-1)*numPerBlock %>> >> </a>
<%	} %> </div> 
</div>

</body>
</html>
