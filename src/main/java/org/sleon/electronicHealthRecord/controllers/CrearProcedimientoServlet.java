package org.sleon.electronicHealthRecord.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.*;
import org.sleon.electronicHealthRecord.services.ProcedimientoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/grupoMedico/crearProcedimiento")
public class CrearProcedimientoServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/grupoMedico/ProcedimientoForm.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection conn = (Connection) req.getAttribute("conn");
            ProcedimientoServiceImpl procedimientoService = new ProcedimientoServiceImpl(conn);

            HttpSession session = req.getSession();
            UsuarioHospital usuarioHospital = (UsuarioHospital) session.getAttribute("usuarioLog");

           Long procedimientoIdParam;
           try{
               procedimientoIdParam = Long.parseLong(req.getParameter("procedimientoId"));
           }catch (NumberFormatException e){
               procedimientoIdParam = 0L;
           }

           Long idAtencionMedicaParam;
           try{
                idAtencionMedicaParam = Long.parseLong(req.getParameter("idAtencionMedica"));
           }catch (NumberFormatException e){
                idAtencionMedicaParam = 0L;
           }

            Map<String, String> errores = new HashMap<>();

            String descripcion = req.getParameter("descripcion");
            String fechaInicioStr = req.getParameter("fechaInicio");
            String horaInicioStr = req.getParameter("horaInicio");
            String fechaFinStr = req.getParameter("fechaFin");
            String horaFinStr = req.getParameter("horaFin");

            if (descripcion == null || descripcion.isBlank()) {
                errores.put("descripcion", "La descripción es obligatoria.");
            }

            Date fechaInicio = null;
            try {
                fechaInicio = Date.valueOf(fechaInicioStr);
            } catch (Exception e) {
                errores.put("fechaInicio", "Fecha de inicio inválida.");
            }

            Date fechaFin = null;
            try {
                fechaFin = Date.valueOf(fechaFinStr);
            } catch (Exception e) {
                errores.put("fechaFin", "Fecha de fin inválida.");
            }

            Time horaInicio = null;
            try {
                horaInicio = Time.valueOf(horaInicioStr + ":00");
            } catch (Exception e) {
                errores.put("horaInicio", "Hora de inicio inválida.");
            }

            Time horaFin = null;
            try {
                horaFin = Time.valueOf(horaFinStr + ":00");
            } catch (Exception e) {
                errores.put("horaFin", "Hora de fin inválida.");
            }

            if (fechaInicio != null && fechaFin != null && fechaFin.before(fechaInicio)) {
                errores.put("fechaFin", "La fecha de fin no puede ser anterior a la de inicio.");
            }

            if (horaInicio != null && horaFin != null && horaFin.before(horaInicio)) {
                errores.put("horaFin", "La hora de fin no puede ser anterior a la de inicio.");
            }

            ProcedimientoMedico procedimiento = new ProcedimientoMedico();
            procedimiento.setDescripcion(descripcion);
            procedimiento.setFechaInicio(fechaInicio);
            procedimiento.setFechaFin(fechaFin);
            procedimiento.setHoraInicio(horaInicio);
            procedimiento.setHoraFin(horaFin);

            AtencionMedica atencionMedica = new AtencionMedica();
            atencionMedica.setId(idAtencionMedicaParam);

            GrupoMedico grupoMedico = new GrupoMedico();
            grupoMedico.setId(usuarioHospital.getId());

            procedimiento.setAtencionMedica(atencionMedica);
            procedimiento.setGrupoMedico(grupoMedico);

            if(procedimientoIdParam > 0 && errores.isEmpty()){
                procedimiento.setId(procedimientoIdParam);
                procedimientoService.actualizar(procedimiento);
                resp.sendRedirect("/electronicHealthRecord/grupoMedico/ProcedimientoListar?idAtencionMedica=" + idAtencionMedicaParam);
            }else if(errores.isEmpty()){
                procedimientoService.crear(procedimiento);
                resp.sendRedirect("/electronicHealthRecord/grupoMedico/ProcedimientoListar?idAtencionMedica=" + idAtencionMedicaParam);
            }else{
                req.setAttribute("procedimiento", procedimiento);
                req.setAttribute("errores",errores);
                req.getRequestDispatcher("/grupoMedico/ProcedimientoForm.jsp?idAtm="+idAtencionMedicaParam).forward(req, resp);
            }
        }
}
