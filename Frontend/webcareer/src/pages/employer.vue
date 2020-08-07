<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          Career Portal
        </q-toolbar-title>
        <q-btn label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />

        <q-route-tab to="/addpayment" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job"/>

        <q-route-tab to="/listofjobs" label="List of Jobs" />
      </q-tabs>
    </q-header>

     <q-page-container style="height: 300px;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Welcome back! {{ this.$store.getters.getUserId }}
          </div>
          <div class="text-subtitle2">
            The job-seeker list has been updated!
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ lorem }}
        </q-card-section>
      </q-card>
      <router-view />
    </q-page-container>

    <q-body>
      <div class="row">
        <div class="col">
          <div class="q-px-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">Job ID</th>
                  <th class="text-left">Poster</th>
                  <th class="text-left">Job title</th>
                  <th class="text-left">Job description</th>
                  <th class="text-left">Date of application</th>
                </tr>
              </thead>
              <tbody>
                <!-- //<div v-for='applications in applicationList' v-bind:key='applications'>  -->
                    <tr v-for='application in applicationList' v-bind:key='application.applicationId'>
                      <td class="text-left">{{application.job.jobId}}</td>
                      <td class="text-left">{{application.jobseeker.email}}</td>
                      <td class="text-left">{{application.job.title}}</td>
                      <td class="text-left">{{application.job.description}}</td>
                      <td class="text-left">{{convertDate(application.applicationDate)}}</td>
                    </tr>
                  <!-- //  </div> -->
                  <div class="q-pa-lg flex flex-center">
                    <q-pagination
                      v-model="current"
                      :max="5"
                      :direction-links="true"
                      :boundary-links="true"
                      icon-first="skip_previous"
                      icon-last="skip_next"
                      icon-prev="fast_rewind"
                      icon-next="fast_forward"
                    >
                    </q-pagination>
                  </div>
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
              v-model="employer.accountBalance"
              size="250px"
              :thickness="0.1"
              color="teal"
              track-color="grey-3"
              class="q-ma-md"
              max="2000"
            >
              Balance ${{ employer.accountBalance }}
            </q-knob>
          </div>
        </div>
      </div>
    </q-body>
  </q-layout>
</template>

<script>
import axios from 'axios';

export default {
  data () {
    return {
      baseUrl: 'http://localhost:7070/',
      current: 3,
      employer: [],
      value: 71,
      applicationList: []
    }
  },
  mounted(){
    console.log('Mounted on employer page');
  
    console.log('getting store data:' + this.$store.getters.getUserId);
    if(this.$store.getters.getUserId === ''){
      console.log('id is indeed empty');
      this.$router.push('/');
    }
    this.getApplicationList(this.$store.getters.getUserId);
    this.getEmployer(this.$store.getters.getUserId);
  },

methods: {
     
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    	},

    getApplicationList(user_email){
      axios
        .get(this.baseUrl + 'application/employer/'+ user_email)
        .then(res => this.applicationList = res.data);
    },

    convertDate(date_str){
      const date = new Date(date_str)
      return new Intl.DateTimeFormat('en', { year: 'numeric', month: 'short', day: '2-digit' }).format(date); 
    },

    getEmployer(user_email){
        axios
        .get(this.baseUrl + 'user/employer/'+ user_email)
        .then(res => this.employer = res.data);
    }
    },
 };

</script>
