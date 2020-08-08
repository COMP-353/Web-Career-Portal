<template>
  <q-layout view="hHh LpR fFf">
    <JSHeader />

    <q-page-container style="height=100%; padding-right:2%; padding-left:2%">
      <router-view />
      <div style="padding-top: 1%; padding-bottom: 1%;">
        <q-card flat bordered class="my-card">
          <q-card-section>
            <div class="text-h6">Welcome back {{ getGreetingField() }}.</div>
            <div class="text-subtitle2">
              The job opportunity list has been updated!
            </div>
          </q-card-section>
        </q-card>
      </div>
      <div class="row">
        <div class="col">
          <div class="q-pl-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">Job ID</th>
                  <th class="text-left">Employer Email</th>
                  <th class="text-left">Date posted</th>
                  <th class="text-left">Job position</th>
                  <th class="text-left">Description</th>
                  <th class="text-right"></th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="application in applicationList"
                  v-bind:key="application.applicationId"
                >
                  <td class="text-left">{{ application.job.jobId }}</td>
                  <td class="text-left">
                    {{ application.job.employer.email }}
                  </td>
                  <td class="text-left">
                    {{ convertDate(application.applicationDate) }}
                  </td>
                  <td class="text-left">{{ application.job.title }}</td>
                  <td class="text-left">{{ application.job.description }}</td>
                  <td class="text-right">
                    <div class="">
                      <q-btn
                        color="red"
                        icon="delete"
                        label=" Delete application"
                        size="sm"
                        @click="deleteApplication(application.applicationId)"
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
              <q-tooltip>
                A negative balance means you have extra credit while a positive
                balance shows how much you owe to the system.
              </q-tooltip>
              Balance {{ this.jobSeeker.accountBalance }}$
            </q-knob>
            <q-dialog v-model="showDialog">
              <q-card>
                <q-card-section>
                  <div class="text-h6">Application Deleted</div>
                </q-card-section>

                <q-card-section class="q-pt-none">
                  Your application has been successfully removed.
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn flat label="OK" color="green" v-close-popup></q-btn>
                </q-card-actions>
              </q-card>
            </q-dialog>
          </div>
        </div>
      </div>
    </q-page-container>
  </q-layout>
</template>

<script>
import axios from 'axios';
import JSHeader from '../components/JSHeader.vue'

export default {
  components:{
JSHeader
  },
  data() {
    return {
      showDialog:false,
      accountType:'basic',
      innerProfileTab:'innerprofile',
      baseUrl: 'http://localhost:7070/',
      applicationList: [],
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      },
    }
  },

  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.jobSeeker.email = this.$store.getters.getUserId;
      this.getUserData(this.jobSeeker.email);
     this.getJobList(this.jobSeeker.email);
     //console.log(this.jobList);
    //  this.getAccountCategory()
    }
  },

  methods: {
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
    // getAccountCategory(){
    //    axios
    //     .get(this.baseUrl + 'user/getCat/' + this.jobSeeker.email)
    //     .then((res) => this.accountType = res.data)
    //     .catch((e) => console.log(e));
    // },
    getUserData(user_email){
      this.amount = 0
      axios
        .get(this.baseUrl + 'user/jobseeker/' + user_email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    },

    getJobList(user_email){
      axios
        .get(this.baseUrl + 'application/jobseeker/'+ user_email)
        .then(res => this.applicationList = res.data);
    },

    convertDate(date_str){
      const date = new Date(date_str)
      return new Intl.DateTimeFormat('en', { year: 'numeric', month: 'short', day: '2-digit' }).format(date); 
    }
    ,
    getGreetingField(){
      return this.jobSeeker.firstName != null ? this.jobSeeker.firstName : this.jobSeeker.email;
    },

    deleteApplication(applicationId){
      axios
        .delete(this.baseUrl + 'application/deleteApplication/'+ applicationId)
        .then(res => console.log(res.data));

         let i = this.applicationList.map(application => application.applicationId).indexOf(applicationId); // find index of your object
        this.applicationList.splice(i, 1);
        this.showDialog = true;
    }
    // makeAPayment(){
    //   axios.put(this.baseUrl +'user/pay/'+ this.jobSeeker.email +'/' +this.amount).then
    //   (this.getUserData()).catch(e => console.log(e))
    // }
  },
};
</script>
