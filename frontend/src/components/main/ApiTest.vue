<template>
  <q-card class="q-pa-md" style="width: 500px">
    <q-card-section>response는 아래와 같음...</q-card-section>
    <q-separator />
    <q-card-section>{{ data1 }}</q-card-section>
    <q-separator />
    <q-card-section>필요한 데이터만 뽑으면...</q-card-section>
    <q-separator />
    <q-card-section>
      <div v-for="data in data2" :key="data.keyword">
        {{ data }}
        <q-separator />
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import { testApi } from "boot/test.js";
export default {
  setup() {
    const data1 = ref(null);
    const data2 = ref(null);
    return {
      data1,
      data2,
    };
  },
  created() {
    testApi(
      (response) => {
        this.data1 = response.data;
        this.data2 = response.data.data;
      },
      () => console.warn("failed")
    );
  },
  methods: {},
};
</script>
