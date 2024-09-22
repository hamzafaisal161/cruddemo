package com.practice.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.practice.cruddemo.doa.AppDAO;
import com.practice.cruddemo.entity.Course;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;
import com.practice.cruddemo.entity.Review;
import com.practice.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner ->{
			//createCourseAndReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}
	
	private void deleteStudent(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting student id: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentsAndCoursesByStudentId(theId);
		Course tempCourse1 = new Course("Spiderman2");
		Course tempCourse2 = new Course("Hitman3");
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		System.out.println("Updating student: " + tempStudent);
		System.out.println("Associated courses: " + tempStudent.getCourses());
		appDAO.update(tempStudent);
		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentsAndCoursesByStudentId(theId);
		System.out.println("Loaded students:" + tempStudent);
		System.out.println("Loaded courses:" + tempStudent.getCourses());
		System.out.println("Done");
		
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 12;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course:" + tempCourse);
		System.out.println("Loaded students:" + tempCourse.getStudents());
		System.out.println("Done");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course tempCourse = new Course("GTA7!!!!!");
		Student tempStudent1 = new Student("Hamza", "Faisal", "hamzi161@gmail.com");
		Student tempStudent2 = new Student("Saud", "Yasin", "saudyasin@gmail.com");
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		System.out.println("Saving the course:" + tempCourse);
		System.out.println("Associated students:" + tempCourse.getStudents());
		appDAO.save(tempCourse);
		System.out.println("Done");
	}


	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 12;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}


	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 12;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
	}


	private void createCourseAndReviews(AppDAO appDAO) {
		Course tempCourse = new Course("Ping Pong");
		tempCourse.addReviews(new Review("Achi to hai"));
		tempCourse.addReviews(new Review("Best to hai"));
		tempCourse.addReviews(new Review("Changi to hai"));
		tempCourse.addReviews(new Review("Chuss to hai"));
		System.out.println("Saving course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);
	}


	private void deleteCourse(AppDAO appDAO) {
		int theId = 12;
		System.out.println("deleting course id: "+  theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}


	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course id: "+  theId);
		Course tempCourse = appDAO.findCourseById(theId);
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("GTA6");
		appDAO.updateCourse(tempCourse);
	}


	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+  theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);
		System.out.println("Done!");
	}


	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
		
	}


	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}


	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}


	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube/", "Gamer");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		
		Course tempCourse1 = new Course("GTA5");
		Course tempCourse2 = new Course("RDR2");
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		System.out.println("Saving instructor" + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}


	private void deleteInstructorDetail(AppDAO appDAO) {
		int theID = 9;
		System.out.println("deleting instructor detail id: "+  theID);
		appDAO.deleteInstructorDetailById(theID);
		System.out.println("Done!");
	}


	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 4;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);
		System.out.println("the assoicated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}


	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}


	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());;
		
	}


	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube/", "Guitar");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		System.out.println("Saving instructor" + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}
	
}