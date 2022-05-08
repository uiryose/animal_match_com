<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">

    <c:if test="${errors != null}">
        <div class="alert alert-danger">
            <h5>入力内容にエラーがあります</h5>
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" /><br>
            </c:forEach>
        </div>
    </c:if>

    <h4 class="title">ご利用方法</h4>
    <div>このサイトは動物のマッチングサイトです。<br>
     動物の掲載はユーザー登録後に可能となります。ユーザー登録後にマイページをご覧ください。<br>
     トップページにいない動物を販売したい場合は、お問い合わせフォームからご連絡ください。<br>
     当サイトにて、動物について飼育難易度、個人飼育の可否などの設定を行います。</div><br>

    <h4 class="title">個人の特定動物飼育制限について</h4>
    <div>個人が飼育できる動物は法律で制限されています。
     また法律で制限がない場合でも、当サイトでは動物の飼育難易度や危険性を考慮して、個人の方への販売を不可としている動物もいます。<br>
     詳しくは<a href="">ご利用の前に</a>をご確認ください。</div><br>

    <h4 class="title">販売にあたって</h4>
    <div>動物の販売契約書の作成を推奨します。<a href="https://www.google.co.jp/">当サイト提携弁護士</a>にご相談いただけます。<br>
     もし購入した個人の方から、飼育困難による返却の相談があった場合は引き受けてください。<br>
     個人の方が動物を販売し対価を受け取ることは法律で禁止されていますので、基本的に無償譲渡になります。</div><br>

     それではユーザー登録をしましょう。<br>
     動物の掲載、取引チャットが利用できるようになります。<br><br>

     <h3 class="my-4 py-1 col-12 text-white text-center bg-primary rounded">ユーザー登録 </h3>

      <form method="POST" action="<c:url value='?action=${actZoo}&command=${commCrt}'/>">
        <div class="form-group row">
          <label for="${AttributeConst.USER_CODE.getValue()}" class="col-2 col-form-label">ログインID</label>
          <div class="col-10">
            <input type="text" class="form-control" name="${AttributeConst.USER_CODE.getValue()}" placeholder="ログインで必要になります"  value="${user.code}">
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
          <label for="${AttributeConst.USER_PASSWORD.getValue()}" class="col-2 col-form-label">パスワード</label>
          <div class="col-10">
            <input type="password" class="form-control" name="${AttributeConst.USER_PASSWORD.getValue()}" placeholder="パスワードを入力してください">
          </div>
        </div>

        <input type="hidden" name="${AttributeConst.USER_FLAG.getValue()}" value="${AttributeConst.USER_ZOO.getIntegerValue()}" />
        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        <div class="form-group row">
           <div class="col-12 text-center">
              <button type="submit" class="btn btn-primary my-3">アカウント作成</button>
           </div>
       </div>
     </form>

  </c:param>
</c:import>

