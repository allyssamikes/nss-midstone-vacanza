package VacanzaLambda.src.main.java.musicplaylistservice.activity.results;

import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;

public class CreateActivityResult {
    private final ActivityModel activity;

    private CreateActivityResult(ActivityModel activity) {
        this.activity = activity;
    }

    public ActivityModel getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return "CreateActivityResult{" +
                "activity=" + activity +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateActivityResult.Builder builder() {
        return new CreateActivityResult.Builder();
    }

    public static class Builder {
        private ActivityModel activity;

        public Builder withActivity(ActivityModel activity) {
            this.activity = activity;
            return this;
        }

        public CreateActivityResult build() {
            return new CreateActivityResult(activity);
        }
    }
}