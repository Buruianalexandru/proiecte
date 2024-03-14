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
  
  <br><br><br><br><br><br><br><br><br><br>


  <div class="calendar">
    <div class="calendar-header">
      <button class="login-button" @click="prevMonth">&lt;</button>
      <h2>{{ formattedMonth }}</h2>
      <button class="login-button" @click="nextMonth">&gt;</button>
    </div>
    <br>
    <div class="calendar-grid">
      <div class="calendar-day" v-for="day in daysInMonth" :key="day">
        <div class="day-header">{{ day }}</div>
        <div class="day-events">
          <div v-for="event in filteredEvents(day)" :key="event.date">
            {{ event.title }}
          </div>
        </div>
      </div>
    </div>

  </div>
  <br><br>
  <div class="calendar">
    <h1>Cereri</h1>
    <ul>
      <li v-for="item in contactusrequestarray" :key="item.requestId">
        <div class="request-item">
          <div class="reqname">{{ item.requesterName }}</div>
          <div class="reqemail">{{ item.requesterEmail }}</div>
          <div class="reqmes">{{ item.requesterMessage }}</div>
        </div>
      </li>
    </ul>

  </div>
  <br><br>
 
</template>

<script>
import { fetchV1FormDataWithAuth } from '@/lib/fetchers';

export default {
  data() {
    return {
      currentMonth: new Date(),
      events: [
        { date: '', },
        { date: '' },

      ],
      contactusrequestarray: ''
    };
  },
  computed: {
    daysInMonth() {
      const year = this.currentMonth.getFullYear();
      const month = this.currentMonth.getMonth();
      const lastDay = new Date(year, month + 1, 0).getDate();
      const days = [];

      for (let i = 1; i <= lastDay; i++) {
        days.push(i);
      }

      return days;
    },
    formattedMonth() {
      const options = { year: 'numeric', month: 'long' };
      return this.currentMonth.toLocaleDateString('en-US', options);
    },
  },
  mounted(){
    this.cerere();
  },
  methods: {
    prevMonth() {
      this.currentMonth = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() - 1, 1);
    },
    nextMonth() {
      this.currentMonth = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() + 1, 1);
    },
    filteredEvents(day) {
      const date = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth(), day);
      const formattedDate = date.toISOString().split('T')[0]; // Get the date without the time
      return this.events.filter(event => event.date === formattedDate);
    },
    async cerere() {
      const res = await fetchV1FormDataWithAuth('requests/contact-us', { method: 'GET' });
      const data=await res.json();
      console.log(res);
      this.contactusrequestarray = data.data;
    }
  }
}
</script>

  