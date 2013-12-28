package org.strongme.commtech.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.strongme.commtech.bean.CourseBean;

@Service
public class CourseService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private LobHandler  lobHandler;
	
	public Integer save(CourseBean bean) {
		final CourseBean tmp = bean;
		int result = -1;
		String sql = "INSERT INTO course(courseid,coursename) VALUES(?,?);";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getCourseid());
				ps.setString(2, tmp.getCoursename());
				
			}});
		return result;
	}
	
	public Integer update(CourseBean bean) {
		final CourseBean tmp = bean;
		int result = -1;
		String sql = "UPDATE course SET courseid= ?,coursename= ? WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getCourseid());
				ps.setString(2, tmp.getCoursename());
			}});
		return result;
	}
	public CourseBean get(String courseid) {
		CourseBean result = new CourseBean();
		String sql = "SELECT id,courseid,coursename FROM course where id='"+courseid+"'";
		List<CourseBean> list = jdbcTemplate.query(sql, new RowMapper<CourseBean>() {
			public CourseBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				CourseBean result = new CourseBean();
				result.setId(arg0.getInt(1));
				result.setCourseid(arg0.getString(2));
				result.setCoursename(arg0.getString(3));
				return result;
			}
		});
		if(list.size()>0)result = list.get(0);
		return result;
	}
	
	public List<CourseBean> getAll() {
		String sql = "SELECT id,courseid,coursename FROM course order by courseid";
		List<CourseBean> result = jdbcTemplate.query(sql, new RowMapper<CourseBean>() {
			public CourseBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				CourseBean result = new CourseBean();
				result.setId(arg0.getInt(1));
				result.setCourseid(arg0.getString(2));
				result.setCoursename(arg0.getString(3));
				return result;
			}
		});
		return result;
	}
	
	
	public boolean isExist(String id) {
		boolean result = false;
		String sql = "select count(1) from course where courseid='"+id+"'";
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			result = true;
		}
		return result;
	}

}
