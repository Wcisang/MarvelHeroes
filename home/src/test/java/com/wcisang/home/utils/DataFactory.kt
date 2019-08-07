package com.wcisang.home.utils

import com.wcisang.core.data.source.response.Data
import com.wcisang.core.data.source.response.MarvelCharactersResponse
import com.wcisang.core.domain.model.*

object DataFactory {

    fun getMarvelResponse() = MarvelCharactersResponse(
        200,
        "OK",
        "© 2019 MARVEL",
        "Data provided by Marvel. © 2019 MARVEL",
        "<a href=\"http://marvel.com\">Data provided by Marvel. © 2019 MARVEL</a>",
        "291d7b8801e4000f4370cc46bc5f3fdffb254ea5",
        Data(
            1, 1, 1, 1,
            getCharacterList()
        )

    )

    fun getCharacterList() = listOf(
        Character(
            1,
            "Thor",
            "Thunder",
            "2019-02-06T18:10:24-0500",
            Thumbnail("path", "img"),
            "uri",
            Comics(
                1,
                "uri",
                getComics(),
                1
            ),
            Series(
                1,
                "uri",
                getSeries(),
                1
            ),
            Stories(
                1,
                "uri",
                getStories(),
                1
            ),
            Events(
                1,
                "uri",
                getEvents(),
                1
            ),
            getUrls()
            )
    )

    fun getComics() = listOf(
        Items(
            "uri",
            "comic"
        )
    )

    fun getSeries() = listOf(
        Items(
            "uri",
            "serie"
        )
    )

    fun getStories() = listOf(
        Items(
            "uri",
            "storie"
        )
    )

    fun getEvents() = listOf(
        Items(
            "uri",
            "event"
        )
    )

    fun getUrls() = listOf(
        Urls(
            "pdf",
            "url"
        )
    )
}