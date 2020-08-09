<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />
    <q-page-container style="height: 300px; padding: 7% 1% 0% 1%;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">Welcome back! {{ this.email }}</div>
          <div class="text-subtitle2">
            The application list has been updated!
          </div>
        </q-card-section>
      </q-card>
      <router-view />

      <q-body>
        <div style="padding-top: 1%;">
          <q-btn
            label="Reload"
            outline
            color="primary"
            rounded
            @click="reload()"
          />
        </div>
        <div class="row">
          <div class="col">
            <div class="q-pr-xs">
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
                  <tr
                    v-for="application in applicationList"
                    v-bind:key="application.applicationId"
                  >
                    <td class="text-left">{{ application.job.jobId }}</td>
                    <td class="text-left">{{ application.jobseeker.email }}</td>
                    <td class="text-left">{{ application.job.title }}</td>
                    <td class="text-left">{{ application.job.description }}</td>
                    <td class="text-left">
                      {{ convertDate(application.applicationDate) }}
                    </td>
                    <td class="text-right">
                      <div class="">
                          <q-btn
                            color="green"
                            icon="check"
                            label="Accept"
                            size="sm"
                            @click="updateApplication(application.applicationId, acceptedApplicationStatusId)"
                          />
                        </div>
                    </td>
                    <td class="text-right">
                      <div class="">
                        <q-btn
                          color="red"
                          icon="delete"
                          label=" Remove"
                          size="sm"
                          @click="deleteApplication(application.applicationId)"
                        />
                      </div>
                    </td>
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
            <div class="q-pa-md flex flex-right">
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
                <q-tooltip>
                  A negative balance means you have extra credit while a
                  positive balance shows how much you owe to the system.
                </q-tooltip>
                Balance ${{ employer.accountBalance }}
              </q-knob>
              <q-dialog v-model="showDialog">
                <q-card>
                  <q-card-section>
                    <div class="text-h6">Application Deleted</div>
                  </q-card-section>

                  <q-card-section class="q-pt-none">
                    Application has been successfully removed.
                  </q-card-section>

                  <q-card-actions align="right">
                    <q-btn flat label="OK" color="green" v-close-popup></q-btn>
                  </q-card-actions>
                </q-card>
              </q-dialog>
            </div>
          </div>
        </div>
      </q-body>
    </q-page-container>
  </q-layout>
</template>

<script>
import axios from 'axios';
import EHeader from 'components/EHeader.vue'
export default {
  components:{
EHeader
  },
  data () {
    return {
      showDialog:false,
      acceptedApplicationStatusId: 2,
      baseUrl: 'http://localhost:7070/',
      current: 3,
      employer: [],
      value: 71,
      applicationList: [],
      email:''
    }
  },
  beforeMount(){
    console.log('Mounted on employer page');
  
    console.log('getting store data:' + this.$store.getters.getUserId);
    if(this.$store.getters.getUserId === ''){
      console.log('id is indeed empty');
      this.$router.push('/');
    }
    this.email = this.$store.getters.getUserId;
    this.getApplicationList(this.$store.getters.getUserId);
    this.getEmployer();
  },

methods: {
     
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    	},

    getApplicationList(){
      axios
        .get(this.baseUrl + 'application/employer/'+ this.email)
        .then(res => this.applicationList = res.data);
    },

    convertDate(date_str){
      const date = new Date(date_str)
      return new Intl.DateTimeFormat('en', { year: 'numeric', month: 'short', day: '2-digit' }).format(date); 
    },

    getEmployer(){
        axios
        .get(this.baseUrl + 'user/employer/'+ this.email)
        .then(res => this.employer = res.data);
    },
     deleteApplication(applicationId){
      axios
        .delete(this.baseUrl + 'application/deleteApplication/'+ applicationId)
        .then(res => console.log(res.data));

         let i = this.applicationList.map(application => application.applicationId).indexOf(applicationId); // find index of your object
        this.applicationList.splice(i, 1);
        this.showDialog = true;
    },
     updateApplication(applicationId, statusId){
      axios
        .get(this.baseUrl + 'application/updateApplicationStatus/'+ applicationId+"/"+statusId)
        .then(res => console.log(res.data));
    },

    reload(){
      this.getEmployer();
      this.getApplicationList();
    }
    },
 };

</script>
