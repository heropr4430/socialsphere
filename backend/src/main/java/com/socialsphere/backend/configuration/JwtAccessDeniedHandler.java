package com.socialsphere.backend.configuration;

import com.socialsphere.backend.dtos.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public JwtAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exception
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<Object> res = new ApiResponse<>();
        res.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
        res.setError("FORBIDDEN");
        res.setMessage("Bạn không có quyền truy cập tài nguyên này");

        mapper.writeValue(response.getWriter(), res);
    }
}
