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
        this.bindClassMethods(['clientLoaded', 'mount', 'addItineraryToPage', 'addActivitiesToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addItineraryToPage);
        this.dataStore.addChangeListener(this.addActivitiesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewItinerary constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const email = urlParams.get('email');
        document.getElementById('itinerary-email').innerText = "Loading Itinerary...";
        const itinerary = await this.client.getItinerary(email);
        this.dataStore.set('itinerary', itinerary);
        document.getElementById('activities').innerText = "(loading activities...)";
        const songs = await this.client.getPlaylistSongs(playlistId);
        this.dataStore.set('songs', songs);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-song').addEventListener('click', this.addSong);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }

    /**
     * When the itinerary  is updated in the datastore, update the itinerary metadata on the page.
     */
    addItineraryToPage() {
        const itinerary = this.dataStore.get('itinerary');
        if (itinerary == null) {
            return;
        }

        document.getElementById('itinerary-tripName').innerText = itinerary.tripName;
        document.getElementById('itinerary-email').innerText = itinerary.email;

        let tagHtml = '';
        let tag;
        for (tag of itinerary.tags) {
            tagHtml += '<div class="tag">' + tag + '</div>';
        }
        document.getElementById('tags').innerHTML = tagHtml;
    }

    /**
     * When the activities are updated in the datastore, update the list of activities on the page.
     */
    addActivitiesToPage() {
        const activities = this.dataStore.get('activities')

        if (activities == null) {
            return;
        }

        let songHtml = '';
        let song;
        for (activity of activities) {
            songHtml += `
                <li class="activity">
                    <span class="title">$(activity.name}</span>
                    <span class="album">${activity.cityCountry}</span>

                </li>
            `;
        }
        document.getElementById('activities').innerHTML = songHtml;
    }

    /**
     * Method to run when the add activity Itinerary submit button is pressed. Call the MusicPlaylistService to add an activity to the
     * itinerary.
     */
    async addActivity() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const itinerary = this.dataStore.get('itinerary');
        if (itinerary == null) {
            return;
        }

        document.getElementById('add-activity').innerText = 'Adding...';
        const name = document.getElementById('activity-name').value;
        const cityCountry = document.getElementById('cityCountry').value;
        const email = email;

        const songList = await this.client.addSongToPlaylist(playlistId, asin, trackNumber, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('songs', songList);

        document.getElementById('add-activity').innerText = 'Add Activity';
        document.getElementById("add-activity-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewItinerary = new ViewItinerary();
    viewItinerary.mount();
};

window.addEventListener('DOMContentLoaded', main);
