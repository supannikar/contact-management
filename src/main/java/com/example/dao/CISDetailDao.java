package com.example.dao;

import com.example.model.CISDetailModel;
import com.example.query.CISDetailQuery;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CISDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CISDetailDao.class);

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
        return jdbcTemplate.queryForObject("SELECT id, name, email, phone, group_id " +
                "FROM cisdetail WHERE id = ?", new CISDetailModelRowMapper(), id);
    }

    public List<CISDetailModel> queryCISDetail(CISDetailQuery query){
        List<Integer> paramTypes = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        boolean isForCount = false;
        String sql = composeSqlConditionForAdSearch(query, paramTypes, params, isForCount);

        int[] paramTypeArray = new int[paramTypes.size()];
        int index = 0;
        for(Integer eachType : paramTypes) {
            paramTypeArray[index] = eachType;
            index++;
        }
        Object[] paramArray = new Object[params.size()];
        paramArray = params.toArray(paramArray);

        // Execute the query
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, paramArray, paramTypeArray);

        List<CISDetailModel> cisDetailModels = new ArrayList<>();
        if(res == null || res.size() == 0){
            return cisDetailModels;
        }
        res.stream().forEach(row -> {
            CISDetailModel model = new CISDetailModel();
            model.setId((Long) row.get("id"));
            model.setName((String) row.get("name"));
            model.setPhone((String) row.get("phone"));
            model.setEmail((String) row.get("email"));
            model.setGroupId((Long) row.get("group_id"));
//            model.setModifiedTime((DateTime) row.get("modifiedtime"));
            cisDetailModels.add(model);
        });

        return cisDetailModels;
    }

    public int countAdObject(CISDetailQuery query) {
        List<Integer> paramTypes = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        boolean isForCount = true;
        String sql = composeSqlConditionForAdSearch(query, paramTypes, params, isForCount);

        int[] paramTypeArray = new int[paramTypes.size()];
        int index = 0;
        for(Integer eachType : paramTypes) {
            paramTypeArray[index] = eachType;
            index++;
        }
        Object[] paramArray = new Object[params.size()];
        paramArray = params.toArray(paramArray);

        return jdbcTemplate.queryForObject(sql, paramArray, paramTypeArray, Integer.class);
    }

    private String composeSqlConditionForAdSearch(CISDetailQuery query, List<Integer> paramTypes,
                                                  List<Object> params, boolean isForCount){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if(isForCount){
            sb.append(" COUNT(d.id) ");
        } else {
            sb.append("d.id, d.name, d.email, d.phone, " +
                    "d.group_id");
        }

        sb.append(" FROM cisdetail d");

        boolean hasCondition = false;
        StringBuilder conditionBuilder = new StringBuilder();
        if(StringUtils.isNoneBlank(query.getEmail())){
            if(hasCondition){
                conditionBuilder.append(" AND ");
            } else hasCondition = true;
            conditionBuilder.append(" d.email = ?");
            paramTypes.add(Types.VARCHAR);
            params.add(query.getEmail());
        }

        if(StringUtils.isNoneBlank(query.getName())){
            if(hasCondition){
                conditionBuilder.append(" AND ");
            } else hasCondition = true;
            conditionBuilder.append(" d.name = ?");
            paramTypes.add(Types.VARCHAR);
            params.add(query.getName());
        }

        if(StringUtils.isNoneBlank(query.getPhone())){
            if(hasCondition){
                conditionBuilder.append(" AND ");
            } else hasCondition = true;
            conditionBuilder.append(" d.phone = ?");
            paramTypes.add(Types.VARCHAR);
            params.add(query.getPhone());
        }

        if(hasCondition) {
            conditionBuilder.insert(0, " WHERE ");
        }

        if(!isForCount) {

            if(StringUtils.isNotBlank(query.getSortBy())) {
                conditionBuilder.append(" ORDER BY  ").append(query.getSortBy()).append(" asc ");
            } else {
                conditionBuilder.append(" ORDER BY d.name desc ");
            }
            conditionBuilder.append(" LIMIT ? OFFSET ? ");
            paramTypes.add(Types.INTEGER);
            params.add(query.getLimit());
            paramTypes.add(Types.INTEGER);
            params.add(query.getOffset());
        }

        String sqlCondition = conditionBuilder.toString();
        if(StringUtils.isNotBlank(sqlCondition)) {
            sb.append(sqlCondition);
        }
        logger.debug("SQL to search ads : " + sb.toString());

        return sb.toString();
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM cisdetail WHERE id = ?", id);
    }

    public List<CISDetailModel> listAll(){
        return jdbcTemplate.query("SELECT d.id, d.name, d.email, d.phone, d.group_id FROM cisdetail d",
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

        jdbcTemplate.update("UPDATE cisdetail SET name = ?, email = ?, phone = ?, group_id = ?" +
                        " WHERE id = ?", model.getName(), model.getEmail(), model.getPhone(),
                model.getGroupId(), model.getId());
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
//            cisDetailModel.setModifiedTime(new DateTime(rs.getLong("modifiedtime")));

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
                    "INSERT INTO cisdetail (name, email, phone, group_id)" +
                            " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            ps.setString(parameterIndex++, model.getName());
            ps.setString(parameterIndex++, model.getEmail());
            ps.setString(parameterIndex++, model.getPhone());
            ps.setLong(parameterIndex, model.getGroupId());
//            ps.setLong(parameterIndex, model.getModifiedTime().getMillis());
            return ps;
        }
    }
}
