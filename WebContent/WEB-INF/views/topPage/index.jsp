<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

  <h2>サイトトップページ</h2>

  <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>
  <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>


    <div class="animalbase">

        <div class="row">

            <div class="col-3 mt-3 base">
                <div class="card-body p-0 border h-75 bg-light">
                     <img class="card-img-top" src="image/animalbase/ぺんぎん.jpg" alt="...">
                </div>
                <div class="card-footer p-2 border h-25">
                    <small class="text-muted">エリマキキツネザル</small><br>
                    <small class="text-muted">個人飼育 可能</small>
                </div>
            </div>
            <div class="col-3 mt-3 base">
                <div class="card-body p-0 border h-75 bg-light">
                     <img class="card-img-top" src="image/animalbase/らいおん.jpg" alt="...">
                </div>
                <div class="card-footer p-2 border h-25">
                    <small class="text-muted">エリマキキツネザル</small><br>
                    <small class="text-muted">個人飼育 可能</small>
                </div>
            </div>
            <div class="col-3 mt-3 base">
                <div class="card-body p-0 border h-75 bg-light">
                     <img class="card-img-top" src="image/animalbase/たぬき.jpg" alt="...">
                </div>
                <div class="card-footer p-2 border h-25">
                    <small class="text-muted">エリマキキツネザル</small><br>
                    <small class="text-muted">個人飼育 可能</small>
                </div>
            </div>
            <div class="col-3 mt-3 base">
                <div class="card-body p-0 border h-75 bg-light">
                     <img class="card-img-top" src="image/animalbase/らいおん.jpg" alt="...">
                </div>
                <div class="card-footer p-2 border h-25">
                    <small class="text-muted">エリマキキツネザル</small><br>
                    <small class="text-muted">個人飼育 可能</small>
                </div>
            </div>
            <div class="col-3 mt-3 base">
                <div class="card-body p-0 border h-75 bg-light">
                     <img class="card-img-top" src="image/animalbase/きつね.jpg" alt="...">
                </div>
                <div class="card-footer p-2 border h-25">
                    <small class="text-muted">エリマキキツネザル</small><br>
                    <small class="text-muted">個人飼育 可能</small>
                </div>
            </div>




        </div>


    </div>







  </c:param>
</c:import>

<!--
    <fieldset class="form-group">
      <div class="row">
        <label class="col-form-label col-2 pt-0">性別</label>
        <div class="col-10">
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

  -->
