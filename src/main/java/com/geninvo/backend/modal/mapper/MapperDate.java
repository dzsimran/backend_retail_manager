package com.geninvo.backend.modal.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperDate {
    public static LocalDateTime asLocalDateTime(Instant date){
        return date == null ? null : LocalDateTime.ofInstant(date, ZoneOffset.UTC);
    }

    public static Instant asInstant(LocalDateTime date){
        return date == null ? null : date.toInstant(ZoneOffset.UTC);
    }

}
