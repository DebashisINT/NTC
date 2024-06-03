package com.trackerbreeze.features.weather.api

import com.trackerbreeze.features.task.api.TaskApi
import com.trackerbreeze.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}