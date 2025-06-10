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

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> bookList = new ArrayList<>();

        String jdbcURL = "jdbc:mysql://localhost:3306/kitapkosemdb?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "147147";

        // Arama sorgusu parametresini alıyoruz
        String searchQuery = request.getParameter("search");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql;
            PreparedStatement statement;

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                // Arama varsa title'da LIKE ile filtre yap
                sql = "SELECT * FROM books WHERE title LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + searchQuery.trim() + "%");
            } else {
                // Arama yoksa tüm kitapları getir
                sql = "SELECT * FROM books";
                statement = connection.prepareStatement(sql);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("description"),
                        resultSet.getInt("publish_year")
                );
                bookList.add(book);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("bookList", bookList);
        request.setAttribute("searchQuery", searchQuery);  // Arama kutusunu JSP'de doldurmak için
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }
}
