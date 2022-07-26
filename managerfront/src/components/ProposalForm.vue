<template>
  <form class="form" @submit.prevent>
    <div class="input">
      <div class="label"> Имя </div>
      <input v-model="firstName" type="text" required maxlength="32">
    </div>
    <div class="input">
      <div class="label"> Фамилия </div>
      <input v-model="lastName" type="text" required maxlength="32">
    </div>
    <div class="input">
      <div class="label"> Отчество </div>
      <input v-model="patronym" type="text" maxlength="32">
    </div>
    <div class="input">
      <div class="label"> Дата рождения </div>
      <input v-model="birthDate" type="date" required>
    </div>
    <div class="input">
      <div class="label"> Серия/номер пасспорта </div>
      <input v-model="seriesNumber" type="text" required minlength="10" maxlength="10" pattern="[0-9]+">
    </div>
    <div class="input">
      <div class="label"> Дата выдачи пасспорта </div>
      <input v-model="passportDate" type="date" required>
    </div>
    <div class="input">
      <div class="label"> Организация, выдавшая пасспорт </div>
      <input v-model="passportOrganName" type="text" required maxlength="128">
    </div>
    <div class="input">
      <div class="label"> Код организации, выдавшей пасспорт </div>
      <input v-model="passportOrganCode" type="text" required minlength="6" maxlength="6" pattern="[0-9]+">
    </div>
    <div class="input">
      <div class="label"> Семейное положение </div>
      <select v-model="isMarried">
        <option :value="true">Женат / жената</option>
        <option :value="false">Не женат / не жената</option>
      </select>
    </div>
    <div class="input">
      <div class="label"> Адрес </div>
      <input v-model="address" type="text" required maxlength="255">
    </div>
    <div class="input">
      <div class="label"> Мобильный телефон </div>
      <input v-model="contactNumber" type="text" required minlength="11" maxlength="12" pattern="+?[0-9]+">
    </div>
    <div class="input">
      <div class="label"> Желаемая сумма кредита в рублях</div>
      <input v-model="wishedSum" type="number" required pattern="\+?[0-9]+">
    </div>

    <div class="input"> Информация о местах работы </div>
    <div class="job-list"
      v-for="period in workPeriod"
      :key="period.startDate">

      <div class="input">
        <div class="label"> Должность </div>
        <input v-model="period.jobPosition" type="text" required maxlength="128">
      </div>
      <div class="input">
        <div class="label"> Название организации </div>
        <input v-model="period.organisationName" type="text" required maxlength="128">
      </div>
      <div class="input">
        <div class="label"> Дата начала работы </div>
        <input v-model="period.startDate" type="date" required>
      </div>
      <div class="input">
        <div class="label"> Дата окончания работы </div>
        <input v-model="period.endDate" type="date" required>
      </div>
      

    </div>
    <button class="btn" @click="addJob">Добавить место работы</button>
    <button class="btn" @click="submit" type="submit">Отправить заявку</button>

    <div v-if="approved != null">
      <div class="input" v-if="approved == true">
        <div>Одобренная сумма : {{this.approvedSum}}</div>
        <div>На срок: {{this.creditTerm}} дней</div>
        <button class="btn" @click="sign">Подписать</button>
        <div v-if="finished == true">Договор подписан успешно</div>
      </div>
      <div v-if="approved != true">
        Кредит не одобрен
      </div>
    </div>
    

  </form>
</template>

<script>
import axios from 'axios'

let API_URL = 'http://localhost:8081'

export default {
  name: 'ProposalForm',
  data() {
    return {
      seriesNumber: "",
      firstName: "",
      lastName: "",
      patronym: "",
      passportDate: "",
      passportOrganName: "",
      passportOrganCode: "",
      birthDate: "",
      isMarried: false,
      address: "",
      contactNumber: "",
      workPeriod: [{jobPosition: "", organisationName: "", startDate: "", endDate: ""}],
      wishedSum: 0.0,
      approved: null,
      creditTerm: null,
      approvedSum: null,
      finished: false
    }

  },
  methods:{
    addJob() {
      let newJob = {
        startDate: "", 
        endDate: "", 
        jobPosition: "", 
        organisationName: ""
      }
      this.workPeriod.push(newJob);
    },

    submit() {
        let obj = {
          seriesNumber: this.seriesNumber,
          firstName: this.firstName,
          lastName: this.lastName,
          patronym: this.patronym,
          passportDate: this.passportDate,
          passportOrganName: this.passportOrganName,
          passportOrganCode: this.passportOrganCode,
          birthDate: this.birthDate,
          married: this.isMarried,
          address: this.address,
          contactNumber: this.contactNumber,
          workPeriod: this.workPeriod
        }
          
        axios.post(API_URL + '/api/proposal?wishedSum=' + this.wishedSum, obj)
          .then((response) => {
              console.log(response.data)
              this.contract_id = response.data.id
              this.approved = response.data.approved
              this.creditTerm = response.data.creditTerm
              this.approvedSum = response.data.approvedSum
          })
          .catch(error => console.log(error))
    },
    sign(){
      axios.patch(API_URL + '/api/contract/' + this.contract_id + '/sign')
        .then(response => {
          console.log(response.data)
          this.finished = true
        })
        .catch(error => console.log(error))
    },
    
  }
}
</script>

<style scoped>
  .form {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
  }
  .input {
    width: 600px;
    display: flex;
    flex-direction: column;
    gap: 2px;
  }
  input select {
    width: 100%;
  }
  .label {
    margin-right: auto;
    margin-left: 10px;
  }
</style>
