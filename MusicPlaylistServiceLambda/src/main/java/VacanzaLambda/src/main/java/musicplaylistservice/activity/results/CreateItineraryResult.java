package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;



import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;

public class CreateItineraryResult {
    private final ItineraryModel itineraryModel;

    private CreateItineraryResult(ItineraryModel itineraryModel) {
        this.itineraryModel = itineraryModel;
    }

    public ItineraryModel getItinerary() {
        return itineraryModel;
    }

    @Override
    public String toString() {
        return "CreatePlaylistResult{" +
                "playlist=" + itineraryModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ItineraryModel playlist;

        public Builder withItineraryModel(ItineraryModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public CreateItineraryResult build() {
            return new CreateItineraryResult(playlist);
        }
    }
}