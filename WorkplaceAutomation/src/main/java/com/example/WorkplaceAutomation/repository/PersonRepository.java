package com.example.WorkplaceAutomation.repository;

import com.example.WorkplaceAutomation.entity.Person;
import com.example.WorkplaceAutomation.entity.WorkPeriod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private final SessionFactory sessionFactory;

    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdatePerson(Person person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (WorkPeriod period: person.getWorkPeriod()) {
            period.setPerson(person);
            session.saveOrUpdate(period);
        }
        session.saveOrUpdate(person);

        session.getTransaction().commit();
        session.close();
    }

    // Поиск пользователя по точному совпадению серии/номера папсспорта
    public Person getPersonById(String seriesNumber) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Person person =  (Person) session.get(Person.class, seriesNumber);

        session.close();
        session.getTransaction().commit();
        return person;
    }

    // Возвращает всех пользователей
    public List<Person> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Person> result = session.createQuery("from Person", Person.class).getResultList();

        session.close();
        session.getTransaction().commit();
        return result;
    }

    // Поиск пользователей по ФИО. ФИО - строка, её части разделены пробелами, порядок любой
    public List<Person> findByName(String name) {
        String[] splitedName = name.split("\\s+");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Person> result = new ArrayList<>();
        for (String splited : splitedName) {
            result.addAll(
                    session.createQuery(
                            "from Person where lower(firstName) like lower(:param) or " +
                                    "lower(lastName) like lower(:param) or lower(patronym) like lower(:param)",
                            Person.class)
                    .setParameter("param", "%" + splited + "%")
                    .getResultList()
            );
        }

        session.close();
        session.getTransaction().commit();
        return result;
    }

    // Поиск пользователей, в чей номер телефона входит заданная строка
    public List<Person> findByContactNumber(String contactNumber) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Person> result = session.createQuery("from Person where contactNumber like :contactNumber", Person.class)
                            .setParameter("contactNumber", "%" + contactNumber + "%")
                            .getResultList();

        session.close();
        session.getTransaction().commit();
        return result;
    }

    // Поиск пользователей, в чью серию/номер пасспорта входит заданная строка
    public List<Person> findBySeriesNumber(String seriesNumber) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Person> result = session.createQuery("from Person where seriesNumber like :seriesNumber", Person.class)
                .setParameter("seriesNumber", "%" + seriesNumber + "%")
                .getResultList();

        session.close();
        session.getTransaction().commit();
        return result;
    }
}
