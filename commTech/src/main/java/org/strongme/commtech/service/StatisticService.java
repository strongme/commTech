package org.strongme.commtech.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.strongme.commtech.bean.StatisticBean;

@Service
public class StatisticService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<StatisticBean> census() {
		List<StatisticBean> result = null;
		String sql = "select avg(a.score),a.courseid,b.classid from studentcomm a,student b where a.studentid=b.studentid  group by a.courseid,b.classid";
		result = jdbcTemplate.query(sql, new RowMapper<StatisticBean>() {
			public StatisticBean mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				StatisticBean bean = new StatisticBean();
				bean.setCourseid(arg0.getString(2));
				bean.setClassid(arg0.getString(3));
				bean.setAvgScore(arg0.getDouble(1));
				return bean;
			}
		});
		return result;
	}

}
