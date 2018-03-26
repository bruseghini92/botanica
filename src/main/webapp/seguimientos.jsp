<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ar.edu.um.ingenieria.dto.PlantaDTO"%>
<%@page import="java.util.ArrayList"%>
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
					<div class="panel-title">Seguimientos</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>
					<table>
						<tr>
							<th>Planta</th>
							<th>Estado</th>
							<th>Etapa</th>
							<th>Tarea</th>
							<th>Ultimo riego</th>
							<th>Pr&oacuteximo Riego</th>
							<th>Fecha Inicio</th>
							<th>Fecha Abono</th>
							<th>Fecha Poda</th>
							<th>Fecha Cosecha</th>
						</tr>
						<c:forEach items="${seguimiento}" var="seguimiento">
							<tr>
								<td>${seguimiento.planta.nombre}</td>
								<td>${seguimiento.estado.nombre}</td>
								<td>${seguimiento.etapa.nombre}</td>
								<td>${seguimiento.tarea.nombre}</td>
								<td><fmt:formatDate value="${seguimiento.ultimoRiego}"
										pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td><fmt:formatDate value="${seguimiento.proximoRiego}"
										pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td><fmt:formatDate value="${seguimiento.fechaInicio}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimiento.fechaAbono}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimiento.fechaPoda}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimiento.fechaCosecha}"
										pattern="dd/MM/yyyy" /></td>
								<td><a type="button" class="btn btn-primary"
									href="/botanica/seguimientos/${seguimiento.id}/${seguimiento.tarea.nombre}">Realizar
										Tarea</a></td>
							</tr>
						</c:forEach>
					</table>
					<a type="button" class="btn btn-primary" href="/botanica/seguimientos/create">Crear
						nuevo seguimiento</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />