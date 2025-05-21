<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout/header.jsp"/>


<div style="padding: 40px 0 0 40px;">
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">← Regresar</a>
</div>


<div class="container d-flex justify-content-center align-items-start" style="min-height: 80vh; padding-top: 20px;">
    <div class="card shadow-lg" style="width: 100%; max-width: 400px;">
        <div class="card-body">
            <h4 class="card-title text-center mb-4">Iniciar Sesión</h4>

            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center" role="alert">
                        ${error}
                </div>
            </c:if>

            <form method="post" action="loginPersonalHospital">
                <div class="mb-3">
                    <label for="username" class="form-label">Nombre de usuario</label>
                    <input type="text" class="form-control" id="username" name="username" required />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required />
                </div>


                <div>
                    <input type="hidden" name="tipo" value=${param.tipo}>
                </div>

                <div class="d-grid">
                    <input type="submit" class="btn btn-primary" value="Ingresar" />
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>


