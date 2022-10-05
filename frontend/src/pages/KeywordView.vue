<template>
  <q-page class="flex flex-center">
    <keyword-comp />
    <keyword-create />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import KeywordComp from "../components/keyword/KeywordComp.vue";
import KeywordCreate from "../components/keyword/result/KeywordCreate.vue";

export default defineComponent({
  name: "KeywordView",
  components: {
    KeywordComp,
    KeywordCreate,
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
