package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;
import java.util.ArrayList;
import java.util.List;

public class SearchItinerariesResult {
    private final List<ItineraryModel> itineraries;

    private SearchItinerariesResult(List<ItineraryModel> itineraries) {
        this.itineraries = itineraries;
    }

    public List<ItineraryModel> getItineraries() {
        return itineraries;
    }

    @Override
    public String toString() {
        return "SearchItinerariesResult{" +
                "itineraries=" + itineraries +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchItinerariesResult.Builder builder() {
        return new SearchItinerariesResult.Builder();
    }

    public static class Builder {
        private List<ItineraryModel> itineraries ;

        public SearchItinerariesResult.Builder withItineraries(List<ItineraryModel> itineraries) {
            this.itineraries = new ArrayList<>(itineraries);
            return this;
        }

        public SearchItinerariesResult build() {
            return new SearchItinerariesResult(itineraries);
        }
    }
}
