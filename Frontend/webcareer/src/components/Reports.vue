<template>
  <div class="q-pa-md">
    <div style="padding-bottom: 1%;">
      <q-btn
        outline
        rounded
        color="primary"
        label="Reload"
        @click="getAllActivity()"
      />
    </div>
    <q-table
      title="Activities In The System"
      :data="activities"
      :columns="columnsForActivities"
      row-key="name"
      :pagination.sync="paginationForActivities"
      hide-pagination
    />

    <div class="row justify-center q-mt-md">
      <q-pagination
        v-model="paginationForActivities.page"
        color="grey-8"
        :max="pagesNumberForActivities"
        size="sm"
      />
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import axios from 'axios';

export default Vue.extend({
  name: 'Reports',
  props: ['email'],
  data() {
    return {
      activities: [],
      paginationForActivities: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        // rowsNumber: xx if getting data from a server
      },
      columnsForActivities: [
        {
          name: 'dateCreated',
          field: 'dateCreated',
          required: true,
          label: 'Date',
          align: 'left',
          sortable: true,
        },
        {
          name: 'userId',
          align: 'left',
          label: 'User',
          field: 'userId',
          sortable: true,
        },
        {
          name: 'description',
          align: 'left',
          label: 'Description',
          field: 'description',
          sortable: true,
        },
      ],
    };
  },
  methods: {
    getAllActivity() {
      axios
        .get('http://localhost:7070/activity/' + this.email)
        .then((res) => (this.activities = res.data))
        .catch((e) => console.log(e));
    },
  },
  mounted() {
    if (this.email !== '') {
      this.getAllActivity();
      console.log('Reports, email is not empty');
    }
  },
  computed: {
    pagesNumberForActivities() {
      return Math.ceil(
        this.activities.length / this.paginationForActivities.rowsPerPage
      );
    },
  },
});
</script>