package com.dao;

import com.dto.BookingDto;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
  public String drv = "com.mysql.cj.jdbc.Driver";
  public String url = "jdbc:mysql://localhost:3306/bookingdb";
  public String user = "BookingConnection";
  public String pass = "0000";
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public BookingDao() {
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
  public BookingDto getBooking(String userId) {
    BookingDto bDto = null;

    String query = "SELECT * FROM booking WHERE m_id=?";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, userId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        bDto = new BookingDto();
        bDto.setB_no(rs.getInt(1));
        bDto.setM_id(rs.getString(2));
        bDto.setR_no(rs.getInt(3));
        bDto.setT_name(rs.getString(4));
        bDto.setB_start(rs.getString(5));
        bDto.setB_end(rs.getString(6));
      }
    } catch (SQLException e) {
      bDto = null;
    } finally {
      close();
    }
    return bDto;
  }

  public int updateBooking(BookingDto bDto) {
    int result = 0;

    String query = "UPDATE booking SET r_no = ?, t_name = ?, b_start = ?, b_end = ? "
      + "WHERE b_no=?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, bDto.getR_no());
      pstmt.setString(2, bDto.getT_name());
      pstmt.setDate(3, Date.valueOf(bDto.getB_start()));
      pstmt.setDate(4, Date.valueOf(bDto.getB_end()));
      pstmt.setInt(5, bDto.getB_no());

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
  public List<BookingDto> selectGuestData(String[] namePhone) {
    List<BookingDto> gList = null;

    String query = "select * from booking right join member " +
      "on booking.m_id = member.m_id " +
      "where member.m_name = ? and member.m_phone = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, namePhone[0]);
      pstmt.setString(2, namePhone[1]);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (gList == null) {
          gList = new ArrayList<>();
        }
        BookingDto gData = new BookingDto();
        gData.setB_no(rs.getInt(1));
        gData.setM_id(rs.getString(2));
        gData.setR_no(rs.getInt(3));
        gData.setT_name(rs.getString(4));
        gData.setB_start(rs.getString(5));
        gData.setB_end(rs.getString(6));


        gList.add(gData);
      }

    } catch (SQLException e) {
      gList = null;

    } finally {
      close();
    }
    return gList;
  }

  public List<BookingDto> selectRBList() {
    List<BookingDto> bList = null;
    String query = "select * from room r "
      + "right join booking b "
      + "on r.r_no = b.r_no;";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (bList == null) {
          bList = new ArrayList<>();
        }
        BookingDto bData = new BookingDto();
        if (rs.getString(4) != null) {
          bData.setB_no(rs.getInt("b_no"));
          bData.setM_id(rs.getString("m_id"));
          bData.setR_no(rs.getInt("r_no"));
          bData.setB_start(rs.getString("b_start"));
          bData.setB_end(rs.getString("b_end"));
        }
        bList.add(bData);
      }
    } catch (SQLException e) {
      bList = null;
    } finally {
      close();
    }
    return bList;
  }

  public List<BookingDto> selectBList() {
    List<BookingDto> bList = null;
    String query = "select * from booking ";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (bList == null) {
          bList = new ArrayList<>();
        }
        BookingDto bData = new BookingDto();
        if (rs.getString(4) != null) {
          bData.setB_no(rs.getInt("b_no"));
          bData.setM_id(rs.getString("m_id"));
          bData.setR_no(rs.getInt("r_no"));
          bData.setB_start(rs.getString("b_start"));
          bData.setB_end(rs.getString("b_end"));
        }
        bList.add(bData);
      }
    } catch (SQLException e) {
      bList = null;
    } finally {
      close();
    }
    return bList;
  }
  //김수엽 end



  //김인성
  public String selectId(String bookingId) {
    String BookId = null;

    String query = "SELECT * FROM booking WHERE m_id = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, bookingId);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        BookId = rs.getString(1);
      }

    } catch (SQLException e) {
      BookId = null;
    } finally {
      close();
    }

    return BookId;

  }

  public int insertData1(BookingDto bDto) {
    int result = 0;

    String query = "INSERT INTO booking VALUES "
      + "(null, ?, ?, ?, ?, ?)";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, bDto.getM_id());
      pstmt.setInt(2, bDto.getR_no());
      pstmt.setString(3, bDto.getT_name());
      pstmt.setDate(4, Date.valueOf(bDto.getB_start()));
      pstmt.setDate(5, Date.valueOf(bDto.getB_end()));

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      result = 0;
    }
    return result;
  }

  public int deleteData(String id) {
    int result = 0;

    String query = "DELETE FROM booking WHERE m_id = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, id);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      result = 0;
    } finally {
      close();
    }
    return result;
  }

  public BookingDto selectData(String id) {
    BookingDto data = null;

    String query = "SELECT * FROM booking WHERE m_id = ?";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        data = new BookingDto();
        data.setM_id(rs.getString("m_id"));
        data.setR_no(rs.getInt(3));
        data.setT_name(rs.getString("t_name"));
        data.setB_start(rs.getString(5));
        data.setB_end(rs.getString(6));
      }

    } catch (SQLException e) {
      //e.printStackTrace();
    } finally {
      close();
    }


    return data;
  }
  //김인성 end

}
