<template>
  <div class="q-pa-md q-gutter-sm">
    <q-btn
      round
      color="primary"
      size="xl"
      icon="message"
      @click="seamless = true"
    />

    <q-dialog v-model="seamless" seamless position="right">
      <q-card
        style="
          width: 300px;
          height: 500px;
          margin-right: 20px;
          margin-top: 20px;
          border-radius: 30px;
        "
      >
        <q-card-section
          class="row items-center no-wrap"
          style="
            background-color: #1a237e !important;
            color: white;
            font-size: 23px;
          "
        >
          <!-- <p>키워드 채팅</p> -->
          <div style="flex-basis: 250px; text-align: center">키워드 채팅</div>
          <q-btn
            flat
            round
            icon="close"
            v-close-popup
            style="flex-basis: 50px"
          />
        </q-card-section>
        <q-card-section>
          <q-scroll-area style="height: 200px; max-width: 300px">
            <q-infinite-scroll @load="onLoad" reverse>
              <template v-slot:loading>
                <div class="row justify-center q-my-md">
                  <q-spinner color="primary" name="dots" size="40px" />
                </div>
              </template>

              <div
                v-for="(item, index) in items"
                :key="index"
                class="caption q-py-sm"
              >
                <q-badge class="shadow-1">
                  {{ items.length - index }}
                </q-badge>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum
                repellendus sit voluptate voluptas eveniet porro. Rerum
                blanditiis perferendis totam, ea at omnis vel numquam
                exercitationem aut, natus minima, porro labore.
              </div>
            </q-infinite-scroll>
          </q-scroll-area>
        </q-card-section>
        <q-card-section>
          <div style="display: flex; flex-direction: row; align-items: center">
            <div style="margin-right: 20px; font-size: 15px; color: gray">
              비밀번호 :
            </div>
            <q-input
              rounded
              standout
              dense
              style="width: 100px"
              color="dark"
              bg-color="grey-3"
              type="password"
              v-model="password"
            />
          </div>
          <q-input filled autogrow v-model="nickname" :label="nicknameLabel" />
          <q-btn round dense flat icon="send" />
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { ref } from "vue";
export default {
  name: "ChatTurnOnButton",
  setup() {
    const items = ref([{}, {}, {}, {}, {}, {}, {}]);
    const nicknameLabel = "[북극곰]";
    const nickname = ref("");
    const password = ref("");

    return {
      seamless: ref(false),
      items,
      nicknameLabel,
      nickname,
      password,
      onLoad(index, done) {
        // 여기서 데이터를 요청함, 그리고 번호를 기록해놓음
        setTimeout(() => {
          items.value.splice(0, 0, {}, {}, {}, {}, {}, {}, {});
          done();
        }, 2000);
      },
    };
  },
};
</script>

<style></style>
>
