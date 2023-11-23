package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car vaz = new Car("Samara", 21014);
      Car toyota = new Car("Camry", 30);
      Car volkswagen = new Car("Golf", 7);
      Car mitsubishi = new Car("Lanser", 9);

      userService.add(user1.setCar(vaz).setUser(user1));
      userService.add(user2.setCar(toyota).setUser(user2));
      userService.add(user3.setCar(volkswagen).setUser(user3));
      userService.add(user4.setCar(mitsubishi).setUser(user4));


      // пользователи с авто
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + " "  + user.getCar());
      }
      System.out.println("                                                                                           ");

      // по модели авто
      System.out.println(userService.getUserCar("Lanser", 9));
      System.out.println("                                                                                           ");

      try {
         System.out.println(userService.getUserCar("Lenser", 9));
      }catch (NoResultException e){
         System.out.println("отсутсвует");
      }

      context.close();
   }
}
