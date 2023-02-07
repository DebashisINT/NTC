package com.ntcv4tracker.features.weather.api

import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.task.api.TaskApi
import com.ntcv4tracker.features.task.model.AddTaskInputModel
import com.ntcv4tracker.features.weather.model.ForeCastAPIResponse
import com.ntcv4tracker.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}