package com.nashss.se.musicplaylistservice.test.helper;

import com.nashss.se.musicplaylistservice.dynamodb.models.AlbumTrack;
import com.nashss.se.musicplaylistservice.dynamodb.models.Itinerary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class PlaylistTestHelper {
    private PlaylistTestHelper() {
    }

    public static Itinerary generatePlaylist() {
        return generatePlaylistWithNAlbumTracks(1);
    }

    public static Itinerary generatePlaylistWithNAlbumTracks(int numTracks) {
        Itinerary playlist = new Itinerary();
        playlist.setId("id");
        playlist.setName("a playlist");
        playlist.setCustomerId("CustomerABC");
        playlist.setTags(Collections.singleton("tag"));

        List<AlbumTrack> albumTracks = new LinkedList<>();
        for (int i = 0; i < numTracks; i++) {
            albumTracks.add(AlbumTrackTestHelper.generateAlbumTrack(i));
        }
        playlist.setSongList(albumTracks);
        playlist.setSongCount(albumTracks.size());

        return playlist;
    }
}
