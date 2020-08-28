var fileNum = 1;		//파일 추가를 위한 변수


/*****추가 모달창*****/

//체크리스트 항목 추가 버튼을 눌렀을 때 
function openItemAdd() { 
  
	//초기화
	$("#checkListItemAdd #itemtitle").val("");							//제목 지우기
	$("#checkListItemAdd #checkListItemContent").text("");				//내용 지우기	
	$("#checkListItemAdd #fileParent").children(".fileBox").remove();	//첨부파일 지우기
	fileNum = 1;	//모달창 열때마다 초기화
	
	// 모달창으로 추가창 띄우기
    $('#checkListItemAdd').modal();
    $("#checkListItemAdd #itemtitle").focus();
    
    //해당 체크리스트의 seq 같이 보내주기 위해 hidden태그에 값 넣어주기
    $("#checkListItemAdd #hiddenCseq").val($(event.srcElement).data("cseq"));
    
    //해당 프로젝트의 멤버 정보 불러오기 
    $.ajax({
    	type: "GET",
    	url: "/orello/checklist/getmemberlist.do",
    	data: "cseq="+ $(event.srcElement).parent().parent().data("cseq"),
    	dataType: "json",
    	success: function(result) {	
    		/*
        		<input type="checkbox" id="member5" class="memberCheck">
                <label for="member5" class="memberPick">
                    <div class="memberBox">
                        <div class="memberPic"></div>
                        <span>MemberName</span>
                    </div>
                </label>
    		*/
    		$("#checkListItemAdd #memberlist").html("");
    		$(result).each(function(index, item) {
    			var temp = '';
    			temp += '<input type="radio" id="member' + item.paseq + '" class="memberCheck" name="paseq" value="' + item.paseq + '">'; 
    			temp += '<label for="member' + item.paseq +'" class="memberPick">'; 
    			temp += '<div class="memberBox">'; 
    			temp += '<div class="memberPic"></div>'; 
    			temp += '<span>' + item.name + '</span>'; 
    			temp += '</div>'; 
    			temp += '</label>'; 
 
    			$("#checkListItemAdd #memberlist").append(temp);
    			$("#checkListItemAdd .memberBox .memberPic").css("background-image", "url('/orello/images/"+ item.profile +"')");
    		});
    		
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}	
    });        
}



//추가모달창 첨부파일 추가 버튼 누를때마다 한줄씩 늘리기
function fileAdd() {
 
	var template = '';
	template += '<div class="fileUploadBox">';
	template += '<label for="afile' + fileNum + '"><i class="fa fa-file-o"></i>File</label>';
	template += '<input type="file" id="afile' + fileNum + '" readonly onchange="showFile();" name="afile' + fileNum + '">';
	template += '<input type="text" id="afileName' + fileNum + '">';
	template += '<i class="glyphicon glyphicon-remove deleteFile" onclick="fileDel();"></i>';
	template += '</div>';
              
	$("#checkListItemAdd #fileParent").append(template);
    fileNum++;
}
function modifyFileAdd() {
	 
	var template = '';
	template += '<div class="fileUploadBox">';
	template += '<label for="afile' + fileNum + '"><i class="fa fa-file-o"></i>File</label>';
	template += '<input type="file" id="afile' + fileNum + '" readonly onchange="showFile();" name="afile' + fileNum + '">';
	template += '<input type="text" id="afileName' + fileNum + '">';
	template += '<i class="glyphicon glyphicon-remove deleteFile" onclick="fileDel();"></i>';
	template += '</div>';
              
	$("#checkListModify #fileParent").append(template);
    fileNum++;
}

// 모달창 첨부파일 삭제 버튼 누를때마다 지우기
function fileDel() {
    $("i.deleteFile").click(function() {
        $(this).parent(".fileUploadBox").remove();
        fileNum--;
    });
}

//파일옆에있는 태그에 파일명 보여주기
function showFile() {
	var file = event.srcElement.value;
	event.srcElement.nextSibling.value = file;
	
}



/***** 수정 모달창 *****/

