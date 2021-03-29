/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DB {
    private static final String JDBC_DRIVER
          = "com.mysql.jdbc.Driver";
  private static final String DATABASE_PATH
          = "jdbc:mysql://localhost/bank1";

  public static Connection getConnection() {
    Connection conn = null;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DATABASE_PATH,
              "root", "123456");
    } catch (ClassNotFoundException | SQLException ex) {
      System.out.println("Eror: "
              + ex.toString());
    }
    return conn;
  }
}
