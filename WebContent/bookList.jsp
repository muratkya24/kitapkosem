<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.kitapkosem.servlet.Book" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Kitap Listesi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
    <h2>Kitap Listesi</h2>

    <!-- Arama Formu -->
    <form action="BookListServlet" method="get" class="mb-3">
        <div class="input-group">
            <input type="text" name="search" class="form-control" placeholder="Kitap adına göre ara..."
                   value="<%= request.getAttribute("searchQuery") != null ? request.getAttribute("searchQuery") : "" %>"/>
            <button class="btn btn-primary" type="submit">Ara</button>
        </div>
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Başlık</th>
            <th>Yazar</th>
            <th>Yayın Yılı</th>
            <th>Açıklama</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Book> books = (List<Book>) request.getAttribute("bookList");
            if (books != null && !books.isEmpty()) {
                for (Book book : books) {
        %>
        <tr>
            <td><a href="BookDetailServlet?bookId=<%=book.getId()%>"><%=book.getTitle()%></a></td>
            <td><%=book.getAuthor()%></td>
            <td><%=book.getPublishYear()%></td>
            <td><%=book.getDescription()%></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4">Kayıtlı kitap bulunamadı.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
