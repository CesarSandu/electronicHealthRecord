<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp" />

<div class="container py-4">
    <h2 class="mb-4 text-center" style="font-size: 2rem; font-weight: bold; color: #4e73df;">Horarios de Visita</h2>

    <p class="lead text-center mb-4">Estos son los horarios de visita generales para nuestros pacientes.</p>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table table-bordered table-hover shadow-sm">
                <thead class="table-light text-center">
                <tr>
                    <th>Día de la Semana</th>
                    <th>Horario de Inicio</th>
                    <th>Horario de Fin</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="text-center">Lunes a Viernes</td>
                    <td class="text-center">16:00</td>
                    <td class="text-center">18:00</td>
                </tr>
                <tr>
                    <td class="text-center">Sábado</td>
                    <td class="text-center">10:00</td>
                    <td class="text-center">12:00</td>
                </tr>
                <tr>
                    <td class="text-center">Sábado</td>
                    <td class="text-center">16:00</td>
                    <td class="text-center">18:00</td>
                </tr>
                <tr>
                    <td class="text-center">Domingo</td>
                    <td class="text-center">10:00</td>
                    <td class="text-center">12:00</td>
                </tr>
                <tr>
                    <td class="text-center">Domingo</td>
                    <td class="text-center">14:00</td>
                    <td class="text-center">16:00</td>
                </tr>
                </tbody>
            </table>

            <div class="mt-4">
                <p class="text-muted"><small>Por favor, respete estos horarios para el bienestar de nuestros pacientes. Los horarios pueden estar sujetos a cambios según las necesidades del hospital.</small></p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/layout/footer.jsp" />