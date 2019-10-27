package com.angelale;

import java.sql.*;

public class Main {
    //class constants. Database name, connection string, table name and columns name.
    public static final String DB_NAME = "testdatabase.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\tropi\\IdeaProjects\\testdatabase\\" + DB_NAME;

    public static final String TABLE_NAME = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args){

        try{

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            //conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_NAME);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (" + COLUMN_NAME +
                    " TEXT, " + COLUMN_PHONE + " INTEGER, "+ COLUMN_EMAIL +" TEXT)");

            insertcontact(statement,"Timb Buchalka", 75758474, "tim@buchalka.com");
            insertcontact(statement,"Karen Williams", 14312232, "karen@buchalka.com");
            insertcontact(statement,"Kim Couchi", 2827364, "tim@buchalka.com");

            statement.execute("UPDATE " + TABLE_NAME +" SET " + COLUMN_EMAIL +"= 'kim@buchalka.com' WHERE " + COLUMN_NAME + " = 'Kim Couchi'");
            statement.execute("UPDATE " + TABLE_NAME +" SET " + COLUMN_NAME +"= 'Tim Buchalka' WHERE " + COLUMN_EMAIL + " = 'tim@buchalka.com'");

            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (result.next()) System.out.println(result.getString(COLUMN_NAME) + " " +
                    result.getInt(COLUMN_PHONE) +" "+
                    result.getString(COLUMN_EMAIL));

            result.close();
            statement.close();
            conn.close();

        }catch(SQLException ex){
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }
    }

    public static boolean insertcontact(Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute(String.format("INSERT INTO %s(%s,%s,%s) VALUES('%s',%d,'%s')",TABLE_NAME,
                    COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL, name,phone,email));
        return false;
    }
}
