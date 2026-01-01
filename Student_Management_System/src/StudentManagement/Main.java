package StudentManagement; 

import java.util.Scanner;


import java.util.ArrayList;
import java.util.*;
import java.io.*;

//PURPOSE: Main controller class of the Student Management System.
//HOW: Coordinates user input, menu selection, student operations,
//and file-based data persistence.

public class Main {

    static Scanner input = new Scanner(System.in);

 // PURPOSE: Stores all student records dynamically.
 // HOW: Uses an ArrayList to allow adding, removing,
 // editing, and searching student objects efficiently.
    static ArrayList<Student> students = new ArrayList<>();
    
 // PURPOSE: Defines the file used for permanent storage.
 // HOW: Student data is written to and read from this file
 // so records are preserved between program runs.
    static final String FILE_NAME = "studentlist.txt"; //  file to store data

    static int count=0;
    
 // PURPOSE: Starts and terminates the application safely.
 // HOW: Loads student data from file, runs the menu loop,
 // and saves updated records before program exit.
    public static void main(String[] args) {
    	System.out.println(new File("studentlist.txt").getAbsolutePath());
    	loadFromFile(); // Load students at start
        processCommands();
        saveToFile();
    }

 // PURPOSE: Displays the program menu and handles user choices.
 // HOW: Uses a loop and switch statement to call
 // the appropriate function based on user input.
    public static void processCommands() {
    	
    	 System.out.println("===== Student list =====");
         System.out.println("1- Add a single student");
         System.out.println("2- Add multiple students (complete roster)");
         System.out.println("3- Display all students");
         System.out.println("4- Find a student by name");
         System.out.println("5- Display highest & lowest GPA");
         System.out.println("6- Count students above age");
         System.out.println("7- Delete student from list");
         System.out.println("8- Edit student detail");
         System.out.println("9- Exit");
         System.out.print("Enter your choice: ");
        
    	int option;

       
        option = input.nextInt();
        
     // Loop continues until user selects Exit (option 9)
     // This allows the program to keep running without restarting
        while (option != 9) {
            switch (option) {

                case 1:
                    Single();
                    break;

                case 2:
                	Multiple();               	
                    break;

                case 3:
                	Display();               	
                    break;
                    
                case 4:
                	Find();          
                    break;
                    
                case 5:
                    GPAHL();
                    break;
                case 6:
                	AgeGreater();
                    break;
                    
                case 7:
                	Delete();
                	break;
                	
                case 8:
                	Edit() ;
                	break;
                default:
                    System.out.println("Invalid option");
            }

         // Prompt user again after each operation
            System.out.print("\nPlease enter option: ");
            option = input.nextInt();
        }

        System.out.println("Program exited.");
    }

   
 // PURPOSE: Adds a single student record to the system.
 // HOW: Prompts the user for student details, creates
 // a Student object, and stores it in the ArrayList.
    public static void Single() {
    	System.out.println("Adding single student..."); 
    	
    	System.out.println("Enter student details (Name ID Age GPA): ");

        String name = input.next();
        int id = input.nextInt();
        int age = input.nextInt();
        double gpa = input.nextDouble();

        students.add(new Student(name, id, age,gpa));
        

        System.out.println("Student added successfully!");
        count ++;
        
        
    }

 // PURPOSE: Adds multiple students in one session.
 // HOW: Continuously reads student data until the user
 // enters "Done", adding each record to the list.
    public static void Multiple() {

        System.out.println("Adding multiple students...");
        System.out.println("When you are done, type: Done");
        System.out.print("Enter student details (Name ID Age GPA): ");
        
     // Infinite loop used so students can be entered continuously
     // Loop stops only when user types "Done"
        while (true) {

            String name = input.next();

            // Exit condition to stop adding students
            if (name.equalsIgnoreCase("Done")) {
                break;
            }

            int id = input.nextInt();
            int age = input.nextInt();
            double gpa = input.nextDouble();

            students.add(new Student(name, id, age,gpa));
            count ++;
        }

        System.out.println("Students added successfully!");
    }
    
