<template>
  <q-layout view="hHh LpR fFf">
    <EHeader />

    <q-page-container style="height: 100%;">
      <router-view />
      <q-body>
        <div class="text-h6">
          Add a new job posting
        </div>
        <div class="window-width row justify-center items-center">
          <!-- <div></div> -->
          <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
            <q-input
              outlined
              v-model="name"
              label="Category name"
              hint="Job category"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />

            <div class="q-pa-md">
              <q-input
                v-model="text"
                label="Description"
                hint="Description of the position"
                outlined
                type="textarea"
              />
            </div>
            <q-input
              outlined
              v-model="name"
              label="Job title"
              hint="What is the position you would like to fulfill"
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />
            <q-input
              outlined
              v-model="name"
              label="Job status"
              hint="Active/Inactive"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            />

            <div class="q-pa-md">
              <q-input
                outlined
                v-model="date"
                mask="date"
                :rules="['date']"
                label="Date"
              >
                <template v-slot:append>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy
                      ref="qDateProxy"
                      transition-show="scale"
                      transition-hide="scale"
                    >
                      <q-date
                        v-model="date"
                        @input="() => $refs.qDateProxy.hide()"
                      />
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
            <!-- <q-input
              filled
              v-model="name"
              label="Date posted"
              hint="Must be of type DD-MM-YYYY"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type something',
              ]"
            /> -->

            <q-toggle v-model="accept" label="I accept the license and terms" />

            <div>
              <q-btn label="Submit" type="submit" color="primary" />
              <q-btn
                label="Reset"
                type="reset"
                color="primary"
                flat
                class="q-ml-sm"
              />
            </div>
          </q-form>
        </div>
      </q-body>
    </q-page-container>
  </q-layout>
</template>

<script>
import EHeader from 'components/EHeader.vue'
export default {
  components:{
EHeader
  },
  data () {
    return {
        accept: false,
        date: ''
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
