<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />

<div class="container my-4">

    <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
        <span class="badge bg-light text-dark p-2">Procedimientos</span>
    </h3>

    <c:choose>
        <c:when test="${sessionScope.usuarioLog.tipo eq 'grupoMedico'}">
            <a href="${pageContext.request.contextPath}/grupoMedico/AtencionMedica/Listar" class="btn btn-secondary mb-4">Regresar</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/Acompanante/Listar" class="btn btn-secondary mb-4">Regresar</a>
        </c:otherwise>
    </c:choose>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
        <c:forEach var="procedimiento" items="${procedimientos}">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <div>
                            <h6 class="card-title text-primary" style="font-weight: bold;">${procedimiento.descripcion}</h6>
                            <small class="text-muted mt-2">
                                <strong>Fecha Inicio:</strong> ${procedimiento.fechaInicio}<br>
                                <strong>Hora Inicio:</strong> ${procedimiento.horaInicio}<br>
                                <strong>Fecha Fin:</strong> ${procedimiento.fechaFin}<br>
                                <strong>Hora Fin:</strong> ${procedimiento.horaFin}
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />