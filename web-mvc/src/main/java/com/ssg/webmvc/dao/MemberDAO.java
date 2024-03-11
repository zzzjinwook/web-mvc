package com.ssg.webmvc.dao;

import com.ssg.webmvc.domain.MemberVO;
import com.ssg.webmvc.dto.MemberDTO;
import com.ssg.webmvc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;

public class MemberDAO {

    public List<MemberVO> listMembers() {
        List<MemberVO> list = new ArrayList<>();
        String query = "SELECT * FROM mvc_member ORDER BY id ASC";

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MemberVO vo = MemberVO.builder()
                    .id(rs.getString("id"))
                    .pwd(rs.getString("pwd"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .joinDate(rs.getString("joinDate"))
                    .build();
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addMember(MemberDTO memberDTO) {
        String query = "INSERT INTO mvc_member (id, pwd, name, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, memberDTO.getId());
            pstmt.setString(2, memberDTO.getPwd());
            pstmt.setString(3, memberDTO.getName());
            pstmt.setString(4, memberDTO.getEmail());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modMember(MemberDTO memberDTO) {
        String query = "UPDATE mvc_member SET pwd=?, name=?, email=? WHERE id=?";

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, memberDTO.getPwd());
            pstmt.setString(2, memberDTO.getName());
            pstmt.setString(3, memberDTO.getEmail());
            pstmt.setString(4, memberDTO.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delMember(String id) {
        String query = "DELETE FROM mvc_member WHERE id=?";

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberVO getMember(String id) {
        MemberVO memberVO = null;
        String query = "SELECT * FROM mvc_member WHERE id=?";

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                memberVO = MemberVO.builder()
                    .id(rs.getString("id"))
                    .pwd(rs.getString("pwd"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .joinDate(rs.getString("joinDate"))
                    .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return memberVO;
    }

    public MemberVO getWithPassword(String id, String pwd) throws Exception {
        String sql = "select id, pwd, name , email from mvc_member where id =? and pwd= ?";

        MemberVO memberVO;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, pwd);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        memberVO = MemberVO.builder()
            .id(rs.getString(1))
            .pwd(rs.getString(2))
            .email(rs.getString(3)).build();

        return memberVO;
    }
}