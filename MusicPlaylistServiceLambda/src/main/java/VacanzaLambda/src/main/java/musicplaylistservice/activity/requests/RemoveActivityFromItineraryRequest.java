package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = RemoveActivityFromItineraryRequest.Builder.class)
public class RemoveActivityFromItineraryRequest {
    private final String email;
    private final String tripName;
    private final String cityCountry;
    private final String name;

    public RemoveActivityFromItineraryRequest(String email, String tripName, String cityCountry, String name) {
        this.email = email;
        this.tripName = tripName;
        this.cityCountry = cityCountry;
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public String getTripName() {
        return tripName;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RemoveActivityFromItineraryRequest{" +
                "email='" + email + '\'' +
                ", tripName='" + tripName + '\'' +
                ", cityCountry='" + cityCountry + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
        public static class Builder {
            private String email;
            private String tripName;
            private String cityCountry;
            private String name;

            public Builder withEmail(String email) {
            this.email = email;
                return this;
            }
            public Builder withTripName(String tripName) {
                this.tripName = tripName;
                return this;
            }
            public Builder withCityCountry(String cityCountry) {
                this.cityCountry = cityCountry;
                return this;
            }
            public Builder withName(String name) {
                this.name = name;
                return this;
            }

            public RemoveActivityFromItineraryRequest build() {
                return new RemoveActivityFromItineraryRequest(email, tripName, cityCountry, name);
            }
        }
}
