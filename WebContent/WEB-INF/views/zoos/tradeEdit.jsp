<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst"%>

<c:set var="actZoo" value="${ForwardConst.ACT_ZOO.getValue()}" />
<c:set var="actCust" value="${ForwardConst.ACT_CUST.getValue()}" />
<c:set var="actChat" value="${ForwardConst.ACT_CHAT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDst" value="${ForwardConst.CMD_DESTROY.getValue()}" />
<c:set var="commTrdIdx" value="${ForwardConst.CMD_TRADE_INDEX.getValue()}" />
<c:set var="commTrdCrt" value="${ForwardConst.CMD_TRADE_CREATE.getValue()}" />
<c:set var="commTrdUpd" value="${ForwardConst.CMD_TRADE_UPDATE.getValue()}" />
<c:set var="commTrdDst" value="${ForwardConst.CMD_TRADE_DESTROY.getValue()}" />


<c:import url="../layout/zooapp.jsp">
  <c:param name ="content">

    <c:import url="../chats/_chatform.jsp" />

<!-- 編集用のjsp -->
        <form method="POST"
            action="<c:url value='?action=${actZoo}&command=${commTrdUpd}&id=${animal.id}&with=${buy_user_id}'/>">
            <div class="form-group row">
                <div class="col-2"></div>
                <div class="form-group col-8 pt-4 ">
                    <label class="mb-0" for="${AttributeConst.COMMENT_CONTENT.getValue()}">メッセージを編集</label><br>
                    <textarea class="form-control "
                        name="${AttributeConst.COMMENT_CONTENT.getValue()}" rows="3" required>${comment_edit.content}</textarea>
                </div>
                <input type="hidden" name="${AttributeConst.COMMENT_EDIT.getValue()}" value="${comment_edit.id}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-info col-8">コメントを編集する</button>
                </div>
            </div>
        </form>


<!-- 削除用のjsp -->
        <div class="row">
            <div class="col-2"></div>
            <div class="col-4 pl-2 pr-2">
                <a class="btn btn-danger w-100" href="<c:url value='?action=${actZoo}&command=${commTrdDst}&id=${animal.id}&with=${buy_user_id}&comment_edit=${comment_edit.id}'/>" onclick="confirmDestroy();">このコメントを削除する</a>
            </div>
            <div class="col-4 pr-2 pl-1">
                <a class="btn btn-info w-100"
                    href="<c:url value='?action=${actZoo}&command=${commTrdIdx}&id=${animal.id}&with=${buy_user_id}' />">新規投稿に戻る</a>
            </div>
        </div>
        <script>
            function confirmDestroy(){
                if(confirm("本当に削除してよろしいですか？")){
                    document.forms[1].submit();
                }
            }
        </script>

    </c:param>
</c:import>