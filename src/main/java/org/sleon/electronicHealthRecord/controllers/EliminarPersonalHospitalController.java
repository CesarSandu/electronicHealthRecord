package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.services.ServiceJdbcException;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;

@WebServlet({"/administrador/usuariosHospital/eliminar", "/Admision/usuariosHospital/eliminar"})
public class EliminarPersonalHospitalController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioHospitalService usuarioHospitalService = new UsuarioHospitalService(conn);

        Long idUsuario = Long.parseLong(req.getParameter("id"));
        String tipoUsuario = req.getParameter("tipo");

        try {
            usuarioHospitalService.eliminarUsuario(idUsuario);
        } catch (ServiceJdbcException e) {
            req.getSession().setAttribute("error", "No es posible eliminar el usuario porque tiene registros asociados");
        }

        switch (tipoUsuario){
            case "grupoMedico" -> req.getRequestDispatcher("/administrador/usuarios/listar?tipo=grupoMedico").forward(req, resp);
            case "admision" -> req.getRequestDispatcher("/administrador/usuarios/listar?tipo=admision").forward(req, resp);
        }

    }
}
