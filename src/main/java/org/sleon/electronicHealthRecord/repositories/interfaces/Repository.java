package org.sleon.electronicHealthRecord.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository <T>{
    void delete(Long id) throws SQLException;
    Optional<T> findById(Long id) throws SQLException;
    Optional<T> findByCredentials(String username, String password, String tipo) throws SQLException;
    List<T> listAll() throws SQLException;
}


