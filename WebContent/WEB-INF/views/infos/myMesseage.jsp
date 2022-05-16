<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="../layout/app.jsp">
  <c:param name ="content">



    <h4 class="title">サイト制作上のメッセージ</h4>
    <div>メモ徒然のページです</div><br>
    <div>
        ユーザーは動物園と個人が存在するため、User親クラスにZooとCustomerの子クラスを作成した。<br>
        Userクラスに動物園か個人のフラグをつけてたことで、以下の制限を実装<br>
    </div>

<div>
・個人と動物園のログイン後の権限とマイページ画面を区別 <br>
・動物の掲載販売、購入権限で個人に制限<br>
・個人飼育不可は個人のみチャットができない、販売済みの動物については全員チャットができないなど細部の条件分岐もJSPで実装<br>
・またライオンなど特定動物については個人飼育の可否を判断するフラグをつけるとともに、動物園が販売したくない動物は個体ごと個人向け価格を-1円(負の数値なら何でもOK)で登録することとした。<br>
(-1円入力よりチェックボックスの方が実用的だが、プログラムで何をしてるかを可視化するため)
</div>
<br>
<div>
●特に苦労したのはチャット関係<br>
チャットのやり取りは個人(買い手)⇔動物園A(売り主)、動物園A(買い手)⇔動物園B(売り主)の複数パターン。<br>
販売動物ごとにチャットを行うため、どの動物に対するチャットか、チャットの送信者、受信者はどのユーザーかを管理しつつ、コメント情報をリスト化して画面に表示させた。<br>
（参考：tradesリスト[[Animal.id1, chat.id1], [Animal.id1, chat.id3],[Animal.id2, chat.id2]....])<br>
チャット画面を個人専用ページと動物園専用ページを区別しているのと、チャット画面で「誰と」、「どの動物についてか」を特定する条件が異なるため、チャット用Actionも個人と動物園で分けた。<br>
</div>
<br>
<div>最後に...</div>
動物の画像はすべて自分で撮影した写真を活用しました。




  </c:param>
</c:import>

