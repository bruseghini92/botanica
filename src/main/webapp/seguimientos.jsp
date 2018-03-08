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
						<c:forEach items="${seguimientos}" var="seguimientos">
							<tr>
								<td>${seguimientos.planta.nombre}</td>
								<td>${seguimientos.estado.nombre}</td>
								<td>${seguimientos.etapa.nombre}</td>
								<td>${seguimientos.tarea.nombre}</td>
								<td><fmt:formatDate value="${seguimientos.ultimoRiego}"
										pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td><fmt:formatDate value="${seguimientos.proximoRiego}"
										pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td><fmt:formatDate value="${seguimientos.fechaInicio}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimientos.fechaAbono}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimientos.fechaPoda}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${seguimientos.fechaCosecha}"
										pattern="dd/MM/yyyy" /></td>
								<td><a type="button" class="btn btn-primary"
									href="seguimientos/${seguimientos.id}/${seguimiento.tarea.nombre}">Realizar
										Tarea</a></td>
							</tr>
						</c:forEach>
					</table>
					<form:form method="POST" data-toggle="validator" id="form"
						modelAttribute="seguimientos" role="form"
						cssClass="form-horizontal" accept-charset="utf-8"
						action="/botanica/seguimientos">
						<!-- Agregar lista de plantas y estados para crear nuevo -->
						<div class="row">
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary">Enviar</button>
					</div>
				</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />