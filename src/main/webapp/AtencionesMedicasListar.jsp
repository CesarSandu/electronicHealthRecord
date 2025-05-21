<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp"/>

<div class="container py-4">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">
        Bienvenido, <span style="color: #2c9c74;">${usuarioLog.idUsuario}</span>
    </h2>
    <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
        <span class="badge bg-light text-dark p-2">Atenciones Médicas</span>
    </h3>



    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/Admision/AtencionMedicaForm.jsp" class="btn btn-success">
            Crear [+]
        </a>
    </div>

    <c:if test="${not empty codAcompañante}">
        <div class="alert alert-success" role="alert">
            Atención médica registrada exitosamente. Código de inicio de sesión del acompañante: <strong>${codAcompañante}</strong>
        </div>
    </c:if>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle">
            <thead class="table-dark text-center">
            <tr>
                <th>Id</th>
                <th>Paciente</th>
                <th>Teléfono</th>
                <th>Género</th>
                <th>Acompañante</th>
                <th>Parentesco</th>
                <th>Tel Acompañante</th>
                <th>Fecha Ingreso</th>
                <th>Hora Ingreso</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="atencion" items="${atenciones}">
                <tr>
                    <td>${atencion.paciente.id}</td>
                    <td>${atencion.paciente.nombre} ${atencion.paciente.apellidoPaterno} ${atencion.paciente.apellidoMaterno}</td>
                    <td>${atencion.paciente.telefono}</td>
                    <td>${atencion.paciente.genero}</td>
                    <td>${atencion.acompaniante.nombre} ${atencion.acompaniante.apellidoPaterno} ${atencion.acompaniante.apellidoMaterno}</td>
                    <td>${atencion.acompaniante.parentesco}</td>
                    <td>${atencion.acompaniante.telefono}</td>
                    <td>${atencion.fechaIngreso}</td>
                    <td>${atencion.horaIngreso}</td>
                    <td>${atencion.estado}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="/layout/footer.jsp"/>
