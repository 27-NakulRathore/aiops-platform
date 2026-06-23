package com.aiops.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiops.auth.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RBAC Testing APIs")
@RestController
public class TestController {

	@Operation(summary = "Admin Endpoint")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/v1/test/admin")
    public ResponseEntity<ApiResponse<String>> admin() {
    	return ResponseEntity.ok(
    	        ApiResponse.<String>builder()
    	                .success(true)
    	                .message("Access Granted")
    	                .data("Admin Resource")
    	                .build()
    	);
    }

	@Operation(summary = "Developer Endpoint")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
	@GetMapping("/api/v1/test/developer")
    public ResponseEntity<ApiResponse<String>> developer() {
    	return ResponseEntity.ok(
    	        ApiResponse.<String>builder()
    	                .success(true)
    	                .message("Access Granted")
    	                .data("Admin Resource")
    	                .build()
    	);
    }

	@Operation(summary = "Viewer Endpoint")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ADMIN','DEVELOPER','VIEWER')")
	@GetMapping("/api/v1/test/viewer")
    public ResponseEntity<ApiResponse<String>> viewer() {
    	return ResponseEntity.ok(
    	        ApiResponse.<String>builder()
    	                .success(true)
    	                .message("Access Granted")
    	                .data("Admin Resource")
    	                .build()
    	);
    }
}