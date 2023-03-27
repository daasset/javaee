package dao;

import model.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static boolean create(Student student) {
        if (student.getId() != null) {
            throw new IllegalArgumentException("Cannot save a student with existing id");
        }

        if (student.getName() == null || student.getSurname() == null
                || student.getBirthDate() == null || student.getCity() == null) {
            return false;
        }

        int row = 0;

        try (PreparedStatement stmt = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "insert into student (name, surname, birthdate, city) values (?, ?, ?, ?)"))
        {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setDate(3, Date.valueOf(student.getBirthDate()));
            stmt.setString(4, student.getCity());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static boolean edit(Student student) {
        if (student.getId() == null || student.getName() == null || student.getSurname() == null
                || student.getBirthDate() == null || student.getCity() == null) {
            return false;
        }

        int row = 0;

        try (PreparedStatement stmt = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "update student set name = ?, surname = ?, birthdate = ?, city = ? where id = ?"))
        {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setDate(3, Date.valueOf(student.getBirthDate()));
            stmt.setString(4, student.getCity());
            stmt.setLong(5, student.getId());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        int row = 0;

        try (PreparedStatement stmt = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "delete from student where id = ?"))
        {
            stmt.setLong(1, id);
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static Student getStudent(Long id) {
        if (id == null) {
            return null;
        }
        Student student = null;
        try (PreparedStatement stmt
                     = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "select * from student where id = ?"))
        {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                student = new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("birthdate").toLocalDate(),
                        rs.getString("city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    public static List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        try (PreparedStatement stmt
                     = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "select * from student order by id"))
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("birthdate").toLocalDate(),
                        rs.getString("city"));
                allStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allStudents;
    }
}
