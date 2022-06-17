package com.timeattack.service;

import java.util.List;

public interface MemberService {
    long saveMember(RequestCreateMemberDTO requestCreateMemberDTO);
    ResponseMemberDTO findMember(Long id);
    long removeMember(Long id);
    long modifyMember(Long id, RequestUpdateMemberDTO requestUpdateMemberDTO);
    List<ResponseMemberDTO> recommendMember(Long id);
}