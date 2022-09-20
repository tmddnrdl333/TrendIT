<template>
  <q-page class="flex flex-center">
    <!-- 메인 페이지에 넣을 컴포넌트들을 채워주삼 -->
    <api-test />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import ApiTest from "../components/test/ApiTest.vue";

export default defineComponent({
  name: "TestView",
  components: {
    ApiTest,
  },
  data() {
    return {
      sampleData: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
});
</script>
