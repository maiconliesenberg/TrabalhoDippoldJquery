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
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.catolica.prog4.daos.ClienteDAO;
import org.ftd.educational.catolica.prog4.daos.exceptions.NonexistentEntityException;
import org.ftd.educational.catolica.prog4.entities.Cliente;

/**
 *
 * @author maicon.liesenberg
 */
@WebServlet(name = "FuncionarioMvcSevlet", urlPatterns = {"/mvcfuncionario"}, initParams = {
    @WebInitParam(name = "do", value = "")})

public class FuncionarioMvcSevlet extends HttpServlet {
    private static final long serialVersionUID = -1587237767624179860L;
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
        request.setAttribute("title","Cadastro de Funcionario");
        request.setAttribute("applicationName","Compra e venda");
        request.setAttribute("userName", (String) request.getSession().getAttribute("username"));
        request.setAttribute("datasource", this.findAll());        
        request.setAttribute("showColumnId", true);        
        request.setAttribute("actionVisu", "mvcfuncionario?do=readmodel&id=");
        request.setAttribute("actionToAdd", "mvcfuncionario?do=addmodel");
        request.setAttribute("actionToUpd", "mvcfuncionario?do=updmodel&id=");
        request.setAttribute("actionToRead", "mvcfuncionario?do=readmodel&id=");
        
        return nextAction;
    }

    private String buildAddModel(HttpServletRequest request, HttpServletResponse response) {
        String nextAction = "/WEB-INF/views/IdNameCreateView.jsp";

        request.setAttribute("title","Adicionando Novo Cliente");
        
        request.setAttribute("controller","mvccustomer");
        request.setAttribute("do","add");
        request.setAttribute("fieldNameLabel","Nome");
        
        return nextAction;
    }

    private String buildUpdModel(HttpServletRequest request, HttpServletResponse response) {
        String nextAction = "/WEB-INF/views/IdNameUpdateView.jsp";
        String id = this.readParameter(request, "id");
        
        request.setAttribute("title","Atualizando o Cliente " + id);
        
        request.setAttribute("controller","mvcfuncionario");
        request.setAttribute("do","upd");
        request.setAttribute("fieldNameLabel","Nome");        
        
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);
        
        request.setAttribute("entity",dao.findCliente(Long.parseLong(id)));
        
        
        return nextAction;
    }

    private String buildReadModel(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("dentro do view");
        String nextAction = "/WEB-INF/views/IdNameReadView.jsp";
        String id = this.readParameter(request, "id");
         request.setAttribute("title","Visualizar cliente");
        request.setAttribute("controller","mvcfuncionario");
        request.setAttribute("do","del");
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);
        request.setAttribute("entity",dao.findCliente(Long.parseLong(id)));
        request.setAttribute("fieldNameLabel","Nome");
        
        return nextAction;
    }

    private String doAddNew(HttpServletRequest request, HttpServletResponse response) {
        String successNextAction = "mvcfuncionario?do=lstmodel";
        String failureNextAction = "mvcfuncionario?do=addmodel";

        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);
        Cliente o = new Cliente();
        
        o.setName(this.readParameter(request, "nameInput"));
        o.setCpf(this.readParameter(request, "cpfInput"));
        
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
        
        String successNextAction = "mvcfuncionario?do=lstmodel";
        String failureNextAction = "mvcfuncionario?do=updmodel";

        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);                      
        Cliente o = dao.findCliente(Long.parseLong(id));
        o.setName(this.readParameter(request, "nameInput"));
                
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
        String successNextAction = "mvcfuncionario?do=lstmodel";
        String failureNextAction = "user?do=readmodel&id=" + id;
        System.out.println("dentro do remove antes");
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);
        try {
            System.out.println("dentro do remove");
            dao.destroy(Long.parseLong(id, 10));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CustomerMvcServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private List<Cliente> findAll() {
        final String PERSISTENCE_UNIT_NAME = "persistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ClienteDAO dao = new ClienteDAO(factory);
        
        return dao.findClienteEntities();
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
