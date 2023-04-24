package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.TYPE_OF_ACTIVITY;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateActivityRequest.Builder.class)
public class CreateActivityRequest {
    private final String name;
    private final String cityCountry;
    private final String address;
    private final TYPE_OF_ACTIVITY type;
    private final String kidFriendly;
    private final String weatherPermitting;

    private CreateActivityRequest(String name, String cityCountry, String address, TYPE_OF_ACTIVITY type, String kidFriendly, String weatherPermitting) {
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

    public TYPE_OF_ACTIVITY getType() {
        return type;
    }
    public String getKidFriendly() { return kidFriendly; }
    public String getWeatherPermitting() { return weatherPermitting; }

    @Override
    public String toString() {
        return "CreateActivityRequest{" +
                "name='" + name + '\'' +
                ", cityCountry='" + cityCountry + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type +
                ", kidFriendly='" + kidFriendly + '\'' +
                ", weatherPermitting='" + weatherPermitting +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private  String cityCountry;
        private  String address;
        private  TYPE_OF_ACTIVITY type;
        private  String kidFriendly;
        private  String weatherPermitting;

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

        public Builder withType(TYPE_OF_ACTIVITY type) {
            this.type = type;
            return this;
        }

        public Builder withKidFriendly(String kidFriendly) {
            this.kidFriendly = kidFriendly;
            return this;
        }

        public Builder withWeatherPermitting(String weatherPermitting) {
            this.weatherPermitting = weatherPermitting;
            return this;
        }

        public CreateActivityRequest build() {
            return new CreateActivityRequest(name, cityCountry, address, type, kidFriendly, weatherPermitting);
        }
    }
}