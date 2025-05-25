package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Administrador;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdministradorRepositoryImpl implements UsuarioRepository<Administrador> {

    private Connection conn;

    public AdministradorRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Administrador administrador) throws SQLException {
        String sql = "{CALL RegistrarAdministrador(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, administrador.getTelefono());
            stmt.setString(2, administrador.getNombre());
            stmt.setString(3, administrador.getApellidoMaterno());
            stmt.setString(4, administrador.getApellidoPaterno());
            stmt.setString(5, administrador.getGenero());
            stmt.setString(6, administrador.getIdUsuario());
            stmt.setString(7, administrador.getContrasenia());
            stmt.setString(8, administrador.getTipo());

            stmt.execute();
        }
    }


    @Override
    public List<Administrador> listar() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();

        String sql = """
    SELECT
        u.id AS id_admin,
        u.nombre,
        u.apellidoP,
        u.apellidoM,
        u.telefono,
        u.genero,
        ph.tipo,
        a.fechaRegistro,

        uResp.nombre AS nombre_responsable,
        uResp.apellidoP AS apellidoP_responsable,
        uResp.apellidoM AS apellidoM_responsable,
        uResp.telefono AS telefono_responsable

    FROM ADMINISTRADOR a
    JOIN PERSONAL_HOSPITAL ph ON a.id = ph.id
    JOIN USUARIO u ON ph.id = u.id

    LEFT JOIN ADMINISTRADOR aResp ON a.responsableRegistro = aResp.id
    LEFT JOIN USUARIO uResp ON aResp.id = uResp.id

    WHERE a.id <> 12;
""";
;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getLong("id_admin"));
                admin.setTelefono(rs.getString("telefono"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApellidoPaterno(rs.getString("apellidoP"));
                admin.setApellidoMaterno(rs.getString("apellidoM"));
                admin.setGenero(rs.getString("genero"));
                admin.setTipo(rs.getString("tipo"));
                admin.setFechaRegistro(rs.getDate("fechaRegistro"));

                Administrador responsable = new Administrador();
                responsable.setNombre(rs.getString("nombre_responsable"));
                responsable.setApellidoPaterno(rs.getString("apellidoP_responsable"));
                responsable.setApellidoMaterno(rs.getString("apellidoM_responsable"));
                responsable.setTelefono(rs.getString("telefono_responsable"));

                admin.setResponsableRegistro(responsable);

                administradores.add(admin);
            }
        }

        return administradores;
    }


    @Override
    public List<Administrador> buscarPorNombre(String nombre) throws SQLException {
        return listar().stream()
                .filter(a -> a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }


    @Override
    public Optional<Administrador> buscarPorId(Long id) throws SQLException {
        return listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public void actualizar(Administrador administrador) throws SQLException {
        String sql = "{CALL ActualizarPersonalHospital(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, administrador.getTelefono());
            stmt.setString(2, administrador.getNombre());
            stmt.setString(3, administrador.getApellidoMaterno());
            stmt.setString(4, administrador.getApellidoPaterno());
            stmt.setString(5, administrador.getGenero());
            stmt.setLong(6, administrador.getId());

            stmt.executeUpdate();
        }
    }

}
