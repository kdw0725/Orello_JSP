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
    useCreationPopup: true,
    useDetailPopup: true,
    calendars: [
        {
            id: "1",
            name: "project1",
            color: "#ffffff",
            bgColor: "#61BD6D",
            dragBgColor: "#61BD6D",
            borderColor: "#61BD6D",
        },
        {
            id: "2",
            name: "project2",
            color: "#ffffff",
            bgColor: "#61BD6D",
            dragBgColor: "#61BD6D",
            borderColor: "#61BD6D",
        },
        {
            id: "3",
            name: "project3",
            color: "#ffffff",
            bgColor: "#61BD6D",
            dragBgColor: "#61BD6D",
            borderColor: "#61BD6D",
        },
    ],
});
cal.createSchedules([
    {
        id: "1",
        calendarId: "1",
        title: "자료 구조",
        category: "time",
        start: "2020-07-20T10:30:00",
        end: "2020-07-25T12:30:00",
    },
    {
        id: "2",
        calendarId: "2",
        title: "건강과 영양",
        category: "time",
        dueDateClass: "",
        start: "2020-07-20T14:30:00",
        end: "2020-07-20T16:30:00",
        isReadOnly: true, // schedule is read-only
    },
]);

var templates = {
    popupIsAllDay: function () {
        return "All Day";
    },
    popupStateFree: function () {
        return "Free";
    },
    popupStateBusy: function () {
        return "Busy";
    },
    titlePlaceholder: function () {
        return "Subject";
    },
    locationPlaceholder: function () {
        return "Location";
    },
    startDatePlaceholder: function () {
        return "Start date";
    },
    endDatePlaceholder: function () {
        return "End date";
    },
    popupSave: function () {
        return "Save";
    },
    popupUpdate: function () {
        return "Update";
    },
    popupDetailDate: function (isAllDay, start, end) {
        var isSameDate = moment(start).isSame(end);
        var endFormat = (isSameDate ? "" : "YYYY.MM.DD ") + "hh:mm a";

        if (isAllDay) {
            return (
                moment(start).format("YYYY.MM.DD") +
                (isSameDate ? "" : " - " + moment(end).format("YYYY.MM.DD"))
            );
        }

        return (
            moment(start).format("YYYY.MM.DD hh:mm a") +
            " - " +
            moment(end).format(endFormat)
        );
    },
    popupDetailLocation: function (schedule) {
        return "Location : " + schedule.location;
    },
    popupDetailUser: function (schedule) {
        return "User : " + (schedule.attendees || []).join(", ");
    },
    popupDetailState: function (schedule) {
        return "State : " + schedule.state || "Busy";
    },
    popupDetailRepeat: function (schedule) {
        return "Repeat : " + schedule.recurrenceRule;
    },
    popupDetailBody: function (schedule) {
        return "Body : " + schedule.body;
    },
    popupEdit: function () {
        return "Edit";
    },
    popupDelete: function () {
        return "Delete";
    },
};

var color = [
    "#8dd3c7",
    "#ffffb3",
    "#bebada",
    "#fb8072",
    "#80b1d3",
    "#fdb462",
    "#b3de69",
    "#fccde5",
    "#d9d9d9",
    "#bc80bd",
    "#ccebc5",
    "#ffed6f",
];

