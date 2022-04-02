$(document).ready(function () { 
	$('#btnSubmit').click(function (event) {
		var form = $('#uploadForm')[0];
		var data = new FormData(from);
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
                $('#result').text('제출완료');
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
            
		
 