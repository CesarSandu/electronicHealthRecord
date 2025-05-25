package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.NotaEvolucion;
import org.sleon.electronicHealthRecord.repositories.interfaces.NotaEvolucionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaEvolucionRepositoryImpl implements NotaEvolucionRepository {

    private Connection conn;

    public NotaEvolucionRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<NotaEvolucion> listarPorIdAtencionMedica(Long id) throws SQLException {
        List<NotaEvolucion> notas = new ArrayList<>();
        String sql = "SELECT * FROM NotaEvolucion WHERE idAtencionMedica = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NotaEvolucion nota = new NotaEvolucion();
                    nota.setId(rs.getLong("id"));
                    nota.setSignos_sintomas(rs.getString("signosSintomas"));
                    nota.setAnalisisEvolucionClinica(rs.getString("analisisEvolucionClinica"));
                    nota.setPlanEstudioTratamiento(rs.getString("planEstudioTratamiento"));
                    nota.setComentariosUltimosExamenes(rs.getString("comentariosUltimosExamenes"));
                    nota.setDatosExploracionFisica(rs.getString("datosExploracionFisica"));
                    nota.setDiasEstacionHospitalaria(rs.getInt("diasEstacionHospitalaria"));
                    nota.setDiagnosticosActuales(rs.getString("diagnosticosActuales"));
                    nota.setFecha(rs.getDate("fecha").toLocalDate());
                    nota.setHora(rs.getTime("hora").toLocalTime());
                    notas.add(nota);
                }
            }
        }
        return notas;
    }

    @Override
    public Optional<NotaEvolucion> listarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM NotaEvolucion WHERE id = (?)";
        NotaEvolucion nota = null;
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    nota = getNotaEvolucion(rs);
                }
            }
        }
        return Optional.ofNullable(nota);
    }

    @Override
    public void crear(NotaEvolucion notaEvolucion) throws SQLException {
        String query = """
    
                INSERT INTO NotaEvolucion (
        idAtencionMedica, 
        signosSintomas, 
        analisisEvolucionClinica, 
        planEstudioTratamiento,
        comentariosUltimosExamenes, 
        datosExploracionFisica, 
        diasEstacionHospitalaria,
        diagnosticosActuales, 
        fecha, 
        hora
    ) 
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), CURTIME())
    """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, notaEvolucion.getAtencionMedica().getId());
            stmt.setString(2, notaEvolucion.getSignos_sintomas());
            stmt.setString(3, notaEvolucion.getAnalisisEvolucionClinica());
            stmt.setString(4, notaEvolucion.getPlanEstudioTratamiento());
            stmt.setString(5, notaEvolucion.getComentariosUltimosExamenes());
            stmt.setString(6, notaEvolucion.getDatosExploracionFisica());
            stmt.setInt(7, notaEvolucion.getDiasEstacionHospitalaria());
            stmt.setString(8, notaEvolucion.getDiagnosticosActuales());
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(NotaEvolucion nota) throws SQLException {
        String sql =
                """
    UPDATE
                NotaEvolucion
                    SET
                    signosSintomas = ?,
                    analisisEvolucionClinica = ?,
                    planEstudioTratamiento = ?,
                    comentariosUltimosExamenes = ?,
                    datosExploracionFisica = ?,
                    diagnosticosActuales = ?
    WHERE
                id = ?
    """;


        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nota.getSignos_sintomas());
            stmt.setString(2, nota.getAnalisisEvolucionClinica());
            stmt.setString(3, nota.getPlanEstudioTratamiento());
            stmt.setString(4, nota.getComentariosUltimosExamenes());
            stmt.setString(5, nota.getDatosExploracionFisica());
            stmt.setString(6, nota.getDiagnosticosActuales());
            stmt.setLong(7, nota.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public boolean existe(Long id, Date fecha) throws SQLException{
        String query = "SELECT COUNT(*) FROM NotaEvolucion WHERE idAtencionMedica = ? AND fecha = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.setDate(2, fecha);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
}

    private static NotaEvolucion getNotaEvolucion(ResultSet rs) throws SQLException {
        NotaEvolucion nota;
        nota = new NotaEvolucion();
        nota.setId(rs.getLong("id"));
        nota.setSignos_sintomas(rs.getString("signosSintomas"));
        nota.setAnalisisEvolucionClinica(rs.getString("analisisEvolucionClinica"));
        nota.setPlanEstudioTratamiento(rs.getString("planEstudioTratamiento"));
        nota.setComentariosUltimosExamenes(rs.getString("comentariosUltimosExamenes"));
        nota.setDatosExploracionFisica(rs.getString("datosExploracionFisica"));
        nota.setDiasEstacionHospitalaria(rs.getInt("diasEstacionHospitalaria"));
        nota.setDiagnosticosActuales(rs.getString("diagnosticosActuales"));
        nota.setFecha(rs.getDate("fecha").toLocalDate());
        nota.setHora(rs.getTime("hora").toLocalTime());
        return nota;
    }
}
