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
      <div class="row">
        <div class="col">
          <div class="q-px-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">ID</th>
                  <th class="text-right">Job Seeker Id</th>
                  <th class="text-right">Employer Id</th>
                  <th class="text-right">Application Id</th>
                  <th class="text-right">Status</th>
                  <th class="text-right">Info</th>
                  <th class="text-right">Resolved!</th>
                </tr>
              </thead>

		<tbody>
                <tr v-for='Help in issuesList' v-bind:key='Help.id'>
                  <td class="text-left width:10">{{Help.id}}</td>
                  <td class="text-left">{{Help.jobSeekerId}}</td>
                  <td class="text-left">{{Help.employerId}}</td>
                  <td class="text-left">{{Help.applicationId}}</td>
                  <td class="text-left">{{Help.status}}</td>
                  <td class="text-left">{{Help.info}}</td>
                  <td class="text-right">
                 
                  </td>
		    <td class="text-right">
                    <div class="q-pa-md q-gutter-sm">
                      <q-btn
                        label="Resolved "
                        size="sm"
			color="orange"
                        icon="send"
                      />
                    </div>
                  </td>
                </tr>
              </tbody>
         
	
     
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
      issuesList:[],
      current: 3,
      value: 71,
      
    }
  },
  mounted(){

this.getIssues();

console.log('Mounted on employer page')
  
    console.log('getting store data:' + this.$store.getters.getUserId)
    if(this.$store.getters.getUserId === ''){
      console.log('id is indeed empty')
      this.$router.push('/')
    }
  },


	methods:{
<!--
		getIssues() {
			axios
        		.get(this.baseUrl + 'help/'+ 123)  //123 is id of my issue; will later replace id with email of employer
        		.then(res => this.issuesList = res.data);
		},
-->

		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},
},
}
</script>
