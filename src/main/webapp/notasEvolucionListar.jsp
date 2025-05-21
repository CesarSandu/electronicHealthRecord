<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />

<div class="container py-4">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">Notas de Evolución</h2>

    <a href="${pageContext.request.contextPath}/grupoMedico/AtencionMedica/Listar" class="btn btn-secondary mb-3">Regresar</a>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach var="nev" items="${notasEvolucion}">
            <div class="col d-flex justify-content-center">
                <div class="card shadow-sm" style="width: 18rem;">
                    <div class="card-body text-center">
                        <h5 class="card-title" style="font-size: 1.25rem;">ID: ${nev.id}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            Fecha: ${nev.fecha} <br>
                            Hora: ${nev.hora}
                        </h6>
                        <a href="${pageContext.request.contextPath}/grupoMedico/desplegarNotaEvolucion?nevid=${nev.id}" class="btn btn-primary btn-block w-100">Ver nota de evolución</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />

