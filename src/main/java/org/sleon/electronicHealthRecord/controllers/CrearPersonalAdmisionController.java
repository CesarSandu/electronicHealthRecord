package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.PersonalAdmision;
import org.sleon.electronicHealthRecord.models.UsuarioHospital;
import org.sleon.electronicHealthRecord.services.PersonalAdmisionServiceImpl;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Optional;

@WebServlet("/administrador/personalAdmision/actualizar")
public class CrearPersonalAdmisionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PersonalAdmisionServiceImpl personalAdmisionService = new PersonalAdmisionServiceImpl(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if(id > 0 ){
            Optional<PersonalAdmision> personalAdmision = personalAdmisionService.listarPorId(id);
            req.setAttribute("accion","actualizar");
            req.setAttribute("pad", personalAdmision);
        }
        req.getRequestDispatcher("/administrador/personalAdmisionForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        HttpSession session = req.getSession();
        UsuarioHospital administrador = (UsuarioHospital) session.getAttribute("usuarioLog");

        PersonalAdmisionServiceImpl admisionService = new PersonalAdmisionServiceImpl(conn);
        UsuarioHospitalService usuarioHospitalService = new UsuarioHospitalService(conn);

        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id_usuario"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        String nombre = req.getParameter("nombre");
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String genero = req.getParameter("genero");
        String telefono = req.getParameter("telefono");
        String accion = req.getParameter("accion");


        HashMap<String,String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio.");
        }

        if (apellidoPaterno == null || apellidoPaterno.isBlank()) {
            errores.put("apellidoPaterno", "El apellido paterno es obligatorio.");
        }

        if (apellidoMaterno == null || apellidoMaterno.trim().isEmpty()) {
            errores.put("apellidoMaterno", "El apellido materno es obligatorio.");
        }

        if (genero == null || genero.isBlank() ||
                (!genero.equalsIgnoreCase("M") &&
                        !genero.equalsIgnoreCase("F") &&
                        !genero.equalsIgnoreCase("Otro"))) {

            errores.put("genero", "El género debe ser 'masculino', 'femenino' u 'otro'.");
        }


        if (telefono == null || !telefono.matches("\\d{10}")) {
            errores.put("telefono", "El teléfono debe contener 10 dígitos numéricos.");
        }

        if(usuarioHospitalService.verificarTelefono(telefono, id)){
            errores.put("telefono", "El numero de telefono le pertenece a otro usuario");
        }

        PersonalAdmision pad = new PersonalAdmision();
        pad.setId(id);
        pad.setNombre(nombre);
        pad.setApellidoPaterno(apellidoPaterno);
        pad.setApellidoMaterno(apellidoMaterno);
        pad.setGenero(genero);
        pad.setTelefono(telefono);
        pad.setResponsableRegistro(administrador);

        //VERIFICACION DE CAMPOS ID DE USUARIO Y CONTRASEÑA
        if(accion != null && accion.equals("crear")){

            String idUsuario = req.getParameter("idUsuario");
            String contrasenia = req.getParameter("contrasenia");

            if (idUsuario == null || idUsuario.isBlank()) {
                errores.put("idUsuario", "El id del usuario es obligatorio");
            }

            if(usuarioHospitalService.verificarIdusuario(idUsuario, id)){
                errores.put("idUsuario", "El id de usuario ya está en uso");
            }

            if (contrasenia == null || contrasenia.isBlank()) {
                errores.put("contrasenia", "La contraseña es obligatoria.");
            }

            pad.setIdUsuario(idUsuario);
            pad.setContrasenia(contrasenia);
        }

        req.setAttribute("pad", Optional.ofNullable(pad));

        if (errores.isEmpty()) {
            if(id > 0){
                admisionService.actualizar(pad);
            }else{
                admisionService.crear(pad);
            }
            resp.sendRedirect("${pageContext.request.contextPath}/administrador/usuarios/listar?tipo=admision");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("accion", "crear");
            req.getRequestDispatcher("/administrador/personalAdmisionForm.jsp").forward(req, resp);
        }
    }
}