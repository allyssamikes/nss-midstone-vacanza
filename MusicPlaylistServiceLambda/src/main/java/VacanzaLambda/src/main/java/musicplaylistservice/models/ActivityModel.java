package VacanzaLambda.src.main.java.musicplaylistservice.models;


import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY;

import java.util.Objects;

public class ActivityModel {
    private String name;
    private String cityCountry;
    private String  address;
    private TYPE_OF_ACTIVITY type;
    private Boolean kidFriendly;
    private Boolean weatherPermitting;

    public ActivityModel(String name, String cityCountry, String address, TYPE_OF_ACTIVITY type, Boolean kidFriendly, Boolean weatherPermitting) {
        this.name = name;
        this.cityCountry = cityCountry;
        this.address = address;
        this.type = type;
        this.kidFriendly = kidFriendly;
        this.weatherPermitting = weatherPermitting;
    }


    public String getName() {
        return name;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public String getAddress() {
        return address;
    }

    public TYPE_OF_ACTIVITY getTYPE_OF_ACTIVITY() {
        return type;
    }

    public Boolean getKidFriendly() {
        return kidFriendly;
    }

    public Boolean getWeatherPermitting() {
        return weatherPermitting;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityModel that = (ActivityModel) o;
        return Objects.equals(name, that.name) && Objects.equals(cityCountry, that.cityCountry) && Objects.equals(address, that.address) && Objects.equals(type, that.type) && Objects.equals(kidFriendly, that.kidFriendly) && Objects.equals(weatherPermitting, that.weatherPermitting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityCountry, address, type, kidFriendly, weatherPermitting);
    }

    public static class Builder {
        private String name;
        private String cityCountry;
        private String address;
        private TYPE_OF_ACTIVITY type;
        private Boolean kidFriendly;
        private Boolean weatherPermitting;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCityCountry(String cityCountry) {

            this.cityCountry = cityCountry;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withTypeOfActivity(TYPE_OF_ACTIVITY type) {
            this.type = type;
            return this;
        }

        public Builder withKidFriendly(Boolean kidFriendly) {
            this.kidFriendly = kidFriendly;
            return this;
        }

        public Builder withWeatherPermitting(Boolean weatherPermitting) {
            this.weatherPermitting = weatherPermitting;
            return this;
        }

        public ActivityModel build() {
            return new ActivityModel(name, cityCountry, address, type, kidFriendly, weatherPermitting);
        }
    }}
