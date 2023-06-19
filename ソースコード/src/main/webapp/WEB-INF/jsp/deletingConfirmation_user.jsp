<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "アカウント削除の確認", PageContext.PAGE_SCOPE);
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
				<p class="text-center">本当に削除しますか&#063;</p>
				<p class="text-center  mt-4">
					<small class="bg-light">&#042;アカウントを削除すると、<br>全ての機能がご利用できなくなりますがよろしいですか&#063;
					</small>
				</p>
				<div class="row mt-3 justify-content-center">
					<div class="col-auto">
						<a href="${root_path}/UserUpdate" role="button"
							class="col-auto btn btn-sm btn-outline-secondary bg-light px-3 mt-5 me-4">
							キャンセル </a>
					</div>
					<div class="col-auto">
						<form action="${root_path}/UserUpdate" method="post" >	
							<input type="hidden" name="token" value="${token}">
							<input type="hidden" name="is_deleted" value="1" />
							<input	type="submit" value="OK"
									class="col-auto btn btn-sm btn-outline-secondary bg-light px-3 mt-5" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>