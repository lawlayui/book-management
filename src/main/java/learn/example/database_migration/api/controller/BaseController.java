package learn.example.database_migration.api.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.Max;
import learn.example.database_migration.api.dto.request.BaseRequestDTO;
import learn.example.database_migration.api.dto.response.BaseResponseDTO;
import learn.example.database_migration.entity.BaseEntity;
import learn.example.database_migration.exception.ResourceNotFound;
import learn.example.database_migration.service.BaseService;

public abstract class BaseController<T extends BaseEntity<ID>, ID, RESP extends BaseResponseDTO<ID>, REQ extends BaseRequestDTO> {
    protected BaseService<T, ID, RESP, REQ> service;

    @GetMapping("/")
    public ResponseEntity<List<RESP>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") @Max(43) int size) {
        List<RESP> responses = service.findAll(PageRequest.of(page, size));
        return ResponseEntity
            .ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESP> getById(@PathVariable ID id) throws ResourceNotFound{
        RESP resp = service.findById(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    public ResponseEntity<RESP> createEntity(@RequestBody REQ request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.save(request));
    }
}
