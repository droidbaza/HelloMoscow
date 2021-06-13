package com.db.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.db.data.tools.DateUtils
import com.db.domain.models.ModelChoice
import com.db.domain.models.ModelEvent
import com.db.domain.usecases.CurrentSpheresByWordsUseCase
import com.db.domain.usecases.EventsBySpheresUseCase
import com.db.domain.usecases.PopularChoicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    private val eventsBySpheres: EventsBySpheresUseCase,
    private val currentSpheresByWordsUseCase: CurrentSpheresByWordsUseCase,
    private val popularChoicesUseCase: PopularChoicesUseCase,
) : ViewModel() {

    val liveEvents: MutableLiveData<List<ModelEvent>> = MutableLiveData()
    val liveChoices: MutableLiveData<List<ModelChoice>> = MutableLiveData()

    private val choices = mutableListOf<ModelChoice>()
    private val events = mutableListOf<ModelEvent>()
    private var currentDate = DateUtils.today()

    init {
        loadPopularChoices()
    }

    fun loadMyChoice(title: String?) {
        if (title == null) return
        if (choices.size > 4) {
            choices.removeLast()
        }
        choices.add(ModelChoice(title, null))
        liveChoices.postValue(choices)
    }

    fun loadPopularChoices() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = popularChoicesUseCase()
            choices.clear()
            choices.addAll(result)
            liveChoices.postValue(choices)
        }
    }

    var job: Job? = null
    private fun loadEvents(spheres: List<Int>) {
        if (job?.isActive == true) job?.cancel()
        reset()
        job = viewModelScope.launch(Dispatchers.IO) {
            eventsBySpheres(spheres, currentDate).onSuccess {
                events.addAll(it.second)
                events.shuffle()
            }
            liveEvents.postValue(events)
        }
    }

    private fun reset() {
        events.clear()
        liveEvents.postValue(events)
    }

    fun searchCategories(value: String?, inProgress: (Pair<Boolean, String>) -> Unit) {
        if (value.isNullOrEmpty()) return
        reset()
        viewModelScope.launch(Dispatchers.IO) {
            val result = currentSpheresByWordsUseCase(value)
            if (result.isNotEmpty()) {
                loadEvents(result)
                withContext(Dispatchers.Main) {
                    inProgress(Pair(true, "поиск подходящих вариантов"))
                }
            } else {
                withContext(Dispatchers.Main) {
                    inProgress(
                        Pair(
                            false,
                            "по запросу $value ничего не найдено, попробуйте изменить запрос"
                        )
                    )
                }
            }
        }
    }

    fun changeDate(date: Date) {
        currentDate = date
    }
}
