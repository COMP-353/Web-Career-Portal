<style lang="sass" scoped>
.my-card
    width: 100%
    max-width: 250px
</style>

<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          Web Career Portal
        </q-toolbar-title>
        <q-btn flat rounded label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="js" label="Home" />
        <q-route-tab to="js-postings" label="Job Postings" />
        <q-route-tab to="js-profile" label="Profile" />
      </q-tabs>
    </q-header>

    <q-page-container style="height: 100%;" class="relative-position">

      <router-view />
      <!-- </q-body> -->
    


      <q-body>
          <q-splitter v-model="splitterModel" style="height: 75%;">

            <template v-slot:before>
              <q-tabs v-model="tab" vertical class="text-teal">
                <q-tab name="tabProfile" icon="person" label="Profile" />
                <q-tab name="tabMakePayment" icon="payment" label="Payment" />
		        <q-tab name="tabSetUpPay" icon="payment" label="Set-up Payment" />
                <q-tab name="tabCategory" icon="category" label="category" />
              </q-tabs>
            </template>

            <template v-slot:after>
              <q-tab-panels
                v-model="tab"
                animated
                transition-prev="slide-down"
                transition-next="slide-up"
              >
                <q-tab-panel name="tabProfile">
                  <div class="text-h4 q-mb-md">Profile Info</div>
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
                  <q-btn label="Modify" />
                  <q-btn>Save</q-btn>
                </q-tab-panel>

                <q-tab-panel name="tabMakePayment">
                  <div class="text-h4 q-mb-md">Make a Payment</div>
                  <p>
                    If you have choosen automatic during set-up payment then you
                    don't need to visit this page. If else, continue to steps
                    below.
                  </p>
                  <p><b>Choose the amount of money you're going to pay.</b></p>
                  <q-input outlined v-model="amount" type="number" prefix="$" />
                  <q-separator></q-separator>
                  <br />
                  <p><b>Choose your method of payment.</b></p>

                  <div>
                    <q-radio
                      v-model="paymentmethod"
                      val="creditcard"
                      label="Credit Card"
                    />
                    <q-radio
                      v-model="paymentmethod"
                      val="checkingaccount"
                      label="Checking Account"
                    />
                  </div>
                  <q-btn
                    label="pay"
                    @click="makeAPayment()"
                    :disabled="!(amount > 0)"
                  />
                </q-tab-panel>

                <q-tab-panel name="tabSetUpPay">
                  <div class="text-h4 q-mb-md">Credit Card</div>
                  <p>Your credit card information</p>
                  <q-input outlined v-model="text" label="Card Number" />
                  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Credit Card Name" />
                  <q-separator></q-separator>
                  <q-input
                    outlined
                    v-model="text"
                    label="Credit Card Security Code"
                  />
                  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Billing Address" />

                  <q-radio
                    v-model="creditcard"
                    val="automatic"
                    label="Automatic Withdrawal"
                  />
                  <q-radio
                    v-model="creditcard"
                    val="default"
                    label="Default Payment"
                  />
                  <br></br>
		              <q-btn color="white" text-color="black" label="Set-Up Checking Account" />
                  <br><br />
                  

                  <div class="text-h4 q-mb-md">Checking Account</div>
                  <p>Your checking account information</p>
                  <q-input outlined v-model="text" label="Bank Number" />
                  <q-separator></q-separator>
                  <q-input outlined v-model="text" label="Account Number" />

                  <q-radio
                    v-model="checkingacc"
                    val="automatic"
                    label="Automatic Withdrawal"
                  />
                  <q-radio
                    v-model="checkingacc"
                    val="default"
                    label="Default Payment"
                  />
                  <br/>
		              <q-btn color="white" text-color="black" label="Set-Up Checking Account" />
                </q-tab-panel>

                <q-tab-panel name="tabCategory">
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
                  <q-btn>Save</q-btn>
                </q-tab-panel>
              </q-tab-panels>
            </template>
            </q-splitter>
         </q-body>
         </q-page-container>
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
      tab:'tabProfile',
      baseUrl: 'http://localhost:7070/',
      jobSeeker:{
      firstName:'',
        lastName:'',
        accountBalance: 0,
        status:'',
        email:'',
      },
      amount:0
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
    getAccountCategory(){
       axios
        .get(this.baseUrl + 'user/getCat/' + this.jobSeeker.email)
        .then((res) => this.accountType = res.data)
        .catch((e) => console.log(e));
    },
    getUserData(){
      this.amount = 0
      axios
        .get(this.baseUrl + 'user/jobseeker/' + this.jobSeeker.email)
        .then((res) => this.assignJsObject(res.data))
        .catch((e) => console.log(e));
    },
    makeAPayment(){
      axios.put(this.baseUrl +'user/pay/'+ this.jobSeeker.email +'/' +this.amount).then
      (this.getUserData()).catch(e => console.log(e))
    }
  },
};
</script>
