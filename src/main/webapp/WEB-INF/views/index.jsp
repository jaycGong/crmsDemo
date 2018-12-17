<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body> 
<div>
	<div class="bg_3"></div>
	<div class="form_3 xy_center has_nav_3">
        <div class="title_6">账号</div>
        <input class="input_1" id="name" maxlength="12">
        <div class="title_6">密码</div>
        <input type="password" id="pwd" maxlength="12">
	</div>
        <button type="button" class="btn_block" id="loginBtn" onclick="login()">登录</button>
</div>
</body>
</html>
<script src="http://passport.cnblogs.com/scripts/jsencrypt.min.js"></script>
<script>
	function login(url)
	{
		 var formData = new FormData();
		 var encrypt = new JSEncrypt();
         encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/fQ+z91P1/uYJKaQ2bMtlO+wZYcVfMMsmCEgPEE3YYrkUX6XqJe7xQ2KMlrR5R45f/1wUg1mnKtGLuBHmZVkQwd1LO0aXAkUdYAWitoJW0SMIkvk1bTehggX7fUE0aKUpSCkIykOF0LOn8UEADXgIRF6dKd67t2Lwcpn4CHmsYwIDAQAB");
		 formData.append('name', document.getElementById("name").value);
		 formData.append('pwd', encrypt.encrypt(document.getElementById("pwd").value));
		  //创建xhr对象 
		  var xhr = new XMLHttpRequest();
		  //设置xhr请求的超时时间
		  xhr.timeout = 3000;
		  //设置响应返回的数据格式
		  xhr.responseType = "text";
		  //创建一个 post 请求，采用异步
		  xhr.open('POST', '/api/login', true);
		  //注册相关事件回调处理函数
		  xhr.onload = function(e) { 
		    var data= JSON.parse(this.responseText);
		    if(this.status == 200){
		        if(data && data.retcode=="200"){
		        	alert(data.body);
		        	location.href="/center";
		        }
		    }else{
		        alert(data.message);
		    }
		  };
		  xhr.send(formData);
	}

</script>
