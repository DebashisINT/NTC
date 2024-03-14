package com.ntcv4tracker.features.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ntcv4tracker.MultiFun
import com.ntcv4tracker.R
import com.ntcv4tracker.app.AppDatabase
import com.ntcv4tracker.app.Pref
import com.ntcv4tracker.app.domain.AddShopDBModelEntity
import com.ntcv4tracker.app.domain.ModeTemplateEntity
import com.ntcv4tracker.app.domain.RuleTemplateEntity
import com.ntcv4tracker.app.domain.ScheduleTemplateEntity
import com.ntcv4tracker.app.domain.SchedulerContactEntity
import com.ntcv4tracker.app.domain.SchedulerDateTimeEntity
import com.ntcv4tracker.app.domain.SchedulerMasterEntity
import com.ntcv4tracker.app.utils.AppUtils
import com.ntcv4tracker.app.utils.PermissionUtils
import com.ntcv4tracker.app.utils.ProcessImageUtils_v1
import com.ntcv4tracker.app.utils.Toaster
import com.ntcv4tracker.base.presentation.BaseFragment
import com.ntcv4tracker.features.dashboard.presentation.DashboardActivity
import com.ntcv4tracker.features.reimbursement.presentation.FullImageDialog
import com.ntcv4tracker.widgets.AppCustomEditText
import com.ntcv4tracker.widgets.AppCustomTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pnikosis.materialishprogress.ProgressWheel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.themechangeapp.pickimage.PermissionHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale
import java.util.Random


class SchedulerAddFormFrag : BaseFragment(), View.OnClickListener {

    private var timestamp: Long? = 0
    private var hr: String=""
    private var min: String=""
    private var particular_date_select: String=""
    private var contactTickL: ArrayList<ScheduleContactDtls> = ArrayList()
    private var selectedHr: String=""
    private var selectedMin: String=""
    private var str_templateID:String = Pref.user_id!!
    private var str_modeoftemplateID:String = Pref.user_id!!
    private var str_ruleoftemplateID:String = Pref.user_id!!
    private var str_contactoftemplate:String = Pref.user_id!!
    private var sortBy:String = ""
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var mContext: Context
    private var filePath = ""
    private var permissionUtils: PermissionUtils? = null
    private lateinit var hour_numPicker:NumberPicker
    private lateinit var min_numPicker:NumberPicker
    private lateinit var tv_selectedTime:TextView
    private lateinit var iv_add_schedular_form_attachment1:ImageView
    private lateinit var iv_add_schedular_form_attachment2:ImageView
    private lateinit var iv_add_schedular_form_attachment3:ImageView
    private lateinit var schedulername: TextInputEditText
    private lateinit var selectTemplate: TextInputEditText
    private lateinit var et_templateContent: TextInputEditText
    private lateinit var tv_rule_Of_scheduler: TextInputEditText
    private lateinit var selectContactSchedule: TextInputEditText
    private lateinit var selectMode: TextInputEditText
    private lateinit var calendarView : MaterialCalendarView
    private lateinit var cv_calendar : CardView
    private lateinit var sw_repeatmonth : Switch
    private lateinit var adapterScheduleContactName:AdapterScheduleContactName
    private lateinit var iv_template : LinearLayout
    private lateinit var textInLaySelectMode : TextInputLayout
    private lateinit var textInLaySelectContact : TextInputLayout
    private lateinit var textInLaySelectRule : TextInputLayout
    private lateinit var iv_mode_template : LinearLayout
    private lateinit var iv_rule_Of_scheduler_dropdown : LinearLayout
    private lateinit var iv_frag_cont_add_schedule_to : LinearLayout
    private lateinit var ll_frag_sch_add_template_root : LinearLayout
    private lateinit var cv_frag_Shceduler_add_submit : CardView
    private lateinit var cv_scheduler_timer : CardView

