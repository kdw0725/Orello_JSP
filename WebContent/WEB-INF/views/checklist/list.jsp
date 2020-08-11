<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/orello/css/jquery-ui.css">
<link rel="stylesheet" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">
<link rel="stylesheet" href="/orello/css/checklist.css">

<style>

</style>
<%@ include file="/inc/asset.jsp"%>
</head>
<body>
	<%@ include file="/inc/header.jsp"%>
	<section id="content" class="container">
	
        <div id="title">
            <h1>Check List</h1>
            <div class="titleBarFunction" id="listAdd"><i class="glyphicon glyphicon-plus-sign"></i> <span>ADD</span></div>
            <div class="titleBarFunction" id="goToProjectHome"><i class="glyphicon glyphicon-home"></i> <span>PROJECT</span></div>
        </div>
        
        <c:forEach items="${list}" var="dto">
        <div class="steps">
            <div class="listTitle"><h3>${dto.title}</h3><i class="glyphicon glyphicon-trash deleteList"></i></div>
            <ul class="sortable connectedSortable">
                
        		<c:forEach items="${dto.list}" var="idto">
	                <li>
	                    <input id='item${idto.seq}' type='checkbox'/>
	                    <div>
	                        <label for='item${idto.seq}'>
	                            <h4>${idto.title}</h4>
	                            <span><i class="glyphicon glyphicon-user"></i> Assigned to: ${idto.name}</span><br>
	                        </label>
	                        <span class="modify"><i class="glyphicon glyphicon-menu-hamburger"></i> <i class="glyphicon glyphicon-paperclip"></i> ${idto.attachcount} <i class="glyphicon glyphicon-comment"></i> ${idto.commentcount}</span>   
	                    </div>
	                </li>
                </c:forEach>
                
            </ul>
            <div class="addCheckList">
                <i class="glyphicon glyphicon-plus-sign"></i>
            </div>
        </div>   
        </c:forEach>


    <!-- 체크리스트 항목 수정&확인 모달 -->
    <div class="modal fade" id="checkListModify">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <input id="titleModify"></input>
                <h4 class="modal-title" id="titleArea">CheckList Title</h4>
            </div>
            <div class="modal-body">
                <div class="modal-menu">
                    
                    <h5>업무 담당자</h5>
                    <div>
                        <input type="checkbox" id="member1" class="memberCheck">
                        <label for="member1" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member2" class="memberCheck">
                        <label for="member2" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member3" class="memberCheck">
                        <label for="member3" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member4" class="memberCheck">
                        <label for="member4" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member5" class="memberCheck">
                        <label for="member5" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member6" class="memberCheck">
                        <label for="member6" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                    </div>
                    <hr>
                    <h5>업무 기간</h5>
                    <div id="datePicker">
                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                            <input id="startpicker-input" type="text" aria-label="Date">
                            <span class="tui-ico-date"></span>
                            <div id="startpicker-container" style="margin-left: -1px;"></div>
                        </div>
                        <span>to</span>
                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                            <input id="endpicker-input" type="text" aria-label="Date">
                            <span class="tui-ico-date"></span>
                            <div id="endpicker-container" style="margin-left: -1px;"></div>
                        </div>
                    </div>
                    <hr>
                    <h5>내용</h5>                    
                    <div class="checkListContent">
                        <textarea resize="none" disabled>Lorem ipsum dolor, sit amet consectetur adipisicing elit. At alias voluptatum nisi minima, blanditiis inventore! Repudiandae consequuntur veritatis doloremque similique ea. Ipsam, aspernatur! Libero ut, dicta quam blanditiis cupiditate earum?</textarea>
                    </div>
                    <div id="fileParent">
                        <div class="fileUploadBox">
                            <label for="file1"><i class="fa fa-file-o"></i>File</label>
                            <input type="file" id="file1" readonly   onchange="javascript:document.getElementById('fileName1').value = this.value" > 
                            <input type="text" id="fileName1">
                            <i class="glyphicon glyphicon-remove deleteFile"></i>
                            <i class="glyphicon glyphicon-plus addFile"></i>
                        </div>
                    </div>
                    
                    <hr>
                    
                    <h5>댓글(<span>3</span>)</h5>                    
                    <div class="be-comment-block">
                        <div class="be-comment">
                            <div class="be-img-comment">	
                                <a href="blog-detail-2.html">
                                    <img src="../images/3.png" alt="" class="be-ava-comment">
                                </a>
                            </div>
                            <div class="be-comment-content">
                                    <span class="be-comment-name">
                                        <a href="blog-detail-2.html">Ravi Sah</a>
                                        </span>
                                    <span class="be-comment-time">
                                        <i class="fa fa-clock-o"></i>
                                        May 27, 2015 at 3:14am
                                    </span>
                                <p class="be-comment-text">
                                    Pellentesque gravida tristique ultrices. 
                                    Sed blandit varius mauris, vel volutpat urna hendrerit id. 
                                    Curabitur rutrum dolor gravida turpis tristique efficitur.
                                </p>
                            </div>
                        </div>
                        <div class="be-comment">
                            <div class="be-img-comment">	
                                <a href="blog-detail-2.html">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="" class="be-ava-comment">
                                </a>
                            </div>
                            <div class="be-comment-content">
                                <span class="be-comment-name">
                                    <a href="blog-detail-2.html">Phoenix, the Creative Studio</a>
                                </span>
                                <span class="be-comment-time">
                                    <i class="fa fa-clock-o"></i>
                                    May 27, 2015 at 3:14am
                                </span>
                                <p class="be-comment-text">
                                    Nunc ornare sed dolor sed mattis. In scelerisque dui a arcu mattis, at maximus eros commodo. Cras magna nunc, cursus lobortis luctus at, sollicitudin vel neque. Duis eleifend lorem non ant. Proin ut ornare lectus, vel eleifend est. Fusce hendrerit dui in turpis tristique blandit.
                                </p>
                            </div>
                        </div>
                        <div class="be-comment">
                            <div class="be-img-comment">	
                                <a href="blog-detail-2.html">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="" class="be-ava-comment">
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
                                    Cras magna nunc, cursus lobortis luctus at, sollicitudin vel neque. Duis eleifend lorem non ant
                                </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <textarea class="form-input" placeholder="Your text"></textarea>
                            <input type="button" id="commentEnter"class="btn btn-info pull-right" value="submit"></input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnDelete" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-info">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
    </div>

      <!-- 체크리스트 항목 추가 모달 -->
    <div class="modal fade" id="checkListAdd">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">CheckList Title</h4>
            </div>
            <div class="modal-body">
                <div class="modal-menu">
                    
                    <h5>업무 담당자</h5>
                    <div>
                        <input type="checkbox" id="member1" class="memberCheck">
                        <label for="member1" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member2" class="memberCheck">
                        <label for="member2" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member3" class="memberCheck">
                        <label for="member3" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member4" class="memberCheck">
                        <label for="member4" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member5" class="memberCheck">
                        <label for="member5" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                        <input type="checkbox" id="member6" class="memberCheck">
                        <label for="member6" class="memberPick">
                            <div class="memberBox">
                                <div class="memberPic"></div>
                                <span>MemberName</span>
                            </div>
                        </label>
                    </div>
                    <hr>
                    <h5>업무 기간</h5>
                    <div id="datePicker">
                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                            <input id="startpicker-input2" type="text" aria-label="Date">
                            <span class="tui-ico-date"></span>
                            <div id="startpicker-container2" style="margin-left: -1px;"></div>
                        </div>
                        <span>to</span>
                        <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                            <input id="endpicker-input2" type="text" aria-label="Date">
                            <span class="tui-ico-date"></span>
                            <div id="endpicker-container2" style="margin-left: -1px;"></div>
                        </div>
                    </div>
                    <hr>
                    <h5>내용</h5>                    
                    <div class="checkListContent">
                        <textarea resize="none" disabled>Lorem ipsum dolor, sit amet consectetur adipisicing elit. At alias voluptatum nisi minima, blanditiis inventore! Repudiandae consequuntur veritatis doloremque similique ea. Ipsam, aspernatur! Libero ut, dicta quam blanditiis cupiditate earum?</textarea>
                    </div>
                    <!-- <hr> -->
                    <div class="checkListFile">
                        <input type="file">

                    </div>
                </div>
            <div class="modal-footer">

            <button type="button" class="btn btn-info" id="addListBtn">Save</button>
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
    </div>

	
	</section>
	<%@ include file="/inc/footer.jsp"%>
	
	<script src="/orello/js/jquery-ui.js"></script>
	<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.js"></script>
	<script src="/orello/js/checklist.js"></script>
	<script>
	
		
	    
	</script>
</body>
