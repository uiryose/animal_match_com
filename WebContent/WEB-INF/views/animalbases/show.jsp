<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actAni" value="${ForwardConst.ACT_ANI.getValue()}" />
<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commShowSell" value="${ForwardConst.CMD_SHOWSELL.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">

<!-- 基本情報 -->
          <div class="" >
            <div class="row p-1">
              <div class="col-8 order-2 pl-0">
                    <table class="table mb-0">
                        <tbody>
                            <tr>
                                <th class="col-2">名称</th>
                                <td class="col-6"><c:out value="${animalbase.baseName}" /></td>
                            </tr>
                            <tr>
                                <th class="col-2">サイズ</th>
                                <td class="col-6"><c:choose>
                                    <c:when test="${animalbase.baseSize == AttributeConst.SIZE_L.getIntegerValue()}" >大型</c:when>
                                    <c:when test="${animalbase.baseSize == AttributeConst.SIZE_M.getIntegerValue()}" >中型</c:when>
                                    <c:when test="${animalbase.baseSize == AttributeConst.SIZE_S.getIntegerValue()}" >小型</c:when>
                                  </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th class="col-2">飼育難易</th>
                                <td class="col-6"><c:out value="${animalbase.baseDifficulty}" /></td>
                            </tr>
                            <tr>
                                <th class="col-2">個人飼育</th>
                                <td><c:choose>
                                    <c:when test="${animalbase.baseBreedFlag == AttributeConst.BREED_FLAG_TURE.getIntegerValue()}" >可能</c:when>
                                    <c:when test="${animalbase.baseBreedFlag == AttributeConst.BREED_FLAG_FALSE.getIntegerValue()}" >不可</c:when>
                                  </c:choose>
                                </td>
                            </tr>
                            <tr class="border-bottom">
                                <th class="col-2">特徴</th>
                                <td class="col-6 pb-1 pr-1 "><pre><c:out value="${animalbase.baseComment}" /></pre></td>
                            </tr>
                        </tbody>
                    </table>
              </div>

              <div class="col-4">
                <img src="<c:url value='/image/animalbase/${animalbase.baseImage}' /> "
                                    alt="${animalbase.baseName}の画像" class="img-fluid">
              </div>
            </div>
          </div>


        <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>
        <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>



<!-- 販売個体 -->
            <div class="row animal">
                <c:forEach var="animal" items="${animals}">
                    <div class="col-3 mt-3 base">
                        <a href="<c:url value='?action=${actBase}&command=${commShowSell}&id=${animal.id}' />">
                            <div class="card-body p-0 border h-30 bg-light">
                                <img class="card-img-top"
                                    src="<c:url value='/image/animal/${animal.animalImage}' /> "
                                    alt="${animal.nickname}の画像">
                        </a>

                            </div>
                            <div class="card-footer p-2 border h-25">
                                <small class="text-muted"><c:out value="${animal.zoo.zooName}" />&nbsp;(<c:out value="${animal.zoo.region}" />)</small><br>
                                <c:choose>
                                    <c:when test="${animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_FALSE.getIntegerValue()}" >
                                        <small class="text-muted">個人用価格&nbsp;:&nbsp;飼育不可</small><br>
                                    </c:when>
                                    <c:when test="${animal.priceForCust < 0}" >
                                        <small class="text-muted">個人用価格&nbsp;:&nbsp;販売しません</small><br>
                                    </c:when>
                                    <c:when test="${animal.animalBase.baseBreedFlag == AttributeConst.BREED_FLAG_TURE.getIntegerValue()}" >
                                        <small class="text-muted">個人用価格&nbsp;:&nbsp;<c:out value="${animal.priceForCust}" />&nbsp;円</small><br>
                                    </c:when>
                                </c:choose>
                                <small class="text-muted">動物園価格&nbsp;:&nbsp;<c:out value="${animal.priceForZoo}" />&nbsp;円</small><br>
                                <small class="text-muted">掲載時年齢&nbsp;:&nbsp;<c:out value="${animal.animalAge}" />&nbsp;才</small><br>
                            </div>
                    </div>
                </c:forEach>
            </div>

    </c:param>
</c:import>

