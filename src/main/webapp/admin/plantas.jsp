<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/partes/head.jsp" flush="true" />
<div class="container">
	<c:if test="${not empty mensaje}">
		<div class="row">
			<div class="col-md-12">
				<div class="alert <c:out value="${estilo}"/>">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<p>
						<c:out value="${mensaje}" />
					</p>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<h1>Plantas</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Suelo</th>
					<th>Clima</th>
					<th>Temporada</th>
					<th>Descripcion</th>
					<th>Tiempo riego</th>
					<th>Tipo Planta</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plantas}" var="plantas">
					<tr>
						<td>${plantas.nombre}</td>
						<td>${plantas.suelo.nombre}</td>
						<td>${plantas.clima.nombre}</td>
						<td>${plantas.temporada.nombre}</td>
						<td>${plantas.descripcion}</td>
						<td><fmt:formatDate value="${plantas.tiempoRiego}" pattern="HH:mm" /></td>
						<td>${plantas.tipo.nombre}</td>
						<td><a href="/botanica/admin/plantaeditar/${plantas.id}"
							class="btn btn-success">Editar</a> <a
							href="/botanica/admin/plantaborrar/${plantas.id}" class="btn btn-danger">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />