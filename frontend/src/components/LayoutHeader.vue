<template>
  <q-toolbar>
    <router-link to="/"> <q-skeleton type="QBtn" /></router-link>
    <div>
      <q-btn class="header" flat label="키워드 분석" href="keyword" />
      <q-btn class="header" flat label="기업 리스트" href="company" />
      <q-btn class="header" flat label="최신 뉴스" href="news" />
      <q-btn class="header" flat label="서비스 소개" href="about" />
      <q-btn class="header" flat label="TEST(임시)" href="test" />
    </div>
  </q-toolbar>
</template>

<style scoped>
.header {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 16px;
}
</style>
