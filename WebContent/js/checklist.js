function addCheckList () { $(".addCheckList i").click(function() {
    // 모달창으로 추가 띄우기
    $('#checkListAdd').modal();

    // 어떤 리스트의 추가 버튼을 눌렀는지 찾기
    var list =  $(this).parent().siblings("ul");

    // 체크리스트 항목 추가를 위한 템플릿
    var chkListTemplate = `                <li>
            <input id='label-4' type='checkbox'/>
            <div>
                <label for='label-4'>
                    <h4>Date with babe</h4>  
                    <span><i class="glyphicon glyphicon-user"></i> Assigned to: <span class="assigned"></span></span><br>   
                </label>
                <span class="modify"><i class="glyphicon glyphicon-menu-hamburger"></i> <i class="glyphicon glyphicon-paperclip">8</i> <i class="glyphicon glyphicon-comment">10</i></span>   
            </div>
        </li>`;

    // + 버튼을 누를때마다 이벤트가 누적됨.
    $("#addListBtn").unbind();
        
    // 저장 버튼 누르면 항목 추가
    $("#addListBtn").click(function() {
        console.log(list);
        list.append(chkListTemplate); 
        $('#checkListAdd').modal('hide');
    });
});}
// 추가된 항목에도 적용되도록 함수 호출
addCheckList();

// 수정 모달창에서 삭제버튼 누르면 삭제시키기
function delCheckList() { $(".modify").click(function() {

    // 모달창 열기
    $('#checkListModify').modal();
    
    // 해당 체크리스트 항목의 최상위 태그 찾아놓기
    var listContent = $(this).parent().parent("li");
    $("#btnDelete").click(function() {
        // 삭제버튼 누르면 항목 지우기
        listContent.remove();
    });

}); }
// 추가된 항목에도 적용되도록 함수 호출
delCheckList();


// 모달창에서 체크리스트 항목 타이틀 수정
$("#titleArea").dblclick(function(){
    $("#titleArea").css("display","none");
    $("#titleModify").css("display","block").val($("#titleArea").text());
});
$("#titleModify").keydown(function(key) {
    if (key.keyCode == 13) {
        $("#titleModify").css("display","none");
        $("#titleArea").css("display","inline").text($("#titleModify").val());
    }
});

// 리스트 타이틀 옆 삭제 아이콘 클릭
function deleteList() {$(".deleteList").click(function() {
    var confirmflag = confirm("리스트를 삭제하시겠습니까?");
    if (confirmflag) {
        $(this).parent().parent().remove();
    }
});}
deleteList();

// 리스트 추가를 위한 템플릿 선언
var listTemplate = 
        `<div class="steps">
            <div class="listTitle"><h3>List Title</h3><i class="glyphicon glyphicon-trash deleteList"></i></div>
            <ul class="sortable connectedSortable">
            </ul>
            <div class="addCheckList">
                <i class="glyphicon glyphicon-plus-sign"></i>
            </div>
        </div>`;

$("#listAdd").click(function() {
    $(".container").append(listTemplate);
    addCheckList();
    deleteList();
});

// 모달창 첨부파일 추가 버튼 누를때마다 한줄씩 늘리기
var fileNum = 2;
var fileTemplate = `<div class="fileUploadBox">
                        <label for="file${fileNum}"><i class="fa fa-file-o"></i>File</label>
                        <input type="file" id="file${fileNum}" onchange="javascript:document.getElementById('fileName${fileNum}').value = this.value"> 
                        <input type="text" id="fileName${fileNum}">
                        <i class="glyphicon glyphicon-remove deleteFile"></i>
                        <i class="glyphicon glyphicon-plus addFile"></i>
                    </div>`;
function fileAdd() {
    $("i.addFile").click(function() {
        $("#fileParent").append(fileTemplate);
    });
    fileNum++;
}
fileAdd();

// 모달창 첨부파일 삭제 버튼 누를때마다 지우기
function fileDel() {
    $("i.deleteFile").click(function() {
        $(this).parent(".fileUploadBox").remove();
        fileNum--;
    });
}
fileDel();


// 프로젝트 홈으로 이동
$("#goToProjectHome").click(function() {
    location.href = "projectMain.html";
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
    },
    // selectableRanges: [
    //     [today, new Date(today.getFullYear() + 1, today.getMonth(), today.getDate())]
    // ]
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
    },
    // selectableRanges: [
    //     [today, new Date(today.getFullYear() + 1, today.getMonth(), today.getDate())]
    // ]
});

