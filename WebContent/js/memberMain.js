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
for (var i = 0; i < projectBox.length - 1; i++) {
    var temp = Math.floor(Math.random() * color.length);
    projectBox[i].children[0].style.backgroundColor = color[temp];
}
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
    projectBox[i].onclick = function () {
        alert("상세정보 들어가기");
    };
}

var moreProject = document.getElementById("moreProject");
moreProject.onmouseover = function () {
    moreProject.className = "btn btn-info";
};

moreProject.onmouseleave = function () {
    moreProject.className = "btn btn-default";
};

moreProject.onclick = function () {
    for (var i = 0; i < Math.floor(Math.random() * 10); i++) {
        var moreProjectBox = document.createElement("div");
        moreProjectBox.className = "projectBox";

        var index = document.createElement("div");
        index.className = "index";
        var temp = Math.floor(Math.random() * color.length);
        index.style.backgroundColor = color[temp];
        moreProjectBox.appendChild(index);

        var detail = document.createElement("p");
        detail.className = "detail";
        detail.innerHTML =
            "자세히 보기<i class='glyphicon glyphicon-play'></i>";

        moreProjectBox.appendChild(detail);

        var moreProjectTitle = document.createElement("div");
        moreProjectTitle.className = "projectTitle";
        var title = document.createElement("strong");
        title.className = "title";
        title.innerText = "Orello";
        moreProjectTitle.appendChild(title);

        var moreProjectType = document.createElement("p");
        var projectList = ["웹", "안드로이드", "IOS", "기타"];

        var projectTemp =
            projectList[Math.floor(Math.random() * projectList.length)];
        if (projectTemp == "웹") {
            moreProjectType.innerHTML =
                "<i class='fab fa-internet-explorer'></i> 웹 프로젝트";
        } else if (projectTemp == "안드로이드") {
            moreProjectType.innerHTML =
                "<i class='fab fa-android'></i> 안드로이드 프로젝트";
        } else if (projectTemp == "IOS") {
            moreProjectType.innerHTML =
                "<i class='fab fa-apple'></i> IOS 프로젝트";
        } else if (projectTemp == "기타") {
            moreProjectType.innerHTML =
                "<i class='fas fa-cat'></i> 기타 프로젝트";
        }
        moreProjectTitle.appendChild(moreProjectType);
        moreProjectBox.appendChild(moreProjectTitle);

        var languageList = [
            "HTML",
            "CSS",
            "python",
            "Java",
            "JS",
            "Swift",
            "C",
        ];
        var moreLanguageBox = document.createElement("div");
        for (var i = 0; i < 3; i++) {
            var languageTemp =
                languageList[Math.floor(Math.random() * languageList.length)];
            var languageIcon = document.createElement("i");
            var languageText = document.createElement("label");
            languageText.for = "fab";

            if (languageTemp == "HTML") {
                languageIcon.className = "fab fa-html5";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "HTML";
            } else if (languageTemp == "CSS") {
                languageIcon.className = "fab fa-css3";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "CSS";
            } else if (languageTemp == "python") {
                languageIcon.className = "fab fa-python";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "python";
            } else if (languageTemp == "Java") {
                languageIcon.className = "fab fa-java";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "Java";
            } else if (languageTemp == "JS") {
                languageIcon.className = "fab fa-js";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "JS";
            } else if (languageTemp == "Swift") {
                languageIcon.className = "fab fa-swift";
                languageIcon.ariaHidden = "true";
                languageText.innerText = "Swift";
            } else if (languageTemp == "C") {
                languageIcon.className = "fab fa-cuttlefish";
                languageText.innerText = "C";
            }
            moreLanguageBox.appendChild(languageIcon);
            moreLanguageBox.appendChild(languageText);
        }
        moreLanguageBox.className = "languageBox";
        moreProjectBox.appendChild(moreLanguageBox);
        let moreTemp = moreProjectBox;
        moreProjectBox.onmouseover = function () {
            if (onmouseFlag == 0) {
                var innerDiv = document.createElement("div");
                moreTemp.children[0].style.width = "250px";
                moreTemp.children[0].style.height = "130px";
                var innerIcon = document.createElement("i");
                innerIcon.className = "glyphicon glyphicon-zoom-in";
                innerIcon.style.fontSize = "80px";
                innerIcon.style.color = "#fff";
                innerIcon.style.margin = "10px";
                innerDiv.appendChild(innerIcon);
                innerDiv.style.color = "#fff";
                innerDiv.style.textAlign = "center";

                var innerContent = document.createElement("p");
                innerContent.innerText = "자세히 보러가기";
                innerContent.style.fontWeight = "bold"
                innerDiv.appendChild(innerContent);
                

                moreTemp.children[0].appendChild(innerDiv);
                moreTemp.children[0].style.cursor = "pointer";

                onmouseFlag = 1;
            }
        };
        moreProjectBox.onmouseleave = function () {
            if (onmouseFlag == 1) {
                moreTemp.children[0].style.width = "50px";
                moreTemp.children[0].style.height = "25px";
                moreTemp.children[0].removeChild(
                    moreTemp.children[0].children[0]
                );
                moreTemp.children[0].style.cursor = "none";
                onmouseFlag = 0;
            }
        };
        $("#newProject").before(moreProjectBox);
    }
    $("#moreProject").css("display", "none");
    console.log($("#contentRight").height());
    $("#contentLeft").css("height", $("#contentRight").height());
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
    var searchArea = document.getElementById("searchArea");
    searchUser.onkeyup = function () {
        if (searchArea.children.length == 2) {
            searchArea.removeChild(searchArea.children[1]);
        }
        var searchUserResult = document.createElement("div");
        searchUserResult.className = "searchUserResult";
        var tbl = document.createElement("table");
        tbl.style.width = "284px";
        for (var i = 0; i < Math.random() * 5; i++) {
            var userData = document.createElement("tr");
            userData.className = "userData";
            var td = document.createElement("td");
            var userImg = document.createElement("img");
            userImg.src = "../images/dog03.jpg";
            var userName = document.createElement("strong");
            userName.innerText = "김동욱";
            var userId = document.createElement("span");
            userId.innerText = "kdw0725";
            var addBtn = document.createElement("input");
            addBtn.type = "button";
            addBtn.value = "+ add";
            addBtn.className = "btn btn-info";
            addBtn.onclick = function () {
                if (confirm("김동욱 님을 프로젝트에 추가하시겠습니까?")) {
                    var workers = document.getElementById("workers");
                    searchUser.value = "";
                    searchArea.removeChild(searchArea.children[1]);
                    var addedWorker = document.createElement("div");
                    addedWorker.className = "team-worker";
                    console.log(
                        workers.children[workers.children.length - 2].style
                            .zIndex
                    );
                    addedWorker.style.zIndex =
                        parseInt(
                            workers.children[workers.children.length - 2].style
                                .zIndex
                        ) + 1;
                    addedWorker.style.left =
                        -(
                            parseInt(
                                workers.children[workers.children.length - 2]
                                    .style.zIndex
                            ) + 1
                        ) * 20;
                    // addedWorker.style.left = "-80px";
                    workers.children[workers.children.length - 1].style.left =
                        parseInt(addedWorker.style.left) - 20 + "px";
                    var newImg = document.createElement("img");
                    newImg.src = "../images/man_03.png";
                    addedWorker.appendChild(newImg);

                    workers.children[workers.children.length - 1].before(
                        addedWorker
                    );
                }
            };

            td.appendChild(userImg);
            td.appendChild(userName);
            td.appendChild(userId);
            td.appendChild(addBtn);

            userData.appendChild(td);

            tbl.appendChild(userData);
        }

        searchUserResult.appendChild(tbl);
        searchUser.after(searchUserResult);
    };
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
