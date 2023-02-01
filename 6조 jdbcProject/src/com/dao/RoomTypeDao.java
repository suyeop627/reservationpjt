package com.dao;

import com.dto.RoomTypeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao {
  public String drv = "com.mysql.cj.jdbc.Driver";
  public String url = "jdbc:mysql://localhost:3306/bookingdb";
  public String user = "BookingConnection";
  public String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public RoomTypeDao() {
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {

    }
  }

  private void close() {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {

    }
  }

  //강찬혁
  public RoomTypeDto getRoomType(String roomType) {
    RoomTypeDto rDto = null;

    String query = "SELECT * FROM roomtype WHERE t_name = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, roomType);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        rDto = new RoomTypeDto();
        rDto.setT_name(rs.getString(1));
        rDto.setT_bed(rs.getInt(2));
        rDto.setT_size(rs.getString(3));
        rDto.setT_tub(rs.getString(4));
        rDto.setT_price(rs.getInt(5));
        rDto.setT_parking(rs.getString(6));
        rDto.setT_maxnum(rs.getInt(7));
        rDto.setT_specialnote(rs.getString(8));
      }
    } catch (SQLException e) {
      rDto = null;
    } finally {
      close();
    }
    return rDto;
  }

  public int updateRoomType(RoomTypeDto rDto) {
    int result = 0;
    String query = "UPDATE roomtype SET t_price = ?, t_specialnote = ? "
      + "WHERE t_name = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, rDto.getT_price());
      pstmt.setString(2, rDto.getT_specialnote());
      pstmt.setString(3, rDto.getT_name());

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      result = 0;
    } finally {
      close();
    }

    return result;
  }
  //강찬혁 end

  //김수엽
  public List<RoomTypeDto> selectRTList() {
    List<RoomTypeDto> tList = null;
    String query = "SELECT * FROM roomtype ";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (tList == null) {
          tList = new ArrayList<>();
        }
        RoomTypeDto tData = new RoomTypeDto();
        if (rs.getString(4) != null) {
          tData.setT_name(rs.getString(1));
          tData.setT_bed(rs.getInt(2));
          tData.setT_size(rs.getString(3));
          tData.setT_tub(rs.getString(4));
          tData.setT_price(rs.getInt(5));
          tData.setT_parking(rs.getString(6));
          tData.setT_maxnum(rs.getInt(7));
          tData.setT_specialnote(rs.getString(8));
        }
        tList.add(tData);
      }
    } catch (SQLException e) {
      tList = null;

    } finally {
      close();
    }
    return tList;
  }
  //김수엽 end

  //김인성
  public String selectRtype(String rType) {
    String result = null;

    String query = "SELECT * FROM roomtype WHERE t_name= ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, rType);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        result = rs.getString(1);
      }

    } catch (SQLException e) {
      result = null;
    }

    return result;
  }
  //김인성 end
}
