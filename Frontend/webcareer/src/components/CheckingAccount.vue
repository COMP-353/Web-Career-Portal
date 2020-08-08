<template>
  <div>
    <div class="text-h4 q-mb-md">Checking Account</div>
    <p>Your checking account information</p>
    <q-input
      outlined
      v-model="ca.bankNumber"
      label="Bank Number"
      :disable="modify"
    />
    <q-separator></q-separator>
    <q-input
      outlined
      v-model="ca.accountNumber"
      label="Account Number"
      :disable="modify"
    />

    <div class="q-gutter-sm">
      <q-checkbox
        v-model="ca.defaultPayment"
        label="Default Payment"
        :disable="modify"
      />
      <q-checkbox
        v-model="ca.automaticWithdrawal"
        label="Automatic Withdrawal"
        :disable="modify"
      />
    </div>
    <br />
    <q-btn
      color="white"
      text-color="black"
      :label="caButtonLabel"
      @click="modifyCc()"
    />

    <q-btn label="Delete" @click="deleteCC()" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'CheckingAccount',
  props: ['ca'],
  computed: {
    caButtonLabel() {
      if (this.modify === true) {
        return 'Modify';
      } else {
        return 'Save';
      }
    },
  },
  methods: {
    modifyCc() {
      this.modify = !this.modify;
      if (this.modify) {
        console.log('Saving Credit card');
        axios
          .put('http://localhost:7070/payment/updateCA', this.ca)
          .then()
          .catch((e) => console.log(e));
        this.$root.$emit('updateca');
      }
    },
    deleteCC() {
      axios
        .delete('http://localhost:7070/payment/checking/' + this.ca.id)
        .then()
        .catch((e) => console.log(e));
      this.$root.$emit('updateca');
    },
  },
  data() {
    return {
      modify: true,
    };
  },
});
</script>