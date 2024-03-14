<template>
  <router-view />
</template>
<script>
import store from '../src/store.js'
export default {
  data() {
    return {
      isuserauth:false, 
    };
  },
  mounted(){
    console.log(store.getters.getUser)
    
    if(store.getters.getUser)
    {
      this.isuserauth=true;
    }
    console.log(this.isuserauth)
  }
};
</script>


