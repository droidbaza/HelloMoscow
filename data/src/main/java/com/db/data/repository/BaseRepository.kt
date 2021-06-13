package com.db.data.repository

import android.util.Log
import com.rusatom.domain.LoadResult
import retrofit2.Response
import java.lang.Exception

object BaseRepository {

    inline fun <T : Any?> safeCall(responseCall: () -> Response<T>): LoadResult<T> {
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            val body = response.body()
            Log.d("RESSSS","${response.message()}")
            if (response.isSuccessful) {
                LoadResult.Success(body)
            } else {
                Log.d("RESSSS","${response.message()}")
                LoadResult.Error(responseCode, response.message())
            }
        } catch (e:Exception) {
            Log.d("RESSSS","${e.message}")
            LoadResult.Error(500)
        }
    }
}