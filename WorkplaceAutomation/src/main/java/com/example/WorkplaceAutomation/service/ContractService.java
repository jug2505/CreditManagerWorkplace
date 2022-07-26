package com.example.WorkplaceAutomation.service;

import com.example.WorkplaceAutomation.entity.Contract;
import com.example.WorkplaceAutomation.entity.Person;
import com.example.WorkplaceAutomation.repository.ContractRepository;
import com.example.WorkplaceAutomation.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class ContractService {
    private final PersonRepository personRepository;
    private final ContractRepository contractRepository;

    public ContractService(PersonRepository personRepository, ContractRepository contractRepository) {
        this.personRepository = personRepository;
        this.contractRepository = contractRepository;
    }

    // Создание контракта. Принятие решения о кредите происходит случайно.
    // Пользователь и контракт сохраняются/обновляются в БД
    public Contract generateContract(Person person, BigDecimal wishedSum) {
        Random random = new Random();
        Contract contract = new Contract();

        if (person.getWorkPeriod().isEmpty()) person.setWorkPeriod(null);
        contract.setPerson(person);
        contract.setWishedSum(wishedSum);
        contract.setApproved(random.nextBoolean());

        if (contract.getApproved()) {
            contract.setApprovedSum(new BigDecimal(Math.abs(random.nextInt())));
            contract.setCreditTerm((short) ((Math.abs(random.nextInt()) % 335) + 30));
            contract.setSigned(false);
        }

        personRepository.saveOrUpdatePerson(person);
        contractRepository.saveOrUpdateContract(contract);

        return contract;
    }

    // Установка поля isSigned в true. Сохрание даты подписи
    public Contract signContract(Long id) {
        Contract contract = contractRepository.getContractById(id);
        if (contract == null) return null;

        contract.setSigned(true);
        contract.setSignDate(new Date(Calendar.getInstance().getTime().getTime()));
        contractRepository.saveOrUpdateContract(contract);

        return contract;
    }

    // Возврат всех одобренных заявок
    public List<Contract> getAllApprovedContracts() {
        return contractRepository.getAllApproved();
    }

    // Возврат всех клиентов
    public List<Person> getAllPersons() {
        return personRepository.getAll();
    }

    // Возврат всех неподписанных контрактов и неодобренных заявок
    public List<Contract> getAllProposals() {
        return contractRepository.getAllProposals();
    }

    // Поиск пользователей по ФИО
    public List<Person> getAllPersonsByName(String name) {
        if (name == null) return new ArrayList<>();
        return personRepository.findByName(name);
    }

    // Поиск пользователей по телефону
    public List<Person> getPersonsByContactNumber(String contactNumber) {
        return personRepository.findByContactNumber(contactNumber);
    }

    // Поиск пользователей по серии/номеру пасспорта
    public List<Person> getPersonsBySeriesNumber(String seriesNumber) {
        return personRepository.findBySeriesNumber(seriesNumber);
    }
}
