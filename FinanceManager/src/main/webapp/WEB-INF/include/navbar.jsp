<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div>
    <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
        <a href="#" class="header_img"> 
            <img src="https://w7.pngwing.com/pngs/178/595/png-transparent-user-profile-computer-icons-login-user-avatars-thumbnail.png" alt=""> 
        </a>
    </header>

    <div class="l-navbar" id="nav-bar">
        <nav class="nav">
            <div>
                <a href="index" class="nav_logo">
                    <i class='bx bx-wallet nav_logo-icon'></i>
                    <span class="nav_logo-name">IFinance</span>
                </a>
                <div class="nav_list">

                    <a href="index" class="nav_link active">
                        <i class='bx bx-grid-alt nav_icon'></i>
                        <span class="nav_name">Dashboard</span>
                    </a>

                    <a href="create-financial-record" class="nav_link">
                        <i class='bx bxs-plus-square'></i>
                        <span class="nav_name">Nova Movimentação</span>
                    </a>

                    <a href="create-category" class="nav_link">
                        <i class='bx bxs-category-alt bx-rotate-270' ></i>
                        <span class="nav_name">Nova Categoria</span>
                    </a>
                    
                    <a href="#" class="nav_link">
                        <i class='bx bx-user nav_icon'></i>
                        <span class="nav_name">Perfil</span>
                    </a>
                    <a href="#" class="nav_link">
                        <i class='bx bx-message-square-detail nav_icon'></i>
                        <span class="nav_name">Notificações</span>
                    </a>
                    <a href="history" class="nav_link">
                        <i class='bx bx-bar-chart-alt-2 nav_icon'></i>
                        <span class="nav_name">Histórico</span>
                    </a>

                    <a href="wallets" class="nav_link">
                        <i class='bx bx-wallet nav_icon'></i>
                        <span class="nav_name">Carteiras</span>
                    </a>

                </div>
            </div>
            <a href="controller?context=users&action=logout" class="nav_link">
                <i class='bx bx-log-out nav_icon'></i>
                <span class="nav_name">SignOut</span>
            </a>
        </nav>
    </div>
</div>