package com.melih.musicapi.model.entity

import lombok.Getter
import lombok.Setter

@Setter
@Getter
data class Playlist(
    val id: Long?,
    val name: String,
    val songs: MutableList<Song> = mutableListOf()
)
