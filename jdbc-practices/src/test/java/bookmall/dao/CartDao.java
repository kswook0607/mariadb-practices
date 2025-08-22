package bookmall.dao;

import java.sql.*;
import java.util.*;

import bookmall.ConnectionFactory;
import bookmall.vo.CartVo;

public class CartDao {
    public boolean insert(CartVo vo) {
        String sql = "INSERT INTO cart(user_no, book_no, quantity) VALUES(?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, vo.getUserNo());
            ps.setLong(2, vo.getBookNo());
            ps.setInt(3, vo.getQuantity());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<CartVo> findByUserNo(Long userNo) {
        List<CartVo> list = new ArrayList<>();
        String sql = "SELECT c.user_no, c.book_no, c.quantity, b.title " +
                     "FROM cart c JOIN book b ON c.book_no = b.no " +
                     "WHERE c.user_no=? ORDER BY c.book_no ASC";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userNo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartVo vo = new CartVo();
                    vo.setUserNo(rs.getLong("user_no"));
                    vo.setBookNo(rs.getLong("book_no"));
                    vo.setQuantity(rs.getInt("quantity"));
                    vo.setBookTitle(rs.getString("title"));
                    list.add(vo);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
        String sql = "DELETE FROM cart WHERE user_no=? AND book_no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userNo);
            ps.setLong(2, bookNo);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}