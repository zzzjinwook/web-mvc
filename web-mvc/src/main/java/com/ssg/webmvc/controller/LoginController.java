package com.ssg.webmvc.controller;

import com.ssg.webmvc.dto.MemberDTO;
import com.ssg.webmvc.service.MemberService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        log.info("info login get...");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        log.info("login post called");

        String id= request.getParameter("id");
        String pwd= request.getParameter("pwd");

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(id,pwd);
            System.out.println("memberDTO : " + memberDTO);
            HttpSession session = request.getSession();

            session.setAttribute("loginInfo",memberDTO);
            System.out.println(session);

            response.sendRedirect("/member/listmembers.do");
        } catch (Exception e) {
            response.sendRedirect("/login?result=error");

        }


    }
}
