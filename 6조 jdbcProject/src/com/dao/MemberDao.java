package com.dao;

import com.dto.MemberDto;

import java.sql.*;

public class MemberDao {
  public String drv = "com.mysql.cj.jdbc.Driver";
  public String url = "jdbc:mysql://localhost:3306/bookingdb";
  public String user = "BookingConnection";
  public String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;


  public MemberDao() {
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
  public String selectMemberId(String name, String phone) {
    String userid = null;

    String query = "SELECT * FROM member WHERE m_name= ? AND m_phone= ? ";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, name);
      pstmt.setString(2, phone);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        userid = rs.getString(1);
      }

    } catch (SQLException e) {
      userid = null;
    } finally {
      close();
    }
    return userid;
  }
  //강찬혁 end


  //김수엽
  public MemberDto selectGuestData(String[] namePhone) {
    MemberDto mdata = null;

    String query = "SELECT * FROM member " +
      "WHERE m_name = ? and m_phone = ?";
    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, namePhone[0]);
      pstmt.setString(2, namePhone[1]);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        mdata = new MemberDto();
        mdata.setM_id(rs.getString(1));
        mdata.setM_pwd(rs.getString(2));
        mdata.setM_name(rs.getString(3));
        mdata.setM_birth(rs.getString(4));
        mdata.setM_phone(rs.getString(5));
        mdata.setM_email(rs.getString(6));
      }
    } catch (SQLException e) {
      mdata = null;
    } finally {
      close();
    }
    return mdata;
  }
  //김수엽 end

}
