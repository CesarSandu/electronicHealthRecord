<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/header.jsp"/>
<c:set var="accion" value="${empty param.accion ? requestScope.accion : param.accion}" />

<div class="container mt-5 d-flex justify-content-center">
    <div class="col-md-8">
        <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">
            ${accion == 'crear' ? 'Crear Personal de Admisión' : 'Editar Personal de Admisión'}
        </h2>

        <a href="${pageContext.request.contextPath}/administrador/usuarios/listar?tipo=admision" class="btn btn-secondary mb-3">Regresar</a>

        <form action="${pageContext.request.contextPath}/administrador/personalAdmision/actualizar" method="post">
            <input type="hidden" name="id_usuario" value="${pad.present ? pad.get().id : ''}" />
            <input type="hidden" name="accion" value="${accion}" />

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${pad.present ? pad.get().nombre : ''}" />
                <c:if test="${not empty errores['nombre']}">
                    <div class="alert alert-danger mt-1">${errores['nombre']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apellidoP" class="form-label">Apellido Paterno:</label>
                <input type="text" class="form-control" id="apellidoP" name="apellidoPaterno" value="${pad.present ? pad.get().apellidoPaterno : ''}" />
                <c:if test="${not empty errores['apellidoPaterno']}">
                    <div class="alert alert-danger mt-1">${errores['apellidoPaterno']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apellidoM" class="form-label">Apellido Materno:</label>
                <input type="text" class="form-control" id="apellidoM" name="apellidoMaterno" value="${pad.present ? pad.get().apellidoMaterno : ''}" />
                <c:if test="${not empty errores['apellidoMaterno']}">
                    <div class="alert alert-danger mt-1">${errores['apellidoMaterno']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="genero" class="form-label">Género:</label>
                <select class="form-select" id="genero" name="genero">
                    <option value="M" ${pad.present && pad.get().genero == 'M' ? 'selected' : ''}>Masculino</option>
                    <option value="F" ${pad.present && pad.get().genero == 'F' ? 'selected' : ''}>Femenino</option>
                    <option value="Otro" ${pad.present && pad.get().genero == 'Otro' ? 'selected' : ''}>Otro</option>
                </select>
                <c:if test="${not empty errores['genero']}">
                    <div class="alert alert-danger mt-1">${errores['genero']}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${pad.present ? pad.get().telefono : ''}" />
                <c:if test="${not empty errores['telefono']}">
                    <div class="alert alert-danger mt-1">${errores['telefono']}</div>
                </c:if>
            </div>

            <c:if test="${accion == 'crear'}">
                <div class="mb-3">
                    <label for="idUsuario" class="form-label">ID Usuario:</label>
                    <input type="text" class="form-control" id="idUsuario" name="idUsuario" value="${pad.present? pad.get().idUsuario : ''}"/>
                    <c:if test="${not empty errores['idUsuario']}">
                        <div class="alert alert-danger mt-1">${errores['idUsuario']}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="contraseña" class="form-label">Contraseña:</label>
                    <input type="text" class="form-control" id="contraseña" name="contraseña" value="${pad.present? pad.get().contrasenia : ''}"/>
                    <c:if test="${not empty errores['contraseña']}">
                        <div class="alert alert-danger mt-1">${errores['contraseña']}</div>
                    </c:if>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary">Guardar</button>

        </form>
    </div>
</div>

<jsp:include page="/layout/footer.jsp"/>