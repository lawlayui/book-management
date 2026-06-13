package com.lawlayui.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lawlayui.library.api.dto.request.BaseRequestDTO;
import com.lawlayui.library.api.dto.response.BaseResponseDTO;
import com.lawlayui.library.entity.BaseEntity;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.util.mapper.GenericMapper;

import jakarta.transaction.Transactional;

public abstract class BaseService<T extends BaseEntity<ID>, ID, RESP extends BaseResponseDTO<ID>, REQ extends BaseRequestDTO, UREQ extends BaseRequestDTO> {
    protected JpaRepository<T, ID> repository; 
    protected GenericMapper<ID, T, REQ, UREQ, RESP> mapper;

    @Transactional
    public RESP findById(ID id) throws ResourceNotFound {
        return mapper.entityToResponse(repository.findById(id).orElseThrow(() -> new ResourceNotFound("Resource with id: " + id + " not found")));
    }

    @Transactional
    public List<RESP> findAll(Pageable pageable) {
        return mapper.entityToResponses(repository.findAll(pageable).getContent());
    }

    @Transactional
    public RESP save(REQ entity) {
        return mapper.entityToResponse(repository.save(mapper.requestToEntity(entity)));
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }

    public abstract RESP update(ID id, UREQ request) throws ResourceNotFound;
}
