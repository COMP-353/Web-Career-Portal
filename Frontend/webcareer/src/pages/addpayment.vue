<template>
  <q-layout view="hHh LpR fFf">
    <q-page-container style="height: 300px;">
      <EHeader />
      <q-page padding>
        <div>
          <q-splitter v-model="splitterModel" style="height: 50%;">
            <template v-slot:before>
              <q-tabs v-model="tab" vertical class="text-primary">
                <q-tab name="payment" icon="payment" label="Payment" />
                <q-tab
                  name="setuppayment"
                  icon="payment"
                  label="Set-Up payment"
                />
                <q-tab name="plan" icon="category" label="Category" />
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
                <!-- Make a payment -->
                <q-tab-panel name="payment">
                  <MakePayment v-bind:user="employer" />
                </q-tab-panel>

                <!-- To add Payment options -->
                <q-tab-panel name="setuppayment">
                  <AddCreditCard v-bind:email="employer.email" />
                  <AddCheckingAccount v-bind:email="employer.email" />
                  <div style="padding-bottom: 1%;">
                    <q-btn
                      label="Add a Credit Card"
                      flat
                      @click="addCreditCard()"
                    />
                    <q-btn
                      flat
                      label="Add a Checking Account"
                      @click="addCheckingAccount()"
                    />
                  </div>
                  <q-btn
                    label="Reload"
                    outline
                    color="primary"
                    rounded
                    @click="getAllPayments()"
                  />

                  <div class="text-h4" style="padding-top: 2%;">
                    Your Credit Cards
                  </div>
                  <CreditCard
                    v-for="creditcard in ccs"
                    :key="creditcard.id"
                    v-bind:cc="creditcard"
                  />

                  <div
                    class="text-h4"
                    style="padding-top: 2%; padding-bottom: 2%;"
                  >
                    Your Checking Accounts
                  </div>
                  <CheckingAccount
                    v-for="checkinga in cas"
                    :key="checkinga.id"
                    v-bind:ca="checkinga"
                  />
                </q-tab-panel>

                <!-- Change employer plan -->
                <q-tab-panel name="plan">
                  <div
                    class="row justify-left full-height full-width"
                    style="max-width=50%"
                  >
                    <div class="q-gutter-sm">
                      <br />
                      <p style="font-size: 100%;">
                        <b>Select type of account</b>
                      </p>

                      <div class="row text-center" style="padding-bottom: 1%;">
                        <q-card flat bordered class="my-card">
                          <q-card-section>
                            <div class="text-h6">Prime Account</div>
                          </q-card-section>

                          <q-card-section class="q-pt-none">
                            Employer can post up to five jobs. A monthly charge
                            of $50 will be applied
                          </q-card-section>

                          <q-separator inset />

                          <q-radio
                            v-model="employer.category"
                            val="Prime"
                            label="Prime (50$/month)"
                            :disable="modifyUserCategory"
                          />
                        </q-card>

                        <q-card flat bordered class="my-card">
                          <q-card-section>
                            <div class="text-h6">Gold Account</div>
                          </q-card-section>

                          <q-card-section class="q-pt-none">
                            Employer can post as many jobs as he/she wants. A
                            monthly charge of $100 will be applied.
                          </q-card-section>

                          <q-separator inset />

                          <q-radio
                            v-model="employer.category"
                            val="Gold"
                            label="Gold (100$/month)"
                            :disable="modifyUserCategory"
                          />
                        </q-card>
                      </div>
                      <q-btn
                        :label="modifyUserCategory ? 'Modify' : 'Save'"
                        color="primary"
                        outline
                        rounded
                        @click="saveUserCategory()"
                      />
                    </div>
                  </div>
                </q-tab-panel>

                <!-- Change Password -->
                <q-tab-panel name="pw">
                  <ChangePassword v-bind:email="employer.email" />
                </q-tab-panel>
              </q-tab-panels>
            </template>
          </q-splitter>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>




<script>
import EHeader from 'components/EHeader.vue'
import CreditCard from 'components/CreditCard.vue';
import CheckingAccount from 'components/CheckingAccount.vue';
import MakePayment from 'components/MakePayment.vue';
import AddCreditCard from 'components/AddCreditCard.vue';
import AddCheckingAccount from 'components/AddCheckingAccount.vue';
import ChangePassword from 'components/ChangePassword.vue';
import axios from 'axios'

export default {

  components:{
EHeader,CheckingAccount, CreditCard, MakePayment, AddCreditCard,
  AddCheckingAccount, ChangePassword
  },

 data () {
    return {
      paymentmethod: 'creditcard',
      tab: 'payment',
      splitterModel: 20,
      cc:{
          creditCardNumber:0,
          creditCardName:'',
          billingAddress:'',
          securityCode:0,
          defaultPayment:false,
          automaticWithdrawal: false
      },ca:{
          bankNumber:0,
          accountNumber:0,
          defaultPayment:false,
          automaticWithdrawal: false
      },
      employer:{
        email:'',
        category:''
      },ccs:[],cas:[],
      baseUrl:'http://localhost:7070/'
    ,
    modifyUserCategory:true
    }
  },
  mounted(){
     if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
    this.employer.email = this.$store.getters.getUserId;
    this.getAccountCategory()
    this.getAllPayments()
    }
  },
	methods:{
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},
getUser(){
      axios.put(this.baseUrl +'user/pay/'+ this.employer.email +'/' +this.amount).then
      (this.getUserData()).catch(e => console.log(e))
    },
        addCreditCard(){
        this.$root.$emit('addcc')
    },
    addCheckingAccount(){
      this.$root.$emit('addca')   
}, getCheckingAccount(){
        axios.get(this.baseUrl + 'payment/checking/' + this.employer.email).then(res => this.cas = res.data).catch(e => console.log(e))
    }, 
    getCreditCard(){
axios.get(this.baseUrl + 'payment/credit/' + this.employer.email).then(res => this.ccs = res.data).catch(e => console.log(e))
    },
    getAllPayments(){
        this.getCreditCard()
        this.getCheckingAccount()
    },
     getAccountCategory(){
       axios
        .get(this.baseUrl + 'user/getCat/' + this.employer.email)
        .then((res) => this.employer.category = res.data)
        .catch((e) => console.log(e));
    },
     saveUserCategory(){
        this.modifyUserCategory = !this.modifyUserCategory
        if (this.modifyUserCategory){
            console.log('Saving User Plan')
            axios.put(this.baseUrl + 'user/updateEmployerCategory/'+this.employer.email +'/' +this.employer.category).then(this.modifyUserCategory = true).catch(e => console.log(e))
        }
    },
},
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>