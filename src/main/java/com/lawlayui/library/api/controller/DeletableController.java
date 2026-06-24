package com.lawlayui.library.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeletableController<ID> {
    @DeleteMapping("/{id}")
    default ResponseEntity<Void> delete(@PathVariable ID id) throws Exception{
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
