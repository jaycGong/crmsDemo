<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- <link ref="stylesheet" href="../static/css/bootstrap.min.css" />
<link ref="stylesheet" href="../static/css/main_d.css" /> -->
</head>
<body> 
<div>
	<div>
        <div class="title_6">欢迎用户：</div>
        ${user.name}
	</div>
</div>


<%-- <script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/vue.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/base/sync.js"></script> --%>


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
		    if(this.status == 200){
		        alert(this.responseText);
		        var data= JSON.parse(this.responseText);
		        if(data && data.retcode=="200"){
		        	alert(data.body);
		        	location.href="/center";
		        }
		    }
		  };
		  //发送数据
		  xhr.send(formData);
	}
	
	
	  
	
/* 	function sendAjax() {
		  //构造表单数据
		  var formData = new FormData();
		  formData.append('username', 'johndoe');
		  formData.append('id', 123456);
		  //创建xhr对象 
		  var xhr = new XMLHttpRequest();
		  //设置xhr请求的超时时间
		  xhr.timeout = 3000;
		  //设置响应返回的数据格式
		  xhr.responseType = "text";
		  //创建一个 post 请求，采用异步
		  xhr.open('POST', '/server', true);
		  //注册相关事件回调处理函数
		  xhr.onload = function(e) { 
		    if(this.status == 200||this.status == 304){
		        alert(this.responseText);
		    }
		  };
		  xhr.ontimeout = function(e) { ... };
		  xhr.onerror = function(e) { ... };
		  xhr.upload.onprogress = function(e) { ... };
		  
		  //发送数据
		  xhr.send(formData);
		} */
</script>
