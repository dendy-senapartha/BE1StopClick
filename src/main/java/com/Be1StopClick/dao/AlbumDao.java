package com.Be1StopClick.dao;
import com.Be1StopClick.model.Album;

import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Dao
 */

public interface AlbumDao extends Dao<Album, Integer> {
    List<Album> getBuyedAlbumOfUser(String userId);
}
