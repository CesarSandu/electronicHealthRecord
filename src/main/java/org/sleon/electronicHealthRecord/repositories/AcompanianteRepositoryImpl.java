package org.sleon.electronicHealthRecord.repositories;

import org.sleon.electronicHealthRecord.models.Acompaniante;
import org.sleon.electronicHealthRecord.repositories.interfaces.UsuarioRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class AcompanianteRepositoryImpl implements UsuarioRepository<Acompaniante> {

    private Connection conn;

    public AcompanianteRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Acompaniante acompaniante) throws SQLException {
        String sql = "{CALL InsertarAcompañante(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, acompaniante.getTelefono());
            cstmt.setString(2, acompaniante.getNombre());
            cstmt.setString(3, acompaniante.getApellidoMaterno());
            cstmt.setString(4, acompaniante.getApellidoPaterno());
            cstmt.setString(5, acompaniante.getGenero());
            cstmt.setString(6, acompaniante.getParentesco());
            cstmt.setLong(7, acompaniante.getResponsableRegistro().getId());
            cstmt.registerOutParameter(8, Types.INTEGER);
            cstmt.executeUpdate();

            Long idAcomp = cstmt.getLong(8);
            acompaniante.setId(idAcomp);

        }
    }

    @Override
    public List<Acompaniante> listar() throws SQLException {
        return List.of();
    }

    @Override
    public List<Acompaniante> buscarPorNombre(String nombre) throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Acompaniante> buscarPorId(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void actualizar(Acompaniante acompaniante) throws SQLException {

    }
}
