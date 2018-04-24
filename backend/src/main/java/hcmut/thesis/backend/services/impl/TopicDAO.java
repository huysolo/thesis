package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.services.ITopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TopicDAO implements ITopicDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Topic> getFilteredTopicList(Integer idFal, Integer idProf, Integer idSpec, Integer semNo) {
        String sql = "SELECT * FROM topic";

        List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql + filterParams( idFal,  idProf, idSpec, semNo));
        return getListTopicMapper(rs);
    }

    @Override
    public List<Topic> getListReviewTopicByProfId(Integer profId) {
        String sql = "SELECT * FROM topic WHERE topic.id_prof IN (SELECT r.id_prof FROM review r WHERE r.id_prof = ?)";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql, profId);
        List<Topic> lstTopic = new LinkedList<>();
        rs.forEach(row -> {
            Topic topic = TopicMapper(row);
            lstTopic.add(topic);
        });
        return lstTopic;
    }

    @Override
    public List<Topic> getListReviewTopicByProfIdAndSemesterNo(Integer profId, Integer semNo) {
        String sql = "SELECT * FROM topic WHERE topic.id_prof AND semester_no = ? IN (SELECT r.id_prof FROM review r WHERE r.id_prof = ?)";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql, profId, semNo);
        return getListTopicMapper(rs);
    }

    private List<Topic> getListTopicMapper(List<Map<String, Object>> rs){
        List<Topic> lstTopic = new LinkedList<>();
        rs.forEach(row -> {
            Topic topic = TopicMapper(row);
            lstTopic.add(topic);
        });
        return lstTopic;
    }
    private Topic TopicMapper(Map<String, Object> row) {
        return new Topic(
                (Integer) row.get("id_top"),
                (String) row.get("title"),
                (Integer) row.get("st_num_limit"),
                (String) row.get("sumary"),
                (Integer) row.get("id_prof"),
                row.get("score") != null ? (Integer) row.get("score") : 0,
                (Integer) row.get("semesterNo"),
                (Integer) row.get("idSpecialize")
        );
    }

    private String filterParams(Integer idFal, Integer idProf, Integer idSpec, Integer semNo){
        List<String> filters = new ArrayList<>();
        if (idProf != null) {
            filters.add("id_prof = " + idProf);
        } else if (idFal != null){
            filters.add(" id_specialize IN ( SELECT s.id_specialize FROM specialize s WHERE s.id_falcuty =" + idFal + " ) ");
        }
        if (idSpec != null) {
            filters.add(" id_specialize = "+ idSpec);
        }
        if (semNo != null) {
            filters.add(" semester_no = " + semNo);
        } else  {
            filters.add(" semester_no IN (select semester_no from semester where semester.apply_close_date < current_timestamp()) ");
        }
        StringBuilder sqlParams = new StringBuilder();
        filters.forEach(filter -> {
            if (!sqlParams.toString().isEmpty()){
                sqlParams.append(" AND ");
            }
            sqlParams.append(filter);
        });
        System.out.println(sqlParams);
        return sqlParams.toString().isEmpty() ? "" : " WHERE " + sqlParams.toString();
    }
}
