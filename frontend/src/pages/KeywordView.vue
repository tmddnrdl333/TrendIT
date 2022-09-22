<template>
  <q-page class="flex flex-center">
    <keyword-comp />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import KeywordComp from "../components/keyword/KeywordComp.vue";

export default defineComponent({
  name: "KeywordView",
  components: {
    KeywordComp,
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
