package com.nfc.ryanjfahsel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*This code connects to the mysql database*/
public class DBConnection
{
    public Connection con = null;
    public Statement statement= null;
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String username = "admin",password = "admin";
    static String dbname = "jdbc:mysql://ryanjfahsel.com/muc";
    String sql="select * from Tools where tid=1";
    String dbtime;

    public  DBConnection(String user , String passwd){
        this.password = passwd;
        this.username= user;
    }

    public void Connect() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            //System.out.println ("Database connection established");
            //System.out.println("capturing from database");
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
                dbtime = rs.getString(5);
                System.out.println(dbtime);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
        }
    }

    public static void main( String args[] )
    {
        DBConnection conn=new DBConnection("admin","admin");
        conn.Connect();
    }
}

