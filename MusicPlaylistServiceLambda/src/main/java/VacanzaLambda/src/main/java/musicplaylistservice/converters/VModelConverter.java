package VacanzaLambda.src.main.java.musicplaylistservice.converters;

import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Activity;
import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.models.Itinerary;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ActivityModel;
import VacanzaLambda.src.main.java.musicplaylistservice.models.ItineraryModel;

/**
 * Converts between Data and API models.
 */
public class VModelConverter {

    public ItineraryModel toItineraryModel(Itinerary itinerary) {

        return ItineraryModel.builder()
                .withEmail(itinerary.getEmail())
                .withTripName(itinerary.getTripName())
                .withActivities(itinerary.getActivities())
                .withCities(itinerary.getCities())
                .withUsers(itinerary.getUsers())
                .build();
    }

    public ActivityModel toActivityModel(Activity activity) {
        return ActivityModel.builder()
                .withName(activity.getName())
                .withCityCountry(activity.getCityCountry())
                .withAddress(activity.getAddress())
                .withTypeOfActivity(activity.getTYPE_OF_ACTIVITY())
                .withKidFriendly(activity.getKidFriendly())
                .withWeatherPermitting(activity.getWeatherPermitting())
                .build();
    }

//    /**
//     * Converts a list of AlbumTracks to a list of SongModels.
//     *
//     * @param albumTracks The AlbumTracks to convert to SongModels
//     * @return The converted list of SongModels
////     */
//    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
//        List<SongModel> songModels = new ArrayList<>();
//
//        for (AlbumTrack albumTrack : albumTracks) {
//            songModels.add(toSongModel(albumTrack));
//        }
//
//        return songModels;
//    }
//
//    /**
//     * Converts a list of Playlists to a list of PlaylistModels.
//     *
//     * @param playlists The Playlists to convert to PlaylistModels
//     * @return The converted list of PlaylistModels
//     */
//    public List<PlaylistModel> toPlaylistModelList(List<Itinerary> playlists) {
//        List<PlaylistModel> playlistModels = new ArrayList<>();
//
//        for (Itinerary playlist : playlists) {
//            playlistModels.add(toPlaylistModel(playlist));
//        }
//
//        return playlistModels;
//    }
}
