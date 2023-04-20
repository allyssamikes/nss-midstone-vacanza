package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddActivityToItineraryRequest.Builder.class)
public class AddActivityToItineraryRequest {
    private final String email;
    private final String tripName;
    private final String cityCountry;
    private final String name;

    public AddActivityToItineraryRequest(String email, String tripName, String cityCountry, String activityName) {
        this.email = email;
        this.tripName = tripName;
        this.cityCountry = cityCountry;
        this.name = activityName;
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


    public static Builder builder() {
        return new Builder();
    }


    @Override
    public String toString() {
        return "AddActivityToItineraryRequest{" +
                "email='" + email + '\'' +
                ", tripName='" + tripName + '\'' +
                ", cityCountry='" + cityCountry + '\'' +
                ", activityName='" + name + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String tripName;
        private String cityCountry;
        private String activityName;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withTripName(String tripName) {
            this.tripName = tripName;
            return this;
        }
        public Builder withCityCountry(String place) {
            this.cityCountry = place;
            return this;
        }
        public Builder withName(String name) {
            this.activityName = name;
            return this;
        }

        public AddActivityToItineraryRequest build() {
            return new AddActivityToItineraryRequest(email, tripName, cityCountry, activityName);
        }

    }


}
