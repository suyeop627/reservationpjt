package com.view;

import com.dto.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataView {

  InOutClass ioc = new InOutClass();

  //강찬혁
  public void msgPrint(String str) {
    ioc.twoPrint(str);
  }

  public void subTitle(String str) {
    ioc.twoPrint("<" + str + ">");
    ioc.twoPrint("---------------------------------------------------------------");
  }

  public int secondMenu() {
    int menu = -1;
    ioc.twoPrint("===============================================================");
    subTitle("정보수정");
    ioc.twoPrint("1. 예약 정보 수정");
    ioc.twoPrint("2. 객실 정보 수정");
    ioc.twoPrint("0. 이전 메뉴로 돌아가기");
    menu = ioc.inNum("선택>");
    return menu;
  }

  public String search(String str) {
    String s = null;
    s = ioc.inStr(str);

    return s;
  }

  public void updateInput(BookingDto bDto) {
    subTitle("수정");
    String str = null;
    int num = 0;
    num = ioc.inNum("변경할 객실(호) : ");
    if (num != -1) {
      bDto.setR_no(num);
    }
    str = ioc.inStr("변경할 객실 등급 : ");
    if (!str.equals("")) {
      bDto.setT_name(str);
    }
    str = ioc.inStr("변경할 예약시작일자(yyyy-mm-dd) : ");
    if (!str.equals("")) {
      bDto.setB_start(str);
    }
    str = ioc.inStr("변경할 예약종료일자(yyyy-mm-dd) : ");
    if (!str.equals("")) {
      bDto.setB_end(str);
    }
  }

  public void outData(BookingDto bDto) {
    subTitle("검색한 데이터");
    if (bDto == null) {
      msgPrint("▷데이터가 없습니다");
      return;
    }
    ioc.twoPrint(bDto.toString());
    ioc.twoPrint("---------------------------------------------------------------");
  }

  public String isupdate() {
    String yn = ioc.inStr("수정하시겠습니까?(y/n)");
    return yn;
  }

  public void outData(RoomTypeDto rDto) {
    subTitle("검색한 데이터");
    if (rDto == null) {
      msgPrint("▷데이터가 없습니다");
      return;
    }
    ioc.twoPrint(rDto.toString());
    ioc.twoPrint("---------------------------------------------------------------");
  }

  public void updateInput(RoomTypeDto rDto) {
    subTitle("수정");
    String str = null;
    int num = -1;
    num = ioc.inNum("변경할 가격 : ");
    rDto.setT_price(num);
    str = ioc.inStr("특이사항 추가 : ");
    rDto.setT_specialnote(str);
  }

  //강찬혁 end


  //김수엽
  public void printMsg(String msg) {
    ioc.twoPrint(msg);
  }

  public int printInquiryMenu() {
    int menu = -1;
    ioc.twoPrint("===============================================================");
    ioc.twoPrint("▶정보 조회");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("1. 객실 정보 조회");
    ioc.twoPrint("2. 예약자 조회");
    ioc.twoPrint("3. 기간 내 예약 가능 객실 조회");
    ioc.twoPrint("0. 이전 메뉴로 돌아가기");
    menu = ioc.inNum("선택>");
    ioc.twoPrint("---------------------------------------------------------------");
    return menu;
  }

  public void printRoomData(List<RoomDto> rList) {
    ioc.twoPrint("▶ 기간 내 예약 가능 객실 조회");
    ioc.twoPrint("---------------------------------------------------------------");
    if (rList == null) {
      ioc.twoPrint("▷입력하신 기간에 사용 가능한 객실 정보가 없습니다.");

      return;
    }
    for (RoomDto r : rList) {
      ioc.twoPrint(r.getR_no() + "호실(" + r.getT_name() + ") ＊" + r.getR_status());
      ioc.twoPrint("---------------------------------------------------------------");

    }
  }

  public String[] inputNamePhone() {
    String[] namePhone = new String[2];
    ioc.twoPrint("▶ 예약자 조회");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("▷검색할 회원의 이름과 전화번호(형식 : 000-0000-0000)를 입력해주세요");

    namePhone[0] = ioc.inStr("회원이름 : ");
    namePhone[1] = ioc.inStr("전화번호 : ");
    ioc.twoPrint("---------------------------------------------------------------");
    return namePhone;
  }

  public void printGuestBookData(List<BookingDto> gList, MemberDto mData) {
    for (BookingDto bData : gList) {
      if (bData.getR_no() != 0) {
        ioc.twoPrint("예약번호 : " + bData.getB_no());
        ioc.twoPrint("객실번호 : " + bData.getR_no());
        ioc.twoPrint("객실등급 : " + bData.getT_name());
        ioc.twoPrint("예약일정 : " + bData.getB_start() + "~" + bData.getB_end());
      } else {
        ioc.twoPrint("'" + mData.getM_name() + "'님의 예약 정보가 없습니다.");
        ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
      }
    }
  }

  public void checkMember(MemberDto mData, String[] namePhone) {
    if (mData != null) {
      ioc.twoPrint("<'" + mData.getM_name() + "'님의 예약정보>");
      ioc.twoPrint("연락처 : " + mData.getM_phone());

    } else {
      ioc.twoPrint("▷입력하신 이름(" + namePhone[0] + "), 전화번호(" + namePhone[1] + ")와 일치하는 회원정보가 없습니다.");
      ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
    }
  }

  public int[] dateToSearch() {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = format.format(date);

    int[] inputPeriod = new int[2];
    ioc.twoPrint("▶ 예약 가능 기간 조회");
    ioc.twoPrint("▷예약을 원하시는 기간을 입력해주세요. (오늘날짜 : " + todayStr + ")");

    String inputStartStr = ioc.inStr("입실날짜(yyyy-mm-dd) : ");
    String inputEndStr = ioc.inStr("퇴실날짜(yyyy-mm-dd) : ");
    ioc.twoPrint("---------------------------------------------------------------");
    try {
      int inputStart = Integer.parseInt(inputStartStr.replaceAll("-", ""));
      int inputEnd = Integer.parseInt(inputEndStr.replaceAll("-", ""));
      int today = Integer.parseInt(todayStr.replaceAll("-", ""));
      if (inputStart <= 10000000 || inputEnd >= 99999999) {
        ioc.twoPrint("▷날짜 확인 후 다시 입력해주세요.(오늘날짜 : " + todayStr + ")");
        ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
        inputPeriod = null;
      } else if (inputStart > inputEnd) {
        ioc.twoPrint("▷날짜 확인 후 다시 입력해주세요.(오늘날짜 : " + todayStr + ")");
        ioc.twoPrint("▷입실날짜는 퇴실날짜보다 빠른 날짜여야 합니다.");
        ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
        inputPeriod = null;
      } else if (inputStart <= today) {
        ioc.twoPrint("▷예약가능일을 확인하시려면, 오늘 이후의 날짜를 입력해주세요.(오늘날짜 : " + todayStr + ")");
        ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
        inputPeriod = null;
      }  else {
        inputPeriod[0] = inputStart;
        inputPeriod[1] = inputEnd;
      }
    } catch (NumberFormatException nfe) {
      ioc.twoPrint("▷날짜 형식(yyyy-mm-dd)에 맞춰 다시 입력해주세요.");
      ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");

      inputPeriod = null;
    }
    return inputPeriod;
  }

  public void printMap(List<RoomDto> rList, List<BookingDto> bList) {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = format.format(date);
    ioc.twoPrint("▶ 객실 조회 (오늘날짜 : " + todayStr + ")");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("✅ : 현재 사용중인 객실");
    ioc.twoPrint("⬜ : 빈 객실\n");

    int today = Integer.parseInt(todayStr.replaceAll("-", ""));
    List<RoomDto> reverseRList = new ArrayList<>();

    for (int i = 1; i <= rList.size(); i++) {
      reverseRList.add(i - 1, (rList.get(rList.size() - i)));
    }
    if (bList != null) {
      List<RoomDto> roomBookedToday = new ArrayList<>();
      for (RoomDto r : reverseRList) {
        for (BookingDto b : bList) {
          int startDate = Integer.parseInt(b.getB_start().replaceAll("-", ""));
          int endDate = Integer.parseInt(b.getB_end().replaceAll("-", ""));
          if ((startDate <= today && today <= endDate) && (r.getR_no() == b.getR_no())) {
            roomBookedToday.add(r);
          }
        }
      }
      for (RoomDto r : reverseRList) {
        if (roomBookedToday.contains(r)) {
          switch (r.getR_no() % 100) {
            case 4:
              ioc.onePrint("[" + r.getR_no() + "✅]\t");
              break;
            case 1:
              ioc.twoPrint("[" + r.getR_no() + "✅]");
              break;
            default:
              ioc.onePrint("[" + r.getR_no() + "✅]");
              break;
          }
        } else {
          switch (r.getR_no() % 100) {
            case 4:
              ioc.onePrint("[" + r.getR_no() + "⬜]\t");
              break;
            case 1:
              ioc.twoPrint("[" + r.getR_no() + "⬜]");
              break;
            default:
              ioc.onePrint("[" + r.getR_no() + "⬜]");
              break;
          }
        }
      }
    } else {
      for (RoomDto r : reverseRList) {
        switch (r.getR_no() % 100) {
          case 4:
            ioc.onePrint("[" + r.getR_no() + "⬜]\t");
            break;
          case 1:
            ioc.twoPrint("[" + r.getR_no() + "⬜]");
            break;
          default:
            ioc.onePrint("[" + r.getR_no() + "⬜]");
            break;
        }
      }
    }
    ioc.twoPrint("");
  }

  public int returnRoomNo() {
    ioc.twoPrint("▷상세정보를 확인 할 방의 번호를 입력해주세요.");
    int menu = ioc.inNum("입력>");
    ioc.twoPrint("---------------------------------------------------------------");

    if (menu == 0) {
      ioc.twoPrint("▷이전 메뉴로 돌아갑니다.");
    }
    return menu;
  }

  public int printEachRoom(List<RoomDto> rList, List<RoomTypeDto> tList, int checkNum) {
    ioc.twoPrint("0.이전 메뉴로 돌아가기");

    int menu = returnRoomNo();
    if (menu == 0) {
      checkNum = 0;
    }
    for (RoomDto r : rList) {
      if (r.getR_no() == menu) {
        checkNum = 1;
        for (RoomTypeDto t : tList) {
          if (t.getT_name().equals(r.getT_name())) {
            ioc.twoPrint("<" + r.getR_no() + "호실(" + r.getT_name() + ")> ＊" + r.getR_status());
            ioc.twoPrint("가격(1박) : " + t.getT_price());
            ioc.twoPrint("객실 크기 : " + t.getT_size());
            ioc.twoPrint("최대 인원 : " + t.getT_maxnum());
            ioc.twoPrint("침대 개수 : " + t.getT_bed());
            ioc.twoPrint("욕조 유무 : " + t.getT_tub());
            ioc.twoPrint("주차가능여부 : " + t.getT_parking());
            if (t.getT_specialnote() != null) {
              ioc.twoPrint("특이사항 : " + t.getT_specialnote());
            }
            ioc.twoPrint("---------------------------------------------------------------");
            break;
          }
        }
      }
    }
    if (checkNum == -1) {
      ioc.twoPrint("▷올바른 객실 번호를 입력해주세요.");
      ioc.twoPrint("---------------------------------------------------------------");
    }
    return checkNum;
  }

  public int loggedInMenu() {
    ioc.twoPrint("===============================================================");
    ioc.twoPrint("예약 관리 프로그램 메뉴");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("1. 정보 조회");
    ioc.twoPrint("2. 예약 추가 및 삭제");
    ioc.twoPrint("3. 예약 수정");
    return ioc.inNum("선택>");
  }
  //김수엽 end


  //기유라
  public int showmemu() {
    int memu = -1;
    ioc.twoPrint("▶관리자 계정 관리");
    ioc.twoPrint("1. 관리자 조회");
    ioc.twoPrint("2. 관리자 수정");
    ioc.twoPrint("3. 관리자 삭제");
    ioc.twoPrint("0. 이전 메뉴로 돌아가기");
    memu = ioc.inNum("선택>");

    return memu;
  }

  public void inputData(AdminDto aDto) {
    ioc.twoPrint("▶신규 관리자 등록");
    ioc.twoPrint("---------------------------------------------------------------");
    aDto.setA_id(ioc.inStr("ID : "));
    aDto.setA_pwd(ioc.inStr("PWD : "));
    aDto.setA_name(ioc.inStr("이름 : "));
  }

  public void outputlIST(List<AdminDto> dList) {
    ioc.twoPrint("▶관리자 조회");
    ioc.twoPrint("---------------------------------------------------------------");
    if (dList == null) {
      ioc.twoPrint("▷등록된 관리자 계정이 없습니다.");
      return;
    }
    for (AdminDto d : dList) {
      ioc.twoPrint("<id> : " + d.getA_id());
      ioc.twoPrint("<pwd> : " + d.getA_pwd());
      ioc.twoPrint("<name> : " + d.getA_name());
      ioc.twoPrint("---------------------------------------------------------------");
    }
  }

  public String searchId() {
    String id = ioc.inStr("▷검색할 아이디를 입력하세요 : ");
    return id;
  }

  public void output(AdminDto aDto) {
    ioc.twoPrint("▷검색 결과");
    ioc.twoPrint("---------------------------------------------------------------");
    if (aDto == null) {
      ioc.twoPrint("▷검색 결과가 존재하지 않습니다.");
      ioc.twoPrint("---------------------------------------------------------------");
      return;
    }
    ioc.twoPrint("ID : " + aDto.getA_id());
    ioc.twoPrint("PWD : " + aDto.getA_pwd());
    ioc.twoPrint("이름 : " + aDto.getA_name());

  }

  public int loginmenu() {
    int loginmenu = -1;
    ioc.twoPrint("===============================================================");
    ioc.twoPrint("예약 관리 프로그램(관리자)");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("1. 관리자 로그인");
    ioc.twoPrint("2. 신규 관리자 등록");
    ioc.twoPrint("0. 종료");
    loginmenu = ioc.inNum("선택>");

    return loginmenu;
  }

  public String inputlogin(String str) {
    String a = ioc.inStr(str);

    return a;
  }

  public AdminDto inputUpdata(AdminDto aDto) {
    ioc.twoPrint("▶관리자 계정 수정");
    ioc.twoPrint("---------------------------------------------------------------");

    String str = null;


    str = ioc.inStr("수정할 아이디를 입력해주세요 : ");
    if (!str.equals("")) {
      aDto.setA_id(str);
    }
    str = ioc.inStr("수정할 패스워드를 입력해주세요 : ");
    if (!str.equals("")) {
      aDto.setA_pwd(str);
    }
    str = ioc.inStr("수정할 이름을 입력해주세요 : ");
    if (!str.equals("")) {
      aDto.setA_name(str);
    }
    return aDto;
  }

  public int printAdminMenu() {
    ioc.twoPrint("===============================================================");
    ioc.twoPrint("예약 관리 프로그램 메뉴");
    ioc.twoPrint("---------------------------------------------------------------");
    ioc.twoPrint("1. 예약 관리");
    ioc.twoPrint("2. 관리자 계정 관리");
    ioc.twoPrint("0. 이전 메뉴로 돌아가기");
    return ioc.inNum("입력>");
  }
  //기유라 end


  //김인성
  public int showMenu() {
    int m = -1;

    ioc.twoPrint("===============================================================");
    ioc.twoPrint("▶예약 추가 및 삭제");
    ioc.twoPrint("---------------------------------------------------------------");

    ioc.twoPrint("1. 예약 입력");
    ioc.twoPrint("2. 예약 삭제");
    ioc.twoPrint("0. 이전 메뉴로 돌아가기");

    m = ioc.inNum("선택>");

    return m;
  }//showmenu end

  public void subTitle1(String str) {
    ioc.twoPrint("<<" + str + ">>");
    ioc.twoPrint("---------------------------------------------------------------");
  }

  public String inputRtype() {
    ioc.twoPrint("선택 : 일반 | 더블 | 특실 | 트리플");
    String inputRt = ioc.inStr("객실 등급 : ");

    return inputRt;
  }

  public String bookingId() {
    String id = null;
    ioc.twoPrint("===============================================================");
    ioc.twoPrint("▶예약 추가");
    ioc.twoPrint("---------------------------------------------------------------");

    id = ioc.inStr("ID : ");

    return id;
  }

  public int bookingR() {
    int num = 0;
    ioc.twoPrint("선택 <3층> 301, 302, 303, 304, 305, 306");
    ioc.twoPrint("선택 <2층> 201, 202, 203, 204, 205, 206");
    ioc.twoPrint("선택 <1층> 101, 102, 103, 104, 105, 106");
    num = ioc.inNum("객실 번호 :");

    return num;
  }

  public void bookingInput(BookingDto bDto, String bookingId, int bookingRnum, String roomType) {
    bDto.setM_id(bookingId);
    bDto.setR_no(bookingRnum);
    bDto.setT_name(roomType);
    bDto.setB_start(ioc.inStr("입실일 : "));
    bDto.setB_end(ioc.inStr("퇴실일 : "));
  }

  public String searchId1() {
    String id = null;
    ioc.twoPrint("▶예약 삭제");
    id = ioc.inStr("ID : ");

    return id;
  }

  public String delete() {
    String yn = ioc.inStr("▷예약 삭제하시겠습니까?(삭제/취소)"
      + "\n" + "선택> ");
    return yn;
  }

  public String searchCode(String str) {
    String id = null;
    subTitle1(str);
    id = ioc.inStr("ID : ");

    return id;
  }

  public void outData1(BookingDto data) {
    subTitle("▶예약 확인");

    if (data == null) {
      printMsg("예약 정보가 없습니다.");
      return;
    }

    ioc.twoPrint(data.searchbook());
  }
  //김인성end
}