 // PURPOSE: Displays all stored student records.
 // HOW: Iterates through the ArrayList and prints
 // each student’s details in a formatted table.
    public static void Display()
    {
    	System.out.print("\nDisplaying all students\n");
    	 System.out.printf("\n%-15s %-8s %-6s %s", "Name", "ID", "Age", "GPA");
    	 System.out.printf("\n%-15s %-8s %-6s %s", "----", "--", "---", "---");
    	 
    	 
    	 for (int i = 0; i < count; i++) 
    	 {
    		    Student s = students.get(i);
    		    System.out.printf("\n%-15s %-8s %-6s %s",s.name ,s.id , s.age , s.gpa);
    	 }
    	
    }
    
 // PURPOSE: Finds a student by ID or name.
 // HOW: Asks the user for search type, then
 // loops through the list to find a matching record.
    public static void Find()
    {
    	//input.nextLine();
    	
    	System.out.print("\n find with Id enter 1 or find with Name enter 2");
    	int option;
    	
    	option = input.nextInt();
    	
    	if (option == 1)
    	{
    		int ID;
    		ID = input.nextInt();
 
    		
    		// Loop through all students to find a matching ID
    		// Program returns immediately once a match is found
    		for (int i = 0; i < count; i++) 
       	 	{
       		    Student s = students.get(i);
       		    
       		    
        		if (s.id == ID )
        		{
        			
        			// Display full student details
        			System.out.println("\nStudent Found!!!");
                    System.out.println("Name: " + s.name);
                    System.out.println("ID: " + s.id);
                    System.out.println("Age: " + s.age);
                    System.out.println("GPA: " + s.gpa);
                   

                    return;  // Stops searching after match
                }
       	 	}
    	}
    	
    	
    	else if (option == 2)
    	{
    		String name = input.next();
    	
    		for (int i = 0; i < count; i++) 
   	 		{
    			Student s = students.get(i);
   		    
    			if (s.name.equalsIgnoreCase(name) )
    			{
    			
    				System.out.println("\nStudent Found!!!");
    				System.out.println("Name: " + s.name);
    				System.out.println("ID: " + s.id);
    				System.out.println("Age: " + s.age);
    				System.out.println("GPA: " + s.gpa);
            
    				return;
    			}
   	 		}
    	}
        System.out.println("Student not found.");
    }
    	
  
 // PURPOSE: Identifies students with highest and lowest GPA.
 // HOW: Iterates through the list to calculate GPA extremes
 // and then displays students matching those values.
    public static void GPAHL() 
    {  
    	// Start with reasonable GPA limits
    	double High = 0.0;
    	double lowest = 4.0;
    	
    	   if (count == 0) {
    	        System.out.println("No students available.");
    	        return;
    	    }
    	
    	   // Find highest and lowest GPA
    	    for (int i = 1; i < count; i++) {
    	        double gpa = students.get(i).gpa;

    	        if (gpa > High) {
    	            High = gpa;
    	        }
    	        if (gpa < lowest) {
    	            lowest = gpa;
    	        }
    	    }

        // Display students with highest GPA
        System.out.printf("\nStudent(s) with the highest GPA (%.2f):\n", High);
        for (int i = 0; i < count; i++)
        { 
        	Student s = students.get(i);
        	
            if (s.gpa == High)
            {
                System.out.println("Name: " + s.name + ", ID: " + s.id + ", Age: " + s.age);
            }
        }

        // Display students with lowest GPA
        System.out.printf("\nStudent(s) with the lowest GPA (%.2f):\n", lowest);
        for (int i = 0; i < count; i++) 
        {
        	Student s = students.get(i);
        	
            if (s.gpa == lowest) 
            {
                System.out.println("Name: " + s.name + ", ID: " + s.id + ", Age: " + s.age);
            }
        }
    }
    
 // PURPOSE: Counts students above a given age.
 // HOW: Compares each student’s age with a user-defined
 // threshold and increments a counter for matches.
    public static void AgeGreater()
    {
    	int num;
    	System.out.print("\nEnter age threshold:") ;
    	
    	num=input.nextInt();
    	
    	int greater = 0;
    	for (int i = 0; i < count; i++)
    	{
    		Student s = students.get(i);
    		if (s.age > num)
    		{
    			greater ++;
    		}
    	}
    	System.out.println("\nNumber of students older than "+ num + " are " + greater);
    	
    }
    
