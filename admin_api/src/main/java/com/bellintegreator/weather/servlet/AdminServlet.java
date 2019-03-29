package com.bellintegreator.weather.servlet;

import com.bellintegreator.weather.jms.JmsCreator;
import com.bellintegrator.dto.AdminRequest;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, обрабатывающий запросы на обновление погоды
 */
@WebServlet("/update")
public class AdminServlet extends HttpServlet {

    private final JmsCreator jmsCreator;

    @Inject
    public AdminServlet(JmsCreator jmsCreator) {
        this.jmsCreator = jmsCreator;
    }


    /**
     * Отобразить страницу с формой
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Обработать данные формы
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminRequest adminRequest = new AdminRequest(request.getParameter("city"), request.getParameter("region"));

        if (isCityEmpty(adminRequest)) {
            forwardException(request, response, "City or Region can not be blank");
            return;
        }
        jmsCreator.sendMessage(adminRequest);
        forwardSuccess(request, response, adminRequest);
    }

    /**
     * Обновить страницу, если возникла ошибка
     *
     * @param attribute сообщение об ошибке
     */
    private void forwardException(HttpServletRequest request, HttpServletResponse response, String attribute) throws ServletException, IOException {
        request.setAttribute("errorMessage", attribute);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Загрузить страницу ответа в случае успешного обновления данных
     *
     * @param adminRequest город
     */
    private void forwardSuccess(HttpServletRequest request, HttpServletResponse response, AdminRequest adminRequest) throws ServletException, IOException {
        request.setAttribute("city", adminRequest);
        getServletContext().getRequestDispatcher("/city.jsp").forward(request, response);
    }

    /**
     * Проверка вводимых данных
     *
     * @param adminRequest город
     * @return true, если данные введены некорректно
     */
    private boolean isCityEmpty(AdminRequest adminRequest) {
        return adminRequest.getCity() == null || adminRequest.getCity().trim().isEmpty()
                || adminRequest.getRegion() == null || adminRequest.getRegion().trim().isEmpty();
    }
}
