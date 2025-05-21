<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp" />

<div class="container py-4">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">
        Bienvenido, <span style="color: #2c9c74;">${usuarioLog.idUsuario}</span>
    </h2>
    <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
        <span class="badge bg-light text-dark p-2">Atenciones Médicas</span>
    </h3>

    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" style="max-width: fit-content;">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white p-2">
                    <h6 class="modal-title" id="errorModalLabel">Error</h6>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body p-3">
                    <small class="text-danger">${error}</small>
                </div>
                <div class="modal-footer p-2">
                    <button type="button" class="btn btn-danger btn-sm" data-bs-dismiss="modal">Ok</button>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${not empty error}">
        <script>
            var myModal = new bootstrap.Modal(document.getElementById('errorModal'));
            myModal.show();
        </script>
    </c:if>
    <%
        session.removeAttribute("error");
    %>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach var="at" items="${atenciones}">
            <div class="col d-flex justify-content-center">
                <div class="card shadow-sm" style="width: 18rem;">
                    <div class="card-body text-center">
                        <h5 class="card-title">${at.paciente.nombre} ${at.paciente.apellidoPaterno} ${at.paciente.apellidoMaterno}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">ID: ${at.id}</h6>

                        <a href="${pageContext.request.contextPath}/grupoMedico/notaEvolucion/Listar?idAtm=${at.id}" class="btn btn-primary btn-block mb-2 w-100">Listar notas de evolución</a>
                        <a href="${pageContext.request.contextPath}/grupoMedico/notaEvolucionForm.jsp?idAtm=${at.id}" class="btn btn-primary btn-block mb-2 w-100">Crear Nota de evolución</a>
                        <a href="${pageContext.request.contextPath}/grupoMedico/ProcedimientoForm.jsp?idAtm=${at.id}" class="btn btn-primary btn-block mb-2 w-100">Registrar Procedimiento</a>
                        <a href="${pageContext.request.contextPath}/grupoMedico/ProcedimientoListar?idAtencionMedica=${at.id}" class="btn btn-primary btn-block mb-2 w-100">Listar Procedimientos</a>
                        <a href="${pageContext.request.contextPath}/grupoMedico/atencionMedica/registrarAlta?idAtm=${at.id}"
                           class="btn btn-success btn-block mb-2 w-100"
                           onclick="return confirm('¿Estás seguro de que deseas registrar el alta?');">
                            Registrar Alta
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<jsp:include page="/layout/footer.jsp"/>