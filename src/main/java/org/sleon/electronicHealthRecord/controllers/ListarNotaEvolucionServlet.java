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
import java.util.List;

@WebServlet("/grupoMedico/notaEvolucion/Listar")
public class ListarNotaEvolucionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        NotaEvolucionServiceImpl notaEvolucionService = new NotaEvolucionServiceImpl(conn);

        Long idNotaEvolucion;
        try {
            idNotaEvolucion = Long.parseLong(req.getParameter("idAtm"));
        } catch (NumberFormatException e) {
            idNotaEvolucion = 0L;
        }

        List<NotaEvolucion> notasEvolucion = notaEvolucionService.listarPorIdAtencionMedica(idNotaEvolucion);
        req.setAttribute("notasEvolucion", notasEvolucion);
        req.getRequestDispatcher("/notasEvolucionListar.jsp").forward(req, resp);
    }
}
