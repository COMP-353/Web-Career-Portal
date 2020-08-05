<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
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
              <!-- All the tabs -->
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

              <q-separator></q-separator>

              <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="seeker">
                  <div>
                    <q-input outlined v-model="jsEmail" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="jsPw" label="Password" />
                    <q-separator></q-separator>
                    <q-btn
                      color="white"
                      text-color="black"
                      label="Log in"
                      @click="loginJs()"
                    />
                    <q-btn
                      to="sign-up-jobSeeker"
                      color="white"
                      text-color="black"
                      label="Sign-Up"
                    />
                    <q-btn
                      to="forgotPassword"
                      color="white"
                      text-color="black"
                      label="Forgot Password"
                    />
                  </div>
                </q-tab-panel>

                <q-tab-panel name="employer">
                  <div>
                    <q-input outlined v-model="eEmail" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="ePassword" label="Password" />
                    <q-separator></q-separator>
                    <q-btn
                      color="white"
                      text-color="black"
                      label="Log in"
                      @click="loginEmployer()"
                    />
                    <q-btn
                      to="sign-up-employer"
                      color="white"
                      text-color="black"
                      label="Sign-Up"
                    />
                    <q-btn
                      to="forgotPassword"
                      color="white"
                      text-color="black"
                      label="Forgot Password"
                    />
                  </div>
                </q-tab-panel>

                <q-tab-panel name="admin">
                  <div>
                    <q-input outlined v-model="aEmail" label="Email Address" />
                    <q-separator></q-separator>
                    <q-input outlined v-model="aPw" label="Password" />
                    <q-separator></q-separator>
                    <q-btn
                      color="white"
                      text-color="black"
                      label="Log in"
                      @click="loginA()"
                    />
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
  eEmail: String = '';
  ePassword = '';
  jsEmail = '';
  jsPw = '';
  aEmail = '';
  aPw = '';

  //Methods that uses axios to connect to backend
  loginEmployer() {
    axios
      .get(
        'http://localhost:7070/user/authenticateUser/' +
          this.eEmail +
          '/' +
          this.ePassword
      )
      .then(response => this.checkResponse(response.data, 'e'))
      .catch(error => console.log(error));
  }

  loginJs() {
    axios
      .get(
        'http://localhost:7070/user/authenticateUser/' +
          this.jsEmail +
          '/' +
          this.jsPw
      )
      .then(response => this.checkResponse(response.data, 'j'))
      .catch(error => console.log(error));
  }

  loginA() {
    axios
      .get(
        'http://localhost:7070/user/authenticateUser/' +
          this.aEmail +
          '/' +
          this.aPw
      )
      .then(response => this.checkResponse(response.data, 'a'))
      .catch(error => console.log(error));
  }

  // Method that leads to another page if successful
  checkResponse(response: boolean, type: String) {
    if (response) {
      if (type === 'j') {
        this.$router.push('job-seeker');
      } else if (type === 'e') {
        this.$router.push('employer');
      } else {
        this.$router.push('job-seeker');
      }
    }
  }
}
</script>
