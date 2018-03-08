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
			<h2>Editar Tema</h2>

			<form:form method="POST" data-toggle="validator" id="form"
				modelAttribute="tema" role="form" cssClass="form-horizontal"
				accept-charset="utf-8">

				<spring:bind path="titulo">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="titulo">Titulo:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="titulo"
								placeholder="Titulo" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="texto">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="texto">Texto::</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="texto"
								placeholder="Texto" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>

				<spring:bind path="categoria">
					<label class="control-label col-md-3" for="categoria">Temporada:</label>
					<div class="col-md-3">
						<form:select path="categoria" cssClass="form-control">
							<c:forEach items="${categoria}" var="categoria">
								<form:option value="${categoria.id}" label="${categoria.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>

				<spring:bind path="fecha">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="fecha">Fecha:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="fecha"/>
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>
				<button type="submit">Enviar</button>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />