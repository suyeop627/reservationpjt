package com.dto;

import lombok.Data;

@Data
public class BookingDto {
    int b_no;
    String m_id;
    int r_no;
    String t_name;
    String b_start;
    String b_end;

    //강찬혁
    public String toString() {
        String str = "예약 번호 : " + b_no + "\n"
                + "유저 아이디 : " + m_id + "\n"
                + "방(호) : " + r_no + "\n"
                + "방 등급 : " + t_name + "\n"
                + "예약 시작일 : " + b_start + "\n"
                + "예약 종료일 : " + b_end;
        return str;
    }
    //강찬혁 end

    //김인성
    public String searchbook() {
        String str = "예약자 ID : " + m_id + "\n"
          + "방 번호 : " + r_no + "\n"
          + "방 타입 : " + t_name + "\n"
          + "예약 입실일 : " + b_start + "\n"
          + "예약 퇴실일 : " + b_end + "\n";
        return str;
    }
    //김인성 end
}
