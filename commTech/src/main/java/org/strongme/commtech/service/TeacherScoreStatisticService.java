package org.strongme.commtech.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.strongme.commtech.bean.StudentCommCountBean;
import org.strongme.commtech.bean.TeacherScoreStatisticBean;

@Service
public class TeacherScoreStatisticService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<TeacherScoreStatisticBean> census(String courseid) {
		List<TeacherScoreStatisticBean> result = null;
		String sql = "select aaa.teacherid,ccc.teachername,ddd.coursename,sum(bbb.avgscore*bbb.countstudent)/sum(bbb.countstudent) s,sum(bbb.countstudent) from teachercourse aaa,(select avg(a.score) avgscore,a.courseid courseid,b.classid classid,count(a.studentid) countstudent from studentcomm a, student b where a.studentid = b.studentid group by a.courseid , b.classid) bbb,teacher ccc,course ddd where aaa.courseid = bbb.courseid and aaa.grade = bbb.classid and aaa.teacherid = ccc.teacherid and ddd.courseid = bbb.courseid group by aaa.teacherid order by s desc";
		if(courseid!=null&&!"".equals(courseid)) {
			sql = "select aaa.teacherid,ccc.teachername,ddd.coursename,sum(bbb.avgscore*bbb.countstudent)/sum(bbb.countstudent) s,sum(bbb.countstudent) from teachercourse aaa,(select avg(a.score) avgscore,a.courseid courseid,b.classid classid,count(a.studentid) countstudent from studentcomm a, student b where a.studentid = b.studentid group by a.courseid , b.classid) bbb,teacher ccc,course ddd where aaa.courseid = bbb.courseid and aaa.grade = bbb.classid and aaa.teacherid = ccc.teacherid and ddd.courseid = bbb.courseid and ddd.courseid='"+courseid+"' group by aaa.teacherid order by s desc";
		}
		result = jdbcTemplate.query(sql, new RowMapper<TeacherScoreStatisticBean>() {
			public TeacherScoreStatisticBean mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				TeacherScoreStatisticBean bean = new TeacherScoreStatisticBean();
				bean.setTeacherId(arg0.getString(1));
				bean.setTeacherName(arg0.getString(2));
				bean.setCourseName(arg0.getString(3));
				bean.setAvgScore(arg0.getDouble(4));
				bean.setCountStudent(arg0.getInt(5));
				return bean;
			}
		});
		return result;
	}
	
	public List<StudentCommCountBean> cencusCommCount() {
		List<StudentCommCountBean> result = new ArrayList<StudentCommCountBean>();
		String sql = "select bb.x '已评教学生数',bb.y '班级',count(ss.studentid) '此班级人数' from student ss,(select dd.x x,classo.classname y,classo.classid z from class classo left join(select aa.studentcountal x, classf.classname y, classf.classid z from class classf, (SELECT count(distinct (a.studentid)) studentcountal,b.classid classid FROM studentcomm a, student b where a.studentid = b.studentid group by b.classid order by b.classid) aa where aa.classid = classf.classid) dd on dd.z=classo.classid) bb where bb.z = ss.classid group by bb.y order by bb.x desc";
		result = jdbcTemplate.query(sql, new RowMapper<StudentCommCountBean>() {
			public StudentCommCountBean mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				StudentCommCountBean bean = new StudentCommCountBean();
				bean.setAlreadyCount(arg0.getLong(1));
				bean.setClassName(arg0.getString(2));
				bean.setTotalCount(arg0.getLong(3));
				return bean;
			}
		});
		return result;
	}
	

}
