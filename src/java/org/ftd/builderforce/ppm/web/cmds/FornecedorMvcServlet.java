/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.builderforce.ppm.web.cmds;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.catolica.prog4.daos.ClienteDAO;
import org.ftd.educational.catolica.prog4.daos.FornecedorDAO;
import org.ftd.educational.catolica.prog4.daos.exceptions.NonexistentEntityException;
import org.ftd.educational.catolica.prog4.entities.Cliente;
import org.ftd.educational.catolica.prog4.entities.Fornecedor;

/**
 *
 * @author maicon.liesenberg
 */
@WebServlet(name = "FornecedorMvcServlet", urlPatterns = {"/mvcfornecedor"})
public class FornecedorMvcServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = this.readParameter(request, "do");
        String nextAction;
        switch (action) {
            case "lstmodel":
                nextAction = buildLstModel(request, response);
                break;
            case "addmodel":
                nextAction = buildAddModel(request, response);
                break;
            case "updmodel":
                nextAction = buildUpdModel(request, response);
                break;
            case "readmodel":
                nextAction = buildReadModel(request, response);
                break;
            case "add":
                nextAction = doAddNew(request, response);
                break;
            case "upd":
                nextAction = doUpdate(request, response);
                break;
            case "del":
                nextAction = doRemove(request, response);
                break;
            default:
                request.setAttribute("msg", "Erro controller: recebi obscuro do=" + action);
                nextAction = "signin.jsp";
        }

        request.getRequestDispatcher(nextAction).forward(request, response);
    }
    
    private String buildLstModel(HttpServletRequest request, HttpServletResponse response) {        
        String nextAction = "/WEB-INF/views/ListView.jsp";
        request.setAttribute("applicationName","Compra e venda");
        request.setAttribute("title","Cadastro de Fornecedor");
        
        request.setAttribute("userName", (String) request.getSession().getAttribute("username"));
        request.setAttribute("datasource", this.findAll());        
        request.setAttribute("showColumnId", true);
        request.setAttribute("nomeCpfCnpj", "CNPJ");
        
        request.setAttribute("actionVisu", "mvcfornecedor?do=readmodel&id=");
        request.setAttribute("actionToAdd", "mvcfornecedor?do=addmodel");
        request.setAttribute("actionToUpd", "mvcfornecedor?do=updmodel&id=");
        request.setAttribute("actionToRead", "mvcfornecedor?do=readmodel&id=");
        
        return nextAction;
    }

    private String buildAddModel(HttpServletRequest request, HttpServletResponse response) {
        String nextAction = "/WEB-INF/views/IdNameCreateView.jsp";

        request.setAttribute("applicationName","Compra e venda");
        request.setAttribute("title","Adicionando Novo Fornecedor");
        request.setAttribute("inputCpfCnpj","cnpjInput");
        request.setAttribute("placeholderCpfCnpj","CNPJ");
        
        request.setAttribute("controller","mvcfornecedor");
        request.setAttribute("do","add");
        request.setAttribute("fieldNameLabel","Nome");
        
        return nextAction;
    }

    private String buildUpdModel(HttpServletRequest request, HttpServletResponse response) {
        String nextAction = "/WEB-INF/views/IdNameUpdateView.jsp";
        String id = this.readParameter(request, "id");

        request.setAttribute("applicationName","Compra e venda");
        request.setAttribute("title","Atualizando o Forneceodr " + id);
        
        request.setAttribute("controller","mvcfornecedor");
        request.setAttribute("do","upd");
        request.setAttribute("fieldNameLabel","Nome");
        request.setAttribute("placeholderCpfCnpj","CNPJ");
        
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);
        
        request.setAttribute("entity",dao.findFornecedor(Long.parseLong(id)));
        
        
        return nextAction;
    }

    private String buildReadModel(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("dentro do view");
        String nextAction = "/WEB-INF/views/IdNameReadView.jsp";
        String id = this.readParameter(request, "id");
         request.setAttribute("title","Visualizar cliente");
        request.setAttribute("controller","mvcfornecedor");
        request.setAttribute("botaoCancelar","mvcfornecedor?do=lstmodel");
        
        request.setAttribute("do","del");
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);
        request.setAttribute("entity",dao.findFornecedor(Long.parseLong(id)));
        request.setAttribute("fieldNameLabel","Nome");
        
        return nextAction;
    }

    private String doAddNew(HttpServletRequest request, HttpServletResponse response) {
        String successNextAction = "mvcfornecedor?do=lstmodel";
        String failureNextAction = "mvcfornecedor?do=addmodel";

        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);
        Fornecedor o = new Fornecedor();
        
        o.setName(this.readParameter(request, "nameInput"));
        o.setCnpj(this.readParameter(request, "cnpjInput"));
        
        try {
            dao.create(o);
            
            return successNextAction;
        } catch (Exception e) {
            request.setAttribute("msg", "A criação do registro falhou! Java: " + e.getMessage());
            
            return failureNextAction;
        }

    }

    private String doUpdate(HttpServletRequest request, HttpServletResponse response) {
        String id = this.readParameter(request, "id");
        
        String successNextAction = "mvcfornecedor?do=lstmodel";
        String failureNextAction = "mvcfornecedor?do=updmodel";

        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);                      
        Fornecedor o = dao.findFornecedor(Long.parseLong(id));
        o.setName(this.readParameter(request, "nameInput"));
        o.setCnpj(this.readParameter(request, "cpfInput"));
        
        try {
            dao.edit(o);
            
            return successNextAction;
        } catch (Exception e) {
            request.setAttribute("msg", "A atualização falhou! Java: " + e.getMessage());
            
            return failureNextAction;
        }
    }

    private String doRemove(HttpServletRequest request, HttpServletResponse response) {
        String id = this.readParameter(request, "id");
        
        
        System.out.println("ID DO REMOVE: "+id);
        String successNextAction = "mvcfornecedor?do=lstmodel";
        String failureNextAction = "user?do=readmodel&id=" + id;
        System.out.println("dentro do remove antes");
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);
        try {
            System.out.println("dentro do remove");
            dao.destroy(Long.parseLong(id, 10));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FornecedorMvcServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return successNextAction;
    }

    private String readParameter(HttpServletRequest req, String parameterName) {
        return readParameter(req, parameterName, "");
    }

    private String readParameter(HttpServletRequest req, String parameterName, String defaultValue) {
        String value = req.getParameter(parameterName);
        if ((value == null) || (value.equals(""))) {
            value = defaultValue;
        }

        return value;
    }

    private List<Fornecedor> findAll() {
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        FornecedorDAO dao = new FornecedorDAO(factory);
        
        return dao.findFornecedorEntities();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
