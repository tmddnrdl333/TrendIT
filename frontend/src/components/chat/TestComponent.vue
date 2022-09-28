<template>
  <q-scroll-area style="height: 200px; max-width: 300px">
    <q-infinite-scroll @load="onLoad" reverse>
      <!-- <template v-slot:loading>
        <div class="row justify-center q-my-md">
          <q-spinner color="primary" name="dots" size="40px" />
        </div>
      </template> -->

      <chat-component v-for="(chat, index) in items" :key="index">
      </chat-component>

      <!-- <div v-for="(item, index) in items" :key="index" class="caption q-py-sm">
        <q-badge class="shadow-1">
          {{ items.length - index }}
        </q-badge>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum
        repellendus sit voluptate voluptas eveniet porro. Rerum blanditiis
        perferendis totam, ea at omnis vel numquam exercitationem aut, natus
        minima, porro labore.
      </div> -->
    </q-infinite-scroll>
  </q-scroll-area>
</template>

<script>
import { ref } from "vue";
import ChatComponent from "./ChatComponent.vue";
export default {
  components: {
    ChatComponent,
  },
  setup() {
    const items = ref([]);

    return {
      items,
      onLoad(index, done) {
        // setTimeout(() => {

        // }, 2000);
        for (let i = 0; i < 10; i++) {
          items.value.unshift({});
        }
        done();
      },
    };
  },
};
</script>

<style></style>
>
