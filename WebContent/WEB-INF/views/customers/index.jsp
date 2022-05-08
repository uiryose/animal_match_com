<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commLogout" value="${ForwardConst.CMD_LOGOUT.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

    <h3 class="mb-4 py-1 col-12 text-white text-center bg-primary rounded">マイページ(個人向け)</h3>

    <div class="row">
        <div class="col-8 font-weight-bold"><c:out value="${login_customer.customerName}" />さん</div>
        <div class="col-4"><a class="float-right  btn btn-primary" href="<c:url value='?action=${actCust}&command=${commEdit}' />">個人情報を修正</a></div>
        <div class="col-9"></div>
        <div class="col-3 mt-1"><a class="float-right btn btn-primary" href="<c:url value='?action=${actAuth}&command=${commLogout}' />">ログアウトする</a></div>
    </div>

    <div class="">やり取りしている動物一覧</div>
    <div class="row mx-0">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th class="col-1">No.</th>
                    <th class="col-2">名称</th>
                    <th class="col-2">愛称</th>
                    <th class="col-2">性別</th>
                    <th class="col-2">取引相手</th>
                    <th class="col-2">チャット画面</th>
                </tr>
            </thead>
            <tbody class="border-bottom">
                <c:forEach var="trade" items="${trades}" varStatus="No">
                     <tr>
                        <td class="align-middle"><c:out value="${No.count}" /></td>
                        <td class="align-middle"><c:out value="${trade.animal.animalBase.baseName}" /></td>
                        <td class="align-middle"><c:out value="${trade.animal.nickname}" /></td>
                        <td class="align-middle">
                            <c:choose>
                            <c:when test="${trade.animal.animalSex == AttributeConst.SEX_MALE.getIntegerValue()}">オス</c:when>
                            <c:when test="${trade.animal.animalSex == AttributeConst.SEX_FEMALE.getIntegerValue()}">メス</c:when>
                            <c:otherwise>不明</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle"><c:out value="${trade.animal.zoo.zooName}" /></td>
                        <td><a class="btn btn-primary my-0 py-1" href="<c:url value='?action=${actChat}&command=${commIdx}&id=${trade.animal.id}&with=${trade.animal.zoo.user.id}' />"> チャット画面</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
  </c:param>
</c:import>

