package com.melih.musicapi.repository

import com.melih.musicapi.model.entity.Playlist
import com.melih.musicapi.model.entity.Song
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class PlaylistRepository {

    private val playlists = ConcurrentHashMap<Long, Playlist>()
    private var currentId = 1L

    private val logger = LoggerFactory.getLogger(javaClass)

    val activeSongId = 1

    fun findAll(): List<Playlist> = playlists.values.toList()

    fun findById(id: Long): Playlist? = playlists[id]

    fun add(playlist: Playlist): Playlist {
        val id = playlist.id.takeIf { it != 0L } ?: currentId++
        val newPlaylist = playlist.copy(id = id)
        playlists[id] = newPlaylist
        return newPlaylist
    }

    fun delete(playlistId: Long?) {
        playlists.remove(playlistId)
    }

    fun addSongToPlaylist(playlistId: Long, song: Song): Playlist? {
        val playlist = playlists[playlistId] ?: return null
        playlist.songs.add(song)
        return playlist
    }

    fun removeSongFromPlaylist(playlistId: Long, songId: Long?): Playlist? {
        val playlist = playlists[playlistId] ?: return null
        playlist.songs.removeIf { it.id == songId }
        return playlist
    }

    fun update(playlist: Playlist, playlistId: Long): Playlist? {
        playlists[playlistId] = playlist
        return playlist
    }

    fun returnActivePlaylist(playlist: Playlist) {
        logger.info("Active playlist is: {}", playlist.name)
    }

    fun skipNextSong(playlist: Playlist) {
        val nextSongId = activeSongId + 1
        if (nextSongId > playlist.songs.size)
            logger.info("End of the playlist.")

    }
}
