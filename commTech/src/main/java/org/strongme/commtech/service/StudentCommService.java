package org.strongme.commtech.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.strongme.commtech.bean.StudentCommBean;

@Service
public class StudentCommService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private LobHandler  lobHandler;
	
	public Integer save(StudentCommBean bean) {
		final StudentCommBean tmp = bean;
		int result = -1;
		String sql = "INSERT INTO studentcomm(studentid,courseid,score) VALUES(?,?,?);";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				ps.setString(2, tmp.getCourseid());
				ps.setInt(3, tmp.getScore());
			}});
		return result;
	}
	
	public Boolean save(List<StudentCommBean> beans) {
		List<String> sqls = new ArrayList<String>();
		for(StudentCommBean bean : beans) {
			sqls.add("INSERT INTO studentcomm(studentid,courseid,score) VALUES('"+bean.getStudentid()+"','"+bean.getCourseid()+"',"+bean.getScore()+");");
		}
		String[] sqlArr = new String[sqls.size()];
		for(int i=0;i<sqls.size();i++) {
			sqlArr[i] = sqls.get(i);
		}
		int[] results = jdbcTemplate.batchUpdate(sqlArr);
		boolean result = true;
		for(int i : results) {
			if(i==-1) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public Integer update(StudentCommBean bean) {
		final StudentCommBean tmp = bean;
		int result = -1;
		String sql = "UPDATE studentcomm SET studentid= ?,courseid= ?,score= ? WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				ps.setString(2, tmp.getCourseid());
				ps.setInt(3, tmp.getScore());
			}});
		return result;
	}
	
	public Boolean ifComm(String studentid) {
		Boolean result = false;
		String sql = "select count(1) from studentcomm where studentid='"+studentid+"'";
		int c = jdbcTemplate.queryForInt(sql);
		if(c>0)result = true;
		return result;
	}
	
	public List<StudentCommBean> get(String studentid) {
		List<StudentCommBean> result = new ArrayList<StudentCommBean>();
		String sql = "select id,studentid,courseid,score from studentcomm where studentid='"+studentid+"'";
		result = jdbcTemplate.query(sql, new RowMapper<StudentCommBean>() {
			public StudentCommBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				StudentCommBean bean = new StudentCommBean();
				bean.setId(arg0.getInt(1));
				bean.setStudentid(arg0.getString(2));
				bean.setCourseid(arg0.getString(3));
				bean.setScore(arg0.getInt(4));
				return bean;
			}
		});
		return result;
	}

}
