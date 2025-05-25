package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.models.NotaEvolucion;
import org.sleon.electronicHealthRecord.services.NotaEvolucionServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/grupoMedico/desplegarNotaEvolucion")
public class DesplegarNotaEvolucionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        NotaEvolucionServiceImpl notaEvolucionService = new NotaEvolucionServiceImpl(conn);

        Long id = null;
        try{
            id = Long.parseLong(req.getParameter("nevid"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        Optional<NotaEvolucion> notaEvolucion = notaEvolucionService.listarPorId(id);
        req.setAttribute("notaEvolucion", notaEvolucion.get());
        req.getRequestDispatcher("/grupoMedico/notaEvolucionForm.jsp").forward(req, resp);
    }
}
