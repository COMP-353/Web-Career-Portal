<template>
  <q-layout view="hHh LpR fFf">
    <JSHeader />

    <q-page-container style="height=100%">
      <router-view />
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Welcome back {{ this.jobSeeker.firstName }}!
          </div>
          <div class="text-subtitle2">
            The job opportunity list has been updated!
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ this.jobSeeker.firstName }}
        </q-card-section>
      </q-card>

      <div class="row">
        <div class="col">
          <div class="q-pl-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">Company</th>
                  <th class="text-left">Job position</th>
                  <th class="text-right">ID</th>
                  <th class="text-right">Email</th>
                  <th class="text-right">Date of posting</th>
                  <th class="text-right">Apply!</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-left">Google</td>
                  <td class="text-left">Software developer</td>
                  <td class="text-right">1</td>
                  <td class="text-right">mia@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                  <td class="text-right">
                    <div class="q-pa-md q-gutter-sm">
                      <q-btn
                        color="blue"
                        icon="mail"
                        icon-right="send"
                        label=" "
                        size="sm"
                      />
                    </div>
                  </td>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
        </div>
        <div class="col-3 row vertical-middle q-pl-xl">
          <div class="q-pa-md flex flex-center">
            <q-knob
              readonly
              show-value
              font-size="20px"
              v-model="this.jobSeeker.accountBalance"
              size="250px"
              :thickness="0.1"
              color="teal"
              track-color="grey-3"
              class="q-ma-md"
            >
              <q-tooltip>
                A negative balance means you have extra credit while a positive
                balance shows how much you owe to the system.
              </q-tooltip>
              Balance {{ this.jobSeeker.accountBalance }}$
            </q-knob>
          </div>
        </div>
      </div>
    </q-page-container>
  </q-layout>
</template>

<script>
import axios from 'axios';
import JSHeader from '../components/JSHeader.vue'

export default {
  components:{
JSHeader
  },
  data() {
    return {
      accountType:'basic',
      innerProfileTab:'innerprofile',
      baseUrl: 'http://localhost:7070/',
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      },
    }
  },

  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.jobSeeker.email = this.$store.getters.getUserId;
     this.getUserData()
    //  this.getAccountCategory()
    }
  },

  methods: {
    assignJsObject(res) {
      console.log(res)
      this.jobSeeker.firstName = res.firstName;
      this.jobSeeker.lastName = res.lastName;
      this.jobSeeker.accountBalance = res.accountBalance;
      this.jobSeeker.status = res.status;
    },
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },
    // getAccountCategory(){
    //    axios
    //     .get(this.baseUrl + 'user/getCat/' + this.jobSeeker.email)
    //     .then((res) => this.accountType = res.data)
    //     .catch((e) => console.log(e));
    // },
    getUserData(){
      this.amount = 0
      axios
        .get(this.baseUrl + 'user/jobseeker/' + this.jobSeeker.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    }
  },
};
</script>
