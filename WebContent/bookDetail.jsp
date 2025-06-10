<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.kitapkosem.servlet.Review" %>
<%@ include file="header.jsp" %>

<%
    // Session'dan kullanıcı bilgileri alınıyor
    String username = (String) session.getAttribute("username");
    Integer userId = (Integer) session.getAttribute("userId");

    com.kitapkosem.servlet.Book book = (com.kitapkosem.servlet.Book) request.getAttribute("book");
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
    Double averageRating = (Double) request.getAttribute("averageRating");
%>

<html>
<head>
    <title><%= book.getTitle() %> - Detay</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
    <h2><%= book.getTitle() %></h2>
    <p><strong>Yazar:</strong> <%= book.getAuthor() %></p>
    <p><strong>Yayın Yılı:</strong> <%= book.getPublishYear() %></p>
    <p><strong>Açıklama:</strong> <%= book.getDescription() %></p>

    <p><strong>Ortalama Puan:</strong>
        <% if (averageRating != null && averageRating > 0) { %>
            <%= String.format("%.2f", averageRating) %>/5
        <% } else { %>
            Henüz puanlanmamış
        <% } %>
    </p>

    <hr>

    <h3>Yorumlar ve Puanlar</h3>
    <% if (reviews != null && !reviews.isEmpty()) {
        for (Review rev : reviews) { %>
            <div class="mb-3">
                <strong>Puan:</strong> <%= rev.getRating() %>/5<br/>
                <strong>Yorum:</strong> <%= rev.getComment() %><br/>
                <small><%= rev.getReviewDate() %></small>
            </div>
            <hr/>
    <%  }
    } else { %>
        <p>Henüz yorum yapılmamış.</p>
    <% } %>

    <hr>

    <% if (username != null) { %>
        <h3>Yorum Yaz</h3>
        <form action="AddReviewServlet" method="post">
            <input type="hidden" name="bookId" value="<%= book.getId() %>" />
            <div class="mb-3">
                <label for="rating">Puan (1-5):</label>
                <select name="rating" id="rating" class="form-select" required>
                    <% for (int i = 1; i <= 5; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="comment">Yorum:</label>
                <textarea name="comment" id="comment" rows="4" class="form-control" required></textarea>
            </div>
            <button type="submit" class="btn btn-success">Gönder</button>
        </form>
    <% } else { %>
        <p>Yorum yapmak için <a href="login.jsp">giriş yapmalısınız.</a></p>
    <% } %>
</div>
</body>
</html>
