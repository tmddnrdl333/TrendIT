<template>
  <q-page class="flex flex-center">
    <!-- 메인 페이지에 넣을 컴포넌트들을 채워주삼 -->
    <keyword-comp />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import KeywordComp from "../components/keyword/KeywordComp.vue";

export default defineComponent({
  name: "TestPageView",
  components: {
    KeywordComp,
  },
});
</script>
