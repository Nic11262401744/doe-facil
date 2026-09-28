package br.com.doefacil.srv.colaboradores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuditLogFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startedAt = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String actor = authentication == null || !authentication.isAuthenticated()
                    ? "anonymous" : authentication.getName();
            String ongId = authentication instanceof JwtAuthenticationToken jwtAuthentication
                    ? jwtAuthentication.getToken().getClaimAsString("ongId") : "-";
            logger.info("AUDIT method={} path={} status={} actor={} ongId={} durationMs={}",
                    request.getMethod(), request.getRequestURI(), response.getStatus(), actor, ongId,
                    System.currentTimeMillis() - startedAt);
        }
    }
}