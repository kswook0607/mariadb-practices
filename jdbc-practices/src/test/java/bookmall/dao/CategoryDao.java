package bookmall.dao;

import java.sql.*;
import java.util.*;

import bookmall.ConnectionFactory;
import bookmall.vo.CategoryVo;

public class CategoryDao {
    public boolean insert(CategoryVo vo) {
        String sql = "INSERT INTO category(name) VALUES(?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vo.getName());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) vo.setNo(rs.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> list = new ArrayList<>();
        String sql = "SELECT no,name FROM category ORDER BY no";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CategoryVo vo = new CategoryVo();
                vo.setNo(rs.getLong("no"));
                vo.setName(rs.getString("name"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteByNo(Long no) {
        String sql = "DELETE FROM category WHERE no=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, no);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}