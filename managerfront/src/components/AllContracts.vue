<template>
  <div class="container">
    <div v-for="contract in contracts" :key="contract.id" class="proposal">
      <div class="prop">Заявка № {{contract.id}}</div>
      <div class="header">Информация о клиенте</div>
      <div>Серия/номер пасспорта: {{contract.person.seriesNumber}}</div>
      <div>Дата выдачи пасспорта: {{contract.person.passportDate}}</div>
      <div>Орган, выдавший пасспорт: {{contract.person.passportOrganName}}</div>
      <div>Код органа, выдавшего пасспорт: {{contract.person.passportOrganCode}}</div>
      <div>Имя: {{contract.person.firstName}}</div>
      <div>Фамилия: {{contract.person.lastName}}</div>
      <div v-if="contract.person.patronym != ''">Отчество: {{contract.person.patronym}}</div>
      <div>Телефон: {{contract.person.contactNumber}}</div>
      <div>Дата рождения: {{contract.person.birthDate}}</div>
      <div v-if="contract.person.married == true">Семейное положение: Женат / жената</div>
      <div v-if="contract.person.married == false">Семейное положение: Не женат / не жената</div>
      <div>Адрес: {{contract.person.address}}</div>
      <div>Контактный телефон: {{contract.person.contactNumber}}</div>

      <div class="header"> Информация о работе</div>
      <div v-for="work in contract.person.workPeriod" :key="work.startDate">
          <div>Должность: {{work.jobPosition}}</div>
          <div>Организация: {{work.organisationName}}</div>
          <div>Период: с {{work.startDate}} по {{work.endDate}}</div>
      </div>

      <div class="header">Информация о заявке</div>
      <div>Желаемая сумма: {{contract.wishedSum}}</div>
      <div>Одобренная сумма : {{contract.approvedSum}}</div>
      <div>На срок: {{contract.creditTerm}} дней</div>
      <div>Статус подписи: 
        <span v-if="contract.signed == true"> Подписан {{contract.signDate}}</span>
        <span v-if="contract.signed == false"> Не подписан</span>
      </div>
    

    </div>

  </div>
</template>

<script>
import axios from 'axios'

let API_URL = 'http://localhost:8081'

export default {
  name: 'AllContracts',
  data() {
    return {
      contracts: []
    }

  },
  created() {
    this.getAllContracts()
  },

  methods:{
    getAllContracts(){
      axios
        .get(API_URL + "/api/contract")
        .then(
          (response) => {
            this.contracts = response.data
          })
        .catch((error) => { console.log(error) })
    }
  }
}
</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .proposal {
    text-align: left;
    margin-bottom: 15px;
  }
  .header {
    font-style: italic;
    margin-bottom: 5px;
  }
  .prop {
    font-weight: bold;
    margin-bottom: 10px;
  }
  

</style>

