<%-- 
    Document   : ListRodaView
    Created on : 30/08/2017
    Author     : Fabio Tavares Dippold
    Versio     : 0.0.3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- HEAD -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="${app}">

        <link rel="icon" href="assets/icons/qb-icon.png">
        <title>${app}</title>

        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
    </head><!-- /HEAD -->

    <!-- DIV MODAL SAIR -->
    <div class="modal fade" id="sair-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="modalLabel">Ação: SAIR</h4>
                </div>
                <div class="modal-body">Deseja sair do sistema?</div>
                <div class="modal-footer">
                    <button id="btnSair" type="button" class="btn btn-primary" onclick="javascript:$('#formSair').submit();">Sim</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                </div>
            </div>
        </div>
    </div><!-- /DIV MODAL SAIR-->    

    <body>
        <!-- MAIN CONTAINER -->   
        <div id="main" class="container-fluid"> 

            <!-- SIMPLE MENU BAR -->            
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="../includes/HeaderMenuInclude.jsp" />
                </div>
            </div>
            <!-- /SIMPLE MENU BAR -->
            <button><a class="btn" href="mvcproduto?do=addmodel" title="ADICIONAR">
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
                                <th>
                                    Id
                                </th>
                                <th>
                                    Nome
                                </th>
                                <th>
                                    Descrição
                                </th>
                                <th>
                                    Preço
                                </th>
                                <th class="actions">
                                    &nbsp;&nbsp;Ações
                                </th>
                            </tr>
                        </thead><!-- /HEADER -->
                        <!-- DATAGRID-LINES -->
                        <tbody>
                            <c:forEach var="o" items="${datasource}">
                                <tr>
                                    <td>
                                      ${o.id}
                                    </td>
                                    <td>
                                      ${o.nome}  
                                    </td>
                                    <td>
                                      ${o.descricao}  
                                    </td>
                                    <td>
                                      ${o.preco}  
                                    </td>
                                    <td>
                                        <!-- DESCRIÇÃO PRINCIPAL -->
                                        <a class="btn btn-warning btn-xs" href="mvcproduto?do=updmodel&id=${o.id}" title="ATUALIZAR">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                        <a class="btn btn-success btn-xs" href="mvcproduto?do=readmodel&id=${o.id}" title="VISUALIZAR/APAGAR">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
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
        <!-- /CORE JAVASCRIPT LYBRARIES -->
    </body>
</html>