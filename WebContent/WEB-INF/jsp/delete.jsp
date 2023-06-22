<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アカウント削除</title>
</head>
<body>
<form action="/docoTsubu/AccountDelete" method="post">
<c:out value="${loginUser.name }"/>さんのパスワードを入力してください<input type="password" name="passCheck"><br>

<input type="submit" value="退会">

</form>

</body>
</html>