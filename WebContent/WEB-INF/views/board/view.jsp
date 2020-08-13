<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberLeft.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board_view.css">
<style>

</style>
<%@ include file="/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
	<%@ include file="/inc/member.jsp"%>
	
	 <div id="contentRight">
        <div
            class="modal fade"
            id="myModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="myModalLabel"
            aria-hidden="true"
        >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button
                            type="button"
                            class="close"
                            data-dismiss="modal"
                            aria-label="Close"
                        >
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            Profile
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div>
                            <img src="/orello/images/1.png" alt="" />
                            <div>HongGildong</div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            class="btn btn-default"
                            data-dismiss="modal"
                        >
                            Close
                        </button>
                        <button type="button" class="btn btn-info">
                            Send Message
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div id="main">
            <div id="hidden">
                <span data-toggle="modal" data-target="#myModal">profile</span>
                <span>board list</span>
                <span>1:1 chat</span>
            </div>
            <div id="main_title">
                <div>border type</div>
                <div id="titleicon"></div>
                <div id="title">
                    <h1>${dto.title}</h1>
                </div>
                <div id="writer">
                    <div id="profile">
                        <img src="/orello/images/1.png" alt="" />
                        <div>${dto.name}</div>
                    </div>
                    <span id="write_date" class="be-comment-time">
                                <i class="fa fa-clock-o"></i>
                                ${dto.regdate}
                            </span>
                </div>
            </div>

            <div id="main_content">
               ${dto.content}

                <div id="content_btn">
                    <button type="button" class="btn btn-default" onclick="location.href='/orello/board/delete.do';">
                        <li class="glyphicon glyphicon-remove"></li>
                        <span>delete</span>
                    </button>
                    <button class="btn btn-default">
                        <li class="glyphicon glyphicon-asterisk"></li>
                        <span>modify</span>
                    </button>


                </div>
            </div>
            <div id="btn_set">
                
                <button class="btn btn-info">
                    <li class="glyphicon glyphicon-chevron-left"></li>
                    <span>prev</span>
                </button>
                
                
                <button type="button" class="btn btn-info">
                    <li class="glyphicon glyphicon-list"></li>
                    <span>back to list</span>
                </button>
                <button class="btn btn-info">
                    <span>next</span>
                    <li class="glyphicon glyphicon-chevron-right"></li>
                </button>
            </div>

            <!-- <link
                href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
                rel="stylesheet"
            /> -->


            <div class="container">
                <div class="be-comment-block">
                    <h1 class="comments-title">Comments (3)</h1>
                    <div id="comment_hidden">
                        <span data-toggle="modal" data-target="#myModal">profile</span>
                        <span>board list</span>
                        <span>1:1 chat</span>
                    </div>

                    <div class="be-comment">
                        <div class="be-img-comment">
                            <a>
                                <img
                                    src="/orello/images/2.png"
                                    alt=""
                                    class="be-ava-comment"
                                />
                            </a>
                        </div>
                        <div class="be-comment-content">
                            <span class="be-comment-name">
                                <a id="comment_name">Ravi Sah</a>
                            </span>
                            <span class="be-comment-time">
                                <i class="fa fa-clock-o"></i>
                                May 27, 2015 at 3:14am
                            </span>

                            <p class="be-comment-text">
                                Pellentesque gravida tristique ultrices. Sed
                                blandit varius mauris, vel volutpat urna
                                hendrerit id. Curabitur rutrum dolor gravida
                                turpis tristique efficitur.
                            </p>
                        </div>
                    </div>
                    <div class="be-comment">
                        <div class="be-img-comment">
                            <a href="blog-detail-2.html">
                                <img
                                    src="/orello/images/3.png"
                                    alt=""
                                    class="be-ava-comment"
                                />
                            </a>
                        </div>
                        <div class="be-comment-content">
                            <span class="be-comment-name">
                                <a href="blog-detail-2.html"
                                    >Phoenix, the Creative Studio</a
                                >
                            </span>
                            <span class="be-comment-time">
                                <i class="fa fa-clock-o"></i>
                                May 27, 2015 at 3:14am
                            </span>
                            <p class="be-comment-text">
                                Nunc ornare sed dolor sed mattis. In scelerisque
                                dui a arcu mattis, at maximus eros commodo. Cras
                                magna nunc, cursus lobortis luctus at,
                                sollicitudin vel neque. Duis eleifend lorem non
                                ant. Proin ut ornare lectus, vel eleifend est.
                                Fusce hendrerit dui in turpis tristique blandit.
                            </p>
                        </div>
                    </div>
                    <div class="be-comment">
                        <div class="be-img-comment">
                            <a href="blog-detail-2.html">
                                <img
                                    src="/orello/images/5.png"
                                    alt=""
                                    class="be-ava-comment"
                                />
                            </a>
                        </div>
                        <div class="be-comment-content">
                            <span class="be-comment-name">
                                <a href="blog-detail-2.html">Cüneyt ŞEN</a>
                            </span>
                            <span class="be-comment-time">
                                <i class="fa fa-clock-o"></i>
                                May 27, 2015 at 3:14am
                            </span>
                            <p class="be-comment-text">
                                Cras magna nunc, cursus lobortis luctus at,
                                sollicitudin vel neque. Duis eleifend lorem non
                                ant
                            </p>
                        </div>
                    </div>
                    <form class="form-block">
                       
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <textarea
                                        class="form-input"
                                        required=""
                                        placeholder="Your text"
                                    ></textarea>
                                </div>
                            </div>
                            <a class="btn btn-info pull-right" id="submit">submit</a>
                        </div>
                    </form>
                </div>
            </div>

            <!-- <input type="button" value="▲top" id="top" /> -->
            <button id="top" class="btn btn-info">▲top</button>
        </div>
    </div>
	
	</section>
	
	<%@ include file="/inc/footer.jsp"%>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/memberLeft.js">
	
	
	</script>
	<script>
    var flag = true;
    $("#profile").click(function () {
        if (flag) {
            $("#hidden").css("display", "block");
            flag = false;
        } else {
            $("#hidden").css("display", "none");
            flag = true;
        }
    });

    var flag2 = true;
    $("#comment_name:first-child").click(function () {
        if (flag2) {
            $("#comment_hidden").css("display", "block");
            flag2 = false;
        } else {
            $("#comment_hidden").css("display", "none");
            flag2 = true;
        }
    });
    
    
    $("#content_btn > button:first-child").click(function() {
        // var result = confirm("do you want to delete?");
        if(confirm("do you want to delete?")){
        	location.href="/orello/board/delete.do?seq="+${dto.seq};
        }else {
        	
        	location.href="/orello/board/view.do?seq="+${dto.seq};
        }
    })
     $("#content_btn > button:last-child").click(function() {
        alert("modify?");
    })

    // back to board
    $("#btn_set > button:nth-child(2)").click(function() {
        location.href="/orello/board/list.do?page="+${page};
    })

    //comment submit
   
</script>
</body>
</html>