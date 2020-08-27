$(document).ready(function() {

	$("#chatOpen").click(function () {
	    // 모달창으로 채팅 띄우기
	    $('#chatModal .modal').modal();
	    
	    //채팅 띄우면 친구목록 가져오기
	    getFriendList();
	    
	});
	
	//채팅목록 탭 
	$("#chatModal .listConvert .convertBtn:first-child").addClass("active");
	$("#chatModal .listConvert .convertBtn").click(function() {
	    $("#chatModal .listConvert .convertBtn").removeClass("active");
	    $(this).addClass("active");
	});

	//헤더의 이미지 누르면 목록이 나타나게 , 다른 영역 클릭하면 목록 사라지게 
	$("#chatModal").click(function(e) {
	    if (e.target.className == "listTarget") {
	        $("#chatModal #chatMemberList .chatMember").css("display", "block");    
	    } else {
	        $("#chatModal #chatMemberList .chatMember").css("display", "none");
	    }
	});
	
	

	//엔터치면 메시지 전송
	$("#chatModal #message-to-send").keyup(function() {
		if (event.keyCode == 13) {
			send();
		}
	}); 
	//send버튼 누르면 메시지 전송
	$("#chatModal #btnSend").click(function() {
		send();	
	});

});

function getFriendList() {
	//친구목록 불러오기
    $.ajax({
    	type: "GET",
    	url: "/orello/chat/friendlist.do",
    	dataType: "json",
    	success: function(result) {	
    		
    	    $("#chatModal #friendlist").html("");
    		$(result).each(function(index, item) {
    			/*
                     <li class="clearfix">
                        <img src="../images/0.png" alt="avatar" />
                        <div class="about">
                            <div class="name">Vincent Porter</div>
                            <div class="status">
                                <i class="fa fa-circle online"></i> online
                            </div>
                        </div>
                    </li> 
    			*/
    			var temp = '';
    			temp += '<li class="clearfix" onclick="showChat('+ item.mseq +');">';
    			temp += '<img src="../images/0.png" alt="avatar" />';
    			temp += '<div class="about">';
    			temp += '<div class="name">'+ item.name +'</div>';
    			temp += '<div class="status"><i class="fa fa-circle online"></i> online</div>';
    			temp += '</div>';
    			temp += '</li>';
 
    			$("#chatModal #friendlist").append(temp);
    		});
    		
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}	
    });
}

function getProjectList() {
	//채팅내역 불러오기(Chat History탭 클릭시)
    $.ajax({
    	type: "GET",
    	url: "/orello/chat/chatproject.do",
    	dataType: "json",
    	success: function(result) {	
    		
    	    $("#chatModal #friendlist").html("");
    		$(result).each(function(index, item) {
    			/*
					<li class="clearfix multiUser">
                        <div class="imgBox">
                            <img src="../images/0.png" alt="avatar" />
                            <img src="../images/1.png" alt="avatar" />
                            <img src="../images/2.png" alt="avatar" />
                            <img src="../images/3.png" alt="avatar" />
                        </div>
                        <div class="about">
                            <div class="name">Project Name <span class="badge">4</span></div>
                            <div class="status">
                                <i class="fa fa-circle multiUser"></i><span> Member1(Leader)</span>
                            </div>
                        </div>
                    </li>
    			*/
    			var temp = '';
    			temp += '<li class="clearfix multiUser" onclick="showMultiChat('+ item.pseq +');">';
    			temp += '<div class="imgBox">';
    			
    			//최대 4명까지만 출력하도록
    			var index= 0;
    			
    			if (item.mlist.length > 4) {
    				index = 4;
    			} else {
    				index = item.mlist.length;
    			}
    			for (var i=0; i< index; i++) {
    				
    				temp += '<img src="../images/'+ item.mlist[i].profile +'.png" alt="avatar" />';
    			}
    			
    			temp += '</div>';
    			temp += '<div class="about">';
    			temp += '<div class="name">'+ item.name +'</div>';
    			temp += '<div class="status"><i class="fa fa-circle online"></i> online</div>';
    			temp += '</div>';
    			temp += '</li>';
 
    			$("#chatModal #friendlist").append(temp);
    		});
    		
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}	
    });
}

