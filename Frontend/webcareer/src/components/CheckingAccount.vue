<template>
  <div>
    <div class="text-h4 q-mb-md">Checking Account</div>
    <p>Your checking account information</p>
    <q-input outlined v-model="ca.bankNumber" label="Bank Number" />
    <q-separator></q-separator>
    <q-input outlined v-model="ca.accountNumber" label="Account Number" />

    <div class="q-gutter-sm">
      <q-checkbox v-model="ca.defaultPayment" label="Default Payment" />
      <q-checkbox
        v-model="ca.automaticWithdrawal"
        label="Automatic Withdrawal"
      />
    </div>
    <br />
    <q-btn color="white" text-color="black" :label="caButtonLabel" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
  name: 'CheckingAccount',
  props: ['ca'],
  computed: {
    caButtonLabel() {
      //  if (this.ca.accountNumber === 0) {
      return 'ADD CHECKING ACCOUNT';
      //  } else if (this.modifyCa){
      //  return 'modify'
      //  } else {
      //  return 'save'
      //  }
    },
  },
  methods: {},
});
</script>