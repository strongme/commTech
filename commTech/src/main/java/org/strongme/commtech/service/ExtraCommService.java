package org.strongme.commtech.service;

import java.io.UnsupportedEncodingException;
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
import org.strongme.commtech.bean.ExtraCommBean;

@Service
public class ExtraCommService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private LobHandler  lobHandler;
	
	public Integer save(ExtraCommBean bean) {
		final ExtraCommBean tmp = bean;
		int result = -1;
		String sql = "INSERT INTO extracomm(studentid,comment) VALUES(?,?);";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				try {
					lc.setBlobAsBytes(ps, 2, tmp.getComment().getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}});
		return result;
	}
	
	public Integer update(ExtraCommBean bean) {
		final ExtraCommBean tmp = bean;
		int result = -1;
		String sql = "UPDATE extracomm SET studentid= ?,comment= ? WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lc)
					throws SQLException, DataAccessException {
				ps.setString(1, tmp.getStudentid());
				try {
					lc.setBlobAsBytes(ps, 2, tmp.getComment().getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}});
		return result;
	}
	
	public List<ExtraCommBean> getAll(int startpage,int pagecount) {
		String sql = "select a.id, bb.ccname, a.comment from extracomm a inner join (select b.studentid bsid, c.classname ccname from student b inner join class c ON b.classid = c.classid) bb ON a.studentid = bb.bsid and a.comment != '' order by bb.ccname  limit "+(startpage-1)*pagecount+","+pagecount;
//		String sql = "select a.id, bb.ccname, a.comment from extracomm a inner join (select b.studentid bsid, c.classname ccname from student b inner join class c ON b.classid = c.classid) bb ON a.studentid = bb.bsid and a.comment != '' order by bb.ccname";
		List<ExtraCommBean> result = jdbcTemplate.query(sql, new RowMapper<ExtraCommBean>() {
			public ExtraCommBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				ExtraCommBean result = new ExtraCommBean();
				result.setId(arg0.getInt(1));
				result.setClassname(arg0.getString(2));
//				result.setStudentid(arg0.getString(3));
//				result.setStudentname(arg0.getString(4));
				result.setComment(arg0.getString(3));
				return result;
			}
		});
		return result;
	}
	public List<ExtraCommBean> getAll() {
		String sql = "select a.id, bb.ccname, a.comment from extracomm a inner join (select b.studentid bsid, c.classname ccname from student b inner join class c ON b.classid = c.classid) bb ON a.studentid = bb.bsid and a.comment != '' order by bb.ccname";
		List<ExtraCommBean> result = jdbcTemplate.query(sql, new RowMapper<ExtraCommBean>() {
			public ExtraCommBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				ExtraCommBean result = new ExtraCommBean();
				result.setId(arg0.getInt(1));
				result.setClassname(arg0.getString(2));
//				result.setStudentid(arg0.getString(3));
//				result.setStudentname(arg0.getString(4));
				result.setComment(arg0.getString(3));
				return result;
			}
		});
		return result;
	}
	
	public long countAll() {
		long result = 0;
		String sql = "select count(a.id) from extracomm a inner join (select b.studentid bsid, c.classname ccname from student b inner join class c ON b.classid = c.classid) bb ON a.studentid = bb.bsid and a.comment != '' order by bb.ccname";
		result = jdbcTemplate.queryForLong(sql);
		return result;
	}
	
	public boolean isExist(String id) {
		boolean result = false;
		String sql = "select count(1) from extracomm where studentid='"+id+"'";
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			result = true;
		}
		return result;
	}

}
