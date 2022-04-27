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


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>
        <div>検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行検索行</div>


        <div class="animalbase">

            <div class="row">
                <c:forEach var="animalbase" items="${animalbases}">
                    <div class="col-3 mt-3 base">
                        <a href="<c:url value='?action=${actBase}&command=${commShow}&id=${animalbase.id}' />">
                            <div class="card-body p-0 border h-75 bg-light">
                                <img class="card-img-top"
                                    src="<c:url value='/image/animalbase/${animalbase.baseImage}' /> "
                                    alt="${animalbase.baseName}の画像">

                            </div>
                            <div class="card-footer p-2 border h-25">
                                <small class="text-muted"><c:out
                                        value="${animalbase.baseName}" /></small><br> <small
                                    class="text-muted">掲載実績: xxx件</small>
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

                <c:forEach var="i" begin="1" end="${((base_count - 1) / maxRow) + 1}" step="1">
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


    </c:param>
</c:import>

