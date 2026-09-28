import "./assets/global.css";
import "primeicons/primeicons.css";
// import 'primevue/resources/themes/aura-light-blue/theme.css'
// * Tema da aplicação, acesse o node_modules @primevue para ver mais.
// * Deixar o tema sempre por último, para que não cause conflito entre outros estilos.
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import PrimeVue from "primevue/config";
import Ripple from "primevue/ripple";
import ToastService from "primevue/toastservice";
import Aura from "@primevue/themes/aura";
import { definePreset } from "@primevue/themes";
import KeyFilter from "primevue/keyfilter";
import ConfirmationService from "primevue/confirmationservice";
import { Tooltip } from "primevue";
// import { createPinia } from "pinia";

const app = createApp(App);
// const pinia = createPinia();
const MyPreset = definePreset(Aura, {
  semantic: {
    primary: {
      50: "#F5F5F5", // Tom bem claro
      100: "#E0E0E0",
      200: "#CCCCCC",
      300: "#B3B3B3",
      400: "#999999",
      500: "#808080", // Cor principal (preto médio)
      600: "#666666",
      700: "#4D4D4D",
      800: "#333333",
      900: "#1A1A1A",
      950: "#0D0D0D", // Quase preto total
    },
  },
});
// app.use(pinia);
app.use(router);
app.use(ConfirmationService);
app.directive("tooltip", Tooltip);
app.use(PrimeVue, {
  ptOptions: {
    mergeSections: true,
    mergeProps: true,
  },
  locale: {
    firstDayOfWeek: 1,
    dayNames: [
      "Domingo",
      "Segunda-feira",
      "Terça-feira",
      "Quarta-feira",
      "Quinta-feira",
      "Sexta-feira",
      "Sábado",
    ],
    dayNamesShort: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
    dayNamesMin: ["D", "S", "T", "Q", "Q", "S", "S"],
    monthNames: [
      "Janeiro",
      "Fevereiro",
      "Março",
      "Abril",
      "Maio",
      "Junho",
      "Julho",
      "Agosto",
      "Setembro",
      "Outubro",
      "Novembro",
      "Dezembro",
    ],
    monthNamesShort: [
      "Jan",
      "Fev",
      "Mar",
      "Abr",
      "Mai",
      "Jun",
      "Jul",
      "Ago",
      "Set",
      "Out",
      "Nov",
      "Dez",
    ],
    today: "Hoje",
    clear: "Limpar",
    weekHeader: "Sem",
  },
  theme: {
    preset: MyPreset,
    options: {
      darkModeSelector: false,
    },
  },
  Ripple: true,
});
app.use(ToastService);
app.directive("ripple", Ripple);
app.directive("keyfilter", KeyFilter);
app.mount("#app");

// export { pinia };
