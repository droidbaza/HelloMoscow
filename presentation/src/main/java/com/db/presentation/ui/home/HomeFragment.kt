package com.db.presentation.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.db.data.tools.DateUtils
import com.db.data.tools.Recognizer
import com.db.domain.models.ModelChoice
import com.db.presentation.MainSharedViewModel
import com.db.presentation.R
import com.db.presentation.ui.adapters.CalendarAdapter
import com.db.presentation.ui.adapters.ChoiceAdapter
import com.db.presentation.ui.result.ResultFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(), TabLayout.OnTabSelectedListener {

    companion object {
        const val RecordAudioRequestCode = 1
        const val TAG_SEARCH = "TAG_SEARCH"
    }

    lateinit var viewModelMain: MainSharedViewModel

    private var speechRecognizer: SpeechRecognizer? = null
    private var micButton: FloatingActionButton? = null
    private var rvCalendar: RecyclerView? = null
    private var caseCalendar: LinearLayout? = null
    private var choiceAdapter: ChoiceAdapter? = null
    private var rvChoices: RecyclerView? = null
    private var swipe: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain = ViewModelProvider(requireActivity()).get(MainSharedViewModel::class.java)
        initUi(view)
        initSpeechRecognizer(view.context)
        viewModelMain.apply {
            liveChoices.observe(viewLifecycleOwner, {
                updateChoices(it)
            })
        }
    }

    private fun initUi(view: View) {
        swipe = view.findViewById(R.id.swipe)
        micButton = view.findViewById(R.id.button)
        caseCalendar = view.findViewById(R.id.calendar_layout)
        rvChoices = view.findViewById(R.id.rv_choice)
        rvCalendar = view.findViewById(R.id.rv_calendar)
        view.findViewById<TabLayout>(R.id.tab_layout).addOnTabSelectedListener(this)
        swipe?.setOnRefreshListener { viewModelMain.loadPopularChoices() }
        choiceAdapter = ChoiceAdapter { searchCategory(it) }
        rvCalendar?.adapter = CalendarAdapter { viewModelMain.changeDate(it) }
        rvChoices?.adapter = choiceAdapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSpeechRecognizer(context: Context) {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        speechRecognizer?.setRecognitionListener(object : Recognizer() {
            override fun onRecognizeResult(words: String?) {
                viewModelMain.loadMyChoice(words)
            }
        })
        micButton?.setOnTouchListener { view, motionEvent ->
            if (!isNeedPermission()) {
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    speechRecognizer?.stopListening()
                }
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    speechRecognizer?.startListening(speechRecognizerIntent)
                }
            }
            false
        }
    }

    private fun updateChoices(it: List<ModelChoice>) {
        swipe?.isRefreshing = false
        val size = if (it.size > 4) it.size - 1 else 0
        choiceAdapter?.update(it)
        rvChoices?.smoothScrollToPosition(size)
    }

    private fun searchCategory(value: String?) {
        viewModelMain.searchCategories(value) {
            if (it.first) {
                showResultFrag()
            } else {
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showResultFrag() {
        if (childFragmentManager.findFragmentByTag(TAG_SEARCH) == null) {
            val dlg = ResultFragment()
            dlg.show(childFragmentManager, TAG_SEARCH)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                viewModelMain.changeDate(DateUtils.today())
                caseCalendar?.visibility = View.GONE
            }
            1 -> {
                viewModelMain.changeDate(DateUtils.tomorrow())
                caseCalendar?.visibility = View.GONE
            }
            2 -> {
                caseCalendar?.visibility = View.VISIBLE
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // TODO("Not yet implemented")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // TODO("Not yet implemented")
    }

    private fun isNeedPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    RecordAudioRequestCode
                )
            }
            return true
        }
        return false
    }
}