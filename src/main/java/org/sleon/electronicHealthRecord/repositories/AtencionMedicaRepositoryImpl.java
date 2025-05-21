package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.models.AtencionMedica;
import org.sleon.electronicHealthRecord.models.Paciente;
import org.sleon.electronicHealthRecord.repositories.interfaces.AtencionMedicaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AtencionMedicaRepositoryImpl implements AtencionMedicaRepository {
    private Connection conn;

    public AtencionMedicaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(AtencionMedica atencionMedica) throws SQLException {
        String sql = "{CALL CrearAtencionMedicaCompleta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, atencionMedica.getPaciente().getTelefono());
            stmt.setString(2, atencionMedica.getPaciente().getNombre());
            stmt.setString(3, atencionMedica.getPaciente().getApellidoMaterno());
            stmt.setString(4, atencionMedica.getPaciente().getApellidoPaterno());
            stmt.setString(5, atencionMedica.getPaciente().getGenero());
            stmt.setString(6, atencionMedica.getPaciente().getMalestar());

            stmt.setString(7, atencionMedica.getAcompaniante().getTelefono());
            stmt.setString(8, atencionMedica.getAcompaniante().getNombre());
            stmt.setString(9, atencionMedica.getAcompaniante().getApellidoMaterno());
            stmt.setString(10, atencionMedica.getAcompaniante().getApellidoPaterno());
            stmt.setString(11, atencionMedica.getAcompaniante().getGenero());
            stmt.setString(12, atencionMedica.getAcompaniante().getParentesco());

            stmt.setLong(13, atencionMedica.getResponsableRegistro().getId());
            stmt.registerOutParameter(14, Types.VARCHAR);
            stmt.execute();
            atencionMedica.getAcompaniante().setCodigoInicioSession(stmt.getString(14));
        }
    }

    @Override
    public List<AtencionMedica> listar() throws SQLException {
        List<AtencionMedica> lista = new ArrayList<>();

        String sql = """
        SELECT 
            am.id AS idAtencion,
            am.fechaIngreso,
            am.horaIngreso,
            am.fechaEgreso,
            am.horaEgreso,
            am.estado, 

            p.id AS idPaciente,
            p.nombre AS nombrePaciente,
            p.apellidoP AS apellidoPPaciente,
            p.apellidoM AS apellidoMPaciente,
            p.telefono AS telefonoPaciente,
            p.genero AS generoPaciente,

            a.id AS idAcompanante,
            a.parentesco AS parentescoAcompanante,
            au.nombre AS nombreAcompanante,
            au.apellidoP AS apellidoPAcompanante,
            au.apellidoM AS apellidoMAcompanante,
            au.telefono AS telefonoAcompanante,
            au.genero AS generoAcompanante

        FROM ATENCION_MEDICA am
        JOIN USUARIO p ON p.id = am.idPaciente
        LEFT JOIN ACOMPAÑANTE a ON a.id = am.idAcompañante
        LEFT JOIN USUARIO au ON au.id = a.id
        ORDER BY am.fechaIngreso DESC, am.horaIngreso DESC;
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AtencionMedica atencionMedica = new AtencionMedica();

                atencionMedica.setId(rs.getLong("idAtencion"));
                atencionMedica.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                atencionMedica.setHoraIngreso(rs.getTime("horaIngreso").toLocalTime());
                atencionMedica.setFechaEgreso(rs.getDate("fechaEgreso") != null ? rs.getDate("fechaEgreso").toLocalDate() : null);
                atencionMedica.setHoraEgreso(rs.getTime("horaEgreso") != null ? rs.getTime("horaEgreso").toLocalTime() : null);
                atencionMedica.setEstado(rs.getString("estado"));

                Paciente paciente = new Paciente();
                paciente.setId(rs.getLong("idPaciente"));
                paciente.setNombre(rs.getString("nombrePaciente"));
                paciente.setApellidoPaterno(rs.getString("apellidoPPaciente"));
                paciente.setApellidoMaterno(rs.getString("apellidoMPaciente"));
                paciente.setTelefono(rs.getString("telefonoPaciente"));
                paciente.setGenero(rs.getString("generoPaciente"));

                Acompaniante acompaniante = new Acompaniante();
                acompaniante.setId(rs.getLong("idAcompanante"));
                acompaniante.setParentesco(rs.getString("parentescoAcompanante"));
                acompaniante.setNombre(rs.getString("nombreAcompanante"));
                acompaniante.setApellidoPaterno(rs.getString("apellidoPAcompanante"));
                acompaniante.setApellidoMaterno(rs.getString("apellidoMAcompanante"));
                acompaniante.setTelefono(rs.getString("telefonoAcompanante"));
                acompaniante.setGenero(rs.getString("generoAcompanante"));

                atencionMedica.setPaciente(paciente);
                atencionMedica.setAcompaniante(acompaniante);

                lista.add(atencionMedica);
            }
        }
        return lista;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM ATENCION_MEDICA WHERE idMedica = ?")) {
            stmt.setLong(1,id);
            stmt.execute();
        }
    }

    @Override
    public void establecerAlta(AtencionMedica atencionMedica) throws SQLException {
        String sql = """
            UPDATE ATENCION_MEDICA
            SET estado = 'Terminado',
                fechaEgreso = CURDATE(),
                horaEgreso = CURTIME()
            WHERE id = (?) AND estado = 'En curso';
            """;

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, atencionMedica.getId());
            stmt.execute();
        }
    }

    @Override
    public List<AtencionMedica> buscarPorNumeroTelPaciente(String numeroTelPaciente) throws SQLException {
        return listar().stream()
                .filter(atencionMedica ->
                        atencionMedica.getPaciente() != null &&
                                atencionMedica.getPaciente().getTelefono() != null &&
                                atencionMedica.getPaciente().getTelefono().equals(numeroTelPaciente))
                .toList();
    }

    @Override
    public List<AtencionMedica> buscarAtencionMedicaEnCurso() throws SQLException {
        return listar()
                .stream()
                .filter(atencionMedica -> atencionMedica.getEstado().equals("En Curso"))
                .toList();
    }

    @Override
    public Optional<AtencionMedica> buscarAtencionMedicaActual(String numeroTelPaciente) throws SQLException {
        return buscarPorNumeroTelPaciente(numeroTelPaciente)
                .stream()
                .filter(am ->am.getEstado().equals("En Curso"))
                .findFirst();
    }
}

