package org.sleon.electronicHealthRecord.controllers;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.sleon.electronicHealthRecord.models.GrupoMedico;
import org.sleon.electronicHealthRecord.models.UsuarioHospital;
import org.sleon.electronicHealthRecord.services.GrupoMedicoServiceImpl;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Optional;

@WebServlet("/administrador/grupoMedico/actualizar")
public class CrearGrupoMedicoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        GrupoMedicoServiceImpl grupoMedicoService = new GrupoMedicoServiceImpl(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if(id > 0 ){
            Optional<GrupoMedico> grupoMedico = grupoMedicoService.listarPorId(id);
            req.setAttribute("accion","actualizar");
            req.setAttribute("grupoMedico", grupoMedico);
        }
        req.getRequestDispatcher("/administrador/grupoMedicoForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        HttpSession session = req.getSession();
        UsuarioHospital administrador = (UsuarioHospital) session.getAttribute("usuarioLog");

        GrupoMedicoServiceImpl grupoMedicoService = new GrupoMedicoServiceImpl(conn);
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
        String especialidad = req.getParameter("especialidad");
        String accion = req.getParameter("accion");
        String idUsuario = "";
        String contraseña = "";

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


        if (especialidad == null || especialidad.trim().isEmpty()) {
            errores.put("especialidad", "La especialidad es obligatoria.");
        }

        GrupoMedico gm = new GrupoMedico();
        gm.setId(id);
        gm.setNombre(nombre);
        gm.setApellidoPaterno(apellidoPaterno);
        gm.setApellidoMaterno(apellidoMaterno);
        gm.setGenero(genero);
        gm.setTelefono(telefono);
        gm.setEspecialidad(especialidad);

        //VERIFICACION DE CAMPOS ID DE USUARIO Y CONTRASEÑA
        if(accion != null && accion.equals("crear")){
            idUsuario = req.getParameter("idUsuario");
            contraseña = req.getParameter("contraseña");

            if (idUsuario == null || idUsuario.isBlank()) {
                errores.put("idUsuario", "El id del usuario es obligatorio");
            }

            if(usuarioHospitalService.verificarIdusuario(idUsuario, id)){
                errores.put("idUsuario", "El id de usuario ya existe.");
            }

            if (contraseña == null || contraseña.isBlank()) {
                errores.put("contraseña", "la contraseña es obligatoria.");
            }
            gm.setIdUsuario(idUsuario);
            gm.setContrasenia(contraseña);
        }

        req.setAttribute("grupoMedico", Optional.ofNullable(gm));

        if (errores.isEmpty()) {
            if(id > 0){
                grupoMedicoService.actualizar(gm);
            }else{
                gm.setIdUsuario(idUsuario);
                gm.setContrasenia(contraseña);
                gm.setResponsableRegistro(administrador);
                grupoMedicoService.crear(gm);
            }
            resp.sendRedirect("/electronicHealthRecord/administrador/usuarios/listar?tipo=grupoMedico");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("accion", "crear");
            req.getRequestDispatcher("/administrador/grupoMedicoForm.jsp").forward(req, resp);
        }
    }
}
