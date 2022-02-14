<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <%@ include file = "../../include/head.jsp" %>
<style>
.fileDrop {
    width: 100%;
    height: 200px;
    border: 2px dotted #0b58a2;
}
</style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <%@ include file = "../../include/main_header.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file = "../../include/left_column.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Starter Page</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="col-lg-12"> 
        	<form role="form" id="writeForm" method="post" action="${path}/article/paging/search/write"> 
        		<div class="card"> 
        			<div class="card-header with-border"> 
        				<h3 class="card-title">게시글 작성</h3> 
        			</div> 
        			<div class="card-body"> 
        				<div class="form-group"> 
        					<label for="title">제목</label> 
        					<input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요"> 
        				</div> 
        				<div class="form-group"> 
        					<label for="content">내용</label> 
        					<textarea class="form-control" id="content" name="content" rows="30" placeholder="내용을 입력해주세요" style="resize: none;"></textarea> 
        				</div> 
        				<div class="form-group"> 
        					<label for="writer">작성자</label> 
        					<input class="form-control" id="writer" name="writer" value="${login.userId}" readonly> 
        				</div> 
        				<%--첨부파일 영역 추가--%>
		                <div class="form-group">
		                    <div class="fileDrop">
		                        <br/>
		                        <br/>
		                        <br/>
		                        <br/>
		                        <p class="text-center"><i class="fa fa-paperclip"></i> 첨부파일을 드래그해주세요.</p>
		                    </div>
		                </div>
        			</div> 
        			<%--첨부파일 영역 추가--%>
        			<div class="card-footer">
		                <ul class="mailbox-attachments clearfix uploadedFileList"></ul>
		            </div>
        			<div class="card-footer"> 
        				<button type="button" class="btn btn-primary"><i class="fa fa-list"></i> 목록</button> 
	        			<div class="float-right"> 
	        				<button type="reset" class="btn btn-warning"><i class="fa fa-reply"></i> 초기화</button> 
	        				<button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 저장</button> 
	        			</div> 
        			</div> 
        		</div> 
       		</form> 
       	</div>
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <%@ include file = "../../include/main_footer.jsp" %>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<%@ include file = "../../include/plugin_js.jsp" %>
</body>
<script id="fileTemplate" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img">
            <img src="{{imgSrc}}" alt="Attachment">
        </span>
        <div class="mailbox-attachment-info">
            <a href="{{originalFileUrl}}" class="mailbox-attachment-name">
                <i class="fa fa-paperclip"></i> {{originalFileName}}
            </a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
                <i class="far fa-trash-alt"></i>
            </a>
        </div>
    </li>
</script>
<script type="text/javascript" src="/resources/dist/js/article_file_upload.js"></script>
<script>
	//게시글 저장 버튼 클릭 시
	$("#writeForm").submit(function(event){
		event.preventDefault();
		var that = $(this);
		filesSubmit(that);
	});
	
	//파일 삭제 버튼 클릭 시
	$(document).on("click", ".delBtn", function(event){
		event.preventDefault();
		var that = $(this);
		deleteFileWrtPage(that);
	});
	
	//이미지 클릭 시 lightbox 팝업 호출
	$(document).on('click', '[data-toggle="lightbox"]', function(event) {
        event.preventDefault();
        $(this).ekkoLightbox();
    });
</script>
</html>
