package com.dao;

import com.dto.AdminDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
  public String drv = "com.mysql.cj.jdbc.Driver";
  public String url = "jdbc:mysql://localhost:3306/bookingdb";
  public String user = "BookingConnection";
  public String pwd = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public AdminDao() {
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {
      System.out.println("로그실패");
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


  //기유라
  public int insertData(AdminDto aDto) {
    int result = 0;

    String query = "INSERT INTO admin VALUE (?, ?, ?)";

    try {
      conn = DriverManager.getConnection(url, user, pwd);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, aDto.getA_id());
      pstmt.setString(2, aDto.getA_pwd());
      pstmt.setString(3, aDto.getA_name());
      result = pstmt.executeUpdate(); // 성공하면 1을 주고 실패하면 0을 준다
    } catch (SQLException e) {
      result = 0;
    }
    return result;

  }

  public List<AdminDto> selectList() {
    List<AdminDto> aList = null;
    String query = "SELECT * FROM admin";

    try {
      conn = DriverManager.getConnection(url, user, pwd);
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        if (aList == null) {
          aList = new ArrayList<>();
        }
        AdminDto aDto = new AdminDto();
        aDto.setA_id(rs.getString(1));
        aDto.setA_pwd(rs.getString(2));
        aDto.setA_name(rs.getString(3));
        aList.add(aDto);
      }
    } catch (SQLException e) {
      aList = null;
    } finally {
      close();
    }
    return aList;
  }

  public AdminDto selectInfo(String userId) {
    AdminDto aDto = null;
    String query = "SELECT * FROM admin WHERE a_id = ?";

    try {
      conn = DriverManager.getConnection(url, user, pwd);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, userId);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        aDto = new AdminDto();
        aDto.setA_id(rs.getString(1));
        aDto.setA_pwd(rs.getString(2));
        aDto.setA_name(rs.getString(3));
      }

    } catch (SQLException e) {
      aDto = null;
    } finally {
      close();
    }
    return aDto;
  }

  public int updateData(AdminDto toUpdateADto, String id) {
    int result = 0;

    String query = "UPDATE admin " + "SET a_id=?, a_pwd=?,a_name=? WHERE a_id = ?";

    try {
      conn = DriverManager.getConnection(url, user, pwd);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, toUpdateADto.getA_id());
      pstmt.setString(2, toUpdateADto.getA_pwd());
      pstmt.setString(3, toUpdateADto.getA_name());
      pstmt.setString(4, id);

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      result = 0;
    } finally {
      close();
    }
    return result;

  }

  public int deleteData(String userId) {
    int result = 0;

    try {
      conn = DriverManager.getConnection(url, user, pwd);
      String query = "DELETE FROM admin WHERE a_id = ?";
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, userId);
      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      result = 0;
    } finally {
      close();
    }
    return result;
  }
  //기유라 end
}
