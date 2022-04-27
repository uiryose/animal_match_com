<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDst" value="${ForwardConst.CMD_DESTROY.getValue()}" />


<c:import url="../layout/app.jsp">
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


        <h3 class="mb-4 py-1 col-12 text-white text-center bg-primary rounded">ユーザー情報の変更</h3>

        <form method="POST"
            action="<c:url value='?action=${actCust}&command=${commUpd}'/>">
            <div class="form-group row">
                <label for="${AttributeConst.USER_CODE.getValue()}"
                    class="col-2 col-form-label">ログインID</label>
                <div class="col-10">
                    <input type="text" class="form-control"
                        name="${AttributeConst.USER_CODE.getValue()}"
                        placeholder="ログインで必要になります" value="${user.code}">
                </div>
            </div>
            <div class="form-group row">
                <label for="${AttributeConst.CUST_NAME.getValue()}"
                    class="col-2 col-form-label">氏名</label>
                <div class="col-10">
                    <input type="text" class="form-control"
                        name="${AttributeConst.CUST_NAME.getValue()}"
                        placeholder="氏名を入力してください" value="${customer.customerName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="${AttributeConst.USER_PASSWORD.getValue()}"
                    class="col-2 col-form-label">パスワード</label>
                <div class="col-10 mb-3">
                    <input type="password" class="form-control"
                        name="${AttributeConst.USER_PASSWORD.getValue()}"
                        placeholder="パスワードを入力してください">
                 <small class="form-text text-muted">※パスワードは変更する場合のみ入力してください</small>
                </div>
            </div>

            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <div class="form-group row">
                <div class="col-7 text-right">
                    <button type="submit" class="btn btn-primary">変更する</button>
                </div>
                <div class="col-5 text-right">
                    <a href="#" class="btn btn-danger" onclick="confirmDestroy();">ユーザーアカウントを削除する</a>
                </div>
            </div>
        </form>

        <form method="POST"
            action="<c:url value='?action=${actCust}&command=${commDst}'/>">
            <input type="hidden" name="${AttributeConst.USER_ID.getValue()}" value="${user.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("本当に削除してよろしいですか？")) {
                    document.forms[1].submit();
                }
            }
        </script>


    </c:param>
</c:import>