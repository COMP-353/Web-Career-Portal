<template>
  <q-layout view="hHh LpR fFf">
    <q-page-container>
      <JSHeader />
      <div>
        <Reports v-bind:email="user" />
      </div>
    </q-page-container>
  </q-layout>
</template>



<script lang="ts">
import Vue from 'vue';
import JSHeader from 'components/JSHeader.vue';
import Reports from 'components/Reports.vue';

export default Vue.extend({
  components: {
    JSHeader,
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