package com.ntcv4tracker.app.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ntcv4tracker.features.viewAllOrder.orderOptimized.CommonProductCatagory

@Dao
interface SchedulerMasterDao {
    @Insert
    fun insert(vararg obj: SchedulerMasterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(kist: List<SchedulerMasterEntity>)

    @Query("select * from crm_scheduler_master")
    fun getAll(): List<SchedulerMasterEntity>

    @Query("delete from crm_scheduler_master")
    fun deleteAll()

    /*@Query("select * from crm_scheduler_master where select_date=:select_date and isActivityDone=:isActivityDone")
    fun getSchedulerByDate(select_date:String , isActivityDone:Boolean): List<SchedulerMasterEntity>*/

    /*@Query("update crm_scheduler_master set isActivityDone =:isActivityDone where select_contact_id=:select_contact_id \n" +
            "and select_timestamp=:select_timestamp \n" +
            "and save_date_time=:save_date_time")
    fun updateSchedulerSucess(select_contact_id:String,select_timestamp:String,save_date_time:String,isActivityDone:Boolean)*/

    @Query("select * from crm_scheduler_master_date_time where select_date =:currentDate \n" +
            "and  :currentTimeSt > select_timestamp and isDone =:isDone and scheduler_id in \n" +
            "(select scheduler_id from crm_scheduler_master where isAutoMail = :isAutoMail) order by select_time asc")
    fun getTimeStampToSent(currentDate:String , isDone:Boolean, isAutoMail:Boolean,currentTimeSt:String): List<SchedulerDateTimeEntity>

    @Query("update crm_scheduler_master_date_time set isDone =:isDone where scheduler_id=:scheduler_id and select_timestamp=:select_timestamp")
    fun updateSchedulerSucess1(scheduler_id:String,select_timestamp:String,isDone:Boolean)

    @Query("select * from crm_scheduler_master where scheduler_id=:scheduler_id")
    fun getSchedulerDtls(scheduler_id:String): SchedulerMasterEntity

}