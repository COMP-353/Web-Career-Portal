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
        <div class="q-pa-md" style="padding-left: 35%; padding-top: 15%;">
          <div class="q-gutter-y-md" style="max-width: 40%;">
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
              <!-- Job seeker sign in -->
              <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="seeker">
                  <div>
                    <q-input
                      outlined
                      v-model="jsEmail"
                      label="Email Address"
                      class="loginInputs"
                    />
                    <q-input
                      outlined
                      v-model="jsPw"
                      label="Password"
                      :type="jsIsPw ? 'password' : 'text'"
                      class="loginInputs"
                    >
                      <template v-slot:append>
                        <q-icon
                          :name="jsIsPw ? 'visibility_off' : 'visibility'"
                          @click="jsIsPw = !jsIsPw"
                        />
                      </template>
                    </q-input>

                    <q-separator></q-separator>
                    <div class="row">
                      <div class="buttons">
                        <q-btn
                          rounded
                          color="primary"
                          outline
                          label="Log in"
                          @click="loginJs()"
                        />
                      </div>
                      <div class="buttons">
                        <q-btn
                          to="sign-up-jobSeeker"
                          color="white"
                          rounded
                          outline
                          text-color="primary"
                          label="Sign-Up"
                        />
                      </div>
                    </div>
                    <div class="buttons">
                      <q-btn
                        to="forgotPassword"
                        rounded
                        flat
                        color="white"
                        text-color="primary"
                        label="Forgot Password"
                      />
                    </div>
                  </div>
                </q-tab-panel>
                <!-- Employer sign in -->
                <q-tab-panel name="employer">
                  <div>
                    <div class="q-mtb-lg">
                      <q-input
                        outlined
                        v-model="eEmail"
                        label="Email Address"
                        class="loginInputs"
                      />
                    </div>

                    <div class="q-mtb-lg">
                      <q-input
                        v-model="ePassword"
                        outlined
                        :type="eIsPw ? 'password' : 'text'"
                        label="Password"
                        class="loginInputs"
                      >
                        <template v-slot:append>
                          <q-icon
                            :name="eIsPw ? 'visibility_off' : 'visibility'"
                            class="cursor-pointer"
                            @click="eIsPw = !eIsPw"
                          />
                        </template>
                      </q-input>
                    </div>
                    <q-separator></q-separator>
                    <div class="row">
                      <div class="buttons">
                        <q-btn
                          color="white"
                          text-color="primary"
                          rounded
                          outline
                          label="Log in"
                          @click="loginEmployer()"
                        />
                      </div>
                      <div class="buttons">
                        <q-btn
                          to="sign-up-employer"
                          color="white"
                          text-color="primary"
                          outline
                          rounded
                          label="Sign-Up"
                        />
                      </div>
                    </div>
                    <div class="buttons">
                      <q-btn
                        to="forgotPassword"
                        color="white"
                        rounded
                        outlined
                        flat
                        text-color="black"
                        label="Forgot Password"
                      />
                    </div>
                  </div>
                </q-tab-panel>

                <!-- Admin log in -->
                <q-tab-panel name="admin">
                  <div>
                    <q-input
                      outlined
                      v-model="aEmail"
                      label="Email Address"
                      class="loginInputs"
                    />

                    <q-input
                      v-model="aPw"
                      outlined
                      class="loginInputs"
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
                    <div class="buttons">
                      <q-btn
                        color="white"
                        rounded
                        outline
                        text-color="primary"
                        label="Log in"
                        @click="loginA()"
                      />
                    </div>
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
        this.$router.push('js').catch((error) => console.log(error));
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

<style lang="sass">
.loginInputs
  padding: 1% 1% 2% 1%

.buttons
  padding: 2% 1% 1% 1%
</style>