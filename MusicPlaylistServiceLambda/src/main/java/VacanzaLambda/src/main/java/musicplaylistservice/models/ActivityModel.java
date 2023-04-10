package VacanzaLambda.src.main.java.musicplaylistservice.models;


import java.util.Objects;

public class ActivityModel {
    private String name;
    private String cityCountry;
    private String  address;
    private Enum TYPE_OF_ACTIVITY;
    private Boolean kidFriendly;
    private Boolean weatherPermitting;

    public ActivityModel(String name, String cityCountry, String address, Enum TYPE_OF_ACTIVITY, Boolean kidFriendly, Boolean weatherPermitting) {
        this.name = name;
        this.cityCountry = cityCountry;
        this.address = address;
        this.TYPE_OF_ACTIVITY = TYPE_OF_ACTIVITY;
        this.kidFriendly = kidFriendly;
        this.weatherPermitting = weatherPermitting;
    }


    public String getName() {
        return name;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public String getAddress() {
        return address;
    }

    public Enum getTYPE_OF_ACTIVITY() {
        return TYPE_OF_ACTIVITY;
    }

    public Boolean getKidFriendly() {
        return kidFriendly;
    }

    public Boolean getWeatherPermitting() {
        return weatherPermitting;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityModel that = (ActivityModel) o;
        return Objects.equals(name, that.name) && Objects.equals(cityCountry, that.cityCountry) && Objects.equals(address, that.address) && Objects.equals(TYPE_OF_ACTIVITY, that.TYPE_OF_ACTIVITY) && Objects.equals(kidFriendly, that.kidFriendly) && Objects.equals(weatherPermitting, that.weatherPermitting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityCountry, address, TYPE_OF_ACTIVITY, kidFriendly, weatherPermitting);
    }

    public static class Builder {
        private String name;
        private String cityCountry;
        private String address;
        private Enum TYPE_OF_ACTIVITY;
        private Boolean kidFriendly;
        private Boolean weatherPermitting;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCityCountry(String cityCountry) {

            this.cityCountry = cityCountry;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withTypeOfActivity(Enum TYPE_OF_ACTIVITY) {
            this.TYPE_OF_ACTIVITY = TYPE_OF_ACTIVITY;
            return this;
        }

        public Builder withKidFriendly(Boolean kidFriendly) {
            this.kidFriendly = kidFriendly;
            return this;
        }

        public Builder withWeatherPermitting(Boolean weatherPermitting) {
            this.weatherPermitting = weatherPermitting;
            return this;
        }

        public ActivityModel build() {
            return new ActivityModel(name, cityCountry, address, TYPE_OF_ACTIVITY, kidFriendly, weatherPermitting);
        }
    }}
