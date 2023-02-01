package com.dto;

import lombok.Data;

@Data
public class RoomTypeDto {
    String t_name;
    int t_bed;
    String t_size;
    String t_tub;
    int t_price;
    String t_parking;
    int t_maxnum;
    String t_specialnote;
    //강찬혁
    public String toString() {
        String str = "방등급 : " + t_name + "\n"
                + "침대갯수 : " + t_bed + "개\n"
                + "방 : " + t_size + "\n"
                + "욕조(유/무) : " + t_name + "\n"
                + "가격 : " + t_price + "원\n"
                + "주차가능여부 : " + t_parking +"\n"
                + "최대인원수 : " + t_maxnum + "\n"
                + "특이사항 : " + t_specialnote + "\n";
        return str;
    }
    //강찬혁 end
}
