package com.db.presentation.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.db.domain.models.ModelEvent
import com.db.presentation.R
import com.db.presentation.MainSharedViewModel
import com.db.presentation.ui.adapters.EventsAdapter
import com.db.presentation.ui.adapters.EventsSmallAdapter


class ResultFragment : DialogFragment() {

    lateinit var viewModelMain: MainSharedViewModel
    private var callback: ViewPager2.OnPageChangeCallback? = null
    private var eventsAdapter: EventsAdapter? = null
    private var eventsSmallAdapter: EventsSmallAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.FullscreenTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventsAdapter = null
        callback = null
        eventsSmallAdapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain = ViewModelProvider(requireActivity()).get(MainSharedViewModel::class.java)
        eventsAdapter = EventsAdapter {}
        val rv: RecyclerView = view.findViewById(R.id.rv_small)
        callback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                rv.smoothScrollToPosition(position)
            }
        }
        val pager: ViewPager2 = view.findViewById(R.id.rv_events)
        eventsSmallAdapter = EventsSmallAdapter {
            pager.setCurrentItem(it, true)
        }
        rv.adapter = eventsSmallAdapter
        pager.apply {
            offscreenPageLimit = 3
            adapter = eventsAdapter
            registerOnPageChangeCallback(callback!!)
        }
        viewModelMain.liveEvents.observe(viewLifecycleOwner, {
            showEvents(it)
        })
    }

    private fun showEvents(it: List<ModelEvent>) {
        eventsAdapter?.update(it)
        eventsSmallAdapter?.update(it)
    }

}