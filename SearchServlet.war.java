package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdb"; // Change 'yourdb' to your database name
    private static final String DB_USER = "username"; // Your database username
    private static final String DB_PASSWORD = "password"; // Your database password

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String query = request.getParameter("query");
        ArrayList<String> results = new ArrayList<>();

        // Establish database connection
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Prepare SQL query
            String sql = "SELECT url FROM Websites WHERE description LIKE ? OR keywords LIKE ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "%" + query + "%");
                statement.setString(2, "%" + query + "%");
                
                // Execute query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        results.add(resultSet.getString("url"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set results as a request attribute and forward to result page
        request.setAttribute("results", results);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}