<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "書籍データ・読書記録の編集", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="ja">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>

<body>

	<form action="${root_path}/Individual" method="post">
		<input type="hidden" name="token" value="${token}">
		<div class="row my-5 ">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<p class="text-center text-decoration-underline">
					<c:out value="${title}" />
				</p>
			</div>

			<div class="col-md-3 text-end">
				<button type="submit" name="update" value="update"
					class="btn btn-outline-warning">更新</button>
			</div>
			<div class="col-md-3">
				<a href="${root_path}/Individual?is_deleted=1">
					<button type="button" class="btn btn-outline-warning">削除</button>
				</a>
			</div>


		</div>

		<div class="container-md-my-2 justify-content-center">
			<div class="row">
				<div class="col-4"></div>
				<div class="col-4 mb-5">
					<label for="title" class="form-label col-md-3 col-form-label-sm">タイトル</label>
					<input type="text" name="title" id="title" required
						value="${book.title}"
						class="text-center p-2 form-control 
						<c:if test="${errors.title != null }">is-invalid</c:if>" />
					<div class="invalid-feedback">${errors.title}</div>
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row ">
				<div class="col col-md-7">
					<div class="row justify-content-center">
						<div class="col col-md-4">
							<label for="start" class="form-label col-form-label-sm">読書期間（開始）</label>
							<input type="date" name="start" id="start" required
								value="${book.start}"
								class="text-end form-control <c:if test="${errors.start != null }">is-invalid</c:if>" />
							<div class="invalid-feedback">${errors.start}</div>
						</div>
						<div class="col col-md-auto mt-5">&#65374;</div>
						<div class="col col-md-4">
							<label for="finish" class="form-label col-form-label-sm">読書期間（終了）</label>
							<input type="date" name="finish" id="finish"
								value="${book.finish}" class="form-control text-end" />
						</div>
					</div>
				</div>
				<div class="col-md-4 ">
					<div class="row">
						<label for="author" class="form-label col-md-3 col-form-label-sm">著者</label>
						<input type="text" name="author" id="author"
							value="${book.author}" class="form-control" />
					</div>
					<div class="row">
						<label for="publication"
							class="form-label col-md-3 col-form-label-sm">発行年</label>
							<input
							type="text" name="publication" id="publication"
							value="${book.publication}" class="form-control text-end" />
					</div>
				</div>

			</div>
			<div class="row mt-5 justify-content-center">
				<div class="col col-md-2"></div>
				<div class="col col-md-7">
					<label for="impressions"
						class="form-label col-md-3 col-form-label-sm">感想</label>
					<textarea rows="5" name="impressions" id="impressions" class="form-control col-md-7 mb-3 "><c:out value="${book.impressions}"/></textarea>
				</div>
				<div class="col col-md-2"></div>
			</div>

			<div class="row mt-5 justify-content-center">
				<a href="${root_path}/Individual?book_id=${book.id}"
					class="col-auto link-secondary mt-5">キャンセル</a>
			</div>
		</div>
	</form>
</body>
</html>