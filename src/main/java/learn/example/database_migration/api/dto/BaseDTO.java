package learn.example.database_migration.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BaseDTO<ID> {
    protected ID id;
    protected Instant timestamp;
    protected LocalDateTime createdAt;
    protected LocalDateTime updateAt; 
}
