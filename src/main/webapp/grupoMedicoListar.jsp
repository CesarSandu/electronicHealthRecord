<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/header.jsp" />

<div class="container mt-5">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">Lista de Grupo Médico</h2>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido Paterno</th>
                <th>Apellido Materno</th>
                <th>Género</th>
                <th>Teléfono</th>
                <th>Tipo</th>
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
                    <td>${gm.nombre}</td>
                    <td>${gm.apellidoPaterno}</td>
                    <td>${gm.apellidoMaterno}</td>
                    <td>${gm.genero}</td>
                    <td>${gm.telefono}</td>
                    <td>${gm.tipo}</td>
                    <td>${gm.especialidad}</td>
                    <td>${gm.fechaRegistro}</td>
                    <td>
                            ${gm.responsableRegistro.nombre}
                            ${gm.responsableRegistro.apellidoPaterno}
                    </td>
                    <td>${gm.responsableRegistro.telefono}</td>
                    <td><a href="/electronicHealthRecord/usuariosHospital/actualizar?id=${gm.id}" class="btn btn-sm btn-warning">Actualizar</a></td>
                    <td><a href="/electronicHealthRecord/usuariosHospital/eliminar?id=${gm.id}" class="btn btn-sm btn-danger">Eliminar</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />