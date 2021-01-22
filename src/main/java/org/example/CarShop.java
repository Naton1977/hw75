package org.example;

import org.example.domain.composite.Menu;
import org.example.domain.composite.Methods;
import org.example.domain.entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CarShop {
    Methods methods;

    public CarShop() {
        methods = new Methods();
    }

    public void createCarShop() throws IOException, SQLException {
        Menu menu = new Menu("Главное меню");

        Menu menu1 = new Menu("Добавить категорию автомобиля", context -> {
            methods.addNewCategory();
        });

        Menu menu2 = new Menu("Редактировать категорию автомобиля", context -> {
            methods.updateCategory();
        });
        Menu menu3 = new Menu("Посмотреть список всех категорий", context -> {
            List<Category> categories = methods.existingCategories();
            for (Category cat : categories) {
                System.out.println(cat.getName());
            }
        });
        Menu menu4 = new Menu("Добавить автомобиль в базу", context -> {
            methods.addNewCar();
        });
        Menu menu5 = new Menu("Удалить автомобиль из базы", context -> {
            methods.deleteCar();
        });
        Menu menu6 = new Menu("Редактировать данные автомобиля", context -> {
            methods.changeCar();
        });
        Menu menu7 = new Menu("Продать автомобиль", context -> {
            methods.sellCar();
        });
        Menu menu8 = new Menu("Посмотреть список проданных автомобилей", context -> {
            methods.soldCars();
        });
        Menu menu9 = new Menu("Посмотреть список не проданных автомобилей", context -> {
            methods.notSold();
        });


        menu.addSubMenu(menu1);
        menu.addSubMenu(menu2);
        menu.addSubMenu(menu3);
        menu.addSubMenu(menu4);
        menu.addSubMenu(menu5);
        menu.addSubMenu(menu6);
        menu.addSubMenu(menu7);
        menu.addSubMenu(menu8);
        menu.addSubMenu(menu9);


        do {
            menu.print();
        } while (!menu.action());


    }
}