    private var contL :ArrayList<AddShopDBModelEntity> = ArrayList()
    private var finalL: ArrayList<ScheduleContactDtls> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    companion object{
        fun getInstance(objects: Any): SchedulerAddFormFrag {
            val objFragment = SchedulerAddFormFrag()
            var obj = objects as String
            return objFragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_scheduler_add_form, container, false)
        initView(view)
        return view
    }
    private fun initView(view: View) {
        progress_wheel = view.findViewById(R.id.progress_wheel_frag_add_cont)
        cv_calendar = view.findViewById(R.id.cv_calendar)
        hour_numPicker = view.findViewById(R.id.np_frag_schedule_add_form_hr)
        iv_add_schedular_form_attachment1 = view.findViewById(R.id.iv_add_schedular_form_attachment1)
        iv_add_schedular_form_attachment2 = view.findViewById(R.id.iv_add_schedular_form_attachment2)
        iv_add_schedular_form_attachment3 = view.findViewById(R.id.iv_add_schedular_form_attachment3)
        sw_repeatmonth = view.findViewById(R.id.sw_repeatmonth)
        schedulername = view.findViewById(R.id.et_add_form_schedule_name)
        selectTemplate = view.findViewById(R.id.tv_select_template)
        et_templateContent = view.findViewById(R.id.et_templateContent)
        min_numPicker = view.findViewById(R.id.np_frag_schedule_add_form_min)
        tv_selectedTime = view.findViewById(R.id.tv_frag_schedule_add_form_selected_time)
        calendarView = view.findViewById(R.id.calendarView_frag_schedule_calendar)
        selectMode = view.findViewById(R.id.tv_select_mode_template)
        selectContactSchedule = view.findViewById(R.id.tv_frag_cont_add_contact_to_schedule)
        iv_mode_template = view.findViewById(R.id.iv_select_mode_template)
        tv_rule_Of_scheduler = view.findViewById(R.id.tv_rule_Of_scheduler)
        iv_rule_Of_scheduler_dropdown = view.findViewById(R.id.iv_rule_Of_scheduler_dropdown)
        ll_frag_sch_add_template_root = view.findViewById(R.id.ll_frag_sch_add_template_root)
        iv_frag_cont_add_schedule_to = view.findViewById(R.id.iv_frag_cont_add_schedule_to)
        cv_frag_Shceduler_add_submit = view.findViewById(R.id.cv_frag_Shceduler_add_submit)
        iv_template = view.findViewById(R.id.iv_frag_sched_add_form_template_dropDown)
        textInLaySelectContact = view.findViewById(R.id.textInLaySelectContact)
        textInLaySelectRule = view.findViewById(R.id.textInLaySelectRule)
        textInLaySelectMode = view.findViewById(R.id.textInLaySelectMode)
        cv_scheduler_timer = view.findViewById(R.id.cv_scheduler_timer)
        cv_scheduler_timer.visibility = View.GONE

        iv_template.setOnClickListener(this)
        selectContactSchedule.setOnClickListener(this)
        textInLaySelectMode.setOnClickListener(this)
        textInLaySelectRule.setOnClickListener(this)
        textInLaySelectContact.setOnClickListener(this)
        selectTemplate.setOnClickListener(this)
        tv_rule_Of_scheduler.setOnClickListener(this)
        iv_mode_template.setOnClickListener(this)
        selectMode.setOnClickListener(this)
        selectContactSchedule.setOnClickListener(this)
        ll_frag_sch_add_template_root.setOnClickListener(this)
        iv_rule_Of_scheduler_dropdown.setOnClickListener(this)
        iv_frag_cont_add_schedule_to.setOnClickListener(this)
        cv_frag_Shceduler_add_submit.setOnClickListener(this)

        contL = ArrayList()
        finalL = ArrayList()
        contactTickL = ArrayList()
        contL = AppDatabase.getDBInstance()!!.addShopEntryDao().getContatcShopsByAddedDate() as ArrayList<AddShopDBModelEntity>
        for(i in 0..contL.size-1){
            finalL.add(ScheduleContactDtls(contL.get(i).shopName,contL.get(i).ownerContactNumber,contL.get(i).shop_id,false))
        }

       // setCal()
        setTestData()
        setModeData()
        setRuleData()

        iv_add_schedular_form_attachment1.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                initPermissionCheck()
            else {
                showPictureDialog()
            }
        }

        cv_scheduler_timer.visibility = View.GONE
        cv_calendar.visibility = View.GONE
        sw_repeatmonth.visibility = View.GONE

    }

    private fun showPictureDialog() {
         val pictureDialog = AlertDialog.Builder(mContext)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Select photo from gallery", "Capture Image", "Select file from file manager")
            pictureDialog.setItems(pictureDialogItems,
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        0 -> selectImageInAlbum()
                        1 -> {
                            //(mContext as DashboardActivity).openFileManager()
                            launchCamera()
                        }
                        2 -> {
                            (mContext as DashboardActivity).openFileManager()
                           // openFile()
                        }
                    }
                })
            pictureDialog.show()
        }

    fun openFile() {
        val mimeTypes = arrayOf(
            "application/pdf" //.pdf
        )
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, mimeTypes)
        }

        startActivityForResult(intent, 2001)
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2001){
            try{
                var f = data
                var ff = data!!.data!!.path
                var customFinalPath = ff!!.substring(ff!!.indexOf(":") + 1);
               // Pref.scheduler_file = customFinalPath
            }catch (ex:Exception){
                ex.printStackTrace()
            }
        }
    }
