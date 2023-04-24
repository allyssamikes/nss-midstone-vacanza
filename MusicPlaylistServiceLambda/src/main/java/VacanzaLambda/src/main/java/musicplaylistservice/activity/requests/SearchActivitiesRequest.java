package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = SearchActivitiesRequest.Builder.class)
public class SearchActivitiesRequest {

    private String cityCountry;

    public SearchActivitiesRequest(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getCityCountry() {
        return cityCountry;
    }
    @Override
    public String toString() {
        return "SearchActivitiesRequest{" +
                "cityCountry='" + cityCountry + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {

        public String cityCountry;

        public Builder withCityCountry(String cityCountry) {
             this.cityCountry = cityCountry;
             return this;
         }
        public SearchActivitiesRequest build() {
            System.out.println(cityCountry+"searchActivitiesRequest");

            return new SearchActivitiesRequest(cityCountry);
        }
    }
}
