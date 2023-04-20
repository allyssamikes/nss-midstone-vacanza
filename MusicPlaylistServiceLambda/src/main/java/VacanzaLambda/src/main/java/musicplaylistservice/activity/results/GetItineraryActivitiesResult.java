package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;

import java.util.ArrayList;
import java.util.List;

public class GetItineraryActivitiesResult {
    private final List<ActivityModel> activityList;

    private GetItineraryActivitiesResult(List<ActivityModel> activityList) {
        this.activityList = activityList;
    }

    public List<ActivityModel> getActivityList() {
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
        private List<ActivityModel> activityList;

        public Builder withActivityList(List<ActivityModel> activityList) {
            this.activityList = activityList;
            return this;
        }

        public GetItineraryActivitiesResult build() {
            return new GetItineraryActivitiesResult(activityList);
        }
    }
}