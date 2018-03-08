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
			<h2>Editar clima</h2>

			<form:form method="POST" data-toggle="validator" id="form"  action="/botanica/admin/climas"
				modelAttribute="clima" role="form" cssClass="form-horizontal"
				accept-charset="utf-8">
				
				<spring:bind path="id">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for=id>ID:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="id"
								placeholder="id" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
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

				<spring:bind path="descripcion">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="descripcion">Descripcion:</label>
						<div class="col-md-7">
							<textarea class="form-control" rows="5" class="form-control" name="descripcion"
								rows="22" id="descripcion" placeholder="Descripcion">${clima.descripcion}</textarea>
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