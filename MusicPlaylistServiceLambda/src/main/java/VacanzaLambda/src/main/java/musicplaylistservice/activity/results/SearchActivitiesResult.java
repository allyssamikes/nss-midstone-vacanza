package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivitiesResult {

    private final List<ActivityModel> activityModelsList;

    public SearchActivitiesResult(List<ActivityModel> activityModelsList) {
        this.activityModelsList = activityModelsList;
    }

    public List<ActivityModel> getActivityModelsList() {
        return new ArrayList<>(activityModelsList);
    }

    @Override
    public String toString() {
        return "SearchActivitiesResult{" +
                "activityModelsList=" + activityModelsList +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ActivityModel> activityModelsList;

        public Builder withActivityModels(List<ActivityModel> activityList) {
            this.activityModelsList = activityList;
            return this;
        }
        public SearchActivitiesResult build(){
            return new SearchActivitiesResult(activityModelsList);
        }
    }
}
