<template>
  <div>
    <q-card class="my-card news__container">
      <q-card-section>
        <div class="news__head">
          {{ test.headline }}
        </div>
      </q-card-section>
      <q-card-section class="q-pt-none news__body">
        {{ test.content }}
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
export default {
  name: "NewsHeadBodyPiece",
  props: {
    test: Object,
  },
};
</script>

<style scoped>
.news__container {
  min-width: 200px;
  max-width: 500px;
  text-align: center;
}
.news__body {
  font-size: 10px;
  font-weight: bold;
}
.news__head {
  font-size: 18px;
}
</style>
