class Navbar extends HTMLElement {

    connectedCallback() {

        const theme = this.getAttribute("theme");
        const username = this.getAttribute("username");

        if (theme === "secondary") {
            document.documentElement.style.setProperty('--nav-first-color', '#F7F6FB');
            document.documentElement.style.setProperty('--nav-second-color', '#6f51f0');
        } else {
            document.documentElement.style.setProperty('--nav-first-color', '#6f51f0');
            document.documentElement.style.setProperty('--nav-second-color', '#F7F6FB');
        }

        this.innerHTML = `
        <div class="navbar">

            <section class="navbar-section">
                <nav class="navbar-nav">
                    <div>
                        
                        <a href="index" class="navbar-logo-icon">
                            <i class='bx bx-wallet'></i>
                            <span class="nav-logo-text">IFinance</span>
                        </a>

                        <div class="nav-list">
                            <a href="index" class="nav-list-link">
                                <i class='bx bx-grid-alt nav-list-icon'></i>
                                <span class="nav-list-text">Dashboard</span>
                            </a>

                            <a href="create-financial-record" class="nav-list-link">
                                <i class='bx bxs-plus-square nav-list-icon'></i>
                                <span class="nav-list-text">Nova Movimentação</span>
                            </a>

                            <a href="create-category" class="nav-list-link">
                                <i class='bx bxs-category-alt bx-rotate-270 nav-list-icon' ></i>
                                <span class="nav-list-text">Nova Categoria</span>
                            </a>
                            
                            <a href="history" class="nav-list-link">
                                <i class='bx bx-bar-chart-alt-2 nav-list-icon'></i>
                                <span class="nav-list-text">Histórico</span>
                            </a>

                        </div>
                    </div>

                    <div class="nav-list-end">
                        <a href="#" class="nav-list-link">
                            <i class='bx bx-user nav-list-icon'></i>
                            <span class="nav-list-text">${username}</span>
                        </a>

                        <a href="controller?context=users&action=logout" class="nav-list-link">
                            <i class='bx bx-log-out nav-list-icon'></i>
                            <span class="nav-list-text">Sair</span>
                        </a>
                    </div>
                </nav>
            </section>
        </div>`;
    }

}


customElements.define('navbar-header', Navbar);


const loadCollapsibleNavbar = () => {

    const showNavbar = () => {
        const body = document.querySelector("body");
        const navbarSection = document.querySelector(".navbar-section");

        navbarSection.addEventListener("mouseover", () => {
            navbarSection.classList.toggle('show-nav');
            body.classList.toggle('pd-left-nav');
        });

        navbarSection.addEventListener("mouseout", () => {
            navbarSection.classList.toggle('show-nav');
            body.classList.toggle('pd-left-nav');
        });
    }

    showNavbar();

    const links = document.querySelectorAll('.nav-list-link');

    const pathValue = window.location.pathname
        .substring(window.location.pathname.indexOf("/", 2))
        .substring(1);

    links.forEach((link) => {
        let hrefValue = link.getAttribute("href");
        let text = link.querySelector(".nav-list-text").textContent;

        if (hrefValue === pathValue || (text === "Dashboard" && pathValue === "index.jsp") || (text === "Histórico" && pathValue === "update-financial-record")) {
            link.classList.add('active');
        }
        else {
            link.classList.remove('active');
        }
    });
}


document.addEventListener("DOMContentLoaded", () => {
    loadCollapsibleNavbar();
});