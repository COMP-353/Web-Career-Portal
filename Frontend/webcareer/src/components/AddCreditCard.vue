<template>
  <q-dialog v-model="addccB" @addcc="showDialog()">
    <q-card>
      <q-card-section>
        <div class="text-h6">Add a New Credit Card</div>
      </q-card-section>

      <q-separator />

      <q-card-section style="max-height: 50vh; min-width: 50vh;" class="scroll">
        <q-input
          outlined
          v-model="cc.creditCardNumber"
          type="number"
          label="Card Number"
        />
        <q-separator></q-separator>
        <q-input
          outlined
          v-model="cc.creditCardName"
          label="Credit Card Name"
        />
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
      </q-card-section>

      <q-separator />

      <q-card-actions align="right">
        <q-btn
          flat
          label="Add new Credit Card"
          color="primary"
          v-close-popup
          @click="saveNewCreditCard()"
        />
        <q-btn flat label="Cancel" color="primary" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'AddCreditCard',
  props: ['email'],
  methods: {
    showDialog() {
      console.log('Responding to event addcc');
      this.addccB = true;
      this.resetCcData();
    },
    saveNewCreditCard() {
      axios
        .post(
          'http://localhost:7070/' + 'payment/newCreditCard/' + this.email,
          this.cc
        )
        .catch((e) => console.log(e));
      this.$root.$emit('addedcc');
    },
    resetCcData() {
      this.cc.creditCardNumber = 0;
      this.cc.creditCardName = '';
      this.cc.billingAddress = '';
      this.cc.securityCode = 0;
      this.cc.defaultPayment = false;
      this.cc.automaticWithdrawal = false;
    },
  },
  data() {
    return {
      addccB: false,
      cc: {
        creditCardNumber: 0,
        creditCardName: '',
        billingAddress: '',
        securityCode: 0,
        defaultPayment: false,
        automaticWithdrawal: false,
      },
    };
  },
  mounted() {
    this.$root.$on('addcc', () => this.showDialog());
  },
});
</script>