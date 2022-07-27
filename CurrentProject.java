// Imported modules
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;


// Class
public class CurrentProject {
    // Main method
    public static void main(String[] args){
        /*
           Project Input Details
           Allows user to enter the data for each question asked
           Try statement for ALL code
           Will execute catch statements for All code
        */
        try{

            // Try statements for main code
            // Will execute catch statements for this block only


            // Connection to our database in our localhost
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser",
                    "swordfish"
            );

            // Declared Statement object
            Statement statement = connection.createStatement();
            Statement addProject = connection.createStatement();
            Statement updateProject = connection.createStatement();

            // Declared ResultSet objects
            ResultSet resultsAdd;
            ResultSet resultsUpdated;

            // Int variable used to count rows added
            int rowsAffected;


            try {


                // Prints the menu option
                String userInput = JOptionPane.showInputDialog("""
                    What do want to do:
                    Add new project to database - Type 1
                    To update the project details - Type 2
                    To finalise the a project - Type 3
                    To check on unfinished or overdue project - Type 4
                    To exit - Type 0""");


                // Creates new project objects and adds them to database
                switch (userInput) {
                    case "1" -> {

                        // Project Name
                        String nameProject = JOptionPane.showInputDialog("""
                                What is project name?
                                If no name is provided, the name should
                                be the building being built + the clients
                                surname. E.g. HouseTyler.""");


                        // Type of building
                        String buildingProject = JOptionPane.showInputDialog
                                ("What type of building is being built?");

                        // Physical address of new building
                        String physicalAddressProject =
                                JOptionPane.showInputDialog
                                        ("What is the physical address" +
                                                " of the project building?");

                        // ERF Number of building
                        String erfProject = JOptionPane.showInputDialog
                                ("What is the ERF number of the building?");

                        // Total Fee for project
                        String totalFeeProject = JOptionPane.showInputDialog
                                ("What is the total cost" +
                                        " of the project?");

                        // Fee paid to date for project
                        String paidToDateProject = JOptionPane.showInputDialog
                                ("What is the cost paid so far" +
                                        " for the project to date?");

                        // Project deadline
                        String deadlineProject = JOptionPane.showInputDialog
                                ("When is the project deadline?" +
                                        "Enter in YYYY-MM-YY format:");

                        // Project completion date
                        String completionDate = "-";

                        // Project complete
                        String isCompleted = "No";

                        // Add project details to project table in database
                        rowsAffected = addProject.executeUpdate(
                                "INSERT INTO projects (project_name," +
                                        " building_type, project_address," +
                                        " erf_number, total_fee, total_paid," +
                                        " deadline_date, completed_date," +
                                        " project_status)" +
                                        "VALUES('"+ nameProject +"', '"
                                        + buildingProject +"', '"
                                        + physicalAddressProject +"', '"
                                        + erfProject +"', '"
                                        + totalFeeProject +"', '"
                                        + paidToDateProject +"'," +
                                        "'"+ deadlineProject +"', '"
                                        + completionDate +"', " +
                                        "'"+ isCompleted +"')"
                        );
                        System.out.println("Query complete, " + rowsAffected +
                                " rows added.");


                        //Engineer details added to database
                        String choiceEngineer = JOptionPane.showInputDialog
                                ("""
                                Do you want to:
                                1. Add a existing engineer to database
                                2. Add a new engineer to database
                                """);

                        if(choiceEngineer.equals("1")){

                            //Rows of engineer data to choose from
                            resultsAdd = addProject.executeQuery
                                    ("SELECT id, name," +
                                    " surname, telephone_number, email," +
                                    " physical_address FROM engineer");

                            /* Loop over the results, printing them all. */
                            printNewResults(resultsAdd);
                            System.out.println("\n");

                            String inputChoice = JOptionPane.showInputDialog
                                    ("""
                                    Enter the number the id number of the
                                    engineer of your choice:
                                    """);

                            // Adds chosen engineer id from engineer table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET engineer_id = " +
                                            "'"+ inputChoice +"' WHERE " +
                                            "project_name = '"+ nameProject +"'"
                            );
                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                        }else if (choiceEngineer.equals("2")){

                            // Engineer details
                            // Allows user to enter the data for each question
                            /*
                             * Contains the following in order
                             * Engineer's name
                             * Engineer's surname
                             * Engineer's telephone number
                             * Engineer's email address
                             * Engineer's physical address
                             */
                            String nameEngineer = JOptionPane.showInputDialog
                                    ("What is the name of the Engineer?");

                            String surnameEngineer =
                                    JOptionPane.showInputDialog
                                    ("What is the surname of the Engineer?");

                            String telephoneEngineer =
                                    JOptionPane.showInputDialog
                                            ("What is the phone number" +
                                                    " of the Engineer?");

                            String emailEngineer = JOptionPane.showInputDialog
                                    ("What is the email address" +
                                            " of the Engineer?");

                            String physicalAddressEngineer =
                                    JOptionPane.showInputDialog
                                            ("What is the physical address" +
                                                    " of the Engineer?");

                            // Adds new engineer details to engineer table
                            rowsAffected = addProject.executeUpdate(
                                    "INSERT INTO engineer (name, surname," +
                                            " telephone_number, email," +
                                            " physical_address)" +
                                            "VALUES('"+ nameEngineer +"', " +
                                            "'"+ surnameEngineer +"', '"
                                            + telephoneEngineer +"', '"
                                            + emailEngineer +"', " +
                                            "'"+ physicalAddressEngineer +"')"

                            );
                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                            // Set ID for engineer_id in projects table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET engineer_id = " +
                                            "(SELECT id FROM engineer WHERE " +
                                            "name = '"+ nameEngineer +"' AND" +
                                            " surname =" +
                                            " '"+ surnameEngineer +"')" +
                                            "WHERE project_name" +
                                            " = '"+ nameProject +"'"

                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");
                        }


                        // Architect details added to database
                        String choiceArchitect = JOptionPane.showInputDialog
                                ("""
                                Do you want to:
                                1. Add a existing architect to database
                                2. Add a new architect to database
                                """);

                        if(choiceArchitect.equals("1")){

                            //Rows of architect data to choose from
                            resultsAdd = statement.executeQuery
                                    ("SELECT id, name, surname," +
                                            " telephone_number, email," +
                                    " physical_address FROM architect");

                            // Loop over the results, printing them all.
                            printNewResults(resultsAdd);
                            System.out.println("\n");

                            String inputChoice = JOptionPane.showInputDialog
                                    ("""
                                    Enter the number the id number of the
                                    architect of your choice:
                                    """);

                            // Adds chosen architect id from engineer table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET architect_id = " +
                                            "'"+ inputChoice +"' WHERE " +
                                            "project_name =" +
                                            " '"+ nameProject +"'"
                            );
                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                        }else if (choiceArchitect.equals("2")){

                            // Architect details
                            // Allows user to enter the data for each question
                            /*
                             * Contains the following in order
                             * Architect's name
                             * Architect's surname
                             * Architect's telephone number
                             * Architect's email address
                             * Architect's physical address
                             */
                            String nameArchitect = JOptionPane.showInputDialog
                                    ("What is the name of the Architect?");

                            String surnameArchitect =
                                    JOptionPane.showInputDialog
                                    ("What is the surname of the Architect?");

                            String telephoneArchitect =
                                    JOptionPane.showInputDialog
                                            ("What is the phone number" +
                                                    " of the Architect?");

                            String emailArchitect =
                                    JOptionPane.showInputDialog
                                    ("What is the email address" +
                                            " of the Architect?");

                            String physicalAddressArchitect =
                                    JOptionPane.showInputDialog
                                            ("What is the physical address" +
                                                    " of the Architect?");

                            // Adds new architect details to architect table
                            rowsAffected = addProject.executeUpdate(
                                    "INSERT INTO architect (name, surname," +
                                            " telephone_number, email," +
                                            " physical_address)" +
                                            "VALUES('"+ nameArchitect +"', " +
                                            "'"+ surnameArchitect +"', '"
                                            + telephoneArchitect +"', '"
                                            + emailArchitect +"', " +
                                            "'"+ physicalAddressArchitect +"')"
                            );
                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                            // Adds architect id to project table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET architect_id = " +
                                            "(SELECT id FROM architect " +
                                            "WHERE name =" +
                                            " '"+ nameArchitect +"'" +
                                            " AND surname = " +
                                            "'"+ surnameArchitect +"')" +
                                            "WHERE project_name =" +
                                            " '"+ nameProject +"'"
                            );

                            System.out.println("Query complete," +
                                    " " + rowsAffected + " rows added.");
                        }


                        // Contractor details to be added to database
                        String choiceContractor = JOptionPane.showInputDialog
                                ("""
                                Do you want to:
                                1. Add a existing contractor to database
                                2. Add a new contractor to database
                                """);

                        if(choiceContractor.equals("1")){

                            // Rows of contractor data to choose from
                            resultsAdd = statement.executeQuery
                                    ("SELECT id, name, surname," +
                                            " telephone_number, email," +
                                    " physical_address FROM manager");

                            // Loop over the results, printing them all.
                            printNewResults(resultsAdd);
                            System.out.println("\n");

                            String inputChoice = JOptionPane.showInputDialog
                                    ("""
                                    Enter the number the id number of the
                                    contractor of your choice:
                                    """);

                            // Adds chosen contractor id from engineer table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET manager_id = " +
                                            "'"+ inputChoice +"' WHERE " +
                                            "project_name = '"+ nameProject +"'"

                            );
                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                        }else if (choiceContractor.equals("2")){

                            // Contractor details
                            // Allows user to enter the data for each question
                            /*
                             * Contains the following in order
                             * Contractor's name
                             * Contractor's surname
                             * Contractor's telephone number
                             * Contractor's email address
                             * Contractor's physical address
                             */
                            String nameContractor =
                                    JOptionPane.showInputDialog
                                    ("What is the name of the contractor?");

                            String surnameContractor =
                                    JOptionPane.showInputDialog
                                    ("What is the surname of the contractor?");

                            String telephoneContractor =
                                    JOptionPane.showInputDialog
                                            ("What is the phone number" +
                                                    " of the contractor?");

                            String emailContractor =
                                    JOptionPane.showInputDialog
                                    ("What is the email address" +
                                            " of the contractor?");

                            String physicalAddressContractor =
                                    JOptionPane.showInputDialog
                                            ("What is the physical address" +
                                                    " of the contractor?");

                            // Add contractor details to manager table
                            rowsAffected = addProject.executeUpdate(
                                    "INSERT INTO manager (name, surname," +
                                            " telephone_number, email, " +
                                            "physical_address)" +
                                            "VALUES('"+ nameContractor +"', " +
                                            "'"+ surnameContractor +"', '"
                                            + telephoneContractor +"', '"
                                            + emailContractor +"', " +
                                            "'"+ physicalAddressContractor +"')"
                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                            // Add manager id to projects table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET manager_id = " +
                                            "(SELECT id FROM manager " +
                                            "WHERE name = " +
                                            "'"+ nameContractor +"'" +
                                            " AND surname =" +
                                            " '"+ surnameContractor +"')" +
                                            "WHERE project_name =" +
                                            " '"+ nameProject +"'"
                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                        }


                        // Customer details to be added to project table
                        String choiceCustomer = JOptionPane.showInputDialog
                                ("""
                                Do you want to:
                                1. Add a existing customer to database
                                2. Add a new customer to database
                                """);


                        if(choiceCustomer.equals("1")){

                            // Rows of customer data to choose from
                            resultsAdd = statement.executeQuery
                                    ("SELECT id, name," +
                                    " surname, telephone_number, email," +
                                    " physical_address FROM customer");

                            // Loop over the results, printing them all.
                            printNewResults(resultsAdd);
                            System.out.println("\n");

                            String inputChoice = JOptionPane.showInputDialog
                                    ("""
                                    Enter the number the id number of the
                                    customer of your choice:
                                    """);


                            // Adds chosen customer id from engineer table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET customer_id = " +
                                            "'"+ inputChoice +"' WHERE " +
                                            "project_name = '"+ nameProject +"'"

                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                        }else if (choiceCustomer.equals("2")){

                            // Customer details
                            // Allows user to enter the data for each question
                            /*
                             * Contains the following in order
                             * Customer's name
                             * Customer's surname
                             * Customer's telephone number
                             * Customer's email address
                             * Customer's physical address
                             */
                            String nameCustomer =
                                    JOptionPane.showInputDialog
                                    ("What is the name of the customer?");

                            String surnameCustomer =
                                    JOptionPane.showInputDialog
                                    ("What is the surname of the customer?");

                            String telephoneCustomer =
                                    JOptionPane.showInputDialog
                                            ("What is the phone number" +
                                                    " of the customer?");

                            String emailCustomer =
                                    JOptionPane.showInputDialog
                                    ("What is the email address" +
                                            " of the customer?");

                            String physicalAddressCustomer =
                                    JOptionPane.showInputDialog
                                            ("What is the physical address" +
                                                    " of the customer?");

                            // Add customer details to customer table
                            rowsAffected = addProject.executeUpdate(
                                    "INSERT INTO customer (name, surname," +
                                            " telephone_number, email," +
                                            " physical_address)" +
                                            "VALUES('"+ nameCustomer +"', " +
                                            "'"+ surnameCustomer +"', '"
                                            + telephoneCustomer +"', '"
                                            + emailCustomer +"', " +
                                            "'"+ physicalAddressCustomer +"')"
                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");

                            // Add customer id to projects table
                            rowsAffected = addProject.executeUpdate(
                                    "UPDATE projects SET customer_id = " +
                                            "(SELECT id FROM customer" +
                                            " WHERE name =" +
                                            " '"+ nameCustomer +"'" +
                                            " AND surname =" +
                                            " '"+ surnameCustomer +"')" +
                                            "WHERE project_name =" +
                                            " '"+ nameProject +"'"

                            );

                            System.out.println("Query complete, " +
                                    "" + rowsAffected + " rows added.");
                        }
                    }

                    case "2" -> {

                        /*
                         * Read from database information
                         * Print the details in list format
                         * Menu class calling list method
                        */
                        Menu.menu();


                        // Select project using project name or ID
                        int choice = Integer.parseInt
                                (JOptionPane.showInputDialog("""
                                Select option to find project you
                                 wish to update
                                1. Project ID
                                2. Project Name
                                """));

                        if(choice == 1) {

                            int numberInput = Integer.parseInt
                                    (JOptionPane.showInputDialog("""
                                            Enter project ID:
                                            """));

                            resultsUpdated = updateProject.executeQuery
                                    ("SELECT * FROM projects WHERE " +
                                            "project_number =" +
                                            " '" + numberInput + "'");

                            // Returns our chosen project details
                            while (resultsUpdated.next()) {
                                System.out.println(resultsUpdated.getInt
                                        ("project_number") + ", "
                                        + resultsUpdated.getString
                                        ("project_name") + ", "
                                        + resultsUpdated.getString
                                        ("building_type") + ", "
                                        + resultsUpdated.getString
                                        ("project_address") + ", "
                                        + resultsUpdated.getString
                                        ("erf_number") + ", "
                                        + resultsUpdated.getFloat
                                        ("total_fee") + ", "
                                        + resultsUpdated.getFloat
                                        ("total_paid") + ", "
                                        + resultsUpdated.getString
                                        ("deadline_date") + ", "
                                        + resultsUpdated.getString
                                        ("completed_date") + ", "
                                        + resultsUpdated.getString
                                        ("project_status") + ", "
                                        + resultsUpdated.getInt
                                        ("engineer_id") + ", "
                                        + resultsUpdated.getInt
                                        ("architect_id") + ", "
                                        + resultsUpdated.getInt
                                        ("manager_id") + ", "
                                        + resultsUpdated.getInt
                                        ("customer_id")
                                );

                                System.out.println("\n");

                                // Menu to choose what we want to edit
                                String menu = JOptionPane.showInputDialog("""
                                        What do you want to edit:
                                        Updated Fee cost - Type 1
                                        Updated fee paid so far - Type 2
                                        New deadline for project - Type 3
                                        """);

                                // If - else/if  statement
                                // Will be executed based on our menu choice

                                if (menu.equalsIgnoreCase("1")) {

                                    // Enter the new total fee for project
                                    String newFee =
                                            JOptionPane.showInputDialog
                                                    ("Enter new total " +
                                                            "price for the " +
                                                            "completion of the" +
                                                            " project: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET total_fee = " +
                                                    "'" + newFee + "' " +
                                                    "WHERE project_number = " +
                                                    "'"+ numberInput + "'"
                                    );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                } else if (menu.equalsIgnoreCase("2")) {

                                    // Enter the new fee paid
                                    // so far for the project
                                    String paidFee =
                                            JOptionPane.showInputDialog
                                                    ("Enter new updated" +
                                                            " fee paid so" +
                                                            " far: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET total_paid = " +
                                                    "'" + paidFee + "' " +
                                                    "WHERE project_number = " +
                                                    "'"+ numberInput + "'"
                                            );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");
                                    break;

                                } else if (menu.equalsIgnoreCase("3")) {

                                    // Enter the new deadline for the project
                                    String date = JOptionPane.showInputDialog
                                            ("Enter a new deadline: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET deadline_date = " +
                                                    "'" + date + "' " +
                                                    "WHERE project_number = " +
                                                    "'"+ numberInput + "'"
                                            );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                }
                                resultsUpdated.close();
                            }
                        } else if(choice == 2){

                            String nameInput = JOptionPane.showInputDialog("""
                                            Enter project ID:
                                            """);

                            resultsUpdated = updateProject.executeQuery
                                    ("SELECT * FROM projects WHERE " +
                                            "project_name =" +
                                            " '" + nameInput + "'");

                            // Returns our chosen project details
                            while (resultsUpdated.next()) {
                                System.out.println(resultsUpdated.getInt
                                        ("project_number") + ", "
                                        + resultsUpdated.getString
                                        ("project_name") + ", "
                                        + resultsUpdated.getString
                                        ("building_type") + ", "
                                        + resultsUpdated.getString
                                        ("project_address") + ", "
                                        + resultsUpdated.getString
                                        ("erf_number") + ", "
                                        + resultsUpdated.getFloat
                                        ("total_fee") + ", "
                                        + resultsUpdated.getFloat
                                        ("total_paid") + ", "
                                        + resultsUpdated.getString
                                        ("deadline_date") + ", "
                                        + resultsUpdated.getString
                                        ("completed_date") + ", "
                                        + resultsUpdated.getString
                                        ("project_status") + ", "
                                        + resultsUpdated.getInt
                                        ("engineer_id") + ", "
                                        + resultsUpdated.getInt
                                        ("architect_id") + ", "
                                        + resultsUpdated.getInt
                                        ("manager_id") + ", "
                                        + resultsUpdated.getInt
                                        ("customer_id")
                                );

                                System.out.println("\n");

                                // Menu to choose what we want to edit
                                String menu = JOptionPane.showInputDialog("""
                                        What do you want to edit:
                                        Updated Fee cost - Type 1
                                        Updated fee paid so far - Type 2
                                        New deadline for project - Type 3
                                        """);

                                // If - else/if  statement
                                // Will be executed based on our menu choice

                                if (menu.equalsIgnoreCase("1")) {

                                    // Enter the new total fee for project
                                    String newFee =
                                            JOptionPane.showInputDialog
                                                    ("Enter new total " +
                                                            "price for the " +
                                                            "completion of the" +
                                                            " project: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET total_fee = " +
                                                    "'" + newFee + "' " +
                                                    "WHERE project_name = " +
                                                    "'"+ nameInput + "'"
                                            );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                } else if (menu.equalsIgnoreCase("2")) {

                                    // Enter the new fee paid
                                    // so far for the project
                                    String paidFee =
                                            JOptionPane.showInputDialog
                                                    ("Enter new updated" +
                                                            " fee paid so" +
                                                            " far: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET total_paid = " +
                                                    "'" + paidFee + "' " +
                                                    "WHERE project_name = " +
                                                    "'"+ nameInput + "'"
                                            );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                } else if (menu.equalsIgnoreCase("3")) {

                                    // Enter the new deadline for the project
                                    String date = JOptionPane.showInputDialog
                                            ("Enter a new deadline: \n");

                                    // Updates our details in project table
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET deadline_date = " +
                                                    "'" + date + "' " +
                                                    "WHERE project_name = " +
                                                    "'"+ nameInput + "'"
                                            );
                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                }
                            }
                            resultsUpdated.close();
                        }
                    }

