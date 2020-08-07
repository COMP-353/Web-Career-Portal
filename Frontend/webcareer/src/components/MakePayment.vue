<template>
  <div>
    <div class="text-h4 q-mb-md">Make a Payment</div>
    <p>
      If you have choosen automatic during set-up payment then you don't need to
      visit this page. If else, continue to steps below.
    </p>
    <p><b>Choose the amount of money you're going to pay.</b></p>
    <q-input outlined v-model="amount" type="creditCardNumber" prefix="$" />
    <q-separator></q-separator>
    <br />
    <p><b>Choose your method of payment.</b></p>

    <div>
      <q-radio v-model="paymentmethod" val="creditcard" label="Credit Card" />
      <q-radio
        v-model="paymentmethod"
        val="checkingaccount"
        label="Checking Account"
      />
    </div>
    <q-btn label="pay" @click="makeAPayment()" :disabled="!(amount > 0)" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';
export default Vue.extend({
  name: 'MakePayment',
  props: ['user'],
  data() {
    return {
      amount: 0,
      baseUrl: 'http://localhost:7070/',
      paymentmethod: 'creditcard',
    };
  },
  methods: {
    makeAPayment() {
      axios
        .put(this.baseUrl + 'user/pay/' + this.user.email + '/' + this.amount)
        .then(this.getUserData())
        .catch((e) => console.log(e));
    },
    getUserData() {
      this.amount = 0;
      //   axios
      //     .get(this.baseUrl + 'user/jobseeker/' + this.user.email)
      //     .then((res) => this.assignJsObject(res.data))
      //     .catch((e) => console.log(e));
    },
    // assignJsObject(res) {
    //   console.log(res);
    //   this.user.firstName = res.firstName;
    //   this.user.lastName = res.lastName;
    //   this.user.accountBalance = res.accountBalance;
    //   this.user.status = res.status;
    // },
  },
});
</script>