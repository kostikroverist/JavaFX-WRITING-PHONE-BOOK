/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;
import java.sql. *;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class DBConnection {
    
    Connection connection;
     void create_or_connection(){
         
        try{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(
        "jdbc:sqlite:users.db");
        
           Statement statement = connection.createStatement();
statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' text);");
System.out.println("Table was created or already used.");

System.out.println("connected");
        
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        
      } 
     
    void insert_to_database(){
          
          try{
        Scanner in = new Scanner(System.in);
    System.out.print("Input a name: ");       
      String name = in.nextLine();;
    System.out.print("Input a phone: ");       
          String phone=  in.nextLine();;
System.out.printf("Added = Name: %s \tPhone: %s\n", name, phone);
           String query = 
                   "INSERT INTO users (name, phone)  VALUES(' "+name +"' , ' "+phone +"') ";
         
                Statement statement = connection.createStatement();
              statement.executeUpdate (query);
              System.out.println("rows added");
              statement.close();
      
          }catch(Exception e){
          System.out.println(e.getMessage());
          }
     
      }
    void select_database(){
      
        try{
            
     Statement statement = connection.createStatement();
     String query = "SELECT id, name, phone FROM users " ;
    
      ResultSet rs =  statement.executeQuery(query);
  
             while(rs.next())
             {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    
                    System.out.println(id +"\t |  "+ name +" \t | "+ phone);
            }
  
             rs.close();
              statement.close();
              
          }catch(Exception e){
          System.out.println(e.getMessage());
          }
      
      }
   void delete_from_database(){
        try{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(
        "jdbc:sqlite:users.db");
        
         System.out.println("connected");
         
           Statement statement = connection.createStatement();
            Scanner in = new Scanner(System.in);
    System.out.print("Input a name: ");       
      String name = in.nextLine();;

String sqldelete =" DELETE FROM users WHERE id =  "+name +"";
            statement.executeUpdate(sqldelete);
System.out.printf("Deleted. = Id: %s ", name);

        }catch(Exception e){
        System.out.println(e.getMessage());
        }
      }


}
