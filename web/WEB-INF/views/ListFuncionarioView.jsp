<%-- 
    Document   : Funcionario
    Created on : 06/12/2017, 20:27:12
    Author     : maicon.liesenberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="main" class="container-fluid"> 

            <!-- SIMPLE MENU BAR -->            
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="../includes/HeaderMenuInclude.jsp" />
                </div>
            </div>
            <!-- /SIMPLE MENU BAR -->
            <button><a class="btn" href="${actionToAdd}" title="ADICIONAR">
            Cadastrar</a></button>
            <!-- DIV-LIST -->
            <div id="list" class="row">
                <!-- SIMPLE GRID... -->
                <div class="table-responsive col-md-12">
                    <!-- TABLE -->
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <!-- HEADER -->
                        <thead>
                            <tr>
                                <th class="actions">
                                    &nbsp;&nbsp;Ações
                                    &nbsp;&nbsp;&nbsp;${listDescriptionLabel}
                                </th>
                            </tr>
                        </thead><!-- /HEADER -->
                        <!-- DATAGRID-LINES -->
                        <tbody>
                            <c:forEach var="o" items="${datasource}">
                                <tr>
                                    <td>
                                        <!-- DESCRIÇÃO PRINCIPAL -->
                                        
                                        <a class="btn btn-warning btn-xs" href="${actionToUpd}${o.id}" title="ATUALIZAR">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                        <a class="btn btn-success btn-xs" href="mvccustomer?do=readmodel&id=${o.id}" title="VISUALIZAR/APAGAR">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                                        &nbsp;&nbsp;
                                        <c:if test="${showColumnId == true}">
                                            <span class="label label-info">${o.id}</span>&nbsp;
                                        </c:if>    
                                        ${o.name}
                                        <!-- /DESCRIÇÃO PRINCIPAL -->
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody><!-- /DATAGRID-LINES -->

                    </table><!-- /TABLE -->
                </div>
                <BR>
            </div><!-- /DIV-LIST -->

        </div><!--/MAIN CONTAINER -->

        <!-- CORE JAVASCRIPT LYBRARIES -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
