package com.ntcv4tracker.features.task.api

import com.ntcv4tracker.features.timesheet.api.TimeSheetApi
import com.ntcv4tracker.features.timesheet.api.TimeSheetRepo

/**
 * Created by Saikat on 12-Aug-20.
 */
object TaskRepoProvider {
    fun taskRepoProvider(): TaskRepo {
        return TaskRepo(TaskApi.create())
    }
}