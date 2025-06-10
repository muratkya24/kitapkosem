<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Bootstrap 5 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
  <div class="container">
    <a class="navbar-brand fw-bold" href="home.jsp">📚 KitapKöşem</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

        <li class="nav-item">
          <a class="nav-link text-white" href="home.jsp">Ana Sayfa</a>
        </li>

        <%
          Object userObj = session.getAttribute("username");
          if (userObj != null) {
              String sessionUsername = (String) userObj;
        %>
            <li class="nav-item">
              <a class="nav-link text-white" href="addBook.jsp">Kitap Ekle</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                 aria-expanded="false">
                👋 Hoşgeldin, <%= sessionUsername %>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li><a class="dropdown-item" href="LogoutServlet">Çıkış Yap</a></li>
              </ul>
            </li>
        <%
          } else {
        %>
            <li class="nav-item">
              <a class="nav-link text-white" href="login.jsp">Giriş Yap</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="register.jsp">Kayıt Ol</a>
            </li>
        <%
          }
        %>

      </ul>
    </div>
  </div>
</nav>

<!-- Bootstrap 5 JS ve Popper -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
