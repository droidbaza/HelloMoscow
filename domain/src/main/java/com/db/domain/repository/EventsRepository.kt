package com.db.domain.repository

import com.rusatom.domain.LoadResult
import com.db.domain.models.ModelCategory
import com.db.domain.models.ModelChoice
import com.db.domain.models.ModelEvent
import java.util.*

interface EventsRepository {

    suspend fun eventDetails(id: Int): LoadResult<ModelEvent>

    suspend fun eventsBySpheres(
        spheres: List<Int>,
        dateStart: Date,
        dateEnd: Date? = null,
        page: Int? = null
    ): LoadResult<Pair<Boolean, List<ModelEvent>>>

    suspend fun currentSpheresByWords(words: String): List<Int>

    suspend fun loadCategories(): List<ModelCategory>

    suspend fun loadPopularChoices(): List<ModelChoice>

}

