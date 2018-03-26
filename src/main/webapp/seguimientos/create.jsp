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
			<h2>Crear nuevo seguimiento</h2>
			<form:form method="POST" data-toggle="validator" id="form"
				modelAttribute="seguimiento" role="form" cssClass="form-horizontal"
				accept-charset="utf-8" action="/botanica/seguimientos">

				<spring:bind path="planta">
					<label class="control-label col-md-3" for="planta">Planta:</label>
					<div class="col-md-3">
						<form:select path="planta" cssClass="form-control">
							<c:forEach items="${planta}" var="planta">
								<form:option value="${planta.id}" label="${planta.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>

				<spring:bind path="estado">
					<label class="control-label col-md-3" for="estado">Estado:</label>
					<div class="col-md-3">
						<form:select path="estado" cssClass="form-control">
							<c:forEach items="${estado}" var="estado">
								<form:option value="${estado.id}" label="${estado.nombre}" />
							</c:forEach>
						</form:select>
						<c:if test="${status.error}">
							<span class="text-danger">${status.errorMessage}</span>
						</c:if>
					</div>
				</spring:bind>
				<div class="row">
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary">Enviar</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/partes/footer.jsp" flush="true" />