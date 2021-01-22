package org.example.domain.composite;

import org.example.domain.context.CreateContext;
import org.example.domain.dao.CarDao;
import org.example.domain.dao.CategoryDao;
import org.example.domain.entity.Car;
import org.example.domain.entity.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Methods {
    private CreateContext createContext;
    private CarDao carDao;
    private CategoryDao categoryDao;
    private Scanner scanner;

    public Methods() {
        createContext = CreateContext.getInstance();
        carDao = createContext.returnContext().getBean(CarDao.class);
        categoryDao = createContext.returnContext().getBean(CategoryDao.class);
        scanner = new Scanner(System.in);
        initDataBase();
    }

    public void addNewCategory() {
        List<Category> categories = existingCategories();
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
        System.out.println("Введите название новой категории");
        String newCategory = scanner.nextLine();
        Category category = new Category();
        category.setName(newCategory);
        categoryDao.save(category);

    }

    public void updateCategory() {
        boolean breakMethod = false;
        List<Category> categories = existingCategories();
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
        do {
            System.out.println("Введите название категории которую нужно обновить");
            String deletedCategory = scanner.nextLine();
            for (Category cat : categories) {
                if (cat.getName().equals(deletedCategory)) {
                    System.out.println("Введите новое название категории");
                    String newName = scanner.nextLine();
                    cat.setName(newName);
                    breakMethod = false;
                    categoryDao.update(cat);
                    break;

                } else {
                    breakMethod = true;
                }
            }
            if (breakMethod) {
                System.out.println("Введите название категории правильно !!!");
            }
        } while (breakMethod);
    }

    public void addNewCar() {
        boolean presentCategory = false;
        BigDecimal bigDecimal;
        Car car = new Car();
        System.out.println("Введите название нового автомобиля");
        String newCarName = scanner.nextLine();

        do {
            System.out.println("Введите цену");
            String price = scanner.nextLine();
            try {
                bigDecimal = new BigDecimal(price);
                break;
            } catch (Exception e) {
                System.out.println("Введите цену правильно");
            }
        } while (true);
        System.out.println("Введите название категории автомобиля ");
        System.out.println("(быверите из существующей категории или введите новую, она сохранится автоматически)");
        List<Category> categories = existingCategories();
        System.out.println("Существующие категории");
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
        String category = scanner.nextLine();
        for (Category cat : categories) {
            if (cat.getName().equals(category)) {
                car.setModel(newCarName);
                car.setPrice(bigDecimal);
                car.setSold(false);
                car.setCategory(cat);
                carDao.save(car);
                presentCategory = true;
                break;
            }
        }

        if (!presentCategory) {
            car.setModel(newCarName);
            car.setPrice(bigDecimal);
            car.setSold(false);
            Category category1 = new Category();
            category1.setName(category);
            car.setCategory(category1);
            categoryDao.save(category1);
            carDao.save(car);
        }
    }


    public List<Category> existingCategories() {
        System.out.println("Доступные категории");
        List<Category> categories = categoryDao.findAll();
        return categories;
    }

    public void deleteCar() {
        System.out.println("Автомобили имеющиеся в базе");
        String sales;
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            if (car.isSold()) {
                sales = "Продано";
            } else {
                sales = "Не продано";
            }
            System.out.printf("Модель :  %15s| Цена : %15f|%11s| Категория : %10s%n", car.getModel(), car.getPrice(), sales, car.getCategory().getName());
        }
        System.out.println("Введите модель которую нужно удалить");
        String model = scanner.nextLine();
        for (Car car : cars) {
            if (car.getModel().equals(model)) {
                addNewCar();
            }
        }
    }

    public void changeCar() {
        boolean presentCategory = false;
        BigDecimal bigDecimal;
        System.out.println("Автомобили имеющиеся в базе");
        String sales;
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            if (car.isSold()) {
                sales = "Продано";
            } else {
                sales = "Не продано";
            }
            System.out.printf("Модель :  %15s| Цена : %15f|%11s| Категория : %10s%n", car.getModel(), car.getPrice(), sales, car.getCategory().getName());
        }
        System.out.println("Введите модель которую нужно редактировать");
        String model = scanner.nextLine();
        for (Car car : cars) {
            if (car.getModel().equals(model)) {
                System.out.println("Введите новое название автомобиля");
                String newCarName = scanner.nextLine();

                do {
                    System.out.println("Введите цену");
                    String price = scanner.nextLine();
                    try {
                        bigDecimal = new BigDecimal(price);
                        break;
                    } catch (Exception e) {
                        System.out.println("Введите цену правильно");
                    }
                } while (true);

                System.out.println("Введите продан автомобиль или нет");
                System.out.println("1 - продан");
                System.out.println("2 - нет");
                String sale = scanner.nextLine();
                if (sale.equals("1")) {
                    car.setSold(true);
                }
                if (sale.equals("2")) {
                    car.setSold(false);
                }
                System.out.println("Введите название категории автомобиля ");
                System.out.println("(быверите из существующей категории или введите новую, она сохранится автоматически)");
                List<Category> categories = existingCategories();
                System.out.println("Существующие категории");
                for (Category cat : categories) {
                    System.out.println(cat.getName());
                }
                String category = scanner.nextLine();
                for (Category cat : categories) {
                    if (cat.getName().equals(category)) {
                        car.setModel(newCarName);
                        car.setPrice(bigDecimal);
                        ;
                        car.setCategory(cat);
                        carDao.update(car);
                        presentCategory = true;
                        break;
                    }
                }

                if (!presentCategory) {
                    car.setModel(newCarName);
                    car.setPrice(bigDecimal);
                    Category category1 = new Category();
                    category1.setName(category);
                    car.setCategory(category1);
                    categoryDao.save(category1);
                    carDao.update(car);
                }
            }
        }
    }

    public void notSold() {
        System.out.println("Не проданные автомобили");
        String sales;
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            if (!car.isSold()) {
                sales = "Не продано";
                System.out.printf("Модель :  %15s| Цена : %15f|%11s| Категория : %10s%n", car.getModel(), car.getPrice(), sales, car.getCategory().getName());
            }
        }
    }

    public void soldCars() {
        System.out.println("Проданные автомобили");
        String sales;
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            if (car.isSold()) {
                sales = "Продано";
                System.out.printf("Модель :  %15s| Цена : %15f|%11s| Категория : %10s%n", car.getModel(), car.getPrice(), sales, car.getCategory().getName());
            }
        }
    }

    public void sellCar() {
        notSold();
        System.out.println("Ведите марку автомобиля который нужно продать");
        String carModel = scanner.nextLine();
        List<Car> cars = carDao.findAll();
        for (Car car : cars) {
            if (car.getModel().equals(carModel)) {
                car.setSold(true);
                carDao.update(car);
            }
        }
    }


    public void initDataBase() {
        List<Car> cars = carDao.findAll();
        if (cars.size() == 0) {
            BigDecimal bigDecimal = new BigDecimal("100000");
            Car car = new Car("Mercedes S600", bigDecimal, true);
            Category category = new Category("Джип");
            car.setCategory(category);
            cars.add(car);

            BigDecimal bigDecimal1 = new BigDecimal("50000");
            Car car1 = new Car("Toyota Camry", bigDecimal1, false);
            Category category1 = new Category("Кабриолет");
            car1.setCategory(category1);
            cars.add(car1);

            BigDecimal bigDecimal2 = new BigDecimal("30000");
            Car car2 = new Car("Opel Vector", bigDecimal2, true);
            Category category2 = new Category("Купе");
            car2.setCategory(category2);
            cars.add(car2);


            BigDecimal bigDecimal3 = new BigDecimal("110000");
            Car car3 = new Car("Range Rower", bigDecimal3, false);
            Category category3 = new Category("Кроссовер");
            car3.setCategory(category3);
            cars.add(car3);


            BigDecimal bigDecimal4 = new BigDecimal("5000");
            Car car4 = new Car("Lada Balkan", bigDecimal4, true);
            Category category4 = new Category("Купе");
            car4.setCategory(category4);
            cars.add(car4);

        }
        for (Car cr : cars) {
            categoryDao.save(cr.getCategory());
            List<Category> categories = categoryDao.findAll();
            for (Category cat : categories) {
                if (cat.getName().equals(cr.getCategory().getName())) {
                    cr.setCategory(cat);
                    carDao.save(cr);
                }
            }
        }
    }

}
