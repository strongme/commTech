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
import org.strongme.commtech.bean.ClassBean;

@Service
public class ClassService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private LobHandler  lobHandler;
	
	public Integer save(ClassBean bean) {
		final ClassBean tmp = bean;
		int result = -1;
		String sql = "INSERT INTO class(classid,classname) VALUES(?,?);";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getClassid());
				ps.setString(2, tmp.getClassname());
			}});
		return result;
	}
	
	public Integer update(ClassBean bean) {
		final ClassBean tmp = bean;
		int result = -1;
		String sql = "UPDATE class SET classid= ?,classname= ? WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getClassid());
				ps.setString(2, tmp.getClassname());
			}});
		return result;
	}
	public List<ClassBean> get() {
		String sql = "SELECT id,classid,classname FROM class  order by id";
		List<ClassBean> result = jdbcTemplate.query(sql, new RowMapper<ClassBean>() {
			public ClassBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				ClassBean result = new ClassBean();
				result.setId(arg0.getInt(1));
				result.setClassid(arg0.getString(2));
				result.setClassname(arg0.getString(3));
				return result;
			}
		});
		return result;
	}
	
	public boolean isExist(String id) {
		boolean result = false;
		String sql = "select count(1) from class where classid='"+id+"'";
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			result = true;
		}
		return result;
	}

}
