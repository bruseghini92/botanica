<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/partes/head.jsp" flush="true" />
<br>
<!-- Container -->
<div class="container">
	<div class="row">
		<div id="loginbox" style="margin-top: 50px;">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Plantas</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>
					<table>
						<tr>
							<th>Nombre</th>
							<th>Suelo</th>
							<th>Clima</th>
							<th>Temporada</th>
							<th>Descripción</th>
							<th>Tiempo Riego</th>
							<th>Tipo de planta</th>
						</tr>
						<c:forEach items="${plantas}" var="plantas">
							<tr>
								<td>${plantas.nombre}</td>
								<td>${plantas.suelo.nombre}</td>
								<td>${plantas.clima.nombre}</td>
								<td>${plantas.temporada.nombre}</td>
								<td>${plantas.descripcion}</td>
								<td>${plantas.tiempoRiego}</td>
								<td>${plantas.tipo.nombre}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />