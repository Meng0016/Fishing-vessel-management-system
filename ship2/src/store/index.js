import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const store =  new Vuex.Store({
  state: {
    currentPathName: ''
  },
  getters: {
  },
  mutations: {
    setPath (state) {
      state.currentPathName = localStorage.getItem("currentPathName")
    }
  },
  actions: {
  },
  modules: {
  }
})

export default store
