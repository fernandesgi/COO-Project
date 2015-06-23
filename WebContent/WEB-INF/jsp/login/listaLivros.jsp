<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exemplo EP2</title>
</head>
<body>
	<br><br><br><br>
	
		<center>
			<table border=1>
				<tr>
					<td><b>Autor</b></td><td><b>Título</b></td>
				</tr>

			<c:forEach items="${livros}" var="livro">
				<tr>
					<td>${livro.autor}</td>
					<td>${livro.titulo}</td>
				</tr>
			</c:forEach>
		</table>
		</center>
</body>
</html>