package com.elliewu.taoyuanapp3

import com.google.gson.annotations.SerializedName


class Login_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("UserPW")var UserPW : String?
)
class Login_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class SelectWorkList_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("Date")var Date : String?,
    @SerializedName("UserID")var UserID : String?
)
class SelectWorkList_Response(
    @SerializedName("WorkList")var WorkList : List<WorkList>?
)
class WorkList(
    @SerializedName("State")var State : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("WorkTime")var WorkTime : String?
)
class RequestLocate_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("WorkTime")var WorkTime : String?
)
class RequestLocate_Response(
    @SerializedName("Locate")var Locate : List<Locate>
)
class Locate(
    @SerializedName("Longitude")var Longitude : String?,
    @SerializedName("Latitude")var Latitude : String,
    @SerializedName("LocateNumber")var LocateNumber : String?
)
class RequestLocateInfo_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("WorkTime")var WorkTime : String?,
    @SerializedName("LocateNumber")var LocateNumber : String?
)
class RequestLocateInfo_Response(
    @SerializedName("Longitude")var Longitude : String?,
    @SerializedName("Latitude")var Latitude : String?,
    @SerializedName("DateTime")var DateTime : String?,
    @SerializedName("InputContent")var InputContent : String?,
    @SerializedName("ImagePhoto")var ImagePhoto : String?
)
class LocateFormUpload_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("WorkTime")var WorkTime : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("Longitude")var Longitude : String?,
    @SerializedName("Latitude")var Latitude : String?,
    @SerializedName("InputContent")var InputContent : String?,
    @SerializedName("ImagePhoto")var ImagePhoto : String?
)
class LocateFormUpload_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class LocateFormUploadAgain_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("WorkTime")var WorkTime : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("LocateNumber")var LocateNumber : String?,
    @SerializedName("InputContent")var InputContent : String?,
    @SerializedName("ImagePhoto")var ImagePhoto : String?
)
class LocateFormUploadAgain_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class WorkInfo_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("WorkCode")var WorkCode : String?
)
class WorkInfo_Response(
    @SerializedName("State")var State : String?,
    @SerializedName("WorkTime")var WorkTime : String?,
    @SerializedName("WorkPath")var WorkPath : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("Date")var Date : String?,
    @SerializedName("UserName")var UserName : String?
)
class NewReportUpload_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("Longitude")var Longitude : String?,
    @SerializedName("Latitude")var Latitude : String?,
    @SerializedName("ReportTitle")var ReportTitle : String?,
    @SerializedName("ReportContent")var ReportContent : String?,
    @SerializedName("ReportPhoto")var ReportPhoto : String?
)
class NewReportUpload_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class RepairList_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("Date")var Date : String?,
    @SerializedName("UserID")var UserID : String?
)
class RepairList_Response(
    @SerializedName("RepairList")var RepairList : List<RepairList>?
)
class RepairList(
    @SerializedName("State")var State : String?,
    @SerializedName("RepairCode")var RepairCode : String?,
    @SerializedName("RepairTitle")var RepairTitle : String?
)
class RepairContent_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("RepairCode")var RepairCode : String?,
    @SerializedName("ReportType")var ReportType : String?
)
class RepairContent_Response(
    @SerializedName("OutsideRepair")var OutsideRepair : OutsideRepair
)
class OutsideRepair(
    @SerializedName("ReportCode")var ReportCode: String?,
    @SerializedName("RepairCode")var RepairCode : String?,
    @SerializedName("Manager")var Manager : String?,
    @SerializedName("Longitude")var Longitude : String?,
    @SerializedName("Latitude")var Latitude : String?,
    @SerializedName("RepairTitle")var RepairTitle : String?,
    @SerializedName("RepairContent")var RepairContent : String?,
    @SerializedName("RepairPhoto")var RepairPhoto : String?,
    @SerializedName("ReportTitle")var ReportTitle : String?,
    @SerializedName("ReportContent")var ReportContent : String?,
    @SerializedName("ReportPhoto")var ReportPhoto : String?,
    @SerializedName("Edit")var Edit : String?
)
class ReportList_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("Date")var Date : String?,
    @SerializedName("UserID")var UserID : String?
)
class ReportList_Response(
    @SerializedName("ReportList")var ReportList : List<ReportList>
)
class ReportList(
    @SerializedName("ReportCode")var ReportCode : String?,
    @SerializedName("State")var State : String?,
    @SerializedName("ReportTime")var ReportTime : String?,
    @SerializedName("ReportTitle")var ReportTitle : String?
)
class ReportContent_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("ReportCode")var ReportCode : String?,
    @SerializedName("ReportType")var ReportType : String?
)
class ReportContent_Response(
    @SerializedName("OutsideRepair")var OutsideRepair : OutsideRepair
)
class ReportUploadAgain_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("ReportCode")var ReportCode : String?,
    @SerializedName("ReportTitle")var ReportTitle : String?,
    @SerializedName("ReportContent")var ReportContent : String?,
    @SerializedName("ReportPhoto")var ReportPhoto : String?,
    @SerializedName("ReportType")var ReportType : String?
)
class ReportUploadAgain_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class FormRequestWork_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("WorkCode")var WorkCode : String?
)
class FormRequestWork_Response(
    @SerializedName("State")var State : String?,
    @SerializedName("FinishContent")var FinishContent : String?,
    @SerializedName("FinishPhoto")var FinishPhoto : String?
)
class FormRequestService_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("WorkCode")var WorkCode : String?
)
class FormRequestService_Response(
    @SerializedName("State")var State : String?,
    @SerializedName("RepairContent")var RepairContent : String?,
    @SerializedName("RepairPhoto")var RepairPhoto : String?,
    @SerializedName("Edit")var Edit : String?
)
class FormUploadWork_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("WorkCode")var WorkCode : String?,
    @SerializedName("State")var State : String?,
    @SerializedName("FinishContent")var FinishContent : String?,
    @SerializedName("FinishPhoto")var FinishPhoto : String?
)
class FormUploadWork_Response(
    @SerializedName("Feedback")var Feedback : String?
)
class FormUploadService_Request(
    @SerializedName("Function")var Function : String?,
    @SerializedName("UserID")var UserID : String?,
    @SerializedName("RepairCode")var RepairCode : String?,
    @SerializedName("State")var State : String?,
    @SerializedName("RepairContent")var RepairContent : String?,
    @SerializedName("RepairPhoto")var RepairPhoto : String?
)
class FormUploadService_Response(
    @SerializedName("Feedback")var Feedback : String?
)
