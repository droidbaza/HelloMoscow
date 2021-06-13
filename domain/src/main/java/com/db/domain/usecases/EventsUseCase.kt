package com.db.domain.usecases

import com.db.domain.repository.EventsRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventDetailsUseCase @Inject constructor(private val eventsRepo: EventsRepository) {
    suspend operator fun invoke(id: Int) = eventsRepo.eventDetails(id)
}

@Singleton
class EventsBySpheresUseCase @Inject constructor(private val eventsRepo: EventsRepository) {
    suspend operator fun invoke(
        spheres: List<Int>,
        dateStart: Date,
        dateEnd: Date? = null,
        page: Int? = null
    ) =
        eventsRepo.eventsBySpheres(spheres, dateStart, dateEnd, page)
}


@Singleton
class CurrentSpheresByWordsUseCase @Inject constructor(private val eventsRepo: EventsRepository) {
    suspend operator fun invoke(words: String) = eventsRepo.currentSpheresByWords(words)
}

@Singleton
class PopularChoicesUseCase @Inject constructor(private val eventsRepo: EventsRepository) {
    suspend operator fun invoke() = eventsRepo.loadPopularChoices()
}
