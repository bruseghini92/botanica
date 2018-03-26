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
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Menu administrador:</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>
					<table>
						<tr>
							<th>Usuarios</th>
						</tr>
						<tr>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/usuarios">Listar Usuarios</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/personas">Listar Personas</a></td>
						</tr>
						<tr>
							<th>Plantas</th>
						</tr>
						<tr>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/plantas">Listar Plantas</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/tiposplantas">Listar Tipos de Plantas</a></td>
						</tr>
						<tr>
							<th>Mantenimientos</th>
						</tr>
						<tr>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/estados">Listar Estados</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/etapas">Listar Etapas</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/tareas">Listar Tareas</a></td>
						</tr>
						<tr>
							<th>Entornos</th>
						</tr>
						<tr>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/suelos">Listar Suelos</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/climas">Listar Climas</a></td>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/temporadas">Listar Temporadas</a></td>
						</tr>
						<tr>
							<th>Seguimientos</th>
						</tr>
						<tr>
							<td><a type="button" class="btn btn-primary"
								href="/botanica/admin/seguimientos">Listar Seguimientos</a></td>
						</tr>
						<c:forEach items="${climas}" var="climas">
							<tr>
								<td>${climas.nombre}:</td>
								<td>${climas.descripcion}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />