package com.db.data.repository

import com.rusatom.domain.LoadResult
import com.db.data.api.ApiEvents
import com.db.data.pojo.Mapper
import com.db.data.pojo.categories.Categories.IDS_ACTIONS
import com.db.data.pojo.categories.Categories.IDS_CHILD
import com.db.data.pojo.categories.Categories.IDS_EDUCATION
import com.db.data.pojo.categories.Categories.IDS_HOLIDAYS
import com.db.data.pojo.categories.Categories.IDS_MY_REGION
import com.db.data.pojo.categories.Categories.IDS_NATURE
import com.db.data.pojo.categories.Categories.IDS_PROMO
import com.db.data.pojo.categories.Categories.IDS_SHOWS
import com.db.data.pojo.categories.Categories.WORDS_ACTIONS
import com.db.data.pojo.categories.Categories.WORDS_CHILDS
import com.db.data.pojo.categories.Categories.WORDS_EDUCATION
import com.db.data.pojo.categories.Categories.WORDS_EMPTY_EAT
import com.db.data.pojo.categories.Categories.WORDS_EMPTY_FRIENDS
import com.db.data.pojo.categories.Categories.WORDS_EMPTY_LOVE
import com.db.data.pojo.categories.Categories.WORDS_HOLIDAYS
import com.db.data.pojo.categories.Categories.WORDS_NATURE
import com.db.data.pojo.categories.Categories.WORDS_PROMOS
import com.db.data.pojo.categories.Categories.WORDS_REGION
import com.db.data.pojo.categories.Categories.WORDS_SHOWS
import com.db.data.tools.DateUtils
import com.db.data.tools.Porter
import com.db.domain.models.ModelCategory
import com.db.domain.models.ModelChoice
import com.db.domain.models.ModelEvent
import com.db.domain.models.Type
import com.db.domain.repository.EventsRepository
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class EventsRepositoryImpl @Inject constructor(
    private val service: ApiEvents,
) : EventsRepository {

    override suspend fun eventDetails(id: Int): LoadResult<ModelEvent> {
        return when (val result =
            BaseRepository.safeCall { service.eventDetailsAsync(id) }) {
            is LoadResult.Loading -> LoadResult.Loading
            is LoadResult.Success -> {
                val model = result.data
                if (model != null) {
                    LoadResult.Success(
                        Mapper.mapToModelEvent(
                            model.id,
                            model.title,
                            model.dateTimestamp,
                            model.text,
                            model.image?.original?.src
                        )
                    )
                } else {
                    LoadResult.Error(402)
                }
            }
            is LoadResult.Error -> LoadResult.Error(result.code)
        }
    }

    override suspend fun eventsBySpheres(
        spheres: List<Int>,
        dateStart: Date,
        dateEnd: Date?,
        page: Int?
    ): LoadResult<Pair<Boolean, List<ModelEvent>>> {
        val sp = if (spheres.isEmpty()) {
            null
        } else {
            spheres.joinToString()
        }
        val start = DateUtils.parseDateForRequest(dateStart)
        val end = DateUtils.parseDateForRequest(dateStart)

        return when (val result =
            BaseRepository.safeCall {
                service.eventsBySpheresAsync(
                    spheres = sp,
                    filter = "{\">=occurrences.date_from\":\"$start 00:00:00\",\"<=occurrences.date_to\":\"$end 23:59:59\"}",
                    page = page
                )
            }) {
            is LoadResult.Loading -> LoadResult.Loading
            is LoadResult.Success -> {
                val model = result.data?.items
                val items = mutableListOf<ModelEvent>()
                val meta = result.data?._meta
                var isLast = meta?.currentPage == meta?.pageCount || meta?.totalCount == 0
                if (model != null) {
                    if (meta?.currentPage!! > 3) isLast = true
                    items.addAll(model.map {
                        Mapper.mapToModelEvent(
                            it.id,
                            it.title,
                            it.dateTimestamp,
                            it.text,
                            it.image?.middle?.src
                        )
                    })

                }
                LoadResult.Success(Pair(true, items))
            }
            is LoadResult.Error -> LoadResult.Error(result.code)
        }
    }

    override suspend fun currentSpheresByWords(words: String): List<Int> {
        val categories = loadCategories()

        val spheres = mutableSetOf<Int>()
        categories.forEach { cat ->
            cat.words.forEach { w ->
                val pattern = w
                    .replace("\"(\\[\"]|.*)?\"".toRegex(), " ")
                    .split("[^\\p{Alpha}]+".toRegex())
                    .filter { w.isNotBlank() }
                    .map(Porter::stem)
                    .filter { it.length > 2 }
                    .joinToString().toRegex()

                val result = pattern.find(words)?.value
                if (!result.isNullOrEmpty()) {
                    cat.spheres.forEach { sphere ->
                        spheres.add(sphere)
                    }
                }
            }
        }
        return spheres.toList()
    }

    override suspend fun loadCategories(): List<ModelCategory> {
        val c1 = ModelCategory(Type.EDUCATION, WORDS_EDUCATION, IDS_EDUCATION)
        val c2 = ModelCategory(Type.NATURE, WORDS_NATURE, IDS_NATURE)
        val c3 = ModelCategory(Type.SHOWS, WORDS_SHOWS, IDS_SHOWS)
        val c4 = ModelCategory(Type.ACTIONS, WORDS_ACTIONS, IDS_ACTIONS)
        val c5 = ModelCategory(Type.HOLIDAY, WORDS_HOLIDAYS, IDS_HOLIDAYS)
        val c6 = ModelCategory(Type.PROMO, WORDS_PROMOS, IDS_PROMO)
        val c7 = ModelCategory(Type.CHILD, WORDS_CHILDS, IDS_CHILD)
        val c8 = ModelCategory(Type.REGION, WORDS_REGION, IDS_MY_REGION)
        val c9 = ModelCategory(Type.EAT, WORDS_EMPTY_EAT, emptyList())
        val c10 = ModelCategory(Type.FRIENDS, WORDS_EMPTY_FRIENDS, emptyList())
        val c11 = ModelCategory(Type.LOVE, WORDS_EMPTY_LOVE, emptyList())
        return listOf(c1, c3, c2, c4, c5, c6, c7, c8, c9, c10, c11)
    }

    override suspend fun loadPopularChoices(): List<ModelChoice> {
        val list1 = listOf(
            "Поднять настроение активным отдыхом",
            "Научиться чему то новому и интересному",
            "Отдохнуть от суеты на свежем воздухе",
            "Насладиться культурным досугом",
        )
        val list2 = listOf(
            "Попробовать себя в марафоне",
            "Сходить на концерт",
            "Провести время с семьей",
            "Сходить на мероприятия поблизости",
        )
        val list3 = listOf(
            "Покататься на велосипедах",
            "Сходить на творческие мероприятия",
            "Познакомится с музейными экспонатами",
            "Провести время с детьми",
        )
        val full = listOf(list1, list2, list3)

        val random = full[Random.nextInt(0, 2)].map { ModelChoice(it, "") }
        return random.shuffled()
    }


}

