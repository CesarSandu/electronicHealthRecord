<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Electronic Health Record</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EHR</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                </li>

                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/horarios.jsp" class="nav-link">Horarios de visita</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Sesión
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/index.jsp">Iniciar sesión</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/logout">Cerrar sesión</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
<jsp:include page="footer.jsp"/>
