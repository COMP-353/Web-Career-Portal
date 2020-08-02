<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>

<template>
  <q-page padding>
    <!-- content -->

<div class="row justify-center full-height full-width text-center">
  <div class="q-gutter-md" style="max-width: 300px">
     <div class="q-pa-md q-gutter-sm">


    <q-banner rounded class="bg-primary text-white">
      Employer Sign-up Form
    </q-banner>
  </div>



    <p><b>Please fill in this form to create an Employer account.</b></p>
    <hr>

     <q-input filled v-model="text" label="Email" />
     <q-input filled v-model="text" label="Password" />

</div>
</div>

<div class="row justify-center full-height full-width text-center">
    <div class="q-gutter-sm">
     	<br></br>
	<p style="font-size:100%"><b>Select type of account</b></p>

<div class="row">


<q-card flat bordered class="my-card">
      <q-card-section>
        <div class="text-h6">Prime Account</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        Employer can post up to five jobs. A monthly charge of $50 will be
applied
      </q-card-section>

      <q-separator inset />

<q-radio v-model="account_type" val="gold" label="Prime (50$/month)" />

</q-card>

<q-card flat bordered class="my-card">
      <q-card-section>
        <div class="text-h6">Gold Account</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        Employer can post as many jobs as he/she wants. A monthly charge
of $100 will be applied.
      </q-card-section>

      <q-separator inset />

 <q-radio v-model="account_type" val="prime" label="Gold (100$/month)" />

</q-card>
</div>
    </div>
</div>
   

<div class="row justify-center full-height full-width text-center">
<div class="clearfix">
      <q-btn color="white" text-color="black" label="Sign-Up" />
      <q-btn to="index" color="white" text-color="black" label="Cancel" />
</div>
</div>

  </q-page>
</template>

<script>
export default {
  data () {
    return {
      account_type: 'basic'
    }
  }
}
</script>
