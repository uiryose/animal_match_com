<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />


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


        <h3 class="my-4 py-1 col-10 text-white text-center bg-primary rounded">ユーザー情報の変更</h3>

        <form method="POST"
            action="<c:url value='?action=${actCust}&command=${commUpd}'/>">
            <div class="form-group row">
                <label for="${AttributeConst.USER_CODE.getValue()}"
                    class="col-2 col-form-label">ログインID</label>
                <div class="col-8">
                    <input type="text" class="form-control"
                        name="${AttributeConst.USER_CODE.getValue()}"
                        placeholder="ログインで必要になります" value="${user.code}">
                </div>
            </div>
            <div class="form-group row">
                <label for="${AttributeConst.CUST_NAME.getValue()}"
                    class="col-2 col-form-label">氏名</label>
                <div class="col-8">
                    <input type="text" class="form-control"
                        name="${AttributeConst.CUST_NAME.getValue()}"
                        placeholder="氏名を入力してください" value="${customer.customerName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="${AttributeConst.USER_PASSWORD.getValue()}"
                    class="col-2 col-form-label">パスワード</label>
                <div class="col-8">
                    <input type="password" class="form-control"
                        name="${AttributeConst.USER_PASSWORD.getValue()}"
                        placeholder="パスワードを入力してください">
                </div>
            </div>

            <input type="hidden" name="${AttributeConst.USER_FLAG.getValue()}"
                value="${AttributeConst.USER_CUST.getIntegerValue()}" /> <input
                type="hidden" name="${AttributeConst.TOKEN.getValue()}"
                value="${_token}" />
            <div class="form-group row">
                <div class="col-10 text-center">
                    <button type="submit" class="btn btn-primary">変更する</button>
                </div>
            </div>
        </form>


    </c:param>
</c:import>