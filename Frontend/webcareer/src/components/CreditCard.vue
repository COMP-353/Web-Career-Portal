<template>
  <div style="padding-top: 3%;">
    <p>Your credit card information</p>
    <q-input
      outlined
      v-model="cc.creditCardNumber"
      type="number"
      label="Card Number"
      :disable="modify"
    />
    <q-separator></q-separator>
    <q-input
      outlined
      v-model="cc.creditCardName"
      label="Credit Card Name"
      :disable="modify"
    />
    <q-separator></q-separator>
    <q-input
      outlined
      v-model="cc.securityCode"
      type="number"
      label="Credit Card Security Code"
      :disable="modify"
    />
    <q-separator></q-separator>
    <q-input
      outlined
      v-model="cc.billingAddress"
      label="Billing Address"
      :disable="modify"
    />

    <div class="">
      <q-checkbox
        v-model="cc.defaultPayment"
        label="Default Payment"
        :disable="modify"
      />
      <q-checkbox
        v-model="cc.automaticWithdrawal"
        label="Automatic Withdrawal"
        :disable="modify"
      />
    </div>

    <br />
    <q-btn
      color="white"
      flat
      text-color="black"
      :label="ccButtonLabel"
      @click="modifyCc()"
    />

    <q-btn flat label="Delete" @click="deleteCC()" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'CreditCard',
  computed: {
    ccButtonLabel() {
      if (this.modify === true) {
        return 'Modify';
      } else {
        return 'Save';
      }
    },
  },
  props: ['cc'],

  data() {
    return {
      modify: true,
    };
  },
  methods: {
    modifyCc() {
      this.modify = !this.modify;
      if (this.modify) {
        console.log('Saving Credit card');
        axios
          .put('http://localhost:7070/payment/updateCC', this.cc)
          .then()
          .catch((e) => console.log(e));
        this.$root.$emit('updatecc');
      }
    },
    deleteCC() {
      axios
        .delete('http://localhost:7070/payment/credit/' + this.cc.id)
        .then()
        .catch((e) => console.log(e));
      this.$root.$emit('updatecc');
    },
  },
});
</script>