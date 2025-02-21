package com.carrentalbackend.cars;

import org.springframework.web.multipart.MultipartFile;


public class CarDto {
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Integer year;
    private MultipartFile image;
    private byte[] returnedImage;

    public CarDto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getPrice() {
        return this.price;
    }

    public Integer getYear() {
        return this.year;
    }

    public MultipartFile getImage() {
        return this.image;
    }

    public byte[] getReturnedImage() {
        return this.returnedImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setReturnedImage(byte[] returnedImage) {
        this.returnedImage = returnedImage;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CarDto)) return false;
        final CarDto other = (CarDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$brand = this.getBrand();
        final Object other$brand = other.getBrand();
        if (this$brand == null ? other$brand != null : !this$brand.equals(other$brand)) return false;
        final Object this$color = this.getColor();
        final Object other$color = other.getColor();
        if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$transmission = this.getTransmission();
        final Object other$transmission = other.getTransmission();
        if (this$transmission == null ? other$transmission != null : !this$transmission.equals(other$transmission))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$year = this.getYear();
        final Object other$year = other.getYear();
        if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        if (!java.util.Arrays.equals(this.getReturnedImage(), other.getReturnedImage())) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CarDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $brand = this.getBrand();
        result = result * PRIME + ($brand == null ? 43 : $brand.hashCode());
        final Object $color = this.getColor();
        result = result * PRIME + ($color == null ? 43 : $color.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $transmission = this.getTransmission();
        result = result * PRIME + ($transmission == null ? 43 : $transmission.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $year = this.getYear();
        result = result * PRIME + ($year == null ? 43 : $year.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        result = result * PRIME + java.util.Arrays.hashCode(this.getReturnedImage());
        return result;
    }

    public String toString() {
        return "CarDto(id=" + this.getId() + ", brand=" + this.getBrand() + ", color=" + this.getColor() + ", name=" + this.getName() + ", type=" + this.getType() + ", transmission=" + this.getTransmission() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", year=" + this.getYear() + ", image=" + this.getImage() + ", returnedImage=" + java.util.Arrays.toString(this.getReturnedImage()) + ")";
    }
}