//친구목록에서 친구를 누르면 대화창 보여주기
function showChat(mseq) {
	
	var fname = "";
	var myname ="";
	
	//히든태그에 넣어놓은 값 초기화하기
	$("#chatModal #hiddenCseq").val("");
	
	//채팅헤더 가져오기
	$.ajax({
		type: "GET",
    	url: "/orello/chat/chatheader.do",
    	data: "mseq=" + mseq,
    	dataType: "json",
    	success: function(result) {	
    		
    		$("#chatModal .chat-header > img.listTarget").show();
    		$("#chatModal .chat-header > div.imgBox").hide();
    		
    		$("#chatModal .chat-header img").attr("src","/orello/images/" + result.profile + ".png");
    		$("#chatModal .chat-header .chat-with").html(result.name);
    		fname = result.name;
    		
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	});
	
	//대화내용 가져오기
	$.ajax({
		type: "GET",
    	url: "/orello/chat/chatlist.do",
    	data: "mseq=" + mseq,
    	dataType: "json",
    	success: function(result) {	
    		/*
	            <li>
	                <div class="message-data">
	                    <span class="message-data-name"><i class="fa fa-circle online"></i>Vincent</span>
	                    <span class="message-data-time">10:20 AM, Today</span>
	                </div>
	                <div class="message other-message">
	                    <span class="msgContent">Actually everything was fine. I'm very excited to show this to our team.</span>
	                </div>
	            </li>
	            
                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:10 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
                    </div>
                    <div class="message my-message float-right">
                        <span class="msgContent">Hi Vincent, how are you? How is the project coming along?</span>
                        <!-- 파일 전송했을 때 -->
                        <div class="fileSend">
                            <div class="fileThumbnail"><i class="glyphicon glyphicon-floppy-disk"></i></div>
                            <div class="fileContent">
                                <span class="fileName">testfilename.txt</span>
                                <span class="fileSize">5kb</span>
                            </div>
                        </div>
                    </div>
                </li>
                
                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:14 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
                    </div>
                    <div class="message my-message float-right">
                        <span class="msgContent">Well I am not sure. The rest of the team is not here yet. Maybe in an hour or so? Have you faced any problems at the last phase of the project?</span>
                        <!-- 이미지 전송했을 때 -->
                        <div class="imageSend">
                            <a target="_blank" href='../images/Logo-wide.jpg'><img class="img" src="../images/Logo-wide.jpg"></a>     
                        </div>
                    </div>
                </li>
    		 */
    		//대화창 초기화해주기
    		$("#chatModal .chat-history > ul").html("");
    		$(result).each(function(index, item) {
    			
    			var temp = '';
    			
    			//히든태그에 대화방 번호 넣어놓기
    			$("#chatModal #hiddenCseq").val(item.cseq);

    			//대화내용 출력하기
    			if (item.mseq == mseq) {	//상대방 대화내역일 경우
    			
    				temp += '<li>';
    				temp += '<div class="message-data">';
    				temp += '<span class="message-data-name"><i class="fa fa-circle online"></i>' + fname + '</span>';
    				temp += '<span class="message-data-time">' + item.regdate + '</span>';
    				temp += '</div>';
    				temp += '<div class="message other-message">';
    				temp += '<span class="msgContent">' + item.content + '</span>';
    				temp += '</div>';
    				temp += '</li>';
    				
    			} else {	//내 대화내역일 경우

    				temp += '<li class="clearfix">';
    				temp += '<div class="message-data align-right">';
    				temp += '<span class="message-data-time">' + item.regdate + '</span> &nbsp; &nbsp;';
    				temp += '<span class="message-data-name"></span> <i class="fa fa-circle me"></i>';
    				temp += '</div>';
    				temp += '<div class="message my-message float-right">';
    				temp += '<span class="msgContent">' + item.content + '</span>';
    				temp += '</div>';
    				temp += '</li>';
    			}
    			$("#chatModal .chat-history > ul").append(temp);
    		});
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	});
}	
	
function showMultiChat(pseq) {
	
	//채팅헤더 가져오기
	$.ajax({
		type: "GET",
    	url: "/orello/chat/multichatheader.do",
    	data: "pseq=" + pseq,
    	dataType: "json",
    	success: function(result) {	
    		
    		/*
            <div class="imgBox multiUser">
                <img src="../images/0.png" alt="avatar" />
                <img src="../images/1.png" alt="avatar" />
                <img src="../images/2.png" alt="avatar" />
                <img src="../images/3.png" alt="avatar" />
            </div>
            <img src="../images/9.png" alt="avatar" class="listTarget"/>
            <div class="chat-about">
                <div class="chat-with">Chat with Vincent Porter</div>
                <div class="chat-num-messages">already 1902 messages</div>
            </div>
    		
    		*/
    		//히든태그에 대화방 번호 넣어놓기
			$("#chatModal #hiddenCseq").val(result.seq);
    		
    		$("#chatModal .chat-header .chat-with").html(result.name);
    		
    		//먼저 초기화해주기
    		$("#chatModal .chat-header > div.imgBox").remove();
    		
    		var temp = '';
    		temp += '<div class="imgBox multiUser">';
    		
    		
			//최대 4명까지만 출력하도록
			var index= 0;
			
			if (result.list.length > 4) {
				index = 4;
			} else {
				index = result.list.length;
			}
			for (var i=0; i< index; i++) {
				temp += '<img src="../images/'+ result.list[i].profile +'.png" alt="avatar" class="listTarget"/>';
			}
			
    		temp += '</div>';
    		$("#chatModal .chat-header").prepend(temp);
    		$("#chatModal .chat-header > img.listTarget").hide();
    		$("#chatModal .chat-header > div.imgBox").show();
    		
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	});
	
	
	//대화내용 가져오기
	$.ajax({
		type: "GET",
    	url: "/orello/chat/multichatlist.do",
    	data: "pseq=" + pseq,
    	dataType: "json",
    	success: function(result) {	
    		/*
	            <li>
	                <div class="message-data">
	                    <span class="message-data-name"><i class="fa fa-circle online"></i>Vincent</span>
	                    <span class="message-data-time">10:20 AM, Today</span>
	                </div>
	                <div class="message other-message">
	                    <span class="msgContent">Actually everything was fine. I'm very excited to show this to our team.</span>
	                </div>
	            </li>
	            
                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:10 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
                    </div>
                    <div class="message my-message float-right">
                        <span class="msgContent">Hi Vincent, how are you? How is the project coming along?</span>
                        <!-- 파일 전송했을 때 -->
                        <div class="fileSend">
                            <div class="fileThumbnail"><i class="glyphicon glyphicon-floppy-disk"></i></div>
                            <div class="fileContent">
                                <span class="fileName">testfilename.txt</span>
                                <span class="fileSize">5kb</span>
                            </div>
                        </div>
                    </div>
                </li>
                
                <li class="clearfix">
                    <div class="message-data align-right">
                        <span class="message-data-time">10:14 AM, Today</span> &nbsp; &nbsp;
                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
                    </div>
                    <div class="message my-message float-right">
                        <span class="msgContent">Well I am not sure. The rest of the team is not here yet. Maybe in an hour or so? Have you faced any problems at the last phase of the project?</span>
                        <!-- 이미지 전송했을 때 -->
                        <div class="imageSend">
                            <a target="_blank" href='../images/Logo-wide.jpg'><img class="img" src="../images/Logo-wide.jpg"></a>     
                        </div>
                    </div>
                </li>
    		 */
    		$("#chatModal .chat-history > ul").html("");
    		$(result).each(function(index, item) {
    			
    			var temp = '';
    			
    			if (item.myflag == 0) {
    			
    				temp += '<li>';
    				temp += '<div class="message-data">';
    				temp += '<span class="message-data-name"><i class="fa fa-circle online"></i>' + item.name + '</span>';
    				temp += '<span class="message-data-time">' + item.regdate + '</span>';
    				temp += '</div>';
    				temp += '<div class="message other-message">';
    				temp += '<span class="msgContent">' + item.content + '</span>';
    				temp += '</div>';
    				temp += '</li>';
    				
    			} else {

    				temp += '<li class="clearfix">';
    				temp += '<div class="message-data align-right">';
    				temp += '<span class="message-data-time">' + item.regdate + '</span> &nbsp; &nbsp;';
    				temp += '<span class="message-data-name"></span> <i class="fa fa-circle me"></i>';
    				temp += '</div>';
    				temp += '<div class="message my-message float-right">';
    				temp += '<span class="msgContent">' + item.content + '</span>';
    				temp += '</div>';
    				temp += '</li>';
    			}
    			$("#chatModal .chat-history > ul").append(temp);
    		});
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	});
}






	
function send() {
	
	var cseq = $("#chatModal #hiddenCseq").val();
	var content = $("#chatModal #message-to-send").val();
	
	if (content != "" || content != null) {	//메세지를 입력한 경우만 전송
		
		$.ajax({
			type: "POST",
	    	url: "/orello/chat/sendok.do",
	    	data: "cseq=" + cseq + "&content=" + content,
	    	dataType: "json",
	    	success: function(result) {	
	    		
	    		if(result.result == 1) {
	    			//말풍선 추가, 텍스트박스 초기화
	    			/*
	                 <li class="clearfix">
	                    <div class="message-data align-right">
	                        <span class="message-data-time">10:10 AM, Today</span> &nbsp; &nbsp;
	                        <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
	                    </div>
	                    <div class="message my-message float-right">
	                        <span class="msgContent">Hi Vincent, how are you? How is the project coming along?</span>
	                    </div>
	                </li>
	    			 */
					$("#chatModal #message-to-send").val("");
					var temp = '';
					temp += '<li class="clearfix">';
					temp += '<div class="message-data align-right">';
					temp += '<span class="message-data-time">' + result.time + '</span> &nbsp; &nbsp;';
					temp += '<span class="message-data-name"></span> <i class="fa fa-circle me"></i>';
					temp += '</div>';
					temp += '<div class="message my-message float-right">';
					temp += '<span class="msgContent">' + content + '</span>';
					temp += '</div>';
					temp += '</li>';
					$("#chatModal .chat-history > ul").append(temp);
					
					$("#chatModal .chat-history").scrollTop($("#chatModal .chat-history")[0].scrollHeight);
					
	    		} else {
	    			alert("failed");
	    		}
	    	},
	    	error: function(a,b,c) {
	    		console.log(a,b,c);
	    	}
		});
	} else {
		alert("메세지를 입력하세요.");		
	}
}	
	
	
	
	
	
	

	
	


