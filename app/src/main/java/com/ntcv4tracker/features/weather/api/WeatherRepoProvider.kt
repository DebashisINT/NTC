package com.ntcv4tracker.features.weather.api

import com.ntcv4tracker.features.task.api.TaskApi
import com.ntcv4tracker.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}