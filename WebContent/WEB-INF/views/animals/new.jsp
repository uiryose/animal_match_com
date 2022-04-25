<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actAni" value="${ForwardConst.ACT_ANIMAL.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />






<c:import url="../layout/app.jsp">
  <c:param name ="content">

  <h2>販売動物　新規登録ページ</h2>





  <form method="POST" action="<c:url value='?action=${actAni}&command=${commCrt}'/>">
    <div class="form-group row">
      <label for="${AttributeConst.BASE_NAME.getValue()}" class="col-2 col-form-label">動物の種類</label>
      <div class="col-5">
        <input type="text" class="form-control" name="${AttributeConst.BASE_NAME.getValue()}" placeholder="動物の種類を選ぶ">
      </div>
    </div>
    <div class="form-group row">
      <label for="name" class="col-2 col-form-label">売り主</label>
      <div class="col-5">
        <c:out value="${sessionScope.login_user.zoo.zoo_name}" />
      </div>
    </div>
    <div class="form-group row">
      <label for="region" class="col-2 col-form-label">所在地</label>
      <div class="col-5">
        <c:out value="${sessionScope.login_user.zoo.region}" />
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.ANI_NICKNAME.getValue()}" class="col-2 col-form-label">ニックネーム</label>
      <div class="col-5">
        <input type="text" class="form-control" name="${AttributeConst.ANI_NICKNAME.getValue()}" placeholder="(例)ユキちゃん">
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.ANI_AGE.getValue()}" class="col-2 col-form-label">登録時の年齢</label>
      <div class="col-5">
        <input type="text" class="form-control" name="${AttributeConst.ANI_AGE.getValue()}" placeholder="数字で入力してください">
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.PRICE_FOR_CUST.getValue()}" class="col-2 col-form-label">個人向けの価格</label>
      <div class="col-5">
        <input type="text" class="form-control" name="${AttributeConst.PRICE_FOR_CUST.getValue()}" placeholder="数字で入力してください">
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.PRICE_FOR_ZOO.getValue()}" class="col-2 col-form-label">動物園向けの価格</label>
      <div class="col-5">
        <input type="text" class="form-control" name="${AttributeConst.PRICE_FOR_ZOO.getValue()}" placeholder="数字で入力してください">
      </div>
    </div>
    <fieldset class="form-group">
      <div class="row">
        <label class="col-form-label col-2 pt-0" for="${AttributeConst.ANI_SEX.getValue()}" >性別</label>
        <div class="col-10">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios1" value="option1" checked>
            <label class="form-check-label" for="radios1">
              オス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios2" value="option2">
            <label class="form-check-label" for="radios2">
              メス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios3" value="option3">
            <label class="form-check-label" for="radios3">
              不明
            </label>
          </div>
        </div>
      </div>
    </fieldset>

    <div class="form-group row">
      <label for="${AttributeConst.ZOO_COMMENT.getValue()}" class="col-2 col-form-label">メッセージ</label>
      <div class="col-5">
        <textarea class="form-control" name="${AttributeConst.ZOO_COMMENT.getValue()}" placeholder="メッセージを入力してください"></textarea>
      </div>
    </div>

<!--***** 動物選択をforEachで行ってみる ******-->
<label for="${AttributeConst.BASE_NAME.getValue()}" class="col-2 col-form-label">動物の種類</label><br>

    <!--     **value=""はjavaに送る信号。  ROLE_GENERAL=0、 ROLE_ADMIN=1  if文は更新時にFlagが責任者ROLE_ADMIN=1と一致したらselected状態にする -->
<select class="custom-selec" name="${AttributeConst.BASE_NAME.getValue()}" >
    <option selected>選択してください</option>
    <c:forEach var="animalbase" items="{$animalBases}" >
        <option value="${animalbase}">
            <c:out value="${animalbase.name}"/>
        </option>
    </c:forEach>
</select>


    <input type="hidden" name="${AttributeConst.USER_FLAG.getValue()}" value="${AttributeConst.USER_ZOO.getIntegerValue()}" />
    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <div class="form-group row">
      <div class="col-7 text-center">
        <button type="submit" class="btn btn-primary">アカウント作成</button>
      </div>
    </div>
  </form>


   </c:param>
</c:import>

