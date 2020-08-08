<template>
  <q-layout view="hHh LpR fFf">
    <q-page-container>
      <EHeader />
      <div>
        <Reports v-bind:email="user" />
      </div>
    </q-page-container>
  </q-layout>
</template>



<script lang="ts">
import Vue from 'vue';
import EHeader from 'components/EHeader.vue';
import Reports from 'components/Reports.vue';

export default Vue.extend({
  components: {
    EHeader,
    Reports,
  },
  beforeMount() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.user = this.$store.getters.getUserId;
    }
  },

  data() {
    return {
      user: '',
    };
  },
});
</script>