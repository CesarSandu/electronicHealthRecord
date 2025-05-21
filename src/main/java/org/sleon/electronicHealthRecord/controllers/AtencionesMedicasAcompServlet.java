package org.sleon.electronicHealthRecord.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.models.AtencionMedica;
import org.sleon.electronicHealthRecord.services.AtencionMedicaServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/Acompanante/Listar")
public class AtencionesMedicasAcompServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HttpSession session = req.getSession();
        Acompaniante acompaniante = (Acompaniante) session.getAttribute("usuarioLog");

        AtencionMedicaServiceImpl atencionMedicaService = new AtencionMedicaServiceImpl(conn);
        List<AtencionMedica> atencionMedicas = atencionMedicaService.listarPoridAcompañante(acompaniante.getId());

        req.setAttribute("atenciones", atencionMedicas);
        req.getRequestDispatcher("/vistaPaciente.jsp").forward(req, resp);
    }
}
