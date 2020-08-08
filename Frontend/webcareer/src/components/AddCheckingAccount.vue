<template>
  <q-dialog v-model="addcaB" @addcc="showDialog()">
    <q-card>
      <q-card-section>
        <div class="text-h6">Add a New Checking Account</div>
      </q-card-section>

      <!-- <q-separator /> -->
      <q-card-section style="max-height: 50vh; min-width: 50vh;" class="scroll">
        <div>
          <p>Your checking account information</p>
          <q-input outlined v-model="ca.bankNumber" label="Bank Number" />
          <!-- <q-separator></q-separator> -->
          <q-input outlined v-model="ca.accountNumber" label="Account Number" />

          <div class="q-gutter-sm">
            <q-checkbox v-model="ca.defaultPayment" label="Default Payment" />
            <q-checkbox
              v-model="ca.automaticWithdrawal"
              label="Automatic Withdrawal"
            />
          </div>
          <!-- <br /> -->
          <q-card-actions align="right">
            <q-btn v-close-popup label="Cancel" color="primary" flat />
            <q-btn
              v-close-popup
              flat
              color="primary"
              label="Add"
              @click="saveNewCheckingAccount()"
            />
          </q-card-actions>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'AddCheckingAccount',
  props: ['email'],
  mounted() {
    this.$root.$on('addca', () => this.showDialog());
  },
  data() {
    return {
      addcaB: false,
      ca: {
        bankNumber: 0,
        accountNumber: 0,
        defaultPayment: false,
        automaticWithdrawal: false,
      },
    };
  },
  methods: {
    showDialog() {
      console.log('Responding to event addcc');
      this.addcaB = true;
      this.resetCaData();
    },
    resetCaData() {
      this.ca.bankNumber = 0;
      this.ca.accountNumber = 0;
      this.ca.defaultPayment = false;
      this.ca.automaticWithdrawal = false;
    },
    saveNewCheckingAccount() {
      axios
        .post(
          'http://localhost:7070/' + 'payment/newCheckingAccount/' + this.email,
          this.ca
        )
        .catch((e) => console.log(e));
      this.$root.$emit('addedca');
    },
  },
});
</script>