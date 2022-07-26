<template>
  <div class="form">
    <div class="input">
      <div class="label"> Поиск по телефону </div>
      <input v-model="phoneSearch" @input="searchByPhone" type="text">
    </div>
    <div class="input">
      <div class="label"> Поиск по ФИО </div>
      <input v-model="personName" @input="searchByName" type="text">
    </div>
    <div class="input">
      <div class="label"> Поиск по серии/номеру пасспорта </div>
      <input v-model="seriesNumber" @input="searchBySeriesNumber" type="text">
    </div>

    <!-- <div v-for="person in persons" :key="person.seriesNumber">
      <div>Серия/номер: {{person.seriesNumber}}</div>
      <div>Имя: {{person.firstName}}</div>
      <div>Фамилия: {{person.lastName}}</div>
      <div v-if="person.patronym != ''">Отчество: {{person.patronym}}</div>
      <div>Телефон: {{person.contactNumber}}</div>
    </div> -->

    <div class="table">
      <div class="header">
        <div class="h-cell">Серия/номер</div>
        <div class="h-cell">Имя</div>
        <div class="h-cell">Фамилия</div>
        <div class="h-cell">Отчество</div>
        <div class="h-cell">Телефон</div>
      </div>
      <template v-for="person in persons" :key="person.seriesNumber" >
        <div class="row">
          <div class="cell">{{person.seriesNumber}}</div>
          <div class="cell">{{person.firstName}}</div>
          <div class="cell">{{person.lastName}}</div>
          <div class="cell">{{person.patronym}}</div>
          <div class="cell">{{person.contactNumber}}</div>
        </div>
      </template>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

let API_URL = 'http://localhost:8081'

export default {
  name: 'SearchPerson',
  data() {
    return {
        phoneSearch: "",
        personName: "",
        seriesNumber: "",
        persons: [],
    }

  },
  created() {
    this.getAllPersons()
  },

  methods:{
    getAllPersons(){
        axios
        .get(API_URL + "/api/person")
        .then(
          (response) => {
            this.persons = response.data
          })
        .catch((error) => { console.log(error) })
    },
    searchByPhone(){
        if (this.phoneSearch == ""){
          this.getAllPersons()
          return
        } 
        axios
        .get(API_URL + "/api/person/number/" + this.phoneSearch)
        .then(
          (response) => {
            this.persons = response.data
            this.personName = ""
            this.seriesNumber = ""
          })
        .catch((error) => { console.log(error) })
    },
    searchBySeriesNumber(){
        if (this.seriesNumber == ""){
          this.getAllPersons()
          return
        } 
        axios
        .get(API_URL + "/api/person/id/" + this.seriesNumber)
        .then(
          (response) => {
            this.persons = response.data
            this.personName = ""
            this.phoneSearch = ""
          })
        .catch((error) => { console.log(error) })
    },
    searchByName(){
        if (this.personName == ""){
          this.getAllPersons()
          return
        } 
        axios
        .get(API_URL + "/api/person/name/" + this.personName)
        .then(
          (response) => {
            this.persons = response.data
            this.seriesNumber = ""
            this.phoneSearch = ""
          })
        .catch((error) => { console.log(error) })
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
  .table {
    display: flex;
    flex-direction: column;
  }
  .header, .row {
      display: grid;
      grid-template-columns: repeat(5, 1fr);
  }
  .h-cell, .cell {
      padding: 5px;
      text-align: center;
      display: grid;
      place-items: center;
      background-color: #c7c7c7;
      height: 100%;
      border: 1px solid rgb(124, 124, 124);
  }
  .cell {
      background-color: #f3f3f3;
  }
</style>

