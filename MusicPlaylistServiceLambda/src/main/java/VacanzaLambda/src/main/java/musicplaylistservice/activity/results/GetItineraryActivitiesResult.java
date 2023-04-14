package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;

import java.util.ArrayList;
import java.util.List;

public class GetItineraryActivitiesResult {
    private final List<Activity> activityList;

    private GetItineraryActivitiesResult(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<Activity> getActivityList() {
        return new ArrayList<>(activityList);
    }

    @Override
    public String toString() {
        return "GetItineraryActivitiesResult{" +
                "activityList=" + activityList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Activity> activityList;

        public Builder withActivityList(List<Activity> activityList) {
            this.activityList = activityList;
            return this;
        }

        public GetItineraryActivitiesResult build() {
            return new GetItineraryActivitiesResult(activityList);
        }
    }
}