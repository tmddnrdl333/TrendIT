<template>
  <q-toolbar>
    <router-link to="/"> <q-skeleton type="QBtn" /></router-link>
    <q-btn flat label="키워드 분석" :to="{ name: 'empty_keyword' }" />
    <q-btn flat label="기업 리스트" href="company" />
    <q-btn flat label="최신 뉴스" href="news" />
    <q-btn flat label="서비스 소개" href="about" />
    <!-- <q-btn flat label="TEST(임시)" href="test" /> -->
  </q-toolbar>
</template>
