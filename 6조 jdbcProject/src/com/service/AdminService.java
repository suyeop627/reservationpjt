package com.service;

import com.dao.AdminDao;
import com.dto.AdminDto;

import java.util.List;

public class AdminService {
    AdminDao aDao = new AdminDao();
//기유라
    public String insertData(AdminDto aDto) {
        String msg = null;
        int res = aDao.insertData(aDto);
        if (res != 0) {
            msg = "▷신규 관리자 등록에 성공했습니다";
        } else {
            msg = "▷신규 관리자 등록에 실패했습니다";
        }
        return msg;
    }
    public List<AdminDto> getList() {
        List<AdminDto> gList = aDao.selectList();
        return gList;
    }
    public AdminDto getInfo(String userId) {
        AdminDto aDto = null;
        aDto = aDao.selectInfo(userId);
        return aDto;
    }
    public String updateData(AdminDto toUpdateADto, String id) {
        String msg = null;
        int res = aDao.updateData(toUpdateADto, id);
        if (res != 0) {
            msg = "▷관리자 계정 정보 수정이 완료되었습니다.";
        } else {
            msg = "▷관리자 계정 정보 수정에 실패했습니다.";
        }
        return msg;

    }
    public String deleteData(String userId) {
        String msg = null;
        int res = aDao.deleteData(userId);

        if (res != 0) {
            msg = "▷해당 관리자 계정을 삭제했습니다.";

        } else {
            msg = "▷해당 관리자 계정 삭제에 실패했습니다";

        }
        return msg;
    }
    //기유라 end
}
