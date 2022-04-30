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

        <h3 class="my-4 py-1 col-12 text-white text-center bg-info rounded">動物掲載情報</h3>

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
            <tbody>
                <tr>
                    <td>12</td>
                    <td>アカカンガルー</td>
                    <td>ユキちゃん</td>
                    <td>オス</td>
                    <td>上山の動物園</td>
                    <td>チャット画面</td>
                </tr>
                <tr>
                    <td>データ</td>
                    <td>データ</td>
                    <td>データ</td>
                    <td>データ</td>
                    <td>データ</td>
                    <td>データ</td>
                </tr>
                <tr>
                    <td>データ</td>
                    <td>データセル</td>
                    <td>データ</td>
                    <td>データセル</td>
                    <td>データ</td>
                    <td>データセル</td>
                </tr>
            </tbody>
        </table>
    </div>


    </c:param>
</c:import>