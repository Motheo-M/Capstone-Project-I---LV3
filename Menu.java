// Imported modules
import java.sql.*;

// Class
public class Menu {
    // Static
    static void menu() {
        // Try - catch statement
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser",
                    "swordfish"
            );

            Statement statement = connection.createStatement();

            ResultSet results;

            // Gives us rows of data from database
            results = statement.executeQuery
                    ("SELECT project_number, project_name," +
                    " building_type, project_address, erf_number," +
                    " total_fee, total_paid, deadline_date, completed_date," +
                    " project_status, engineer_id, architect_id, manager_id," +
                    " customer_id FROM projects");

            /* Loop over the results, printing them all. */
            while (results.next()) {
                System.out.println(results.getInt("project_number") + ", "
                        + results.getString("project_name") + ", "
                        + results.getString("building_type") + ", "
                        + results.getString("project_address") + ", "
                        + results.getString("erf_number") + ", "
                        + results.getFloat("total_fee") + ", "
                        + results.getFloat("total_paid") + ", "
                        + results.getString("deadline_date") + ", "
                        + results.getString("completed_date") + ", "
                        + results.getString("project_status") + ", "
                        + results.getInt("engineer_id") + ", "
                        + results.getInt("architect_id") + ", "
                        + results.getInt("manager_id") + ", "
                        + results.getInt("customer_id"));
            }
            System.out.println("\n");


        } catch (SQLException e) {
            System.out.println("Check your SQL code");
            e.printStackTrace();
        }
    }
}

