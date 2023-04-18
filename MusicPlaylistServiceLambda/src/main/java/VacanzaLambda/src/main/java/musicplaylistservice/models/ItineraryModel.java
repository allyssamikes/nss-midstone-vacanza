package VacanzaLambda.src.main.java.musicplaylistservice.models;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;

import java.util.List;
import java.util.Objects;

public class ItineraryModel {
    private String tripName;
    private String email;
    private List<String> cities;

    private List<Activity> activities;

    private List<String> users;
    private List<String> tags;

    public ItineraryModel(String tripName, String email, List<String> cities, List<Activity> activities,
                          List<String> users, List<String> tags) {
        this.tripName = tripName;
        this.email = email;
        this.cities = cities;
        this.activities = activities;
        this.users = users;
        this.tags = tags;
    }


    public String getTripName() {
        return tripName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    public List<String> getTags() {
        return tags;
    }


    public List<String> getUsers() {
        return users;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItineraryModel that = (ItineraryModel) o;
        return Objects.equals(tripName, that.tripName) && Objects.equals(email, that.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripName, email);
    }

    public static class Builder {
        private String tripName;
        private String email;
        private List<String> cities;

        private List<Activity> activities;

        private List<String> users;
        private List<String> tags;

        public Builder withTripName(String tripName) {
            this.tripName = tripName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withCities(List<String> cities) {
            this.cities = cities;
            return this;
        }

        public Builder withActivities(List<Activity> activities) {
            this.activities = activities;
            return this;
        }

        public Builder withUsers(List<String> users) {
            this.users = users;
            return this;
        }
        public Builder withTags(List<String> tags){
            this.tags = tags;
            return this;
        }

        public ItineraryModel build() {
            return new ItineraryModel(tripName, email, cities, activities, users, tags);
        }
    }
}

