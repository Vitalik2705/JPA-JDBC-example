package com.example.jdbcjpademo.repository;

import com.example.jdbcjpademo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    Student findByFirstNameAndLastName(String firstName,
                                       String lastName);

    //JPQL
    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmail(String email);


    //JPQL
    @Query("select s.firstName from Student s where s.email = ?1")
    String getStudentFirstNameByEmail(String email);

    //Native
    @Query(
            value = "SELECT * FROM student s where s.email = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailNative(String email);


    //Native Named Param
    @Query(
            value = "SELECT * FROM student s where s.email = :email",
            nativeQuery = true
    )
    Student getStudentByEmailNativeNamedParam(
            @Param("email") String email
    );

    @Modifying
    @Transactional
    @Query(
            value = "update student set first_name = ?1 where email = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmail(String firstName, String email);

}
