<%-- 
    Document   : IdNameCreateView.jsp
    Created on : 31/08/2017
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- HEAD -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="${applicationName}">
        <meta name="author" content="Fábio Tavares Dippold">
        <link rel="icon" href="assets/icons/qb-icon.png">
        <title>${applicationName} - ${title}</title>
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
    </head><!-- /HEAD -->

    <body>    
        <!-- MAIN CONTAINER -->   
        <div id="main" class="container-fluid">

            <div class="row">
                <div class="col-md-6"><h2>${title}</h2></div>
            </div>

            <!-- FORM MAIN -->
            <form id="formCreate" name="formCreate" method="POST" action="${controller}">
                <input type="hidden" id="do" name="do" value="${do}">

                <!-- LINHA-1 -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="nameInput">${fieldNameLabel}:</label>
                        <input type="text" class="form-control" id="nameInput" name="nameInput" max="100" required="required" placeholder="Chave de configuração">
                        <span id="contadorInputName" class="label label-warning">100 Restantes!</span>
                    </div>
                   
                </div><!-- /LINHA-1 -->           

                <!-- LINHA-2 -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="systemCombo">Sistema?</label>                        
                        <!-- COMBOBOX SISTEMA -->
                        <SELECT id="systemCombo" name="systemCombo" size="1" class="form-control">
                            <option value="true">Sim</option> 
                            <option value="false">Não</option> 
                        </SELECT><!-- /COMBOBOX SISTEMA -->   
                    </div> 
                </div><!-- /LINHA-2 -->                   

                <!-- LINHA-3 : BUTTONS SAVE AND CANCEL -->
                <div class="row">
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="${controller}?do=lstmodel" class="btn btn-default">Cancelar</a>
                    </div>
                </div><!-- /LINHA-3 -->
                <br><br>
            </form><!-- /FORM MAIN -->

            <!-- MESSAGE BAR -->
            <jsp:include page="../includes/FooterMessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->

        </div> <!--/MAIN CONTAINER -->        
        <!-- CORE JAVASCRIPT LYBRARIES...
        ================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("#nameInput").keyup(
                        function () {
                            var limite = 100;
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#contadorInputName").text(caracteresRestantes + " Restantes!");
                        }
                );

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->          
    </body><!-- BODY -->        
</html>
