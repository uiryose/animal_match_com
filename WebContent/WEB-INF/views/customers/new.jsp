<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
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
    <c:if test="${flush != null}">
        <div class="alert alert-success py-4">
            <c:out value="${flush}" />
        </div>
    </c:if>

    <h4 class="title">ご利用方法</h4>
    <div>このサイトでは動物園が販売している動物を探すことができます。<br>
    ペットショップでは入手困難な動物もいます。ただし、個人が飼育できる動物は法律で制限されています。</div>
    <div>また法律の制限がない場合でも、当サイトでは動物の飼育難易度や危険性を考慮して、個人の方への販売を<br>
    不可としている動物もいます。<br>
    詳しくは<a href="">ご利用の前に</a>をご確認ください。</div><br>

    <h4 class="title">飼育の責任</h4>
    <div>動物を飼うことは責任も伴います。ご購入前に実際に動物を見に行くことをお奨めします。<br>
    ペットの保険のご利用はぜひ<a href="https://www.google.co.jp/">当サイト提携保険会社</a>にご相談ください。<br>
    もし購入された動物を手放す場合は、購入された動物園にご相談ください。<br>
    個人の方が動物を販売し、対価を受け取ることは法律で禁止されています。<br>
    動物園は返金できませんが、動物のために動物園への「無償譲渡」をお願い致します。</div><br>

    それではユーザー登録をしましょう。<br>
    動物園とチャットのやり取りができるようになります。<br><br>

     <h3 class="my-4 py-1 col-12 text-white text-center bg-primary rounded">ユーザー登録  </h3>

      <form method="POST" action="<c:url value='?action=${actCust}&command=${commCrt}'/>">
        <div class="form-group row">
          <label for="${AttributeConst.USER_CODE.getValue()}" class="col-2 col-form-label">ログインID</label>
          <div class="col-10">
            <input type="text" class="form-control" name="${AttributeConst.USER_CODE.getValue()}" placeholder="ログインで必要になります" value="${user.code}">
          </div>
        </div>
        <div class="form-group row">
          <label for="${AttributeConst.CUST_NAME.getValue()}" class="col-2 col-form-label">氏名</label>
          <div class="col-10">
            <input type="text" class="form-control" name="${AttributeConst.CUST_NAME.getValue()}" placeholder="氏名を入力してください" value="${customer.customerName}">
          </div>
        </div>
        <div class="form-group row">
          <label for="${AttributeConst.USER_PASSWORD.getValue()}" class="col-2 col-form-label">パスワード</label>
          <div class="col-10">
            <input type="password" class="form-control" name="${AttributeConst.USER_PASSWORD.getValue()}" placeholder="パスワードを入力してください">
          </div>
        </div>

        <div class="form-group row text-center">
          <div class="col-12">
            <div class="form-check">
              <label class="form-check-label" for="check1">
              <input class="form-check-input Iread" type="checkbox" id="check1"  onclick="clickBtn1()">
                <a href="">「ご利用の前に」</a>を確認しました。
              </label>
            </div>
          </div>
        </div>

        <input type="hidden" name="${AttributeConst.USER_FLAG.getValue()}" value="${AttributeConst.USER_CUST.getIntegerValue()}" />
        <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        <div class="form-group row">
          <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary" disabled id="b1">アカウント作成</button>
          </div>
        </div>
      </form>

    <script>
    function clickBtn1(){
        if (document.getElementById("b1").disabled === true){
            // disabled属性を削除
            document.getElementById("b1").removeAttribute("disabled");
        }else{
            // disabled属性を設定
            document.getElementById("b1").setAttribute("disabled", true);
        }
    }
    </script>
  </c:param>
</c:import>

