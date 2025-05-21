<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp"/>

<div class="container mt-5 d-flex justify-content-center">
    <div class="col-md-8">
        <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">
            Registro de atenciones médicas
        </h2>

        <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
            <span class="badge bg-light text-dark p-2">Información del Paciente</span>
        </h3>

        <a href="${pageContext.request.contextPath}/Admision/AtencionMedica/Listar" class="btn btn-secondary mb-3">Regresar</a>

        <form action="${pageContext.request.contextPath}/Admision/AtencionMedica/crear" method="post">

            <div class="mb-3">
                <label for="nomPaciente" class="form-label">Nombre del Paciente:</label>
                <input type="text" class="form-control" id="nomPaciente" name="nomPaciente" value="${param.nomPaciente}">
                <c:if test="${not empty errores.nomPaciente}">
                    <div class="alert alert-danger mt-1">${errores.nomPaciente}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apP_Paciente" class="form-label">Apellido Paterno del Paciente:</label>
                <input type="text" class="form-control" id="apP_Paciente" name="apP_Paciente" value="${param.apP_Paciente}">
                <c:if test="${not empty errores.apP_Paciente}">
                    <div class="alert alert-danger mt-1">${errores.apP_Paciente}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apM_Paciente" class="form-label">Apellido Materno del Paciente:</label>
                <input type="text" class="form-control" id="apM_Paciente" name="apM_Paciente" value="${param.apM_Paciente}">
                <c:if test="${not empty errores.apM_Paciente}">
                    <div class="alert alert-danger mt-1">${errores.apM_Paciente}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="telPaciente" class="form-label">Teléfono del Paciente:</label>
                <input type="text" class="form-control" id="telPaciente" name="telPaciente" value="${param.telPaciente}">
                <c:if test="${not empty errores.telPaciente}">
                    <div class="alert alert-danger mt-1">${errores.telPaciente}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="generoPaciente" class="form-label">Género del Paciente:</label>
                <select class="form-select" id="generoPaciente" name="generoPaciente">
                    <option value="">Seleccione...</option>
                    <option value="M" <c:if test="${param.generoPaciente == 'M'}">selected</c:if>>Masculino</option>
                    <option value="F" <c:if test="${param.generoPaciente == 'F'}">selected</c:if>>Femenino</option>
                    <option value="Otro" <c:if test="${param.generoPaciente == 'Otro'}">selected</c:if>>Otro</option>
                </select>
                <c:if test="${not empty errores.generoPaciente}">
                    <div class="alert alert-danger mt-1">${errores.generoPaciente}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="malestarPaciente" class="form-label">Malestar del Paciente:</label>
                <textarea class="form-control" id="malestarPaciente" name="malestarPaciente">${param.malestarPaciente}</textarea>
                <c:if test="${not empty errores.malestarPaciente}">
                    <div class="alert alert-danger mt-1">${errores.malestarPaciente}</div>
                </c:if>
            </div>

            <h3 class="mb-4 text-center" style="font-size: 1.75rem; color: #6c757d;">
                <span class="badge bg-light text-dark p-2">Información del Acompañante</span>
            </h3>

            <div class="mb-3">
                <label for="nomAcomp" class="form-label">Nombre del Acompañante:</label>
                <input type="text" class="form-control" id="nomAcomp" name="nomAcomp" value="${param.nomAcomp}">
                <c:if test="${not empty errores.nomAcomp}">
                    <div class="alert alert-danger mt-1">${errores.nomAcomp}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apP_Acomp" class="form-label">Apellido Paterno del Acompañante:</label>
                <input type="text" class="form-control" id="apP_Acomp" name="apP_Acomp" value="${param.apP_Acomp}">
                <c:if test="${not empty errores.apP_Acomp}">
                    <div class="alert alert-danger mt-1">${errores.apP_Acomp}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="apM_Acomp" class="form-label">Apellido Materno del Acompañante:</label>
                <input type="text" class="form-control" id="apM_Acomp" name="apM_Acomp" value="${param.apM_Acomp}">
                <c:if test="${not empty errores.apM_Acomp}">
                    <div class="alert alert-danger mt-1">${errores.apM_Acomp}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="telAcomp" class="form-label">Teléfono del Acompañante:</label>
                <input type="text" class="form-control" id="telAcomp" name="telAcomp" value="${param.telAcomp}">
                <c:if test="${not empty errores.telAcomp}">
                    <div class="alert alert-danger mt-1">${errores.telAcomp}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="generoAcomp" class="form-label">Género del Acompañante:</label>
                <select class="form-select" id="generoAcomp" name="generoAcomp">
                    <option value="">Seleccione...</option>
                    <option value="M" <c:if test="${param.generoAcomp == 'M'}">selected</c:if>>Masculino</option>
                    <option value="F" <c:if test="${param.generoAcomp == 'F'}">selected</c:if>>Femenino</option>
                    <option value="Otro" <c:if test="${param.generoAcomp == 'Otro'}">selected</c:if>>Otro</option>
                </select>
                <c:if test="${not empty errores.generoAcomp}">
                    <div class="alert alert-danger mt-1">${errores.generoAcomp}</div>
                </c:if>
            </div>

            <div class="mb-3">
                <label for="parentesco" class="form-label">Parentesco:</label>
                <input type="text" class="form-control" id="parentesco" name="parentesco" value="${param.parentesco}">
                <c:if test="${not empty errores.parentesco}">
                    <div class="alert alert-danger mt-1">${errores.parentesco}</div>
                </c:if>
            </div>

            <c:if test="${not empty errores.atencionMedica}">
                <div class="alert alert-danger text-center">
                    <strong>${errores.atencionMedica}</strong>
                </div>
            </c:if>

            <c:if test="${not empty errores.noPertenece}">
                <div class="alert alert-danger text-center">
                    <strong>${errores.noPertenece}</strong>
                </div>
            </c:if>

            <div>
                <input type="hidden" id="idPersonalAdmision" name="idPersonalAdmision" value="">
            </div>

            <div>
                <input type="hidden" id="idAtm" name="idAtm" value="${idAtm}">
            </div>

            <button type="submit" class="btn btn-primary">Registrar</button>

        </form>
    </div>
</div>

<jsp:include page="/layout/footer.jsp"/>


