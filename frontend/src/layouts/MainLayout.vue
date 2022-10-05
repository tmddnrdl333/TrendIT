<template>
  <q-layout view="lHh Lpr lFf" class="background-color ">
    <q-header reveal :reveal-offset="50" class="bg-grey-4 q-pt-xs" style="height:60px">
      <q-toolbar  class="justify-center items-center" >
        <!-- <q-toolbar-title>TRENDIT</q-toolbar-title> -->
        <router-link to="/" > 
        <!-- <q-skeleton type="QBtn" /> -->
        <q-btn flat><img class="logo" src="~/assets/logo_v1.png" alt=""></q-btn>
        </router-link>
        </q-toolbar>
      
      <!-- <LayoutHeader /> -->
    </q-header>

    <q-page-container>
      <q-page padding style="padding-top: 66px">

      
      <suspense>
        <router-view />
      </suspense>
      <!-- Footer에 넣을 내용 채워주삼 -->
      <LayoutFooter />
      <q-page-sticky expand position="top">
      <LayoutHeader />
      </q-page-sticky>
      <q-page-scroller position="bottom" :scroll-offset="200" :offset="[10, 10]">
          <q-btn fab icon="keyboard_arrow_up" color="scroller" />
      </q-page-scroller>
      </q-page>
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
.logo{
  width: 170px;
}


.bg-scroller {
  background: #98c1d9 !important;
}

</style>
