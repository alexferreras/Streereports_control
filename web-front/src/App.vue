<template>
  <div id="app">
    <NavBar/>
    <br/>
    <router-view/>
  </div>
</template>

<style lang="scss">
</style>

<script>
import NavBar from '@/components/NavBar';

export default {
  name: 'App',
  components: {
    NavBar
  }  
}
</script>