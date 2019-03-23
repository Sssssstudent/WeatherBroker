package com.bellintegreator.weather;

import dto.AdminRequest;
import exceptions.AdminException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Сервлет, обрабатывающий запросы на обновление погоды
 */
@WebServlet("/update")
public class AdminServlet extends HttpServlet {

    @Inject
    private JmsCreator jmsCreator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminRequest adminRequest = new AdminRequest(request.getParameter("city"), request.getParameter("region"));
        if (adminRequest.getCity() == null || adminRequest.getRegion() == null || adminRequest.getCity().equals("") || adminRequest.getRegion().equals("")){
            throw new AdminException("Enter parameters city/region");
        }else {
            jmsCreator.sendMessage(adminRequest);
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            request.setAttribute("adminRequest", adminRequest);
            getServletContext().getRequestDispatcher("/adminRequest.jsp").forward(request, response);
        }finally {
            writer.close();
        }
    }

}
