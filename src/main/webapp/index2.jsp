<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body style="margin-top: 80px;">
	<div class="container">
		<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Registrar Cuenta</h3>
					</div>
					<div class="panel-body">
						<form action="registro/ValidarRegistro.php" method="POST">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Usuario"
										name="usuario" type="text" autofocus required>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Contraseña"
										name="contraseña" type="password" required>
								</div>
								<input type="submit" value="Registrar"
									class="btn btn-lg btn-success btn-block">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Iniciar Sesi&oacute;n</h3>
					</div>
					<div class="panel-body">
						<form action="ingreso/validarLogin.php" method="POST">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Usuario"
										name="usuario" type="text" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Contraseña"
										name="contrasenia" type="password" value="">
								</div>
								<input type="submit" value="Ingresar"
									class="btn btn-lg btn-success btn-block">
							</fieldset>
						</form>
						<div></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${usuarios}" var="usuario">
				<div class="col-md-6">
					<c:out value="${usuario.user}" />
				</div>
				<div class="col-md-6">
					<c:out value="${usuario.email}" />
				</div>
			</c:forEach>
		</div>
		<div class="row">
			
			<c:forEach items="${temas}" var="tema">
				<div class="col-md-12">
					<a href="<c:url value="/temas/${tema.id}"/>"><c:out value="${tema.nombre}" /> </a>
				</div>
				
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-md-12">
				<c:out value="${cantUsuarios}"></c:out>
			</div>
		</div>
	</div>
</body>
</html>