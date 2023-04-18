import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
* Logic needed for the view playlist page of the website.
*/
class ViewActivitiesItinerary extends BindingClass {
constructor() {
super();
this.bindClassMethods(['clientLoaded', 'mount', 'addItineraryToPage', 'addActivitiesToPage'], this);
this.dataStore = new DataStore();
this.dataStore.addChangeListener(this.addItineraryToPage);
this.dataStore.addChangeListener(this.addActivitiesToPage);
this.header = new Header(this.dataStore);
console.log("viewactivitiesitinerary constructor");
}

/**
* Once the client is loaded, get the playlist metadata and song list.
*/
async clientLoaded() {
//this will change with path email/tripname
const urlParams = new URLSearchParams(window.location.search);
const playlistId = urlParams.get('id');

document.getElementById('tripName').innerText = "Loading Itinerary ...";
const playlist = await this.client.getItinerary(email, tripName);
this.dataStore.set('itinerary', itinerary);
document.getElementById('activities').innerText = "(loading activities...)";
//check this name
const activities = await this.client.getItineraryActivities(email, tripName);
this.dataStore.set('activities', activities);
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
* When the playlist is updated in the datastore, update the playlist metadata on the page.
*/
addItineraryToPage() {
const itinerary = this.dataStore.get('itinerary');
if (itinerary == null) {
return;
}

document.getElementById('tripName').innerText = itinerary.tripName;
document.getElementById('email').innerText = itinerary.email;

let tagHtml = '';
let tag;
for (tag of itinerary.activities) {
tagHtml += '<div class="activities">' + tag + '</div>';
}
document.getElementById('activities').innerHTML = tagHtml;
}

/**
* When the songs are updated in the datastore, update the list of songs on the page.
*/
addActivitiesToPage() {
const activities = this.dataStore.get('activities')

if (songs == null) {
return;
}

let songHtml = '';
let activity;
for (activity of activities) {
songHtml += `
<li class="activity">
  <span class="title">${activity.name}</span>
  <span class="album">${activity.cityCountry}</span>
</li>
`;
}
document.getElementById('activities').innerHTML = songHtml;
}

/**
* Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
* playlist.
*/
async addSong() {

const errorMessageDisplay = document.getElementById('error-message');
errorMessageDisplay.innerText = ``;
errorMessageDisplay.classList.add('hidden');

const playlist = this.dataStore.get('playlist');
if (playlist == null) {
return;
}

document.getElementById('add-song').innerText = 'Adding...';
const asin = document.getElementById('album-asin').value;
const trackNumber = document.getElementById('track-number').value;
const playlistId = playlist.id;

const songList = await this.client.addSongToPlaylist(playlistId, asin, trackNumber, (error) => {
errorMessageDisplay.innerText = `Error: ${error.message}`;
errorMessageDisplay.classList.remove('hidden');
});

this.dataStore.set('songs', songList);

document.getElementById('add-song').innerText = 'Add Song';
document.getElementById("add-song-form").reset();
}
}

/**
* Main method to run when the page contents have loaded.
*/
const main = async () => {
const viewActivitiesItinerary = new ViewActivitiesItinerary();
viewActivitiesItinerary.mount();
};

window.addEventListener('DOMContentLoaded', main);
