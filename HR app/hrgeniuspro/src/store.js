
import {createApp} from 'vue'
import Vuex from 'vuex'

createApp().use(Vuex)

export default new Vuex.Store({
  state: {
    user: null

  },
  mutations: {
    setuser (state,userinfo) {
      state.user=userinfo;
    }

  },
  getters: {
    getUser(state) {
      return state.user
    }
  },
})