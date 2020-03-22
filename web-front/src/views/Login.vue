<template>
<div class="column is-half is-offset-one-quarter">
  <form>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">Login</p>
      </header>
      <section class="modal-card-body">
        <b-field label="Username">
          <b-input
            type="text"
            v-model="username"
            placeholder="Your username"
            required
          />
        </b-field>

        <b-field label="Password">
          <b-input
            type="password"
            v-model="password"
            icon-pack="fas"
            icon-right="eye"
            password-reveal
            placeholder="Your password"
            required
          />
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <div class="buttons">
          <b-button type="is-danger" @click="cancel()">
            Cancel
          </b-button>
          <b-button type="is-primary" @click="login()">
            Login
          </b-button>
        </div>
      </footer>
    </div>
  </form>
</div>
</template>

<script>
import swal from 'sweetalert';

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    async login() {
      if (this.username === this.$store.state.credentials.username 
        && this.password === this.$store.state.credentials.password
      ){
        const confirmation = await swal({
          title: 'Confirmación',
          text: `Acceso correcto`,
          icon: 'success'
        });

        if (confirmation) {
          this.$store.state.user.username = this.username;
          this.$store.state.user.password = this.password;
          this.$store.dispatch('login', true);

          this.$router
            .push({ name: 'Home'})
            .catch(e => {});
        }

      } else {
        swal({
          title: 'Advertencia',
          text: `Acceso incorrecto`,
          icon: 'warning'
        }); 
      }            
    },
    cancel() {
      this.$router.push({ name: 'Home' });
    }
  }
}
</script>