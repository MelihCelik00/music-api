package com.melih.musicapi.repository

import com.melih.musicapi.model.entity.Song
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class SongRepository {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val songs = ConcurrentHashMap<Long, Song>()
    private var currentId = 1L

    fun findAll(): List<Song> = songs.values.toList()

    fun findById(id: Long?): Song? = songs[id]

    fun add(song: Song): Song {
        val id = song.id.takeIf { it != 0L } ?: currentId++
        val newSong = song.copy(id = id)
        songs[id] = newSong
        return newSong
    }

    fun delete(song: Song) {
        songs.remove(song.id)
    }

    fun returnActiveSong(song: Song) {
        logger.info("Active song is: {}", song.name)
    }

    fun stopActiveSong(song: Song) {
        logger.info("Song stopped!")
    }

}