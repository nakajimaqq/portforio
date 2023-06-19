<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "書籍データ・読書記録の表示", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>


	<div class="row my-5 ">
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<p class="text-center text-decoration-underline">
				<c:out value="${title}" />
			</p>
		</div>
		<div class="col-md-1"></div>


		<div class="col-md-3 text-end">
			<a href="${root_path}/Individual?book_id=${book.id}&update=update">
				<button type="button" class="btn btn-outline-warning">編集</button>
			</a>
		</div>
		<div class="col-md-3">
			<a href="${root_path}/Logout">
				<button type="button" class="btn btn-outline-warning">ログアウト</button>
			</a>
		</div>

	</div>

	<div class="container-md-my-2 justify-content-center">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4 mb-5">
				<p class="text-center  border p-2">
					<c:out value="${book.title}" />
				</p>
			</div>
			<div class="col-4"></div>
		</div>
		<div class="row justify-content-between">
			<div class="col-7">
				<p class="text-end">
					読書期間：
					<fmt:formatDate var="date" value="${book.start}"
						pattern="yyyy/MM/dd (E)" />
					<c:out value="${date}" />
					&#65374;
					<fmt:formatDate var="date" value="${book.finish}"
						pattern="yyyy/MM/dd (E)" />
					<c:out value="${date}" />
				</p>
			</div>
			<div class="col-4">
				<div class="row">
					<p>
						著者：
						<c:out value="${book.author}" />
					</p>
				</div>
				<div class="row">
					<p>
						発行年：
						<c:choose>
							<c:when test="${book.publication} == 0">
								<c:out value="" />
							</c:when>
							<c:otherwise>
								<c:out value="${book.publication}" />
							</c:otherwise>
						</c:choose>
						年
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="row mt-5 justify-content-center">
		<p class="border mb-3 col-md-8 text-center">
			<c:out value="${book.impressions}" />
		</p>
	</div>

	<div class="row mt-5 justify-content-center">
		<a href="${root_path}/Main" class="col-auto link-secondary mt-5">トップへ</a>
	</div>
</body>
</html>