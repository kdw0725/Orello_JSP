<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img src="<%= request.getContextPath() %>/images/logo.png" alt="Logo" id="logo-img" style="width: 20px;" /></a>
          </div>
      
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li class="active"><a href="#"><i class="glyphicon glyphicon-home"></i>&nbsp;Home</span><span class="sr-only">(current)</span></a></li>
              <li><a href="#"><i class="glyphicon glyphicon-bullhorn"></i>&nbsp; Notice</span></a></li>
              <li><a href="#"><i class="glyphicon glyphicon-folder-open"></i>&nbsp; Project</span></a></li>
              <li><a href="#"><i class="glyphicon glyphicon-question-sign"></i>&nbsp; QnA</span></a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search" id="search">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
              </div>
              <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
            </form>
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">User Name<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Profile</a></li>
                  <li><a href="#">Friends</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Log out</a></li>
                </ul>
              </li>
            </ul>
            <span class="nav navbar-nav navbar-right" id="alermArea">
              <i class="far fa-envelope"></i>
              <span class="alermCount">1</span>
              <i class="far fa-bell"></i>
              <span class="alermCount">1</span>
            </span>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>