*/

    private fun launchCamera() {
        (mContext as DashboardActivity).captureImage()
    }
    private fun selectImageInAlbum() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        (mContext as DashboardActivity).startActivityForResult(galleryIntent, PermissionHelper.REQUEST_CODE_STORAGE)
    }

    private fun initPermissionCheck() {

        //begin mantis id 26741 Storage permission updation Suman 22-08-2023
        var permissionList = arrayOf<String>( Manifest.permission.CAMERA)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissionList += Manifest.permission.READ_MEDIA_IMAGES
            permissionList += Manifest.permission.READ_MEDIA_AUDIO
            permissionList += Manifest.permission.READ_MEDIA_VIDEO
        }else{
            permissionList += Manifest.permission.WRITE_EXTERNAL_STORAGE
            permissionList += Manifest.permission.READ_EXTERNAL_STORAGE
        }
//end mantis id 26741 Storage permission updation Suman 22-08-2023

        permissionUtils = PermissionUtils(mContext as Activity, object : PermissionUtils.OnPermissionListener {
            override fun onPermissionGranted() {
                showPictureDialog()
            }

            override fun onPermissionNotGranted() {
                (mContext as DashboardActivity).showSnackMessage(getString(R.string.accept_permission))
            }

        },permissionList)// arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }

    private fun setCal(){
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE)

        calendarView.state().edit()
            .setMinimumDate(CalendarDay.today())
            //.setMaximumDate(CalendarDay.today())
            .commit()
        hour_numPicker.minValue = 0
        hour_numPicker.maxValue = 23
        min_numPicker.minValue = 0
        min_numPicker.maxValue = 59
        var hrL = Array<String>(24) { "" }
        for(i in 0..23){
            hrL[i] = "${i} h"
        }
        var minL = Array<String>(60) { "" }
        for(i in 0..59){
            minL[i] = "${i} min"
        }
        val currentHour = LocalTime.now().hour
        val currentMinute = LocalTime.now().minute
        val adjustedHour = currentHour % 24
       // val adjustedMinute = currentMinute

        hour_numPicker.value = adjustedHour
        min_numPicker.value = currentMinute
        hour_numPicker.displayedValues = hrL
        min_numPicker.displayedValues =minL

        selectedHr = adjustedHour.toString()+" h"
        selectedMin = currentMinute.toString()+" min"


        tv_selectedTime.text = adjustedHour.toString()+"h"+" "+ currentMinute.toString()+"min"


        hour_numPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
            override fun onValueChange(numberPicker: NumberPicker, i: Int, i2: Int) {
                try{
                    selectedHr = hrL[i2].toString()
                    tv_selectedTime.text = selectedHr+" "+ selectedMin

                }catch (ex:Exception){
                    ex.printStackTrace()
                    println("tag_picker_err ${ex.message}")
                }
            }
        })
        min_numPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
            override fun onValueChange(numberPicker: NumberPicker, i: Int, i2: Int) {
                try{
                    selectedMin = minL[i2].toString()
                    tv_selectedTime.text =selectedHr+" "+ selectedMin

                }catch (ex:Exception){
                    ex.printStackTrace()
                    println("tag_picker_err ${ex.message}")
                }
            }
        })
    }
    private fun setTestData(){
        if((AppDatabase.getDBInstance()?.scheduleTemplateDao()?.getAll() as ArrayList<ScheduleTemplateEntity>).size == 0){

            var obj = ScheduleTemplateEntity()
            obj.template_id = 1
            obj.template_name = "Auto"

            var obj1 = ScheduleTemplateEntity()
            obj1.template_id = 2
            obj1.template_name = "Manual"

            AppDatabase.getDBInstance()?.scheduleTemplateDao()?.insert(obj)
            AppDatabase.getDBInstance()?.scheduleTemplateDao()?.insert(obj1)
        }
    }
    private fun setModeData(){

        if((AppDatabase.getDBInstance()?.modeTemplateDao()?.getAll() as ArrayList<ModeTemplateEntity>).size == 0){
            var objMode1 = ModeTemplateEntity()
            objMode1.mode_template_id = 1
            objMode1.mode_template_name = "WhatsApp"

            var objMode2 = ModeTemplateEntity()
            objMode2.mode_template_id = 2
            objMode2.mode_template_name = "Email"

            AppDatabase.getDBInstance()?.modeTemplateDao()?.insert(objMode1)
            AppDatabase.getDBInstance()?.modeTemplateDao()?.insert(objMode2)
        }


    }
    private fun setRuleData(){
        if((AppDatabase.getDBInstance()?.ruleTemplateDao()?.getAll() as ArrayList<RuleTemplateEntity>).size == 0){
            var objRule1 = RuleTemplateEntity()
            objRule1.rule_template_id = 1
            objRule1.rule_template_name = "Auto"

            var objRule2 = RuleTemplateEntity()
            objRule2.rule_template_id = 2
            objRule2.rule_template_name = "Manual"

            AppDatabase.getDBInstance()?.ruleTemplateDao()?.insert(objRule1)
            AppDatabase.getDBInstance()?.ruleTemplateDao()?.insert(objRule2)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_frag_sched_add_form_template_dropDown , R.id.tv_select_template->{
                if((AppDatabase.getDBInstance()?.scheduleTemplateDao()?.getAll() as ArrayList<ScheduleTemplateEntity>).size>0){
                    loadTemplateList(AppDatabase.getDBInstance()?.scheduleTemplateDao()?.getAll() as ArrayList<ScheduleTemplateEntity>)
                }else{
                    Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.iv_select_mode_template, R.id.tv_select_mode_template->{
                if((AppDatabase.getDBInstance()?.modeTemplateDao()?.getAll() as ArrayList<ModeTemplateEntity>).size>0){
                    loadModeOfTemplateList(AppDatabase.getDBInstance()?.modeTemplateDao()?.getAll() as ArrayList<ModeTemplateEntity>)
                }else{
                    Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.iv_rule_Of_scheduler_dropdown , R.id.tv_rule_Of_scheduler->{
                if((AppDatabase.getDBInstance()?.modeTemplateDao()?.getAll() as ArrayList<ModeTemplateEntity>).size>0){
                    loadRuleOfTemplateList(AppDatabase.getDBInstance()?.ruleTemplateDao()?.getAll() as ArrayList<RuleTemplateEntity>)
                }else{
                    Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.iv_frag_cont_add_schedule_to , R.id.tv_frag_cont_add_contact_to_schedule->{
                if(contL.size >0){
                    loadContactOfTemplateList()
                } else{
                    Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cv_frag_Shceduler_add_submit ->{
                AppUtils.hideSoftKeyboard(mContext as DashboardActivity)
                    submitValidationCheck()

               /* try {
                    val time = System.currentTimeMillis()
                    var fileName = "Contact" +  "_" + time
                     val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/FSMApp/CallHis/"
                    var sendingPath = "/storage/emulated/0/"+Pref.scheduler_file

                    var m = Mail()
                    var toArr = arrayOf("")
                    m = Mail("suman.bachar@indusnet.co.in", "jfekuhstltfkarrv") // generate under 2-step verification -> app password
                    toArr = arrayOf("suman.bachar@indusnet.co.in","puja.basak@indusnet.co.in")
                    m.setTo(toArr)
                    m.setFrom("TEAM");
                    m.setSubject("Contact Activity.")
                    m.setBody("Hello ,  \n Please find attachment below. \n\n\n Regards \n${Pref.user_name}.")

                    doAsync {
                        try{
                            val fileUrl = Uri.parse(sendingPath)
                            m.send(fileUrl.path)

                        }catch (ex:Exception){
                            ex.printStackTrace()
                            println("tag_file_automain error ${ex.message}")
                        }
                        uiThread {
                            println("tag_file_automain success")
                        }
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }*/

            }
        }
    }
    private fun submitValidationCheck() {

        Pref.scheduler_template=
            et_templateContent.text.toString().trim()+"\n" +
                    "\n" +
                    "\n" +
                    " Regards\n" +
                    "${Pref.user_name}"
        progress_wheel.spin()

        if(schedulername.text.toString().length==0 || schedulername.text.toString().trim().equals("")){

            schedulername.requestFocus()
            schedulername.setError("Enter Scheduler Name")
            progress_wheel.stopSpinning()
            return
        }
        if(selectTemplate.text.toString().length==0 || selectTemplate.text.toString().trim().equals("")){
            (mContext as DashboardActivity).showSnackMessage("Select a template.")
            progress_wheel.stopSpinning()
            return
        }
        if(et_templateContent.text.toString().length==0 || et_templateContent.text.toString().trim().equals("") && et_templateContent.isEnabled==true){
            (mContext as DashboardActivity).showSnackMessage("Write template content")
            progress_wheel.stopSpinning()
            return
        }
        if(selectMode.text.toString().length==0 || selectMode.text.toString().trim().equals("")){
            (mContext as DashboardActivity).showSnackMessage("Select a mode")
            progress_wheel.stopSpinning()
            return
        }
        if(tv_rule_Of_scheduler.text.toString().length==0 || tv_rule_Of_scheduler.text.toString().trim().equals("")){
            (mContext as DashboardActivity).showSnackMessage("Select a rule")
            progress_wheel.stopSpinning()
            return
        }

       if (calendarView.selectedDates.size==0 &&  tv_rule_Of_scheduler.text.toString().trim().equals("Auto")){
           (mContext as DashboardActivity).showSnackMessage("Select a date")
           progress_wheel.stopSpinning()
            return
        }
        if(selectContactSchedule.text.toString().length==0 || selectContactSchedule.text.toString().trim().equals("")){
            (mContext as DashboardActivity).showSnackMessage("Select a contact")
            progress_wheel.stopSpinning()
            return
        }

        var curentTimeStamp = System.currentTimeMillis()

        for(j in 0..calendarView.selectedDates.size-1){
            particular_date_select = calendarView.selectedDates.get(j).year.toString() + "-" + String.format("%02d", calendarView.selectedDates.get(j).month.toInt()
            ) + "-" + String.format("%02d", calendarView.selectedDates.get(j).day.toInt())
            if (tv_rule_Of_scheduler.text.toString().equals("Auto")) {
                hr = String.format("%02d", selectedHr.replace(" h", "").toInt())
                min = String.format("%02d", selectedMin.replace(" min", "").toInt())

                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val date = simpleDateFormat.parse(particular_date_select + " $hr:$min:00")
                timestamp = date?.time
            }
        }
        if(timestamp!! <= curentTimeStamp && tv_rule_Of_scheduler.text.toString().equals("Auto")){
            (mContext as DashboardActivity).showSnackMessage("Set a schedule time after current time")
            progress_wheel.stopSpinning()

        }else {
            try {

                doAsync {
                    //new code begin
                    val random = Random()
                    var schObj = SchedulerMasterEntity()
                    schObj.scheduler_id = Pref.user_id + "_" + System.currentTimeMillis().toString() +  (random.nextInt(999 - 100) + 100).toString()
                    schObj.scheduler_name = schedulername.text.toString().trim()
                    schObj.select_template = selectTemplate.text.toString().trim()
                    schObj.template_content = et_templateContent.text.toString().trim()
                    schObj.select_mode_id = str_modeoftemplateID
                    schObj.select_mode = selectMode.text.toString().trim()
                    schObj.select_rule_id = str_ruleoftemplateID
                    schObj.select_rule = tv_rule_Of_scheduler.text.toString().trim()

                    schObj.save_date_time = AppUtils.getCurrentDateTimeNew()
                    schObj.isUploaded = true
                    if (schObj.select_rule.equals("Manual", ignoreCase = true)) {
                        schObj.isAutoMail = false
                    }else{
                        schObj.isAutoMail = true
                    }

                    var dateTimeL : ArrayList<SchedulerDateTimeEntity> = ArrayList()
                    if (schObj.select_rule.equals("Auto", ignoreCase = true)) {
                        hr = String.format("%02d", selectedHr.replace(" h","").toInt())
                        min = String.format("%02d", selectedMin.replace(" min","").toInt())

                        for(j in 0..calendarView.selectedDates.size-1){
                            var dateTimeObj = SchedulerDateTimeEntity()
                            dateTimeObj.scheduler_id = schObj.scheduler_id
                            dateTimeObj.select_hour = selectedHr
                            dateTimeObj.select_minute = selectedMin
                            dateTimeObj.select_time = hr + ":" + min + ":00"

                            var y = calendarView.selectedDates.get(j).year.toString()
                            var m = String.format("%02d", calendarView.selectedDates.get(j).month.toInt())
                            var d = String.format("%02d", calendarView.selectedDates.get(j).day.toInt())
                            var dt =  y+ "-" + m + "-" + d
                            dateTimeObj.select_date = dt

                            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                            val date = simpleDateFormat.parse(dt + " $hr:$min:00")
                            val timestamp = date?.time

                            dateTimeObj.select_timestamp = timestamp.toString()
                            dateTimeL.add(dateTimeObj)
                        }
                    }
                    var contL : ArrayList<SchedulerContactEntity> = ArrayList()
                    for (j in 0..contactTickL.size - 1) {
                        contL.add(SchedulerContactEntity(scheduler_id =schObj.scheduler_id, select_contact_id = contactTickL.get(j).contact_id,
                            select_contact = contactTickL.get(j).contact_name, select_contact_number = contactTickL.get(j).contact_number))
                    }
                    AppDatabase.getDBInstance()!!.schedulerMasterDao().insert(schObj)
                    if(dateTimeL.size>0)
                        AppDatabase.getDBInstance()!!.schedulerDateTimeDao().insertAll(dateTimeL)
                    if(contL.size>0)
                        AppDatabase.getDBInstance()!!.schedulerContactDao().insertAll(contL)
                    //new code end

                    /*var schedulerObj = SchedulerMasterEntity()
                    schedulerObj.scheduler_name = schedulername.text.toString().trim()
                    schedulerObj.select_template = selectTemplate.text.toString().trim()
                    schedulerObj.template_content = et_templateContent.text.toString().trim()
                    schedulerObj.select_mode_id = str_modeoftemplateID
                    schedulerObj.select_mode = selectMode.text.toString().trim()
                    schedulerObj.select_rule_id = str_ruleoftemplateID
                    schedulerObj.select_rule = tv_rule_Of_scheduler.text.toString().trim()
                    if (schedulerObj.select_rule.equals("Auto", ignoreCase = true)) {
                        hr = String.format("%02d", selectedHr.replace(" h","").toInt())
                        min = String.format("%02d", selectedMin.replace(" min","").toInt())
                        schedulerObj.select_hour = selectedHr
                        schedulerObj.select_minute = selectedMin
                        schedulerObj.select_time = hr + ":" + min + ":00"
                    }else{
                        schedulerObj.select_hour = ""
                        schedulerObj.select_minute = ""
                        schedulerObj.select_time = "00" + ":" + "00" + ":00"
                    }
                    schedulerObj.repeat_every_month = false
                    //  schedulerObj.select_contact = selectContactSchedule.text.toString().trim()
                    schedulerObj.isUploaded = true
                    schedulerObj.save_date_time = AppUtils.getCurrentDateTimeNew()
                    if (schedulerObj.select_rule.equals("Manual", ignoreCase = true)) {
                        schedulerObj.isActivityDone = true
                    } else {
                        schedulerObj.isActivityDone = false
                    }
                    var addedContact_idL: ArrayList<String> = ArrayList()

                    for (i in 0..contactTickL.size - 1) {
                        schedulerObj.select_contact_id = contactTickL.get(i).contact_id
                        schedulerObj.select_contact = contactTickL.get(i).contact_name

                        if (calendarView.selectedDates.size == 0) {
                            schedulerObj.select_timestamp = ""
                            schedulerObj.select_date = ""
                            AppDatabase.getDBInstance()!!.schedulerMasterDao().insert(schedulerObj)
                        } else {
                            for (j in 0..calendarView.selectedDates.size - 1) {
                                particular_date_select = calendarView.selectedDates.get(j).year.toString() + "-" + String.format(
                                        "%02d", calendarView.selectedDates.get(j).month.toInt()
                                    ) + "-" + String.format(
                                        "%02d",
                                        calendarView.selectedDates.get(j).day.toInt()
                                    )
                                schedulerObj.select_date = particular_date_select

                                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                val date = simpleDateFormat.parse(particular_date_select + " $hr:$min:00")
                                val timestamp = date?.time
                                schedulerObj.select_timestamp = timestamp.toString()
                                AppDatabase.getDBInstance()!!.schedulerMasterDao()
                                    .insert(schedulerObj)
                            }
                        }
                        addedContact_idL.add(schedulerObj.select_contact_id)
                    }*/

                    uiThread {
                        val simpleDialog = Dialog(mContext)
                        simpleDialog.setCancelable(false)
                        simpleDialog.getWindow()!!
                            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        simpleDialog.setContentView(R.layout.dialog_ok)
                        val dialogHeader =
                            simpleDialog.findViewById(R.id.dialog_yes_header_TV) as AppCustomTextView
                        dialogHeader.text = "Shceduler added successfully"
                        val dialogYes =
                            simpleDialog.findViewById(R.id.tv_dialog_yes) as AppCustomTextView
                        dialogYes.setOnClickListener({ view ->
                            simpleDialog.cancel()
                        })
                        simpleDialog.show()

                        //new code begin
                        if(schObj.isAutoMail == false){
                            for (l in 0..contL.size - 1) {
                                var shopObj = AppDatabase.getDBInstance()!!.addShopEntryDao().getShopByIdN(contL.get(l).select_contact_id)
                                MultiFun.autoMailScheduler(shopObj.ownerEmailId, schObj.template_content,shopObj)
                            }
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).onBackPressed()
                        }else{
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).onBackPressed()
                        }
                        //new code end

                        /*if (schedulerObj.select_rule.equals("Manual", ignoreCase = true)) {
                            for (l in 0..addedContact_idL.size - 1) {
                                var shopObj = AppDatabase.getDBInstance()!!.addShopEntryDao().getShopByIdN(addedContact_idL.get(l))
                                MultiFun.generateContactDtlsPdf(shopObj, mContext)
                            }
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).onBackPressed()
                        } else {
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).onBackPressed()
                        }*/
                    }
                }

            }
            catch (e: Exception) {
                progress_wheel.stopSpinning()
                e.printStackTrace()
            }
        }
    }
    private fun loadTemplateList(schedule_list:ArrayList<ScheduleTemplateEntity>) {
        if (schedule_list.size>0) {
            var genericL : ArrayList<CustomData> = ArrayList()
            for(i in 0..schedule_list.size-1){
                genericL.add(CustomData(schedule_list.get(i).template_id.toString(),schedule_list.get(i).template_name.toString()))
            }
            GenericDialog.newInstance("Template",genericL as ArrayList<CustomData>){
                selectTemplate.setText(it.name)
                str_templateID = it.id
                if (!selectTemplate.text.toString().equals("Manual")){
                    et_templateContent.isEnabled = false
                    Pref.scheduler_template=  "Hello @name"+"\n" +
                            "\n" +
                            "\n"
                    et_templateContent.setText(Pref.scheduler_template)
                    et_templateContent.setTextColor(context?.getResources()!!.getColor(R.color.gray))
                }else{
                    et_templateContent.isEnabled =true
                    et_templateContent.setText("")
                }

            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        } else {
            Toaster.msgShort(mContext, "No Template Found")
        }
    }
    private fun loadModeOfTemplateList(mode_list:ArrayList<ModeTemplateEntity>) {
        if (mode_list.size>0) {
            var genericL : ArrayList<CustomData> = ArrayList()
            for(i in 0..mode_list.size-1){
                genericL.add(CustomData(mode_list.get(i).mode_template_id.toString(),mode_list.get(i).mode_template_name.toString()))
            }
            GenericDialog.newInstance("Mode of Template",genericL as ArrayList<CustomData>){
                selectMode.setText(it.name)
                str_modeoftemplateID = it.id
            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        } else {
            Toaster.msgShort(mContext, "No Mode Found")
        }

    }
    private fun loadRuleOfTemplateList(rule_list:ArrayList<RuleTemplateEntity>) {
        if (rule_list.size>0) {
            var genericL : ArrayList<CustomData> = ArrayList()
            for(i in 0..rule_list.size-1){
                genericL.add(CustomData(rule_list.get(i).rule_template_id.toString(),rule_list.get(i).rule_template_name.toString()))
            }
            GenericDialog.newInstance("Rule of Template",genericL as ArrayList<CustomData>){
                tv_rule_Of_scheduler.setText(it.name)
                str_ruleoftemplateID = it.id
                if (tv_rule_Of_scheduler.text.toString().equals("Manual")){
                    cv_scheduler_timer.visibility = View.GONE
                    cv_calendar.visibility = View.GONE
                    sw_repeatmonth.visibility = View.GONE

                }else{

                    cv_scheduler_timer.visibility = View.VISIBLE
                    cv_calendar.visibility = View.VISIBLE
                    sw_repeatmonth.visibility = View.GONE
                    setCal()
                    calendarView.clearSelection()


                }
            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        } else {
            Toaster.msgShort(mContext, "No Rule Found")
        }
    }
@SuppressLint("SuspiciousIndentation")
private fun loadContactOfTemplateList() {

            val contactDialog = Dialog(mContext)
            contactDialog.setCancelable(true)
            contactDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            contactDialog.setContentView(R.layout.dialog_cont_select)
            val rvContactL = contactDialog.findViewById(R.id.rv_dialog_cont_list) as RecyclerView
            val tvHeader = contactDialog.findViewById(R.id.tv_dialog_cont_sel_header) as TextView
            val submit = contactDialog.findViewById(R.id.tv_dialog_cont_list_submit) as TextView
            val et_contactNameSearch =
                contactDialog.findViewById(R.id.et_dialog_contact_search) as AppCustomEditText
            val cb_selectAll =
                contactDialog.findViewById(R.id.cb_dialog_cont_select_all) as CheckBox
            val iv_close =
                contactDialog.findViewById(R.id.iv_dialog_generic_list_close_icon) as ImageView
            tvHeader.text = if(contactTickL.size == 0) "Select Contact(s)" else "Selected Contact(s) : (${contactTickL.size})"
            iv_close.setOnClickListener {
                contactDialog.dismiss()
            }

        //contactTickL = ArrayList()

        adapterScheduleContactName = AdapterScheduleContactName(mContext, finalL, object : AdapterScheduleContactName.onClick {
            override fun onTickUntick(obj: ScheduleContactDtls, isTick: Boolean) {
                if (isTick) {
                    contactTickL.add(obj)
                    finalL.filter { it.contact_name.equals(obj.contact_name) }.first().isTick = true
                    finalL.filter { it.contact_id.equals(obj.contact_id) }.first().isTick = true

                    tvHeader.text = "Selected Contact(s) : (${contactTickL.size})"


                } else {
                    contactTickL.remove(obj)
                    finalL.filter { it.contact_name.equals(obj.contact_name) }.first().isTick = false
                    tvHeader.text = "Selected Contact(s) : (${contactTickL.size})"
                }
            }
        }, {
            it
                    })
                cb_selectAll.setOnCheckedChangeListener { compoundButton, b ->
                    if (compoundButton.isChecked) {
                        adapterScheduleContactName.selectAll()
                        cb_selectAll.setText("Deselect All")
                    } else {
                        adapterScheduleContactName.deselectAll()
                        cb_selectAll.setText("Select All")
                    }
                }

                et_contactNameSearch.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        adapterScheduleContactName!!.getFilter()
                            .filter(et_contactNameSearch.text.toString().trim())
                    }
                })
                submit.setOnClickListener {
                    if (contactTickL.size > 0) {
                        contactDialog.dismiss()
                        var nameText = ""
                        if(contactTickL.size==1){
                            nameText = contactTickL.get(0).contact_name
                        }else{
                            for(i in 0..contactTickL.size-1) {
                                nameText = nameText+contactTickL.get(i).contact_name+","
                            }
                        }
                        if (nameText.endsWith(",")) {
                            nameText = nameText.substring(0, nameText.length - 1);
                        }
                        selectContactSchedule.setText(nameText)
                    } else {
                        contactDialog.dismiss()
                        selectContactSchedule.setText("")
                        //Toaster.msgShort(mContext, "Select Contact(s)")
                    }
                }
                rvContactL.adapter = adapterScheduleContactName
                contactDialog.show()

    }

    fun setImage(filePath: String) {
        val file = File(filePath)
        FullImageDialog.getInstance(file.path).show((mContext as DashboardActivity).supportFragmentManager, "")

        var newFile: File? = null

        progress_wheel.spin()
        doAsync {

            val processImage = ProcessImageUtils_v1(mContext, file, 50, true)
            newFile = processImage.ProcessImage()

            uiThread {
                if (newFile != null) {
                    Timber.e("=========Image from new technique==========")
                    documentPic(newFile!!.length(), newFile?.absolutePath!!)
                } else {
                    // Image compression
                    val fileSize = AppUtils.getCompressImage(filePath)
                    documentPic(fileSize, filePath)
                }
            }
        }
    }

    private fun documentPic(fileSize: Long, file_path: String) {
        val fileSizeInKB = fileSize / 1024
        Log.e("Add Document", "image file size after compression=====> $fileSizeInKB KB")

        progress_wheel.stopSpinning()

        if (!TextUtils.isEmpty(Pref.maxFileSize)) {
            if (fileSizeInKB > Pref.maxFileSize.toInt()) {
                (mContext as DashboardActivity).showSnackMessage("More than " + Pref.maxFileSize + " KB file is not allowed")
                return
            }
        }

        filePath = file_path
    }
}