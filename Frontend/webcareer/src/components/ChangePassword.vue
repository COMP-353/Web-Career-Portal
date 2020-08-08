<template>
  <!-- <q-card> -->
  <div>
    <div class="text-h4 q-mb-md">Change Your Account Password</div>
    <div class="text">Make sure the passwords match</div>
    <div style="padding-top: 1%;">
      <q-input
        outlined
        v-model="pw1"
        label="New Password"
        :type="isPw1 ? 'password' : 'text'"
      >
        <template v-slot:append>
          <q-icon
            :name="isPw1 ? 'visibility_off' : 'visibility'"
            @click="isPw1 = !isPw1"
          />
        </template>
      </q-input>
    </div>
    <div style="padding-top: 1%;">
      <q-input
        outlined
        v-model="pw2"
        label="Confirm New Password"
        :type="isPw2 ? 'password' : 'text'"
      >
        <template v-slot:append>
          <q-icon
            :name="isPw2 ? 'visibility_off' : 'visibility'"
            @click="isPw2 = !isPw2"
          />
        </template>
      </q-input>
    </div>
    <div style="padding-top: 1%;">
      <q-btn
        outline
        rounded
        color="primary"
        label="change password"
        @click="changePassword()"
      />
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'ChangePassword',
  props: ['email'],
  data() {
    return {
      pw1: '',
      pw2: '',
      isPw1: true,
      isPw2: true,
    };
  },
  methods: {
    changePassword() {
      if (this.pw1 === this.pw2) {
        axios
          .put(
            'http://localhost:7070/user/updateUserPassword/' +
              this.email +
              '/' +
              this.pw1
          )
          .then(this.reset())
          .catch((e) => console.log(e));
      }
    },
    reset() {
      this.pw1 = '';
      this.pw2 = '';
    },
  },
});
</script>