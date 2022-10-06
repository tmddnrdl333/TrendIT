<template>
  <q-page>
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
});
</script>
