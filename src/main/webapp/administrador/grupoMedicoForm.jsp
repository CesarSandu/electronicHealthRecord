<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />
<c:set var="accion" value="${empty param.accion ? requestScope.accion : param.accion}" />

<div class="container mt-5 d-flex justify-content-center">
  <div class="col-md-8">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">
      ${accion == 'crear' ? 'Crear Grupo Médico' : 'Editar Grupo Médico'}
    </h2>

    <a href="${pageContext.request.contextPath}/administrador/usuarios/listar?tipo=grupoMedico" class="btn btn-secondary mb-3">Regresar</a>

    <form action="${pageContext.request.contextPath}/administrador/grupoMedico/actualizar" method="post">
      <input type="hidden" name="id_usuario" value="${grupoMedico.present ? grupoMedico.get().id : ''}" />
      <input type="hidden" name="accion" value="${accion}" />

      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre:</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${grupoMedico.present ? grupoMedico.get().nombre : ''}" />
        <c:if test="${not empty errores['nombre']}">
          <div class="alert alert-danger mt-1">${errores['nombre']}</div>
        </c:if>
      </div>

      <div class="mb-3">
        <label for="apellidoP" class="form-label">Apellido Paterno:</label>
        <input type="text" class="form-control" id="apellidoP" name="apellidoPaterno" value="${grupoMedico.present ? grupoMedico.get().apellidoPaterno : ''}" />
        <c:if test="${not empty errores['apellidoPaterno']}">
          <div class="alert alert-danger mt-1">${errores['apellidoPaterno']}</div>
        </c:if>
      </div>

      <div class="mb-3">
        <label for="apellidoM" class="form-label">Apellido Materno:</label>
        <input type="text" class="form-control" id="apellidoM" name="apellidoMaterno" value="${grupoMedico.present ? grupoMedico.get().apellidoMaterno : ''}" />
        <c:if test="${not empty errores['apellidoMaterno']}">
          <div class="alert alert-danger mt-1">${errores['apellidoMaterno']}</div>
        </c:if>
      </div>

      <div class="mb-3">
        <label for="genero" class="form-label">Género:</label>
        <select class="form-select" id="genero" name="genero">
          <option value="M" ${grupoMedico.present && grupoMedico.get().genero == 'M' ? 'selected' : ''}>Masculino</option>
          <option value="F" ${grupoMedico.present && grupoMedico.get().genero == 'F' ? 'selected' : ''}>Femenino</option>
          <option value="Otro" ${grupoMedico.present && grupoMedico.get().genero == 'Otro' ? 'selected' : ''}>Otro</option>
        </select>
        <c:if test="${not empty errores['genero']}">
          <div class="alert alert-danger mt-1">${errores['genero']}</div>
        </c:if>
      </div>

      <div class="mb-3">
        <label for="telefono" class="form-label">Teléfono:</label>
        <input type="text" class="form-control" id="telefono" name="telefono" value="${grupoMedico.present ? grupoMedico.get().telefono : ''}" />
        <c:if test="${not empty errores['telefono']}">
          <div class="alert alert-danger mt-1">${errores['telefono']}</div>
        </c:if>
      </div>

      <div class="mb-3">
        <label for="especialidad" class="form-label">Especialidad:</label>
        <input type="text" class="form-control" id="especialidad" name="especialidad" value="${grupoMedico.present ? grupoMedico.get().especialidad : ''}" />
        <c:if test="${not empty errores['especialidad']}">
          <div class="alert alert-danger mt-1">${errores['especialidad']}</div>
        </c:if>
      </div>

      <c:if test="${accion == 'crear'}">
        <div class="mb-3">
          <label for="idUsuario" class="form-label">ID Usuario:</label>
          <input type="text" class="form-control" id="idUsuario" name="idUsuario" value="${grupoMedico.present? grupoMedico.get().idUsuario : ''}"/>
          <c:if test="${not empty errores['idUsuario']}">
            <div class="alert alert-danger mt-1">${errores['idUsuario']}</div>
          </c:if>
        </div>

        <div class="mb-3">
          <label for="contrasenia" class="form-label">Contraseña:</label>
          <input type="password" class="form-control" id="contrasenia" name="contrasenia" value="${grupoMedico.present? grupoMedico.get().contrasenia : ''}"/>
          <c:if test="${not empty errores['contrasenia']}">
            <div class="alert alert-danger mt-1">${errores['contrasenia']}</div>
          </c:if>
        </div>
      </c:if>

      <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
  </div>
</div>

<jsp:include page="/layout/footer.jsp" />
