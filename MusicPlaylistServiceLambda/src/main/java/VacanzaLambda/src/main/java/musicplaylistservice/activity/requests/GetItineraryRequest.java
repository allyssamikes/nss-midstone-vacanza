package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import com.nashss.se.musicplaylistservice.activity.requests.GetPlaylistRequest;

public class GetItineraryRequest {

    private final String email;
    private final String tripName;

    private GetItineraryRequest(String email, String tripName) {
        this.email = email;
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
        return "GetItineraryRequest{" +
                "email='" + email + '\'' +
                "tripName=" + tripName +  '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String tripName;


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTripName(String tripName) {
            this.tripName = tripName;
            return this;
        }

        public GetItineraryRequest build() {
            return new GetItineraryRequest(email, tripName);
        }
    }
}
