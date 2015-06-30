<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/index.css"
	rel="stylesheet" type="text/css" />
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap/js/index.js"></script>
<title>Configuracoes</title>
</head>
<body>
	<div class="col-sm-6 col-sm-offset-3">
		<a href="alteraConta" type="button" class="form-control btn btn-info">Alterar Conta</a>
	</div>
	<div class="col-sm-6 col-sm-offset-3">
		<a href="deletaConta" type="button" class="form-control btn btn-danger">Deleta Conta</a>
	</div>
</body>
</html>