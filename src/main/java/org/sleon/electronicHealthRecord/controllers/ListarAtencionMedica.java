package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.models.AtencionMedica;
import org.sleon.electronicHealthRecord.services.AtencionMedicaServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/Admision/AtencionMedica/Listar", "/grupoMedico/AtencionMedica/Listar"})
public class ListarAtencionMedica extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        AtencionMedicaServiceImpl atencionMedicaService = new AtencionMedicaServiceImpl(conn);
        List<AtencionMedica> atencionesMedicas = atencionMedicaService.listarAtencionesMedicasActuales();
        req.setAttribute("atenciones", atencionesMedicas);

        String path = req.getServletPath();
        if (path.equals("/Admision/AtencionMedica/Listar")) {
            String codAcompañante = req.getParameter("codAcompaniante");
            req.setAttribute("codAcompañante", codAcompañante);
            req.getRequestDispatcher("/AtencionesMedicasListar.jsp").forward(req, resp);
        }
        else if (path.equals("/grupoMedico/AtencionMedica/Listar")) {
            req.getRequestDispatcher("/vistaGrupoMedico.jsp").forward(req, resp);
        }
    }
}
