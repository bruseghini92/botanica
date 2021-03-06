<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/partes/head.jsp" flush="true" />
<div class="container" style="margin-top: 100px">
	<div class="row">
		<div class="col-md-12">
			<h2>Editar Ventas</h2>

			<form:form method="POST" data-toggle="validator" id="form"
				modelAttribute="venta" role="form" cssClass="form-horizontal"
				accept-charset="utf-8">

				<spring:bind path="producto">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="producto">Producto:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="producto"
								placeholder="Producto" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="descripcion">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="descripcion">Descripcion:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="descripcion"
								placeholder="Descripcion" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="cerrado">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="cerrado">Cerrado:</label>
						<div class="col-md-7">
							<form:password cssClass="form-control" path="cerrado"
								placeholder="Cerrado" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="venta.usuario.user">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="venta.usuario.user">Nombre:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="venta.usuario.user"
								placeholder="Nombre" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="persona.apellido">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="persona.apellido">Apellido:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="persona.apellido"
								placeholder="Apellido" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="persona.fechaNacimiento">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3"
							for="persona.fechaNacimiento">Edad:</label>
						<div class="col-md-7">
							 <form:input cssClass="form-control" path="persona.fechaNacimiento"
								placeholder="dd-MM-yyyy" required="required"/> 
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>
				
				<spring:bind path="rol">
					<label class="control-label col-md-3" for="rol">Tipo de
						Usuario:</label>
					<div class="col-md-3">
						<form:select path="rol" cssClass="form-control">
							<c:forEach items="${rol}" var="rol">
								<form:option value="${rol.id}" label="${rol.descripcion}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>

				<button type="submit">Enviar</button>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />