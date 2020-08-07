<template>
  <q-header
    reveal
    class="bg-primary fixed-top absolute-top text-white"
    height-hint="98"
  >
    <q-toolbar>
      <q-toolbar-title>
        Job Portal
      </q-toolbar-title>
      <q-btn flat rounded label="logout" @click="logOut()" />
    </q-toolbar>

    <q-tabs align="left">
      <q-route-tab to="employer" label="Home" />

      <q-route-tab to="/addpayment" label="Add payment" />
      <q-route-tab to="/addjob" label="Add job" />

      <q-route-tab to="/listofjobs" label="List of Jobs" />
      <q-route-tab to="issues" label="Issues" />
    </q-tabs>
  </q-header>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
  name: 'EHeader',
  methods: {
    logOut() {
      this.$store.commit('RESET_USER_ID');
      this.$router.push('');
    },
  },
});
</script>