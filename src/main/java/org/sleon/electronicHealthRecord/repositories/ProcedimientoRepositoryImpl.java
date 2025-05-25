package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.GrupoMedico;
import org.sleon.electronicHealthRecord.models.ProcedimientoMedico;
import org.sleon.electronicHealthRecord.repositories.interfaces.ProcedimientoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoRepositoryImpl implements ProcedimientoRepository {

    private Connection conn;

    public ProcedimientoRepositoryImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public List<ProcedimientoMedico> listarProcedimientosMedicos(Long id) throws SQLException {
        String sql = "SELECT * FROM Procedimiento WHERE idAtencionMedica = ?";
        List<ProcedimientoMedico> procedimientosMedicos = new ArrayList<ProcedimientoMedico>();
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProcedimientoMedico procedimientoMedico = new ProcedimientoMedico();
                    GrupoMedico grupoMedico = new GrupoMedico();
                    grupoMedico.setId(rs.getLong("idGrupoMedicoEncargado"));
                    procedimientoMedico.setId(rs.getLong("id"));
                    procedimientoMedico.setHoraInicio(rs.getTime("horaInicio"));
                    procedimientoMedico.setHoraFin(rs.getTime("horaFin"));
                    procedimientoMedico.setFechaInicio(rs.getDate("fechaInicio"));
                    procedimientoMedico.setFechaFin(rs.getDate("fechaFin"));
                    procedimientoMedico.setDescripcion(rs.getString("descripcion"));

                    procedimientosMedicos.add(procedimientoMedico);
                }
            }
        }
        return procedimientosMedicos;
    }

    public void crear(ProcedimientoMedico procedimiento) throws SQLException {
        String sql = "{CALL RegistrarProcedimiento(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, procedimiento.getAtencionMedica().getId());
            stmt.setLong(2, procedimiento.getGrupoMedico().getId());
            stmt.setString(3, procedimiento.getDescripcion());
            stmt.setTime(4, procedimiento.getHoraInicio());
            stmt.setDate(5, procedimiento.getFechaInicio());
            stmt.setDate(6, procedimiento.getFechaFin());
            stmt.setTime(7, procedimiento.getHoraFin());
            stmt.execute();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM Procedimiento WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(ProcedimientoMedico procedimiento) throws SQLException {
        String sql = "UPDATE Procedimiento SET " +
                "idNotaEvolucion = ?, " +
                "idGrupoMedicoEncargado = ?, " +
                "descripcion = ?, " +
                "horaInicio = ?, " +
                "fechaInicio = ?, " +
                "fechaFin = ?, " +
                "horaFin = ? " +
                "WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, procedimiento.getGrupoMedico().getId());
            stmt.setString(2, procedimiento.getDescripcion());
            stmt.setTime(3, procedimiento.getHoraInicio());
            stmt.setDate(4, procedimiento.getFechaInicio());
            stmt.setDate(5, procedimiento.getFechaFin());
            stmt.setTime(6, procedimiento.getHoraFin());
            stmt.setLong(7, procedimiento.getId());

            stmt.executeUpdate();
        }
    }

}