var projectBox = document.getElementsByClassName("projectBox");
//for (var i = 0; i < projectBox.length - 1; i++) {
//    var temp = Math.floor(Math.random() * color.length);
//    projectBox[i].children[0].style.backgroundColor = color[temp];
//}
var onmouseFlag = 0;
for (var i = 0; i < projectBox.length - 1; i++) {
    let temp = projectBox[i];
    projectBox[i].onmouseover = function () {
        if (onmouseFlag == 0) {
            var innerDiv = document.createElement("div");
            temp.children[0].style.width = "250px";
            temp.children[0].style.height = "130px";

            var innerIcon = document.createElement("i");
            innerIcon.className = "glyphicon glyphicon-zoom-in";
            innerIcon.style.fontSize = "80px";
            innerIcon.style.color = "#fff";
            innerIcon.style.margin = "10px";
            innerDiv.appendChild(innerIcon);
            innerDiv.style.color = "white";
            innerDiv.style.textAlign = "center";

            var innerContent = document.createElement("p");
            innerContent.innerText = "자세히 보러가기";
            innerContent.style.color = "#fff";
            innerContent.style.fontWeight = "bold";
            innerDiv.appendChild(innerContent);

            temp.children[0].appendChild(innerDiv);
            temp.children[0].style.cursor = "pointer";

            onmouseFlag = 1;
        }
    };
    projectBox[i].onmouseleave = function () {
        if (onmouseFlag == 1) {
            temp.children[0].style.width = "50px";
            temp.children[0].style.height = "25px";
            temp.children[0].removeChild(temp.children[0].children[0]);
            temp.children[0].style.cursor = "none";
            onmouseFlag = 0;
        }
    };
}

var moreProject = document.getElementById("moreProject");
moreProject.onmouseover = function () {
    moreProject.className = "btn btn-info";
};

moreProject.onmouseleave = function () {
    moreProject.className = "btn btn-default";
};


var notice = document.getElementById("notice");

var index = 0;
var noticeList = [
    "오늘은 프로젝트 하느라 집을 못갑니다.",
    "정보처리기사 공부를 안하고있습니다",
    "이번에도 떨어지면 사람이 아닙니다.",
    "오늘은 메인을 다 짜고싶습니다.",
    "다 하고 집에 갈 수 있을까요?",
];
notice.innerHTML = "<i class='glyphicon glyphicon-exclamation-sign'></i> ";
var innerSpan = document.createElement("span");
var innerNotice = document.createElement("span");
innerSpan.innerText = noticeList[index];
notice.appendChild(innerSpan);

