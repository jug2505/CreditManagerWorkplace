package com.example.WorkplaceAutomation.controller;

import com.example.WorkplaceAutomation.entity.Contract;
import com.example.WorkplaceAutomation.entity.Person;
import com.example.WorkplaceAutomation.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api")
public class ApiController {
    private final ContractService contractService;

    public ApiController(ContractService contractService) {
        this.contractService = contractService;
    }

    // Создание контракта для пользователя с желаемой суммой кредита
    @PostMapping("/proposal")
    public Contract createProposal(@RequestBody Person person, @RequestParam BigDecimal wishedSum) {
        return contractService.generateContract(person, wishedSum);
    }

    // Возвращает все неподписанные договоры и неодобренные заявки
    @GetMapping("/proposal")
    public List<Contract> getAllProposals() {
        return contractService.getAllProposals();
    }

    @GetMapping("/contract")
    public List<Contract> getAllContracts() {
        return contractService.getAllApprovedContracts();
    }

    // Подпись контракта по его id
    @PatchMapping("/contract/{id}/sign")
    public ResponseEntity<Contract> signContract(@PathVariable Long id) {
        Contract contract = contractService.signContract(id);
        if (contract == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    // Возврат всех клиентов
    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return contractService.getAllPersons();
    }

    // Поиск пользователей по ФИО. ФИО - строка, её части разделены пробелами, порядок любой
    @GetMapping("/person/name/{name}")
    public List<Person> getPersonsByName(@PathVariable String name) {
        return contractService.getAllPersonsByName(name);
    }

    // Поиск пользователей, в чью серию/номер пасспорта входит заданная строка
    @GetMapping("/person/id/{seriesNumber}")
    public List<Person> getPersonsBySeriesNumber(@PathVariable String seriesNumber) {
        return contractService.getPersonsBySeriesNumber(seriesNumber);
    }

    // Поиск пользователей, в чей номер телефона входит заданная строка
    @GetMapping("/person/number/{contactNumber}")
    public List<Person> getPersonsByContactNumber(@PathVariable String contactNumber) {
        return contractService.getPersonsByContactNumber(contactNumber);
    }
}
