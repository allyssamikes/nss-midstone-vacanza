import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";



/**
 * Logic needed for the view playlist page of the website.
 */
class ViewItinerary extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addItineraryToPage', 'addCitiesToPage'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const email = urlParams.get("email");
        const tripName = urlParams.get("tripName");
        const itinerary = await this.client.getItinerary(email, tripName);
        this.dataStore.set('itinerary', itinerary);
       // document.getElementById('activities').innerText = activitiesHtml;
      //  const activities = await this.client.getItineraryActivities(email, tripName);
       // this.dataStore.set('activities', activities);
        const cities = itinerary.cities;
        this.dataStore.set('cities', cities);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('viewItinerary')
        this.header.addHeaderToPage();
        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }

    /**
     * When the itinerary  is updated in the datastore, update the itinerary metadata on the page.
     */
    async addItineraryToPage() {
        const itinerary = this.dataStore.get('itinerary');

        document.getElementById('tripName').innerHTML = itinerary.tripName

//        let activityHtml = '';
//        let activity;
//        for (activity of activities) {
//            activityHtml += '<div class="activity">' + activity + '</div>';
//        }
//        document.getElementById('activities').innerHTML = activityHtml;
    }

    /**
     * When the activities are updated in the datastore, update the list of activities on the page.
     */
//    async addActivitiesToPage() {
//        const activities = this.dataStore.get('activities')
//
//        let activitiesHtml = '';
//        let activity;
//        for (activity of activities) {
//            activityHtml += `
//                <li class="activity">
//                    <span class="name">$(activity.name}</span>
//                    <span class="cityCountry">${activity.cityCountry}</span>
//                </li>
//            `;
//        }
//        document.getElementById('activities').innerHTML = activitiesHtml;
//    }

     addCitiesToPage() {
            const cities = this.dataStore.get('cities');

            let cityHtml = '';
            let city;
            for (city of cities) {
                cityHtml += `
                    <li class="city">
                        <span>$(city}</span>
                    </li>
                `;
            }
            document.getElementById('cities').innerHTML = cityHtml;
        }

    /**
     * Method to run when the add activity Itinerary submit button is pressed. Call the MusicPlaylistService to add an activity to the
     * itinerary.
     */
//    async addActivity() {
//
//        const errorMessageDisplay = document.getElementById('error-message');
//        errorMessageDisplay.innerText = ``;
//        errorMessageDisplay.classList.add('hidden');
//
//        const itinerary = this.dataStore.get('itinerary');
//        if (itinerary == null) {
//            return;
//        }
//
//        document.getElementById('add-activity').innerText = 'Adding...';
//        const name = document.getElementById('activity-name').value;
//        const cityCountry = document.getElementById('cityCountry').value;
//        const email = email;
//
//        const activities = await this.client.addActivityToItinerary(name, cityCountry, (error) => {
//            errorMessageDisplay.innerText = `Error: ${error.message}`;
//            errorMessageDisplay.classList.remove('hidden');
//        });
//
//        this.dataStore.set('activities', activities);
//
//        document.getElementById('add-activity').innerText = 'Add Activity';
//        document.getElementById("add-activity-form").reset();
//    }
}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewItinerary = new ViewItinerary();
    viewItinerary.mount();
};

window.addEventListener('DOMContentLoaded', main);
