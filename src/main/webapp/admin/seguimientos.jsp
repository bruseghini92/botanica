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
		<h1>Seguimientos</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Usuario</th>
					<th>Planta</th>
					<th>Estado</th>
					<th>Etapa</th>
					<th>Tarea</th>
					<th>Ultimo Riego</th>
					<th>Fecha Inicio</th>
					<th>Fecha Abono</th>
					<th>Fecha Poda</th>
					<th>Fecha Cosecha</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${seguimientos}" var="seguimientos">
					<tr>
						<td>${seguimientos.usuario.user}</td>
						<td>${seguimientos.planta.nombre}</td>
						<td>${seguimientos.estado.nombre}</td>
						<td>${seguimientos.etapa.nombre}</td>
						<td>${seguimientos.tarea.nombre}</td>
						<td>${seguimientos.ultimoRiego}</td>
						<td>${seguimientos.fechaInicio}</td>
						<td>${seguimientos.fechaAbono}</td>
						<td>${seguimientos.fechaPoda}</td>
						<td>${seguimientos.fechaCosecha}</td>	
						<td><a href="/botanica/admin/seguimientoeditar/${seguimientos.id}"
							class="btn btn-success">Editar</a> <a
							href="/botanica/admin/seguimientoborrar/${seguimientos.id}" class="btn btn-danger">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />