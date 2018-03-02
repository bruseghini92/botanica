<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Botanic</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="css/landing-page.css" rel="stylesheet">

</head>

<body>
		<div class="container">
			<h2>Informaci&oacute;N</h2>
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="row">
						<div class="col-lg-10 col-lg-offset-2 text-center">
							<div class="thumb-error">
							<div class="thumbnail">
								<div class="badge">
									<span class="fa fa-info"></span>
								</div>
								<div class="caption">
										<strong>404 P&aacute;gina no encontrada</strong>
								</div>
							</div>
						</div>
						</div>
					</div>
					<div class="row" style="padding-bottom:20px;">
						<div class="col-lg-6 text-center">
							<a href="<c:url value="/login/"/>"
								class="btn btn-primary btn-block"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span>
								Ingreso al Sistema</a>
						</div>
						<div class="col-lg-6  text-center">
							<a href="<c:url value="/"/>" class="btn btn-success btn-block"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span>
								Inicio</a>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
