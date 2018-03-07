<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Asistente Botánico</title>
<LINK REL="SHORTCUT ICON" HREF="img/header.jpg" />

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>

<!-- Plugin CSS -->
<link href="/botanica/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/creative.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="/botanica/">Botanica</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:if test="${empty session}">
						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="/botanica/registro">Registrarse</a></li>

						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="/botanica/login">Iniciar Sesi&oacute;n</a></li>
					</c:if>
					<c:if test="${not empty session}">
						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="#"><c:out value="${sesion.user}" /></a></li>
						<li class="nav-item"><a class="nav-link js-scroll-trigger"
							href="/logout">Cerrar Sesi&oacute;n</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">Servicios</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/foro">Foro</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/#contact">Contacto</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/#about">Quienes Somos</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
</body>
</html>	