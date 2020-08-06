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
          <div class="q-gutter-y-md" style="max-width: 60%;">
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
                    <q-input
                      outlined
                      v-model="jsPw"
                      label="Password"
                      :type="jsIsPw ? 'password' : 'text'"
                    >
                      <template v-slot:append>
                        <q-icon
                          :name="jsIsPw ? 'visibility_off' : 'visibility'"
                          @click="jsIsPw = !jsIsPw"
                        />
                      </template>
                    </q-input>

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
                      rounded
                      flat
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
                    <!-- <q-input outlined v-model="ePassword" label="Password" /> -->
                    <q-input
                      v-model="ePassword"
                      outlined
                      :type="eIsPw ? 'password' : 'text'"
                      label="Password"
                    >
                      <template v-slot:append>
                        <q-icon
                          :name="eIsPw ? 'visibility_off' : 'visibility'"
                          class="cursor-pointer"
                          @click="eIsPw = !eIsPw"
                        />
                      </template>
                    </q-input>
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
                      rounded
                      flat
                      text-color="black"
                      label="Forgot Password"
                    />
                  </div>
                </q-tab-panel>

                <q-tab-panel name="admin">
                  <div>
                    <q-input outlined v-model="aEmail" label="Email Address" />
                    <q-separator></q-separator>
                    <!-- <q-input outlined v-model="aPw" label="Password" /> -->
                    <q-input
                      v-model="aPw"
                      outlined
                      :type="aIsPw ? 'password' : 'text'"
                      label="Password"
                    >
                      <template v-slot:append>
                        <q-icon
                          :name="aIsPw ? 'visibility_off' : 'visibility'"
                          class="cursor-pointer"
                          @click="aIsPw = !aIsPw"
                        />
                      </template>
                    </q-input>
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
// import { State, Action, Mutation, namespace } from 'vuex-class';
// import { UserStateInterface } from 'app/store/module-example/state';
// const someModule = namespace('store/');
@Component({
  // components: { EssentialLink }
})
export default class Index extends Vue {
  // @State('user') user; //: UserStateInterface;
  // @Action('setUserId') setId: any;
  data() {
    return { tab: 'seeker', jsIsPw: true, eIsPw: true, aIsPw: true };
  }
  //Models
  eEmail = '';
  ePassword = '';
  jsEmail = '';
  jsPw = '';
  aEmail = '';
  aPw = '';

  //Methods that uses axios to connect to backend
  loginEmployer() {
    axios
      .get(
        'http://localhost:7070/user/authenticatee/' +
          this.eEmail +
          '/' +
          this.ePassword
      )
      .then((response) => this.checkResponse(response.data, this.eEmail, 'e'))
      .catch((error) => console.log(error));
  }

  loginJs() {
    axios
      .get(
        'http://localhost:7070/user/authenticatejs/' +
          this.jsEmail +
          '/' +
          this.jsPw
      )
      .then((response) => this.checkResponse(response.data, this.jsEmail, 'j'))
      .catch((error) => console.log(error));
  }

  loginA() {
    axios
      .get(
        'http://localhost:7070/user/authenticatea/' +
          this.aEmail +
          '/' +
          this.aPw
      )
      .then((response) => this.checkResponse(response.data, this.aEmail, 'a'))
      .catch((error) => console.log(error));
  }

  // Method that leads to another page if successful
  checkResponse(response: boolean, id: string, type: string) {
    if (response) {
      this.$store.commit('SET_USER_ID', id);
      if (type === 'j') {
        this.$router.push('job-seeker').catch((error) => console.log(error));
        // this.$store.commit('setUserId', id);
      } else if (type === 'e') {
        this.$router.push('employer').catch((error) => console.log(error));
        // this.$store.commit('setUserId', id);
      } else {
        this.$router.push('admin').catch((error) => console.log(error));
        // this.$store.commit('setUserId', id);
      }
    }
  }
}
</script>
