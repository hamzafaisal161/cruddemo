package com.practice.cruddemo.doa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.cruddemo.entity.Course;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;
import com.practice.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;
	
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		entityManager.persist(theInstructor);
	}

	@Override
	public Instructor findInstructorById(int theId) {
		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int theID) {
		Instructor tempInstructor = entityManager.find(Instructor.class, theID);
		tempInstructor.getCourses().forEach(c -> c.setInstructor(null));
		entityManager.remove(tempInstructor);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int theId) {
		return entityManager.find(InstructorDetail.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int theId) {
		InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
		tempInstructorDetail.getInstructor().setInstructorDetail(null);
		entityManager.remove(tempInstructorDetail);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		TypedQuery<Course> query = entityManager.createQuery(
									"from Course where instructor.id = :data", Course.class);
		query.setParameter("data", theId);
		List<Course> courses = query.getResultList();
		return courses;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int theId) {
		TypedQuery<Instructor> query = entityManager.createQuery(
				"select i from Instructor i JOIN FETCH i.courses "
				+ "JOIN FETCH i.instructorDetail where "
				+ "i.id = :data", Instructor.class);
		query.setParameter("data", theId);
		Instructor instructor = query.getSingleResult();
		return instructor;
	}

	@Override
	@Transactional
	public void update(Instructor tempInstructor) {
		entityManager.merge(tempInstructor);
	}

	@Override
	@Transactional
	public void updateCourse(Course tempCourse) {
		entityManager.merge(tempCourse);
	}

	@Override
	public Course findCourseById(int theId) {
		return entityManager.find(Course.class, theId);
	}

	@Override
	@Transactional
	public void deleteCourseById(int theId) {
		Course tempCourse = entityManager.find(Course.class, theId);
		entityManager.remove(tempCourse);
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		entityManager.persist(theCourse);
		
	}

	@Override
	public Course findCourseAndReviewsByCourseId(int theId) {
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c JOIN FETCH "
				+ "c.reviews "
				+ "where c.id = :data", Course.class);
		query.setParameter("data", theId);
		Course course = query.getSingleResult();
		return course;
	}

	@Override
	public Course findCourseAndStudentsByCourseId(int theId) {
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c JOIN FETCH "
				+ "c.students "
				+ "where c.id = :data", Course.class);
		query.setParameter("data", theId);
		Course course = query.getSingleResult();
		return course;
	}

	@Override
	public Student findStudentsAndCoursesByStudentId(int theId) {
		TypedQuery<Student> query = entityManager.createQuery(
				"select s from Student s "
				+ "JOIN FETCH s.courses "
				+ "where s.id = :data",Student.class);
		query.setParameter("data", theId);
		Student student = query.getSingleResult();
		return student;
	}

	@Override
	@Transactional
	public void update(Student tempStudent) {
		entityManager.merge(tempStudent);
	}

	@Override
	@Transactional
	public void deleteStudentById(int theId) {
		Student tempStudent = entityManager.find(Student.class, theId);
		entityManager.remove(tempStudent);
	}

}
