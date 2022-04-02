<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta charset="utf-8">
<title> Ajax 업로드</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function () { 
	$('#btnSubmit').click(function (event) {		 
		var form = $('#uploadForm')[0];
		var data = new FormData(form);
		$('#btnSubmit').prop('disabled', true);
		$.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url : 'upload.do', 
            data: data,
            cache:false,
            timeout:600000,
             processData: false,
            contentType: false,
            success: function (data) {
                $('#result').text(data);
                console.log('SUCCESS : ', data);
                $('#btnSubmit').prop('disabled', false);
            },
            error: function (e) {
                $('#result').text(e.responseText);
                console.log('ERROR : ', e);
                $('#btnSubmit').prop('disabled', false);
            }
            });
            });
            });

</script>	
</head>
<body>
<h3>Ajax 업로드</h3>
<form id="uploadForm"  enctype="multipart/form-data">
	학번: <input type="text" name="studentNumber" />
	<br/>
	리포트파일: <input type="file" name="report" />
	<br/>
	<input type="button" id="btnSubmit" value="제출"/>
</form>

 <h1>결과</h1>
 <span id="result">
 
 </span>

</body>
</html>
