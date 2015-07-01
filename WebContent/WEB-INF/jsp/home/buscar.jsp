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
<title>Resultados</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<table class="table">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Autor</th>
						<th>Editora</th>
						<th>ISBN</th>
					</tr>
				</thead>
				<tbody>
						<tr>
						<td><c:out value="${livro.titulo}" /></td>
						<td><c:out value="${livro.autor}" /></td>
						<td><c:out value="${livro.editora}" /></td>
						<td><c:out value="${livro.isbn}" /></td>
						<td><a href="solicitar" type="button" class="btn btn-login">Solicitar</a></td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>