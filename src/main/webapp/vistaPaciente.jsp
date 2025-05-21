<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />

<div class="container py-4">
  <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">
    Bienvenido, <span style="color: #2c9c74;">${usuarioLog.nombre}</span>
  </h2>
  <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
    <span class="badge bg-light text-dark p-2">Atenciones Médicas</span>
  </h3>

  <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
    <c:forEach var="at" items="${atenciones}">
      <div class="col d-flex justify-content-center">
        <div class="card shadow-sm" style="width: 18rem;">
          <div class="card-body text-center">
            <h5 class="card-title">${at.paciente.nombre} ${at.paciente.apellidoPaterno} ${at.paciente.apellidoMaterno}</h5>
            <h6 class="card-subtitle mb-2 text-muted">ID: ${at.id}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Fecha ingreso: ${at.fechaIngreso}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Fecha egreso: ${at.fechaEgreso}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Estado: ${at.estado}</h6>
            <a href="${pageContext.request.contextPath}/Acompanante/ProcedimientoListar?idAtencionMedica=${at.id}" class="btn btn-primary btn-block w-100">Listar Procedimientos</a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>

</div>

<jsp:include page="/layout/footer.jsp"/>
<%-- Doble inclusión del footer removida --%>