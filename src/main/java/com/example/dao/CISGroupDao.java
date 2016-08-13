package com.example.dao;

import com.example.model.CISGroupModel;
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
public class CISGroupDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CISGroupModel save(CISGroupModel model) {
        CISGroupModel result = null;

        if (model.getId() == null) {
            result = add(model);
        } else {
            result = update(model);
        }
        return result;
    }

    public CISGroupModel findById(Long id){
        return jdbcTemplate.queryForObject("SELECT id, name, modifiedtime " +
                "FROM cisgroup WHERE id = ?", new CISGroupModelRowMapper(), id);
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM cisgroup WHERE id = ?", id);
    }

    public List<CISGroupModel> listAll(){
        return jdbcTemplate.query("SELECT id, name, modifiedtime  FROM cisgroup",
                new CISGroupModelRowMapper());
    }

    private CISGroupModel add(CISGroupModel model) {
        Long result = null;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new CISGroupStatementCreator(model),keyHolder);
        result = keyHolder.getKey().longValue();
        model.setId(result);
        return model;

    }

    private CISGroupModel update(CISGroupModel model) {

        jdbcTemplate.update("UPDATE cisgroup SET name = ?, modifiedtime = ?" +
                " WHERE id = ?", model.getName(), model.getModifiedTime().getMillis(),
                model.getId());
        return model;
    }


    private class CISGroupModelRowMapper implements RowMapper<CISGroupModel> {
        public CISGroupModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            CISGroupModel cisGroupModel = new CISGroupModel();
            cisGroupModel.setId(rs.getLong("id"));
            cisGroupModel.setName(rs.getString("name"));
            cisGroupModel.setModifiedTime(new DateTime(rs.getLong("modifiedtime")));

            return cisGroupModel;
        }
    }

    private static class CISGroupStatementCreator implements PreparedStatementCreator {
        private final CISGroupModel model;

        public CISGroupStatementCreator(CISGroupModel model) {
            this.model = model;
        }

        public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO cisgroup (name, modifiedtime)" +
                    " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            ps.setString(parameterIndex++, model.getName());
            ps.setLong(parameterIndex, model.getModifiedTime().getMillis());
            return ps;
        }
    }
}
