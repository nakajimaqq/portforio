<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "アカウント名", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>

	<div class="row my-5 ">
		<div class="col-md-2"></div>
		<div class="col-md-2">
			<p class="text-center text-decoration-underline">
				<a href="${root_path}/UserUpdate">
				<c:out value="${title}：${user.user_name}さん" />
				</a>
			</p>
		</div>
		<div class="col-md-2"></div>

		<div class="col-md-3 text-end">
			<a href="${root_path}/BookRegister">
				<button type="button" class="btn btn-outline-warning">新規追加</button>
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
				<p class="text-center  border p-2">読書記録</p>
			</div>
			<div class="col-4"></div>
		</div>

		<div class="row justify-content-around">
			<c:forEach var="book" items="${books}">

				<div class="card 
				<c:if test="${book.finish == null}">
				border-secondary</c:if>" 
				style="width: 16rem;">
					<div class="card-body ">
						<h5 class="card-title ">
							<c:out value="${book.title}" />
						</h5>
						<h6 class="card-subtitle mb-2 text-muted">
							著者：
							<c:out value="${book.author}" />
						</h6>
						<p class="card-text">
							<fmt:formatDate var="date" value="${book.start}"
								pattern="yyyy/MM/dd（E）" />
							<c:out value="${date}" />
							<br>&#65374;<br>
							<c:if test="${book.finish} != null">
								<fmt:formatDate var="date" value="${book.finish}"
								pattern="yyyy/MM/dd（E）" />
								<c:out value="${date}" />
							</c:if>
							
						</p>
							<a href="${root_path}/Individual?book_id=${book.id}" class="card-link">詳細を見る</a>
					</div>
				</div>
			</c:forEach>
						
		</div>

	</div>


</body>
</html>