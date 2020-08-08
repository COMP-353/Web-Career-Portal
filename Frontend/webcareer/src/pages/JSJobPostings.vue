<style lang="sass" scoped>
.my-card
    width: 100%
    max-width: 250px
</style>

<template>
  <q-layout view="hHh LpR fFf">
    <JSHeader />
      <router-view />

    <q-page-container style="height: 100%;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6 ">
            Welcome back {{ getGreetingField()}}!
          </div>
        </q-card-section>
      </q-card>

      <div class="row">
        <div class="col">
          <div class="q-pl-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">Job ID</th>
                  <th class="text-left">Job position</th>
                  <th class="text-left">Employer email</th>
                  <th class="text-left">Description</th>
                  <th class="text-left">Category</th>
                  <th class="text-left">Status</th>
                  <th class="text-right">Apply!</th>
		  <th class="text-right">Help!</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for='job in jobList' v-bind:key='job.jobId'>
                  <td class="text-left width:10">{{job.jobId}}</td>
                  <td class="text-left">{{job.title}}</td>
                  <td class="text-left">{{job.employer.email}}</td>
                  <td class="text-left">{{job.description}}</td>
                  <td class="text-left">{{job.jobCategory.category}}</td>
                  <td class="text-left">{{job.jobStatus.status}}</td>
                  <td class="text-right">
                    <div class="q-pa-md q-gutter-sm">
                      <q-btn
                        @click="createApplication(job.jobId)"
                        color="blue"
                        icon="send"
                        label="Apply "
                        size="sm"
                      />
                    </div>
                  </td>
		    <td class="text-right">
                    <div class="q-pa-md q-gutter-sm">
                      <q-btn
			@click="someHelp=true"
                        label="Help "
                        size="sm"
			color="orange"
                        icon="send"
                      />
                    </div>
                  </td>
                </tr>
              </tbody>
            </q-markup-table>

<q-dialog v-model="someHelp">
      <q-card>
        <q-card-section>
          <div class="text-h6">Help</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <p>What is your issue?</p>
	  <q-input
                v-model="Help.info"
                label="Description"
                outlined
                type="textarea"
                :rules="[
                (val) => (val && val.length > 0 || this.accept ==false) || 'Please provide a description of the job',
              ]"
              />
        </q-card-section>

 <q-card-actions align="right">
          <q-btn flat @click="CreateHelp()" label="Submit" color="primary" v-close-popup />
	  <q-btn flat label="Cancel" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>


            <q-dialog v-model="showDialog">
                  <q-card>
                      <q-card-section>
                        <div class="text-h6">Application Sent</div>
                      </q-card-section>

                    <q-card-section class="q-pt-none">
                      Your application has been sent to the employer.
                    </q-card-section>

                  <q-card-actions align="right">
                    <q-btn flat label="OK" color="green" v-close-popup></q-btn>
                  </q-card-actions>
              </q-card>
            </q-dialog>
          </div>
        </div>
      </div>
      <!-- </q-body> -->
    </q-page-container>
  </q-layout>
</template>

<script>
import { Dialog } from 'quasar'

import axios from 'axios';
import JSHeader from '../components/JSHeader.vue'

export default {
    components:{
        JSHeader
    },
  data() {
    return {
      someHelp: false,
      Help: {
      info:''
      },
      showDialog:false,
      creditcard: 'automatic',
      checkingacc: 'automatic',
      paymentmethod: 'creditcard',
      tab: 'apps',
      accountType:'basic',
      innerProfileTab:'innerprofile',
      baseUrl: 'http://localhost:7070/',
      jobList:[], 
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      },
      application:{
        applicationDate: '',
        applicationStatus: {
          statusId:1
        },
        jobseeker:{
          email: ''
        },
        job:{
          jobId:''
        }
      },
      amount:0
    }
  },



  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.jobSeeker.email = this.$store.getters.getUserId;
      this.application.jobseeker.email = this.$store.getters.getUserId;

     this.getUserData();
     this.getJobList(this.jobSeeker.email);
     this.getAccountCategory();
    }
  },

  methods: {

    CreateHelp(){
    axios
        .post(
          this.baseUrl+'help/'
        )
},

    assignJsObject(res) {
      console.log(res)
      this.jobSeeker.firstName = res.firstName;
      this.jobSeeker.lastName = res.lastName;
      this.jobSeeker.accountBalance = res.accountBalance;
      this.jobSeeker.status = res.status;
    },
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },
    getAccountCategory(){
       axios
        .get(this.baseUrl + 'user/getCat/' + this.jobSeeker.email)
        .then((res) => this.accountType = res.data)
        .catch((e) => console.log(e));
    },
    getUserData(){
      this.amount = 0
      axios
        .get(this.baseUrl + 'user/jobseeker/' + this.jobSeeker.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    },
    makeAPayment(){
      axios.put(this.baseUrl +'user/pay/'+ this.jobSeeker.email +'/' +this.amount).then
      (this.getUserData()).catch(e => console.log(e))
    },

    getJobList(user_email){
      axios
        .get(this.baseUrl + 'job/notapplied/jobseeker/'+ user_email)
        .then(res => this.jobList = res.data);
    },

    getGreetingField(){
      return this.jobSeeker.firstName != null ? this.jobSeeker.firstName : this.jobSeeker.email;
    },


    

    createApplication(jobId){
      this.application.job.jobId = jobId;
      this.application.applicationDate = new Date();

      var config = {
            method: 'post',
            url: 'http://localhost:7070/application/newApplication',
            headers: { 
              'Content-Type': 'application/json'
            },
           data : JSON.stringify(this.application)  
        };

        console.log(JSON.stringify(this.application));


        axios(config)
          .then(function (response) {
          return response;
        });
        let i = this.jobList.map(job => job.jobId).indexOf(jobId); // find index of your object
        this.jobList.splice(i, 1);
        this.showDialog = true;
    }
  },
};
</script>
