<template>
  <q-page padding>
    <q-header reveal class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
          Job Portal
        </q-toolbar-title>
       <q-btn label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />
        <q-route-tab to="/addpayment" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job"/>
        <q-route-tab to="/listofjobs" label="List of Jobs" />
      </q-tabs>
    </q-header>
  </q-page>
</template>

<script>
export default {
  // name: 'PageName',
	methods:{
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		}
},
}
</script>
