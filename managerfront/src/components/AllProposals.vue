<template>
  <div class="container">
    <div v-for="proposal in proposals" :key="proposal.id" class="proposal">
      <div class="prop">Заявка № {{proposal.id}}</div>
      <div class="header">Информация о клиенте</div>
      <div>Серия/номер пасспорта: {{proposal.person.seriesNumber}}</div>
      <div>Дата выдачи пасспорта: {{proposal.person.passportDate}}</div>
      <div>Орган, выдавший пасспорт: {{proposal.person.passportOrganName}}</div>
      <div>Код органа, выдавшего пасспорт: {{proposal.person.passportOrganCode}}</div>
      <div>Имя: {{proposal.person.firstName}}</div>
      <div>Фамилия: {{proposal.person.lastName}}</div>
      <div v-if="proposal.person.patronym != ''">Отчество: {{proposal.person.patronym}}</div>
      <div>Телефон: {{proposal.person.contactNumber}}</div>
      <div>Дата рождения: {{proposal.person.birthDate}}</div>
      <div v-if="proposal.person.married == true">Семейное положение: Женат / жената</div>
      <div v-if="proposal.person.married == false">Семейное положение: Не женат / не жената</div>
      <div>Адрес: {{proposal.person.address}}</div>
      <div>Контактный телефон: {{proposal.person.contactNumber}}</div>

      <div class="header"> Информация о работе</div>
      <div v-for="work in proposal.person.workPeriod" :key="work.startDate">
          <div>Должность: {{work.jobPosition}}</div>
          <div>Организация: {{work.organisationName}}</div>
          <div>Период: с {{work.startDate}} по {{work.endDate}}</div>
      </div>

      <div class="header">Информация о заявке</div>
      <div>Желаемая сумма: {{proposal.wishedSum}}</div>
      
      <div v-if="proposal.approved == true">
        <div>Статус одобрения: Одобрен</div>
        <div>Одобренная сумма : {{proposal.approvedSum}}</div>
        <div>На срок: {{proposal.creditTerm}} дней</div>
      </div>
      <div v-if="proposal.approved == false">Статус одобрения: Не одобрен</div>

    </div>

  </div>
</template>

<script>
import axios from 'axios'

let API_URL = 'http://localhost:8081'

export default {
  name: 'AllProposals',
  data() {
    return {
      proposals: []
    }

  },
  created() {
    this.getAllProposals()
  },

  methods:{
    getAllProposals(){
      axios
        .get(API_URL + "/api/proposal")
        .then(
          (response) => {
            this.proposals = response.data
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