window.onload = function () {
    setInterval(function () {
        setTimeout(function () {
            notice.style.opacity = 0;
            index++;
            if (index >= noticeList.length) index = 0;
        }, 3000);
        notice.innerHTML =
            "<i class='glyphicon glyphicon-exclamation-sign'></i> ";
        var innerSpan = document.createElement("span");
        innerSpan.innerText = noticeList[index];
        notice.appendChild(innerSpan);
        notice.style.opacity = 1;
    }, 4000);
};
var newProject = document.getElementById("newProject");
newProject.onmouseover = function () {
    newProject.children[0].style.fontSize = "8em";
    newProject.children[0].style.marginBottom = "15px";
};
newProject.onmouseleave = function () {
    newProject.children[0].style.fontSize = "80px";
};
newProject.onclick = function () {
    $("#createProject").modal("show");
};
var tooltipFlag = 0;
var teamWorker = document.getElementsByClassName("team-worker");
for (var i = 0; i < teamWorker.length; i++) {
    teamWorker[i].onclick = function () {
        if (tooltipFlag == 0) {
            var tooltips = document.createElement("div");
            tooltips.className = "tooltips";
            var img = document.createElement("img");
            img.src = "../images/man_01.png";
            img.style.width = "70px";
            img.style.height = "70px";
            tooltips.appendChild(img);

            var tooltipInfo = document.createElement("div");
            tooltipInfo.className = "tooltipInfo";

            var tooltipName = document.createElement("div");
            tooltipName.className = "tooltipName";
            tooltipName.innerText = "김동욱";

            var tooltipCompany = document.createElement("div");
            tooltipCompany.className = "tooltipCompany";
            tooltipCompany.innerText = "쌍용교육센터";

            var tooltipButton = document.createElement("div");
            tooltipButton.className = "tooltipButton";

            var friend = document.createElement("button");
            friend.className = "btn btn-default btn-sm";
            friend.innerHTML = "<i class='fas fa-plus'></i> <b>친구추가</b>";
            friend.id = "addFriend";
            friend.onclick = function () {
                friend.removeChild(friend.children[1]);
                friend.innerHTML =
                    "<i class='fas fa-user-friends'></i> <b>친구입니다.</b>";
                event.cancelBubble = true;
            };

            tooltipButton.appendChild(friend);

            tooltipInfo.appendChild(tooltipName);
            tooltipInfo.appendChild(tooltipCompany);
            tooltipInfo.appendChild(tooltipButton);

            tooltips.appendChild(tooltipInfo);

            this.appendChild(tooltips);
            tooltipFlag = 1;
        } else if (tooltipFlag == 1) {
            var addFriend = document.getElementById("addFriend");
            if (
                event.srcElement != addFriend &&
                event.srcElement != addFriend.children[0] &&
                event.srcElement != addFriend.children[1] &&
                event.srcElement != addFriend.parentElement
            ) {
                var temp = document.getElementsByClassName("tooltips");
                temp[0].parentElement.removeChild(temp[0]);
                tooltipFlag = 0;
            }
        }
    };
}
var newWorker = document.getElementsByClassName("new-worker")[0];
newWorker.onclick = function () {
    var searchUser = document.getElementById("searchUser");
    searchUser.style.height = "40px";
    searchUser.style.border = "1px solid #ccc";
    searchUser.style.padding = "6px 12px";
    searchUser.autocomplete = "off";
    searchUser.onkeyup = function () {
    	if($("#searchUser").val().length != 0){
    		$("#searchUserResult").show();
    	} else{
    		$("#searchUserResult").hide();
    	}
		var pattern = /^[가-힇]{1,6}$/;
		if(pattern.test($("#searchUser").val())){
			var nameData = {"name" : $("#searchUser").val()};
			$.ajax({
	    		type : "POST",
	    		url : "/orello/member/listmember.do",
	    		data : nameData, 
	    		dataType : "JSON",
	    		success : function(result){
					var searchResult = document.getElementById("searchUserResult");
					searchResult.innerHTML = "";
	    			var table = document.createElement("table");
	    			table.innerHTML = "";
	    			for(var i=0;i<result.length;i++){
	    				var tr = document.createElement("tr");
	    				tr.className = "userData"
	    				var td = document.createElement("td");
	    				
	    				var img = document.createElement("img");
	    				img.src = "/orello/images/" + result[i].file;
	    				
	    				var strong = document.createElement("strong");
	    				strong.innerHTML = result[i].name;
	    				
	    				var span = document.createElement("span");
	
	    				if(result[i].social == 'KAKAO'){
	    					span.innerHTML = '<img class="icon-logo" alt="KAKAO" src="/orello/images/kakao-icon.png">';
	    				} else if(result[i].social == 'GOOGLE'){
	    					span.innerHTML = '<img class="icon-logo" alt="GOOGLE" src="/orello/images/google-icon.png">';
	    				} else if(result[i].social == 'NAVER'){
	    					span.innerHTML = '<img class="icon-logo" alt="NAVER" src="/orello/images/naver-icon.PNG">';
	    				} else{
	    					span.innerHTML = "(" + result[i].email + ")"
	    				}
	    				
	    				var btn = document.createElement("input")
	    				btn.type = "button";
	    	            btn.value = "+ add";
	    	            btn.className = "btn btn-info add-member";
	    	            btn.dataset.target = result[i].seq;
	    	            
	    	            for(var i=0; i<$(".team-worker").length;i++){
	    	            	if(btn.dataset.target == $(".team-worker")[i].val()){
	    	            		btn.value = "추가됨";
	    	            		btn.disabled = 'disabled';
	    	            	}
	    	            }
	    	            btn.onclick = function(){
	    	            	var memberData = {"seq" : event.srcElement.dataset.target};
	    	            	var temp = event.srcElement;
	    	            	$.ajax({
	    	            		type : "POST",
	    	            		data : memberData,
	    	            		dataType : "JSON",
	    	            		url : "/orello/member/getmemberinfo.do",
	    	            		success: function(result){
	    	            			if(confirm(result.name + "님을 추가하시겠습니까?")){
	    	            				var teamWorker = document.createElement("div");
	    	            				teamWorker.className = "team-worker";
	    	            				
	    	            				var img = document.createElement("img");
	    	            				img.src = "/orello/images/" + result.file;
	    	            				img.alt = "worker";
	    	            				
	    	            				var input = document.createElement("input");
	    	            				input.type = "hidden";
	    	            				input.value = result.seq 
	    	            				
	    	            				teamWorker.appendChild(img);
	    	            				
	    	            				//$("#workers").before(teamWorker, $(".new-worker"));
	    	            				$(".new-worker").before(teamWorker);
	    	            				temp.value = "추가됨";
	    	            				temp.disabled = 'disabled';
	    	            			}
	    	            		},
	    	            		error: function(a, b, c){
	    	            			console.log(a, b, c);
	    	            		}
	    	            	});
	    	            }
	    	            	
	    	            
	    	            td.appendChild(img);
	    	            td.appendChild(strong);
	    	            td.appendChild(span);
	    	            td.appendChild(btn);
	    	            
	    	            tr.appendChild(td);
	    	            table.appendChild(tr);
	    			}
	    			$("#searchUserResult").append(table);
	    		},
	    		error : function(a, b, c){
	    			console.log(a, b, c);
	    		}
	    	});
	    }
	}
};

