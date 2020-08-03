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
        <q-toolbar-subtitle>
          Job-seeker
       </q-toolbar-subtitle>
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="" label="Home" />
        <q-route-tab to="/page2" label="Add payment" />
        <q-route-tab to="/page3" label="Logout" />
      </q-tabs>
    </q-header>

    <q-page-container style="height: 250px">
        <q-card
        flat bordered class="my-card"
      >
        <q-card-section>
          <div class="text-h6">Welcome back!</div>
          <div class="text-subtitle2">The job opportunity list has been updated!</div>
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
            <div class="q-pl-xl">
              <q-markup-table>
                <thead>
                  <tr>
                    <th class="text-left">Company</th>
                    <th class="text-left">Job position</th>
                    <th class="text-right">ID</th>
                    <th class="text-right">Email</th>
                    <th class="text-right">Date of posting</th>
                    <th class="text-right">Apply!</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">1</td>
                    <td class="text-right">mia@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">2</td>
                    <td class="text-right">joanna@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">3</td>
                    <td class="text-right">leonie@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">4</td>
                    <td class="text-right">ashley@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">5</td>
                    <td class="text-right">peter@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">6</td>
                    <td class="text-right">peter@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">Google</td>
                    <td class="text-left">Software developer</td>
                    <td class="text-right">7</td>
                    <td class="text-right">peter@gmail.com</td>
                    <td class="text-right">2020-08-02</td>
                    <td class="text-right">
                      <div class="q-pa-md q-gutter-sm">
                        <q-btn color="blue" icon="mail" icon-right="send" label=" " size="sm" />
                      </div>
                    </td>
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

    <q-footer bordered class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg">
          </q-avatar>
          Support: (123) 123-4567
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script>
export default {
  data () {
    return {
      current: 3,
      value: 71
    }
  }
}
</script>
