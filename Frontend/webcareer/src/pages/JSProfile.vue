<style lang="sass" scoped>
.my-card
    width: 100%
    max-width: 250px
</style>

<template>
  <q-layout view="hHh LpR fFf">
    <JSHeader />

    <q-page-container
      style="height: 100%; padding-right: 1%; padding-left: 1%;"
      class="relative-position"
    >
      <router-view />

      <q-body>
        <q-splitter v-model="splitterModel" style="height: 75%;">
          <template v-slot:before>
            <q-tabs v-model="tab" vertical class="text-primary">
              <q-tab name="tabProfile" icon="person" label="Profile" />
              <q-tab name="tabMakePayment" icon="payment" label="Payment" />
              <q-tab name="tabSetUpPay" icon="payment" label="Set-up Payment" />
              <q-tab name="tabCategory" icon="category" label="category" />
              <q-tab name="pw" icon="vpn_key" label="Password" />
            </q-tabs>
          </template>

          <template v-slot:after>
            <q-tab-panels
              v-model="tab"
              animated
              transition-prev="slide-down"
              transition-next="slide-up"
            >
              <!-- The Profile Information -->
              <q-tab-panel name="tabProfile">
                <div class="text-h4 q-mb-md">Profile Info</div>
                <p>Your profile information</p>
                <div style="padding-bottom: 1%;">
                  <q-input
                    outlined
                    v-model="jobSeeker.firstName"
                    label="First Name"
                    :disable="modifyProfileInfo"
                  />
                </div>
                <q-separator></q-separator>
                <div style="padding-bottom: 1%;">
                  <q-input
                    outlined
                    :disable="modifyProfileInfo"
                    v-model="jobSeeker.lastName"
                    label="Last Name"
                  />
                </div>
                <q-btn
                  :label="modifyProfileInfo ? 'Modify' : 'Save'"
                  outline
                  rounded
                  color="primary"
                  @click="modifyInfo()"
                />
              </q-tab-panel>

              <!-- To Make a Payment -->
              <q-tab-panel name="tabMakePayment">
                <MakePayment v-bind:user="jobSeeker" />
              </q-tab-panel>

              <!-- To Set Up A Payment -->
              <q-tab-panel name="tabSetUpPay">
                <AddCreditCard
                  v-bind:email="jobSeeker.email"
                  @updatecc="getCreditCard()"
                />
                <AddCheckingAccount
                  v-bind:email="jobSeeker.email"
                  @updateca="getCheckingAccount()"
                />
                <div style="padding-top: 1%; padding-bottom: 1%;">
                  <q-btn
                    flat
                    color="black"
                    label="Add Credit Card"
                    @click="addCreditCard()"
                  />
                  <q-btn
                    flat
                    color="black"
                    label="Add Checking Account"
                    @click="addCheckingAccount()"
                  />
                </div>
                <div style="padding-bottom: 1%;">
                  <q-btn
                    label="Reload"
                    color="primary"
                    outline
                    rounded
                    @click="getAllPayments()"
                  />
                </div>

                <div class="text-h4">Your Credit Cards</div>

                <CreditCard
                  v-for="creditcard in ccs"
                  :key="creditcard.id"
                  v-bind:cc="creditcard"
                  @updatecc="getCreditCard()"
                />

                <div class="text-h4" style="padding-top: 1%;">
                  Your Checking Accounts
                </div>
                <br /><br />
                <CheckingAccount
                  v-for="checkinga in cas"
                  :key="checkinga.id"
                  v-bind:ca="checkinga"
                  @updateca="getCheckingAccount()"
                />
              </q-tab-panel>

              <!-- To Change User Category aka Plan -->
              <q-tab-panel name="tabCategory">
                <div class="text-h4 q-mb-md">Category</div>
                <div
                  class="row justify-left full-height full-width text-center"
                  style="padding-top: 1%; padding-bottom: 1%;"
                >
                  <div class="q-gutter-sm">
                    <p style="font-size: 100%;">
                      <b>Select type of account</b>
                    </p>

                    <div class="row">
                      <q-card flat bordered class="my-card">
                        <q-card-section>
                          <div class="h6">Basic Account</div>
                        </q-card-section>

                        <q-card-section class="q-pt-none">
                          You can only view job postings but not apply. Free.
                        </q-card-section>

                        <q-separator inset />

                        <q-radio
                          v-model="accountType"
                          val="Basic"
                          label="Free"
                          :disable="modifyUserCategory"
                        />
                      </q-card>

                      <q-card flat bordered class="my-card">
                        <q-card-section>
                          <div class="h6">Prime Account</div>
                        </q-card-section>

                        <q-card-section class="q-pt-none">
                          You can apply to up to five jobs. A monthly charge of
                          $10 will be applied
                        </q-card-section>

                        <q-separator inset />

                        <q-radio
                          v-model="accountType"
                          val="Prime"
                          label="Prime (10$/month)"
                          :disable="modifyUserCategory"
                        />
                      </q-card>

                      <q-card flat bordered class="my-card">
                        <q-card-section>
                          <div class="h6">Gold Account</div>
                        </q-card-section>

                        <q-card-section class="q-pt-none">
                          You can apply to as many jobs as you wants. A monthly
                          charge of $20 will be applied.
                        </q-card-section>

                        <q-separator inset />

                        <q-radio
                          v-model="accountType"
                          val="Gold"
                          :disable="modifyUserCategory"
                          label="Gold (20$/month)"
                        />
                      </q-card>
                    </div>
                  </div>
                </div>
                <q-btn
                  :label="modifyUserCategory ? 'Modify' : 'Save'"
                  color="primary"
                  outline
                  rounded
                  @click="saveUserCategory()"
                />
              </q-tab-panel>

              <q-tab-panel name="pw">
                <ChangePassword v-bind:email="jobSeeker.email" />
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
import JSHeader from '../components/JSHeader.vue';
import CreditCard from 'components/CreditCard.vue';
import CheckingAccount from 'components/CheckingAccount.vue';
import MakePayment from 'components/MakePayment.vue';
import AddCreditCard from 'components/AddCreditCard.vue';
import AddCheckingAccount from 'components/AddCheckingAccount.vue';
import ChangePassword from 'components/ChangePassword.vue'

