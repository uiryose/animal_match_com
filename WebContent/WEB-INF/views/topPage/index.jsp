<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actBase" value="${ForwardConst.ACT_BASE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commSrhName" value="${ForwardConst.CMD_SEARCH_NAME.getValue()}" />
<c:set var="commSrhBF" value="${ForwardConst.CMD_SEARCH_BREEDFLAG.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="row">

            <form method="POST" action="<c:url value='?action=${actBase}&command=${commSrhName}'/>"  class="col-9" >
               <div class="input-group">

                  <input type="text" name="${AttributeConst.BASE_NAME.getValue()}" class="form-control input-group-prepend" placeholder="動物名を入力"></input>
                  <span class="input-group-btn input-group-append">
                    <input type="submit" class="btn btn-primary" value="検索">
                  </span>
                </div>
            </form>
            <div class="col-3 mx-0 ">
                <a class="btn btn-primary px-2" href="<c:url value='?action=${actBase}&command=${commSrhBF}&${AttributeConst.BASE_BREED_FLAG.getValue()}=${AttributeConst.BREED_FLAG_TURE.getIntegerValue()}'/>">個人飼育可で絞り込み</a>

            </div>
        </div>

        <c:if test="${searchs.size() == 0}">
            <div class="mt-3">該当する動物はいませんでした。違う動物をお探しください</div>
        </c:if>



        <div class="animalbase">
            <div class="row">
                <c:forEach var="animalbase" items="${animalbases}">
                    <div class="col-3 my-3 base">
                            <div class="card-body p-0 border border-bottom-0 bg-light">
                                <a class="text-decoration-none" href="<c:url value='?action=${actBase}&command=${commShow}&id=${animalbase.id}' />">
                                    <img class="card-img-top"
                                        src="<c:url value='/image/animalbase/${animalbase.baseImage}' /> "
                                        alt="${animalbase.baseName}の画像">
                                </a>
                            </div>

                            <div class="card-footer pl-2 pr-0 pt-0 border border-top-0 h-25 text-decoration-none">
                                <c:set var="Exist" scope="request" value="false" />
                                <small class="text-muted"><c:out value="${animalbase.baseName}" /></small><br>
                                <small class="text-muted">掲載実績：
                                    <c:forEach var="count" items="${animals_count}">
                                        <c:if test="${count[0] == animalbase.id}">
                                            <c:out value="${count[1] }" />
                                            <c:set var="Exist" scope="request" value="true" />
                                        </c:if>
                                    </c:forEach> <c:if test="${Exist == false}">0</c:if> 件
                                </small>
                            </div>
                    </div>
                </c:forEach>
 <!-- 検索結果を表示 -->
                <c:forEach var="search" items="${searchs}">
                    <div class="col-3 my-3 base">
                            <div class="card-body p-0 border border-bottom-0 bg-light">
                                <a class="text-decoration-none" href="<c:url value='?action=${actBase}&command=${commShow}&id=${search.id}' />">
                                    <img class="card-img-top"
                                        src="<c:url value='/image/animalbase/${search.baseImage}' /> "
                                        alt="${search.baseName}の画像">
                                </a>
                            </div>

                            <div class="card-footer pl-2 pr-0 pt-0 border border-top-0 h-25 text-decoration-none">
                                <c:set var="Exist" scope="request" value="false" />
                                <small class="text-muted"><c:out value="${search.baseName}" /></small><br>
                                <small class="text-muted">掲載実績：
                                    <c:forEach var="count" items="${animals_count}">
                                        <c:if test="${count[0] == search.id}">
                                            <c:out value="${count[1] }" />
                                            <c:set var="Exist" scope="request" value="true" />
                                        </c:if>
                                    </c:forEach> <c:if test="${Exist == false}">0</c:if> 件
                                </small>
                            </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <c:if test="${call_method != AttributeConst.SEARCH.getValue() }"> <!-- 今回はsearchNameメソッドからの呼び出し時に、該当件数が少ないためページネーションを省略するための措置 -->
            <!-- ここからページネーション -->
            <c:choose>
                <c:when test="${animalbases.size() == 0}">
                    <h4 class="my-3">指定されたページに該当する動物はいません。</h4>
                </c:when>
                <c:otherwise>
                    <nav aria-label="ページネーションです" class="mt-3 ">
                        <ul class="pagination justify-content-center">
                            <c:if test="${page > 1}">
                                <c:choose>
                                    <c:when test="${searching != null}">
                                        <li class="page-item "><a class="page-link"
                                            href="<c:url value='?action=${actBase}&command=${commSrhBF}&${AttributeConst.BASE_BREED_FLAG.getValue()}=${AttributeConst.BREED_FLAG_TURE.getIntegerValue()}&page=${page - 1}' />"
                                            tabindex="-1">前</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item "><a class="page-link"
                                            href="<c:url value='?action=${actBase}&command=${commIdx}&page=${page - 1}' />"
                                            tabindex="-1">前</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>

                            <c:forEach var="i" begin="1"
                                end="${((bases_count - 1) / maxRow) + 1}" step="1">
                                <c:choose>
                                    <c:when test="${i == page}">
                                        <li class="page-item page-link disabled"><c:out
                                                value="${i}" /></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><c:choose>
                                                <c:when test="${searching != null}">
                                                    <a class="page-link"
                                                        href="<c:url value='?action=${actBase}&command=${commSrhBF}&${AttributeConst.BASE_BREED_FLAG.getValue()}=${AttributeConst.BREED_FLAG_TURE.getIntegerValue()}&page=${i}' />"><c:out
                                                            value="${i}" /></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="page-link"
                                                        href="<c:url value='?action=${actBase}&command=${commIdx}&page=${i}' />"><c:out
                                                            value="${i}" /></a>
                                                </c:otherwise>
                                            </c:choose></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${((bases_count - 1) / maxRow) > page}">
                                <c:choose>
                                    <c:when test="${searching != null}">
                                        <li class="page-item"><a class="page-link"
                                            href="<c:url value='?action=${actBase}&command=${commSrhBF}&${AttributeConst.BASE_BREED_FLAG.getValue()}=${AttributeConst.BREED_FLAG_TURE.getIntegerValue()}&page=${page + 1}' />">次</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                            href="<c:url value='?action=${actBase}&command=${commIdx}&page=${page + 1}' />">次</a></li>
                                    </c:otherwise>
                                </c:choose>

                            </c:if>
                        </ul>
                    </nav>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:param>
</c:import>

