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
                label="Reload Contact information from Applicants"
                @click="getAllInfo()"
              />
            </div>
            <div class="col">
              <div class="q-pl-xl">
                <div>
                  <q-table
                    title="Your Contacts"
                    @row-click="clickedRow"
                    :data="rowsForEmployers"
                    :columns="columnsForEmployers"
                    row-key="id"
                  >
                  <template v-slot:body-cell-actions="props">
                      <q-td :props="props">
                        <q-btn
                          dense
                          round
                          flat
                          color="grey"
                          @click="closeJob(JobRow.applicationId)"
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
          applicationId: ''
        },
      columnsForEmployers: [
              {
                name: 'id',
                required: true,
                label: 'ID',
                field: 'id',
                align: 'left',
                style: 'width: 50px',
              //   field: row => row.name,
              //   format: val => `${val}`,
                sortable: true
              },
              { name: 'applicationId', align: 'center', label: 'Application ID', field: "applicationId", sortable: true},
              { name: 'employerId', label: 'Employer ID', field: "employerId", sortable: true},
              { name: 'info', label: 'Info', field: 'info', sortable: true},
              { name: 'jobSeekerId', label: 'Job Seeker ID', field: 'jobSeekerId', sortable: true},
              { name: 'status', label: 'Status', field: "status", sortable: true},
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
     getAllInfo(){
      axios
      .get(this.baseUrl + 'help/' + this.$store.getters.getUserId)
      .then(res=>this.rowsForEmployers = res.data)
      .catch(e=>console.log(e));
    },
    closeJob(id){
      axios
      .post(this.baseUrl + 'help/close/' + id)
      .then(res => console.log(res.data));
    } ,
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
        },
    clickedRow(evt, row){
      this.fixedclickedRow = true
      console.log('row application Id' + row.applicationId)
      this.JobRow.jobId  = row.applicationId
    }   
  }
};
</script>

