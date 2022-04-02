<%@ page   contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
<center><h3>로그인</h3>
<hr>
<form action="login.do" method="POST">
<table>
<tr><td colspan="2" align="center">로그인</td></tr>
<tr><td> 아이디 </td>
<td><input type="text" name="userid"></td></tr>
<tr><td> 비밀번호 </td>
<td><input type="password" name="userpwd"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="로그인">
<input type="reset" value="취소"></td></tr>
</table>
</form>
</center>
</body>
</html>


