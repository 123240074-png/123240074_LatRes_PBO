package com.pbo.latres.model;

import com.pbo.latres.config.Koneksi;
import com.pbo.latres.dto.InsertTodoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbToDoRepository implements TodoRepository {

    @Override
    public List<TodoTask> getAll() {

        List<TodoTask> list = new ArrayList<>();

        String sql = "SELECT * FROM todos";

        try {

            Connection conn = Koneksi.getKoneksi();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                TodoTask todo = new TodoTask(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("status")
                );

                list.add(todo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public TodoTask getById(int id) {

        String sql = "SELECT * FROM todos WHERE id=?";

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new TodoTask(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean insert(InsertTodoDTO insertTodoDTO) {

        String sql = "INSERT INTO todos(title, status) VALUES (?, ?)";

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, insertTodoDTO.getTitle());
            ps.setString(2, "Pending");

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean update(TodoTask todoTask) {

        String sql = "UPDATE todos SET title=?, status=? WHERE id=?";

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, todoTask.getTitle());
            ps.setString(2, todoTask.getStatus());
            ps.setInt(3, todoTask.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean deleteById(int id) {

        String sql = "DELETE FROM todos WHERE id=?";

        try {

            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}