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

@WebServlet("/grupoMedico/atencionMedica/registrarAlta")
public class AltaAtencionMedicaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        AtencionMedicaServiceImpl atencionMedicaService = new AtencionMedicaServiceImpl(conn);

        Long idAtencionMedica;
        try{
            idAtencionMedica = Long.parseLong(req.getParameter("idAtm"));
        }catch (NumberFormatException e){
            idAtencionMedica = 0L;
        }

        AtencionMedica atm = new AtencionMedica();
        atm.setId(idAtencionMedica);
        atencionMedicaService.establecerAlta(atm);
        resp.sendRedirect("/electronicHealthRecord/grupoMedico/AtencionMedica/Listar");
    }
}
