<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actAni" value="${ForwardConst.ACT_ANI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/zooapp.jsp">
  <c:param name ="content">

  <h3 class="my-4 py-1 col-12 text-white text-center bg-info rounded">動物新規登録画面</h3>

  <c:if test="${errors != null}">
      <div class="alert alert-danger">
          <h5>入力内容にエラーがあります</h5>
          <c:forEach var="error" items="${errors}">
          ・<c:out value="${error}" />
              <br>
          </c:forEach>
      </div>
  </c:if>

  <form method="POST" enctype="multipart/form-data" action="<c:url value='?action=${actAni}&command=${commCrt}'/>">
            <div class="form-group row">
                <label for="${AttributeConst.BASE_ID.getValue()}" class="col-3 col-form-label">動物の種類</label>
                    <select	class="custom-select col-7 " name="${AttributeConst.BASE_ID.getValue()}" required="required">
                    <option selected disabled value="">選択してください</option>
                    <c:forEach var="animalbase" items="${animalbases}">
                        <option value="${animalbase.id}">
                            <c:out value="${animalbase.baseName}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
      <label for="name" class="col-3 col-form-label pt-0">売り主</label>
      <div class="col-7">
        <c:out value="${login_zoo.zooName}" />
      </div>
    </div>
    <div class="form-group row">
      <label for="region" class="col-3 col-form-label pt-0">所在地</label>
      <div class="col-7">
        <c:out value="${login_zoo.region}" />
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.ANI_NICKNAME.getValue()}" class="col-3 col-form-label">ニックネーム</label>
      <div class="col-7">
        <input type="text" class="form-control" name="${AttributeConst.ANI_NICKNAME.getValue()}" placeholder="例 : ユキちゃん" value="${animal.nickname}" required>
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.ANI_AGE.getValue()}" class="col-3 col-form-label">登録時の年齢</label>
      <div class="col-7">
        <input type="number" class="form-control" name="${AttributeConst.ANI_AGE.getValue()}" placeholder="数字で入力してください" value="${animal.animalAge}" required maxlength="3">
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.PRICE_FOR_CUST.getValue()}" class="col-3 col-form-label">個人向けの価格</label>
      <div class="col-7">
        <input type="number" class="form-control" name="${AttributeConst.PRICE_FOR_CUST.getValue()}" placeholder="数字で入力してください"  value="${animal.priceForCust}" required>
      </div>
    </div>
    <div class="form-group row">
      <label for="${AttributeConst.PRICE_FOR_ZOO.getValue()}" class="col-3 col-form-label">動物園向けの価格</label>
      <div class="col-7">
        <input type="number" class="form-control" name="${AttributeConst.PRICE_FOR_ZOO.getValue()}" placeholder="数字で入力してください"  value="${animal.priceForZoo}" required>
      </div>
    </div>
    <fieldset class="form-group">
      <div class="row">
        <label class="col-form-label col-3 pt-0" for="${AttributeConst.ANI_SEX.getValue()}" >性別</label>
        <div class="col-7">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios1" value="${AttributeConst.SEX_MALE.getIntegerValue()}" required>
            <label class="form-check-label" for="radios1">
              オス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios2" value="${AttributeConst.SEX_FEMALE.getIntegerValue()}">
            <label class="form-check-label" for="radios2">
              メス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="${AttributeConst.ANI_SEX.getValue()}" id="radios3" value="${AttributeConst.SEX_UNKNOWN.getIntegerValue()}">
            <label class="form-check-label" for="radios3">
              不明
            </label>
          </div>
        </div>
      </div>
    </fieldset>


    <div class="form-group row">
      <label for="${AttributeConst.ZOO_COMMENT.getValue()}" class="col-3 col-form-label">メッセージ</label>
      <div class="col-7">
        <textarea class="form-control" name="${AttributeConst.ZOO_COMMENT.getValue()}" rows="4" placeholder="メッセージを入力してください" required>${animal.animalComment}</textarea>
      </div>
    </div>

    <div class="form-group row mb-5">
    <div class="col-3">動物の画像</div>
    <div class="custom-file col-7">
        <input type="file" class="custom-file-input" name="${AttributeConst.ANI_IMAGE.getValue()}" lang="ja">
        <label class="custom-file-label" for="${AttributeConst.ANI_IMAGE.getValue()}">画像を選択...</label>
    </div>
    </div>

    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
    <div class="form-group row">
      <div class="col-12 text-center">
        <button type="submit" class="btn btn-info">動物の新規登録</button>
      </div>
    </div>
  </form>


   </c:param>
</c:import>