$("#editComment").click(function () {
    $("#profileComment").val($("#comment > span").text());
    $("#commentInput").css("display", "block").css("height", "70px");
    $("#comment").css("display", "none");
});
$("#commentCancel").click(function () {
    $("#commentInput").css("display", "none").css("height", "70px");
    $("#comment").css("display", "block");
});
$("#commentSave").click(function () {
    $("#commentInput").css("display", "none").css("height", "70px");
    $("#comment").css("display", "block");
    var commentData = {
    	"comment" : $("#commentArea input").val() 
    };
    $.ajax({
    	type : "POST",
    	url : "/orello/member/updatecomment.do",
    	data : commentData,
    	dataType : "JSON",
    	success : function(){
    	},
    	error : function(a, b, c){
    		console.log(a, b, c);
    	}
    });
    
    $("#comment > span").text($("#profileComment").val());

    // $("#comment").innerText($("#profileComment").val());
});
$("#timeLine .btn").mouseover(function () {
    $("#timeLine .btn").attr("class", "btn btn-info");
});
$("#timeLine .btn").mouseleave(function () {
    $("#timeLine .btn").attr("class", "btn btn-default");
});

$("#timeLineMore").click(function () {
    for (var i = 0; i < 10; i++) {
        var tr = document.createElement("tr");
        var td = document.createElement("td");
        td.className = "timeLineList";
        var img = document.createElement("img");
        img.src = "../images/man_01.png";
        img.alt = "profile";
        td.appendChild(img);

        var timeLineAction = document.createElement("div");
        timeLineAction.className = "timeLineAction";
        var name = document.createElement("strong");
        name.innerText = "조윤경";
        timeLineAction.appendChild(name);
        var time = document.createElement("span");
        time.innerText = "12시간전";
        timeLineAction.appendChild(time);
        var action = document.createElement("div");
        action.innerText = "조윤경님이 손바닥이 빨개졌습니다.";
        timeLineAction.appendChild(action);

        td.appendChild(timeLineAction);
        tr.appendChild(td);
        $("#timeLineContent tr:last-child").after(tr);
    }
});
var today = new Date();
var picker = tui.DatePicker.createRangePicker({
    startpicker: {
        date: today,
        input: "#startpicker-input",
        container: "#startpicker-container",
    },
    endpicker: {
        date: today,
        input: "#endpicker-input",
        container: "#endpicker-container",
    },
    // selectableRanges: [
    //     [today, new Date(today.getFullYear() + 1, today.getMonth(), today.getDate())]
    // ]
});