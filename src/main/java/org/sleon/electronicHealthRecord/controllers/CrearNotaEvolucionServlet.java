package org.sleon.electronicHealthRecord.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.AtencionMedica;
import org.sleon.electronicHealthRecord.models.NotaEvolucion;
import org.sleon.electronicHealthRecord.services.NotaEvolucionServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;

@WebServlet("/grupoMedico/notaEvolucion/crear")
public class CrearNotaEvolucionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        NotaEvolucionServiceImpl notaEvolucionService = new NotaEvolucionServiceImpl(conn);

        Long idAtencionMedica;
        Long idNotaEvolucion;
        String signosSintomas = req.getParameter("signosSintomas");
        String analisisEvolucionClinica = req.getParameter("analisisEvolucionClinica");
        String planEstudioTratamiento = req.getParameter("planEstudioTratamiento");
        String comentariosUltimosExamenes = req.getParameter("comentariosUltimosExamenes");
        String datosExploracionFisica = req.getParameter("datosExploracionFisica");
        String diagnosticosActuales = req.getParameter("diagnosticosActuales");
        int diasEstacionHospitalaria = 0;

        try{
            idAtencionMedica = Long.parseLong(req.getParameter("idAtm"));
        } catch (NumberFormatException e) {
            idAtencionMedica = 0L;
        }


        try{
            idNotaEvolucion = Long.parseLong(req.getParameter("idnev"));
        }catch (NumberFormatException e) {
            idNotaEvolucion = 0L;
        }

        HashMap<String,String> errores = new HashMap<>();

        AtencionMedica atencionMedica = new AtencionMedica();
        atencionMedica.setId(idAtencionMedica);

        diasEstacionHospitalaria = notaEvolucionService.listarPorIdAtencionMedica(idAtencionMedica).size();

        NotaEvolucion notaEvolucion = new NotaEvolucion();
        notaEvolucion.setSignos_sintomas(signosSintomas);
        notaEvolucion.setAnalisisEvolucionClinica(analisisEvolucionClinica);
        notaEvolucion.setPlanEstudioTratamiento(planEstudioTratamiento);
        notaEvolucion.setComentariosUltimosExamenes(comentariosUltimosExamenes);
        notaEvolucion.setDatosExploracionFisica(datosExploracionFisica);
        notaEvolucion.setDiasEstacionHospitalaria(diasEstacionHospitalaria);
        notaEvolucion.setDiagnosticosActuales(diagnosticosActuales);
        notaEvolucion.setAtencionMedica(atencionMedica);
        Date fechaActual = new Date(System.currentTimeMillis());


        if(idNotaEvolucion > 0 ){
            notaEvolucion.setId(idNotaEvolucion);
            notaEvolucionService.actualizar(notaEvolucion);
            resp.sendRedirect("/electronicHealthRecord/grupoMedico/AtencionMedica/Listar");
            return;
        }

        if(notaEvolucionService.existe(idAtencionMedica,fechaActual)){
            errores.put("atm","solo es posible crear una nota de evolucion por dia!");
            HttpSession session =  req.getSession();
            session.setAttribute("error","!solo es posible crear una nota de evolucion por dia!");
            resp.sendRedirect("/electronicHealthRecord/grupoMedico/AtencionMedica/Listar");
        }else{
            notaEvolucionService.crear(notaEvolucion);
            resp.sendRedirect("/electronicHealthRecord/grupoMedico/notaEvolucion/Listar?idAtm=" + idAtencionMedica);
        }
    }
}
