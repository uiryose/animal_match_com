<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDst" value="${ForwardConst.CMD_DESTROY.getValue()}" />


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


        <h3 class="my-4 py-1 col-12 text-white text-center bg-primary rounded">動物園情報の変更</h3>

        <form method="POST"
            action="<c:url value='?action=${actZoo}&command=${commUpd}'/>">
            <div class="form-group row">
                <label for="${AttributeConst.USER_CODE.getValue()}"
                    class="col-2 col-form-label">ログインID</label>
                <div class="col-10">
                    <input type="text" class="form-control"
                        name="${AttributeConst.USER_CODE.getValue()}"
                        placeholder="ログインIDを入力してください" value="${user.code}">
                </div>
            </div>
            <div class="form-group row">
              <label for="${AttributeConst.ZOO_NAME.getValue()}" class="col-2 col-form-label">動物園名</label>
              <div class="col-10">
                <input type="text" class="form-control" name="${AttributeConst.ZOO_NAME.getValue()}" placeholder="動物園名を入力してください" value="${zoo.zooName}">
              </div>
            </div>
            <div class="form-group row">
              <label for="${AttributeConst.ZOO_REGION.getValue()}" class="col-2 col-form-label">所在地</label>
              <div class="col-10">
                <input type="text" class="form-control" name="${AttributeConst.ZOO_REGION.getValue()}" placeholder="都道府県名を入力してください" value="${zoo.region}">
                <small id="helpRegion" class="form-text text-muted">※正しく入力しないとエラーになります。入力例：大阪府・長野県・東京都</small>
              </div>
            </div>
            <div class="form-group row">
              <label for="${AttributeConst.ZOO_PHONE.getValue()}" class="col-2 col-form-label">電話番号</label>
              <div class="col-10">
                <input type="text" class="form-control" name="${AttributeConst.ZOO_PHONE.getValue()}" placeholder="数字のみで電話番号を入力してください" value="${zoo.phone}">
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
            action="<c:url value='?action=${actZoo}&command=${commDst}'/>">
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