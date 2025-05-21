package org.sleon.electronicHealthRecord.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login/Acompanante")
public class LoginAcompananteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoInicioSesion = req.getParameter("codigo");

        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioHospitalService usuarioService = new UsuarioHospitalService(conn);

        Optional<Acompaniante> acompaniante = usuarioService.loginAcompaniante(codigoInicioSesion);

        if (acompaniante.isPresent() && acompaniante.get().getTipo().equals("Acompanante")) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLog", acompaniante.get());
            resp.sendRedirect(req.getContextPath() + "/Acompanante/Listar");
        }else{
            req.setAttribute("error","Credenciales invalidas");
            req.getRequestDispatcher("/loginAcompañante.jsp").forward(req,resp);
        }
    }
}


