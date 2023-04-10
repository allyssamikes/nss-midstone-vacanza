package VacanzaLambda.src.main.java.musicplaylistservice.dependency;

import VacanzaLambda.src.main.java.musicplaylistservice.activity.*;

import com.nashss.se.musicplaylistservice.dependency.DaoModule;
import com.nashss.se.musicplaylistservice.dependency.MetricsModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Itinerary Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface VServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddActivityToItineraryActivity
     */
    AddActivityToItineraryActivity provideAddActivityToItineraryActivity();

    /**
     * Provides the relevant activity.
     * @return CreateItineraryActivity
     */
    CreateItineraryActivity provideCreateItineraryActivity();

    /**
     * Provides the relevant activity.
     * @return GetItineraryActivity
     */
    GetItineraryActivity provideGetItineraryActivity();

    /**
     * Provides the relevant activity.
     * @return SearchItinerariesActivity
     */
    SearchItinerariesActivity provideSearchItinerariesActivity();

    /**
     * Provides the relevant activity.
     * @return GetItineraryActivitiesActivity
     */
    GetItineraryActivitiesActivity provideGetItineraryActivitiesActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateItineraryActivity
     */
    UpdateItineraryActivity provideUpdateItineraryActivity();

}
