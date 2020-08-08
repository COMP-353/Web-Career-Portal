<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>

<template>
  <q-page padding>
    <div class="row justify-center full-height full-width text-center">
      <div class="q-gutter-md" style="max-width: 300px;">
        <div class="q-pa-md q-gutter-sm"></div>

        <p>
          <b
            >Please input your email. If it matches the one we have in our
            system, you will see your password in your email.</b
          >
        </p>
        <hr />
        <q-input outlined v-model="email" label="Email" />
      </div>
    </div>

    <br />

    <div class="row justify-center full-height full-width text-center">
      <div class="clearfix">
        <q-btn
          color="white"
          text-color="black"
          label="Submit"
          @click="sendEmail()"
        />
        <q-btn to="login" color="white" text-color="black" label="Cancel" />
      </div>
    </div>

    <div class="justify-center text-center">
      {{ text }}
    </div>
  </q-page>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      email: '',
      text: ''
    };
  },
  methods: {
    sendEmail() {
      axios
        .get('http://localhost:7070/user/forgot/' + this.email)
        .then(this.done())
        .catch(e => console.log(e));
    },
    done() {
      this.email = '';
      this.text =
        "It's done, check your email. \nIf you do not receive any emails anywhere, your email is not registered in our system.";
    }
  }
};
</script>
