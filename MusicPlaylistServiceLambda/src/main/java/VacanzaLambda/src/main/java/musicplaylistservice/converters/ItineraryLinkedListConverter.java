package VacanzaLambda.src.main.java.musicplaylistservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.List;

public class ItineraryLinkedListConverter implements DynamoDBTypeConverter<String, List> {

    /**
     * Turns an object of type T into an object of type S.
     *
     * @param object
     */
    @Override
    public String convert(List object) {
        return null;
    }

    /**
     * Turns an object of type S into an object of type T.
     *
     * @param object
     */
    @Override
    public List unconvert(String object) {
        return null;
    }

}
