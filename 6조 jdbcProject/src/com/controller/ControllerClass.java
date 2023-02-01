package com.controller;

import com.dto.*;
import com.service.*;
import com.view.DataView;

import java.util.List;

public class ControllerClass {

  DataView dView = new DataView();
  MemberService mServ = new MemberService();
  BookingService bServ = new BookingService();
  RoomTypeService tServ = new RoomTypeService();
  RoomService rServ = new RoomService();
  AdminService aServ = new AdminService();

  public void menuRun() {
    while (true) {
      int menu = -1;

      menu = dView.loggedInMenu();
      if (menu == 0) {
        dView.msgPrint("이전 메뉴로 돌아갑니다.");
        break;
      }
      switch (menu) {
        case 1:
          bookingInquiry();
          break;
        case 2:
          addDeleteRun();
          break;
        case 3:
          runUpdate();
          break;

        default:
          dView.msgPrint("0~3사이의 숫자만 입력하시오");
          break;
      }
    }
  }

  //기유라 프로그램 시작, 관리자 계정 관리
  public void run() {
    int menu = -1;
    while (true) {
      menu = dView.loginmenu();
      if (menu == 0) {
        dView.msgPrint("▷이전 메뉴로 돌아갑니다.");
        break;
      }
      switch (menu) {
        case 1:
          int res = login();
          if (res == 1) {
            dView.msgPrint("▷관리자 로그인이 완료됐습니다.");

            adminMenu();

          } else {
            dView.msgPrint("▷관리자 로그인에 실패했습니다.");
          }
          break;
        case 2:
          inputData();
          break;
        default:
          dView.msgPrint("▷0~2번을 입력해주세요.");
          break;
      }//스위치끝
    }//와일문끝
  }

  private void adminMenu() {
    while (true) {
      int menu = -1;
      menu = dView.printAdminMenu();
      if (menu == 0) {
        dView.printMsg("▷이전 메뉴로 돌아갑니다.");
        break;
      }
      switch (menu) {
        case 1:
          menuRun();
          break;
        case 2:
          adminControl();
          break;
        default:
          dView.printMsg("▷0~2까지의 숫자를 입력하세요.");
          break;
      }
    }
  }

  private int login() {
    int result = 0;
    String id = dView.inputlogin("ID : ");
    String pwd = dView.inputlogin("PASS :");
    AdminDto aDto = aServ.getInfo(id);
    try {
      if (id.equals(aDto.getA_id()) && pwd.equals(aDto.getA_pwd())) {
        result = 1;
      }
    } catch (NullPointerException ne) {
      result = 0;
    }

    return result;
  }

  public void adminControl() {
    int menu = -1;
    while (true) {
      menu = dView.showmemu();
      if (menu == 0) {
        dView.msgPrint("▷종료되었습니다.");
        break;
      }
      switch (menu) {

        case 1:
          outData();
          break;
        case 2:
          updateData();
          break;
        case 3:
          deleteData();
          break;
        default:
          dView.msgPrint("▷0~3번을 입력해주세요.");
          break;
      }//스위치끝
    }//와일문 끝
  }

  private void deleteData() {
    String userId = dView.searchId();
    AdminDto aDto = aServ.getInfo(userId);
    dView.output(aDto);
    String msg = aServ.deleteData(userId);
    dView.msgPrint(msg);

  }

  private void updateData() {
    String id = dView.searchId();
    AdminDto aDto = aServ.getInfo(id);
    if (aDto != null) {
      dView.inputUpdata(aDto);
      String msg = aServ.updateData(aDto, id);
      dView.msgPrint(msg);
    }
  }

  private void outData() {
    List<AdminDto> dList = aServ.getList();
    dView.outputlIST(dList);
  }

  private void inputData() {
    AdminDto aDto = new AdminDto();
    dView.inputData(aDto);
    String msg = aServ.insertData(aDto);
    dView.msgPrint(msg);
  }
  //기유라 end



  //강찬혁 예약 내역수정
  public void runUpdate() {
    int menu = -1;
    while (true) {
      menu = dView.secondMenu();
      if (menu == 0) {
        dView.msgPrint("▷수정페이지를 나갑니다.");
        break;
      }
      switch (menu) {
        case 1:
          bookingUpdate();
          break;
        case 2:
          roomTypeUpdate();
          break;
        default:
          dView.msgPrint("▷0에서 2까지의 숫자만 입력하세요");
          break;
      }
    }

  }

