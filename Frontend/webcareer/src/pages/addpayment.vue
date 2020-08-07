<template>
  <q-page padding>
    <q-header reveal class="bg-primary fixed-top text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          Job Portal
        </q-toolbar-title>
        <q-btn flat rounded label="logout" @click="logOut()" />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />
        <q-route-tab to="/addpayment" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job" />
        <q-route-tab to="/listofjobs" label="List of Jobs" />
        <q-route-tab to="issues" label="Issues" />
      </q-tabs>
    </q-header>

    <div>
      <q-splitter v-model="splitterModel" style="height: 50%;">
        <template v-slot:before>
          <q-tabs v-model="tab" vertical class="text-teal">
            <q-tab name="payment" icon="payment" label="Payment" />
            <q-tab name="setuppayment" icon="payment" label="Set-Up payment" />
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
              <div class="text-h4 q-mb-md">Payment</div>
              <p>
                If you have choosen automatic during set-up payment then you
                don't need to visit this page. If else, continue to steps below.
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

            <q-tab-panel name="setuppayment">
              <div class="text-h4 q-mb-md">Set-Up Payment</div>
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
              <br />
              <q-btn
                color="white"
                text-color="black"
                label="Set-Up Checking Account"
              />
              <br /><br />

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
              <br />
              <q-btn
                color="white"
                text-color="black"
                label="Set-Up Checking Account"
              />
            </q-tab-panel>
          </q-tab-panels>
        </template>
      </q-splitter>
    </div>
  </q-page>
</template>




<script>
export default {
  // name: 'PageName',

 data () {
    return {

      creditcard: 'automatic',
      checkingacc: 'automatic',
      paymentmethod: 'creditcard',

      tab: 'payment',
      splitterModel: 20
    }
  },
	methods:{
		logOut(){
      			this.$store.commit('RESET_USER_ID');
      			this.$router.back();
    		},
makeAPayment(){
      axios.put(this.baseUrl +'user/pay/'+ this.jobSeeker.email +'/' +this.amount).then
      (this.getUserData()).catch(e => console.log(e))
    }
},
}
</script>
