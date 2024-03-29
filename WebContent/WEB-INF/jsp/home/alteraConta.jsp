<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/index.css" rel="stylesheet" type="text/css" />
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/index.js"></script>
<title>Altera Conta</title>
</head>
<body>
	<form id="change-form" action="configuracoes?sucesso" method="post" role="form">
		<div class="form-group">
			<input type="password" name="senha" id="password" tabindex="1" class="form-control" placeholder="Senha atual" required>
		</div>
		<div class="form-group">
			<input type="password" name="nova" id="password" tabindex="2" class="form-control" placeholder="Nova senha" required>
		</div>
		<div class="form-group">
			<input type="password" name="confirmation" id="confirmpassword" tabindex="3" class="form-control" placeholder="Confirme a nova Senha" required>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<input type="submit" name="alterar" id="alterar" tabindex="4" class="form-control btn btn-register" value="Alterar">
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-sm-offset-3">
			<a href="configuracoes" type="button" class="btn btn-warning">Voltar</a>
		</div>
	</form>
</body>
</html>