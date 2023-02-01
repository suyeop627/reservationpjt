package com.dao;

import com.dto.RoomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
  public String drv = "com.mysql.cj.jdbc.Driver";
  public String url = "jdbc:mysql://localhost:3306/bookingdb";
  public String user = "BookingConnection";
  public String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public RoomDao() {
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 로드 실패");
    }
  }

  public void close() {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
    }
  }

  //김수엽
  public List<RoomDto> selectRList() {
    List<RoomDto> rList = null;
    String query = "select r.r_no, r.t_name,r.r_status, m.m_id, "
      + "m.m_name, m.m_phone, b.b_start, b.b_end "
      + "from room r "
      + "left join booking b "
      + "on b.r_no = r.r_no "
      + "left join member m "
      + "on b.m_id = m.m_id";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (rList == null) {
          rList = new ArrayList<>();
        }
        RoomDto rData = new RoomDto();
        if (rs.getString(4) != null) {
          rData.setR_no(rs.getInt(1));
          rData.setT_name(rs.getString(2));
          rData.setR_status(rs.getString(3) + "\n"
            + "예약자 정보 : " + rs.getString(5) + "(" + rs.getString(4) + ","
            + rs.getString(6) + ")\n입실예정일 : " + rs.getString(7) + " \n퇴실예정일 : " + rs.getString(8));
        } else {
          rData.setR_no(rs.getInt(1));
          rData.setT_name(rs.getString(2));
          rData.setR_status(rs.getString(3));
        }
        rList.add(rData);
      }
    } catch (SQLException e) {
      rList = null;
    } finally {
      close();
    }
    return rList;
  }
  //김수엽 end


  //김인성
  public String selectBr(int rNum) {
    String result = null;

    String query = "SELECT r_status FROM room WHERE r_no= ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, rNum);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        result = rs.getString(1);
      }
    } catch (SQLException e) {
      result = null;
    } finally {
      close();
    }


    return result;
  }
  //김인성 end
}
