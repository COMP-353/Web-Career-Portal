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
                <q-tab-panel name="payment">
                  <MakePayment v-bind:user="employer" />
                </q-tab-panel>

                <q-tab-panel name="setuppayment">
                  <CreditCard v-bind:cc="cc" />
                  <br /><br />

                  <CheckingAccount v-bind:ca="ca" />
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
export default {
  // name: 'PageName',
  components:{
EHeader,CheckingAccount, CreditCard, MakePayment
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
      }
    }
  },
  mounted(){
    this.employer.email = this.$store.getters.getUserId;
  },
	methods:{
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},
getUser(){
      axios.put(this.baseUrl +'user/pay/'+ this.jobSeeker.email +'/' +this.amount).then
      (this.getUserData()).catch(e => console.log(e))
    }
},
}
</script>
