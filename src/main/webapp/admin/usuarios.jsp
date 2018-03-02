<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/partes/head.jsp" flush="true" />
<div class="container" style="margin-top: 80px;">
	<c:if test="${not empty mensaje}">
		<div class="row">
			<div class="col-md-12">
				<div
					class="alert <c:out value="${estilo}"/>">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<p>
						<c:out value="${mensaje}" />
					</p>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<h1>Usuarios</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Usuario</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Email</th>
					<th>Tipo de Cuenta</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.user}</td>
						<td>${usuario.nombre}</td>
						<td>${usuario.apellido}</td>
						<td>${usuario.email}</td>
						<td>${usuario.rol.descripcion}</td>
						<td><a href="/admin/usuarioeditar/${usuario.id}"
							class="btn btn-success">Editar</a> <a
							href="/admin/usuarioborrar/${usuario.id}" class="btn btn-danger">Borrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />