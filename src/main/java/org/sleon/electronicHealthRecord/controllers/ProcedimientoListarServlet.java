package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.models.ProcedimientoMedico;
import org.sleon.electronicHealthRecord.services.ProcedimientoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/grupoMedico/ProcedimientoListar", "/Acompanante/ProcedimientoListar"})
public class ProcedimientoListarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conexion = (Connection) request.getAttribute("conn");
        ProcedimientoServiceImpl procedimientoService = new ProcedimientoServiceImpl(conexion);

        Long idAtencionMedica;
        try {
            idAtencionMedica = Long.parseLong(request.getParameter("idAtencionMedica"));
        } catch (NumberFormatException e) {
            idAtencionMedica = 0L;
        }

        if(idAtencionMedica > 0){
            List<ProcedimientoMedico> procedimientos = procedimientoService.listar(idAtencionMedica);
            request.setAttribute("procedimientos", procedimientos);
        }
        request.getRequestDispatcher("/ProcedimientosListar.jsp").forward(request, response);
    }
}
