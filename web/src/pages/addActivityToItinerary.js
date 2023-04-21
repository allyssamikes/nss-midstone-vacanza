import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class AddActivityToItinerary extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewPlaylist'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewPlaylist);
        this.header = new Header(this.dataStore);
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('add-activity').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
    }
        /**
         * Method to run when the create activity submit button is pressed. Call the MusicPlaylistService to create the
         * activity.
         */
        async submit(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('add-activity');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const cityCountry = document.getElementById('activity-cityCountry').value;
            const name = document.getElementById('activity-name').value;

            const email = document.getElementById("email").value;
            const tripName = document.getElementById("itinerary-name").value;

            const activities = await this.client.addActivityToItinerary(email, tripName, cityCountry, name);
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');

            this.dataStore.set('activityList', activities);
            const itinerary = await this.client.getItinerary(email, tripName);
            this.dataStore.set('itinerary', itinerary);
            createButton.innerText = 'Complete';
            createButton.innerText = 'Add Another Activity';
        }
   /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     //this will be two redirects to activities and view itinerary pages
     */
    redirectToViewPlaylist() {

        const itinerary = this.dataStore.get('itinerary');
        if (playlist != null) {
            window.location.href = `/index.html?email=${itinerary.email}?tripName=${itinerary.tripName}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const addActivityToItinerary = new AddActivityToItinerary();
    addActivityToItinerary.mount();
};

window.addEventListener('DOMContentLoaded', main);