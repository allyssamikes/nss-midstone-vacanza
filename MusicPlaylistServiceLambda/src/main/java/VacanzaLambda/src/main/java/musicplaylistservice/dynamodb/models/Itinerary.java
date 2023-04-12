package VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Objects;


@DynamoDBTable(tableName = "itineraries")
public class Itinerary {
    private String tripName;
    private String email;
    private List<String> cities;

    private List<Activity> activities;

    private List<String> users;


    @DynamoDBRangeKey(attributeName = "trip_name")
    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName= tripName;
    }
    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    @DynamoDBAttribute(attributeName = "cities")
    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @DynamoDBAttribute(attributeName = "activities")
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities= activities;
    }

    @DynamoDBAttribute(attributeName = "users")
    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerary itinerary = (Itinerary) o;
        return Objects.equals(tripName, itinerary.tripName) && Objects.equals(email, itinerary.email) && Objects.equals(cities, itinerary.cities) && Objects.equals(activities, itinerary.activities) && Objects.equals(users, itinerary.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripName, email, cities, activities, users);
    }
}