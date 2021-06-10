package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarService carService = context.getBean(CarService.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("UserE", "LastnameE", "user1@mail.ru",
                carService.add(new Car("Mercedes", 124))));
        userService.add(new User("UserS", "LastnameS", "user2@mail.ru",
                carService.add(new Car("Mercedes", 140))));
        userService.add(new User("UserG", "LastnameG", "user3@mail.ru",
                carService.add(new Car("Volvo", 90))));
        userService.add(new User("UserV", "LastnameV", "user4@mail.ru",
                carService.add(new Car("Volkswagen_golf", 2))));

        List<User> userList = userService.listUsers();
        for (User u : userList) {
            System.out.println(u.toString());
        }

        System.out.println("User with Mercedes 140");
        User user = userService.findOwner("Mercedes", 140);
        System.out.println(user.toString());

        context.close();
    }
}