//모달창 열기
function openModify() {
	
	$('#checkListModify').modal();

	//이벤트 버블링으로 인한 현상 방지..(i 태그를 누르면 ciseq가 자꾸 undefined로 나타남)
	var ciseq = 0;
	
	if (event.srcElement.nodeName == "SPAN") {
		ciseq = $(event.srcElement).data("ciseq");
	} else {
		ciseq = $(event.srcElement).parent().data("ciseq");
	}
	$("#hiddenCiseq").val(ciseq);
	
	//세션에 담길 내용?? 로그인한 사람의 프로젝트 참여 번호..
	var paseq = $("#hiddenPaseq").val();
	
	//초기화 해주기
	$("#checkListModify .be-comment-block").html("");
	$("#checkListModify #fileParent").html("");
	$("#commentArea").val() == "";
		
	//체크리스트 항목 제목, 기간, 내용 가져오기
	$.ajax({
		type: "GET",
    	url: "/orello//checklist/getselecteditem.do",
    	data: "ciseq="+ ciseq,
    	dataType: "json",
    	success: function(result) {
    		
    		$("#checkListModify #titleArea").text(result.title);
    		$("#checkListModify #titleModify").val(result.title);
    		$("#checkListModify .checkListContent textarea").text(result.content);
    		$("#checkListModify #startpicker-input").val(result.startdate);
    		$("#checkListModify #endpicker-input").val(result.enddate);
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	});
	
	 //해당 프로젝트의 멤버 정보 불러오기 
    $.ajax({
    	type: "GET",
    	url: "/orello/checklist/getselectedmemberlist.do",
    	data: "ciseq="+ ciseq,
    	dataType: "json",
    	success: function(result) {	
    		/*
        		<input type="checkbox" id="member5" class="memberCheck">
                <label for="member5" class="memberPick">
                    <div class="memberBox">
                        <div class="memberPic"></div>
                        <span>MemberName</span>
                    </div>
                </label>
    		*/
    		$("#checkListModify #members").html("");
    		$(result).each(function(index, item) {
    			
    			//console.log(item.assign);
    			
    			var temp = '';

    			if (item.assign == 1) {
    				temp += '<input type="radio" id="member' + item.mseq + '" class="memberCheck" name="mseq" value="' + item.mseq + '" checked="checked">';    				
    			} else {
    				temp += '<input type="radio" id="member' + item.mseq + '" class="memberCheck" name="mseq" value="' + item.mseq + '">';    				    				
    			}
    			temp += '<label for="member' + item.mseq +'" class="memberPick">'; 
    			temp += '<div class="memberBox">'; 
    			temp += '<div class="memberPic"></div>'; 
    			temp += '<span>' + item.name + '</span>'; 
    			temp += '</div>'; 
    			temp += '</label>'; 
    			
    			$("#checkListModify #members").append(temp);
    			$("#checkListModify .memberBox .memberPic").css("background-image", "url('/orello/images/"+ item.profile +"')");
    		});
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}	
    });        
	
    //첨부파일 정보 불러오기
    $.ajax({
    	type: "GET",
    	url: "/orello/checklist/getselectedattachlist.do",
    	data: "ciseq="+ ciseq,
    	dataType: "json",
    	success: function(result) {
    		
    		$(result).each(function(index, item) {
    			/*
                <div class="fileUploadBox">
                	<i class="fa fa-file-o"></i>
					<span>[2020-08-08]</span>
					<span>asdkjads.jpg</span>
					<i class="glyphicon glyphicon-remove deleteFile" onclick="location.href='';"></i>
				</div>
    			*/
    			var temp = '';
    			temp += '<div class="fileUploadBox" id="attach'+ item.seq +'">';
    			temp += '<i class="fa fa-file-o"></i>';
    			temp += '<span>[' + item.regdate + ']</span>';
    			temp += '<span>' + item.orgfilename + '</span>';
    			temp += '<i class="glyphicon glyphicon-remove deleteFile" onclick="delattach('+ item.seq +');"></i>';
    			temp += '</div>';
    			
    			$("#checkListModify #fileParent").append(temp);
    			
    		});
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}    	
    });
	
	
	//댓글정보 불러오기
    $.ajax({
    	type: "GET",
    	url: "/orello/checklist/getselectedcommentlist.do",
    	data: "ciseq="+ ciseq,
    	dataType: "json",
    	success: function(result) {	
    		/*
    		 <div class="be-comment">
                <div class="be-img-comment">	
                    <img src="../images/3.png" alt="" class="be-ava-comment">
                </div>
                <div class="be-comment-content">
                    <span class="be-comment-name"></span>
                    <span class="be-comment-time">
                        <i class="fa fa-clock-o"></i>
                        May 27, 2015 at 3:14am
                    </span>
                	<p class="be-comment-text"></p>
                </div>
            </div> 
    		*/
    		$(result).each(function(index, item) {
    			
	    		var temp = '';
	    		temp += '<div class="be-comment" data-cmseq="' + item.seq + '">';
	    		temp += '<div class="be-img-comment"><img src="../images/'+ item.profile +'" class="be-ava-comment"></div>';
	    		temp += '<div class="be-comment-content">';
	    		temp += '<span class="be-comment-name">' + item.writer + '</span>';
	    		temp += '<span class="be-comment-time"><i class="fa fa-clock-o"></i>' + item.regdate + '</span>';
	    		temp += '<p class="be-comment-text">' + item.content + '</p>';
	    		temp += '</div>';
	    		temp += '</div>';
	    		
	    		$("#checkListModify .be-comment-block").append(temp);
    		});
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
    });
    
    //댓글 등록하기
    $("#commentEnter").click(function() {
    	
    	if ($("#commentArea").val() == "") {
    		
    		alert("댓글 내용을 입력하세요.");
    	
    	} else {
    		
    		$.ajax({
    			
    			type: "POST",
    			url: "/orello/checklist/addcommentok.do",
    			data: "ciseq="+ ciseq + "&paseq=" + paseq + "&content=" + $("#commentArea").val(),
    			dataType: "json",
    			success: function(result) {	
    				if (result.result == 1) {
    					
    					console.log(ciseq);
    					console.log(result);
    					var temp = '';
    		    		temp += '<div class="be-comment" data-cmseq="' + result.seq + '">';
    		    		temp += '<div class="be-img-comment"><img src="../images/'+ item.profile +'" class="be-ava-comment"></div>';
    		    		temp += '<div class="be-comment-content">';
    		    		temp += '<span class="be-comment-name">' + result.writer + '</span>';
    		    		temp += '<span class="be-comment-time"><i class="fa fa-clock-o"></i>' + result.regdate + '</span>';
    		    		temp += '<p class="be-comment-text">' + result.content + '</p>';
    		    		temp += '</div>';
    		    		temp += '</div>';
    		    		
    		    		$("#checkListModify .be-comment-block").append(temp);
    					
    				} else {
    					alert("failed");
    				}
    			},
    			error: function(a,b,c) {
    				console.log(a,b,c);
    			}
    		});
    	}
    });
    
    //삭제버튼 누르면 지우기
    $("#btnDelete").click(function() {
    	location.href="/orello/checklist/deletechecklistitemok.do?ciseq=" + ciseq;
    })
	
}


//수정 모달창에서 파일 삭제하기
function delattach(aseq) {
	$.ajax({
		type: "GET",
    	url: "/orello/checklist/deleteattachok.do",
    	data: "aseq="+ aseq,
    	dataType: "json",
    	success: function(result) {	
    		if (result.result == 1) {
    			var id = "#attach" + aseq;
    			$(id).remove();
    		}
    	},
    	error: function(a,b,c) {
    		console.log(a,b,c);
    	}
	}); 
}


//수정 모달창에서 삭제버튼 누르면 삭제시키기
function delCheckList() { 
    
    // 해당 체크리스트 항목의 최상위 태그 찾아놓기
    var listContent = $(this).parent().parent("li");
    $("#btnDelete").click(function() {
        // 삭제버튼 누르면 항목 지우기
        listContent.remove();
    });
}

// 모달창에서 체크리스트 항목 타이틀 수정
$("#checkListModify #titleArea").click(function(){
    $("#checkListModify #titleArea").css("display","none");
    $("#checkListModify #titleModify").css("display","block").val($("#checkListModify #titleArea").text());
});
$("#checkListModify #titleModify").keydown(function(key) {
    if (key.keyCode == 13) {
        $("#checkListModify #titleModify").css("display","none");
        $("#checkListModify #titleArea").css("display","inline").text($("#checkListModify #titleModify").val());
    }
});

// 리스트 타이틀 옆 삭제 아이콘 클릭시 삭제하기 
function deleteList() {
    
	var confirmflag = confirm("리스트를 삭제하시겠습니까?");
    
	if (confirmflag) {
		
    	$.ajax ({
    		//db로 보내기 
    		type: "GET",
    		url: "/orello/checklist/deletechecklistok.do",
    		data: "cseq=" + event.srcElement.dataset.cseq,
    		dataType: "json",
    		success: function(result) {
    			
    			if(result.result != 0 || result.result == null) {
    			
    				//data-seq로 해당 시퀀스 값을 가지고 있는 step을 화면에서 삭제하기 	
    				$("i[data-cseq ='" +  result.cseq +  "'").parent().parent().remove();
    			
    			} else {
    				alert("삭제에 실패하였습니다.");
    			}
    		},
    		error: function(a,b,c) {
    			console.log(a,b,c);
    		}
    	});
    }
}

//체크하면 상태 변경하기
function changeCheck(ciseq) {
	
	var flag = $(event.srcElement).prop("checked");	
	
	$.ajax({
		
		type: "GET",
		url: "/orello/checklist/changecheckok.do",
		data: "ciseq=" + ciseq + "&flag=" + flag,
		dataType: "json",
		success: function(result) {
			
			if (result.result == 1) {
				
				return;
				
			} else {
				event.srcElement.prop("checked", !flag);
				alert("failed");
			}
		},
		error: function(a,b,c) {
			console.log(a,b,c);
		}
	});
}

//메뉴바에 있는 체크리스트 추가버튼
$("#listAdd").click(function() {

	$.ajax({
		
		type: "GET",
		url: "/orello/checklist/addchecklistok.do",
		data: "pseq=" + $(this).data("pseq"),
		dataType: "json",
		success: function(result){
		
			// 리스트 추가를 위한 템플릿 선언
			var temp = "";
			temp += '<div class="steps" data-cseq="'+ result.seq +'">';
			temp += '<div class="listTitle"><h3>'+ result.title +'</h3><i class="glyphicon glyphicon-trash deleteList" data-cseq="'+ result.seq +'" onclick="deleteList();"></i></div>';
			temp += '<ul class="sortable connectedSortable"></ul>';
			temp += '<div class="addCheckList"><i class="glyphicon glyphicon-plus-sign" data-cseq="'+ result.seq +'" onclick="openItemAdd();"></i></div>';
			temp += '</div>';
			
			//추가하기
			$(".container").append(temp);
		},
		error: function(a,b,c) {
			console.log(a,b,c)
		}		
	});
});


//위치 이동 가능하도록
$(".sortable").sortable({   
    placeholder: "ui-sortable-placeholder",
    connectWith: ".connectedSortable"   
}).disableSelection();  

//날짜 선택창
var today = new Date();
var picker1 = tui.DatePicker.createRangePicker({
    startpicker: {
        date: today,
        input: '#startpicker-input',
        container: '#startpicker-container'
    },
    endpicker: {
        date: today,
        input: '#endpicker-input',
        container: '#endpicker-container'
    }
});
var picker2 = tui.DatePicker.createRangePicker({
    startpicker: {
        date: today,
        input: '#startpicker-input2',
        container: '#startpicker-container2'
    },
    endpicker: {
        date: today,
        input: '#endpicker-input2',
        container: '#endpicker-container2'
    }
});

