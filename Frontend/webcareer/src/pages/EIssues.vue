<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />

    <q-page-container style="height: 300px;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Help these people
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
              label="Reload Issues"
              @click="getIssues()"
            />
          </div>



      <div class="q-pa-md">
    <q-table
      title="Issues"
      :data="data"
      :columns="columns"
      row-key="name"
      :selected-rows-label="getSelectedString"
      selection="multiple"
      :selected.sync="selected"
    />



    <div class="q-mt-md">
      Selected: {{ JSON.stringify(selected) }}
    </div>
  </div>
        
	
     
            </q-markup-table>
          </div>
        </div>
      </div>
    </q-body>
  </q-layout>
</template>


<script>
import { Dialog } from 'quasar'

import axios from 'axios';
import EHeader from 'components/EHeader.vue'


export default {
  components:{
EHeader
  },
  data () {
     return {
      baseUrl: 'http://localhost:7070/',
      selected: [],
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
      data: [],
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
        },
  mounted(){
      console.log('Mounted on employer page')
      console.log('getting store data:' + this.$store.getters.getUserId)
      if(this.$store.getters.getUserId === ''){
        console.log('id is indeed empty')
        this.$router.push('/')
      } else{
        this.getIssues()
      }
  } 
},


	methods:{

		getIssues() {
			axios
        		.get(this.baseUrl +'help/get/'+123) // is id of my issue 
        		.then(res => this.data = res.data).catch(e=>console.log(e));;
		},


		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},


    		getSelectedString () { return this.selected.length === 0 ? '' : `${this.selected.length} record${this.selected.length > 1 ? 's' : ''} selected of ${this.data.length}`
    }
},
}
</script>
