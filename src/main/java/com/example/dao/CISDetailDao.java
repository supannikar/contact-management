package com.example.dao;

import com.example.model.CISDetailModel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CISDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CISDetailModel save(CISDetailModel model) {
        CISDetailModel result = null;

        if (model.getId() == null) {
            result = add(model);
        } else {
            result = update(model);
        }
        return result;
    }

    public CISDetailModel findById(Long id){
        return jdbcTemplate.queryForObject("SELECT id, name, email, phone, group_id, modifiedtime " +
                "FROM cisdetail WHERE id = ?", new CISDetailModelRowMapper(), id);
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM cisdetail WHERE id = ?", id);
    }

    public List<CISDetailModel> listAll(){
        return jdbcTemplate.query("SELECT id, name, email, phone, group_id, modifiedtime FROM cisdetail",
                new CISDetailModelRowMapper());
    }

    private CISDetailModel add(CISDetailModel model) {
        Long result = null;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new CISDetailStatementCreator(model),keyHolder);
        result = keyHolder.getKey().longValue();
        model.setId(result);
        return model;

    }

    private CISDetailModel update(CISDetailModel model) {

        jdbcTemplate.update("UPDATE cisdetail SET name = ?, email = ?, phone = ?, group_id = ?, modifiedtime = ?" +
                        " WHERE id = ?", model.getName(), model.getEmail(), model.getPhone(),
                model.getGroupId(), model.getModifiedTime().getMillis(), model.getId());
        return model;
    }

    private class CISDetailModelRowMapper implements RowMapper<CISDetailModel> {
        public CISDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            CISDetailModel cisDetailModel = new CISDetailModel();
            cisDetailModel.setId(rs.getLong("id"));
            cisDetailModel.setName(rs.getString("name"));
            cisDetailModel.setEmail(rs.getString("email"));
            cisDetailModel.setPhone(rs.getString("phone"));
            cisDetailModel.setGroupId(rs.getLong("group_id"));
            cisDetailModel.setModifiedTime(new DateTime(rs.getLong("modifiedtime")));

            return cisDetailModel;
        }
    }

    private static class CISDetailStatementCreator implements PreparedStatementCreator {
        private final CISDetailModel model;

        public CISDetailStatementCreator(CISDetailModel model) {
            this.model = model;
        }

        public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO cisdetail (name, email, phone, group_id, modifiedtime)" +
                            " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            ps.setString(parameterIndex++, model.getName());
            ps.setString(parameterIndex++, model.getEmail());
            ps.setString(parameterIndex++, model.getPhone());
            ps.setLong(parameterIndex++, model.getGroupId());
            ps.setLong(parameterIndex++, model.getModifiedTime().getMillis());
            return ps;
        }
    }
}
