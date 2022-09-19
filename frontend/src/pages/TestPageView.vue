<template>
  <q-page class="flex flex-center">
    <!-- 메인 페이지에 넣을 컴포넌트들을 채워주삼 -->
    <keyword-search />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import KeywordSearch from "../components/keyword_analyze/KeywordSearch.vue";

export default defineComponent({
  name: "TestPageView",
  components: {
    KeywordSearch,
  },
});
</script>
