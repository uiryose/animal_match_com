<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />


<meta name="viewport" content="width=device-width,initial-scale=1">

<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShowSell" value="${ForwardConst.CMD_SHOWSELL.getValue()}" />



<!-- 販売個体 概略 -->
    <div class="row p-1">
        <div class="col-8 order-2 pl-0">
            <table class="table mb-0">
                <tbody>
                    <tr class="pr-0">
                        <th class="col-2">売り主</th>
                        <td class="col-4"><c:out value="${animal.zoo.zooName}" />（ <c:out
                                value="${animal.zoo.region}" /> ）</td>
                        <td class="col-2 py-1 border-bottom"><a class="btn btn-info"
                            href="<c:url value='?action=${actBase}&command=${commShowSell}&id=${animal.id}' />">詳細を確認</a></td>
                    </tr>
                    <tr>
                        <th class="col-2">名前</th>
                        <td class="col-8"><c:out value="${animal.animalBase.baseName}" />（<c:out
                                value="${animal.nickname}" />）</td>
                    </tr>
                    <tr class="border-bottom">
                        <th class="col-2">年齢</th>
                        <td class="col-4"><c:out value="${animal.animalAge}" />&nbsp;才</td>
                        <td class="col-2"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-4">
            <img src="<c:url value='/image/animal/${animal.animalImage}' /> "
                alt="${animal.nickname}の画像" class="img-fluid">
        </div>
    </div>
<!-- チャット画面 -->
       <h3 class="my-4 py-1 col-12 text-white text-center bg-info rounded">チャット取引画面</h3>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10 bg-light px-4 pt-4 border">

            <c:forEach var="comment" items="${comments}">
                <c:choose>
                    <c:when test="${comment.chat.myUser.id == login_user.id }">
                        <div class="message d-flex flex-row-reverse align-items-start mb-2">
                            <div
                                class="message-icon rounded-circle bg-secondary text-white fs-3">
                                自分</div>
                            <p class="message-text mr-3 px-3 py-2 ms-2 mb-0 bg-info align-self-center">
                                <c:out value="${comment.content}" />
                            </p>
                            <div class="mr-2 pb-1 align-self-end ">
                                <fmt:parseDate value="${comment.createdAt}"
                                    pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                                <fmt:formatDate value="${createDay}" pattern="yyyy/MM/dd" />
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="message d-flex flex-row align-items-start mb-2">
                            <div
                                class="message-icon rounded-circle bg-secondary text-white fs-3">
                                <i class="fas fa-user"></i>
                            </div>
                            <p class="message-text ml-3 px-3 py-2 ms-2 mb-0 bg-warning align-self-center">
                                <c:out value="${comment.content}" />
                            </p>
                            <div class="ml-2 pb-1 align-self-end ">
                                <fmt:parseDate value="${comment.createdAt}"
                                    pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                                <fmt:formatDate value="${createDay}" pattern="yyyy/MM/dd" />
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>

    <form method="POST"	action="<c:url value='?action=${actChat}&command=${commCrt}&id=${animal.id}&with=${animal.zoo.user.id}'/>">
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
