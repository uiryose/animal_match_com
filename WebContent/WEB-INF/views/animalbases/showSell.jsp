<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actAni" value="${ForwardConst.ACT_ANI.getValue()}" />
<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commTrdIdx" value="${ForwardConst.CMD_TRADE_INDEX.getValue()}" />
<c:set var="commShowLogin" value="${ForwardConst.CMD_SHOW_LOGIN.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">

<!-- 販売個体 -->
      <div class="row p-1">
        <div class="col-8 order-2 pl-0">
              <table class="table mb-0">
                  <tbody>
                      <tr>
                          <th class="col-2">売り主</th>
                          <td class="col-6"><c:out value="${animal.zoo.zooName}" />（ <c:out value="${animal.zoo.region}" /> ）</td>
                      </tr>
                      <tr>
                          <th class="col-2">名前</th>
                          <td class="col-6"><c:out value="${animal.animalBase.baseName}" />（<c:out value="${animal.nickname}" />）</td>
                      </tr>
                      <tr>
                          <th class="col-2">年齢</th>
                          <td class="col-6"><c:out value="${animal.animalAge}" />&nbsp;才</td>
                      </tr>
                      <tr>
                          <th class="col-2">性別</th>
                          <td class="col-6">
                            <c:choose>
                                <c:when test="${animal.animalSex == AttributeConst.SEX_MALE.getIntegerValue()}" >オス</c:when>
                                <c:when test="${animal.animalSex == AttributeConst.SEX_FEMALE.getIntegerValue()}" >メス</c:when>
                                <c:when test="${animal.animalSex == AttributeConst.SEX_UNKNOWN.getIntegerValue()}" >不明</c:when>
                            </c:choose>
                          </td>
                      </tr>
                      <tr>
                          <th class="col-2">個人飼育</th>
                          <td><c:choose>
                              <c:when test="${animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_TURE.getIntegerValue()}" >可能</c:when>
                              <c:when test="${animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_FALSE.getIntegerValue()}" >不可</c:when>
                            </c:choose>
                          </td>
                      </tr>
                      <tr>
                          <th class="col-2">個人価格</th>
                          <td class="col-6">
                            <c:choose>
                                <c:when test="${animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_FALSE.getIntegerValue()}" >飼育不可</c:when>
                                <c:when test="${animal.priceForCust < 0}" >販売しません</c:when>
                                <c:otherwise><c:out value="${animal.priceForCust}" />&nbsp;円</c:otherwise>
                            </c:choose>
                          </td>
                      </tr>
                      <tr>
                          <th class="col-2">動物園価格</th>
                          <td class="col-6"><c:out value="${animal.priceForZoo}" />&nbsp;円</td>
                      </tr>
                      <tr class="border-bottom">
                          <th class="col-2">メッセージ</th>
                          <td class="col-6 pb-1 pr-1 "><pre><c:out value="${animal.animalComment}" /></pre></td>
                      </tr>
                      <tr>
                          <th class="col-2">登録日時</th>
                          <fmt:parseDate value="${animal.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                          <td class="col-6"><fmt:formatDate value="${createDay}" pattern="yyyy年MM月dd日 HH時mm分" /></td>
                      </tr>
                      <tr class="border-bottom">
                          <th class="col-2 ">更新日時</th>
                          <fmt:parseDate value="${animal.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                          <td class="col-6"><fmt:formatDate value="${updateDay}" pattern="yyyy年MM月dd日 HH時mm分" /></td>
                      </tr>
                  </tbody>
              </table>
        </div>
        <div class="col-4">
          <img src="<c:url value='/image/animal/${animal.animalImage}' /> "
                              alt="${animal.nickname}の画像" class="img-fluid">
        </div>
      </div>

    <div class="row mt-3">
        <div class="col-4"></div>
        <div class="col-3 px-0">
            <c:choose>
                <c:when test="${animal.zoo.user.id == login_user.id}">
                    <a class="btn btn-info" href="<c:url value='?action=${actAni}&command=${commShow}&id=${animal.id}' />" >マイページで確認する</a>
                </c:when>
                <c:when test="${animal.soldFlag == AttributeConst.SOLD_FLAG_TRUE.getIntegerValue()}">
                    <div class="btn btn-warning disabled" style="opacity:1" >この動物は販売済みです</div>
                </c:when>
                <c:when test="${login_user.userFlag == AttributeConst.USER_CUST.getIntegerValue() && (animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_FALSE.getIntegerValue() || animal.priceForCust < 0 ) }">
                    <div class="btn btn-warning disabled" style="opacity:1" >個人の方は購入できません</div>
                </c:when>
                <c:when test="${login_user.userFlag == AttributeConst.USER_CUST.getIntegerValue()}">
                    <a class="btn btn-info" href="<c:url value='?action=${actChat}&command=${commIdx}&id=${animal.id}&with=${animal.zoo.user.id}' />">チャットを始める</a>
                </c:when>
                <c:when test="${login_user.userFlag == AttributeConst.USER_ZOO.getIntegerValue()}">
                    <a class="btn btn-info" href="<c:url value='?action=${actZoo}&command=${commTrdIdx}&id=${animal.id}&with=${animal.zoo.user.id}' />">チャットを始める</a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-info" href="<c:url value='?action=${actAuth}&command=${commShowLogin}' />">ログインしてチャットを始める</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-5 text-right">
           <a class="btn btn-info"  href="<c:url value='?action=${actBase}&command=${commShow}&id=${animal.animalBase.id}' />"><c:out value="${animal.animalBase.baseName}" />の一覧に戻る</a>
        </div>
    </div>

    </c:param>
</c:import>

