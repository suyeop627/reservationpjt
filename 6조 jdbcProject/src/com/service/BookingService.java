package com.service;

import com.dao.BookingDao;
import com.dto.BookingDto;

import java.util.List;

public class BookingService {

  BookingDao bDao = new BookingDao();

  //강찬혁
  public BookingDto getBooking(String userId) {
    BookingDto bDto = null;

    bDto = bDao.getBooking(userId);

    return bDto;
  }

  public String updateBooking(BookingDto bDto) {
    String msg = null;
    int res = bDao.updateBooking(bDto);
    if (res != 0) {
      msg = "변경 성공";
    } else {
      msg = "변경실패";
    }
    return msg;
  }
  //강찬혁 end

  //김수엽
  public List<BookingDto> getAllBookingData() {
    List<BookingDto> bList = bDao.selectBList();
    return bList;
  }

  public List<BookingDto> getGuestDataBList(String[] namePhone) {
    List<BookingDto> gList = bDao.selectGuestData(namePhone);
    return gList;
  }
  //김수엽 end


  //김인성
  public String insertData1(BookingDto bDto) {
    String msg = null;

    int res = bDao.insertData1(bDto);

    if (res == 0) {
      msg = "입력 실패";
    } else {
      msg = "예약 성공하셨습니다.";
    }
    return msg;
  }

  public String getBookingId(String bookingId) {
    String BookingId = null;
    BookingId = bDao.selectId(bookingId);

    return BookingId;
  }

  public BookingDto getData(String id) {
    BookingDto data = null;
    data = bDao.selectData(id);

    return data;
  }

  public String deleteData(String id) {
    String msg = null;

    int res = bDao.deleteData(id);

    if (res > 0) {
      msg = "예약 취소하셨습니다.";
    } else {
      msg = "예약 취소 실패하셨습니다";
    }

    return msg;
  }

  //김인성 end


}
