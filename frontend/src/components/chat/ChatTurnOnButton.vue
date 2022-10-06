<template>
  <div class="q-pa-md q-gutter-sm">
    <q-btn
      round
      color="primary"
      size="xl"
      icon="message"
      @click="seamless = !seamless"
    />

    <q-dialog id="chat" v-model="seamless" seamless position="right">
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
          <!-- <test-component></test-component> -->
          <q-scroll-area style="height: 200px; max-width: 300px">
            <q-infinite-scroll @load="onLoad" reverse>
              <!-- <template v-slot:loading>
                <div class="row justify-center q-my-md">
                  <q-spinner color="primary" name="dots" size="40px" />
                </div>
              </template> -->

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
          <q-input filled autogrow v-model="content" :label="nicknameLabel">
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
import TestComponent from "./TestComponent.vue";

export default {
  name: "ChatTurnOnButton",
  components: {
    ChatComponent,
    // TestComponent,
  },
  props: {
    keywordIde: Number,
  },
  watch: {
    keywordIde: function () {
      console.log(this.$props.keywordIde, "WATCH");
      getBoards(
        this.$props.keywordIde,
        (response) => {
          const data = response.data.boardData;
          this.boardId = data[data.length - 1].boardId;
          // 1~100, 101~200 => 200~101 // 100~1

          for (let i = data.length - 1; i >= 0; i--) {
            // 첫 인덱스 저장
            this.chats.push(
              new ChatDataRes(
                data[i].boardId,
                data[i].createdDate,
                data[i].userName,
                data[i].boardContent
              )
            );
            // chats 맨 마지막에 data[0]이 와야됨
          }
        },
        () => {
          console.warn();
        }
      );
    },
  },
  setup() {
    // console.log(this.keywordIde, "KEYWORD IED");
    function makeNickName() {
      const adj = ["강력한", "웃긴", "귀여운", "멋진", "아름다운"];
      const nouns = ["곰", "사자", "호랑이", "강아지", "고양이"];
      const name =
        adj[Math.floor(Math.random() * adj.length)] +
        nouns[Math.floor(Math.random() * nouns.length)];
      console.log(name);
      return name;
    }
    // console.log(this.keywordIde);
    // const items = ref([{}, {}, {}, {}, {}, {}, {}]);
    const chats = ref([]);

    const nickname = makeNickName();
    const nicknameLabel = "[" + nickname + "]";
    const content = ref("");
    const password = ref("");
    const boardId = ref(1);

    // const keywordId = 1;

    // getBoards(
    //   keywordId,
    //   (response) => {
    //     console.log(response);
    //     const data = response.data.boardData;
    //     boardId.value = data[data.length - 1].boardId;
    //     console.log(boardId.value);
    //     // 1~100, 101~200 => 200~101 // 100~1

    //     for (let i = data.length - 1; i >= 0; i--) {
    //       // 첫 인덱스 저장
    //       chats.value.push(
    //         new ChatDataRes(
    //           data[i].createdDate,
    //           data[i].userName,
    //           data[i].boardContent
    //         )
    //       );
    //       // chats 맨 마지막에 data[0]이 와야됨
    //     }
    //     console.log("first", chats.value);
    //   },
    //   () => {
    //     console.warn();
    //   }
    // );

    return {
      boardId,
      chats,
      seamless: ref(false),
      nickname,
      nicknameLabel,
      content,
      password,
      onLoad(index, done) {
        // 여기서 데이터를 요청함, 그리고 번호를 기록해놓음
        // setTimeout(() => {
        //   items.value.splice(0, 0, {}, {}, {}, {}, {}, {}, {});
        //   done();
        // }, 2000);
        // setTimeout(() => {

        // }, 2000);
        let length;
        // getBoards(
        //   this.keywordIde,
        //   (response) => {
        //     console.log(response);
        //     const data = response.data.boardData;
        //     length = data.length;
        //     boardId.value = data[data.length - 1].boardId;
        //     for (let i = 0; i < data.length; i++) {
        //       chats.value.unshift(
        //         new ChatDataRes(
        //           data[i].createdDate,
        //           data[i].userName,
        //           data[i].boardContent
        //         )
        //       );
        //     }
        //     if (length >= 100) done();
        //     else done(true);
        //     // done();
        //   },
        //   () => {
        //     console.warn();
        //   },
        //   boardId.value
        // );
      },
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
          // 성공일 때
          // 성공 알림 후

          this.chats.push(
            new ChatDataRes(
              response.data.data.boardId,
              response.data.data.createdDate,
              this.nickname,
              this.content
            )
          );
          console.log("push2");

          this.password = "";
          this.content = "";

          // 실패일 때
          // 실패 알림
        },
        () => {
          console.warn();
        }
      );
    },
  },
};
</script>

<style></style>
>
