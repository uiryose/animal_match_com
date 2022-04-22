<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

  <h2>動物園　新規登録ページ</h2>

  <form method="POST" action="<c:url value='?action=${action}&command=${commCrt}'/>">
    <div class="form-group row">
      <label for="name" class="col-sm-2 col-form-label">USERCODE</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="name" placeholder="ログインで必要になります">
      </div>
    </div>
    <div class="form-group row">
      <label for="name" class="col-sm-2 col-form-label">動物園名</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="name" placeholder="動物園名を入力してください">
      </div>
    </div>
    <div class="form-group row">
      <label for="name" class="col-sm-2 col-form-label">所在地</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="name" placeholder="都道府県名を入力してください">
      </div>
    </div>
    <div class="form-group row">
      <label for="name" class="col-sm-2 col-form-label">電話番号</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="name" placeholder="数字のみで電話番号を入力してください">
      </div>
    </div>

    <div class="form-group row">
      <label for="inputPassword" class="col-sm-2 col-form-label">パスワード</label>
      <div class="col-sm-5">
        <input type="password" class="form-control" id="inputPassword" placeholder="パスワードを入力">
      </div>
    </div>
<!--
    <fieldset class="form-group">
      <div class="row">
        <label class="col-form-label col-sm-2 pt-0">性別</label>
        <div class="col-sm-10">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radios" id="radios1" value="option1" checked>
            <label class="form-check-label" for="radios1">
              オス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radios" id="radios2" value="option2">
            <label class="form-check-label" for="radios2">
              メス
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radios" id="radios3" value="option3">
            <label class="form-check-label" for="radios3">
              不明
            </label>
          </div>
        </div>
      </div>
    </fieldset>

    <div class="form-group row">
      <div class="col-sm-2">注意事項</div>
      <div class="col-sm-10">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" id="check1">
          <label class="form-check-label" for="check1">
            注意事項に同意します。
          </label>
        </div>
      </div>
    </div>
  -->

    <div class="form-group row">
      <div class="col-sm-10 text-center">
        <button type="submit" class="btn btn-primary">アカウント作成</button>
      </div>
    </div>
  </form>
    <div>てすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすとてすと
    てすとてすとてすとてすとてすと</div>








  </c:param>
</c:import>

