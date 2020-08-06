<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>

<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          Web Career Portal
        </q-toolbar-title>
        <q-btn label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs v-model="tab" align="left">
        <q-tab name="apps" label="Home" />
        <q-tab name="page2" label="Job Postings" />
        <q-tab name="page3" label="Profile" />
      </q-tabs>
    </q-header>

    <!-- <q-page-container style="height: 250px;"> -->
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="apps">
        <q-page-container>
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
                  Balance {{ this.jobSeeker.accountBalance }}$
                </q-knob>
              </div>
            </div>
          </div>
          <!-- </q-body> -->
        </q-page-container>
      </q-tab-panel>

      <q-tab-panel name="page2"
        ><q-page-container>
        </q-page-container>
      </q-tab-panel>

      <q-tab-panel name="page3">
        <q-page-container>
          <q-splitter v-model="splitterModel" style="height: 75%;">
            <template v-slot:before>
              <q-tabs v-model="innerProfileTab" vertical class="text-teal">
                <q-tab name="innerprofile" icon="person" label="Profile" />
                <q-tab name="innerAlarms" icon="payment" label="Payment" />
		<q-tab name="innerSetUpPay" icon="payment" label="Set-up Payment" />
                <q-tab name="innerMovies" icon="category" label="category" />
              </q-tabs>
            </template>

            <template v-slot:after>
              <q-tab-panels
                v-model="innerProfileTab"
                animated
                transition-prev="slide-down"
                transition-next="slide-up"
              >
                <q-tab-panel name="innerprofile">
                  <div class="text-h4 q-mb-md">Profile</div>
                  <p>Your profile information</p>
                  <q-input
                    outlined
                    v-model="jobSeeker.firstName"
                    label="First Name"
                  />
                  <q-separator></q-separator>
                  <q-input
                    outlined
                    v-model="jobSeeker.lastName"
                    label="Last Name"
                  />
                </q-tab-panel>

                <q-tab-panel name="innerAlarms">
                  <div class="text-h4 q-mb-md">Payments</div>
                  <p>If you have choosen automatic during set-up payment then you don't need to visit this page. If else, continue to steps below.</p>
<p><b>Choose the amount of money you're going to pay.</b></p>
<q-input outlined v-model="text" label="Amount" />
<q-separator></q-separator>
<br></br>
<p><b>Choose your method of payment.</b></p>
<q-radio v-model="paymentmethod" val="creditcard" label="Credit Card" />
<q-radio v-model="paymentmethod" val="checkingaccount" label="Checking Account" />

                </q-tab-panel>

		<q-tab-panel name="innerSetUpPay">
                  <div class="text-h4 q-mb-md">Credit Card</div>
                  <p>Your credit card information</p>
                   <q-input outlined v-model="text" label="Card Number" />
                  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Credit Card Name" />
		  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Credit Card Security Code" />
		  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Billing Address" />

      <q-radio v-model="creditcard" val="automatic" label="Automatic Withdrawal" />
      <q-radio v-model="creditcard" val="default" label="Default Payment" />

<br></br>
			
		   <div class="text-h4 q-mb-md">Checking Account</div>
                  <p>Your checking account information</p>
                  <q-input outlined v-model="text" label="Bank Number" />
                  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Account Number" />

      <q-radio v-model="checkingacc" val="automatic" label="Automatic Withdrawal" />
      <q-radio v-model="checkingacc" val="default" label="Default Payment" />
                </q-tab-panel>

                <q-tab-panel name="innerMovies">
                  <div class="text-h4 q-mb-md">Category</div>
                  <div
                    class="row justify-center full-height full-width text-center"
                  >
                    <div class="q-gutter-sm">
                      <p style="font-size: 100%;">
                        <b>Select type of account</b>
                      </p>

                      <div class="row">
                        <q-card flat bordered class="my-card">
                          <q-card-section>
                            <div class="text-h6">Basic Account</div>
                          </q-card-section>

                          <q-card-section class="q-pt-none">
                            You can only view job postings but not apply. Free.
                          </q-card-section>

                          <q-separator inset />

                          <q-radio
                            v-model="accountType"
                            val="Basic"
                            label="Free"
                          />
                        </q-card>

                        <q-card flat bordered class="my-card">
                          <q-card-section>
                            <div class="text-h6">Prime Account</div>
                          </q-card-section>

                          <q-card-section class="q-pt-none">
                            You can apply to up to five jobs. A monthly charge
                            of $10 will be applied
                          </q-card-section>

                          <q-separator inset />

                          <q-radio
                            v-model="accountType"
                            val="Prime"
                            label="Prime (10$/month)"
                          />
                        </q-card>

                        <q-card flat bordered class="my-card">
                          <q-card-section>
                            <div class="text-h6">Gold Account</div>
                          </q-card-section>

                          <q-card-section class="q-pt-none">
                            You can apply to as many jobs as you wants. A
                            monthly charge of $20 will be applied.
                          </q-card-section>

                          <q-separator inset />

                          <q-radio
                            v-model="accountType"
                            val="Gold"
                            label="Gold (20$/month)"
                          />
                        </q-card>
                      </div>
                    </div>
                  </div>
                </q-tab-panel>
              </q-tab-panels>
            </template> </q-splitter></q-page-container
      ></q-tab-panel>
    </q-tab-panels>
  </q-layout>
</template>

<script>
import axios from 'axios';


export default {
  data() {
    return {
      creditcard: 'automatic',
      checkingacc: 'automatic',
      paymentmethod: 'creditcard',
      tab: 'apps',
      accountType:'basic',
      innerProfileTab:'innerprofile',
      baseUrl: 'http://localhost:7070/',
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      }
    }
  },



  mounted() {
    if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
      this.jobSeeker.email = this.$store.getters.getUserId;
     this.getUserData()
     this.getAccountCategory()
    }
  },

  methods: {
    assignJsObject(res){
      this.jobSeeker.firstName = res.firstName;
      this.jobSeeker.lastName = res.lastName;
      this.jobSeeker.accountBalance = res.accountBalance;
      this.jobSeeker.status = res.status;
    },
    logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },
    getAccountCategory(){
       axios
        .get(this.baseUrl + 'user/getCat/' + this.jobSeeker.email)
        .then((res) => this.accountType = res.data)
        .catch((e) => console.log(e));
    },
    getUserData(){
      axios
        .get(this.baseUrl + 'user/jobseeker/' + this.jobSeeker.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    }
  },
};
</script>
