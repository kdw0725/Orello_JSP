<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/calendar_calendar.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous"/>

<script src="https://uicdn.toast.com/tui.code-snippet/v1.5.2/tui-code-snippet.min.js"></script>
<script src="https://uicdn.toast.com/tui.time-picker/v2.0.3/tui-time-picker.min.js"></script>
<script src="https://uicdn.toast.com/tui.date-picker/v4.0.3/tui-date-picker.min.js"></script>
<script src="/orello/calendar_file/js/tui-calendar.js"></script>
<!-- <script src="/orello/calendar_file/js/data/calendars.js"></script> -->
<script src="/orello/calendar_file/js/data/schedules.js"></script>

<!-- random data -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
<link rel="stylesheet" type="text/css" href="/orello/calendar_file/css/tui-calendar.css"/>



<!-- If you use the default popups, use this. -->
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css"/>
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css"/>
<link rel="stylesheet" type="text/css" href="/orello/calendar_file/css/default.css"/>
<link rel="stylesheet" type="text/css" href="/orello/calendar_file/css/icons.css"/>


<style>

</style>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<section id="content" class="container">
	<%
		out.flush();
	    RequestDispatcher dheader = request.getRequestDispatcher("/inc/member.do?pseq=" + request.getAttribute("pseq"));
	    dheader.include(request, response);
	%>
	
	    <div id="contentRight">
        <div id="calendarArea">
            <!-- 캘린더 위에 메뉴바 -->
            <div id="menu">
                <span id="menu-navi">
                    <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
                        <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
                    </button>
                    <span id="renderRange" class="render-range"></span>
                    <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
                        <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
                    </button>
                    <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Today</button>
                </span>
                <span class="dropdown">
                    <button id="dropdownMenu-calendarType" class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="true">
                        <i id="calendarTypeIcon" class="calendar-icon ic_view_month" style="margin-right: 4px;"></i>
                        <span id="calendarTypeName">View</span>&nbsp;
                        <i class="calendar-icon tui-full-calendar-dropdown-arrow"></i>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu-calendarType">
                        <li role="presentation">
                            <a class="dropdown-menu-title" role="menuitem" data-action="toggle-daily">
                                <i class="calendar-icon ic_view_day"></i>Daily
                            </a>
                        </li>
                        <li role="presentation">
                            <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weekly">
                                <i class="calendar-icon ic_view_week"></i>Weekly
                            </a>
                        </li>
                        <li role="presentation">
                            <a class="dropdown-menu-title" role="menuitem" data-action="toggle-monthly">
                                <i class="calendar-icon ic_view_month"></i>Month
                            </a>
                        </li>
                        <li role="presentation">
                            <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks2">
                                <i class="calendar-icon ic_view_week"></i>2 weeks
                            </a>
                        </li>
                        <li role="presentation">
                            <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks3">
                                <i class="calendar-icon ic_view_week"></i>3 weeks
                            </a>
                        </li>
                        <li role="presentation" class="dropdown-divider"></li>
                        <li role="presentation">
                            <a role="menuitem" data-action="toggle-workweek">
                                <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-workweek" checked>
                                <span class="checkbox-title"></span>Show weekends
                            </a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" data-action="toggle-start-day-1">
                                <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-start-day-1">
                                <span class="checkbox-title"></span>Start Week on Monday
                            </a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" data-action="toggle-narrow-weekend">
                                <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-narrow-weekend">
                                <span class="checkbox-title"></span>Narrower than weekdays
                            </a>
                        </li>
                    </ul>
                </span>
            </div>
            <!-- 캘린더 영역 -->
            <div id="calendar"></div>
        </div>
    </div>
	
	
	
	</section>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>
	
	<script type="text/javascript" class="code-js">
	
    var MONTHLY_CUSTOM_THEME = {
        // month header 'dayname'
        "month.dayname.height": "42px",
        "month.dayname.borderLeft": "none",
        "month.dayname.paddingLeft": "8px",
        "month.dayname.paddingRight": "0",
        "month.dayname.fontSize": "13px",
        "month.dayname.backgroundColor": "inherit",
        "month.dayname.fontWeight": "normal",
        "month.dayname.textAlign": "left",
        // month day grid cell 'day'
        "month.holidayExceptThisMonth.color": "#f3acac",
        "month.dayExceptThisMonth.color": "#bbb",
        "month.weekend.backgroundColor": "#fafafa",
        "month.day.fontSize": "16px",
        // month schedule style
        "month.schedule.borderRadius": "5px",
        "month.schedule.height": "18px",
        "month.schedule.marginTop": "2px",
        "month.schedule.marginLeft": "10px",
        "month.schedule.marginRight": "10px",
        // month more view
        "month.moreView.boxShadow": "none",
        "month.moreView.paddingBottom": "0",
        "month.moreView.border": "1px solid #9a935a",
        "month.moreView.backgroundColor": "#f9f3c6",
        "month.moreViewTitle.height": "28px",
        "month.moreViewTitle.marginBottom": "0",
        "month.moreViewTitle.backgroundColor": "#f4f4f4",
        "month.moreViewTitle.borderBottom": "1px solid #ddd",
        "month.moreViewTitle.padding": "0 10px",
        "month.moreViewList.padding": "10px",
    };
    
    var cal = new tui.Calendar("#calendar", {
        defaultView: "month",
        theme: MONTHLY_CUSTOM_THEME, // set theme
        // 캘린더 카테고리(?) 생성 -> 나중에 멤버별로 적용하기 
         useCreationPopup: true,
        useDetailPopup: true,
       calendars: [
    	   <c:forEach items="${plist}" var="dto">
    	   {
               id: "${dto.seq}",
               name: "${dto.name}",
               color: "#ffffff",
               bgColor: "${dto.colorcode}",
               dragBgColor: "${dto.colorcode}",
               borderColor: "${dto.colorcode}",
           },
    	   </c:forEach>
       ],
       
    });

    // 캘린더 내용 생성하기 
    cal.createSchedules([
    		
    	<c:forEach items="${plist}" var="dto">
    	{
            id: "${dto.num}",
            calendarId: "${dto.seq}",
            title: "${dto.name}",
            category: "time",
            start: "${dto.startdate}T10:30:00",
            end: "${dto.enddate}T12:30:00",
        },
    	
    	</c:forEach>
    	
    
    ]);
    
    
    // 달력 누르면 팝업 띄우기
    // register templates
    var templates = {
        popupIsAllDay: function() {
        return 'All Day';
        },
        popupStateFree: function() {
        return 'Free';
        },
        popupStateBusy: function() {
        return 'Busy';
        },
        titlePlaceholder: function() {
        return 'Subject';
        },
        locationPlaceholder: function() {
        return 'Location';
        },
        startDatePlaceholder: function() {
        return 'Start date';
        },
        endDatePlaceholder: function() {
        return 'End date';
        },
        popupSave: function() {
        return 'Save';
        },
        popupUpdate: function() {
        return 'Update';
        },
        popupDetailDate: function(isAllDay, start, end) {
        var isSameDate = moment(start).isSame(end);
        var endFormat = (isSameDate ? '' : 'YYYY.MM.DD ') + 'hh:mm a';

        if (isAllDay) {
            return moment(start).format('YYYY.MM.DD') + (isSameDate ? '' : ' - ' + moment(end).format('YYYY.MM.DD'));
        }

        return (moment(start).format('YYYY.MM.DD hh:mm a') + ' - ' + moment(end).format(endFormat));
        },
        popupDetailLocation: function(schedule) {
        return 'Location : ' + schedule.location;
        },
        popupDetailUser: function(schedule) {
        return 'User : ' + (schedule.attendees || []).join(', ');
        },
        popupDetailState: function(schedule) {
        return 'State : ' + schedule.state || 'Busy';
        },
        popupDetailRepeat: function(schedule) {
        return 'Repeat : ' + schedule.recurrenceRule;
        },
        popupDetailBody: function(schedule) {
        return 'Body : ' + schedule.body;
        },
        popupEdit: function() {
        return 'Edit';
        },
        popupDelete: function() {
        return 'Delete';
        }
    };
</script>

<script src="/orello/calendar_file/js/default.js"></script>
	
	
</body>
</html>