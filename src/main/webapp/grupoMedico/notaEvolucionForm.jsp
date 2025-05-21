<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp" />

<%
    String idAtm = request.getParameter("idAtm");
%>

<div class="container mt-5 d-flex justify-content-center">
    <div class="col-md-8">
        <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold;">Registrar Nota de Evolución</h2>

        <a href="${pageContext.request.contextPath}/grupoMedico/AtencionMedica/Listar" class="btn btn-secondary mb-3">Regresar</a>

        <form action="${pageContext.request.contextPath}/grupoMedico/notaEvolucion/crear" method="post">
            <div class="mb-3">
                <label for="signosSintomas" class="form-label">Signos y Síntomas</label>
                <textarea class="form-control" id="signosSintomas" name="signosSintomas" rows="3">${notaEvolucion.signos_sintomas}</textarea>
            </div>

            <div class="mb-3">
                <label for="analisisEvolucionClinica" class="form-label">Análisis y Evolución Clínica</label>
                <textarea class="form-control" id="analisisEvolucionClinica" name="analisisEvolucionClinica" rows="3">${notaEvolucion.analisisEvolucionClinica}</textarea>
            </div>

            <div class="mb-3">
                <label for="planEstudioTratamiento" class="form-label">Plan de Estudio y Tratamiento</label>
                <textarea class="form-control" id="planEstudioTratamiento" name="planEstudioTratamiento" rows="3">${notaEvolucion.planEstudioTratamiento}</textarea>
            </div>

            <div class="mb-3">
                <label for="comentariosUltimosExamenes" class="form-label">Comentarios de Últimos Exámenes</label>
                <textarea class="form-control" id="comentariosUltimosExamenes" name="comentariosUltimosExamenes" rows="3">${notaEvolucion.comentariosUltimosExamenes}</textarea>
            </div>

            <div class="mb-3">
                <label for="datosExploracionFisica" class="form-label">Datos de Exploración Física</label>
                <textarea class="form-control" id="datosExploracionFisica" name="datosExploracionFisica" rows="3">${notaEvolucion.datosExploracionFisica}</textarea>
            </div>

            <div class="mb-3">
                <label for="diagnosticosActuales" class="form-label">Diagnósticos Actuales</label>
                <textarea class="form-control" id="diagnosticosActuales" name="diagnosticosActuales" rows="3">${notaEvolucion.diagnosticosActuales}</textarea>
            </div>

            <input type="hidden" name="idAtm" value="<%=idAtm%>">
            <input type="hidden" name="idnev" value="${notaEvolucion.id}">

            <div>
                <button type="submit" class="btn btn-primary">Guardar Nota de Evolución</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />
