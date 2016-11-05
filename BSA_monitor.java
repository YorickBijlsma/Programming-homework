package single_file_bsa_monitor;
import java.util.Scanner;

/**
 *
 * @author Yorick
 * 
 * author note:
 * originally intended to have a separate .java file for the Course class,
 * but the hand-in mechanism requires you to submit a single .java file.
 * This makes the file a bit messy.
 * 
 * This program asks grades for courses, and tells you if you're on the path of
 * being allowed to continue your education, and with what grades and study points.
 */

class Course
{           
    Course(String _courseName,int _maxStudyPoints)
    {
        courseName = _courseName;
        maxStudyPoints = _maxStudyPoints;
    }

    String courseName;
    int maxStudyPoints;
    int obtainedStudyPoints = 0;
    double courseGrade;

    Scanner sc = new Scanner(System.in);

    int computeStudyPoints()
    {
        System.out.println("\nVoer je cijfer voor "+courseName+" hier in: ");
        courseGrade = sc.nextDouble();

        if (courseGrade >= 5.5)
        {
            obtainedStudyPoints = maxStudyPoints;
        }
        else
        {
            obtainedStudyPoints = 0;
        }
        
        return obtainedStudyPoints;
    }
}

public class Single_file_BSA_Monitor
{
    public static void main(String[] args)
    {
        int obtainedStudyPoints = 0;
        int availableStudyPoints = 0;
        
        /*
        * 5/6 of all available study points are required to pass
        * 5/6 of x is equal to (x * 0.833...), so we define a constant to use in formula
        */
        final double FIVE_SIXTH = 0.833;
        
        Course projectFYS;
        Course programming;
        Course databases;
        Course personalSkills;
        Course projectSkills;
        Course gameBasics;
        Course gameProgramming;
        Course userInteraction;
        
        //array to compute study points for all courses
        Course[] courses = {
                            projectFYS = new Course("Fasten Your Seatbelts",12),
                            programming = new Course("Programming",3),
                            databases = new Course("Databases",3),
                            personalSkills = new Course("Personal Skills",2),
                            projectSkills = new Course("Project Skills",2),
                            gameBasics = new Course("Game Basics",3),
                            gameProgramming = new Course("Game Programming",3),
                            userInteraction = new Course("User interaction",3)
                           };
        
        //calculate the study points you get for every course according to course grade
        //add obtained points to total study points
        int coursePoints;
        for (Course currentCourse : courses)
        {
            availableStudyPoints += currentCourse.maxStudyPoints;
            coursePoints = currentCourse.computeStudyPoints();
            obtainedStudyPoints += coursePoints;
        }              
        
        //how many study points are needed to pass
        int neededPoints = (int) Math.floor(FIVE_SIXTH *
                                            availableStudyPoints
                                            );
        
        //trickery to save a print statement
        String BSA = new String();
        if (obtainedStudyPoints >= neededPoints){
            BSA = "positief";
        }else{
            BSA = "negatief";}
        
        for (Course currentCourse : courses)
        {
            //show the user its grades
            System.out.println("\n"+
                                "Vak/project: "+
                               currentCourse.courseName+
                               "\r\nCijfer: \t"+currentCourse.courseGrade+
                               "\r\nStudiepunten:    "+currentCourse.obtainedStudyPoints                             
                              );
        }
        
        //give feedback to user in nice format
        System.out.println("\n\n***********************************************************\n*\t"+
                           "Je bent op koers voor een "+BSA+" BSA.           *\n"+
                           "*\tJe hebt op het moment "+obtainedStudyPoints+
                           " van de "+availableStudyPoints+" studiepunten.  "+
                           "*\n***********************************************************");
    }
}
