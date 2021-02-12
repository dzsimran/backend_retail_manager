package com.geninvo.backend.controller;

import javax.validation.Valid;
import com.geninvo.backend.modal.dto.SearchDto;
import com.geninvo.backend.modal.dto.ShopCreateDto;
import com.geninvo.backend.modal.dto.ShopDto;
import com.geninvo.backend.modal.mapper.MapperShop;
import com.geninvo.backend.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api
@RestController
@RequestMapping("/v1/api/shop")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private MapperShop shopMapper;

    @PostMapping(value = {
            ""
    }, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopDto> create(@Valid @RequestBody ShopCreateDto dto) {
        return ResponseEntity
                .ok()
                .body(shopMapper.fromShop(shopService.create(shopMapper.toShop(dto))));
    }

    @PostMapping(value = {
            "/search"
    }, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ShopDto>> search(
            @RequestBody SearchDto dto,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {

        return ResponseEntity
                .ok()
                .body(shopService
                        .search(dto, pageNumber, pageSize)
                        .map(shop -> shopMapper.fromShop(shop)));
    }

}
