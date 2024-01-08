package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class MainApp {

   @Autowired
   private UserDao userDao;

   @Autowired
   private UserService userService;

   @Autowired
   private CarService carService;

   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      MainApp mainApp = context.getBean(MainApp.class);
      mainApp.run();
   }

   private void run() {

      List<User> existingUsers = userService.listUsers();
      if (existingUsers.isEmpty()) {

         Car car1 = new Car("Coupe", 2);
         Car car2 = new Car("GranCoupe", 4);
         Car car3 = new Car("gt", 5);
         carService.add(car1);
         carService.add(car2);
         carService.add(car3);

         User user1 = new User("User1", "Lastname1", "user1@mail.ru");
         user1.setCar(car1);
         userService.add(user1);

         User user2 = new User("User2", "Lastname2", "user2@mail.ru");
         user2.setCar(car2);
         userService.add(user2);

         User user3 = new User("User3", "Lastname3", "user3@mail.ru");
         user3.setCar(car3);
         userService.add(user3);


         userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
         userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
         userService.add(new User("User6", "Lastname6", "user6@mail.ru"));
         userService.add(new User("User7", "Lastname7", "user7@mail.ru"));

         existingUsers = userService.listUsers(); // Обновляем список существующих пользователей после их добавления
      }


      List<User> users = userService.listUsers();
         for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("car = " + user.getCar());

         }

         List<User> users2 = userService.getUserByModelSeries("gt", 5);
         for (User user : users2) {
            System.out.println();
            System.out.println(user.getCar());
            System.out.println("Пользователь с id: " + user.getId());
         }

      }
   }




