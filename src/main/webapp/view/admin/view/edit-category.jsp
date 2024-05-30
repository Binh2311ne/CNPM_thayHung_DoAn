<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url value="/view/admin/static" var="url"></c:url>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit Category</title>
    <!-- Bootstrap Styles -->
    <link href="${url}/css/bootstrap.css" rel="stylesheet" />
    <!-- Font Awesome Styles -->
    <link href="${url}/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom Styles -->
    <link href="${url}/css/custom.css" rel="stylesheet" />
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
    <div id="wrapper">
        <jsp:include page="/view/admin/view/nav-bar.jsp"></jsp:include>
        <!-- NAV TOP -->
        <jsp:include page="/view/admin/view/slide-bar.jsp"></jsp:include>
        <!-- NAV SIDE -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Edit Category</h1>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/admin/category/edit" method="POST">
                    <input type="hidden" name="id" value="${category.id}">
                    <label for="name">Category Name:</label>
                    <input type="text" id="name" name="name" value="${category.name}" required>
                    <br>
                    <!-- Save Changes Button -->
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
                <!-- Back to Category List Button -->
                <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-default">Back to Category List</a>
            </div>
        </div>
    </div>
    <!-- JQUERY SCRIPTS -->
    <script src="${url}/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="${url}/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="${url}/js/jquery.metisMenu.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="${url}/js/custom.js"></script>
</body>
</html>
