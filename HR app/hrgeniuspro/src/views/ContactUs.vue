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
  
  <br><br><br><br><br><br><br><br><br>

  <main>
    <section class="contact-form">
      <img class="logo" src="../assets/logo1.png">
      <h1 style="text-align: center;">Contact us</h1>
      <br>
      <form @submit.prevent="contactus">
        <div class="form-group">
          <label for="name">Nume prenume/denumire companie:</label>
          <input type="text" id="requesterName" v-model="requesterName" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="requesterEmail" v-model="requesterEmail" required>
        </div>
        <div class="form-group">
          <label for="message">Message:</label>
          <textarea id="requesterMessage" v-model="requesterMessage" rows="5" required></textarea>
        </div>
        <button type="submit" >Submit</button>
      </form>
    </section>
  </main>
</template>

<script>
import { fetchV1WithAuth } from "../lib/fetchers.js";

export default {
  name: "ContactUs",
  data() {
    return {
      requesterName: "",
      requesterEmail: "",
      requesterMessage: "",
    };
  },
  methods: {
    async contactus() {
      const res = await fetchV1WithAuth('requests/contact-us/new', {
        method: "POST",
        body: JSON.stringify({
          requesterName: this.requesterName,
          requesterEmail: this.requesterEmail,
          requesterMessage: this.requesterMessage,
        }),
      });

      if (res.ok) {
        const data = await res.json();
        console.log(data);
        alert("Mesajul a fost trimis cu succes");
        this.$router.push({ name: 'dashboard' });
      } else {
        alert("A aparut o eroare in completarea formularului, va rugam sa mai incercati");
      }
    },
  },
};
</script>
