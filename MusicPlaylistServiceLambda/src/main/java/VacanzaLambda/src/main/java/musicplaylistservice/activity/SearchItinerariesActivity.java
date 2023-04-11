package VacanzaLambda.src.main.java.musicplaylistservice.activity;


import VacanzaLambda.src.main.java.musicplaylistservice.dynamodb.ItineraryDao;
import com.nashss.se.musicplaylistservice.activity.requests.SearchPlaylistsRequest;
import com.nashss.se.musicplaylistservice.activity.results.SearchPlaylistsResult;
import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PlaylistDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Playlist;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static com.nashss.se.musicplaylistservice.utils.NullUtils.ifNull;

public class SearchItinerariesActivity {

    private final Logger log = LogManager.getLogger();
    private final ItineraryDao itineraryDao;

    /**
     * Instantiates a new SearchItinerariesActivity object.
     *
     * @param itineraryDao itineraryDao to access the itinerary table.
     */
    @Inject
    public SearchItinerariesActivity(ItineraryDao itineraryDao) {
        this.itineraryDao = itineraryDao;
    }


    /**
     * This method handles the incoming request by searching for  itinerary from the database.
     * <p>
     * It then returns the matching itineraries, or an empty result list if none are found.
     *
     * @param searchItineraryRequest request object containing the search criteria
     * @return searchItinerariesResult result object containing the playlists that match the
     * search criteria.
     */
    public SearchPlaylistsResult handleRequest(final SearchPlaylistsRequest searchPlaylistsRequest) {
        log.info("Received SearchPlaylistsRequest {}", searchPlaylistsRequest);

        String criteria = ifNull(searchPlaylistsRequest.getCriteria(), "");
        String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.split("\\s");

        List<Playlist> results = playlistDao.searchPlaylists(criteriaArray);
        List<PlaylistModel> playlistModels = new ModelConverter().toPlaylistModelList(results);

        return SearchPlaylistsResult.builder()
                .withPlaylists(playlistModels)
                .build();
    }
}

}