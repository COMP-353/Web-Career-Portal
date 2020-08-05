<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title>
          Web Career Portal
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <template>
        <div class="q-pa-md">
          <div class="q-gutter-y-md" style="max-width: 60%">
            <q-card>
              <q-tabs
                v-model="tab"
                active-color="primary"
                indicator-color="primary"
                align="justify"
              >
                <q-tab name="seeker" label="Job Seeker"></q-tab>
                <q-tab name="employer" label="Employer"></q-tab>
                <q-tab name="admin" label="Admin"></q-tab>
              </q-tabs>

              <!-- <q-separator></q-separator> -->

              <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="seeker">
                  <div>
                    <q-input outlined v-model="email" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="password" label="Password" />
                    <q-separator></q-separator>
                    <q-btn color="white" text-color="black" label="Log in" />
		    <q-btn to="sign-up-jobSeeker" color="white" text-color="black" label="Sign-Up" />
        <q-btn to="forgotPassword" color="white" text-color="black" label="Forgot Password" />
                  </div>
                </q-tab-panel>

                <q-tab-panel name="employer">
                  <div>
                    <q-input outlined v-model="email" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="password" label="Password" />
                    <q-separator></q-separator>
                    <q-btn color="white" text-color="black" label="Log in" />
                    <q-btn to="sign-up-employer" color="white" text-color="black" label="Sign-Up" />
                    <q-btn to="forgotPassword" color="white" text-color="black" label="Forgot Password" />
                  </div>
                </q-tab-panel>

                <q-tab-panel name="admin">
                  <div>
                    <q-input outlined v-model="email" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="password" label="Password" />
                    <q-separator></q-separator>
                    <q-btn color="white" text-color="black" label="Log in" />
                  </div>
                </q-tab-panel>
              </q-tab-panels>
            </q-card>
          </div>
        </div>
      </template>

      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator';
import axios from 'axios';
import { METHODS } from 'http';
import { error } from 'util';

@Component({
  // components: { EssentialLink }
})
export default class Index extends Vue {
  data() {
    return { tab: 'seeker' };
  }
  //Models
  email: String = '';
  password = '';

  //Methods that uses axios to connect to backend
  loginEmployer() {
    axios
      .get(
        'http://localhost:7070/user/authenticateUser/' +
          this.email +
          '/' +
          this.password
      )
      .then(response => this.checkResponse(response.data))
      .catch(error => console.log(error));
  }

  // Method that leads to another page if successful
  checkResponse(response: boolean) {
    if (response) {
      this.$router.push('job-seeker');
    }
  }
}
</script>
