<template>
    <header>
  <div class="navigatie">
    
    <img class="logonav" src="/logo1.png">

    <router-link to="/dashboard">Dashboard</router-link>
    <router-link to='/profile'>Profil</router-link>
    <router-link to='/cerere'>Cerere</router-link>
    <router-link to="/jobs">Jobs</router-link>
    <router-link to="/contact">Contact us</router-link>
    <router-link to='/intrb'>Intrebari Frecvente</router-link>
    <router-link to="/org">Organigrama</router-link>
  </div>
</header>
  
    <br>
    <div class="application-container">
    <h2>Job Application</h2>
    <br>
    <form class="application-form" submit.prevent="submitappjob">

        <br>
        <br>
        <label for="firstname">First Name:</label>
        
        <input type="text" id="firstname" name="firstname" required>
        <br>
        <br>
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required>
        <br>
        <br>
        <label for="contactnumber">Contact Number:</label>
        <input type="tel" id="contactnumber" name="contactnumber" required>
        <br>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <br>
        <label for="resume">Upload Resume (PDF):</label>
        <input type="file" id="resume" name="resume" accept=".pdf" required>
        <br>
        <br>
        <button type="submit" v-on:click="appjob">Submit Application</button>
    </form>
</div>
</template>
<script>
import {fetchV1WithAuth} from "../lib/fetchers.js"
export default {
  data() {

  },
  methods: {
    submitappjob() {

      const formData = {
        contactnumber: this.$refs.contactnumber.value, 
        resume: this.$refs.resume.value
      }
      console.log(formData)
    },
    async appjob()
    {

      const res = await fetchV1WithAuth('', {method: "POST", body: JSON.stringify(formData)})   
      
      if(res.ok)
      {
        const data=await res.json()
        console.log(data)
        alert("Cererea de angajare a fost trimisa cu succes")
        {    
                this.$router.push({name:'home'})
        }
      }
      else {
        console.error(error);
        alert("A aparut o eroare in completarea formularului, va rugam sa mai incercati")
      }
    }
}
}
</script>