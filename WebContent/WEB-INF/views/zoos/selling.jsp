<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actAni" value="${ForwardConst.ACT_ANI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />


<c:import url="../layout/zooapp.jsp">
    <c:param name="content">


    <h3 class="my-4 py-1 col-12 text-white text-center bg-info rounded">掲載中の動物一覧</h3>
    <div class="">&nbsp;全&nbsp;<c:out value="${selling_count}" />&nbsp;件</div>
    <div class="row mx-0">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th class="col-1">No.</th>
                    <th class="col-2">名称</th>
                    <th class="col-2">愛称</th>
                    <th class="col-1">性別</th>
                    <th class="col-1">年齢</th>
                    <th class="col-2">登録日</th>
                    <th class="col-2">詳細画面</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="animal" items="${animals}" varStatus="No">
                    <tr>
                        <td><c:out value="${No.count}" /></td>
                        <td><c:out value="${animal.animalBase.baseName}" /></td>
                        <td><c:out value="${animal.nickname}" /></td>
                        <td>
                            <c:choose>
                            <c:when test="${animal.animalSex == AttributeConst.SEX_MALE.getIntegerValue()}">オス</c:when>
                            <c:when test="${animal.animalSex == AttributeConst.SEX_FEMALE.getIntegerValue()}">メス</c:when>
                            <c:otherwise>不明</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${animal.animalAge}" />&nbsp;才</td>
                        <fmt:parseDate value="${animal.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                        <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd" /></td>
                        <td><h5><a class="badge badge-info font-weight-normal" href="<c:url value='?action=${actAni}&command=${commShow}&id=${animal.id}' />">確認・編集</a></h5></td>
<%--                         <a href="<c:url value='?action=${actAni}&command=${commShow}&id=${animal.id}' />">確認</a></td>
 --%>                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    </c:param>
</c:import>