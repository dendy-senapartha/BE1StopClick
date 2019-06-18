package com.Be1StopClick.dao;
import com.Be1StopClick.model.Video;

import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Dao
 */

public interface VideoDao extends Dao<Video, Integer> {
    List<Video> findVideoByProductId(String productId);
}
