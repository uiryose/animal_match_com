<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h4 class="title mb-3">エサMatch.com紹介ページ</h4>
        <div>
            <img class="col-12 px-0"
                src="<c:url value='/image/esamatch.jpg' /> " alt="エサマッチドットコムの紹介画像">
        </div>


    </c:param>
</c:import>

