<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:include page="layout/header.jsp" />

<div class="container mt-5 d-flex flex-column align-items-center">
    <div class="text-center mb-4">
        <img src="img/EHRRB.png" alt="Logo EHL" class="img-fluid rounded shadow-sm" style="max-width: 150px;">
        <h1 class="mt-3" style="font-size: 2.5rem; font-weight: bold;">Electronic Health Record</h1>
        <p class="lead">Seleccione su tipo de usuario para continuar</p>
    </div>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <div class="col">
            <a href="loginAcompañante.jsp" class="text-decoration-none">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <h2 class="card-title" style="font-size: 1.5rem; font-weight: bold;">Acompañante</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col">
            <a href="login.jsp?tipo=grupoMedico" class="text-decoration-none">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <h2 class="card-title" style="font-size: 1.5rem; font-weight: bold;">Grupo Médico</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col">
            <a href="login.jsp?tipo=Admision" class="text-decoration-none">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <h2 class="card-title" style="font-size: 1.5rem; font-weight: bold;">Personal de admisión</h2>
                    </div>
                </div>
            </a>
        </div>

        <div class="col">
            <a href="login.jsp?tipo=Administrador" class="text-decoration-none">
                <div class="card h-100 shadow-sm">
                    <div class="card-body text-center">
                        <h2 class="card-title" style="font-size: 1.5rem; font-weight: bold;">Administrador</h2>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp" />