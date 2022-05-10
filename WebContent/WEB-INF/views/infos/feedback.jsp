<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">


    <h3 class="mb-4 py-1 col-12 text-white text-center bg-primary rounded">お問い合わせ  </h3>

      <form method="POST" action="<c:url value='?action=${abc}&command=${abc}'/>">
        <div class="form-group row">
          <label for="${name1}" class="col-3 col-form-label">お名前</label>
          <div class="col-9">
            <input type="text" class="form-control" name="${name1}" placeholder="氏名を入力してください" value="${abc}">
          </div>
        </div>
        <div class="form-group row">
          <label for="${email1}" class="col-3 col-form-label">メールアドレス</label>
          <div class="col-9">
            <input type="email" class="form-control" name="${email1}" placeholder="メールアドレスを入力してください" value="${abc}">
          </div>
        </div>
        <div class="form-group row">
          <label for="${feedback1}" class="col-3 col-form-label">お問い合わせ内容</label>
          <div class="col-9">
            <textarea rows="5" class="form-control" name="${feedback1}" placeholder="お問い合わせ内容を入力してください"></textarea>
          </div>
        </div>


        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        <div class="form-group row m-4">
          <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary" disabled id="b1">このページは稼働していません</button>
          </div>
        </div>
      </form>


  </c:param>
</c:import>

