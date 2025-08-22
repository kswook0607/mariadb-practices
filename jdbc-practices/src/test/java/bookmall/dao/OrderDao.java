package bookmall.dao;

import java.sql.*;
import java.util.*;

import bookmall.ConnectionFactory;
import bookmall.vo.OrderVo;
import bookmall.vo.OrderBookVo;

public class OrderDao {
    public boolean insert(OrderVo vo) {
        String sql = "INSERT INTO orders(user_no, number, payment, shipping, status) VALUES(?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, vo.getUserNo());
            ps.setString(2, vo.getNumber());
            ps.setInt(3, vo.getPayment());
            ps.setString(4, vo.getShipping());
            ps.setString(5, vo.getStatus());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) vo.setNo(rs.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean insertBook(OrderBookVo vo) {
        String sql = "INSERT INTO order_book(order_no, book_no, quantity, price) VALUES(?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, vo.getOrderNo());
            ps.setLong(2, vo.getBookNo());
            ps.setInt(3, vo.getQuantity());
            ps.setInt(4, vo.getPrice());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public OrderVo findByNoAndUserNo(Long no, Long userNo) {
        String sql = "SELECT no,user_no,number,payment,shipping,status FROM orders WHERE no=? AND user_no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, no);
            ps.setLong(2, userNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrderVo vo = new OrderVo();
                    vo.setNo(rs.getLong("no"));
                    vo.setUserNo(rs.getLong("user_no"));
                    vo.setNumber(rs.getString("number"));
                    vo.setPayment(rs.getInt("payment"));
                    vo.setShipping(rs.getString("shipping"));
                    vo.setStatus(rs.getString("status"));
                    return vo;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
        List<OrderBookVo> list = new ArrayList<>();
        String sql = "SELECT ob.order_no, ob.book_no, ob.quantity, ob.price, b.title " +
                     "FROM order_book ob " +
                     "JOIN orders o ON o.no = ob.order_no " +
                     "JOIN book b ON b.no = ob.book_no " +
                     "WHERE ob.order_no=? AND o.user_no=? ORDER BY ob.book_no ASC";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderNo);
            ps.setLong(2, userNo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderBookVo vo = new OrderBookVo();
                    vo.setOrderNo(rs.getLong("order_no"));
                    vo.setBookNo(rs.getLong("book_no"));
                    vo.setQuantity(rs.getInt("quantity"));
                    vo.setPrice(rs.getInt("price"));
                    vo.setBookTitle(rs.getString("title"));
                    list.add(vo);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteBooksByNo(Long orderNo) {
        String sql = "DELETE FROM order_book WHERE order_no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderNo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteByNo(Long orderNo) {
        String sql = "DELETE FROM orders WHERE no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderNo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}