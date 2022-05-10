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
    <c:param name="content">

        <h3 class="mb-0 py-1 col-12 text-white text-center bg-primary rounded">チャット取引画面</h3>

        <c:import url="_chatform.jsp" />

<!-- 個人ページ用のチャット送信 -->
        <form method="POST"
            action="<c:url value='?action=${actChat}&command=${commCrt}&id=${animal.id}&with=${animal.zoo.user.id}'/>">
            <div class="form-group row">
                <div class="col-2"></div>
                <div class="form-group col-8 pt-4 ">
                    <label class="mb-0" for="${AttributeConst.CHAT_CONTENT.getValue()}">新規メッセージ</label><br>
                    <textarea class="form-control "
                        name="${AttributeConst.CHAT_CONTENT.getValue()}" rows="3" required>${chat.content}</textarea>
                </div>
                <input type="hidden" name="${AttributeConst.CHAT_WITH.getValue()}"
                    value="${animal.zoo.user.id}" /> <input type="hidden"
                    name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                <div class="col-12 text-center">
                    <div class="col-2 px-0"></div>
                    <c:choose>
                        <c:when
                            test="${animal.soldFlag != AttributeConst.SOLD_FLAG_TRUE.getIntegerValue()}">
                            <button type="submit" class="btn btn-primary col-8">送信する</button>
                        </c:when>
                        <c:otherwise>
                            <div class="btn btn-warning col-8">この動物は販売終了したため、チャットの利用はできません</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
        <div class="mt-5 d-flex justify-content-end">
            <a class="btn btn-primary my-0 py-1 justify-content-right"
                href="<c:url value='?action=${actCust}&command=${commIdx}' />">
                取引の一覧に戻る</a>
        </div>

    </c:param>
</c:import>