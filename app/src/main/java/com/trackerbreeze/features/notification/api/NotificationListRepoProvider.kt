package com.trackerbreeze.features.notification.api

/**
 * Created by Saikat on 06-03-2019.
 */
object NotificationListRepoProvider {
    fun notificationListRepository(): NotificationListRepo {
        return NotificationListRepo(NotificationListApi.create())
    }
}