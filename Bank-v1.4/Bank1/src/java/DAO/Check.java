package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public abstract class Check {

  /*
    rs.getString
   */
  public String getString(ResultSet rs, String columnName) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getString(columnName);
    } catch (SQLException ex) {
      System.out.println("getString columnName method error: " + ex);
    }

    return "";
  }

  public String getString(ResultSet rs, int columnIndex) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getString(columnIndex);
    } catch (SQLException ex) {
      System.out.println("getString columnIndex method error: " + ex);
    }

    return "";
  }

  /**
   * If the value is SQL NULL, the value returned is 0
   *
   * @param rs
   * @param columnName
   * @return
   */
  public int getInt(ResultSet rs, String columnName) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getInt(columnName);
    } catch (SQLException ex) {
      System.out.println("getInt columnName method error: " + ex);
    }

    return 0;
  }

  /**
   * If the value is SQL NULL, the value returned is 0
   *
   * @param rs
   * @param columnIndex
   * @return
   */
  public int getInt(ResultSet rs, int columnIndex) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getInt(columnIndex);
    } catch (SQLException ex) {
      System.out.println("getInt columnIndex method error: " + ex);
    }

    return 0;
  }

  public Date getDate(ResultSet rs, String columnName) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getDate(columnName);
    } catch (SQLException ex) {
      System.out.println("getInt columnIndex method error: " + ex);
    }

    return new Date();
  }

  public Date getDate(ResultSet rs, int columnIndex) {
    if (rs == null) {
      throw new IllegalArgumentException("ResultSet required not null");
    }

    try {
      return rs.getDate(columnIndex);
    } catch (SQLException ex) {
      System.out.println("getInt columnIndex method error: " + ex);
    }

    return new Date();
  }

  /*
  *Close Statement
   */
  public void closeStatement(Statement st) {
    if (st != null) {
      try {
        st.close();
      } catch (SQLException ex) {
        System.out.println("Error close statement: " + ex);
      }
    }
  }

  /*
  *Close Result
   */
  public void closeResult(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ex) {
        System.out.println("Error close ResultSet: " + ex);
      }
    }
  }

  /*
    Close Connection
   */
  public void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ex) {
        System.out.println("closeConnection method error: " + ex);
      }
    }
  }

  public void closePre(PreparedStatement prs) {
    if (prs != null) {
      try {
        prs.close();
      } catch (SQLException ex) {
        System.out.println("closePreparedStatement method error: " + ex);
      }
    }
  }
}
