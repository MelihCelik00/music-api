package com.melih.musicapi.controller

import com.melih.musicapi.model.entity.Playlist
import com.melih.musicapi.model.entity.Song
import com.melih.musicapi.repository.PlaylistRepository
import com.melih.musicapi.repository.SongRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/*
Sarki listesi olusturma +
Sarki listesi düzenleme +
Sarki listesi silme +
Sarki listesine sark ekleme +
Sarki listesinden sark silme +
Sarki listesi çalma +
Sarki calma +
Sarki duraklatma +-
Listedeki bir sonraki sarkiya geçme +
Listedeki bir önceki sarkiya geçme
*/

@RequestMapping("/v1/controller")
@RestController
@Validated
class SpotifyController(
    val songRepository: SongRepository, val playlistRepository: PlaylistRepository
) {

    @PostMapping("/newPlaylist")
    fun createPlaylist(
        @RequestBody requestBody: CreatePlaylistRequest
    ): Playlist {
        val playlist = Playlist(id = null, name = requestBody.name)
        return playlistRepository.add(playlist)
    }

    @PostMapping("/addSongToPlaylist")
    fun addSongToPlaylist(
        @RequestParam playlistId: Long, @RequestBody songRequest: AddSongRequest
    ): Playlist? {
        val song = songRepository.add(Song(id = null, name = songRequest.name))
        return playlistRepository.addSongToPlaylist(playlistId, song)
    }

    @DeleteMapping("/deleteSongFromPlaylist")
    fun deleteSongFromPlaylist(
        @RequestParam playlistId: Long, @RequestBody songRequest: RemoveSongRequest
    ): Playlist? {
        val song = songRepository.add(Song(id = songRequest.id, name = songRequest.name))
        return playlistRepository.removeSongFromPlaylist(playlistId, song.id)
    }

    @DeleteMapping("/deletePlaylist")
    fun deletePlaylist(
        @RequestParam playlistId: Long
    ) {
        println("deleted playlist!")
        return playlistRepository.delete(playlistId)
    }

    @PatchMapping("/renamePlaylist")
    fun renamePlaylist(
        @RequestParam playlistId: Long, @RequestBody newName: NewPlaylistName
    ): Playlist {
        val playlist = playlistRepository.findById(playlistId) ?: throw IllegalArgumentException("Playlist not found")
        val updatedPlaylist = playlist.copy(name = newName.name)
        println(newName)

        return playlistRepository.update(updatedPlaylist, playlistId)!!
    }

    @PatchMapping("/returnActivePlaylist")
    fun patchActivePlaylist(
        @RequestBody playlist: Playlist
    ) {
        return playlistRepository.returnActivePlaylist(playlist)
    }

    @PatchMapping("/returnActiveSong")
    fun patchActiveSong(
        @RequestBody song: Song
    ) {
        return songRepository.returnActiveSong(song)
    }

    @PatchMapping("/stopActiveSong")
    fun stopActiveSong(
        @RequestBody song: Song
    ) {
        return songRepository.stopActiveSong(song)
    }

    data class CreatePlaylistRequest(val name: String)
    data class AddSongRequest(val name: String)
    data class RemoveSongRequest(val id: Long?, val name: String)
    data class NewPlaylistName(val name: String)
}