package com.geninvo.backend.modal.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.geninvo.backend.modal.entity.Shop;

public class ShopCreateDto {

    @NotBlank
    private String name;
    @NotNull
    private Shop.Category category;
    @NotBlank
    private String ownerName;
    @NotNull
    @Valid
    private AddressCreatedDto address;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Shop.Category getCategory() {
        return category;
    }

    public void setCategory(final Shop.Category category) {
        this.category = category;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    public AddressCreatedDto getAddress() {
        return address;
    }

    public void setAddress(final AddressCreatedDto address) {
        this.address = address;
    }

}
