<template>
  <div>
    <div class="text-h4 q-mb-md">Credit Card</div>
    <p>Your credit card information</p>
    <q-input
      outlined
      v-model="cc.creditCardNumber"
      type="number"
      label="Card Number"
    />
    <q-separator></q-separator>
    <q-input outlined v-model="cc.creditCardName" label="Credit Card Name" />
    <q-separator></q-separator>
    <q-input
      outlined
      v-model="cc.securityCode"
      type="number"
      label="Credit Card Security Code"
    />
    <q-separator></q-separator>
    <q-input outlined v-model="cc.address" label="Billing Address" />

    <div class="q-gutter-sm">
      <q-checkbox v-model="cc.defaultPayment" label="Default Payment" />
      <q-checkbox
        v-model="cc.automaticWithdrawal"
        label="Automatic Withdrawal"
      />
    </div>

    <br />
    <q-btn color="white" text-color="black" :label="ccButtonLabel" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
  name: 'CreditCard',
  computed: {
    ccButtonLabel() {
      return 'ADD CREDIT CARD';
    },
  },
  props: ['cc'],

  methods: {},
});
</script>