 // PURPOSE: Removes a student record from the system.
 // HOW: Searches by student ID, removes the matching object
 // from the ArrayList, and updates the student count.
    public static void Delete() 
    {
    	int x ;
    	System.out.print("Please enter the id of student to remove form list :");
    	
    	x = input.nextInt();
    	
    	for (int i = 0; i < count; i++) 
   	 	{
   		    Student s = students.get(i);
   		    
    		if (s.id == x)
    		{
    			System.out.println("\nStudent Found!!!");
                System.out.println("Name: " + s.name);
                System.out.println("ID: " + s.id);
                System.out.println("Age: " + s.age);
                System.out.println("GPA: " + s.gpa);
                
             // Removing by index avoids ConcurrentModificationException
             // Count is reduced to keep loops in sync
                students.remove(i);
                count --;
                System.out.println("\nStudent removed from list");
                
                return; // Exit once student is deleted
            }	
   	 	}  
    	System.out.println("Student not found");
    }
    
    
 // PURPOSE: Updates an existing student’s information.
 // HOW: Finds the student by ID, displays edit options,
 // and modifies the selected field directly.
    public static void Edit() 
    {
    	int x ;
    	System.out.print("Please enter the id of student to Edit details of form list :");
    	
    	x = input.nextInt();
    	
    	for (int i = 0; i < count; i++) 
   	 	{
   		    Student s = students.get(i);
   		    
    		if (s.id == x)
    		{
    			System.out.println("\nStudent Found!!!");
                System.out.println("Name: " + s.name);
                System.out.println("ID: " + s.id);
                System.out.println("Age: " + s.age);
                System.out.println("GPA: " + s.gpa + "\n");
                
                System.out.print("Options for Edit\n"
                		+ "1: For new Name\n "
                		+ "2: For new id \n"
                		+ "3: for new Age\n"
                		+ "4: for new Gpa\n"
                		+ "Enter Option:");
                int o;
                o=input.nextInt();
                
                // Edit name
                if (o==1) {
                System.out.println("Enter new Name :");
                String newname;
                newname = input.next();
                
             // Student object is edited directly in the ArrayList
             // No need to remove and reinsert the object
                s.name=newname;
                System.out.println("Name Successfully changed:");
                }
             // Edit id
                else if (o==2) {
                System.out.println("Enter new id :");
                int newid;
                newid = input.nextInt();
                s.id=newid;
                System.out.println("ID Successfully changed:");
                }
                
             // Edit Age
                else if (o==3) {
                System.out.println("Enter new Age :");
                int newAge;
                newAge = input.nextInt();
                s.age=newAge;
                System.out.println("Age Successfully changed:");
                }
                
             // Edit Gpa
                else if (o==4) {
                	
                System.out.println("Enter new Gpa:");
              double newGpa;
                newGpa = input.nextDouble();
                s.gpa=newGpa;
                System.out.println("GPA Successfully changed:");
                }
                else
                {
                	System.out.println("Wrong option");
                }
                return;
            }
   	 	}  
    	System.out.println("Student not found");
    }
    
 // ------------------ LOAD FROM FILE ------------------
 // PURPOSE: Loads previously saved student data.
 // HOW: Reads each line from the file, parses values,
 // and reconstructs Student objects at startup.
    public static void loadFromFile() {
    	
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // No file yet

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            	
            	// Each line represents one student record
            	// Data is split using comma delimiter
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                double gpa = Double.parseDouble(parts[3]);
             // Reconstruct student object from stored values
                students.add(new Student(name, id, age, gpa));
                
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
    
    
 // PURPOSE: Saves current student data to a file.
 // HOW: Writes all student records in a comma-separated
 // format to allow easy reloading later.
    public static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new File(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.id + "," + s.name + "," + s.age + "," + s.gpa);
            }
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    
}
