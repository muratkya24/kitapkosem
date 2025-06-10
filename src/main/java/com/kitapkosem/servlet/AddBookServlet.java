package com.kitapkosem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Veritabanı bağlantı bilgilerini kendine göre düzenle
    private final String jdbcURL = "jdbc:mysql://localhost:3306/kitapkosemdb?useSSL=false&serverTimezone=UTC";
    private final String dbUser = "root";
    private final String dbPassword = "147147";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formdan gelen parametreleri al
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publishYearStr = request.getParameter("publishYear");
        String description = request.getParameter("description");

        int publishYear = 0;
        try {
            publishYear = Integer.parseInt(publishYearStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Yayın yılı geçerli bir sayı olmalıdır.");
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {

                String sql = "INSERT INTO books (title, author, publish_year, description) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, title);
                    statement.setString(2, author);
                    statement.setInt(3, publishYear);
                    statement.setString(4, description);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        // Başarılı ekleme sonrası kitap listesini göster
                        response.sendRedirect("BookListServlet");
                    } else {
                        request.setAttribute("errorMessage", "Kitap ekleme sırasında bir hata oluştu.");
                        request.getRequestDispatcher("addBook.jsp").forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Sunucu hatası: " + e.getMessage());
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }
    }
}
