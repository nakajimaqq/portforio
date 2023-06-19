<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "エラー", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>
	<div class="container-md">
		<div class="row my-5 ">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<p class="text-center text-decoration-underline">
				<c:out value="${title}" />
				</p>
			</div>
			<div class="row my-5"></div>
			<div class="row mt-5 justify-content-center">
				<p class="text-center alert alert-danger" role="alert">エラーが発生しました。</p>
			</div>
		</div>
	</div>
	

</body>
</html>