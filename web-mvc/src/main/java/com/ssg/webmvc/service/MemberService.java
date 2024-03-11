package com.ssg.webmvc.service;

import com.ssg.webmvc.dao.MemberDAO;
import com.ssg.webmvc.domain.MemberVO;
import com.ssg.webmvc.dto.MemberDTO;
import com.ssg.webmvc.util.MapperUtil;
import java.util.List;
import org.modelmapper.ModelMapper;

public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper mapper;

    MemberService() {
        memberDAO = new MemberDAO();
        mapper = MapperUtil.INSTANCE.get();
    }

    public List<MemberVO> listMembers() {
        return memberDAO.listMembers();
    }

    public void addMember(MemberDTO memberDTO) {
        memberDAO.addMember(memberDTO);
    }

    public void modMember(MemberDTO memberDTO) {
        memberDAO.modMember(memberDTO);
    }

    public void delMember(String id) {
        memberDAO.delMember(id);
    }

    public MemberVO getMember(String id) {
        return memberDAO.getMember(id);
    }

    public MemberDTO login(String id, String pwd) {
        MemberVO vo = null;
        try {
            vo = memberDAO.getWithPassword(id, pwd);
        } catch (Exception e) {
            System.out.println("service에서 에러남");
            System.out.println(e.getMessage());
        }

        if (vo == null) {
            System.out.println("vo가 null임");
            return null;
        }
        System.out.println("dto 변환 전");
        MemberDTO memberDTO = MemberDTO.builder()
            .id(vo.getId())
            .pwd(vo.getPwd())
            .name(vo.getName())
            .email(vo.getEmail())
            .joinDate(vo.getJoinDate())
            .build();
        System.out.println("dto 변환 후");

        System.out.println(memberDTO);
        return memberDTO;
    }

}
