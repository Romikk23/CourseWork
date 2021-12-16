package database;

import music.Track;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Database {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static Connection Database() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        return conn;
    }
    public static List<Track> ReadDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        List<Track> tracklist = new ArrayList();
        resSet = statmt.executeQuery("SELECT * FROM records");

        while(resSet.next())
        {
            String  name = resSet.getString("name");
            Duration duration = Duration.ofSeconds(resSet.getInt("duration"));
            String  style = resSet.getString("style");
            tracklist.add(new Track(name, duration, style));
        }

        return tracklist;
    }
    public static void AddDB(List<Track> tracklist) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO records(name,duration,style) VALUES(?,?,?)";
        for(Track track:tracklist){
            try (Connection conn = Database();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, track.getName());
                pstmt.setLong(2, track.getDuration().toSeconds());
                pstmt.setString(3, track.getStyle());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();
    }

}
