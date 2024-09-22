package com.practice.cruddemo.doa;

import java.util.List;

import com.practice.cruddemo.entity.Course;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;
import com.practice.cruddemo.entity.Student;

public interface AppDAO {
	
	void save(Instructor theInstructor);
	public Instructor findInstructorById(int theId);
	void deleteInstructorById(int theID);
	InstructorDetail findInstructorDetailById(int theId);
	void deleteInstructorDetailById(int theId);
	List<Course> findCoursesByInstructorId(int theId);
	Instructor findInstructorByIdJoinFetch(int theId);
	void update(Instructor tempInstructor);
	void updateCourse(Course tempCourse);
	Course findCourseById(int theId);
	void deleteCourseById(int theId);
	void save(Course theCourse);
	Course findCourseAndReviewsByCourseId(int theId);
	Course findCourseAndStudentsByCourseId(int theId);
	Student findStudentsAndCoursesByStudentId(int theId);
	void update(Student tempStudent);
	void deleteStudentById(int theId);
}
