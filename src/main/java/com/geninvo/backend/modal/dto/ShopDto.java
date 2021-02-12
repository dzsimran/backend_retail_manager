package com.geninvo.backend.modal.dto;

import java.io.Serializable;
import java.util.Date;
import com.geninvo.backend.modal.entity.Shop;

public class ShopDto implements Serializable {

    private Long id;
    private String shopName;
    private String ownerName;
    private Shop.Category category;
    private AddressDto address;
    private Date dateUpdated;
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(final String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    public Shop.Category getCategory() {
        return category;
    }

    public void setCategory(final Shop.Category category) {
        this.category = category;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(final AddressDto address) {
        this.address = address;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(final Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
