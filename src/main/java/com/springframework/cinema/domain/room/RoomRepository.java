package com.springframework.cinema.domain.room;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springframework.cinema.infrastructure.model.BaseRepository;

/**
 * Created by Patryk on 2017-05-27.
 */
@Repository
public interface RoomRepository extends BaseRepository<Room, Long> {
    Room findByName(String name);

}
