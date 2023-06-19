<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "ログイン", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>
	<div class="container-md mt-5">
		<form action="${root_path}/Login" method="post">
			<input type="hidden" name="token" value="${token}">
			<h1 class="text-center">図書カードアプリ</h1>
			<div class="container row-8 my-5">
				<div class="row mb-4 justify-content-center">
					<label for="user_name" class="form-label col-md-3 col-form-label-sm">アカウント名</label>
					<div class="col-md-5">
						<input type="text" name="user_name" id="user_name" class="form-control">
					</div>
				</div>
				<div class="row mt-2 mb-5 justify-content-center">
					<label for="password" class="form-label col-md-3 col-form-label-sm">パスワード</label>
					<div class="col-md-5">
						<input type="password" name="password" id="password" class="form-control">
					</div>
				</div>
				<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">${error}</div>
				</c:if>
			
			</div>
			<div class="row mt-5 justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-secondary">ログイン</button>
				</div>
				<div class="col-2">
					<a href="${root_path}/UserRegister" >
						<button type="button" class="btn btn-secondary">新規登録</button>
					</a>
				</div>
			</div>

		</form>

	</div>
</body>
</html>