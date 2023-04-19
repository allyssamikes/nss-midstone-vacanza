import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class GetItineraryActivities extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'addItineraryToPage', 'addActivitiesToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addActivitiesToPage);
        //listener is not a function error with a dud listener as above and with out this?
        this.dataStore.addChangeListener(this.addItineraryToPage);
        this.header = new Header(this.dataStore);
    }
    /**
     * Add the header to the page and load the MusicPlaylistClient.
     //do we need another client??
     */
    mount() {
        document.getElementById('activities-of-itinerary').addEventListener('click', this.submit);
console.log("mount");
        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
    }
        /**
         * Method to run when the create itinerary submit button is pressed. Call the MusicPlaylistService to create the
         * playlist.
         */
        async submit(evt) {
            evt.preventDefault();
console.log("submit");
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');
            const getButton = document.getElementById('activities-of-itinerary');
            const origButtonText = getButton.innerText;
            getButton.innerText = 'Loading...';
            const tripName = document.getElementById('tripName').value;
            const email = document.getElementById('email').value;
 console.log(email);
 console.log(tripName);
            const itinerary = await this.client.getItinerary(email, tripName, (error) => {
                getButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
            const activities = await this.client.getItineraryActivities(email, tripName, (error) => {
                getButton.innerText = origButtonText;
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });
console.log(itinerary);
            this.dataStore.set('itinerary', itinerary);
console.log(activities);
            this.dataStore.set('activities', activities);
            getButton.innerText = 'Complete';
            getButton.innerText = 'Submit to View';
        }
     addItineraryToPage() {
           const itinerary = this.dataStore.get('itinerary');
                if (itinerary == null) {
                    return;
                }
                document.getElementById('tripName').innerText = itinerary.tripName;

 console.log(itinerary.tripName);
 console.log(itinerary.activities);

            const submitResultsContainer = document.getElementById('submit-results-container');
            const submitCriteriaDisplay  =   document.getElementById('submit-criteria-display');
             const submitResultsDisplay  =   document.getElementById('submit-results-display');
            const submitUsersDisplay  =   document.getElementById('users-display');
             const activitiesContainer = document.getElementById('activities-container');
             activitiesContainer.classList.remove('hidden');
             //document.getElementById('activities').innerHTML = JSON.stringify(itinerary.activities);
             document.getElementById('submit-results-display').innerHTML = itinerary.activities;

console.log(itinerary.users);
                activitiesContainer.classList.remove('hidden');
                submitResultsContainer.classList.remove('hidden');
                submitResultsDisplay.innerHTML = itinerary.cities;
                //submitCriteriaDisplay.innerHTML = itinerary.cities;
                submitUsersDisplay.innerHTML = itinerary.users;
                          // const activities = this.dataStore.get('activities');
                          const activities = itinerary.activities
                           console.log(activities);

                          if (activities == null) {
                                return;
                          }

                          let activityHtml = '';
                          let activity;
                              for (activity of activities) {
                              console.log(activity);
                              console.log(activity.name);
                              console.log(activity.cityCountry);
                                 activityHtml += `
                                <li class="activity">
                                 <span class="name">${activity.name}</span>
                                 <span class="space">${" : "}</span>
                                 <span class="place">${activity.cityCountry}</span>
                                  </li>
                                        `;
                              }
                          document.getElementById('activities').innerHTML = activityHtml;
            }


     addActivitiesToPage() {

           const activities = this.dataStore.get('activities');
           console.log(activities);
          if (activities == null) {
                return;
          }

          let activityHtml = '';
          let activity;
              for (activity of activities) {
              console.log(activity);
                 activityHtml += `
                <li class="activity">
                 <span class="name">${activity.tripName}</span>
                 <span class="place">${activity.cityCountry}</span>
                  </li>
                        `;
              }
          document.getElementById('activities-table').innerHTML = activityHtml;
     }
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
console.log("main");
    const getItineraryActivities = new GetItineraryActivities();
    getItineraryActivities.mount();
};

window.addEventListener('DOMContentLoaded', main);