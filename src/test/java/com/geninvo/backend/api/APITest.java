package com.geninvo.backend.api;

import com.geninvo.backend.modal.dto.AddressCreatedDto;
import com.geninvo.backend.modal.dto.AddressDto;
import com.geninvo.backend.modal.dto.SearchDto;
import com.geninvo.backend.modal.dto.ShopCreateDto;
import com.geninvo.backend.modal.dto.ShopDto;
import com.geninvo.backend.modal.entity.Shop;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;


@Rollback
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class APITest {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected HttpHeaders headers;

    @Test
    public void createShop() {
        this.createShopDetails("test",26.8783675,75.7596791);
    }

    private void createShopDetails(String shopName, double lat, double lng) {
        ShopCreateDto shopCreateDto = this.prepareShopDetails(shopName, lat, lng);

        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<ShopDto> exchange = restTemplate.exchange("/v1/api/shop", POST, new HttpEntity(shopCreateDto, headers), ShopDto.class);
        assertEquals(OK, exchange.getStatusCode());
        assertNotNull(exchange.getBody());
        ShopDto response = exchange.getBody();
        assertEquals(shopCreateDto.getName(), response.getShopName());
        assertEquals(shopCreateDto.getOwnerName(), response.getOwnerName());
        assertEquals(shopCreateDto.getCategory(), response.getCategory());
    }

    private ShopCreateDto prepareShopDetails(String name, double lat, double lng) {
        ShopCreateDto shopDto = new ShopCreateDto();
        shopDto.setName(name);
        shopDto.setCategory(Shop.Category.GENERALSTORE);
        shopDto.setOwnerName(RandomStringUtils.randomAlphabetic(10));
        shopDto.setAddress(this.prepareAddressDetails(lat, lng));
        return shopDto;
    }

    private AddressCreatedDto prepareAddressDetails( double lat, double lng) {
        AddressCreatedDto addressDto = new AddressCreatedDto();
        addressDto.setCity(RandomStringUtils.randomAlphabetic(5));
        addressDto.setState(RandomStringUtils.randomAlphabetic(7));
        addressDto.setLatitude(lat);
        addressDto.setLongitude(lng);
        return addressDto;
    }

    @Test
    public void getShopsByName() {
        this.createShopDetails("check shop",26.861668,75.7644593);
        this.createShopDetails("check shop",26.8783675,75.7596791);
        this.createShopDetails("demo shop",26.9252337,75.8010876);

        SearchDto searchDto = new SearchDto();
        searchDto.setName("ch");

        ResponseEntity<ListDto<ShopDto>> exchange = restTemplate.exchange("/v1/api/shop/search", POST, new HttpEntity(searchDto, headers), new ParameterizedTypeReference<ListDto<ShopDto>>() {
        });
        assertNotNull(exchange.getBody());

        assertEquals(
                2,
                exchange
                        .getBody()
                        .getContent()
                        .size());

        SearchDto locationDto = this.prepareShopSearchDto(26.8747617, 75.7590125);
        ResponseEntity<ListDto<ShopDto>> locationExchange = restTemplate.exchange("/v1/api/shop/search", POST, new HttpEntity(locationDto, headers), new ParameterizedTypeReference<ListDto<ShopDto>>() {});
        assertNotNull(locationExchange.getBody());
        assertEquals(
                2,
                locationExchange
                        .getBody()
                        .getContent()
                        .size());

        SearchDto shopSearchDto = this.prepareShopSearchDto(26.9196878, 75.78800509999999);
        ResponseEntity<ListDto<ShopDto>> locationExchange1 = restTemplate.exchange("/v1/api/shop/search", POST, new HttpEntity(shopSearchDto, headers), new ParameterizedTypeReference<ListDto<ShopDto>>() {});
        assertNotNull(locationExchange1.getBody());
        assertEquals(
                1,
                locationExchange1
                        .getBody()
                        .getContent()
                        .size());
    }

    private SearchDto prepareShopSearchDto(double lt, double lng)  {
        SearchDto locationDto = new SearchDto();
        locationDto.setLatitude(lt);
        locationDto.setLongitude(lng);
        return locationDto;
    }

}