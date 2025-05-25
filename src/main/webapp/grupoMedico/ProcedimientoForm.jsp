<%@ page import="org.sleon.electronicHealthRecord.models.ProcedimientoMedico" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />

<div class="container mt-5 d-flex justify-content-center">
    <div class="col-md-8">
        <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">
            ${procedimiento.id != null && procedimiento.id > 0 ? 'Editar' : 'Registrar'} Procedimiento
        </h2>

        <a href="${pageContext.request.contextPath}/grupoMedico/AtencionMedica/Listar" class="btn btn-secondary mb-3">Regresar</a>

        <form action="${pageContext.request.contextPath}/grupoMedico/crearProcedimiento" method="post">
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción:</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3">${procedimiento.descripcion != null ? procedimiento.descripcion : ''}</textarea>
                <c:if test="${not empty errores['descripcion']}">
                    <div class="alert alert-danger mt-1">${errores['descripcion']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="fechaInicio" class="form-label">Fecha de Inicio:</label>
                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" value="${procedimiento.fechaInicio != null ? procedimiento.fechaInicio : ''}">
                <c:if test="${not empty errores['fechaInicio']}">
                    <div class="alert alert-danger mt-1">${errores['fechaInicio']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="horaInicio" class="form-label">Hora de Inicio:</label>
                <input type="time" class="form-control" id="horaInicio" name="horaInicio" value="${procedimiento.horaInicio != null ? procedimiento.horaInicio : ''}">
                <c:if test="${not empty errores['horaInicio']}">
                    <div class="alert alert-danger mt-1">${errores['horaInicio']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="fechaFin" class="form-label">Fecha de Fin:</label>
                <input type="date" class="form-control" id="fechaFin" name="fechaFin" value="${procedimiento.fechaFin != null ? procedimiento.fechaFin : ''}">
                <c:if test="${not empty errores['fechaFin']}">
                    <div class="alert alert-danger mt-1">${errores['fechaFin']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="horaFin" class="form-label">Hora de Fin:</label>
                <input type="time" class="form-control" id="horaFin" name="horaFin" value="${procedimiento.horaFin != null ? procedimiento.horaFin : ''}">
                <c:if test="${not empty errores['horaFin']}">
                    <div class="alert alert-danger mt-1">${errores['horaFin']}</div>
                </c:if>
            </div>

            <div class="mb-3">
<%--                <label for="doctorEncargado" class="form-label">Doctor Encargado:</label>--%>
                <input type="hidden" class="form-control" id="doctorEncargado" value="${procedimiento.grupoMedico.nombre != null ? procedimiento.grupoMedico.nombre: ''}" readonly>
            </div>

            <input type="hidden" name="procedimientoId" value="${param.procedimiento.id}">
            <input type="hidden" name="idAtencionMedica" value="${param.idAtm}">
            <input type="hidden" name="grupoMedicoId" value="${param.procedimiento.grupoMedico.id}">

            <div>
                <button type="submit" class="btn btn-primary">
                    ${procedimiento.id != null && procedimiento.id > 0 ? 'Actualizar' : 'Registrar'}
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />


