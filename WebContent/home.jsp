<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    HttpSession sessionObj = request.getSession(false);
    String username = null;

    if (sessionObj != null) {
        username = (String) sessionObj.getAttribute("username");
    }
%>

<html>
<head>
    <title>KitapKöşem - Ana Sayfa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<div class="container mt-5">
    <h2>
        <% if (username != null) { %>
            Hoş Geldin, <%= username %>!
        <% } else { %>
            Hoş Geldin, Ziyaretçi!
        <% } %>
    </h2>

    <div class="mt-4">
        <% if (username != null) { %>
            <a href="addBook.jsp" class="btn btn-success">Kitap Ekle</a>
            <a href="BookListServlet" class="btn btn-primary">Kitapları Listele</a>
            <a href="logout" class="btn btn-danger">Çıkış Yap</a>
        <% } else { %>
            <a href="login.jsp" class="btn btn-primary">Giriş Yap</a>
            <a href="register.jsp" class="btn btn-secondary">Kayıt Ol</a>
            <a href="BookListServlet" class="btn btn-primary">Kitapları Listele</a>
        <% } %>
    </div>

    <div class="mt-4">
        <p>Burada kitap incelemeleri ve puanlama işlemleri yapılabilir. Devam etmek için yukarıdaki seçenekleri kullan.</p>
    </div>
</div>

</body>
</html>
