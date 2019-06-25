package com.Be1StopClick.dao;
import com.Be1StopClick.model.Product;
import com.Be1StopClick.model.Track;
import com.Be1StopClick.model.Video;

import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Dao
 */

public interface TrackDao extends Dao<Track, Integer> {
    List<Track> findTrackByProductId(String productId);
    List<Track> getTracksByAlbumAndUserId(String productId);
}
