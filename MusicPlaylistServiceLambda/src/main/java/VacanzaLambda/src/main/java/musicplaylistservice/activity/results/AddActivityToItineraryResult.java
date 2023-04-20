package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;

import java.util.ArrayList;
import java.util.List;

public class AddActivityToItineraryResult {
    private final List<ActivityModel> activityModelsList;

    public AddActivityToItineraryResult(List<ActivityModel> activityModelsList) {
        this.activityModelsList = activityModelsList;
    }
    public List<ActivityModel> getActivityModelsList() {
        return new ArrayList<>(activityModelsList);
    }
    @Override
    public String toString() {
        return "AddActivityToItineraryResult{" +
                "activityModelsList=" + activityModelsList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ActivityModel> activityModelList;
        public Builder() {
            this.activityModelList = activityModelList;
        }
        public Builder withActivitiesList(List<ActivityModel> list) {
            this.activityModelList = new ArrayList<>(list);
            return this;

        }



        public AddActivityToItineraryResult build() {
            return new AddActivityToItineraryResult(activityModelList);
        }
    }
}
