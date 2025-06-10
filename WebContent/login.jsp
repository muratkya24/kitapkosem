<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giriş Yap - KitapKöşem</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h2>Giriş Yap</h2>
    <form action="LoginServlet" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Kullanıcı Adı</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Şifre</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Giriş Yap</button>
    </form>
</div>
</body>
</html>
