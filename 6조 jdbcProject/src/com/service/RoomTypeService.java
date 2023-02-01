package com.service;

import com.dao.RoomTypeDao;
import com.dto.RoomTypeDto;

import java.util.List;

public class RoomTypeService {
  RoomTypeDao tDao = new RoomTypeDao();

  //강찬혁
  public RoomTypeDto getRoomType(String roomType) {
    RoomTypeDto rDto = null;
    rDto = tDao.getRoomType(roomType);
    return rDto;
  }

  public String updateInput(RoomTypeDto rDto) {
    String msg = null;
    int res = tDao.updateRoomType(rDto);
    if (res != 0) {
      msg = "정보 변경에 성공했습니다.";
    } else {
      msg = "정보 변경에 실패했습니다.";
    }
    return msg;
  }
  //강찬혁 end

  //김수엽
  public List<RoomTypeDto> getAllRoomTypeData() {
    List<RoomTypeDto> tList = tDao.selectRTList();
    return tList;
  }
  //김수엽 end

  //김인성
  public String roomType(String rType) {
    String getRoomType = null;
    getRoomType = tDao.selectRtype(rType);

    return getRoomType;
  }
  //김인성 end
}