export default {
components:{
  JSHeader,
  CreditCard,
  CheckingAccount,
  MakePayment,
  AddCreditCard,
  AddCheckingAccount,
  ChangePassword
},
  data() {
    return {
    modifyProfileInfo: true,
    modifyUserCategory: true,
    addcc:false,
    justSaved:false,
      creditcard: 'automatic',
      checkingacc: 'automatic',
      paymentmethod: 'creditcard',
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
      amount:0,
      splitterModel:20,
      ccs:[],
      cas:[]
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
     this.getCheckingAccount()
     this.getCreditCard()
     this.$root.$on('updatecc', this.getCreditCard())
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
   
    modifyInfo(){
        this.modifyProfileInfo = !this.modifyProfileInfo
        if(this.modifyProfileInfo){
            console.log('ready to save profile info')
            axios
            .put(this.baseUrl + 'user/updateName', this.jobSeeker)
            .then(this.modifyProfileInfo = true)
            .catch(e => console.log(e))
        }
    },
    saveUserCategory(){
        this.modifyUserCategory = !this.modifyUserCategory
        if (this.modifyUserCategory){
            console.log('Saving User Plan')
            axios.put(this.baseUrl + 'user/updateJobSeekerCategory/'+this.jobSeeker.email +'/' +this.accountType).then(this.modifyUserCategory = true).catch(e => console.log(e))
        }
    },
    addCreditCard(){
        this.$root.$emit('addcc')
    },
    addCheckingAccount(){
      this.$root.$emit('addca')   
},
    getCheckingAccount(){
        axios.get(this.baseUrl + 'payment/checking/' + this.jobSeeker.email).then(res => this.cas = res.data).catch(e => console.log(e))
    }, 
    getCreditCard(){
axios.get(this.baseUrl + 'payment/credit/' + this.jobSeeker.email).then(res => this.ccs = res.data).catch(e => console.log(e))
    },
    getAllPayments(){
        this.getCreditCard()
        this.getCheckingAccount()
    }
  },
};
</script>
