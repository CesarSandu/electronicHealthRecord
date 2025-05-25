package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.models.*;
import org.sleon.electronicHealthRecord.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/administrador/usuarios/listar"})
public class ListarUsuariosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        String tipo = req.getParameter("tipo");

        switch (tipo){
            case "administrador" : AdministradorServiceImpl adminService = new AdministradorServiceImpl(conn);
                List<Administrador> administradores = adminService.listar();
                req.setAttribute("administradores", administradores);
                req.getRequestDispatcher("/PersonalHospitalListar.jsp").forward(req, resp);
                break;

            case "admision" : PersonalAdmisionServiceImpl personalAdmisionService = new PersonalAdmisionServiceImpl(conn);
                List<PersonalAdmision> personalAdmision = personalAdmisionService.listar();
                req.setAttribute("personalAdmision", personalAdmision);
                req.getRequestDispatcher("/PersonalHospitalListar.jsp").forward(req, resp);
                break;

            case "grupoMedico" : GrupoMedicoServiceImpl gpoMedicoService = new GrupoMedicoServiceImpl(conn);
                List<GrupoMedico> grupoMedicos = gpoMedicoService.listar();
                req.setAttribute("grupoMedicos", grupoMedicos);
                req.getRequestDispatcher("/PersonalHospitalListar.jsp").forward(req, resp);
                break;
            }
        }
    }

