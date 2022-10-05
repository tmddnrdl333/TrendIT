<template>

  <q-toolbar class="q-pa-sm bg-grey-2 text-black justify-center">
    <!-- <router-link to="/" class="vertical-bottom absolute-left"> 
      <q-skeleton type="QBtn" />
       <q-img class="intro-img" src="src/assets/logo_v2.png"  />
    </router-link> -->
    <q-btn flat label="키워드 분석" :to="{ name: 'empty_keyword' }" />
    <q-btn flat label="기업 리스트" :to="{ name: 'company' }" />
    <q-btn flat label="최신 뉴스" :to="{ name: 'news' }" />
    <q-btn flat label="서비스 소개" :to="{ name: 'about' }" />
    <!-- <q-btn flat label="TEST(임시)" href="test" /> -->
  </q-toolbar>
</template>

<style scoped>
div {
  font-family: "NanumBarunGothic";
  font-size: 22px;
}
</style>
