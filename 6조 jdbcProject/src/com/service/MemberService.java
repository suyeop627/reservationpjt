package com.service;


import com.dao.MemberDao;
import com.dto.MemberDto;

public class MemberService {

  MemberDao mDao = new MemberDao();
  //강찬혁
  public String getMemberId(String name, String phone) {
    String userId = null;
    userId = mDao.selectMemberId(name, phone);
    return userId;
  }
  //강찬혁 end

  //김수엽
  public MemberDto checkGuestData(String[] namePhone) {
    MemberDto mData = mDao.selectGuestData(namePhone);
    return mData;
  }
  //김수엽 end

}
