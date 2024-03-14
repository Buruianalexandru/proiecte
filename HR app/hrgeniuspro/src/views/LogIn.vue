
<template>
  <br><br><br>
  <div id="app">
    <div class="login-container">
      <img class="logo" src="../assets/logo1.png">
      <h1>Log in</h1>
      <br>
    <form @submit.prevent="login">
      <label for="email">Username:</label>
      <input type="text" id="email" v-model="email" required>
      <br>
      <br>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required>
      <br>
      <br>
      <button type="submit" class="login-button" v-on:click="login">Login</button>
      <router-link to="/">Nu aveti cont? Inregistrati-va acum</router-link>
    </form>
    </div>
  </div>
</template>
<script>
 import {fetchV1WithAuth} from "../lib/fetchers.js"
 import store from "../store.js"
export default{
  name:"LogIn",
  data()
  {
    return{
      email:'',
      password:''
    }
  },
  methods:{

   async login()
    {

      console.log(this.email, this.password)
      const res = await fetchV1WithAuth('auth/sign-in', {method: "POST", body: JSON.stringify({email: this.email, password: this.password})})
      console.log(res)
      if(res.ok)
      {

        const data=await res.json()
        store.commit('setuser',data.data);
        
        console.log(store.getters.getUser);
        
        this.$router.push({name:'dashboard'})
        console.log(data.data.userId)
      
      }
    }
  }
}

</script>
