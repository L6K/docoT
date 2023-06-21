<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録</title>
</head>
<body>
	<form action="/docoTsubu/AccountRegister" method="post">
		ユーザーID<input type="text" name="name"><br>
		パスワード<input type="password" name="pass"><br>
		<input type="submit" value="新規登録">
	</form>
</body>
</html>