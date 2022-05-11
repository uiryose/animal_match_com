<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commTrdIdx" value="${ForwardConst.CMD_TRADE_INDEX.getValue()}" />


<c:import url="../layout/zooapp.jsp">
    <c:param name="content">

    <c:if test="${errors != null}">
        <div class="alert alert-danger">
            <h5>入力内容にエラーがあります</h5>
            <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                <br>
            </c:forEach>
        </div>
    </c:if>

    <h3 class="my-3 py-1 col-12 text-white text-center bg-info rounded">動物園マイページ</h3>
    <div class="row">
            <div class="col-3 font-weight-bold"><c:out value="${login_zoo.zooName}" />さん</div>
            <div class="col-2 pr-0">掲載中の動物：</div><div class="col-1 text-right px-0"><c:out value="${selling_count}" /></div><div class="col-5"> 件</div>
    </div>
    <div class="row">
            <div class="col-3"></div>
            <div class="col-2 pr-0">販売済の動物：</div><div class="col-1 text-right px-0"><c:out value="${sold_count}" /></div><div class="col-5"> 件</div>
    </div>
    <div class="row">
            <div class="col-3"></div>
            <div class="col-2 pr-0">取引中の案件：</div><div class="col-1 text-right px-0"><c:out value="${trades_count}" /></div><div class="col-5"> 件</div>&nbsp;
    </div>


    <h4 class="mb-0 mt-3 py-1 col-12 text-white text-center bg-secondary rounded">販売取引中一覧</h4>
    <div class="row mx-0">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th class="col-1">No.</th>
                    <th class="col-2">名称</th>
                    <th class="col-2">愛称</th>
                    <th class="col-1">性別</th>
                    <th class="col-1">年齢</th>
                    <th class="col-2">取引相手</th>
                    <th class="col-2">チャット画面</th>
                </tr>
            </thead>
            <tbody class="border-bottom">
                <c:forEach var="trade" items="${trades}" varStatus="No">
                     <tr>
                        <td class="align-middle"><c:out value="${No.count}" /></td>
                        <td class="align-middle"><c:out value="${trade[0].animalBase.baseName}" /></td>
                        <td class="align-middle"><c:out value="${trade[0].nickname}" /></td>
                        <td class="align-middle">
                            <c:choose>
                            <c:when test="${trade[0].animalSex == AttributeConst.SEX_MALE.getIntegerValue()}">オス</c:when>
                            <c:when test="${trade[0].animalSex == AttributeConst.SEX_FEMALE.getIntegerValue()}">メス</c:when>
                            <c:otherwise>不明</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle"><c:out value="${trade[0].animalAge}" />&nbsp;才</td>

                        <td class="align-middle">
                            <c:choose>
                                <c:when
                                    test="${trade[1].myUser.userFlag == AttributeConst.USER_CUST.getIntegerValue()}">
                                    <c:forEach var="customer" items="${customers}">
                                        <c:if test="${customer.user.id == trade[1].myUser.id}">
                                            <c:out value="${customer.customerName }" />
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:when
                                    test="${trade[1].myUser.userFlag == AttributeConst.USER_ZOO.getIntegerValue()}">
                                    <c:forEach var="zoo" items="${zoos}">
                                        <c:if test="${zoo.user.id == trade[1].myUser.id}">
                                            <c:out value="${zoo.zooName }" />
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                         </td>
                         <td><a class="btn btn-info my-0 py-1" href="<c:url value='?action=${actZoo}&command=${commTrdIdx}&id=${trade[0].id}&with=${trade[1].myUser.id}' />"> チャット画面</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    </c:param>
</c:import>