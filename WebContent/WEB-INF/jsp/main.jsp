<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<c:out value="${loginUser.name}"/>さん、ログイン中
<a href="/docoTsubu/Logout">ログアウト</a>
<a href="/docoTsubu/AccountDelete">サービス退会</a>
</p>

<p><a href="/docoTsubu/Main">更新</a></p>
<form action="/docoTsubu/Main" method="post" enctype="multipart/form-data">
<input type="file" name="image" accept="image/*">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<c:if test="${not empty errorMsg}">
<p>${errorMsg}</p>
</c:if>
<c:forEach var="mutter" items="${mutterList}">
<p><c:out value="${mutter.userName }"/>:
<c:out value="${mutter.text }"/><br>
<c:if test="${not empty mutter.imagePath }">
<img src="/docoTsubu/upload/${mutter.imagePath }">
</c:if>
</p>
</c:forEach>

</body>
</html>
