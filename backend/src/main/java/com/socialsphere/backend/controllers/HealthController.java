package com.socialsphere.backend.controllers;

import com.socialsphere.backend.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ApiResponse<String> health() {
        return ApiResponse.success("Backend is running");
    }

}
