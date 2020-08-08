<style lang="sass" scoped>
.my-card
  width: 100%
</style>

<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          Admin Portal
        </q-toolbar-title>
        <q-btn flat rounded label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs v-model="tab" align="left">
        <q-tab name="users" label="Users" />
        <q-tab name="activities" label="System Logs" />
        <q-tab name="pw" label="Password" />
      </q-tabs>
    </q-header>

    <!-- The Dialog -->
    <q-dialog v-model="fixed">
      <q-card>
        <q-card-section>
          <div class="text-h6">User</div>
        </q-card-section>

        <q-separator />

        <q-card-section
          style="max-height: 50vh; min-width: 50vh;"
          class="scroll"
        >
          <p>User: {{ temployer.email }}</p>
          <p>Balance: {{ temployer.accountBalance }}$</p>
          <p>Status: {{ temployer.status }}</p>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn
            flat
            :label="cellLabel"
            color="primary"
            v-close-popup
            @click="changeUserStatus()"
          />
          <q-btn flat label="Done" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <!-- Start of the pages -->
    <div class="column" style="padding-right: 2%; padding-left: 2%;">
      <q-tab-panels v-model="tab" animated>
        <!-- Page for the Users -->
        <q-tab-panel name="users">
          <q-page-container>
            <q-card flat bordered class="my-card">
              <q-card-section>
                <div class="text-h6">Welcome back {{ this.admin.email }}!</div>
              </q-card-section>
            </q-card>
            <div style="padding-top: 1%;">
              <q-btn
                outline
                rounded
                color="primary"
                label="Reload"
                @click="reloadAllUsers()"
              />
            </div>

            <div class="q-pa-md">
              <q-table
                title="Employers"
                @row-click="clickedRow"
                :data="rowsForEmployers"
                :columns="columnsForEmployers"
                row-key="id"
              />
            </div>

            <div class="q-pa-md">
              <q-table
                title="Job Seekers"
                @row-click="clickedRow"
                :data="rowsForJs"
                :columns="columnsForEmployers"
                row-key="id"
              />
            </div>
          </q-page-container>
        </q-tab-panel>

        <!-- Page for all Logs -->
        <q-tab-panel name="activities"
          ><q-page-container>
            <div class="q-pa-md">
              <div style="padding-bottom: 1%;">
                <q-btn
                  outline
                  rounded
                  color="primary"
                  label="Reload"
                  @click="getAllActivities()"
                />
              </div>
              <q-table
                title="Activities In The System"
                :data="rowsForActivities"
                :columns="columnsForActivities"
                row-key="name"
                :pagination.sync="paginationForActivities"
                hide-pagination
              />

              <div class="row justify-center q-mt-md">
                <q-pagination
                  v-model="paginationForActivities.page"
                  color="grey-8"
                  :max="pagesNumberForActivities"
                  size="sm"
                />
              </div>
            </div>
          </q-page-container>
        </q-tab-panel>

        <q-tab-panel name="pw">
          <!-- <q-page-container> -->
          <q-page>
            <div style="padding-top: 7%;">
              <ChangePassword v-bind:email="admin.email" />
            </div>
          </q-page>
          <!-- </q-page-container> -->
        </q-tab-panel>
      </q-tab-panels>
    </div>
  </q-layout>
</template>

<script>
import axios from 'axios';
import ChangePassword from 'components/ChangePassword.vue'

export default {
  components:{
    ChangePassword
  },
  data() {
    return {
      tab: 'users',
      fixed: false,
      innerProfileTab:'innerprofile',
      baseUrl: 'http://localhost:7070/',
      admin:{
      firstName:'',
        email:''
      },
       paginationForActivities: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 10
        // rowsNumber: xx if getting data from a server
      },
      columnsForActivities: [
        {
          name: 'dateCreated',
          field: 'dateCreated',
          required: true,
          label: 'Date',
          align: 'left',
          sortable: true
        },
        { name: 'userId', align: 'left', label: 'User', field: 'userId', sortable: true },
        { name: 'description', align:'left' ,label: 'Description', field: 'description', sortable: true },
      ],
      rowsForActivities:[],
      temployer:{
          email:'origi',
          accountBalance:0,
          status:''
      }
      ,
      columnsForEmployers: [
        {
          name: 'email',
          required: true,
          label: 'Email',
          field: 'email',
          align: 'left',
          sortable: true
        },
        { name: 'status', align: 'center', label: 'Status', field: 'status', sortable: true },
        { name: 'accountBalance', label: 'accountBalance', field: 'accountBalance', sortable: true, style: 'width: 10px' },
      ],
      rowsForEmployers: [],
      rowsForJs:[]
    }
  },
computed: {
    pagesNumberForActivities () {
    return Math.ceil(this.rowsForActivities.length / this.paginationForActivities.rowsPerPage)
    },
    cellLabel(){
        if (this.temployer.status === 'Active'){
            return 'Deactivate'
        } else {
            return 'Activate'
        }
    }
    },
  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.admin.email = this.$store.getters.getUserId;
    //  this.getUserData()
    this.getAllActivities()
    this.getAllEmployers()
    this.getAllJobSeekers()
    //  this.getAccountCategory()
    }
  },

  methods: {
    assignJsObject( res) {
      this.jobSeeker.firstName = res.firstName;
      this.jobSeeker.lastName = res.lastName;
      this.jobSeeker.accountBalance = res.accountBalance;
      this.jobSeeker.status = res.status;
    },
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },
    
    getUserData(){
      axios
        .get(this.baseUrl + 'user/allJs' + this.admin.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    },
    getAllActivities(){
        this.rowsForActivities = []
        axios.get(this.baseUrl + 'activity/')
        .then(res => this.rowsForActivities = res.data)
        .catch(e => console.log(e))
    },
    getAllEmployers(){
       axios
        .get(this.baseUrl + 'user/allEmployers')
        .then((res) => this.rowsForEmployers = res.data)
        .catch((e) => console.log(e));
    },
    getAllJobSeekers(){
axios
        .get(this.baseUrl + 'user/allJs')
        .then((res) => this.rowsForJs = res.data)
        .catch((e) => console.log(e));
    }, 
    clickedRow(evt, row){
        this.fixed = true
        console.log('row email' + row.email)
        console.log('row balance' + row.accountBalance)
        this.temployer.email  = row.email
        this.temployer.accountBalance = row.accountBalance
        this.temployer.status = row.status
         },
         changeUserStatus(){
             if(this.temployer.status === 'Active'){
                 axios.put(this.baseUrl + 'user/deactivate/' + this.temployer.email)
                 .then(this.reloadAllUsers()).catch(e => console.log(e))
             }else {
                 axios.put(this.baseUrl + 'user/activate/' + this.temployer.email)
                 .then(this.reloadAllUsers()).catch(e => console.log(e))
             }
         },
         reloadAllUsers(){
             this.rowsForEmployers = []
             this.getAllEmployers()
             this.rowsForJs = []
             this.getAllJobSeekers()
         }
        
  },
};
</script>
