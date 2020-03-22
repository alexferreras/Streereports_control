<template>
<div>
  <b-navbar
      type="is-dark"
      wrapper-class="container"
      class="b-navbar is-spaced"
    >
      <template slot="brand">
        <b-navbar-item
          tag="router-link"
          :to="{ name: 'Home' }"
        >
          <img
            src="https://raw.githubusercontent.com/buefy/buefy/dev/static/img/buefy-logo.png"
            alt="Lightweight UI components for Vue.js based on Bulma"
          >
        </b-navbar-item>
      </template>

      <template slot="start">
        <b-navbar-item
          tag="router-link"
          :to="{ name: 'Home' }"
        >
          <span>
            <i class="fas fa-home"></i>
            &nbsp;Home
          </span>
        </b-navbar-item>

        <b-navbar-item
          tag="router-link"
          :to="{ name: 'Users' }"
        >
          <span>
            <i class="fas fa-users"></i>
            &nbsp;Users
          </span>
        </b-navbar-item>
      </template>

      <template slot="end">
        <b-navbar-item tag='div'>
          <template v-if="$store.state.user.logged">
            <b>{{ $store.state.user.username }}</b>
          </template>

          <template v-else>
            <b-button
              type="is-info"
              icon-right="sign-out-alt"
              icon-pack="fas"
              @click="login()"
            >
              Login
            </b-button>
          </template>
        </b-navbar-item>

        <div class="buttons">
          <b-navbar-item tag="div">
            <template v-if="$store.state.user.logged">
              <b-button
                type="is-warning"
                icon-left="sign-out-alt"
                icon-pack="fas"
                @click="logout()"
              >
                Logout
              </b-button>
            </template>
          </b-navbar-item>
        </div>
      </template>
  </b-navbar>
</div>
</template>

<script>
export default {
  name: 'NavBar',
  methods: {
    login() {
      return this.$router.push({ name: 'Login' });
    },
    logout() {
      this.$store.dispatch('logout', false);

      return this.$router
        .push({ name: 'Login' })
        .catch(e => {
          console.error(e);
        });
    }
  }
}
</script>