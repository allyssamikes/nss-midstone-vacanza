import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the AddActivityToItinerary page of the website.
 */
class AddActivityToItinerary extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','submitRemove', 'searchActivities'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }
    /**
     * Add the header to the page and load the Client.
     */
    mount() {
        document.getElementById('add-activity').addEventListener('click', this.submit);
        document.getElementById('remove-activity').addEventListener('click', this.submitRemove);
        document.getElementById('search-activity').addEventListener('click', this.searchActivities);
        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
    }
        /**
         * Method to run when the add activity submit button is pressed. Calls the VacanzaService to add the
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

            const activities = await this.client.addActivityToItinerary(email, tripName, cityCountry, name, (error) => {
                createButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('activityList', activities);
            const itinerary = await this.client.getItinerary(email, tripName);
            this.dataStore.set('itinerary', itinerary);
            let activityInput1 = document.getElementById('activity-cityCountry');
            activityInput1.value = "";
            let activityInput2 = document.getElementById('activity-name');
            activityInput2.value = "";
            createButton.innerText = 'Complete';
            createButton.innerText = 'Add Another Activity';
        }
        /**
         * Method to run when the remove activity submit button is pressed. Calls the VacanzaService to remove the
         * activity.
         */
        async submitRemove(evt) {
            evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('remove-activity');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const cityCountry = document.getElementById('activity-cityCountry').value;
            const name = document.getElementById('activity-name').value;

            const email = document.getElementById("email").value;
            const tripName = document.getElementById("itinerary-name").value;

            const activities = await this.client.removeActivityFromItinerary(email, tripName, cityCountry, name, (error) => {
                createButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('activityList', activities);
            const itinerary = await this.client.getItinerary(email, tripName);
            this.dataStore.set('itinerary', itinerary);
            let activityInput1 = document.getElementById('activity-cityCountry');
            activityInput1.value = "";
            let activityInput2 = document.getElementById('activity-name');
            activityInput2.value = "";
            createButton.innerText = 'Complete';
            createButton.innerText = 'Remove Another Activity';
        }
        async searchActivities(evt) {
           evt.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('search-activity');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Loading...';

            const cityCountry = document.getElementById('search-activity-cityCountry').value;
  console.log(cityCountry);
            const activitiesFound = await this.client.searchActivities(cityCountry, (error) => {
                  createButton.innerText = origButtonText;
                  const errorMessageDisplay = document.getElementById('error-message');
                  errorMessageDisplay.innerText = `Error: ${error.message}`;
                  errorMessageDisplay.classList.remove('hidden');
                  });
  console.log(activitiesFound);
            this.dataStore.set('activitiesFound', activitiesFound);
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