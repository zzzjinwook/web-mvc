package com.ssg.webmvc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Builder
public class MemberDTO {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String joinDate;
}
