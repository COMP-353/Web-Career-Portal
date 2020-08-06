<template>
  <q-layout view="hHh LpR fFf">
    <q-header reveal class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
          Job Portal
        </q-toolbar-title>
        <q-toolbar-subtitle>
          Employer
        </q-toolbar-subtitle>
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="employer" label="Home" />
        <q-route-tab to="/page2" label="Add payment" />
        <q-route-tab to="/addjob" label="Add job" />
        <q-route-tab to="/page3" label="Logout" />
      </q-tabs>
    </q-header>

    <q-page-container style="height: 300px;">
      <q-card flat bordered class="my-card">
        <q-card-section>
          <div class="text-h6">
            Welcome back! {{ this.$store.getters.getUserId }}
          </div>
          <div class="text-subtitle2">
            The job-seeker list has been updated!
          </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ lorem }}
        </q-card-section>
      </q-card>
      <router-view />
    </q-page-container>

    <q-body>
      <div class="row">
        <div class="col">
          <div class="q-px-xl">
            <q-markup-table>
              <thead>
                <tr>
                  <th class="text-left">Job-seeker name</th>
                  <th class="text-right">ID</th>
                  <th class="text-right">Email</th>
                  <th class="text-right">Date of application</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-left">Mia Yang</td>
                  <td class="text-right">1</td>
                  <td class="text-right">mia@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Joanna Smith</td>
                  <td class="text-right">2</td>
                  <td class="text-right">joanna@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Leonie Green</td>
                  <td class="text-right">3</td>
                  <td class="text-right">leonie@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Ashley Miller</td>
                  <td class="text-right">4</td>
                  <td class="text-right">ashley@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">5</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">6</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <td class="text-left">Peter White</td>
                  <td class="text-right">7</td>
                  <td class="text-right">peter@gmail.com</td>
                  <td class="text-right">2020-08-02</td>
                </tr>
                <tr>
                  <div class="q-pa-lg flex flex-center">
                    <q-pagination
                      v-model="current"
                      :max="5"
                      :direction-links="true"
                      :boundary-links="true"
                      icon-first="skip_previous"
                      icon-last="skip_next"
                      icon-prev="fast_rewind"
                      icon-next="fast_forward"
                    >
                    </q-pagination>
                  </div>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
        </div>
        <div class="col-3 row vertical-middle q-pl-xl">
          <div class="q-pa-md flex flex-center">
            <q-knob
              readonly
              show-value
              font-size="20px"
              v-model="value"
              size="250px"
              :thickness="0.1"
              color="teal"
              track-color="grey-3"
              class="q-ma-md"
            >
              Balance ${{ value }}
            </q-knob>
          </div>
        </div>
      </div>
    </q-body>
  </q-layout>
</template>

<script>
export default {
  data () {
    return {
      current: 3,
      value: 71
    }
  },
  mounted(){
console.log('Mounted on employer page')
  
    console.log('getting store data:' + this.$store.getters.getUserId)
    if(this.$store.getters.getUserId === ''){
      console.log('id is indeed empty')
      this.$router.push('/')
    }
  }
}
</script>
