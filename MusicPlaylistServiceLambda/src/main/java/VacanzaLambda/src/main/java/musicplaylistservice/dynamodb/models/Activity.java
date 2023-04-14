package VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "activities")
public class Activity{
    private String name;
    private String cityCountry;
    private String  address;
    private TYPE_OF_ACTIVITY type;
    private Boolean kidFriendly;
    private Boolean weatherPermitting;

    @DynamoDBRangeKey(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DynamoDBHashKey(attributeName = "cityCountry")
    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }
    @DynamoDBAttribute(attributeName = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @DynamoDBAttribute(attributeName = "type_of_activity")
    public TYPE_OF_ACTIVITY getTYPE_OF_ACTIVITY() {
        return type;
    }

    public void setTYPE_OF_ACTIVITY(TYPE_OF_ACTIVITY type) {
        this.type = type;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "kid_friendly")
    public Boolean getKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(Boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "weather_permitting")
    public Boolean getWeatherPermitting() {
        return weatherPermitting;
    }

    public void setWeatherPermitting(Boolean weatherPermitting) {
        this.weatherPermitting = weatherPermitting;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", cityCountry='" + cityCountry + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", kidFriendly=" + kidFriendly +
                ", weatherPermitting=" + weatherPermitting +
                '}';
    }
}
