<template>
  <q-toolbar>
    <router-link to="/"> <q-skeleton type="QBtn" /></router-link>
    <q-btn flat label="키워드 분석" :to="{ name: 'empty_keyword' }" />
    <q-btn flat label="기업 리스트" :to="{ name: 'company' }" />
    <q-btn flat label="최신 뉴스" :to="{ name: 'news' }" />
    <q-btn flat label="서비스 소개" :to="{ name: 'about' }" />
    <!-- <q-btn flat label="TEST(임시)" href="test" /> -->
  </q-toolbar>
</template>

<style scoped>
.header {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 16px;
}
</style>
