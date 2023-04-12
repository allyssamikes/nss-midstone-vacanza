package VacanzaLambda.src.main.java.musicplaylistservice.dependency;

import com.nashss.se.musicplaylistservice.activity.AddSongToPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.CreatePlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.GetPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.GetPlaylistSongsActivity;
import com.nashss.se.musicplaylistservice.activity.SearchPlaylistsActivity;
import com.nashss.se.musicplaylistservice.activity.UpdatePlaylistActivity;

import com.nashss.se.musicplaylistservice.dependency.DaoModule;
import com.nashss.se.musicplaylistservice.dependency.MetricsModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Itinerary Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
    CreatePlaylistActivity provideCreatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    SearchPlaylistsActivity provideSearchPlaylistsActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */
    UpdatePlaylistActivity provideUpdatePlaylistActivity();

}