package learn.example.database_migration.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import learn.example.database_migration.api.dto.BaseDTO;
import learn.example.database_migration.entity.BaseEntity;
import learn.example.database_migration.exception.ResourceNotFound;

public abstract class BaseService<T extends BaseEntity, ID, RESP extends BaseDTO<ID>, REQ extends BaseDTO<ID>> {
    protected JpaRepository<T, ID> repository; 

    public abstract T mapToEntity(REQ request);
    public abstract RESP mapToResponse(T entity);
    public abstract List<RESP> mapToResponse(List<T> response);

    public RESP findById(ID id) throws ResourceNotFound {
        T result = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Resource with id: " + id + " not found"));
        return mapToResponse(result);
    }

    public List<RESP> findAll(Pageable pageable) {
        return mapToResponse(repository.findAll(pageable).getContent());
    }

    public void save(REQ entity) {
        repository.save(mapToEntity(entity));
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }
}
