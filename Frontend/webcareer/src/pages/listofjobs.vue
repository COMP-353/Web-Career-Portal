<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
          Job Portal
        </q-toolbar-title>
       <q-btn label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />
        <q-route-tab to="/page2" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job"/>
        <q-route-tab to="/listofjobs" label="List of Jobs" />
      </q-tabs>
    </q-header>

    <q-page-container style="height: 300px;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Here you can find your list of jobs!
          </div>
          <div class="text-subtitle2">
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ lorem }}
        </q-card-section>
      </q-card>
      <router-view />
    </q-page-container>

    <q-body>

      <div class="q-pl-xl">
            <div class="q-pa-md">
              <q-table
                title="Employers"
                :data="rowsForEmployers"
                :columns="columnsForEmployers"
                row-key="jobId"
              />
            </div>
          </div>


      <div class="row">
        <div class="col">
          <div class="q-px-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">ID</th>
                  <th class="text-right">Title</th>
                  <th class="text-right">Category</th>
                  <th class="text-right">Description</th>
                  <th class="text-right">Job status</th>
                  <th class="text-right">Date posted</th>
                  <th class="text-right">Delete posting!</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-left">Description</td>
                  <td class="text-right">1</td>
                  <td class="text-right">mia@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Joanna Smith</td>
                  <td class="text-right">2</td>
                  <td class="text-right">joanna@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Leonie Green</td>
                  <td class="text-right">3</td>
                  <td class="text-right">leonie@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Ashley Miller</td>
                  <td class="text-right">4</td>
                  <td class="text-right">ashley@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">5</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">6</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">7</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">Job status</td>
                  <td class="text-right">Date posted</td>
                  <td class="text-right">
                    <q-btn color="deep-orange" push>
                      <div class="row items-center no-wrap">
                        <q-icon left name="map" />
                        <div class="text-center">
                          Delete
                        </div>
                      </div>
                    </q-btn>
                  </td>
                </tr>
                <tr>
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
import axios from 'axios';

export default {
  data () {
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
              { name: 'jobStatus', label: 'Job Status', field: 'jobStatus', sortable: true },
              { name: 'application_applicationId', label: 'Application ID', field: 'application_applicationId', sortable: true },
              { name: 'title', label: 'Title', field: 'title', sortable: true },
              { name: 'employer_email', label: 'Employer Email', field: 'employer_email', sortable: true },
              { name: 'job_category', label: 'Job Category', field: 'job_category', sortable: true },
              { name: 'job_status', label: 'Job Status', field: 'job_status', sortable: true },
              // { name: 'category', label: 'Category', field: 'calcium', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
            ]
    }
  },

  paginationForActivities: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 10
        // rowsNumber: xx if getting data from a server
      },
  
      rowsForEmployers: [],


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
      .get(this.baseUrl + 'job/employer/')
      .then(res=>this.rowsForEmployers = res.data)
      .catch(e=>console.log(e));
    }
  }
}
</script>