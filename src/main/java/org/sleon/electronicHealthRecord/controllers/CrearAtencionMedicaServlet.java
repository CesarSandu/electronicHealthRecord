package org.sleon.electronicHealthRecord.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.models.AtencionMedica;
import org.sleon.electronicHealthRecord.models.Paciente;
import org.sleon.electronicHealthRecord.models.UsuarioHospital;
import org.sleon.electronicHealthRecord.services.AtencionMedicaServiceImpl;
import org.sleon.electronicHealthRecord.services.UsuarioHospitalService;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Admision/AtencionMedica/crear")
public class CrearAtencionMedicaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Connection conn = (Connection) request.getAttribute("conn");
        AtencionMedicaServiceImpl atencionMedicaService = new AtencionMedicaServiceImpl(conn);
        UsuarioHospitalService usuarioHospitalService = new UsuarioHospitalService(conn);

        UsuarioHospital pad = (UsuarioHospital) session.getAttribute("usuarioLog");

        // Recibir parámetros
        String nomPaciente = request.getParameter("nomPaciente");
        String apP_Paciente = request.getParameter("apP_Paciente");
        String apM_Paciente = request.getParameter("apM_Paciente");
        String telPaciente = request.getParameter("telPaciente");
        String generoPaciente = request.getParameter("generoPaciente");
        String malestarPaciente = request.getParameter("malestarPaciente");

        String nomAcomp = request.getParameter("nomAcomp");
        String apP_Acomp = request.getParameter("apP_Acomp");
        String apM_Acomp = request.getParameter("apM_Acomp");
        String telAcomp = request.getParameter("telAcomp");
        String generoAcomp = request.getParameter("generoAcomp");
        String parentesco = request.getParameter("parentesco");

        Map<String, String> errores = new HashMap<>();

        // Validar campos del paciente
        if (nomPaciente == null || nomPaciente.isBlank()) {
            errores.put("nomPaciente", "El nombre del paciente es obligatorio");
        }
        if (apP_Paciente == null || apP_Paciente.isBlank()) {
            errores.put("apP_Paciente", "El apellido paterno del paciente es obligatorio");
        }
        if (apM_Paciente == null || apM_Paciente.isBlank()) {
            errores.put("apM_Paciente", "El apellido materno del paciente es obligatorio");
        }
        if (telPaciente == null || !telPaciente.matches("^\\d{10}$")) {
            errores.put("telPaciente", "El teléfono del paciente debe tener exactamente 10 dígitos numéricos");
        }
        if(telPaciente == null || !telPaciente.matches("^\\d{10}$") || telPaciente.equals(telAcomp)){
            errores.put("telAcomp", "no es posible tener el mismo numero de telefono");
        }
        if (generoPaciente == null || generoPaciente.isBlank()) {
            errores.put("generoPaciente", "El género del paciente es obligatorio");
        }
        if (malestarPaciente == null || malestarPaciente.isBlank()) {
            errores.put("malestarPaciente", "El malestar del paciente es obligatorio");
        }

        //Validar los campos del acompañante
        if (nomAcomp == null || nomAcomp.isBlank()) {
            errores.put("nomAcomp", "El nombre del acompañante es obligatorio");
        }

        if (telAcomp == null || !telAcomp.matches("^\\d{10}$")) {
            errores.put("telAcomp", "El teléfono del acompañante debe tener exactamente 10 dígitos numéricos");
        }

        if(telAcomp == null || !telAcomp.matches("^\\d{10}$") || telAcomp.equals(telPaciente)){
            errores.put("telAcomp", "no es posible tener el mismo numero de telefono");
        }

        if (apP_Acomp == null || apP_Acomp.isBlank()) {
            errores.put("apP_Acomp", "El apellido paterno del acompañante es obligatorio");
        }
        if (apM_Acomp == null || apM_Acomp.isBlank()) {
            errores.put("apM_Acomp", "El apellido materno del acompañante es obligatorio");
        }
        if (generoAcomp == null || generoAcomp.isBlank()) {
            errores.put("generoAcomp", "El género del acompañante es obligatorio ");
        }
        if (parentesco == null || parentesco.isBlank()) {
            errores.put("parentesco", "El parentesco es obligatorio");
        }

        if(atencionMedicaService.buscaratencionMedicaActual(telPaciente).isPresent()){
            errores.put("atencionMedica", "El paciente tiene una atencion medica en curso");
        };

        if (errores.isEmpty()) {
            Paciente paciente = new Paciente();
            paciente.setTelefono(telPaciente);
            paciente.setNombre(nomPaciente);
            paciente.setApellidoMaterno(apM_Paciente);
            paciente.setApellidoPaterno(apP_Paciente);
            paciente.setGenero(generoPaciente);
            paciente.setMalestar(malestarPaciente);
            paciente.setPersonalAdmision(pad);

            Acompaniante acompaniante = new Acompaniante();
            acompaniante.setNombre(nomAcomp);
            acompaniante.setApellidoMaterno(apM_Acomp);
            acompaniante.setApellidoPaterno(apP_Acomp);
            acompaniante.setTelefono(telAcomp);
            acompaniante.setGenero(generoAcomp);
            acompaniante.setParentesco(parentesco);
            acompaniante.setResponsableRegistro(pad);

            AtencionMedica atencionMedica = new AtencionMedica();
            atencionMedica.setPaciente(paciente);
            atencionMedica.setAcompaniante(acompaniante);
            atencionMedica.setResponsableRegistro(pad);

            try {
                atencionMedicaService.crear(atencionMedica);
            } catch (Exception e) {
                errores.put("noPertenece",e.getMessage());
                request.setAttribute("errores", errores);
                request.getRequestDispatcher("/Admision/AtencionMedicaForm.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("codAcompañante",atencionMedica.getAcompaniante().getCodigoInicioSession());
            response.sendRedirect(request.getContextPath() + "/Admision/AtencionMedica/Listar");
        } else {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("/Admision/AtencionMedicaForm.jsp").forward(request, response);
        }
    }
}
