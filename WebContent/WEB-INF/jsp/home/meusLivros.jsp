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
<title>Meus Livros</title>
</head>
<body>
	<center>
		<table border=1>
			<tr>
				<td><b>Autor</b></td>
				<td><b>Título</b></td>
			</tr>

			<c:forEach items="${livros}" var="livro">
				<tr>
					<td>${livro.autor}</td>
					<td>${livro.titulo}</td>
				</tr>
			</c:forEach>
		</table>
	</center>

	<div class="col-sm-6 col-sm-offset-3">
		<a href="deletaLivro" type="button" class="btn btn-danger">Deletar Livro</a>
	</div>
	<div class="col-sm-6 col-sm-offset-3">
		<a href="home" type="button" class="btn btn-login">Voltar</a>
	</div>
</body>
</html>