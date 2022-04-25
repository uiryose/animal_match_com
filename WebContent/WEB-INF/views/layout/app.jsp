<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commLogin" value="${ForwardConst.CMD_LOGIN.getValue()}" />
<c:set var="commShowLogin" value="${ForwardConst.CMD_SHOW_LOGIN.getValue()}" />



<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><c:out value="アニマッチドットコム" /></title>

<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">


</head>
<body>
    <script src="<c:url value='/js/jquery-3.6.0.slim.min.js' />"></script>
    <script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>

    <div class="container">

<header class="jumbotron">
                <div class="login"><a href="<c:url value='?action=${actAuth}&command=${commShowLogin}' />">ログイン</a></div>


                <div class="d-flex justify-content-end m-0">
                    <div class="row ">
                        <div class="p-1 text-center"><a class="list-group-item list-group-item-success list-group-item-action py-0 px-3 " href="<c:url value='?action=${actCust}&command=${commNew}' />">ご利用案内 <br>個人の方へ</a></div>
                        <div class="p-1 text-center" ><a class="list-group-item list-group-item-success list-group-item-action py-0 px-3" href="<c:url value='?action=${actZoo}&command=${commNew}' />">ご利用案内 <br>動物園へ</a></div>
                        <div class="p-1 text-center" ><a class="list-group-item list-group-item-success list-group-item-action py-2.3 px-2" href="<c:url value='?action=${a}&command=${a}' />">制作コメント</a></div>
                        <div class="p-1 text-center" ><a class="list-group-item list-group-item-success list-group-item-action py-2.3 px-2" href="<c:url value='?action=${a}&command=${a}' />">お問い合わせ</a></div>
                    </div>
                </div>

<%--                 <c:if test="${sessionScope.login_employee != null}">
                    <div id="user_name">
                        <c:out value="${sessionScope.login_user.name}" />
                        &nbsp;さん&nbsp;&nbsp;&nbsp; <a
                            href="<c:url value='?action=${a}&command=${a}' />">ログアウト</a>
                    </div>
                </c:if> --%>

</header>
<!-- hedderここまで -->

        <div class="row">
            <div class="col-2 mt-3">
                <div class=" fixed-left">
                    <nav class="bg-info">
                        <ul class="nav flex-column m-0 border border-dark">
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actBase}&command=${commIdx}' />" class="nav-link py-3 text-dark border border-light px-2 list-group-item-action">トップページ</a></li>
                            <li class="nav-item mb-1"><a href="#" class="nav-link py-3 text-dark border border-light px-2 list-group-item-action">サイトの趣旨</a></li>
                            <li class="nav-item mb-1"><a href="#" class="nav-link py-3 text-dark border border-light px-2 list-group-item-action">ご利用の前に</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actZoo}&command=${commNew}' />" class="nav-link py-3 text-dark border border-light px-2 list-group-item-action">動物を掲載する</a></li>
                            <li class="nav-item mb-0"><a href="#" class="nav-link py-3 text-dark border border-light px-2 list-group-item-action">エサMatch.com</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div id="content" class="col-10">
                 ${param.content}
            </div>
        </div>

<footer class="py-3 mt-5 bg-secondary text-dark">
  <div class="container text-center">

    <div>アニマッチドットコム
    <small>東京都台東区上野中野下野9-9-9</small></div>
    <small>Copyright &copy;2022 Animal Match.com, All Rights Reserved.</small>
  </div>
</footer>

    </div>
</body>
</html>