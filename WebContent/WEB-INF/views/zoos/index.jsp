<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />


<c:import url="../layout/zooapp.jsp">
    <c:param name="content">

        <c:if test="${errors != null}">
            <div class="alert alert-danger">
                <h5>入力内容にエラーがあります</h5>
                <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" />
                    <br>
                </c:forEach>
            </div>
        </c:if>
<h1>動物園indexの予定</h1>

        <h3 class="my-4 py-1 col-12 text-white text-center bg-primary rounded">動物掲載情報</h3>




    </c:param>
</c:import>