  private void roomTypeUpdate() {
    String roomType = dView.search("수정할 RoomType을 입력하세요 : ");
    RoomTypeDto rDto = tServ.getRoomType(roomType);
    dView.outData(rDto);
    if (rDto != null) {
      String yn = dView.isupdate();
      if (yn.equals("y")) {
        dView.updateInput(rDto);

        String msg = tServ.updateInput(rDto);

        dView.msgPrint(msg);
      }
    }
  }

  private void bookingUpdate() {
    String name = dView.search("예약자 이름 검색 : ");
    String phone = dView.search("예약자 전화번호 검색 :");
    String userId = mServ.getMemberId(name, phone);
    BookingDto bDto = bServ.getBooking(userId);
    dView.outData(bDto);
    if (bDto != null) {
      String yn = dView.isupdate();
      if (yn.equals("y")) {
        dView.updateInput(bDto);

        String msg = bServ.updateBooking(bDto);

        dView.msgPrint(msg);
      } else {
        dView.msgPrint("수정을 취소합니다");
      }
    }
  }
  //강찬혁 end



  //김수엽 예약 조회
  public void bookingInquiry() {
    while (true) {
      int menu = -1;
      menu = dView.printInquiryMenu();
      if (menu == 0) {
        dView.printMsg("▷이전 메뉴로 돌아갑니다.");
        break;
      }
      switch (menu) {
        case 1:
          inquireRoomData();
          break;
        case 2:
          inquireGuestData();
          break;
        case 3:
          inquireRoomBookable();
          break;
        default:
          dView.printMsg("▷0~3까지의 숫자를 입력하세요.");
          break;
      }
    }
  }

  private void inquireRoomBookable() {
    int[] inputPeriod = dView.dateToSearch();
    if (inputPeriod == null) {
      return;
    }
    List<RoomDto> availRoomList = rServ.checkRoomAvail(inputPeriod);
    dView.printRoomData(availRoomList);
  }

  private void inquireGuestData() {
    String[] namePhone = dView.inputNamePhone();
    MemberDto mData = mServ.checkGuestData(namePhone);
    dView.checkMember(mData, namePhone);

    if (mData != null) {
      List<BookingDto> gList = bServ.getGuestDataBList(namePhone);
      dView.printGuestBookData(gList, mData);
    }
  }

  private void inquireRoomData() {
    List<RoomDto> rList = rServ.getAllRoomData();
    List<BookingDto> bList = bServ.getAllBookingData();
    List<RoomTypeDto> tList = tServ.getAllRoomTypeData();

    while (true) {
      int checkNum = -1;
      dView.printMap(rList, bList);

      checkNum = dView.printEachRoom(rList, tList, checkNum);
      if (checkNum == 0) {
        break;
      }
    }
  }
  //김수엽 end



  //김인성 예약 추가 및 삭제
  public void addDeleteRun() {
    int menu = -1;

    while (true) {
      menu = dView.showMenu();

      if (menu == 0) {
        dView.printMsg("▷이전 메뉴로 돌아갑니다.");
        break;
      }

      switch (menu) {
        case 1:
          inputbooking();
          break;
        case 2:
          deletedata();
          break;
        default:
          dView.printMsg("▷숫자만 입력해주세요");
      }

    }//while end
  }

  private void deletedata() {
    String id = dView.searchCode("예약 삭제");
    BookingDto data = bServ.getData(id);
    dView.outData(data);

    if (data != null) {
      String yn = dView.delete();
      if (yn.equals("삭제")) {
        String msg = bServ.deleteData(id);
        dView.printMsg(msg);

      }
    }
  }

  private void inputbooking() {
    String bookingId = dView.bookingId();
    String userId = bServ.getBookingId(bookingId);

    if (userId == null) {
      System.out.println("▷등록되지 않은 아이디입니다.");

    } else {
      System.out.println("▷회원 확인이 완료되었습니다.");

      //예약된 회원 방조회


      //예약 안된 회원의 예약할 방이름
      int bookingRnum = dView.bookingR();
      String bookingRoom = rServ.getBookingRoom(bookingRnum);

      if (bookingRoom == null) {
        System.out.println("존재하지 않은 객실 번호를 입력하셨습니다.");

      } else {
        //예약중인지 확인
        dView.printMsg(bookingRoom);

        if (bookingRoom.equals("이미 예약 중인 객실입니다.")) {

        } else {
          String rType = dView.inputRtype();
          String roomType = tServ.roomType(rType);

          if (roomType == null) {
            System.out.println("객실 등급을 다시 확인해주세요.");

          } else {
            BookingDto bDto = new BookingDto();
            dView.bookingInput(bDto, bookingId, bookingRnum, roomType);
            String msg = bServ.insertData1(bDto);
            dView.printMsg(msg);
          }
        }
      }//방상태 end
    }//회원 end
  }
  //김인성 end


}
