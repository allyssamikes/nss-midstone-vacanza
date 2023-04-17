import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

//const SEARCH_CRITERIA_KEY_ONE = 'search-criteria';
//const SEARCH_RESULTS_KEY = 'search-results';
//const EMPTY_DATASTORE_STATE = {
//    [SEARCH_CRITERIA_KEY]: '',
//    [SEARCH_RESULTS_KEY]: [],
//};
const SEARCH_CRITERIA_EMAIL = 'email';
const SEARCH_CRITERIA_TRIP_NAME = 'tripName';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_EMAIL]: '',
    [SEARCH_CRITERIA_TRIP_NAME]: '',
   [SEARCH_RESULTS_KEY]: [],
};

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewItineraryActivities extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'search', 'displaySearchResults', 'getHTMLForSearchResults'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("viewItineraryActivities constructor");
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('view-itinerary-activities-form').addEventListener('submit', this.search);
        document.getElementById('activities-of-itinerary').addEventListener('click', this.search);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
    }

    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const email = document.getElementById('search-criteria1').value;
        const tripName = document.getElementById('search-criteria2').value;

        // If the user didn't change the search criteria, do nothing
        if (previousSearchCriteria === searchCriteria) {
            return;
        }

        if (searchCriteria) {
            const results = await this.client.search(searchCriteria1);

            this.dataStore.setState({
                [SEARCH_CRITERIA_EMAIL]: email,
                  [SEARCH_CRITERIA_TRIP_NAME]: tripName,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }
        addItineraryToPage() {
            const playlist = this.dataStore.get('itinerary');
            if (playlist == null) {
                return;
            }

            document.getElementById('itinerary-name').innerText = itinerary.tripName;
            //document.getElementById('playlist-owner').innerText = itinerary.customerName;

            let tagHtml = '';
            let tag;
            for (tag of itinerary.activities) {
                tagHtml += '<div class="activities">' + activity + '</div>';
            }
            document.getElementById('activities').innerHTML = tagHtml;
        }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (searchCriteria === '') {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        } else {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
            searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
        }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
        if (searchResults.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>Name</th><th>cityCountry</th><th>Address</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                //how is intinerary.html taking the path????
                    <a href="itinerary.html?id=${res.id}">${res.name}</a>
                </td>
                <td>${res.address}</td>
               // <td>${res.tags?.join(', ')}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewItineraryActivities = new viewItineraryActivities();
    viewItineraryActivities.mount();
};

window.addEventListener('DOMContentLoaded', main);
