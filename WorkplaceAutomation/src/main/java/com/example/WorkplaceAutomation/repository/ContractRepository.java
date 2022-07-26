package com.example.WorkplaceAutomation.repository;

import com.example.WorkplaceAutomation.entity.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepository {

    private final SessionFactory sessionFactory;

    public ContractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdateContract(Contract contract) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(contract);

        session.close();
        session.getTransaction().commit();
    }

    public Contract getContractById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(session.createQuery("from Person").getResultList());

        Contract person =  (Contract) session.get(Contract.class, id);

        session.close();
        session.getTransaction().commit();
        return person;
    }

    // Возврат всех одобренных заявок
    public List<Contract> getAllApproved() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Contract> result = session.createQuery("from Contract where isApproved = true", Contract.class).getResultList();

        session.close();
        session.getTransaction().commit();
        return result;
    }

    // Возвращает все неподписанные договоры и неодобренные заявки
    public List<Contract> getAllProposals() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Contract> result = session.createQuery("from Contract where isSigned = false or isApproved = false", Contract.class).getResultList();

        session.close();
        session.getTransaction().commit();
        return result;
    }
}
