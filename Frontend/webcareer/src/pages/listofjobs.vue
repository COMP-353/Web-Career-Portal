<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />
    <q-page-container style="height: 300px;">
      <q-card flat bordered class="my-card">
        <br><br>
        <q-card-section>
          <div class="text-h6">
            Here you can find your personal list of jobs!
          </div>
          <div class="text-subtitle2"></div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ lorem }}
        </q-card-section>
      </q-card>
      <router-view />
    </q-page-container>

    <q-body>

    <div class="q-pa-xl">
      <div class="column" style="height: 150px">
          <div class="col">
            <q-btn 
              outline
              rounded
              color="primary"
              label="Reload Personal Jobs"
              @click="getAllJobsByID()"
            />
          </div>
      <div class="col">
        <div class="q-pl-xl">
          <div>
            <q-table
              title="Employers"
              :data="rowsForEmployers"
              :columns="columnsForEmployers"
              row-key="jobId"
            />
            </div>
          </div>
        </div>
      </div>
    </div>


    </q-body>

  </q-layout>
</template>


<script>
import axios from 'axios';
import EHeader from 'components/EHeader.vue'

export default {
  components:{
EHeader
  },  data () {
    return {
      current: 3,
      value: 71,
      baseUrl: 'http://localhost:7070/',
      columnsForEmployers: [
              {
                name: 'jobId',
                required: true,
                label: 'JobID',
                field: 'jobId',
                align: 'left',
              //   field: row => row.name,
              //   format: val => `${val}`,
                sortable: true
              },
              { name: 'category', align: 'center', label: 'Category', field: 'category', sortable: true },
              { name: 'description', label: 'Description', field: 'description', sortable: true, style: 'width: 10px' },
              { name: 'application_applicationId', label: 'Application ID', field: 'application_applicationId', sortable: true },
              { name: 'title', label: 'Title', field: 'title', sortable: true },
              { name: 'employer_email', label: 'Employer Email', field: 'employer_email', sortable: true },
              { name: 'job_category', label: 'Job Category', field: 'job_category', sortable: true },
              { name: 'job_status', label: 'Job Status', field: 'job_status', sortable: true },
              // { name: 'category', label: 'Category', field: 'calcium', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
            ],
            rowsForEmployers: [],
            paginationForActivities: {
            sortBy: 'desc',
            descending: false,
            page: 1,
            rowsPerPage: 10
            // rowsNumber: xx if getting data from a server
        }
    }
  },

computed:{
  pagesNumberForActivities () {
        return Math.ceil(this.rowsForActivities.length / this.paginationForActivities.rowsPerPage)
        },
  mounted(){
      console.log('Mounted on employer page')
      console.log('getting store data:' + this.$store.getters.getUserId)
      if(this.$store.getters.getUserId === ''){
        console.log('id is indeed empty')
        this.$router.push('/')
      } else{
        this.getAllJobsByID()
      }
  } 
},

methods: {
     getAllJobsByID(){
      axios
      .get(this.baseUrl + 'job/employer/' + this.$store.getters.getUserId)
      .then(res=>this.rowsForEmployers = res.data)
      .catch(e=>console.log(e));
    }
   },
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},
  }
</script>
