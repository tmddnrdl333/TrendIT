<template>
  <div>
    <section-first></section-first>
    <section-second></section-second>
    <section-third></section-third>
    <section-forth></section-forth>
    <section-fifth></section-fifth>
  </div>
</template>

<script>
import { defineComponent } from "vue";

import SectionFirst from "src/components/about/SectionFirst.vue";
import SectionSecond from "src/components/about/SectionSecond.vue";
import SectionThird from "src/components/about/SectionThird.vue";
import SectionForth from "src/components/about/SectionForth.vue";
import SectionFifth from "src/components/about/SectionFifth.vue";

export default defineComponent({
  name: "AboutView",
  components: {
    SectionFirst,
    SectionSecond,
    SectionThird,
    SectionForth,
    SectionFifth,
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

<style scoped></style>
<!-- MEMO
1. 만약에 SectionFirst 와 SectionSecond 의 너비 조정하려면
width 의 단위를 vw 에서 다른걸로 변경-->
