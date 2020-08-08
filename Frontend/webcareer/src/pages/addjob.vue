<template>

  <q-layout view="hHh LpR fFf">
    <EHeader />

    <q-page-container style="height: 100%;">
      <router-view />
      <q-body>
        <div class="text-h6">
          Add a new job posting
        </div>
        <div class="window-width row justify-center items-center">
          <!-- <div></div> -->
          <q-form @submit="onSubmit" class="q-gutter-md">
                 <q-select  filled  v-model="job.jobCategory.categoryId" :options="options" label="CATEGORY" emit-value map-options></q-select>
                 <q-select  filled  v-model="job.jobStatus.statusId" :options="jobstatusoptions" label="STATUS" emit-value map-options></q-select>

              <q-input
                v-model="job.description"
                label="Description"
                outlined
                type="textarea"
                :rules="[
                (val) => (val && val.length > 0 || this.accept ==false) || 'Please provide a description of the job',
              ]"
              />
      
            <q-input
              outlined
              v-model="job.title"
              label="Job title"
              :rules="[
                (val) => (val && val.length > 0 || this.accept ==false) || 'Please provide a title for the job',
              ]"
            />

            <q-toggle v-model="accept" label="I accept the license and terms" />

            <q-input disable readonly :rules="[
                   (val) => (this.accept == true) || 'Please accept the trems and conditions',
                ]"/>
            <div>
              <q-btn label="Submit" type="submit" color="primary" />
              <q-btn
                label="Reset"
                type="reset"
                color="primary"
                flat
                class="q-ml-sm"
              />
            </div>
          </q-form>
        </div>
      </q-body>
    </q-page-container>
  </q-layout>
</template>

<script>
import { Notify } from 'quasar'
import EHeader from 'components/EHeader.vue'
import axios from 'axios'
export default {
  components:{
EHeader
  },
  data () {
    return {
        accept: false,
        baseUrl: 'http://localhost:7070/',
        options: [],
        jobstatusoptions: [],
        job:{
          title:'',
          description:'',
          jobStatus:{
            statusId: ''
          },
          jobCategory: {
            categoryId: ''
          },
          employer: {
            email:''
          } 
      }
    }
  },
  mounted(){
      if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } 
    else {
    this.fetchCategoriesSelectionList();
    this.fetchJobStatussSelectionList();
    }
  },
  methods: {
    onSubmit () {
      if (this.accept == true) {
       
        this.job.employer.email = this.$store.getters.getUserId;
        var config = {
            method: 'post',
            url: 'http://localhost:7070/job/newJob',
            headers: { 
              'Content-Type': 'application/json'
            },
           data : JSON.stringify(this.job)  
        };

        axios(config)
          .then(function (response) {
           console.log(JSON.stringify(response.data));
           return response;
        });
        this.resetFormData();
      }
    },

    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },

    fetchCategoriesSelectionList(){
      axios
        .get(this.baseUrl + 'jobCategory/all')
        .then(res => {for(var i in res.data){
         let item = {
           value: res.data[i].categoryId,
           label:res.data[i].category
         }
          this.options.push(item);
          if(i == 0){
            this.job.jobCategory.categoryId = item.value;
          }
        }});
    },

    fetchJobStatussSelectionList(){
      axios
        .get(this.baseUrl + 'jobStatus/all')
        .then(res => {for(var i in res.data){
         let item = {
           value: res.data[i].statusId,
           label:res.data[i].status
         }
          this.jobstatusoptions.push(item);
           if(i == 0){
            this.job.jobStatus.statusId = item.value;
          }
        }});
    },

    resetFormData () {
      this.accept = false
      this.job.title = ''
      this.job.description = '' 
    }
  }
}
</script>
