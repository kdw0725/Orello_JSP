    $(document).ready(function () {
        $("#header").load("../include/header.html");
        $("#footer").load("../include/footer.html");
    });

    //contentLeft
    //클릭한 메뉴만 표시되도록
    $(".list-group > a").click(function () {
        $(".list-group > a").removeClass("active");
        $(this).addClass("active");
    });

    //아코디언 메뉴
    var acc = document.getElementsByClassName("parent");
    for (var i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            var li = $(this).children("li");
            li.slideToggle(400);
            this.classList.toggle("active");
        });
    }