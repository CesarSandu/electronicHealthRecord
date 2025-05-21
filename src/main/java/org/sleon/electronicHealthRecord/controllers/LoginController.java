package org.sleon.electronicHealthRecord.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.UsuarioHospital;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/loginPersonalHospital", "/index"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idUsuario = req.getParameter("username");
        String contrasenia = req.getParameter("password");
        String tipo = req.getParameter("tipo");

        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioHospitalService usuarioService = new UsuarioHospitalService(conn);
        Optional<UsuarioHospital> usuarioOpt = usuarioService.login(idUsuario, contrasenia, tipo);

        if (usuarioOpt.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLog", usuarioOpt.get());
            if(usuarioOpt.get().getTipo().equals("Admision") || usuarioOpt.get().getTipo().equals("grupoMedico")){
                resp.sendRedirect(req.getContextPath() + "/" + usuarioOpt.get().getTipo() + "/AtencionMedica/Listar");
            } else if(usuarioOpt.get().getTipo().equals("administrador")){
                resp.sendRedirect(req.getContextPath()  + "/administrador/usuarios/listar?tipo=grupoMedico");
            }
        }else{
            req.setAttribute("error","Credenciales invalidas");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
