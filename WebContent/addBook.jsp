<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Kitap Ekle</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
    <h2>Yeni Kitap Ekle</h2>

    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <div class="alert alert-danger"><%= errorMessage %></div>
    <% } %>

    <form action="AddBookServlet" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Kitap Başlığı</label>
            <input type="text" class="form-control" id="title" name="title" required />
        </div>
        <div class="mb-3">
            <label for="author" class="form-label">Yazar</label>
            <input type="text" class="form-control" id="author" name="author" required />
        </div>
        <div class="mb-3">
            <label for="publishYear" class="form-label">Yayın Yılı</label>
            <input type="number" class="form-control" id="publishYear" name="publishYear" required />
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Açıklama</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>
        <button type="submit" class="btn btn-success">Ekle</button>
        <a href="BookListServlet" class="btn btn-secondary ms-2">İptal</a>
    </form>
</div>
</body>
</html>
