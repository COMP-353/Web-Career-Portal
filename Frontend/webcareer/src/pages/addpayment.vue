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
                  <q-btn label="Add new Credit Card" @click="addCreditCard()" />
                  <q-btn
                    label="Add new Checking Account"
                    @click="addCheckingAccount()"
                  />
                  <q-btn
                    label="Reload"
                    flat
                    rounded
                    @click="getAllPayments()"
                  />
                  <CreditCard
                    v-for="creditcard in ccs"
                    :key="creditcard.id"
                    v-bind:cc="creditcard"
                  />

                  <br /><br />
                  <CheckingAccount
                    v-for="checkinga in cas"
                    :key="checkinga.id"
                    v-bind:ca="checkinga"
                  />
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
import axios from 'axios'

export default {

  components:{
EHeader,CheckingAccount, CreditCard, MakePayment, AddCreditCard,
  AddCheckingAccount
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
      },ccs:[],cas:[],
      baseUrl:'http://localhost:7070/'
    }
  },
  mounted(){
     if (this.$store.getters.getUserId === '') {
      console.log("User id is indeed ''");
      this.$router.push('/');
    } else {
    this.employer.email = this.$store.getters.getUserId;
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
    }
},
}
</script>
