<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>

<template>
  <q-page padding>
    

<div class="row justify-center full-height full-width text-center">
  
  <div class="q-gutter-md" style="max-width: 300px">
     <div class="q-pa-md q-gutter-sm">
  </div>

    <p><b>Please input your email so that we can show you your password.</b></p>
    <hr>
     <q-input filled v-model="text" label="Email" />

</div>

</div>

<br></br>

<div class="row justify-center full-height full-width text-center">
<div class="clearfix">
      <q-btn color="white" text-color="black" label="Submit" />
      <q-btn to="index" color="white" text-color="black" label="Cancel" />
</div>
</div>

<br></br>

<div class="row justify-center full-height full-width text-center">
<q-card flat bordered class="my-card">
      <q-card-section>
        <div class="text-h6">Your password is:</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        your password will be displayed when you click submit
      </q-card-section>

      <q-separator inset />

 

</q-card>
</div>

  </q-page>
</template>

<script>
export default {
  // name: 'PageName',
}
</script>
