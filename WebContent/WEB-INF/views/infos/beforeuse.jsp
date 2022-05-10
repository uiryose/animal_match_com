<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

        <h4 class="title mb-3">ご利用方法</h4>

        <div class="mb-3">
            ユーザーの権限についての説明です。<br>
            動物に関するチャット取引はログイン後に可能です。
        </div>

        <div class="">
            <img class="col-12 px-0"
                src="<c:url value='/image/beforeuse.png' /> "
                alt="サイトの説明画像">
        </div>


        <h4 class="title my-3">その他</h4>
        <div>飼育の責任、動物に関する法律、利用規約など記載したいですが割愛します。</div>

    </c:param>
</c:import>

