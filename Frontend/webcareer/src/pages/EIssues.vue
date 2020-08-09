<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />

    <q-page-containerstyle="
        height: 300px;
        padding-right: 1%;
        padding-left: 1%;
        padding-top: 7%;
      "
    >
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Help these people
          </div>
          <div class="text-subtitle2"></div>
        </q-card-section>
      </q-card>
      <router-view />
   


<q-body>
<div class="q-pa-xl">
      <div class="column" style="height: 150px">
          <div class="col">
            <q-btn 
              outline
              rounded
              color="primary"
              label="Reload Issues"
              @click="getIssues()"
            />
          </div>

<div class="col">
<div class="q-pa-md">
<div>
    <q-table
      title="Issues"
      @row-click="clickedRow"
      :data="rowdata"
      :columns="columns"
      row-key="id"
      :selected-rows-label="getSelectedString"
      selection="multiple"
      :selected.sync="selected"
    >
<template v-slot:body-cell-actions="props">
                      <q-td :props="props">
                        <q-btn
                          dense
                          round
                          flat
                          color="grey"
                          @click="deleteIssue()"
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
  },
  data () {
     return {
current: 3,
      value: 71,
      baseUrl: 'http://localhost:7070/',
      columns: [
        {
          name: 'Help_Id',
          required: true,
          label: 'Help_Id',
          align: 'left',
          field: 'id',
          //format: val => `${val}`,
          sortable: true
        },
        { name: 'Job_Seeker_id', align: 'center', label: 'Job Seeker email', field: row => row.jobseeker.email, sortable: true },
        { name: 'Employer_id', label: 'Employer email', field: row => row.employer.email, sortable: true },
        { name: 'application_id', label: 'Application id', field: row => row.Application.applicationId, sortable: true },
        { name: 'status_', label: 'Status', field: row => row.Application.status, sortable: true },
        { name: 'info_', label: 'Info', field:'info', sortable: true },
      ],
      rowdata: [],
	    paginationForActivities: {
            sortBy: 'desc',
            descending: false,
            page: 1,
            rowsPerPage: 10
            
        },

    }
  },


computed:{
  pagesNumberForActivities () {
        return Math.ceil(this.rowsForActivities.length / this.paginationForActivities.rowsPerPage)
        }},
  beforeMount(){
      console.log('Mounted on employer page')
      console.log('getting store data:' + this.$store.getters.getUserId)
      if(this.$store.getters.getUserId === ''){
        console.log('id is indeed empty')
        this.$router.push('/')
      } else{
        this.getIssues()
      }
},


	methods:{

		getIssues() {
			axios
        		.get(this.baseUrl +'help/'+ this.$store.getters.getUserId) 
        		.then(res => this.rowdata = res.data).catch(e=>console.log(e));
		},


		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		}

    }
};
</script>
