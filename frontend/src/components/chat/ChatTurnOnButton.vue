<template>
  <div class="q-pa-md q-gutter-sm">
    <q-btn
      class="chat-btn"
      round
      push
      fab
      color="btn"
      size="lg"
      icon="textsms"
      @click="seamless = !seamless"
    />

    <q-dialog id="chat" v-model="seamless" seamless position="right">
      <q-card class="chat-dialog" style="border-radius: 10px">
        <q-card-section class="chat-title row items-center no-wrap">
          <!-- <p>키워드 채팅</p> -->
          <div style="margin: auto; text-align: center">키워드 채팅</div>
          <q-btn
            flat
            round
            icon="close"
            v-close-popup
            style="position: absolute; right: 5px"
          />
        </q-card-section>
        <q-card-section>
          <q-scroll-area style="height: 270px; max-width: 300px">
            <q-infinite-scroll reverse>
              <chat-component
                v-for="(chat, index) in chats"
                :key="index"
                :idx="index"
                @updateBoard="updateBoardToChats"
                @deleteBoard="deleteBoardToChats"
                :board="chat"
              >
              </chat-component>
            </q-infinite-scroll>
          </q-scroll-area>
        </q-card-section>
        <q-card-section class="input-card q-pa-sm">
          <div
            class="q-pa-xs"
            style="display: flex; flex-direction: row; align-items: center"
          >
            <div style="margin-right: 5px; font-size: 12px; color: gray">
              비밀번호 :
            </div>
            <q-input
              standout
              dense
              style="width: 80px"
              class="input-pw"
              color="dark"
              bg-color="grey-3"
              type="password"
              v-model="password"
            />
          </div>
          <q-input
            filled
            autogrow
            v-model="content"
            stack-label
            :label="nicknameLabel"
          >
            <template v-slot:after>
              <q-btn round dense flat icon="send" @click="sendMasseage" />
            </template>
          </q-input>
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { ref } from "vue";
import { postBoard, getBoards } from "src/boot/board.js";
import ChatComponent from "./ChatComponent.vue";
import { BoardPostReq } from "src/boot/request/BoardReq.js";
import { ChatDataRes } from "src/boot/response/ChatDataRes.js";

export default {
  name: "ChatTurnOnButton",
  components: {
    ChatComponent,
  },
  props: {
    keywordIde: Number,
  },
  watch: {
    keywordIde: function () {
      getBoards(
        this.$props.keywordIde,
        (response) => {
          const data = response.data.boardData;
          this.boardId = data[data.length - 1].boardId;

          for (let i = data.length - 1; i >= 0; i--) {
            this.chats.push(
              new ChatDataRes(
                data[i].boardId,
                data[i].createdDate,
                data[i].userName,
                data[i].boardContent
              )
            );
          }
        },
        () => {
          console.warn();
        }
      );
    },
  },
  setup() {
    function makeNickName() {
      const adj = ["강력한", "웃긴", "귀여운", "멋진", "아름다운"];
      const nouns = ["곰", "사자", "호랑이", "강아지", "고양이"];
      const name =
        adj[Math.floor(Math.random() * adj.length)] +
        nouns[Math.floor(Math.random() * nouns.length)];
      return name;
    }
    const chats = ref([]);

    const nickname = makeNickName();
    const nicknameLabel = "[" + nickname + "]";
    const content = ref("");
    const password = ref("");
    const boardId = ref(1);

    return {
      boardId,
      chats,
      seamless: ref(false),
      nickname,
      nicknameLabel,
      content,
      password,
    };
  },
  methods: {
    updateBoardToChats(idx, boardContent) {
      this.chats[idx].boardContent = boardContent;
    },
    deleteBoardToChats(idx) {
      this.chats.splice(idx, 1);
    },
    sendMasseage() {
      postBoard(
        new BoardPostReq(
          this.$props.keywordIde,
          this.nickname,
          this.password,
          this.content
        ),
        (response) => {
          this.chats.push(
            new ChatDataRes(
              response.data.data.boardId,
              response.data.data.createdDate,
              this.nickname,
              this.content
            )
          );

          this.password = "";
          this.content = "";
        },
        () => {
          console.warn();
        }
      );
    },
  },
};
</script>

<style>
.chat-title {
  font-family: "NanumBarunGothic";
  background-color: #3d5a80 !important;
  color: white;
  font-size: 20px;
  height: 55px;
}
.chat-dialog {
  width: 300px;
  height: 450px;
  margin-top: 20px;
  margin-right: 10px;
}
.chat-btn {
}
.input-card {
  position: absolute;
  bottom: 0;
  width: 100%;
  font-family: "NanumBarunGothic";
}
.input-pw .q-field__control {
  height: 25px;
}

.q-dialog__inner {
  position: fixed;
  bottom: -15%;
}

.bg-btn {
  background: #3d5a80;
}
</style>
