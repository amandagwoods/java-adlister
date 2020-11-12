package com.codeup.adlister.dao;


import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.sql.Result;


public class MySQLUsersDao implements Users{
    import com.codeup.adlister.models.User;
    private Connection connection = null;

    public MySQLUsersDao(Config config){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }

    }
    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";

        try{
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }catch (SQLException exception) {
            throw new RuntimeException("Error finding a user by username", exception);
        }
        return null;
    }

    @Override
    public Long insert(User user) {
        String  query = "INSET INTO users(username, email, password) values (?,?,?)";

        try{
            PreparedStatement stmt connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

            Result rs = stmt.getGeneratedKeys();
            ((ResultSet) rs).next();
            return rs.getLong(1);
        }catch (SQLException exception){
            throw new RuntimeException("Error creating new user");
        }
    }
}
