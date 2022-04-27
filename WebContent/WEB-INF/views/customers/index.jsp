<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

    <h3 class="mb-4 py-1 col-12 text-white text-center bg-primary rounded">マイページ(個人向け)</h3>

    <div class="row">
        <div class="col-4"><c:out value="${customer.customerName}" />さん</div><br>
        <div class="col-8"><a class="float-right" href="<c:url value='?action=${actCust}&command=${commEdit}' />">個人情報を修正する</a></div>
    </div>
        <div class="col-12 pr-0"><a class="float-right" href="<c:url value='?action=${actAuth}&command=${commLogout}' />">ログアウト</a></div>
    <br>

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

