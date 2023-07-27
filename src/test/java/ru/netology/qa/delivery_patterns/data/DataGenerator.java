package ru.netology.qa.delivery_patterns.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }

    public static String generateCity() {
        var cities = new String[]{"Майкоп", "Горно-Алтайск", "Барнаул", "Благовещенск", "Архангельск", "Астрахань",
                "Уфа", "Белгород", "Брянск", "Улан-Удэ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала", "Биробиджан", "Чита", "Иваново",
                "Магас", "Иркутск", "Нальчик", "Калининград", "Элиста", "Калуга", "Петропавловск-Камчатский", "Черкесск", "Петрозаводск", "Кемерово", "Киров", "Сыктывкар", "Кострома",
                "Краснодар", "Красноярск", "Симферополь", "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Йошкар-Ола",
                "Саранск", "Москва", "Мурманск", "Нарьян-Мар", "Нижний Новгород", "Великий Новгород", "Новосибирск",
                "Омск", "Оренбург", "Орёл", "Пенза", "Пермь", "Владивосток", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Санкт-Петербург", "Саратов",
                "Якутск", "Южно-Сахалинск", "Екатеринбург", "Севастополь", "Владикавказ", "Смоленск", "Ставрополь",
                "Тамбов", "Казань", "Тверь", "Томск", "Тула", "Кызыл", "Тюмень", "Ижевск", "Ульяновск", "Хабаровск",
                "Абакан", "Ханты-Мансийск", "Челябинск", "Грозный", "Чебоксары", "Анадырь", "Салехард", "Ярославль"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        String randomName = (faker.name().lastName() + " " + faker.name().firstName());
        return randomName.replace('ё', 'е');
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}