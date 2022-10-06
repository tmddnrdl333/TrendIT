import MainLayout from "../layouts/MainLayout.vue";

import MainView from "../pages/MainView.vue";
import ErrorView from "../pages/ErrorView.vue";
import KeywordView from "../pages/KeywordView.vue";
import CompanyView from "../pages/CompanyView.vue";
import NewsView from "../pages/NewsView.vue";
import AboutView from "../pages/AboutView.vue";
import ChatTurnOnButtonVue from "src/components/chat/ChatTurnOnButton.vue";
import ChatComponent from "src/components/chat/ChatComponent.vue";

import NoResult from "../components/keyword/result/NoResult.vue";
import KeywordResult from "../components/keyword/result/KeywordResult.vue";

const routes = [
  {
    path: "/",
    component: MainLayout,
    children: [
      {
        path: "",
        component: MainView,
      },
      {
        path: "keyword",
        component: KeywordView,
        children: [
          {
            name: "empty_keyword",
            path: "",
            component: NoResult,
          },
          {
            name: "search_keyword",
            path: ":keyword_id",
            component: KeywordResult,
          },
        ],
      },
      {
        name: "company",
        path: "company",
        component: CompanyView,
      },
      {
        name: "news",
        path: "news",
        component: NewsView,
      },
      {
        name: "about",
        path: "about",
        component: AboutView,
      },
      {
        path: "dev",
        component: ChatTurnOnButtonVue,
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: ErrorView,
  },
];

export default routes;
