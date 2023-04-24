package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;

public class GetItineraryResult {
    private final ItineraryModel itinerary;

    private GetItineraryResult(ItineraryModel itinerary) {
        this.itinerary = itinerary;
    }
    public ItineraryModel getItinerary() {
        return itinerary;
    }
    @Override
    public String toString() {
        return "GetItineraryResult{" +
                "itinerary=" + itinerary +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private ItineraryModel itinerary;

        public Builder withItinerary(ItineraryModel itinerary) {
            this.itinerary = itinerary;
            return this;
        }

        public GetItineraryResult build() {
            return new GetItineraryResult(itinerary);
        }
    }
}
