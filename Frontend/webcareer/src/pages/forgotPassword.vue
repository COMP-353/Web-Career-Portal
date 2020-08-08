<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px

.allContents
  padding-top: 5%
  padding-left: 35%
  padding-right: 35%

.buttons
  padding: 2% 1% 1% 1%
</style>

<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar>
        <q-toolbar-title>
          Web Career Portal
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page class="allContents">
      <div class="row justify-center text-center">
        <div class="q-gutter-md">
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

      <div class="row justify-between">
        <div class="buttons">
          <q-btn to="login" color="black" flat rounded label="Return" />
        </div>

        <div class="buttons">
          <q-btn
            color="white"
            text-color="primary"
            label="Submit"
            outline
            rounded
            @click="sendEmail()"
          />
        </div>
      </div>

      <div class="justify-center text-center">
        {{ text }}
      </div>
    </q-page>
  </q-layout>
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