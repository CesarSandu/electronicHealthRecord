<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp"/>

<div class="container py-4">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">
        Bienvenido, <span style="color: #2c9c74;">${usuarioLog.idUsuario}</span>
    </h2>
    <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
        <span class="badge bg-light text-dark p-2">Lista de Usuarios</span>
    </h3>

    <%
        String error = (String) session.getAttribute("error");
        if (error != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show text-center mb-3" role="alert">
        <%= error %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            session.removeAttribute("error");
        }
    %>

    <div class="mb-3">
        <label for="listarOpcion" class="form-label">Listar:</label>
        <select name="tipo" class="form-select" id="listarOpcion" onchange="document.getElementById('listarForm').submit();">
            <option disabled selected>Selecciona una opción</option>
            <option value="admision" ${param.tipo == 'admision' ? 'selected' : ''}>Personal Admisión</option>
            <option value="grupoMedico" ${param.tipo == 'grupoMedico' ? 'selected' : ''}>Grupo Médico</option>
            <option value="administrador" ${param.tipo == 'administrador' ? 'selected' : ''}>Administrador</option>
        </select>
        <form id="listarForm" method="get" action="${pageContext.request.contextPath}/administrador/usuarios/listar" style="display:none;">
            <input type="hidden" name="tipo" id="tipoSeleccionado">
        </form>
        <script>
            document.getElementById('listarOpcion').addEventListener('change', function() {
                document.getElementById('tipoSeleccionado').value = this.value;
                document.getElementById('listarForm').submit();
            });
        </script>
    </div>

    <div class="mb-4">
        <a href="${pageContext.request.contextPath}/administrador/personalAdmisionForm.jsp?accion=crear" class="btn btn-success me-2">
            Crear Personal de Admisión [+]
        </a>
        <a href="${pageContext.request.contextPath}/administrador/grupoMedicoForm.jsp?accion=crear" class="btn btn-success">
            Crear Grupo Médico [+]
        </a>
    </div>

    <c:if test="${not empty grupoMedicos}">
        <h4 class="mt-4 mb-3" style="color: #6c757d;">Grupo Médico</h4>
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Nombre Completo</th>
                    <th>Género</th>
                    <th>Teléfono</th>
                    <th>Especialidad</th>
                    <th>Fecha Registro</th>
                    <th>Responsable Registro</th>
                    <th>Tel Responsable Registro</th>
                    <th>Actualizar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="gm" items="${grupoMedicos}">
                    <tr>
                        <td>${gm.id}</td>
                        <td>${gm.nombre} ${gm.apellidoPaterno} ${gm.apellidoMaterno}</td>
                        <td>${gm.genero}</td>
                        <td>${gm.telefono}</td>
                        <td>${gm.especialidad}</td>
                        <td>${gm.fechaRegistro}</td>
                        <td>
                                ${gm.responsableRegistro.nombre}
                                ${gm.responsableRegistro.apellidoPaterno}
                        </td>
                        <td>${gm.responsableRegistro.telefono}</td>
                        <td class="text-center"><a href="${pageContext.request.contextPath}/administrador/grupoMedico/actualizar?id=${gm.id}&accion=actualizar" class="btn btn-sm btn-warning">Actualizar</a></td>
                        <td class="text-center"><a href="${pageContext.request.contextPath}/administrador/usuariosHospital/eliminar?id=${gm.id}&tipo=grupoMedico" class="btn btn-sm btn-danger">Eliminar</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${not empty personalAdmision}">
        <h4 class="mt-4 mb-3" style="color: #6c757d;">Personal de Admisión</h4>
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Nombre Completo</th>
                    <th>Género</th>
                    <th>Teléfono</th>
                    <th>Fecha Registro</th>
                    <th>Responsable Registro</th>
                    <th>Tel Responsable Registro</th>
                    <th>Actualizar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pa" items="${personalAdmision}">
                    <tr>
                        <td>${pa.id}</td>
                        <td>${pa.nombre} ${pa.apellidoPaterno} ${pa.apellidoMaterno}</td>
                        <td>${pa.genero}</td>
                        <td>${pa.telefono}</td>
                        <td>${pa.fechaRegistro}</td>
                        <td>
                                ${pa.responsableRegistro.nombre}
                                ${pa.responsableRegistro.apellidoPaterno}
                        </td>
                        <td>${pa.responsableRegistro.telefono}</td>
                        <td class="text-center"><a href="${pageContext.request.contextPath}/administrador/personalAdmision/actualizar?id=${pa.id}&accion=actualizar" class="btn btn-sm btn-warning">Actualizar</a></td>
                        <td class="text-center"><a href="${pageContext.request.contextPath}/administrador/usuariosHospital/eliminar?id=${pa.id}&tipo=admision" class="btn btn-sm btn-danger">Eliminar</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${not empty administradores}">
        <h4 class="mt-4 mb-3" style="color: #6c757d;">Administradores</h4>
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Nombre Completo</th>
                    <th>Género</th>
                    <th>Teléfono</th>
                    <th>Fecha Registro</th>
                    <th>Responsable Registro</th>
                    <th>Tel Responsable Registro</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ad" items="${administradores}">
                    <tr>
                        <td>${ad.id}</td>
                        <td>${ad.nombre} ${ad.apellidoPaterno} ${ad.apellidoMaterno}</td>
                        <td>${ad.genero}</td>
                        <td>${ad.telefono}</td>
                        <td>${ad.fechaRegistro}</td>
                        <td>
                                ${ad.responsableRegistro.nombre}
                                ${ad.responsableRegistro.apellidoPaterno}
                        </td>
                        <td>${ad.responsableRegistro.telefono}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

</div>
<jsp:include page="/layout/footer.jsp"/>