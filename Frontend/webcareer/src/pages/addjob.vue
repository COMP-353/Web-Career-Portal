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
          <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
            <q-input
              outlined
              v-model="job.jobCategory.category"
              label="Category name"
              hint="Job category"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />

            <div class="q-pa-md">
              <q-input
                v-model="job.description"
                label="Description"
                hint="Description of the position"
                outlined
                type="textarea"
              />
            </div>
            <q-input
              outlined
              v-model="job.title"
              label="Job title"
              hint="What is the position you would like to fulfill"
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />
            <q-input
              outlined
              v-model="job.jobStatus.status"
              label="Job jobStatus"
              hint="Active/Inactive"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />

            <!-- <div class="q-pa-md">
              <q-input
                outlined
                v-model="job.date"
                mask="date"
                :rules="['date']"
                label="Date"
              >
                <template v-slot:append>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy
                      ref="qDateProxy"
                      transition-show="scale"
                      transition-hide="scale"
                    >
                      <q-date
                        v-model="job.date"
                        @input="() => $refs.qDateProxy.hide()"
                      />
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div> -->
            <!-- <q-input
              filled
              v-model="name"
              label="Date posted"
              hint="Must be of type DD-MM-YYYY"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            /> -->

            <q-toggle v-model="accept" label="I accept the license and terms" />

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
import EHeader from 'components/EHeader.vue'
import axios from 'axios'
export default {
  components:{
EHeader
  },
  data () {
    return {
        accept: false,
         employer:{
            email:'',
            status:'',
            accountBalance:'',
            employerCategory:''
          },
        job:{
          title:'',
          jobStatus:{
            status:'',
            description:''
          },
          description:'',
          jobCategory: {
            category:'',
            description:''
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
    this.employer.email = this.$store.getters.getUserId;
    }
  },
  methods: {
    // getEmployer(){
    //   axios.get('http://localhost:7070/user/employer/'+ this.employer.email).then(res => this.job.employer = res).catch(e => console.log(e))
    // },
    onSubmit () {
      if (this.accept !== true) {
        this.$q.notify({
          color: 'red-5',
          textColor: 'white',
          icon: 'warning',
          message: 'You need to accept the license and terms first'
        })
      }
      else {
        // this.$q.notify({
        //   color: 'green-4',
        //   textColor: 'white',
        //   icon: 'cloud_done',
        //   message: 'Submitted'
        // })
        
        axios.post('http://localhost:7070/job/newJob/' + this.employer.email, this.job).catch(e => console.log(e))
        this.$q.reset()
      }
    },

logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },

    onReset () {
      this.job.title = ''
      this.job.description = '' 
      this.accept = false
      this.job.jobStatus.status = ''
      // this.job.date = ''
      this.job.jobCategory.category = ''
    }
  }
}
</script>
