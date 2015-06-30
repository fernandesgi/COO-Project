<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap/js/matchPassword.js"></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Entrar</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Cadastre-se</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="autentica" method="post"
									role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="usuario.login" id="username"
											tabindex="1" class="form-control" placeholder="Email"
											required>
									</div>
									<div class="form-group">
										<input type="password" name="usuario.password" id="password"
											tabindex="2" class="form-control" placeholder="Senha"
											required min=6>
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember">Mantenha-me
											Conectado</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login"
													value="Entrar">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="recover" tabindex="5" class="forgot-password">Esqueceu
														sua Senha?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="registra" method="post"
									role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="usuario.nome" id="nome" tabindex="1"
											class="form-control" placeholder="Nome" required>
									</div>
									<div class="form-group">
										<input type="text" name="usuario.sobrenome" id="sobrenome"
											tabindex="1" class="form-control" placeholder="Sobrenome" required>
									</div>
									<div class="form-group">
										<input type="email" name="usuario.login" id="email"
											tabindex="1" class="form-control"
											placeholder="Endereço de Email" required>
									</div>
									<div class="form-group">
										<input type="password" title="Favor preencher a senha" name="usuario.password" id="password"
											tabindex="2" class="form-control" placeholder="Senha" onchange="register-form.confirm-password.pattern = this.value;"
											required pattern="^([a-zA-Z0-9@*#_]{6,})$">
									</div>
									<div class="form-group">
										<input type="password" title="Favor confirmar a senha" name="confirm-password"
											id="confirm-password" tabindex="3" class="form-control"
											placeholder="Confirme a Senha" required >
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register" value="Cadastrar">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>