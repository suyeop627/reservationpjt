package com.service;

import com.dao.BookingDao;
import com.dao.RoomDao;
import com.dto.BookingDto;
import com.dto.RoomDto;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
  RoomDao rDao = new RoomDao();
  BookingDao bDao = new BookingDao();
//김수엽
  private List<RoomDto> compareList(List<RoomDto> rList, List<BookingDto> bookedList) {
    List<RoomDto> toRemove = new ArrayList<>();
    if (rList != null) {
      for (RoomDto r : rList) {
        for (BookingDto b : bookedList) {

          if (r.getR_no() == b.getR_no()) {
            toRemove.add(r);
          }
        }
      }
      rList.removeAll(toRemove);
    } else {
      rList = null;
    }
    return rList;
  }
  public List<RoomDto> checkRoomAvail(int[] inputPeriod) {
    List<RoomDto> rList = rDao.selectRList();
    List<BookingDto> rbList = bDao.selectRBList();
    List<BookingDto> bookedList = findNotAvaliRoom(inputPeriod, rbList);
    List<RoomDto> availRoomList = compareList(rList, bookedList);

    return availRoomList;
  }
  private List<BookingDto> findNotAvaliRoom(int[] inputPeriod, List<BookingDto> rbList) {
    int inputDateStart = inputPeriod[0];
    int inputDateEnd = inputPeriod[1];
    List<BookingDto> bookedList = new ArrayList<>();
    for (BookingDto b : rbList) {
      try {
        int startDate = Integer.parseInt(b.getB_start().replaceAll("-", ""));
        int endDate = Integer.parseInt(b.getB_end().replaceAll("-", ""));
        int[] bookedDate = new int[endDate - startDate + 1];
        int[] wantToBookDate = new int[inputDateEnd - inputDateStart + 1];
        int sd = startDate;
        int ids = inputDateStart;

        for (int i = 0; i < endDate - startDate + 1; i++) {
          bookedDate[i] = sd++;
        }
        for (int i = 0; i < inputDateEnd - inputDateStart + 1; i++) {
          wantToBookDate[i] = ids++;
        }
        for (int j = 0; j < wantToBookDate.length; j++) {
          for (int i = 0; i < bookedDate.length; i++) {
            if (wantToBookDate[j] == bookedDate[i]) {
              bookedList.add(b);
              break;
            }
          }
        }
      } catch (NullPointerException e) {
      }
    }
    return bookedList;
  }
  public List<RoomDto> getAllRoomData() {
    List<RoomDto> rList = rDao.selectRList();
    return rList;
  }
  //김수엽 end

  //김인성
  public String getBookingRoom(int bookingRnum) {
    String getBookingR = null;
    getBookingR = rDao.selectBr(bookingRnum);

    return getBookingR;

  }
  //김인성 end
}
