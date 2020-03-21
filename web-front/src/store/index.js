import Vue from 'vue';
import Vuex from 'vuex';

import urls from './urls';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    urls,
    credentials: {
      username: 'admin',
      password: '123'
    },
    user: {
      id: 1,
      username: 'admin',
      password: '123',
      logged: true
    }
  },
  mutations: {
    LOGIN(state) {
      state.user.logged = true;
    },
    LOGOUT(state) {
      state.user.logged = false;
    },
  },
  actions: {
    login(state, payload) {
      if (payload === true) {
        state.commit('LOGIN');
      }
    },
    logout(state, payload) {
      if (payload === false) {
        state.commit('LOGOUT');
      }
    }
  },
  getters: {
    isUserLogged(state) {
      return state.user.logged;
    },
    user(state) {
      return state.user;
    }
  },
  modules: {
  }
})
