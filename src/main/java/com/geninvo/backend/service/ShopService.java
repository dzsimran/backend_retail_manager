package com.geninvo.backend.service;

import com.geninvo.backend.helper.PageRequestBuilder;
import com.geninvo.backend.modal.dto.SearchDto;
import com.geninvo.backend.modal.entity.Address;
import com.geninvo.backend.modal.entity.Shop;
import com.geninvo.backend.repository.AddressRepository;
import com.geninvo.backend.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class ShopService {

    @Value("${app.nearest.location.radius}")
    private Integer distance;

    private ShopRepository shopRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ShopService(final ShopRepository shopRepository, final AddressRepository addressRepository) {
        this.shopRepository = shopRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Method used to create new Shop
     *
     * @param entity : Shop
     *
     * @return : Shop
     */
    @Transactional
    public Shop create(Shop entity) {
        Address save = addressRepository.save(entity.getAddress());
        entity.setAddress(save);
        return shopRepository.save(entity);
    }

    /**
     * Method used to search shops according to shop name and coordinates
     *
     * @param dto      : dto
     * @param pageNumber :  pageNumber
     * @param pageSize : pageSize
     *
     * @return :  Page<Shop> page of shops
     */
    @Transactional
    public Page<Shop> search(SearchDto dto, Integer pageNumber, Integer pageSize) {
        if (isNotBlank(dto.getName())) {
            return shopRepository.findShopsByNameORLocation(dto.getName(), null, null, distance, PageRequestBuilder.getPageRequest(pageSize, pageNumber, "-date_created"));
        } else if (isBlank(dto.getName()) && dto.getLatitude() > 0 && dto.getLongitude() > 0) {
            return shopRepository.findShopsByNameORLocation(null, dto.getLatitude(), dto.getLongitude(), distance, PageRequestBuilder.getPageRequest(pageSize, pageNumber, "-date_created"));
        } else {
            return shopRepository.findAll(PageRequestBuilder.getPageRequest(pageSize, pageNumber, "-dateCreated"));
        }
    }

}
