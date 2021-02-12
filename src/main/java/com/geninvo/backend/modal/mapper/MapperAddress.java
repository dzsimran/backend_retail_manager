package com.geninvo.backend.modal.mapper;

import com.geninvo.backend.modal.dto.AddressCreatedDto;
import com.geninvo.backend.modal.dto.AddressDto;
import com.geninvo.backend.modal.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(uses = {MapperDate.class}, componentModel = "spring", unmappedTargetPolicy = ERROR, nullValueMappingStrategy = RETURN_DEFAULT)
public interface MapperAddress {
    @Mappings({@Mapping(target = "id", ignore = true),
              @Mapping(target = "dateCreated", ignore = true),
              @Mapping(target = "dateUpdated", ignore = true)})
    Address toAddress(AddressCreatedDto dto);

    @Mappings({})
    AddressDto fromAddress(Address address);

}
