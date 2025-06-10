package com.kitapkosem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String jdbcURL = "jdbc:mysql://localhost:3306/kitapkosemdb?useSSL=false&serverTimezone=UTC";
    private final String dbUser = "root";
    private final String dbPassword = "147147";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Oturum kontrolü
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Kullanıcı ve formdan gelen veriler
        int userId = (Integer) session.getAttribute("userId");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // Basit validasyon
        if (rating < 1 || rating > 5) {
            response.sendRedirect("BookDetailServlet?bookId=" + bookId + "&error=invalidRating");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                String sql = "INSERT INTO reviews (book_id, user_id, rating, comment, review_date) VALUES (?, ?, ?, ?, NOW())";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, bookId);
                    stmt.setInt(2, userId);
                    stmt.setInt(3, rating);
                    stmt.setString(4, comment);
                    stmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kitap detay sayfasına geri yönlendir
        response.sendRedirect("BookDetailServlet?bookId=" + bookId);
    }
}
