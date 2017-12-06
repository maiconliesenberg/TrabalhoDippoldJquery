<%-- 
    Document   : SignIn.jsp
    Created on : 29/012/2016, 10:37:06
    Author     : Fabio Tavares Dippold
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%! 

    private String cidadeId;

    private String chamarMetodo() {
        return "ABCD...";
    }

%>

<%
    String userName = (String) session.getAttribute("username");
%>


<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>EASY SERVICES SignIn <%=userName%> <%=chamarMetodo()%></title>

        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/fonts/css/font-awesome.min.css" rel="stylesheet">
        <link href="assets/css/animate.min.css" rel="stylesheet">

        <!-- Custom styling plus plugins -->
        <link href="assets/css/custom.css" rel="stylesheet">
        <link href="assets/css/icheck/flat/green.css" rel="stylesheet">

    </head>

    <body style="background:#F7F7F7;">

        <div>
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>            
            <div id="wrapper">
                <!-- LOGIN FORM -->
                <div id="login" class="animate form">
                    <section class="login_content">
                        <form id="frmsignin"  name="frmsignin" method="POST" action="signin">
                            <h1>Autenticação</h1>
                            <div>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="josefino@gmail.com" required />
                            </div>
                            <div>
                                <input type="password" class="form-control" id="passwd" name="passwd" placeholder="Senha" value="senha" required />
                            </div>
                            <div>
                                <input type="submit" class="btn btn-default submit" value="Autenticar" />
                                ${msg}
                            </div>
                            <div class="clearfix"></div>
                            <div class="separator">
                                <p class="change_link">Novo no site?
                                    <a href="#toregister" class="to_register"> Criar conta </a>
                                </p>
                                <div class="clearfix"></div>
                                <div>
                                    <h2><i class="fa fa-university" style="font-size: 20px;"></i>&nbsp;FTD Software Engineer</h2>
                                    <p><span class="glyphicon glyphicon-copyright-mark"></span>&nbsp;2017 Todos os direitos são reservados.</p>
                                </div>
                            </div>
                        </form>
                        <!-- form -->
                    </section>
                    <!-- content -->
                </div>
                <!-- /LOGIN FORM -->

                <!-- REGISTER FORM -->
                <div id="register" class="animate form">
                    <section class="login_content">
                        <form id="frmregister" name="frmregister" action="ca" onsubmit="return validateRegisterForm(this);">
                            <h1>Criar uma conta</h1>
                            <div>
                                <input type="text" class="form-control" id="newNameInput" name="newNameInput" placeholder="Nome do usuário" required />
                            </div>
                            <div>
                                <input type="email" class="form-control" id="newEmailInput" name="newEmailInput" placeholder="Email" required />
                            </div>
                            <div>
                                <input type="password" class="form-control" id="newPasswdInput" name="newPasswdInput" 
                                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Senha" required title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" />
                            </div>
                            <div>
                                <input type="password" class="form-control" id="newPasswdConfirmInput" name="newPasswdConfirmInput" 
                                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Confirme a senha" required title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"/>
                            </div>                            
                            <div>
                                <!-- <a class="btn btn-default submit" href="ca">Criar</a> -->
                                <input type="submit" class="btn btn-default submit" value="Criar" />
                                ${msg}
                            </div>
                            <div class="clearfix"></div>
                            <div class="separator">
                                <p class="change_link">Já possui uma conta?
                                    <a href="#tologin" class="to_register"> Autenticar </a>
                                </p>
                                <div class="clearfix"></div>
                                <div>
                                    <h2><i class="fa fa-university" style="font-size: 20px;"></i>&nbsp;FTD Software Engineer</h2>
                                    <p><span class="glyphicon glyphicon-copyright-mark"></span>&nbsp;2017 Todos os direitos são reservados.</p>
                                </div>
                            </div>
                        </form>
                        <!-- form -->
                    </section>
                    <!-- content -->
                </div>
                <!-- /REGISTER FORM -->
            </div>
        </div>

        <!-- JQUERY -->                    
        <script src="assets/js/jquery.min.js"></script>
        <!-- /JQUERY --> 

        <!-- FORM REGISTER VALIDATION -->
        <script type="text/javascript">
            var validateRegisterForm = function (form) {

                if (form.newPasswdInput.value !== ""
                        && form.newPasswdInput.value === form.newPasswdConfirmInput.value) {
                    if (form.newPasswdInput.value.length < 6) {
                        alert("Erro: A senha deve conter no mínimo 6 caracteres!");
                        form.newPasswdInput.focus();
                        return false;
                    }
                    if (form.newPasswdInput.value === form.username.value) {
                        alert("Erro: A senha deve ser diferente do nome do usuário!");
                        form.newPasswdInput.focus();
                        return false;
                    }
                    re = /[0-9]/;
                    if (!re.test(form.newPasswdInput.value)) {
                        alert("Erro: A senha deve conter ao menos um número (0-9)!");
                        form.newPasswdInput.focus();
                        return false;
                    }
                    re = /[a-z]/;
                    if (!re.test(form.newPasswdInput.value)) {
                        alert("Erro: A senha deve conter ao menos uma letra em minúsculo (a-z)!");
                        form.newPasswdInput.focus();
                        return false;
                    }
                    re = /[A-Z]/;
                    if (!re.test(form.newPasswdInput.value)) {
                        alert("Erro: A senha deve conter ao menos uma letra em maiúsculo (A-Z)!");
                        form.newPasswdInput.focus();
                        return false;
                    }
                } else {
                    alert("Error: A senha e confirmação da senha não conferem!");
                    form.newPasswdInput.focus();
                    return false;
                }


                return true;
            };
        </script><!-- /FORM REGISTER VALIDATION -->

    </body>
</html>
