<template>
  <header>
  <div class="navigatie">
    
    <img class="logonav" src="/logo1.png">

    <router-link to="/dashboard">Dashboard</router-link>
    <router-link to='/profile'>Profil</router-link>
    <router-link to='/cerere'>Cerere</router-link>
    <router-link to="/Jobs">Jobs</router-link>
    <router-link to="/contact">Contact us</router-link>
    <router-link to='/intrb'>Intrebari Frecvente</router-link>
    <router-link to="/org">Organigrama</router-link>
  </div>
</header>
  
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>   
    <div class="cerereconc">
   <section class="hero">
        
        <h1 class="h1_titlu">CERERE DE CONCEDIU</h1>
    </section>
    <br>
    <section class="formular">
 

        <form>
            <label for="tip-concediu">Tip Concediu:</label>
            <select id="tip-concediu">
                <option value="---">---</option>
                <option value="Odihna">Concediu de Odihna</option>
                <option value="Medical">Concediu Medical</option>
            </select>
            <br>
            <div class="date-inputs">
                <label for="data-inceput">Data de început:</label>
                <input type="date" id="data-inceput" required>

                <label for="data-sfarsit">Data de sfârșit:</label>
                <input type="date" id="data-sfarsit" required>
            </div>
            <br>
            <label for="detalii">Alte Detalii:</label>
            <textarea id="detalii" rows="4"></textarea>
            <br>
            <button type="submit">Trimite Cererea</button>
        </form>
   
    </section>
</div>
</template>
<script>
import { fetchV1WithAuth } from "../lib/fetchers.js"
export default {
  name:"CereRe",
  data()
  {
    return{
      requesterName: " ",
  requesterEmail: " ",
  requesterMessage: " "
    }
  },
  methods: {
    async contactus() {

      const res = await fetchV1WithAuth('requests/contact-us/new', { method: "POST", body: JSON.stringify({requesterName: this.requesterName,requesterEmail:this.requesterEmail,requesterMessage:this.requesterMessage}) })

      if (res.ok) {
        const data = await res.json()
        console.log(data)
        alert("Mesajul a fost trimis cu succes")
        {
          this.$router.push({ name: 'dashboard' })
        }
      }
      else {
     
        alert("A aparut o eroare in completarea formularului, va rugam sa mai incercati")
      }
    }
  }
}
</script>