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


<c:import url="../layout/app.jsp">
    <c:param name="content">

<!-- 基本情報 -->

          <!-- パネル01 -->
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

<%--

<!-- 販売個体 -->
        <div class="animal">

            <div class="row">
                <c:forEach var="animal" items="${animals}">
                    <div class="col-3 mt-3 base">
                        <a
                            href="<c:url value='?action=${actAni}&command=${commShow}&id=${animal.id}' />">
                            <div class="card-body p-0 border h-75 bg-light">
                                <img class="card-img-top"
                                    src="<c:url value='/image/animal/${animal.animalImage}' /> "
                                    alt="${animal.nickname}の画像">

                            </div>
                            <div class="card-footer p-2 border h-25">
                                <small class="text-muted"><c:out
                                        value="${animal.nickname}" /></small><br> <small
                                    class="text-muted">コメントZZZ: xxx件</small>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>


        <!-- ここからページネーション -->
        <nav aria-label="ページネーションです" class = "mt-5 " >
            <ul class="pagination justify-content-center">
                <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">前</a></li>

                <c:forEach var="i" begin="1" end="${((ani_count - 1) / maxRow) + 1}" step="1">
                    <c:choose>
                        <c:when test="${i == page}">
                            <li class="page-item page-link disabled"><c:out value="${i}" /></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="<c:url value='?action=${actBase}&command=${commIdx}&page=${i}' />"></a>
                                <c:out value="${i}" />
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <li class="page-item"><a class="page-link" href="<c:url value='?action=${actBase}&command=${commIdx}&page=${page+1}' />">次</a></li>
            </ul>
        </nav>

 --%>
    </c:param>
</c:import>

