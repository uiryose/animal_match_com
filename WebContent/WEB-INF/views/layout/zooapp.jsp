<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actAni" value="${ForwardConst.ACT_ANI.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commSell" value="${ForwardConst.CMD_SELLING.getValue()}" />
<c:set var="commSold" value="${ForwardConst.CMD_SOLD.getValue()}" />
<c:set var="commLogin" value="${ForwardConst.CMD_LOGIN.getValue()}" />
<c:set var="commShowLogin" value="${ForwardConst.CMD_SHOW_LOGIN.getValue()}" />
<c:set var="commLogout" value="${ForwardConst.CMD_LOGOUT.getValue()}" />



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

        <header class="">

            <c:if test="${sessionScope.login_user != null}">
                <div class="login"><a href="<c:url value='?action=${actCust}&command=${commIdx}' />">マイページ</a></div>
            </c:if>

        </header>
<!-- hedderここまで -->

        <div class="row">
            <div class="col-2 mt-3">
                <div class=" fixed-left">
                    <nav class="bg-secondary">
                        <ul class="nav flex-column m-0 border border-white">
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actBase}&command=${commIdx}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">ホーム</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actAni}&command=${commSell}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">掲載中の動物</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actAni}&command=${commNew}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">動物の新規登録</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actBase}&command=${commIdx}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">購入検討中の動物</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actAni}&command=${commSold}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">売約済みの動物</a></li>
                            <li class="nav-item mb-1"><a href="<c:url value='?action=${actZoo}&command=${commEdit}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">動物園情報の変更</a></li>
                            <li class="nav-item mb-0"><a href="<c:url value='?action=${actAuth}&command=${commLogout}' />" class="nav-link py-3 text-light border border-light px-2 list-group-item-action">ログアウト</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-10">
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

    <script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>
    <script>
      bsCustomFileInput.init();
    </script>
</body>
</html>