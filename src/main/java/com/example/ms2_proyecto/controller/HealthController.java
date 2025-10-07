package com.example.ms2_proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ms2/api/health")
@Tag(name = "Health", description = "API para verificación del estado del servicio")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping
    @Operation(summary = "Verificar el estado del servicio")
    public ResponseEntity<Map<String, Object>> checkHealth() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("message", "El servicio está funcionando correctamente");
        status.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
