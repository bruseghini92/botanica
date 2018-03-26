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
			<h2>Editar Planta</h2>

			<form:form method="POST" data-toggle="validator" id="form"
				action="/botanica/admin/plantas" modelAttribute="planta" role="form"
				cssClass="form-horizontal" accept-charset="utf-8">

				<spring:bind path="id">
					<form:hidden cssClass="form-control" path="id" placeholder="Id" />
				</spring:bind>

				<spring:bind path="nombre">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="nombre">Nombre:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="nombre"
								placeholder="Nombre" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="suelo">
					<label class="control-label col-md-3" for="suelo">Tipo de
						suelo:</label>
					<div class="col-md-3">
						<form:select path="suelo" cssClass="form-control">
							<c:forEach items="${suelo}" var="suelo">
								<form:option value="${suelo.id}" label="${suelo.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>

				<spring:bind path="clima">
					<label class="control-label col-md-3" for="clima">Tipo de
						Clima:</label>
					<div class="col-md-3">
						<form:select path="clima" cssClass="form-control">
							<c:forEach items="${clima}" var="clima">
								<form:option value="${clima.id}" label="${clima.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>

				<spring:bind path="temporada">
					<label class="control-label col-md-3" for="temporada">Temporada:</label>
					<div class="col-md-3">
						<form:select path="temporada" cssClass="form-control">
							<c:forEach items="${temporada}" var="temporada">
								<form:option value="${temporada.id}" label="${temporada.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
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

				<spring:bind path="tiempoRiego">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="tiempoRiego">Tiempo
							de Riego:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="tiempoRiego"
								placeholder="HH:mm:ss" required="required" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="tipo">
					<label class="control-label col-md-3" for="tipo">Tipo de
						planta:</label>
					<div class="col-md-3">
						<form:select path="tipo" cssClass="form-control">
							<c:forEach items="${tipo}" var="tipo">
								<form:option value="${tipo.id}" label="${tipo.nombre}" />
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