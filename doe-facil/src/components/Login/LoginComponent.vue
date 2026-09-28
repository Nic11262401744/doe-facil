<template>
  <div class="flex no-wrap min-h-dvh items-stretch">

    <div class="flex flex-col items-center justify-center w-full bg-cover">

      <div class="flex flex-col items-center justify-center rounded-3xl glass relative">
        <div class="flex flex-col justify-center items-center">
          <img src="../../assets/img/doe_facil_log.png" alt="Logo Doar Fácil" class="w-[100px] mx-40 my-5">
          <h3 class="!font-extrabold !text-[var(--p-primary-700)] p-5 textShadow">
            {{ textoExibido }}<span v-if="!finalizado" class="cursor">|</span>
          </h3>
          <!-- @autor: Gabriel -->
        </div>

        <div v-if="login" class="flex flex-col gap-5 w-full items-center">
          <FloatLabel class="w-[75%]" variant="on">
            <InputMask v-model="username" name="login" mask="99.999.999/9999-99" />
            <label> CNPJ </label>
          </FloatLabel>

          <FloatLabel class="w-[75%]" variant="on">
            <Password v-model="password" toggleMask name="senha" class="w-[100%]" :feedback="false"
              @keyup.enter="entrar" />
            <label for="user"> Senha </label>
          </FloatLabel>

          <!-- <div>
            Esqueceu a senha? <span @click="visibleEsqueceu = true"
              class="text-[var(--p-primary-700)] underline cursor-pointer">Clique aqui</span>
          </div> -->

          <div class="flex gap-10 w-[72.5%]">
            <Button @click="login = false" label="VOLTAR" class="w-1/2 !rounded-3xl" icon="pi pi-chevron-left"></Button>
            <Button @click="entrar" type="button" label="LOGIN" :loading="loading" icon="pi pi-sign-in"
              class="w-1/2 !bg-[var(--p-primary-950)] !rounded-3xl"></Button>
          </div>
        </div>

        <div v-else-if="cadastro" class="flex flex-col gap-5">
          <FloatLabel variant="on">
            <InputText v-model="nomeOng" />
            <label>Nome da ONG</label>
          </FloatLabel>

          <FloatLabel variant="on">
            <InputText v-model="objetivoOng" />
            <label>Objetivo da ONG</label>
          </FloatLabel>

          <!-- <FloatLabel variant="on">
            <InputText v-model="emailOng" />
            <label>E-mail da ONG</label>
          </FloatLabel> -->

          <FloatLabel variant="on">
            <InputMask v-model="cnpjOng" mask="99.999.999/9999-99" />
            <label>CPNJ</label>
          </FloatLabel>

          <div class="flex flex-col gap-2">
            <FloatLabel variant="on">
              <InputMask v-model="ruaOng" mask="99999-999" />
              <label>CEP</label>
            </FloatLabel>
            <Button label="Consultar CEP" icon="pi pi-map-marker" severity="secondary" @click="consultarCep" />
            <small v-if="enderecoConsultado">{{ enderecoConsultado }}</small>
          </div>
          <FloatLabel variant="on">
            <InputText v-model="numeroEndereco" />
            <label>Número</label>
          </FloatLabel>
          <FloatLabel variant="on">
            <InputText v-model="complementoEndereco" />
            <label>Complemento (opcional)</label>
          </FloatLabel>


          <FloatLabel variant="on">
            <Password v-model="senhaOng" toggleMask name="senha" class="w-full" :feedback="false" />
            <label>Senha</label>
          </FloatLabel>

          <FloatLabel variant="on">
            <Password v-model="confirSenhaOng" toggleMask :feedback="false" class="w-full" />
            <label>Confirme sua senha</label>
          </FloatLabel>

          <div class="flex gap-10">
            <Button label="VOLTAR" class="!rounded-3xl" icon="pi pi-chevron-left" @click="cadastro = false" />
            <Button label="PRÓXIMO" class="!bg-[var(--p-primary-950)] !rounded-3xl" icon="pi pi-chevron-right"
              @click="proximaEtapa = true, cadastro = false" />
          </div>

        </div>

        <div v-else-if="proximaEtapa" class="flex flex-col gap-5 w-[75%]">
          <FloatLabel variant="on">
            <InputText v-model="emailOng" />
            <label>E-mail</label>
          </FloatLabel>
          <FloatLabel variant="on">
            <InputText v-model="telefoneOng" />
            <label>Telefone</label>
          </FloatLabel>
          <FloatLabel variant="on">
            <InputText v-model="nomeResponsavel" />
            <label>Nome do responsável</label>
          </FloatLabel>
          <FloatLabel variant=" on">
            <InputMask v-model="cpf" mask="999.999.999-99" />
            <label>CPF</label>
          </FloatLabel>

          <div class="flex items-start gap-2 text-sm">
            <Checkbox v-model="aceiteLegal" inputId="aceite-legal" binary />
            <label for="aceite-legal">
              Li e aceito os <a href="/termos" target="_blank" rel="noopener">Termos de Uso</a> e a
              <a href="/privacidade" target="_blank" rel="noopener">Política de Privacidade</a>.
            </label>
          </div>

          <div class="flex gap-10">
            <Button label="VOLTAR" class="!rounded-3xl" icon="pi pi-chevron-left"
              @click="proximaEtapa = false, cadastro = true" />
            <Button label="CADASTRAR" :disabled="!aceiteLegal" class="!bg-[var(--p-primary-950)] !rounded-3xl" icon="pi pi-plus"
              @click="cadastrar" />
          </div>

        </div>

        <div v-else class="flex flex-col gap-5 w-[75%]">
          <Button label="LOGIN" class="!bg-[var(--p-primary-950)] !rounded-3xl" @click="login = true" />
          <Button label="CADASTRO" class="!bg-[var(--p-primary-950)] !rounded-3xl" @click="cadastro = true" />
        </div>

      </div>
    </div>
  </div>

  <!-- <Dialog v-model:visible="visibleEsqueceu" header="Recuperar senha" modal dismissableMask>
    <InputMask v-model="esqueceuCnpjOng" mask="99.999.999/9999-99" />

    <div class="flex justify-center pt-5">
      <Button label="Enviar" class="!bg-[var(--p-primary-950)] !rounded-3xl" @click="esqueceuSenha" />
    </div>
  </Dialog> -->

  <Toast position="bottom-right" />
