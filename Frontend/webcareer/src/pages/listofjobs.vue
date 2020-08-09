<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />
    <q-page-container
      style="
        height: 300px;
        padding-right: 1%;
        padding-left: 1%;
        padding-top: 7%;
      "
    >
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Here you can find your personal list of jobs!
          </div>
          <div class="text-subtitle2"></div>
        </q-card-section>
      </q-card>
      <router-view />

      <q-body>
        <div class="q-pa-md">
          <div class="column" style="height: 150px;">
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
                    title="Your Jobs"
                    @row-click="clickedRow"
                    :data="rowsForEmployers"
                    :columns="columnsForEmployers"
                    row-key="jobId"
                  >
                    <template v-slot:body-cell-actions="props">
                      <q-td :props="props">
                        <q-btn
                          dense
                          round
                          flat
                          color="grey"
                          @click="deleteJob(JobRow.jobId)"
                          icon="delete"
                        ></q-btn>
                      </q-td>
                    </template>
                  </q-table>
                </div>
              </div>
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
  },  data () {
    return {
      current: 3,
      value: 71,
      baseUrl: 'http://localhost:7070/',
      JobRow:
        {
          jobId: ''
        },
      columnsForEmployers: [
              {
                name: 'jobId',
                required: true,
                label: 'Job ID',
                field: 'jobId',
                align: 'left',
                style: 'width: 50px',
              //   field: row => row.name,
              //   format: val => `${val}`,
                sortable: true
              },
              { name: 'category', align: 'center', label: 'Category ID', field: row => row.jobCategory.categoryId, sortable: true},
              { name: 'job_category', label: 'Category Name', field: row => row.jobCategory.category, sortable: true},
              { name: 'description', label: 'Description', field: 'description', sortable: true},
              { name: 'title', label: 'Title', field: 'title', sortable: true},
              { name: 'employer_email', label: 'Employer Email', field: row => row.employer.email, sortable: true},
              { name: 'jobStatus', label: 'Job Status', field: row => row.jobStatus.status, sortable: true},
              { name: 'actions', label: "Actions", field: ''}
              // { name: 'category', label: 'Category', field: 'calcium', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
            ],
            rowsForEmployers: [],
            paginationForActivities: {
            sortBy: 'desc',
            descending: false,
            page: 1,
            rowsPerPage: 10
            // rowsNumber: xx if getting data from a server
        },
        
    }
  },

computed:{
  pagesNumberForActivities () {
        return Math.ceil(this.rowsForActivities.length / this.paginationForActivities.rowsPerPage)
        }
        },
  beforeMount(){
      console.log('Mounted on employer page')
      console.log('getting store data:' + this.$store.getters.getUserId)
      if(this.$store.getters.getUserId === ''){
        console.log('id is indeed empty')
        this.$router.push('/')
      } else{
        this.getAllJobsByID()
      }
  
},

methods: {
     getAllJobsByID(){
      axios
      .get(this.baseUrl + 'job/employer/' + this.$store.getters.getUserId)
      .then(res=>this.rowsForEmployers = res.data)
      .catch(e=>console.log(e));
    },
    deleteJob(id){
      axios
      .delete(this.baseUrl + 'job/deleteJob/' + id)
      .then(res => console.log(res.data));
    } ,
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
        },
    clickedRow(evt, row){
      this.fixedclickedRow = true
      console.log('row job ID' + row.jobId)
      this.JobRow.jobId  = row.jobId
      getAllJobsByID()
    }
        
  }
};
</script>
