<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "アカウントの編集", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>

	<form action="${root_path}/UserUpdate" method="post">
		<input type="hidden" name="token" value="${token}">
		<div class="row my-5 ">
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<p class="text-center text-decoration-underline">
					<c:out value="${title}" />
				</p>
			</div>
			<div class="col-md-2"></div>

			<div class="col-md-3 text-end">
				<a href="${root_path}/UserUpdate?is_deleted=1">
				<button type="button" class="btn btn-outline-warning">削除</button>
				</a>
			</div>
			<div class="col-md-3">
				<button type="submit" class="btn btn-outline-warning">更新</button>
			</div>
		</div>

		<div class="container-md mt-5">
			<div class="row mb-5 justify-content-center">
				<label for="user_name" class="form-label col-md-3 col-form-label-sm">&#042;アカウント名</label>
				<div class="col-md-5">
					<input type="text" name="user_name" id="user_name" placeholder="アカウント名を入力してください"
						value="<c:out value="${user.user_name}"/>"
						class="form-control
						 <c:if test="${errors.user_name != null }">is-invalid</c:if>">
					<div class="invalid-feedback">${errors.user_name}</div>
				</div>
			</div>
			<div class="row mt-2 mb-5 justify-content-center">
				<label for="email" class="form-label col-md-3 col-form-label-sm">&#042;メールアドレス</label>
				<div class="col-md-5">
					<input type="email" name="email" id="email" placeholder="メールアドレスを入力してください"
					value="<c:out value="${user.email}"/>"
					class="form-control 
					<c:if test="${errors.email != null }">is-invalid</c:if>">
					<div class="invalid-feedback">${errors.email}</div>
					<c:if test="${db_error != null}">
						<div class="alert alert-danger" role="alert">${db_error}</div>
					</c:if>
				</div>
			</div>
			<div class="row mt-2 mb-5 justify-content-center">
				<label for="password" class="form-label col-md-3 col-form-label-sm">&#042;パスワード</label>
				<div class="col-md-5">
					<input type="password" name="password" id="password" placeholder="パスワードを入力してください"
						value="<c:out value="${user.password}"/>" 
						class="form-control <c:if test="${errors.password != null }">is-invalid</c:if>">
					<div class="invalid-feedback">${errors.password}</div>
				</div>
			</div>
			<div class="row mt-5 justify-content-center">
				<a href="${root_path}/Main" class="col-auto link-secondary mt-5">キャンセル</a>
			</div>
		</div>

	</form>

</body>
</html>