                    case "3"-> {

                        /*
                         * Read from database information
                         * Print the details in list format
                         * Menu class calling list method
                         */
                        Menu.menu();

                        java.util.List<Edit> addList =
                                new ArrayList<>();
                        java.util.List<CustomerDetails> newList =
                                new ArrayList<>();

                        // Method to find project
                        String choice = JOptionPane.showInputDialog("""
                                How do you wish to find the project:
                                1. Project ID
                                2. Project Name
                                """);

                        // Project ID
                        if (choice.equals("1")){
                            int numberInput = Integer.parseInt
                                    (JOptionPane.showInputDialog("""
                                            Enter project ID:
                                            """));

                            resultsUpdated = statement.executeQuery
                                    ("SELECT project_number, project_name," +
                                    " building_type, project_address," +
                                            " erf_number, total_fee," +
                                            " total_paid, deadline_date," +
                                            " completed_date, project_status," +
                                            " engineer_id, architect_id," +
                                            " manager_id, customer_id" +
                                            " FROM projects");

                            /* Loop over the results, printing them all. */
                            while (resultsUpdated.next()) {
                                Edit edit = new Edit
                                        (resultsUpdated.getInt
                                        ("project_number"),
                                        resultsUpdated.getString
                                        ("project_name"),
                                        resultsUpdated.getString
                                        ("building_type"),
                                        resultsUpdated.getString
                                        ("project_address"),
                                        resultsUpdated.getString
                                        ("erf_number"),
                                        resultsUpdated.getFloat
                                        ("total_fee"),
                                        resultsUpdated.getFloat
                                        ("total_paid"),
                                        resultsUpdated.getString
                                        ("deadline_date"),
                                        resultsUpdated.getString
                                        ("completed_date"),
                                        resultsUpdated.getString
                                        ("project_status"),
                                        resultsUpdated.getInt
                                        ("engineer_id"),
                                        resultsUpdated.getInt
                                        ("architect_id"),
                                        resultsUpdated.getInt
                                        ("manager_id"),
                                        resultsUpdated.getInt
                                        ("customer_id"));

                                addList.add(edit);
                            }
                                System.out.println("\n");


                            // For loop details of project
                            for(Edit editObject: addList){

                                // Allows us to choose a project by name
                                if(numberInput ==
                                        editObject.getProjectNumber()) {

                                    resultsAdd = updateProject.executeQuery
                                            ("SELECT id, name, surname," +
                                                    " telephone_number, email," +
                                                    " physical_address" +
                                                    " FROM customer" +
                                                    " WHERE id =" +
                                                    " '"+ editObject.
                                                    getCustomerId() +"'");

                                    // Loop over the results, printing them all.
                                    while (resultsAdd.next()) {
                                        CustomerDetails details =
                                                new CustomerDetails
                                                (resultsAdd.getInt
                                                        ("id"),
                                                        resultsAdd.getString
                                                        ("name"),
                                                        resultsAdd.getString
                                                        ("surname"),
                                                        resultsAdd.getString
                                                        ("telephone_number"),
                                                        resultsAdd.getString
                                                        ("email"),
                                                        resultsAdd.getString
                                                        ("physical_address"));

                                        newList.add(details);
                                    }

                                    // Subtract total amount
                                    // by amount paid so far.
                                    float amountDue = editObject.getTotalFee()
                                            - editObject.getPaidToDate();

                                    // Input date for completed project
                                    String finalDate =
                                            JOptionPane.showInputDialog("""
                                            Please enter the complete date
                                             in the following format:
                                            YYYY-MM-DD e.g. 2016-08-21.
                                            """);

                                    // Menu to choose what we want to edit
                                    String finalise = "Yes";

                                    if(amountDue > 0) {

                                        // Prints invoice and amount owed
                                        for (CustomerDetails detailsObject :
                                                newList) {
                                            System.out.println
                                            (detailsObject.toString());
                                            System.out.println
                                            ("\nAmount owed: " + amountDue);
                                            System.out.println
                                            ("Thanks for your patronage.");
                                        }
                                    } else {
                                        // No invoice if paid in full
                                        System.out.println
                                        ("Project has been paid for already.");
                                    }

                                    // Updates project details
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET completed_date = " +
                                                    "'" + finalDate + "' " +
                                                    "WHERE project_number = " +
                                                    "'"+ numberInput + "'"
                                            );

                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    // Updates project details
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET project_status = " +
                                                    "'" + finalise + "' " +
                                                    "WHERE project_number = " +
                                                    "'"+ numberInput + "'"
                                            );

                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                }
                            }
                        } else if(choice.equals("2")) {

                            // Select project using project name
                            String nameInput = JOptionPane.showInputDialog("""
                                            Enter project name:
                                            """);

                            resultsUpdated = statement.executeQuery
                                    ("SELECT project_number, project_name," +
                                    " building_type, project_address," +
                                    " erf_number, total_fee, total_paid," +
                                    " deadline_date, completed_date," +
                                    " project_status, engineer_id," +
                                    " architect_id, manager_id," +
                                    " customer_id FROM projects");

                            /* Loop over the results, printing them all. */
                            while (resultsUpdated.next()) {
                                Edit edit = new Edit
                                        (resultsUpdated.getInt
                                        ("project_number"),
                                                resultsUpdated.getString
                                        ("project_name"),
                                                resultsUpdated.getString
                                        ("building_type"),
                                                resultsUpdated.getString
                                        ("project_address"),
                                                resultsUpdated.getString
                                        ("erf_number"),
                                                resultsUpdated.getFloat
                                        ("total_fee"),
                                                resultsUpdated.getFloat
                                        ("total_paid"),
                                                resultsUpdated.getString
                                        ("deadline_date"),
                                        resultsUpdated.getString
                                        ("completed_date"),
                                                resultsUpdated.getString
                                        ("project_status"),
                                                resultsUpdated.getInt
                                        ("engineer_id"),
                                                resultsUpdated.getInt
                                        ("architect_id"),
                                                resultsUpdated.getInt
                                        ("manager_id"),
                                                resultsUpdated.getInt
                                        ("customer_id"));

                                addList.add(edit);
                            }
                            System.out.println("\n");

                            for(Edit editObject: addList){

                                // Allows us to choose a project by name
                                if(nameInput.equals(editObject.
                                        getProjectName())) {

                                    // Select our chosen project
                                    resultsAdd = updateProject.executeQuery
                                            ("SELECT id, name," +
                                                    " surname," +
                                                    " telephone_number," +
                                                    " email," +
                                                    " physical_address" +
                                                    " FROM customer" +
                                                    " WHERE id =" +
                                                    " '"+ editObject.
                                                    getCustomerId() +"'");

                                    // Loop over the results, printing them all.
                                    while (resultsAdd.next()) {
                                        CustomerDetails details =
                                                new CustomerDetails
                                                (resultsAdd.getInt
                                                        ("id"),
                                                        resultsAdd.getString
                                                        ("name"),
                                                        resultsAdd.getString
                                                        ("surname"),
                                                        resultsAdd.getString
                                                        ("telephone_number"),
                                                        resultsAdd.getString
                                                        ("email"),
                                                        resultsAdd.getString
                                                        ("physical_address"));

                                        newList.add(details);
                                    }

                                    float amountDue = editObject.getTotalFee()
                                            - editObject.getPaidToDate();

                                    // Input date for completed project
                                    String finalDate =
                                            JOptionPane.showInputDialog("""
                                            Please enter the complete date
                                             in the following format:
                                            YYYY-MM-DD e.g. 2016-08-21.
                                            """);

                                    // Menu to choose what we want to edit
                                    String finalise = "Yes";

                                    if(amountDue > 0) {

                                        // Prints invoice and amount owed
                                        for (CustomerDetails detailsObject :
                                                newList) {
                                            System.out.println
                                            (detailsObject.toString());
                                            System.out.println
                                            ("\nAmount owed: " + amountDue);
                                            System.out.println
                                            ("Thanks for your patronage.");
                                        }
                                    } else {
                                        // No invoice if paid in full
                                        System.out.println
                                        ("Project has been paid for already.");
                                    }

                                    // Updates project details
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET completed_date = " +
                                                    "'" + finalDate + "' " +
                                                    "WHERE project_name = " +
                                                    "'"+ nameInput + "'"
                                            );

                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    // Updates project details
                                    rowsAffected = updateProject.executeUpdate
                                            ("UPDATE projects " +
                                                    "SET project_status = " +
                                                    "'" + finalise + "' " +
                                                    "WHERE project_name = " +
                                                    "'"+ nameInput + "'"
                                            );

                                    System.out.println(
                                            "Query complete, " + rowsAffected +
                                                    " rows updated.");

                                    break;
                                }
                            }
                        }


                    }

                    case "4" -> {


                        // Choose options available
                        String choice = JOptionPane.showInputDialog("""
                            Which do you want to see:
                            Incomplete projects - 1
                            Overdue and incomplete projects - 2
                            """);

                        if (choice.equalsIgnoreCase("1")) {


                            System.out.println("Check for" +
                                    " incomplete projects");

                            // Finds all incomplete projects
                            resultsUpdated = statement.executeQuery
                                    ("SELECT * FROM projects WHERE " +
                                            "project_status = 'No'");

                            // Returns our chosen project details
                            printResults(resultsUpdated);

                        } else if (choice.equalsIgnoreCase("2")) {

                            System.out.println("Check for incomplete" +
                                    " and overdue projects");

                            // Create a java calendar instance
                            Calendar calendar = Calendar.getInstance();

                            // Java date from the Calendar instance.
                            // Java date will represent "now".
                            java.util.Date currentDate = calendar.getTime();

                            // Create a java.sql.Date from the java.util.Date
                            // This will give us the current date for MySQL
                            java.sql.Date date = new java.sql.Date
                                    (currentDate.getTime());

                            // Finds all incomplete and overdue projects
                            resultsUpdated = statement.executeQuery
                                    ("SELECT * FROM projects WHERE " +
                                            "project_status = 'No'" +
                                            "AND deadline_date <=" +
                                            " '"+ date +"' ");

                            // Returns our chosen project details
                            printResults(resultsUpdated);

                        }
                    }

                    // Ends Program
                    case "0" -> System.exit(0);
                }

                // Closed connections and statements
                addProject.close();
                statement.close();
                updateProject.close();
                connection.close();

            } catch (NullPointerException e) {

                // Null exception message if executed
                System.out.println("There is a null value");
                throw e;
            }

        }
        catch(HeadlessException e) {
            // Headless exception message if executed
            System.out.println("You are in a HEADLESS environment");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect user input");
            throw e;
        } catch (RuntimeException e) {
            // Runtime exception message if executed
            System.out.println
                    ("Runtime exceptions. Check logic or syntax errors.");
            throw e;
        } catch (SQLException e) {
            // SQL Errors printed to console
            e.printStackTrace();
        }
    }

    // Methods

    // Used  in the add task (Switch case -> 1)
    public static void printNewResults(ResultSet resultsAdd)
            throws SQLException {
        while (resultsAdd.next()) {
            System.out.println(resultsAdd.getInt
                    ("id") + ", "
                    + resultsAdd.getString
                    ("name") + ", "
                    + resultsAdd.getString
                    ("surname") + ", "
                    + resultsAdd.getString
                    ("telephone_number") + ", "
                    + resultsAdd.getString
                    ("email") + ", "
                    + resultsAdd.getString
                    ("physical_address"));
        }
    }

    // Used  in the overdue and incomplete task (Switch case -> 4)
    public static void printResults(ResultSet resultsUpdated)
            throws SQLException {
        while (resultsUpdated.next()) {
            System.out.println(resultsUpdated.getInt
                    ("project_number") + ", "
                    + resultsUpdated.getString
                    ("project_name") + ", "
                    + resultsUpdated.getString
                    ("building_type") + ", "
                    + resultsUpdated.getString
                    ("project_address") + ", "
                    + resultsUpdated.getString
                    ("erf_number") + ", "
                    + resultsUpdated.getFloat
                    ("total_fee") + ", "
                    + resultsUpdated.getFloat
                    ("total_paid") + ", "
                    + resultsUpdated.getString
                    ("deadline_date") + ", "
                    + resultsUpdated.getString
                    ("completed_date") + ", "
                    + resultsUpdated.getString
                    ("project_status") + ", "
                    + resultsUpdated.getInt
                    ("engineer_id") + ", "
                    + resultsUpdated.getInt
                    ("architect_id") + ", "
                    + resultsUpdated.getInt
                    ("manager_id") + ", "
                    + resultsUpdated.getInt
                    ("customer_id")
            );
        }
    }
}

/* References
* https://www.oracle.com/za/technical-resources/articles/java/javadoc-tool
*
* // To get date
* https://stackoverflow.com/questions/18257648
*   /get-the-current-date-in-java-sql-date-format
*/