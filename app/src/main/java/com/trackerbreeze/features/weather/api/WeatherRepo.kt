package com.trackerbreeze.features.weather.api

import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.task.api.TaskApi
import com.trackerbreeze.features.task.model.AddTaskInputModel
import com.trackerbreeze.features.weather.model.ForeCastAPIResponse
import com.trackerbreeze.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}