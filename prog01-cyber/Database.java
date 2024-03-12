import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

import java.io.FileNotFoundException;
import java.io.File;

/**
 * Creates a database class
 */
public class Database {

	Hasher hasher = null;

	private ArrayList<Pair> users = new ArrayList<>();
	private ArrayList<Student> students = new ArrayList<>();
	
	/**
	 * Constructor 
	 * creates a database
	 * @param h 
	 */
	public Database(Hasher h) {
		hasher = h;
		loadUsers();
		loadStudents();
	}
	
	/**
	 * A method that checks if the username and password are valid
	 * @param username the students username 
	 * @param password the students password
	 * @return if it is true or not 
	 */
	public boolean valid(String username, String password) {
		for(Pair s : users){
			if(s.get(1).equals(username) && hasher.hash(password).equals(s.get(2))){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A method to opens the user files and load the contents into the user Arraylist
	 */
	public void loadUsers() {
		try { 
			File myUserFile = new File("users.txt");
			Scanner myScanner = new Scanner(myUserFile);
			while (myScanner.hasNextLine()) {
				String userData = myScanner.nextLine();
				String[] pair = userData.split(",");
				Pair p = new Pair(pair[0], pair[1]);
				users.add(p);
			}
			myScanner.close();
		}	catch (FileNotFoundException error){
			System.out.println("An error occurred.");
			error.printStackTrace();
		}	
    }
	
	/*
	 * A method to open the students files and load the contents in it
	 */
	public void loadStudents() {
		try{
			File myStudentflie = new File("students.csv");
			Scanner myScanner = new Scanner(myStudentflie);
			while(myScanner.hasNextLine()){
				String studentData = myScanner.nextLine();
				String[] studentsPar = studentData.split(",");
				LinkedList<String> myCourses = new LinkedList<String>();
				for(int i = 3; i < studentsPar.length; i ++){
					myCourses.add(studentsPar[i]);
				}
				Student temp = new Student(studentsPar[0], studentsPar[1],studentsPar[2],myCourses);
				students.add(temp);
			}
			myScanner.close();
		}catch (FileNotFoundException error){
			System.out.println("An error occurred.");
			error.printStackTrace();
		}	
	}

	/**
	 * a getter to get the given students name 
	 * @param user
	 * @return
	 */
	public Student getStudent(String user) {
		for(Student s: students){
			if(user.equals(s.username())){
				return s;
			}
		}
		return null;
	}
}
