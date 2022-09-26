<template>
  <q-layout view="lHh Lpr lFf" class="background-color">
    <q-header elevated>
      <LayoutHeader />
    </q-header>

    <q-page-container>
      <suspense>
        <router-view />
      </suspense>
      <!-- Footer에 넣을 내용 채워주삼 -->
      <LayoutFooter />
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent } from "vue";
import LayoutFooter from "src/components/LayoutFooter.vue";
import LayoutHeader from "src/components/LayoutHeader.vue";

export default defineComponent({
  name: "MainLayout",

  components: { LayoutFooter, LayoutHeader },

  setup() {
    return {};
  },
});
</script>

<style scoped>
.background-color {
  background-color: #d9d9d9;
}
</style>
