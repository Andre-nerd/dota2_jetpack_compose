package com.zoom_machine.dota_2.common

import com.zoom_machine.dota_2.R
import com.zoom_machine.dota_2.data.Comment

fun getComment(): List<Comment> {
    return listOf(
        Comment(
            "Auguste Conte",
            "February 14, 2019",
            "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
            R.drawable.avatar_1
        ),
        Comment(
            "Jang Marcelino",
            "February 14, 2019",
            "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
            R.drawable.avatar_2
        )
    )
}