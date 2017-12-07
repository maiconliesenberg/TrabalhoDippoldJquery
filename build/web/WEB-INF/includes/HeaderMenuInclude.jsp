<%-- 
    Document   : HeaderMenuInclude
    Created on : 04/12/2017, 18:40:20
    Author     : ftdippold
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="mvcmenu?do=lstmodel">${applicationName}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="mvcfuncionario?do=lstmodel">Funcionario</a></li>
            <li><a href="mvccustomer?do=lstmodel">Cliente</a></li>
            <li><a href="#">Produto</a></li>
            <li><a href="mvcfornecedor?do=lstmodel">Fornecedor</a></li>
        </ul>
        
        <ul class="nav navbar-nav navbar-right">
            <li><a>Usuario: ${userName}</a></li>
            <li><a href="logout"><span class="glyphicon glyphicon-user"></span>Sair</a></li>
        </ul>
    </div>
</nav>
