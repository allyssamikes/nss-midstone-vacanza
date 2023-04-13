package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

public class SearchItinerariesRequest {
    private final String criteria;

    private SearchItinerariesRequest(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchItinerariesRequest{" +
                "criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchItinerariesRequest.Builder builder() {
        return new SearchItinerariesRequest.Builder();
    }

    public static class Builder {
        private String criteria;

        public SearchItinerariesRequest.Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchItinerariesRequest build() {
            return new SearchItinerariesRequest(criteria);
        }
    }
}
