<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actInfo" value="${ForwardConst.ACT_INFO.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commBefore" value="${ForwardConst.CMD_BEFOREUSE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

<!-- 「サイトの趣旨」画面  -->
    <div class="">
        <img class="col-12 px-0 border"
            src="<c:url value='/image/info111.jpg' /> "
            alt="ニューノーマルな時代に、スペシャルな家族を探そう">
    </div>
    <div class="mt-3">
    このサイトは動物園と個人のための動物マッチングサイトです。<br>
    動物園が抱える諸課題を解決するツールになればと思い作成しました。&nbsp;
    <a class="" href="https://www.asahi.com/special/dobutsuen/">(参考)朝日新聞</a>
    </div>

    <h4 class="title mt-3">動物園に対して</h4>
        ・余剰動物を販売することで動物園の収益力向上<br>
        ・動物の繁殖を積極的に行える環境作り<br>
        ・高齢動物のペット販売で園内動物の新陳代謝を促す<br>

    <h4 class="title mt-3">個人に対して</h4>
        ・個人では入手困難な動物の購入機会を提供<br>
        ・動物園を身近に感じてもらう<br>

    <h4 class="title mt-3">サイト運営者に対して</h4>
        ・ビジネスモデルの確立 &nbsp;<small>(日本動物園水族館協会からの運営協賛金収入・売買成約報酬・弁護士、ペット保険斡旋手数料etc...</small><br>

    <h4 class="title mt-3">その他</h4>
    動物園と個人だけではなく、動物園同士のやり取りも可能にしています。<br>
    また特定の動物の飼育については法律の制限や動物園の意向もあるため、当サイトでは個人の購入を制限する仕組みも実装したことで実用性を高めました。
    詳しくは、<a href="<c:url value='?action=${actInfo}&command=${commBefore}' />">ご利用前に</a>を参照お願いします。


  </c:param>
</c:import>

