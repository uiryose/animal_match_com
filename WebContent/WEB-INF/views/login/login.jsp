<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commLogin" value="${ForwardConst.CMD_LOGIN.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

     <c:if test="${loginError}">
         <div class="alert alert-danger">
             <h5>ログインIDかパスワードが間違っています。</h5>
         </div>
     </c:if>
     <c:if test="${flush != null}">
          <div class="alert alert-success py-4 col-12">
                <c:out value="${flush}" />
          </div>
     </c:if>

     <h3 class="mb-5 py-1 col-12 text-white text-center bg-primary rounded">Login Page</h3>

      <form method="POST" action="<c:url value='?action=${actAuth}&command=${commLogin}'/>">
        <div class="form-group row mb-4">
          <label for="${AttributeConst.USER_CODE.getValue()}" class="col-2 col-form-label">ログインID</label>
          <div class="col-10">
            <input type="text" class="form-control" name="${AttributeConst.USER_CODE.getValue()}" placeholder="ログインIDを入力してください">
          </div>
        </div>

        <div class="form-group row mb-5">
          <label for="${AttributeConst.USER_PASSWORD.getValue()}" class="col-2 col-form-label">パスワード</label>
          <div class="col-10">
            <input type="password" class="form-control" name="${AttributeConst.USER_PASSWORD.getValue()}" placeholder="パスワードを入力してください">
          </div>
        </div>

        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        <div class="form-group row  mb-4">
          <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary mb-4">ログイン</button>
          </div>
        </div>
      </form>

      <div class="col-12 text-center"><small>※ユーザー登録がまだの方はご利用案内からユーザー登録をお願いします。</small></div>


  </c:param>
</c:import>

