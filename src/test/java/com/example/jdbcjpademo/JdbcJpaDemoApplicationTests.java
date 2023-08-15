package com.example.jdbcjpademo;

import com.example.jdbcjpademo.entity.Student;
import com.example.jdbcjpademo.entity.Teacher;
import com.example.jdbcjpademo.repository.StudentRepository;
import com.example.jdbcjpademo.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JdbcJpaDemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void saveStudent() {
		Teacher teacher = Teacher.builder()
				.email("sergiiyaroshko@gmail.com")
				.firstName("Serhii")
				.lastName("Yaroshko")
				.build();

		teacherRepository.save(teacher);

		Student student = Student.builder()
				.email("vitalik@gmail.com")
				.firstName("Vitalik")
				.lastName("Yatskiv")
				.teacher(teacher)
				.build();

//		Student student = Student.builder()
//				.email("vitalik1@gmail.com")
//				.firstName("Vitalik")
//				.lastName(null)
//				.build();

		studentRepository.save(student);
	}

	@Test
	public void printAllStudents() {
		List<Student> studentList =
				studentRepository.findAll();

		System.out.println("studentList = " + studentList);
	}

	@Test
	public void printStudentByFirstName() {

		List<Student> student =
				studentRepository.findByFirstName("Vitalik");

		System.out.println("student = " + student);
	}

	@Test
	public void printStudentByFirstNameContaining() {

		List<Student> student =
				studentRepository.findByFirstNameContaining("Vi");

		System.out.println("student = " + student);
	}

	@Test
	public void printStudentByLastNameNotNull() {

		List<Student> student =
				studentRepository.findByLastNameNotNull();

		System.out.println("student = " + student);
	}

	@Test
	public void printStudentByFirstNameAndLastName() {

		Student student =
				studentRepository.findByFirstNameAndLastName("Vitalik", "Yatskiv");

		System.out.println("student = " + student);
	}

	@Test
	public void printGetStudentByEmail() {
		Student student =
				studentRepository.getStudentByEmail(
						"vitalik@gmail.com"
				);

		System.out.println("student = " + student);
	}

	@Test
	public void printGetStudentFirstNameByEmailAddress() {
		String firstName =
				studentRepository.getStudentFirstNameByEmail(
						"vitalik@gmail.com"
				);
		System.out.println("firstName = " + firstName);
	}

	@Test
	public void printGetStudentByEmailAddressNative(){
		Student student =
				studentRepository.getStudentByEmailNative(
						"vitalik@gmail.com"
				);

		System.out.println("student = " + student);
	}

	@Test
	public void printGetStudentByEmailAddressNativeNamedParam() {
		Student student =
				studentRepository.getStudentByEmailNativeNamedParam(
						"vitalik@gmail.com"
				);

		System.out.println("student = " + student);
	}

	@Test
	public void updateStudentNameByEmailIdTest() {
		studentRepository.updateStudentNameByEmail(
				"Vitalii",
				"vitalik@gmail.com");
	}
}
