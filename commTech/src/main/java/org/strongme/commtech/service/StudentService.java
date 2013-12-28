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
import org.strongme.commtech.bean.StudentBean;

@Service
public class StudentService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private LobHandler  lobHandler;
	
	public Integer save(StudentBean bean) {
		final StudentBean tmp = bean;
		int result = -1;
		String sql = "INSERT INTO student(studentid,studentname,studentpassword,classid) VALUES(?,?,?,?);";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				ps.setString(2, tmp.getStudentname());
				ps.setString(3, tmp.getStudentpassword());
				ps.setString(4, tmp.getClassid());
			}});
		return result;
	}
	
	public Integer update(StudentBean bean) {
		final StudentBean tmp = bean;
		int result = -1;
		String sql = "UPDATE student SET studentid= ?,studentname= ?,studentpassword= ?,classid= ? WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				ps.setString(2, tmp.getStudentname());
				ps.setString(3, tmp.getStudentpassword());
				ps.setString(4, tmp.getClassid());
			}});
		return result;
	}
	public StudentBean get(String studentid) {
		StudentBean result = new StudentBean();
		String sql = "SELECT id,studentid,studentname,studentpassword,classid FROM student  where studentid='"+studentid+"'";
		List<StudentBean> list = jdbcTemplate.query(sql, new RowMapper<StudentBean>() {
			public StudentBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				StudentBean result = new StudentBean();
				result.setId(arg0.getInt(1));
				result.setStudentid(arg0.getString(2));
				result.setStudentname(arg0.getString(3));
				result.setStudentpassword(arg0.getString(4));
				result.setClassid(arg0.getString(5));
				return result;
			}
		});
		if(list.size()>0)result = list.get(0);
		return result;
	}
	
	public boolean isExist(String id) {
		boolean result = false;
		String sql = "select count(1) from student where studentid='"+id+"'";
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			result = true;
		}
		return result;
	}

}
