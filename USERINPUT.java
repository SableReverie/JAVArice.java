import java.util.Scanner;
public class Main{
    public static void main(String[] args){
       
     int age = 0;
     age += 1;
     
     System.out.println("What's your age: ");
     Scanner scanner = new Scanner(System.in);
     age = scanner.nextInt();
     scanner.close();
     
     System.out.println("My age is:: " + age);
        
    }
}
