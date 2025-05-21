package org.sleon.electronicHealthRecord.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sleon.electronicHealthRecord.models.Usuario;

import java.io.IOException;

@WebFilter({"/grupoMedico/*","/Admision/*","/administrador/*","/Acompanante/*"})
public class AutorizacionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        if (path.equals("/login.jsp") || path.equals("/login") || path.equals("/logout") ||
                path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/img/") || path.startsWith("/webjars/")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (session != null && session.getAttribute("usuarioLog") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLog");
            String tipoUsuario = usuario.getTipo();

            session.setAttribute("tipoUsuario",tipoUsuario);

            if (path.startsWith("/" + tipoUsuario)) {
                filterChain.doFilter(request, response);
            } else {
                session.invalidate();
                resp.sendRedirect(req.getContextPath() + "/accesoDenegado.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/accesoDenegado.jsp");
        }
    }
}