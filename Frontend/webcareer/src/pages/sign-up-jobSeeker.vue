<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
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

    <q-page>
      <!-- Inputs -->
      <div style="max-width = 75 %; padding-top:5%">
        <div
          class="row justify-center full-height full-width text-center"
          style="padding-bottom: 1%;"
        >
          <div class="q-gutter-md" style="max-width: 300px;">
            <div class="q-pa-md q-gutter-sm">
              <q-banner rounded class="bg-primary text-white">
                Job Seeker Sign-up Form
              </q-banner>
            </div>

            <p>
              <b>Please fill in this form to create a Job Seeker account.</b>
            </p>
            <hr />
            <q-input outlined v-model="firstName" label="First Name" />
            <q-input outlined v-model="lastName" label="Last Name" />
            <q-input outlined v-model="email" label="Email" />

            <q-input
              outlined
              v-model="password"
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
          </div>
        </div>
        <!-- Cards to choose the plan -->
        <div class="row justify-center full-height full-width text-center">
          <div class="q-gutter-sm">
            <p style="font-size: 100%;"><b>Select type of account</b></p>

            <div class="row">
              <q-card flat bordered class="my-card">
                <div>
                  <div>
                    <q-card-section>
                      <div class="text-h6">Basic Account</div>
                    </q-card-section>

                    <q-card-section class="q-pt-none">
                      You can only view job postings but not apply. Free.
                    </q-card-section>
                  </div>
                  <div style="margin-bottom: 0px;">
                    <q-separator inset />

                    <q-radio v-model="account_type" val="basic" label="Free" />
                  </div>
                </div>
              </q-card>

              <q-card flat bordered class="my-card">
                <q-card-section>
                  <div class="text-h6">Prime Account</div>
                </q-card-section>

                <q-card-section class="q-pt-none">
                  You can apply to up to five jobs. A monthly charge of $10 will
                  be applied
                </q-card-section>

                <q-separator inset />

                <q-radio
                  v-model="account_type"
                  val="prime"
                  label="Prime (10$/month)"
                />
              </q-card>

              <q-card flat bordered class="my-card">
                <q-card-section>
                  <div class="text-h6">Gold Account</div>
                </q-card-section>

                <q-card-section class="q-pt-none">
                  You can apply to as many jobs as you wants. A monthly charge
                  of $20 will be applied.
                </q-card-section>

                <q-separator inset />

                <q-radio
                  v-model="account_type"
                  val="gold"
                  label="Gold (20$/month)"
                />
              </q-card>
            </div>
          </div>
        </div>

        <!-- Buttons -->
        <div
          class="row justify-center full-height text-center"
          style="padding-top: 1%;"
        >
          <div style="padding-right: 5%;">
            <q-btn
              to="login"
              color="white"
              flat
              text-color="black"
              label="Cancel"
            />
          </div>
          <div style="padding-left: 5%;">
            <q-btn
              text-color="primary"
              outline
              rounded
              label="Sign-Up"
              @click="signUp()"
            />
          </div>
        </div>
      </div>
    </q-page>
  </q-layout>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      account_type: 'basic',
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      jobSeeker: {
        firstName: '',
        lastName: '',
        email: '',
        password: ''
      },
      jsIsPw: true
    };
  },
  methods: {
    signUp() {
      this.createJobSeekerObject();
      axios
        .post(
          'http://localhost:7070/user/newJobSeeker/' + this.account_type,
          this.jobSeeker
        )
        .then(result => this.checkResultOfAdding(result.data))
        .catch(e => console.log(e));
    },
    createJobSeekerObject() {
      this.jobSeeker.firstName = this.firstName;
      this.jobSeeker.lastName = this.lastName;
      this.jobSeeker.email = this.email;
      this.jobSeeker.password = this.password;
      // this.jobSeeker.jobSeekerCategory = this.account_type;
    },
    checkResultOfAdding(r) {
      if (r === true) {
        this.resetInfos();
        this.$router.push('login');
      }
    },
    resetInfos() {
      this.email = '';
      this.password = '';
      this.firstName = '';
      this.lastName = '';
      this.account_type = 'basic';
    }
  }
};
</script>
