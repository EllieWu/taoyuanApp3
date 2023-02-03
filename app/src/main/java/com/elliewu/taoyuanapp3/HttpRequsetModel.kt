package com.elliewu.taoyuanapp3


class Login_Request(
    var Function : String?,
    var UserID : String?,
    var UserPW : String?
)
class Login_Response(
    var Feedback : String?
)
class SelectWorkList_Request(
    var Function : String?,
    var Date : String?,
    var UserID : String?
)
class SelectWorkList_Response(
    var WorkList : List<WorkList>
)
class WorkList(
    var State : String?,
    var WorkCode : String?,
    var WorkTime : String?
)
class RequestLocate_Request(
    var Function : String?,
    var WorkCode : String?,
    var WorkTime : String?
)
class RequestLocate_Response(
    var Locate : List<Locate>
)
class Locate(
    var Longitude : String?,
    var Latitude : String,
    var LocateNumber : String?
)
class RequestLocateInfo_Request(
    var Function : String?,
    var WorkCode : String?,
    var WorkTime : String?,
    var LocateNumber : String?
)
class RequestLocateInfo_Response(
    var Longitude : String?,
    var Latitude : String?,
    var DateTime : String?,
    var InputContent : String?,
    var ImagePhoto : String?
)
class LocateFormUpload_Request(
    var Function : String?,
    var UserID : String?,
    var WorkTime : String?,
    var WorkCode : String?,
    var Longitude : String?,
    var Latitude : String?,
    var InputContent : String?,
    var ImagePhoto : String?
)
class LocateFormUpload_Response(
    var Feedback : String?
)
class LocateFormUploadAgain_Request(
    var Function : String?,
    var UserID : String?,
    var WorkTime : String?,
    var WorkCode : String?,
    var LocateNumber : String?,
    var InputContent : String?,
    var ImagePhoto : String?
)
class LocateFormUploadAgain_Response(
    var Feedback : String?
)
class WorkInfo_Request(
    var Function : String?,
    var WorkCode : String?
)
class WorkInfo_Response(
    var State : String?,
    var WorkTime : String?,
    var WorkPath : String?,
    var WorkCode : String?,
    var Date : String?,
    var UserName : String?
)
class NewReportUpload_Request(
    var Function : String?,
    var UserID : String?,
    var Longitude : String?,
    var Latitude : String?,
    var ReportTitle : String?,
    var ReportContent : String?,
    var ReportPhoto : String?
)
class NewReportUpload_Response(
    var Feedback : String?
)
class RepairList_Request(
    var Function : String?,
    var Date : String?,
    var UserID : String?
)
class RepairList_Response(
    var RepairList : List<RepairList>
)
class RepairList(
    var State : String?,
    var RepairCode : String?,
    var RepairTitle : String?
)
class RepairContent_Request(
    var Function : String?,
    var RepairCode : String?,
    var ReportType : String?
)
class RepairContent_Response(
    var OutsideRepair : OutsideRepair
)
class OutsideRepair(
    var ReportCode: String?,
    var RepairCode : String?,
    var Manager : String?,
    var Longitude : String?,
    var Latitude : String?,
    var RepairTitle : String?,
    var RepairContent : String?,
    var RepairPhoto : String?,
    var ReportTitle : String?,
    var ReportContent : String?,
    var ReportPhoto : String?,
    var Edit : String?
)
class ReportList_Request(
    var Function : String?,
    var Date : String?,
    var UserID : String?
)
class ReportList_Response(
    var ReportList : List<ReportList>
)
class ReportList(
    var ReportCode : String?,
    var State : String?,
    var ReportTime : String?,
    var ReportTitle : String?
)
class ReportContent_Request(
    var Function : String?,
    var ReportCode : String?,
    var ReportType : String?
)
class ReportContent_Response(
    var OutsideRepair : OutsideRepair
)
class ReportUploadAgain_Request(
    var Function : String?,
    var UserID : String?,
    var ReportCode : String?,
    var ReportTitle : String?,
    var ReportContent : String?,
    var ReportPhoto : String?,
    var ReportType : String?
)
class ReportUploadAgain_Response(
    var Feedback : String?
)
class FormRequestWork_Request(
    var Function : String?,
    var WorkCode : String?
)
class FormRequestWork_Response(
    var State : String?,
    var FinishContent : String?,
    var FinishPhoto : String?
)
class FormRequestService_Request(
    var Function : String?,
    var WorkCode : String?
)
class FormRequestService_Response(
    var State : String?,
    var RepairContent : String?,
    var RepairPhoto : String?,
    var Edit : String?
)
class FormUploadWork_Request(
    var Function : String?,
    var UserID : String?,
    var WorkCode : String?,
    var State : String?,
    var FinishContent : String?,
    var FinishPhoto : String?
)
class FormUploadWork_Response(
    var Feedback : String?
)
class FormUploadService_Request(
    var Function : String?,
    var UserID : String?,
    var RepairCode : String?,
    var State : String?,
    var RepairContent : String?,
    var RepairPhoto : String?
)
class FormUploadService_Response(
    var Feedback : String?
)
