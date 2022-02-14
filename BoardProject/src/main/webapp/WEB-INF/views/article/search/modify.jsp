<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <%@ include file = "../../include/head.jsp" %>
</head>
<style>
.fileDrop {
    width: 100%;
    height: 200px;
    border: 2px dotted #0b58a2;
}
</style>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <%@ include file = "../../include/main_header.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file = "../../include/left_column.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="col-lg-12"> 
        	<form role="form" id="writeForm" method="post" action="${path}/article/paging/modify"> 
	        	<div class="card"> 
	        		<div class="card-header"> 
	        			<h3 class="card-title">게시글 수정</h3> 
	        		</div> 
	        		<div class="card-body"> 
	        			<input type="hidden" name="article_no" value="${article.article_no}"> 
	        			<input type="hidden" name="page" value="${searchCriteria.page}">
	        			<input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}">
	        			<input type="hidden" name="searchType" value="${searchCriteria.searchType}">
	        			<input type="hidden" name="keyword" value="${searchCriteria.keyword}">
	        			<div class="form-group"> 
	        				<label for="title">제목</label> 
	        				<input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" value="${article.title}"> 
	        			</div> 
	        			<div class="form-group"> 
	        				<label for="content">내용</label> 
	        				<textarea class="form-control" id="content" name="content" rows="30" placeholder="내용을 입력해주세요" style="resize: none;">${article.content}</textarea> 
	        			</div> 
	        			<div class="form-group"> 
	        				<label for="writer">작성자</label> 
	        				<input class="form-control" id="writer" name="writer" value="${article.writer}" readonly> 
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
	        				<button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-trash"></i> 취소</button> 
	        				<button type="submit" class="btn btn-success modBtn"><i class="fa fa-save"></i> 수정 저장</button> 
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
	$(document).ready(function () { 
		var article_no = "${article.article_no}"; // 현재 게시글 번호
		var formObj = $("form[role='form']"); 
		console.log(formObj); 
		
		$(".modBtn").on("click", function () { 
			formObj.submit(); 
		}); 
		
		$(".cancelBtn").on("click", function () { 
			history.go(-1); 
		}); 
		
		$(".listBtn").on("click", function () { 
			self.location = "${path}/article/paging/search/list?page=${searchCriteria.page}"
					+ "&perPageNum=${searchCriteria.perPageNum}"
					+ "&searchType=${searchCriteria.searchType}"
					+ "&keyword=${searchCriteria.keyword}"
		}); 
		
		// 첨부파일 삭제 버튼 클릭 이벤트
		$(document).on("click", ".delBtn", function (event) {
		    event.preventDefault();
		    if (confirm("삭제하시겠습니까? 삭제된 파일은 복구할 수 없습니다.")) {
		        var that = $(this);
		        deleteFileModPage(that, articleNo);
		    }
		});
		
		// 첨부파일 목록 호출
		getFiles(article_no);

		// 수정 처리시 첨부파일 정보도 함께 처리
		$("#modifyForm").submit(function (event) {
		    event.preventDefault();
		    var that = $(this);
		    filesSubmit(that);
		});
	});
</script>
</html>
