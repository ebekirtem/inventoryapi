package com.inventory.dto.depot;

import com.inventory.dto.ApiRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateDepotDto extends ApiRequestDto {

    @NotBlank(message = "Please Provide a Name")
    @Size(min=1, max=100,message = "Name must be 1 and 100 char long")
    private String name;

    @NotBlank(message = "Please Provide an Address")
    @Size(min=1, max=255,message = "Address must be 1 and 255 char long")
        private String address;

    @NotBlank(message = "Please Provide a Region")
    @Size(min=1, max=100,message = "Region must be 1 and 100 char long")
    private String region;

    @NotBlank(message = "Please Provide a City")
    @Size(min=1, max=100,message = "City must be 1 and 100 char long")
        private String city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

}

