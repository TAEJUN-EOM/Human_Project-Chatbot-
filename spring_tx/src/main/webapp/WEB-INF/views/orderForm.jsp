<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8">
<title>주문 Form</title>
</head>
<body>
<form   action="order.do"  method="post">
<p>
	<label for="item_id">상품 </label>
	<select name="item_id" id="item_id">
	<option value="1">iPad Air</option>
	<option value="2">LG Ultra Gram</option>
	<option value="3">삼성 BookPro</option>
	<option value="4">삼성 Pad8</option>
	</select>
</p>

<p>
	<label for="id">구매자  </label>
	<input  id="costomer_id" name="costomer_id" type="text" />
</p>	
<p>
	<label for="id">배송 주소</label>
	<input   id="address" name="address" type="text" />
</p>
<p>
	<input type="submit" value="주문">
</p>
</form>
</body>
</html>



