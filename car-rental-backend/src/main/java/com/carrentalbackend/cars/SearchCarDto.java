package com.carrentalbackend.cars;

public class SearchCarDto {
    private String brand;
    private String type;
    private String transmission;
    private String color;

    public SearchCarDto() {
    }

    public String getBrand() {
        return this.brand;
    }

    public String getType() {
        return this.type;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public String getColor() {
        return this.color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SearchCarDto)) return false;
        final SearchCarDto other = (SearchCarDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$brand = this.getBrand();
        final Object other$brand = other.getBrand();
        if (this$brand == null ? other$brand != null : !this$brand.equals(other$brand)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$transmission = this.getTransmission();
        final Object other$transmission = other.getTransmission();
        if (this$transmission == null ? other$transmission != null : !this$transmission.equals(other$transmission))
            return false;
        final Object this$color = this.getColor();
        final Object other$color = other.getColor();
        if (this$color == null ? other$color != null : !this$color.equals(other$color)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SearchCarDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $brand = this.getBrand();
        result = result * PRIME + ($brand == null ? 43 : $brand.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $transmission = this.getTransmission();
        result = result * PRIME + ($transmission == null ? 43 : $transmission.hashCode());
        final Object $color = this.getColor();
        result = result * PRIME + ($color == null ? 43 : $color.hashCode());
        return result;
    }

    public String toString() {
        return "SearchCarDto(brand=" + this.getBrand() + ", type=" + this.getType() + ", transmission=" + this.getTransmission() + ", color=" + this.getColor() + ")";
    }
}
