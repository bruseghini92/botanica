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
			<h2>Registraci&oacute;n</h2>
			<form:form method="POST" data-toggle="validator" id="form"
				modelAttribute="usuario" role="form" cssClass="form-horizontal"
				accept-charset="utf-8" action="/botanica/registro/create/">
				
				<spring:bind path="user">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="user">Usuario:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="user"
								placeholder="Usuario" required="required" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>
				
				
				<spring:bind path="email">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="email">email:</label>
						<div class="col-md-7">
							<form:input cssClass="form-control" path="email"
								placeholder="Email" required="required" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>
				
				
				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : '' }">
						<label class="control-label col-md-3" for="password">Password:</label>
						<div class="col-md-7">
							<form:password cssClass="form-control" path="password"
								placeholder="Password" required="required" />
							<c:if test="${status.error}">
								<span class="text-danger">${status.errorMessage}</span>
							</c:if>
						</div>
					</div>
				</spring:bind>
				
				
				
				
				<div class="row" >
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary">Enviar</button>
					</div>					
				</div>

			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />