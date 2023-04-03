package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car audi = new Car("AUDI", 6);
      Car ford = new Car("FORD", 3);
      Car bmw = new Car("BMW", 7);
      Car opel = new Car("OPEL", 1);

      carService.add(audi);
      carService.add(ford);
      carService.add(bmw);
      carService.add(opel);



      userService.add(new User("Bob", "Larson", "user1@mail.ru",audi));
      userService.add(new User("Jon", "Hamish", "user2@mail.ru",ford));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",bmw));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",opel));

      System.out.println("//----------------------------------------//");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar());
         System.out.println();
      }
      System.out.println("//-----------------------------------------//");

      User user = userService.getUserHasCarByModelAndSeries("FORD", 3);
      if(user == null){
         System.out.println("Владелец машины не найден ");
      } else {
         System.out.println();
         System.out.println("Владелец машины: " + user.getCar() + " - " + user.getFirstName() +
                 " " + user.getLastName());
      }
      context.close();

   }

}
