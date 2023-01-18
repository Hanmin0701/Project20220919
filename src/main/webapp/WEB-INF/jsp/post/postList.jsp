<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 목록</h1>
		
		<table class="table">
			<thead class="justify-content-center align-items-center">
				<tr>
					<th class="text-weight-bold">No.</th>
					<th class="text-weight-bold">제목</th>
					<th class="text-weight-bold">작성날짜</th>
					<th class="text-weight-bold">수정날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:foreach items="${postList}" var="post" varStatus="status">
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/post_detail_view?postId=${post.id}"></a>${post.subject}</td>
					<td>					
						<fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd a hh:mm:ss" />
					</td>
					<td>					
						<fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd a hh:mm:ss" />
					</td>
				</tr>
			</c:foreach>
			</tbody>
		</table>
		
		<div class="d-flex justify-contetn-end">
			<a href="post/post_create_view" class="btn btn-info">글쓰기</a>
		</div>
	</div>
</div>