<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8">
<title>주문 완료</title>
</head>
<body>
주문을 완료했습니다.
<ul>
	<li>주문번호: ${orderResult.orderInfo.order_id}</li>
	<li>상품번호: ${orderResult.item.item_id}</li>
	<li>주문가격: ${orderResult.paymentInfo.price} 원</li>
	<li>주문 고객 ID: ${orderResult.orderInfo.costomer_id}</li>
	<li>주소: ${orderResult.orderInfo.address}</li>
</ul>
</body>
</html>