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
<title>Busca Livro</title>
</head>
<body>
	<form id="change-form" action="buscar" method="post" role="form">
	<div class="form-group">
		<input type="text" name="livro.titulo" id="titulo" tabindex="1" class="form-control" placeholder="Insira o Titulo" required>
	</div>
	<div class="form-group">
		<input type="text" name="livro.autor" id="autor" tabindex="2" class="form-control" placeholder="Insira o Autor" required>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<input type="submit" name="livrosearch" id="livro.search" tabindex="3" class="form-control btn btn-warning" value="Buscar">
			</div>
		</div>
	</div>
	</form>
</body>
</html>