package com.ssg.webmvc.controller;

import com.ssg.webmvc.dto.MemberDTO;
import com.ssg.webmvc.service.MemberService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet(urlPatterns = {"/member/*"})
public class MemberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/listmembers.do":
                log.info("listmembers 실행");
                showList(request, response);
                break;
            case "/addMember.do":
                showRegisterForm(request, response);
                break;
            case "/viewMember.do":
                viewMember(request, response);
                break;
            case "/modMemberForm.do":
                modMemberForm(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("memberList", MemberService.INSTANCE.listMembers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listMembers.jsp");
        dispatcher.forward(request, response);
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addMemberForm.jsp");
        dispatcher.forward(request, response);
    }

    private void viewMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("memberVO", MemberService.INSTANCE.getMember(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberView.jsp");
        dispatcher.forward(request, response);
    }

    private void modMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("memberVO", MemberService.INSTANCE.getMember(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modMemberForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        switch (pathInfo) {
            case "/addMember.do":
                addMember(request, response);
                break;
            case "/modMember.do":
                updateMember(request, response);
                break;
            case "/delMember.do":
                deleteMember(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberDTO memberDTO = MemberDTO.builder()
            .id(request.getParameter("id"))
            .pwd(request.getParameter("pwd"))
            .name(request.getParameter("name"))
            .email(request.getParameter("email"))
            .build();

        MemberService.INSTANCE.addMember(memberDTO);
        response.sendRedirect(request.getContextPath() + "/member/listmembers.do");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberDTO memberDTO = MemberDTO.builder()
            .id(request.getParameter("id"))
            .pwd(request.getParameter("pwd"))
            .name(request.getParameter("name"))
            .email(request.getParameter("email"))
            .build();

        MemberService.INSTANCE.modMember(memberDTO);
        response.sendRedirect(request.getContextPath() + "/member/listmembers.do");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        MemberService.INSTANCE.delMember(id);
        response.sendRedirect(request.getContextPath() + "/member/listmembers.do");
    }
}
