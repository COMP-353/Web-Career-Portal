<template>

  <q-layout view="hHh LpR fFf">
      <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg">
          </q-avatar>
          Job Portal
        </q-toolbar-title>
        <q-btn label="logout" @click="logOut()" />
      </q-toolbar>
      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />
        <q-route-tab to="/addpayment" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job"/>
        <q-route-tab to="/listofjobs" label="List of jobs" />
      </q-tabs>
      </q-header>
    <q-body>
        <div class="window-height window-width row justify-center items-center">

    <div>

    </div>
    <q-form 
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="name"
        label="Category name"
        hint="Job category"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />

      <div class="q-pa-md" style="max-width: 300px">
        <q-input
        v-model="text"
        label= "Description"
        hint = "Description of the position"
        filled
        type="textarea"
        />
        </div>
      <q-input
        filled
        v-model="name"
        label="Job title"
        hint="What is the position you would like to fulfill"
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />
      <q-input
        filled
        v-model="name"
        label="Job status"
        hint= "Active/Inactive"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />
      <q-input
        filled
        v-model="name"
        label="Date posted"
        hint = "Must be of type DD-MM-YYYY"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />

      <q-toggle v-model="accept" label="I accept the license and terms" />

      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
      </div>
    </q-form>

  </div>
    </q-body>

  </q-layout>
</template>

<script>
export default {
  data () {
    return {
        accept: false
    }
  },
  methods: {
    onSubmit () {
      if (this.accept !== true) {
        this.$q.notify({
          color: 'red-5',
          textColor: 'white',
          icon: 'warning',
          message: 'You need to accept the license and terms first'
        })
      }
      else {
        this.$q.notify({
          color: 'green-4',
          textColor: 'white',
          icon: 'cloud_done',
          message: 'Submitted'
        })
      }
    },

logOut(){
      this.$store.commit('RESET_USER_ID');
      this.$router.back();
    },

    onReset () {
      this.name = null
      this.age = null
      this.accept = false
    }
  }
}
</script>
