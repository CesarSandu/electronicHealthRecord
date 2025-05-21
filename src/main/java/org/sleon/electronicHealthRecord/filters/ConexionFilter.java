package org.sleon.electronicHealthRecord.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.sleon.electronicHealthRecord.services.ServiceJdbcException;
import org.sleon.electronicHealthRecord.util.ConexionBD;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.Filter;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection conn = ConexionBD.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}



