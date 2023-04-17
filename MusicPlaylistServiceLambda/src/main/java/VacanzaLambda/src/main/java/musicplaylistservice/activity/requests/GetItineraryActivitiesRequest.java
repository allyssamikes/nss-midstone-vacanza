package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

public class GetItineraryActivitiesRequest {
    private final String email;
    private final String tripName;

    private GetItineraryActivitiesRequest(String cityCountry, String tripName) {
        this.email = cityCountry;
        this.tripName = tripName;
    }

    public String getEmail() {
        return email;
    }

    public String getTripName() {
        return tripName;
    }

    @Override
    public String toString() {
        return "GetItineraryActivitiesRequest{" +
                "email='" + email + '\'' +
                ", tripName='" + tripName + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String tripName;

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withTripName(String tripName){
            this.tripName = tripName;
            return this;
        }

        public GetItineraryActivitiesRequest build(){
            return new GetItineraryActivitiesRequest(email, tripName);
        }
    }
}
