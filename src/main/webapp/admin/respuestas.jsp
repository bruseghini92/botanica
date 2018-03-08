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
		<h1>Respuestas</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Tema</th>
					<th>Texto</th>
					<th>Usuario</th>
					<th>Fecha</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${respuestas}" var="respuestas">
					<tr>
						<td>${respuestas.tema.titulo}</td>
						<td>${respuestas.texto}</td>
						<td>${respuestas.usuario.user}</td>
						<td>${respuestas.fecha}</td>
						<td><a href="/botanica/admin/respuestaeditar/${respuestas.id}"
							class="btn btn-success">Editar</a> <a
							href="/botanica/admin/respuestaborrar/${respuestas.id}" class="btn btn-danger">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />