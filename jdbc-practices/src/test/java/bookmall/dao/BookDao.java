package bookmall.dao;

import java.sql.*;
import java.util.*;

import bookmall.ConnectionFactory;
import bookmall.vo.BookVo;

public class BookDao {
    public boolean insert(BookVo vo) {
        String sql = "INSERT INTO book(title, price, category_no) VALUES(?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vo.getTitle());
            ps.setInt(2, vo.getPrice());
            ps.setLong(3, vo.getCategoryNo());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) vo.setNo(rs.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteByNo(Long no) {
        String sql = "DELETE FROM book WHERE no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, no);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}