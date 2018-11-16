package servlets;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.*;
public class sqlmethods {
    //get mysql connection
    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://aa7x7sh3ue1pjf.cplt8fs2jqg6.us-east-1.rds.amazonaws.com:3306/csi418y"; //suppress ssl error
            String username = "root";
            String password = "cstianshi";

            //hide credential from public
            //String DB_Url = System.getProperty("JDBC_CONNECTION_STRING");
            //String DB_User = System.getProperty("JDBC_USER");
            //String DB_Password = System.getProperty("JDBC_PASSWORD");

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("connceted");
            return conn;
        } catch (Exception e) {
            System.out.println("hey");
        }
        return null;
    }

    //insert into "data" table data(acquiredDate,headline,symbol,symbolText)
    public static void insertTable(String headline, String symbol, String text) throws SQLException {
        // YYYY-MM-DD HH:MM:SS
        //aws default timezone UTC, -5h => EST
        Timestamp ts_when_accquired = new Timestamp(System.currentTimeMillis()-3600 * 5000);
        try {
            Connection dbconnection = getConnection();
            // the mysql insert statement
            String query = " insert into data (acquiredDate,headline,symbol,symbolText)"
                    + " values (?,?,?,?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = dbconnection.prepareStatement(query);
            preparedStmt.setTimestamp(1, ts_when_accquired);
            preparedStmt.setString(2, headline);
            preparedStmt.setString(3, symbol);
            preparedStmt.setString(4, text);
            // execute the preparedstatement
            preparedStmt.execute();
            preparedStmt.close();
            dbconnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: search sql
    @SuppressWarnings("finally")
    public static ArrayList<ArrayList<String>> searchTable(ArrayList<String> parameter, ArrayList<String> value) throws SQLException {
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        String selectSQL = "";
        if (parameter.get(0).equals("acquiredDate")){
            selectSQL = "SELECT * FROM data WHERE " + "DATE(" + parameter.get(0) + ")" + "= " + "\"" + value.get(0) + "\"";
        }
        else {
            selectSQL = "SELECT * FROM data WHERE " + parameter.get(0) + "= " + "\"" + value.get(0) + "\"";
        }
        int size = parameter.size();
        for (int i = 1; i < size; i++) {
            if (parameter.get(i).equals("acquiredDate")) {
                selectSQL = selectSQL + " OR " + "DATE("+ parameter.get(i) + ")" + "= " + "\"" + value.get(i) + "\"";

            } else {
                selectSQL = selectSQL + " OR " + parameter.get(i) + "= " + "\"" + value.get(i) + "\"";
            }
        }
        Connection dbConnection = getConnection();
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);
        try {
            while (rs.next()) {
                ArrayList<String> result = new ArrayList<String>();
                int id = rs.getInt("ID");
                result.add(String.valueOf(id));
                String Date = rs.getString("acquiredDate");
                result.add(Date);
                String headline = rs.getString("headline");
                result.add(headline);
                String symbol = rs.getString("symbol");
                result.add(symbol);
                String sContent = rs.getString("symbolText");
                result.add(sContent);
                results.add(result);
                System.out.println("ID : " + id + " AcquiredDate : " + Date
                        + " headline : " + headline
                        + " symbol : " + symbol
                        + " text : " + sContent );
                //String all = "ID: " + id + " "+ "acquiredDate: "+ Date + " " + "headline: " + headline + " "
                //        + "symbol: " + symbol + " " +"text: " + sContent;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            stmt.close();
            dbConnection.close();
            return results;
        }
    }
    public static boolean verifyUser(String entered_un, String entered_pw) throws SQLException {
        Connection dbConnection = null;
        Statement stmt = null;
        ResultSet rs = null;
        dbConnection = getConnection();
        boolean isUser = false;


        stmt = dbConnection.createStatement();
        String sql = "SELECT * FROM user WHERE username = \"" + entered_un + "\" AND password = \"" + entered_pw + "\"";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            System.out.println("authorized");
            isUser = true;
        }
        rs.close();
        stmt.close();
        dbConnection.close();
        return isUser;
    }

    public static void updatepw(String username, String entered_npw) throws SQLException {
        Connection dbConnection = null;
        dbConnection = getConnection();

        // create the java mysql update preparedstatement
        String query = "update user set password = ? where username = ?";
        PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
        preparedStmt.setString   (1, entered_npw);
        preparedStmt.setString(2, username);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();
        dbConnection.close();
    }
}