</template>

<script setup lang="ts">
import Password from "primevue/password";
import Button from "primevue/button";
import { onMounted, ref, type Ref } from "vue";
import Toast from "primevue/toast";
import { useToast } from "primevue/usetoast";
import { Checkbox, Dialog, FloatLabel, InputMask, InputText } from "primevue";
import { tituloProjeto } from "@/utils";
import { useRouter } from "vue-router";
import { api, apiUrls } from "@/plugins/axios";

const toast = useToast();
const router = useRouter();

const username: Ref<string> = ref("");
const password: Ref<string> = ref("");
const loading: Ref<boolean> = ref(false);

const login = ref<boolean>(false)
const cadastro = ref<boolean>(false)
const proximaEtapa = ref<boolean>(false)

const visibleEsqueceu = ref<boolean>(false);
const esqueceuCnpjOng: Ref<string> = ref("");

const nomeOng: Ref<string> = ref("");
const cnpjOng: Ref<string> = ref("");
const telefoneOng: Ref<string> = ref("");
const emailOng: Ref<string> = ref("");
const objetivoOng: Ref<string> = ref("");
const senhaOng: Ref<string> = ref("");
const confirSenhaOng: Ref<string> = ref("");
const ruaOng: Ref<string> = ref("");
const nomeResponsavel: Ref<string> = ref("");
const cpf: Ref<string> = ref("");
const aceiteLegal = ref(false);
const numeroEndereco: Ref<string> = ref("");
const complementoEndereco: Ref<string> = ref("");
const enderecoConsultado: Ref<string> = ref("");

const consultarCep = async () => {
  try {
    const cep = ruaOng.value.replace(/\D/g, "");
    const response = await api.get(`${apiUrls.ongs}/ongs/cep/${cep}`);
    enderecoConsultado.value = [response.data.logradouro, response.data.localidade, response.data.uf]
      .filter(Boolean).join(" - ");
  } catch {
    enderecoConsultado.value = "CEP não localizado. Confira o número e tente novamente.";
  }
};


