package VacanzaLambda.src.main.java.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static VacanzaLambda.src.main.java.musicplaylistservice.utils.CollectionUtils.copyToList;
@JsonDeserialize(builder = CreateItineraryRequest.Builder.class)
public class CreateItineraryRequest {
    private final String name;

    private final String email;
    private final List<String> tags;

    private CreateItineraryRequest(String name, String email, List<String> tags) {
        this.name = name;
        this.email = email;
        this.tags = tags;
    }
    public String getTripName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public List<String> getTags() {
        return copyToList(tags);
    }
    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "Itinerary name='" + name + '\'' +
                ", customerEmail='" + email + '\'' +
                ", tags=" + tags +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String email;
        private List<String> tags;

        public Builder withTripName(String name) {
            this.name = name;
            return this;
        }


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = copyToList(tags);
            return this;
        }

        public CreateItineraryRequest build() {
            return new CreateItineraryRequest(name, email, tags);
        }
    }
}