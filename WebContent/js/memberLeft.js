 // 메뉴 상단에 프로젝트 정보 출력하기 
    $("#projectName").append("Project Name");
    $("#projectPeriod").append("<span>2020.07.01 ~ 2020.07.10</span><br>");
    for (var i=0; i<5; i++) {
        $("#projectMember").append("<div class='profilePic'></div>")
    }
    var profilePic = document.getElementsByClassName("profilePic");
    for (var i=0; i<5; i++) {
        profilePic[i].setAttribute("style", `background-image: url('/orello/images/${i}.png')`)
    }

    //클릭한 메뉴만 표시되도록 
    $(".list-group > a").click(function() {
        $(".list-group > a").removeClass("active");
        $(this).addClass("active");
    });

    //아코디언 메뉴
    var acc = document.getElementsByClassName("parent");
    for (var i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            var li = $(this).children("li");
            li.slideToggle(1000);
            this.classList.toggle("active");
        });
    }