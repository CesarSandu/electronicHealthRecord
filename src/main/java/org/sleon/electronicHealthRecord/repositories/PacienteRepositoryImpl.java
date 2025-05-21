package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Paciente;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PacienteRepositoryImpl implements UsuarioRepository<Paciente> {

    private Connection conn;

    public PacienteRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Paciente paciente) throws SQLException {
        String sql = "{CALL InsertarPaciente(?, ?, ?, ?, ?, ?, ?,?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, paciente.getTelefono());
            cstmt.setString(2, paciente.getNombre());
            cstmt.setString(3, paciente.getApellidoMaterno());
            cstmt.setString(4, paciente.getApellidoPaterno());
            cstmt.setString(5, paciente.getGenero());
            cstmt.setString(6, paciente.getMalestar());
            cstmt.setLong(7, paciente.getPersonalAdmision().getId());
            cstmt.registerOutParameter(8, Types.INTEGER);

            cstmt.executeUpdate();
            Long idPaciente = cstmt.getLong(8);
            paciente.setId(idPaciente);
        }
    }

    @Override
    public List<Paciente> listar() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();

        String sql = """
        SELECT 
            p.id AS paciente_id,
            u.telefono AS paciente_telefono,
            u.nombre AS paciente_nombre,
            u.apellidoP AS paciente_apellidoP,
            u.apellidoM AS paciente_apellidoM,
            u.genero AS paciente_genero,
            p.malestar
        FROM PACIENTE p
        JOIN USUARIO u ON p.id = u.id
    """;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getLong("paciente_id"));
                paciente.setTelefono(rs.getString("paciente_telefono"));
                paciente.setNombre(rs.getString("paciente_nombre"));
                paciente.setApellidoPaterno(rs.getString("paciente_apellidoP"));
                paciente.setApellidoMaterno(rs.getString("paciente_apellidoM"));
                paciente.setGenero(rs.getString("paciente_genero"));
                paciente.setMalestar(rs.getString("malestar"));

                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    @Override
    public List<Paciente> buscarPorNombre(String nombre) throws SQLException {
        return listar().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Paciente> buscarPorId(Long id) throws SQLException {
        return listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }


    @Override
    public void actualizar(Paciente paciente) throws SQLException {
        String sql = "{CALL ActualizarPaciente(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, paciente.getId());
            stmt.setString(2, paciente.getTelefono());
            stmt.setString(3, paciente.getNombre());
            stmt.setString(4, paciente.getApellidoMaterno());
            stmt.setString(5, paciente.getApellidoPaterno());
            stmt.setString(6, paciente.getGenero());
            stmt.setString(7, paciente.getMalestar());

            stmt.execute();
        }
    }
}
