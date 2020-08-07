<template>
  <q-header reveal class="bg-primary text-white" height-hint="98">
    <q-toolbar>
      <q-toolbar-title>
        Web Career Portal
      </q-toolbar-title>
      <q-btn flat rounded label="logout" @click="logOut()" />
    </q-toolbar>

    <q-tabs align="left">
      <q-route-tab to="js" label="Home" />
      <q-route-tab to="js-postings" label="Job Postings" />
      <q-route-tab to="js-profile" label="Profile" />
    </q-tabs>
  </q-header>
</template>

<script>
	
export default {
name:'JSHeader',
  data () {
    return {
			
    }
    },
    methods:{
        logOut(){
        this.$store.commit('RESET_USER_ID');
        this.$router.back();
        }
    }
}

</script>