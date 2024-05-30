<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <c:url value="/view/client/static" var="url"></c:url>
    <title>Edit User</title>

    <!-- Include the same CSS links as in myaccount.jsp -->
    <link rel="stylesheet" href="${url}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${url}/css/shop.style.css">
    <link rel="stylesheet" href="${url}/css/headers/header-v5.css">
    <link rel="stylesheet" href="${url}/css/footers/footer-v4.css">
    <link rel="stylesheet" href="${url}/plugins/animate.css">
    <link rel="stylesheet" href="${url}/plugins/line-icons/line-icons.css">
    <link rel="stylesheet" href="${url}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${url}/plugins/scrollbar/css/jquery.mCustomScrollbar.css">
    <link rel="stylesheet" href="${url}/css/pages/log-reg-v3.css">
    <link rel="stylesheet" href="${url}/css/theme-colors/default.css" id="style_color">
    <link rel="stylesheet" href="${url}/css/custom.css">
</head>
<body class="header-fixed">

    <!-- Header and Navbar -->
    <div class="wrapper">
        <div class="header-v5 header-static">
            <jsp:include page="topbar.jsp"></jsp:include>
            <jsp:include page="navbar.jsp"></jsp:include>
        </div>

        <!-- Breadcrumbs -->
        <div class="breadcrumbs-v4">
            <div class="container">
                <h1>SD<span class="shop-green">COMPUTER</span></h1>
                <ul class="breadcrumb-v4-in">
                    <li><a href="${pageContext.request.contextPath}/welcome">Trang chủ</a></li>
                    <li class="active">Edit User</li>
                </ul>
            </div>
        </div>

        <!-- Edit User Form -->
        <div class="container">
            <h2>Edit User</h2>
            <form action="${pageContext.request.contextPath}/member/editUser" method="post" class="log-reg-v3">
                <input type="hidden" name="id" value="${user.id}" />

                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" value="${user.username}" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" 
                    value="${sessionScope.account.password }"/>
                    		
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" value="${user.address}" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" value="${user.phone}" class="form-control" />
                </div>


                <div class="form-group">
                    <input type="submit" value="Update User" class="btn-u btn-u-default" />
                </div>
            </form>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>
    </div>

    <!-- JavaScripts -->
    <script src="${url}/plugins/jquery/jquery.min.js"></script>
    <script src="${url}/plugins/jquery/jquery-migrate.min.js"></script>
    <script src="${url}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${url}/plugins/back-to-top.js"></script>
    <script src="${url}/plugins/smoothScroll.js"></script>
    <script src="${url}/plugins/sky-forms-pro/skyforms/js/jquery.validate.min.js"></script>
    <script src="${url}/plugins/scrollbar/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="${url}/js/custom.js"></script>
    <script src="${url}/js/shop.app.js"></script>
    <script src="${url}/js/forms/page_registration.js"></script>
    <script>
        jQuery(document).ready(function() {
            App.init();
            App.initScrollBar();
            Registration.initRegistration();
        });
    </script>
</body>
</html>
