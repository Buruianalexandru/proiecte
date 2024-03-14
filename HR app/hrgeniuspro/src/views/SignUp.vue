
  <template>
    <br><br><br>
    <div id="app">
    <div class="login-container">
      <img class="logo" src="../assets/logo1.png">
      <h1>Sign up</h1>
      <br>
    <form @submit.prevent="signup">
      <label for="email">Username:</label>
      <input type="text" id="email" v-model="email" required>
      <br>
      <br>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required>
      <br>
      <br>
      <button type="submit" class="login-button" v-on:click="signup">Sign up</button>
    </form>
    <router-link to="/login">Intra in cont</router-link>
    </div>
  </div>
</template>
<script>
import {fetchV1WithAuth} from "../lib/fetchers.js"
export default{
  name:"SignUp",
  data()
  {
    return{
      email:'',
      password:''
    }
  },
  methods:{

   async signup()
    {
      console.log(this.email, this.password)
      const res = await fetchV1WithAuth('auth/sign-up', {method: "POST", body: JSON.stringify({email: this.email, password: this.password})})
      console.log(res)
      if(res.ok)
      {
        const data=await res.json()
        console.log(data)
        this.$router.push({name:'dashboard'})
        console.log(data.data.userId)
      }
    }
  }
}
</script>
  
  