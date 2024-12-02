<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pt-BR">
    <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <title>IFinance - Histórico de Registros financeiros</title>
</head>

<body id="body-pd">
    
    <jsp:include page="../../../WEB-INF/include/navbar.jsp" />
    <main class="bg-light">

        <h1 class="mx-2 my-5">Histórico de Registros Financeiros</h1>

        <c:choose>
            <c:when test="${fn:length(financialRecords) > 0}">
                <table
                        class="table table-bordered table-striped table-hover table-responsive">
                    <thead>
                    <tr>
                        <th scope="col" class="p-3">#</th>
                        <th scope="col" class="p-3">Tipo de Transação</th>
                        <th scope="col" class="p-3">Valor</th>
                        <th scope="col" class="p-3">Data</th>
                        <th scope="col" class="p-3">Categoria</th>
                        <th scope="col" class="p-3">Descrição</th>
                        <th scope="col" colspan="2" class="p-3" style="text-align: center;">Outros</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="financialRecord" items="${financialRecords}"
                               varStatus="index">
                        <tr>
                            <td class="p-3">${financialRecord.id}</td>
                            <td class="p-3">${financialRecord.transactionType}</td>
                            <td class="p-3">
                                <fmt:formatNumber value="${financialRecord.amount}" var="formattedAmount" type="text" pattern=".00"/>
                                R$ ${formattedAmount}
                            </td>
                            <td class="p-3">
                                <fmt:parseDate value="${financialRecord.transactionDate}"
                                               pattern="yyyy-MM-dd" var="parsedDate"
                                               type="date" />
                                <fmt:formatDate value="${parsedDate}"
                                                var="formattedDate" type="date"
                                                pattern="dd/MM/yyyy"/>
                                    ${formattedDate}
                            </td>
                            <td class="p-3">${financialRecord.categoryName}</td>
                            <td class="p-3">
                                <c:choose>
                                    <c:when test="${fn:length(financialRecord.description) > 45}">
                                    <span class="short-description">
                                                        ${fn:substring(financialRecord.description, 0, 45)}...
                                                    </span>
                                    <a type="text/html" class="link-underline-light" data-bs-toggle="modal" data-bs-target="#completeDescriptionModal"
                                       data-bs-text="${financialRecord.description}"> Ler Mais</a>

                                </c:when>
                                <c:otherwise>
                                    <span class="short-description">
                                            ${financialRecord.description}
                                    </span>
                                </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <a href="update-financial-record?id=${financialRecord.id}" class="btn btn-warning btn-sm edit-button btn-icon-text"><i class="fas fa-edit"></i> Editar</a>
                              </td>
                              <td>
                                <a href="#" class="btn btn-danger btn-sm delete-button btn-icon-text"><i class="fas fa-trash-alt"></i> Excluir</a>
                              </td> 

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-center">Nenhum registro financeiro cadastrado.</p>
            </c:otherwise>
        </c:choose>

</main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="assets/js/navbar.js" type="module"></script>
</body>
</html>