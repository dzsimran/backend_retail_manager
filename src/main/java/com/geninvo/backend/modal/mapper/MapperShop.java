package com.geninvo.backend.modal.mapper;

import com.geninvo.backend.modal.dto.ShopCreateDto;
import com.geninvo.backend.modal.dto.ShopDto;
import com.geninvo.backend.modal.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(uses = {MapperAddress.class}, componentModel = "spring", unmappedTargetPolicy = ERROR, nullValueMappingStrategy = RETURN_DEFAULT)
public interface MapperShop {

    @Mappings({@Mapping(target = "id", ignore = true),
               @Mapping(target = "dateCreated", ignore = true),
               @Mapping(target = "dateUpdated", ignore = true)})
    Shop toShop(ShopCreateDto dto);

    @Mappings({
                @Mapping(target = "shopName", source = "name")
    })
    ShopDto fromShop(Shop shop);
}
