<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/docoTsubu/css/style.css">
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>どこつぶ</title>
</head>
<body>
	<h1 id="title">どこつぶへようこそ</h1>
	<form action="/docoTsubu/Login" method="post">
		ユーザー名：<input type="text" name="name"><br>
		パスワード：<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<a href="/docoTsubu/AccountRegister">新規登録</a>
</body>
</html>