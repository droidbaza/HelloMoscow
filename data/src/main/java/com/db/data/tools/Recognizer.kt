package com.db.data.tools

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

abstract class Recognizer : RecognitionListener {
    override fun onReadyForSpeech(bundle: Bundle) {}
    override fun onBeginningOfSpeech() {
        onRecognizeStart()
    }

    open fun onRecognizeStart() {}

    override fun onRmsChanged(v: Float) {}
    override fun onBufferReceived(bytes: ByteArray) {}
    override fun onEndOfSpeech() {}
    override fun onError(i: Int) {}
    override fun onResults(bundle: Bundle) {
        val data =
            bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        onRecognizeResult(data?.get(0))
    }

    abstract fun onRecognizeResult(words: String?)

    override fun onPartialResults(bundle: Bundle) {}
    override fun onEvent(i: Int, bundle: Bundle) {}
}