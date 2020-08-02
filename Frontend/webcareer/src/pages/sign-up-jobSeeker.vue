<template>
  <q-page padding>
    <!-- content -->
<form>
  <div class="q-gutter-md" style="max-width: 300px">
     <div class="q-pa-md q-gutter-sm">


    <q-banner rounded class="bg-primary text-white">
      Sign-up
    </q-banner>
  </div>



    <p>Please fill in this form to create an Job Seeker account.</p>
    <hr>

     <q-input filled v-model="text" label="Email" />
     <q-input filled v-model="text" label="Password" />

</div>

    <div class="q-gutter-sm">
        <q-radio v-model="account_type" val="basic" label="Basic" />
     	<q-radio v-model="account_type" val="gold" label="Gold" />
        <q-radio v-model="account_type" val="prime" label="Prime" />
    </div>

   

<div class="clearfix">
      <q-btn color="white" text-color="black" label="Sign-Up" />
      <q-btn color="white" text-color="black" label="Cancel" />
</div>

</form>
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

