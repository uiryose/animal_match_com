<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">



    <c:import url="_chatform.jsp" />
<!-- 個人ページ用のチャット送信 -->
    <form method="POST" action="<c:url value='?action=${actChat}&command=${commCrt}&id=${animal.id}&with=${animal.zoo.user.id}'/>">
        <div class="form-group row">
            <div class="col-2"></div>
            <div class="form-group col-8 pt-4 ">
                <label class="mb-0" for="${AttributeConst.CHAT_CONTENT.getValue()}">新規メッセージ</label><br>
                <textarea class="form-control "
                    name="${AttributeConst.CHAT_CONTENT.getValue()}" rows="3" required>${chat.content}</textarea>
            </div>
            <input type="hidden" name="${AttributeConst.CHAT_WITH.getValue()}" value="${animal.zoo.user.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-info col-8">送信する</button>
            </div>
        </div>
    </form>

  </c:param>
</c:import>