const entrar = async () => {

  const cnpjRMask = username.value.replace(/[^\d]/g, "")

  api.post(`${apiUrls.ongs}/login`, {
    cnpj: cnpjRMask,
    senha: password.value
  }).then(function (response) {

    if (response.status === 200) {
      login.value = true;
      localStorage.setItem('idOng', response.data.idOng)
      localStorage.setItem('Token', response.data.token)
      localStorage.setItem('Expiration', String(Date.now() + response.data.expiresIn * 1000))
      router.push("/home");
    }
  }).catch(function (error) {
    console.log(error)
    if (error.response?.status === 401) {
      toast.add({ severity: 'error', summary: 'Erro ao tentar executar o login', detail: 'Verifique se a senha e cnpj estão corretos.', life: 3000 });
    }
  });
};

const cadastrar = async () => {
  try {
    await api.post(`${apiUrls.ongs}/ongs`, {
      nome: nomeOng.value,
      area_autacao: objetivoOng.value,
      cnpj: cnpjOng.value.replace(/[^\d]/g, ""),
      cep: ruaOng.value.replace(/\D/g, ""),
      numero: numeroEndereco.value,
      complemento: complementoEndereco.value,
      senha: senhaOng.value,
      confirmacao_senha: confirSenhaOng.value,
      email: emailOng.value,
      telefone: telefoneOng.value.replace(/[^\d]/g, ""),
      responsavel: nomeResponsavel.value,
      cpf: cpf.value.replace(/[^\d]/g, ""),
      aceiteTermos: aceiteLegal.value,
      aceitePrivacidade: aceiteLegal.value
    });

    toast.add({
      severity: "success",
      summary: "ONG cadastrada com sucesso!",
      detail: "Você já pode fazer o login na plataforma.",
      life: 3000
    });

    username.value = cnpjOng.value;
    login.value = true;
    cadastro.value = false;
    proximaEtapa.value = false;
    aceiteLegal.value = false;
  } catch (error) {
    toast.add({
      severity: "error",
      summary: "Erro ao tentar cadastrar ONG.",
      detail: "Verifique se os dados inseridos estão corretos.",
      life: 3000
    });
  }
  finally {
    setTimeout(() => {
      if (!login.value) {
        cadastro.value = false
        proximaEtapa.value = false
      }
    }, 3250);
  }
};

const textos = ["Doar é fácil", `${tituloProjeto}`];

const textoExibido = ref("");
let indiceTextoAtual = 0;
let indiceCaractere = 0;
let apagando = false;
let finalizado = false;

const velocidadeDigitacao = 100; // Velocidade de digitação (ms)
const velocidadeApagacao = 50; // Velocidade de apagar (ms)
const pausaEntreTextos = 1500; // Pausa entre os textos (ms)

function escreverTexto() {
  if (finalizado) return;

  const textoAtual = textos[indiceTextoAtual];

  if (apagando) {
    textoExibido.value = textoAtual.substring(0, indiceCaractere - 1);
    indiceCaractere--;
  } else {
    textoExibido.value = textoAtual.substring(0, indiceCaractere + 1);
    indiceCaractere++;
  }

  let tempoEspera = apagando ? velocidadeApagacao : velocidadeDigitacao;

  if (!apagando && indiceCaractere === textoAtual.length) {
    if (indiceTextoAtual === textos.length - 1) {
      // Estamos no último texto -> NÃO apagar, apenas finalizar
      finalizado = true;
      return;
    } else {
      // Se não é o último texto, aguardar e começar a apagar
      tempoEspera = pausaEntreTextos;
      apagando = true;
    }
  } else if (apagando && indiceCaractere === 0) {
    // Terminou de apagar, passar para o próximo texto
    indiceTextoAtual++;
    apagando = false;
  }

  setTimeout(escreverTexto, tempoEspera);
}

onMounted(() => {
  escreverTexto();
});

</script>

<style scoped></style>
