package com.kitapkosem.servlet;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/kitapkosemdb?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "147147";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
                String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        int userId = rs.getInt("id");

                        // Oturum başlat ve gerekli bilgileri session'a ekle
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setAttribute("userId", userId); // Yorum ve puan için kritik

                        response.sendRedirect("home.jsp");
                    } else {
                        request.setAttribute("error", "Geçersiz kullanıcı adı veya şifre!");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Giriş işlemi sırasında hata oluştu: " + e.getMessage());
        }
    }
}
