package com.kitapkosem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String jdbcURL = "jdbc:mysql://localhost:3306/kitapkosemdb?useSSL=false&serverTimezone=UTC";
    private final String dbUser = "root";
    private final String dbPassword = "147147";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookIdParam = request.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.isEmpty()) {
            response.sendRedirect("booklist.jsp");
            return;
        }

        int bookId = Integer.parseInt(bookIdParam);
        Book book = null;
        List<Review> reviews = new ArrayList<>();
        double averageRating = 0.0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Kitap bilgisi
            String bookQuery = "SELECT * FROM books WHERE id = ?";
            PreparedStatement bookStmt = connection.prepareStatement(bookQuery);
            bookStmt.setInt(1, bookId);
            ResultSet bookRs = bookStmt.executeQuery();

            if (bookRs.next()) {
                book = new Book(
                        bookRs.getInt("id"),
                        bookRs.getString("title"),
                        bookRs.getString("author"),
                        bookRs.getString("description"),
                        bookRs.getInt("publish_year")
                );
            }

            // Yorumlar
            String reviewQuery = "SELECT * FROM reviews WHERE book_id = ? ORDER BY review_date DESC";
            PreparedStatement reviewStmt = connection.prepareStatement(reviewQuery);
            reviewStmt.setInt(1, bookId);
            ResultSet reviewRs = reviewStmt.executeQuery();

            while (reviewRs.next()) {
                Review rev = new Review(
                        reviewRs.getInt("id"),
                        reviewRs.getInt("book_id"),
                        reviewRs.getInt("user_id"),
                        reviewRs.getInt("rating"),
                        reviewRs.getString("comment"),
                        reviewRs.getTimestamp("review_date")
                );
                reviews.add(rev);
            }

            // Ortalama puan
            String avgQuery = "SELECT AVG(rating) AS average_rating FROM reviews WHERE book_id = ?";
            PreparedStatement avgStmt = connection.prepareStatement(avgQuery);
            avgStmt.setInt(1, bookId);
            ResultSet avgRs = avgStmt.executeQuery();
            if (avgRs.next()) {
                averageRating = avgRs.getDouble("average_rating");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("book", book);
        request.setAttribute("reviews", reviews);
        request.setAttribute("averageRating", averageRating);

        request.getRequestDispatcher("bookDetail.jsp").forward(request, response);
    }
}
