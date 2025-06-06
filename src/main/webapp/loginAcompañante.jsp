<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp" />

<div style="padding: 40px 0 0 40px;">
  <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">← Regresar</a>
</div>

<div class="container d-flex justify-content-center align-items-start" style="min-height: 80vh; padding-top: 20px;">
  <div style="width: 100%; max-width: 400px;">
    <div class="card shadow">
      <div class="card-body">
        <h4 class="card-title text-center mb-4">Iniciar Sesión</h4>

        <c:if test="${not empty error}">
          <div class="alert alert-danger text-center" role="alert">
              ${error}
          </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login/Acompanante" method="post">
          <div class="mb-3">
            <label for="codigo" class="form-label">Código de Inicio de Sesión</label>
            <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Introduce tu código" required>
          </div>
          <div class="d-grid mb-3">
            <input type="submit" class="btn btn-primary" value="Iniciar Sesión">
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/layout/footer.jsp" />

