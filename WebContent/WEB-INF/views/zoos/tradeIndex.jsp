<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commTrdIdx" value="${ForwardConst.CMD_TRADE_INDEX.getValue()}" />
<c:set var="commTrdCrt" value="${ForwardConst.CMD_TRADE_CREATE.getValue()}" />


<c:import url="../layout/zooapp.jsp">
  <c:param name ="content">

    <h3 class="mt-3 mb-0 py-1 col-12 text-white text-center bg-info rounded">チャット取引画面</h3>

    <c:import url="../chats/_chatform.jsp" />

<!-- 動物園ページの販売用のチャット送信画面 -->
    <form method="POST" action="<c:url value='?action=${actZoo}&command=${commTrdCrt}&id=${animal.id}&with=${buy_user_id}'/>">
        <div class="form-group row">
            <div class="col-2"></div>
            <div class="form-group col-8 pt-4 ">
                <label class="mb-0" for="${AttributeConst.CHAT_CONTENT.getValue()}">新規メッセージ</label><br>
                <textarea class="form-control "
                    name="${AttributeConst.CHAT_CONTENT.getValue()}" rows="3" required>${chat.content}</textarea>
            </div>
            <input type="hidden" name="${AttributeConst.CHAT_WITH.getValue()}" value="${buy_user_id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <div class="col-12 text-center">
               <c:choose>
                   <c:when
                       test="${animal.soldFlag != AttributeConst.SOLD_FLAG_TRUE.getIntegerValue()}">
                       <button type="submit" class="btn btn-info col-8">送信する</button>
                   </c:when>
                   <c:otherwise>
                       <div class="btn btn-warning col-8">この動物は販売終了したため、チャットの利用はできません</div>
                   </c:otherwise>
               </c:choose>
            </div>
        </div>
    </form>

  </c:param>
</c:import>