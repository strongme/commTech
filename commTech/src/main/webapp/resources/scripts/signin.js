$(function() {
	 $(document).foundation();
	 $('#studentid').focus();
});

function sign() {
	if(checkform()) {
		document.forms['signin'].submit();
	}else {
		alert('请填写编号和密码');
	}
}

function reset_() {
	document.forms['signin'].reset();
}

function checkform() {
	var teacherid  = document.getElementById('studentid').value;
	var p1 = document.getElementById('p1').value;
	if(teacherid&&p1) {
		return true;
	}else{
		return false;
	}
}

function isExist(studentid) {
	if(!studentid)return;
	$.post(
			basepath+'/isExist',
			{studentid:studentid},
			function(data,status) {
				if(!data) {
					alert('学号"'+studentid+'"不存在');
					document.getElementById('studentid').value = '';
					$('#studentid').focus();
				}
			},'json'
	);
}
