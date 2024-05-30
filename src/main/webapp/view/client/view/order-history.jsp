<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
   <meta charset="ISO-8859-1">
    <title>Order History</title>
    <!-- Add any necessary CSS and JS libraries here if needed -->
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">
<c:url value="/view/client/static" var="url"></c:url>

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css'
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet"
	href="${url}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${url}/css/shop.style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="${url}/css/headers/header-v5.css">
<link rel="stylesheet" href="${url}/css/footers/footer-v4.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="${url}/plugins/animate.css">
<link rel="stylesheet" href="${url}/plugins/line-icons/line-icons.css">
<link rel="stylesheet"
	href="${url}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${url}/plugins/jquery-steps/css/custom-jquery.steps.css">
<link rel="stylesheet"
	href="${url}/plugins/scrollbar/css/jquery.mCustomScrollbar.css">

<!-- Style Switcher -->
<link rel="stylesheet" href="${url}/css/plugins/style-switcher.css">

<!-- CSS Theme -->
<link rel="stylesheet" href="${url}/css/theme-colors/default.css"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet" href="${url}/css/custom.css">

</head>
<body>
<!-- Subiz -->
	<script>
		(function(s, u, b, i, z) {
			u[i] = u[i] || function() {
				u[i].t = +new Date();
				(u[i].q = u[i].q || []).push(arguments);
			};
			z = s.createElement('script');
			var zz = s.getElementsByTagName('script')[0];
			z.async = 1;
			z.src = b;
			z.id = 'subiz-script';
			zz.parentNode.insertBefore(z, zz);
		})(document, window, 'https://widgetv4.subiz.com/static/js/app.js',
				'subiz');
		subiz('setAccount', 'acqetkhujxyunuatbxzw');
	</script>
	<!-- End Subiz -->
<body class="header-fixed">
<div class="wrapper">
	
	<!--=== Header v5 ===-->
		<div class="header-v5 header-static">
			<!-- Topbar v3 -->
			<jsp:include page="topbar.jsp"></jsp:include>
			<!-- End Topbar v3 -->

			<!-- Navbar -->
			<jsp:include page="navbar.jsp"></jsp:include>
			<!-- End Navbar -->
		</div>
	
	<!--=== Breadcrumbs v4 ===-->
		<div class="breadcrumbs-v4">
			<div class="container">
				<span class="page-name">Check Out</span>
				<h1>
					Maecenas <span class="shop-green">enim</span> sapien
				</h1>
				<ul class="breadcrumb-v4-in">
					<li><a href="index.html">Home</a></li>
					<li><a href="">Product</a></li>
					<li class="active">Shopping Cart</li>
				</ul>
			</div>
			<!--/end container-->
		</div>
		<!--=== End Breadcrumbs v4 ===-->
<div class="content-md margin-bottom-30">
    <div class="container">
    <form class="shopping-cart" action="#">
        <div>
            <div class="header-tags">
                <div class="overflow-h">
                    <h2>Order History</h2>
                    <!-- Add any additional description if needed -->
                    <i class="rounded-x fa fa-check"></i>
                </div>
            </div>

            <section>
                <c:if test="${not empty orderHistory}">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Product Name</th>	
                                    <th>Quantity</th>  
                                     <th>Address</th>        <!-- Add this column -->
        							<th>Phone</th>                           
                                    <th>Total Price</th>
                                   
                                    <!-- Add other columns if needed -->
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="orderItem" items="${orderHistory}">
                                    <tr>
                                        <td>${orderItem.id}</td>
                                        <td>${orderItem.product.name}</td>
                                        <td>${orderItem.quantity}</td> 
                                         <td>${userAddress}</td>  <!-- Add this cell -->
           								 <td>${userPhone}</td>                                      
                                        <td>${orderItem.product.price * orderItem.quantity}</td>
                                        <!-- Add other data cells if needed -->
                                         <td>
            <form action="${pageContext.request.contextPath}/member/order/history" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${orderItem.id}">
         <button type="submit" style="color: white; background-color: red; padding: 10px 15px; border: none; cursor: pointer;">Hủy Đơn</button>
            </form>
        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <c:if test="${empty orderHistory}">
                    <p>No order history available.</p>
                </c:if>
            </section>
        </div>
        </form>
    </div>
    
    <!--/end container-->
</div>
<!--=== End Content Medium Part ===-->


<!--=== Footer v4 ===-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--=== End Footer v4 ===-->
<!-- Add any necessary JavaScript code here if needed -->
</div>

<!-- JS Global Compulsory -->
	<script src="${url}/plugins/jquery/jquery.min.js"></script>
	<script src="${url}/plugins/jquery/jquery-migrate.min.js"></script>
	<script src="${url}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- JS Implementing Plugins -->
	<script src="${url}/plugins/back-to-top.js"></script>
	<script src="${url}/plugins/smoothScroll.js"></script>
	<script src="${url}/plugins/jquery-steps/build/jquery.steps.js"></script>
	<script
		src="${url}/plugins/scrollbar/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script
		src="${url}/plugins/sky-forms-pro/skyforms/js/jquery.validate.min.js"></script>
	<!-- JS Customization -->
	<script src="${url}/js/custom.js"></script>
	<!-- JS Page Level -->
	<script src="${url}/js/shop.app.js"></script>
	<script src="${url}/js/forms/page_login.js"></script>
	<script src="${url}/js/plugins/stepWizard.js"></script>
	<script src="${url}/js/forms/product-quantity.js"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.initLogin();
			App.initScrollBar();
			StepWizard.initStepWizard();
			StyleSwitcher.initStyleSwitcher();
		});
	</script>

</body>
</body>
</html>
