package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Administrador;
import org.sleon.electronicHealthRecord.models.GrupoMedico;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoMedicoRepositoryImpl implements UsuarioRepository<GrupoMedico> {

    private Connection conn;

    public GrupoMedicoRepositoryImpl(Connection connection) {
        this.conn = connection;
    }

    public void crear(GrupoMedico grupoMedico) throws SQLException {
        CallableStatement stmt = null;

        try {
            String sql = "{CALL RegistrarGrupoMedico(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            stmt = conn.prepareCall(sql);

            setParam(grupoMedico, stmt);
            stmt.setString(6, grupoMedico.getIdUsuario());
            stmt.setString(7,grupoMedico.getContrasenia());
            stmt.setString(8,grupoMedico.getEspecialidad());
            stmt.setLong(9,grupoMedico.getResponsableRegistro().getId());
            stmt.execute();

        } finally {
            if (stmt != null) stmt.close();
        }
    }

    @Override
    public List<GrupoMedico> listar() throws SQLException {
        List<GrupoMedico> grupoMedicos = new ArrayList<>();

        String sql = """
            SELECT 
                u.id AS id_usuario,
                u.nombre,
                u.apellidoP,
                u.apellidoM,
                u.genero,
                u.telefono,
                u.tipo AS tipo_usuario,
                
                gm.id AS id_grupo_medico,
                gm.especialidad,
                gm.fechaRegistro AS fecha_registro_grupo,
                
                uAdmin.id AS id_admin,
                uAdmin.nombre AS nombre_admin,
                uAdmin.apellidoP AS apellidoP_admin,
                uAdmin.apellidoM AS apellidoM_admin,
                uAdmin.telefono AS telefono_admin
                
            FROM USUARIO u
            JOIN PERSONAL_HOSPITAL ph ON u.id = ph.id
            JOIN GRUPO_MEDICO gm ON ph.id = gm.id
            JOIN ADMINISTRADOR admin ON gm.responsableRegistro = admin.id
            JOIN USUARIO uAdmin ON admin.id = uAdmin.id
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                GrupoMedico grupoMedico = new GrupoMedico();
                grupoMedico.setId(rs.getLong("id_usuario"));
                grupoMedico.setNombre(rs.getString("nombre"));
                grupoMedico.setApellidoPaterno(rs.getString("apellidoP"));
                grupoMedico.setApellidoMaterno(rs.getString("apellidoM"));
                grupoMedico.setGenero(rs.getString("genero"));
                grupoMedico.setTelefono(rs.getString("telefono"));
                grupoMedico.setTipo(rs.getString("tipo_usuario"));
                grupoMedico.setEspecialidad(rs.getString("especialidad"));
                grupoMedico.setFechaRegistro(rs.getDate("fecha_registro_grupo"));

                Administrador administrador = new Administrador();
                administrador.setId(rs.getLong("id_admin"));
                administrador.setNombre(rs.getString("nombre_admin"));
                administrador.setApellidoPaterno(rs.getString("apellidoP_admin"));
                administrador.setApellidoMaterno(rs.getString("apellidoM_admin"));
                administrador.setTelefono(rs.getString("telefono_admin"));

                grupoMedico.setResponsableRegistro(administrador);

                grupoMedicos.add(grupoMedico);
            }
        }
        return grupoMedicos;
    }

    @Override
    public List<GrupoMedico> buscarPorNombre(String nombre) throws SQLException {
        return listar().stream()
                .filter(g -> g.getNombre().equalsIgnoreCase(nombre))
                .toList();
    }

    @Override
    public Optional<GrupoMedico> buscarPorId(Long id) throws SQLException {
        return listar().stream()
                .filter(g -> g.getId() == id)
                .findFirst();
    }

    @Override
    public void actualizar(GrupoMedico grupoMedico) throws SQLException {
        String sql = "{CALL ActualizarGrupoMedico(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            setParam(grupoMedico, stmt);
            stmt.setString(6, grupoMedico.getEspecialidad());
            stmt.setLong(7, grupoMedico.getId());
            stmt.execute();
        }
    }

    private static void setParam(GrupoMedico grupoMedico, CallableStatement stmt) throws SQLException {
        stmt.setString(1, grupoMedico.getTelefono());
        stmt.setString(2, grupoMedico.getNombre());
        stmt.setString(3, grupoMedico.getApellidoMaterno());
        stmt.setString(4, grupoMedico.getApellidoPaterno());
        stmt.setString(5, grupoMedico.getGenero());
    }
}
