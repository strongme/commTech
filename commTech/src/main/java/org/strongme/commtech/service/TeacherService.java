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
import org.springframework.stereotype.Service;
import org.strongme.commtech.bean.StudentBean;
import org.strongme.commtech.bean.TeacherBean;
import org.strongme.commtech.bean.TeacherCourseBean;

@Service
public class TeacherService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public Integer save(TeacherBean bean) {
		int result = -1;
		String sql = "INSERT INTO teacher(teacherid,teachername) VALUES('"+bean.getTeacherId()+"','"+bean.getTeacherName()+"');";
		result = jdbcTemplate.update(sql);
		return result;
	}
	
	public Integer update(TeacherBean bean) {
		int result = -1;
		String sql = "UPDATE teacher SET teacherid= '"+bean.getTeacherId()+"',teachername= '"+bean.getTeacherName()+"' WHERE id='"+bean.getId()+"';";
		result = jdbcTemplate.update(sql);
		return result;
	}
	
	public List<TeacherCourseBean> queryByIdAndName(String teacherid,String teachername,String courseid) {
		String sql = "select c.a1,c.a2,c.a3,c.a4,c.a5 from (select b.id a1,a.teacherid a2,a.teachername a3,b.courseid a4,b.grade a5 from teacher a left join teachercourse b on a.teacherid=b.teacherid) c";
		String sub = "";
		if(teacherid!=null&&!"".equals(teacherid)) {
			sub += " and a2='"+teacherid+"'";
		}
		if(courseid!=null&&!"".equals(courseid)) {
			sub += " and a4 = '"+courseid+"'";
		}
		if(teachername!=null&&!"".equals(teachername)) {
			sub += " and a3 like '%"+teachername+"%'";
		}
		if(!"".equals(sub)) {
			sql +=" where 1=1"+sub;
		}
		sql +=" order by c.a2";
		List<TeacherCourseBean> result = jdbcTemplate.query(sql, new RowMapper<TeacherCourseBean>(){
			public TeacherCourseBean mapRow(ResultSet arg0, int arg1) throws SQLException {
				TeacherCourseBean bean = new TeacherCourseBean();
				bean.setId(arg0.getInt(1));
				bean.setTeacherId(arg0.getString(2));
				bean.setTeacherName(arg0.getString(3));
				bean.setCourseId(arg0.getString(4));
				bean.setGrade(arg0.getString(5));
				return bean;
			}});
		return result;
	}
	
	public int updateTeacherCourse(TeacherCourseBean bean) {
		int result = -1;
		String sql = "";
		if(bean.getId()!=0) {
			//更新
			sql ="update teachercourse set courseid='"+bean.getCourseId()+"',grade='"+bean.getGrade()+"' where id="+bean.getId();
		}else {
			//插入
			sql = "insert into teachercourse(teacherid,courseid,grade) values('"+bean.getTeacherId()+"','"+bean.getCourseId()+"','"+bean.getGrade()+"')";
		}
		result = jdbcTemplate.update(sql);
		return result;
	}
	
	public boolean isAlreadyExist(String courseid,String classid) {
		boolean result = false;
		String sql = "select count(1) from teachercourse where courseid='"+courseid+"' and grade='"+classid+"'";
		int tmp = jdbcTemplate.queryForInt(sql);
		if(tmp>0)result= true;
		return result;
	}
	
	public int deleteTeacherCourse(int id) {
		int result = -1;
		String sql = "DELETE FROM teachercourse WHERE id="+id;
		result = jdbcTemplate.update(sql);
		return result;
	}

}
