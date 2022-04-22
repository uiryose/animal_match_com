<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><c:out value="アニマルマッチドットコム：システム" /></title>

<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">


</head>
<body>
<script src="<c:url value='/js/jquery-3.6.0.slim.min.js' />"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>

    <div  class="container">
        <div id="header">
            <div id="header_menu">
                <h1 class="text-light">アニマルマッチドットコム</h1>
            </div>
        </div>


        <div class="row">
            <div class="col-2">
                <div class="sidebar_fixed fixed-left">
                    <nav class="bg-success">
                        <ul class="nav flex-column m-0 p-3 text-dark">
                            <li class="nav-item mb-2"><a href="#" class="nav-link">メニューサンプル１</a></li>
                            <li class="nav-item mb-2"><a href="#" class="nav-link">メニューサンプル２</a></li>
                            <li class="nav-item mb-2"><a href="#" class="nav-link">メニューサンプル３</a></li>
                            <li class="nav-item mb-2"><a href="#" class="nav-link">メニューサンプル４</a></li>
                        </ul>
                    </nav>
                </div>
            </div>


            <div id="content" class="col-10">
                 ${param.content}
            </div>



        </div>
        <div id="footer">by Taro Kirameki.</div>
    </div>
</body>
</html>