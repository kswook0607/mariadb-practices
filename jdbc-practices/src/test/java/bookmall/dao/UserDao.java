package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bookmall.ConnectionFactory;
import bookmall.vo.UserVo;

public class UserDao {

    public boolean insert(UserVo vo) {
        String sql = "INSERT INTO user(name,email,password,phone) VALUES(?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vo.getName());
            ps.setString(2, vo.getEmail());
            ps.setString(3, vo.getPassword());
            ps.setString(4, vo.getPhone());
            int count = ps.executeUpdate();
            if (count == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) vo.setNo(rs.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<UserVo> findAll() {
        List<UserVo> list = new ArrayList<>();
        String sql = "SELECT no,name,email,password,phone FROM user ORDER BY no";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UserVo vo = new UserVo();
                vo.setNo(rs.getLong("no"));
                vo.setName(rs.getString("name"));
                vo.setEmail(rs.getString("email"));
                vo.setPassword(rs.getString("password"));
                vo.setPhone(rs.getString("phone"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteByNo(Long no) {
        String sql = "DELETE FROM user WHERE no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, no);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}