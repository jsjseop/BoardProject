<%@ page contentType="text/html; charset=UTF-8" language="java" %> 
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
  <!-- Brand Logo -->
  <a href="${path}/" class="brand-link">
    <img src="${path}/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
    <span class="brand-text font-weight-light">AdminLTE 3</span>
  </a>

  <!-- Sidebar -->
  <div class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
       <c:if test="${empty login}">
        <div class="image">
          <img src="${path}/dist/img/default-150x150.png" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
               <%-- Status --%>
           	<a href="#"><i class="fa fa-circle text-danger"></i> Guest</a>
        </div>
       </c:if>
       
       <c:if test="${not empty login}">
        <div class="image">
          <img src="${path}/dist/img/default-150x150.png" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
               <%-- Status --%>
           	<a href="#"><i class="d-block"></i> ${login.userName}</a>
        </div>
       </c:if>
     </div>

    <!-- Sidebar Menu -->
    <nav class="mt-2">
      <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
        <li class="nav-item">
          <a href="#" class="nav-link active">
            <i class="nav-icon fas fa-tachometer-alt"></i>
            <p>
              기본 게시판
              <i class="right fas fa-angle-left"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
          	<li class="nav-item">
	          <a href="${path}/article/write" class="nav-link">
	            <i class="nav-icon fas fa-th"></i>
	            <p>
	              글 작성
	              <span class="right badge badge-danger">New</span>
	            </p>
	          </a>
	        </li>
            <li class="nav-item">
              <a href="${path}/article/list" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>글 목록</p>
              </a>
            </li>
          </ul>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link active">
            <i class="nav-icon fas fa-tachometer-alt"></i>
            <p>
              페이징 게시판
              <i class="right fas fa-angle-left"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
          	<li class="nav-item">
	          <a href="${path}/article/paging/write" class="nav-link">
	            <i class="nav-icon fas fa-th"></i>
	            <p>
	              글 작성
	              <span class="right badge badge-danger">New</span>
	            </p>
	          </a>
	        </li>
            <li class="nav-item">
              <a href="${path}/article/paging/list" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>글 목록(페이징)</p>
              </a>
            </li>
          </ul>
        </li>
        <li class="nav-item menu-open">
          <a href="#" class="nav-link active">
            <i class="nav-icon fas fa-tachometer-alt"></i>
            <p>
              검색 페이징 게시판
              <i class="right fas fa-angle-left"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
          	<li class="nav-item">
	          <a href="${path}/article/paging/search/write" class="nav-link">
	            <i class="nav-icon fas fa-th"></i>
	            <p>
	              글 작성
	              <span class="right badge badge-danger">New</span>
	            </p>
	          </a>
	        </li>
            <li class="nav-item">
              <a href="${path}/article/paging/search/list" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>글 목록(검색)</p>
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
    <!-- /.sidebar-menu -->
  </div>
  <!-- /.sidebar -->
</aside>