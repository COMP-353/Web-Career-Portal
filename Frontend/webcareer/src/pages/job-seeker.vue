<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <!-- <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" /> -->
          <!-- </q-avatar> -->
          Web Career Portal
        </q-toolbar-title>
        <!-- <q-toolbar-subtitle> -->
        <q-btn label="logout" />
        <!-- </q-toolbar-subtitle> -->
      </q-toolbar>

      <q-tabs v-model="tab" align="left">
        <q-tab name="page1" label="Home" />
        <q-tab name="page2" label="Applications" />
        <q-tab name="page3" label="Profile" />
      </q-tabs>
    </q-header>

    <!-- <q-page-container style="height: 250px;"> -->
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="page1">
        <q-page-container>
          <q-card flat bordered class="my-card">
            <q-card-section>
              <div class="text-h6">
                Welcome back {{ this.jobSeeker.firstName }}!
              </div>
              <div class="text-subtitle2">
                The job opportunity list has been updated!
              </div>
            </q-card-section>

            <q-card-section class="q-pt-none">
              {{ this.jobSeeker.firstName }}
            </q-card-section>
          </q-card>
          <!-- <router-view /> -->
          <!-- </q-page-container> -->

          <!-- <q-body> -->
          <div class="row">
            <div class="col">
              <div class="q-pl-xl">
                <q-markup-table>
                  <thead>
                    <tr>
                      <th class="text-left">Company</th>
                      <th class="text-left">Job position</th>
                      <th class="text-right">ID</th>
                      <th class="text-right">Email</th>
                      <th class="text-right">Date of posting</th>
                      <th class="text-right">Apply!</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="text-left">Google</td>
                      <td class="text-left">Software developer</td>
                      <td class="text-right">1</td>
                      <td class="text-right">mia@gmail.com</td>
                      <td class="text-right">2020-08-02</td>
                      <td class="text-right">
                        <div class="q-pa-md q-gutter-sm">
                          <q-btn
                            color="blue"
                            icon="mail"
                            icon-right="send"
                            label=" "
                            size="sm"
                          />
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </q-markup-table>
              </div>
            </div>
            <div class="col-3 row vertical-middle q-pl-xl">
              <div class="q-pa-md flex flex-center">
                <q-knob
                  readonly
                  show-value
                  font-size="20px"
                  v-model="this.jobSeeker.accountBalance"
                  size="250px"
                  :thickness="0.1"
                  color="teal"
                  track-color="grey-3"
                  class="q-ma-md"
                >
                  Balance {{ this.jobSeeker.accountBalance }}$
                </q-knob>
              </div>
            </div>
          </div>
          <!-- </q-body> -->
        </q-page-container>
      </q-tab-panel>

      <q-tab-panel name="page2"
        ><q-page-container>Page 2</q-page-container>
      </q-tab-panel>

      <q-tab-panel name="page3">
        <q-page-container>Page 3</q-page-container></q-tab-panel
      >
    </q-tab-panels>
  </q-layout>
</template>

<script>
import axios from 'axios';


export default {
  data() {
    return {
      tab: 'page1',
      baseUrl: 'http://localhost:7070/',
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      }
    }
  },

  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.jobSeeker.email = this.$store.getters.getUserId;
      axios
        .get(this.baseUrl + 'user/jobseeker/' + this.jobSeeker.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    }
  },

  methods: {
    assignJsObject( res) {
      this.jobSeeker.firstName = res.firstName;
      this.jobSeeker.lastName = res.lastName;
      this.jobSeeker.accountBalance = res.accountBalance;
      this.jobSeeker.status = res.status;
    }
  },
};
</script>
