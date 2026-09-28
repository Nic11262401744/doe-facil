<template>
    <div class="view">
        <section class="container glass w-auto !p-0 min-w-[30em]">

            <!-- TELA INICIAL (Histórico) -->
            <div v-if="telaAtual === 'historico'">
                <div class="bg-black flex justify-center rounded-t-3xl py-10">
                    <Button label="Nova Doação" @click="telaAtual = 'opcoes'"
                        class="!bg-[var(--p-primary-50)] !text-black !rounded-3xl w-full mx-8" icon="pi pi-plus" />
                </div>

                <div class="px-10">
                    <Button @click="router.push('/home')" icon="pi pi-arrow-left"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl mt-5" />
                    <h1 class="text-center my-6 text-2xl font-semibold py-5">Histórico de Doações</h1>
                        
                    <ul v-for="doacao in doacoes" :key="doacao.id"
                        class="border-2 border-black m-6 p-4 rounded-xl grid grid-cols-2 gap-4 [&>div]:flex [&>div]:flex-col [&>div]:justify-center">
                        <!-- Coluna 1: Tipo + mensagem -->
                        <div>
                            <li>{{ doacao.tipo }}</li>
                            <li><small>{{ doacao.descricao }}</small></li>
                        </div>

                        <!-- Coluna 2: Código + doador -->
                        <div class="items-end">
                            <li>Cod: {{ doacao.id }}</li>
                            <li><small>Doador: {{ doacao.doador }}</small></li>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- OPÇÕES DE DOAÇÃO -->
            <div v-else-if="telaAtual === 'opcoes'" class="flex flex-col w-full my-10">
                <div class="flex justify-center items-center relative">
                    <Button @click="telaAtual = 'historico'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl m-6 absolute right-[41%]"
                        icon="pi pi-chevron-left" />
                    <h1 class="absolute left-[25%] w-full">Nova Doação</h1>
                </div>

                <div class="flex flex-col gap-5 mt-10">
                    <Button label="Nova doação de Bem" @click="telaAtual = 'doacaoBem'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl mx-6" icon="pi pi-plus" />
                    <Button label="Nova doação Monetária" @click="telaAtual = 'doacaoMonetaria'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl mx-6" icon="pi pi-plus" />
                </div>
            </div>

            <!-- DOAÇÃO DE BEM -->
            <div v-else-if="telaAtual === 'doacaoBem'" class="flex flex-col w-full my-10">
                <div class="flex justify-center items-center relative">
                    <Button @click="telaAtual = 'opcoes'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl m-6 absolute right-[41%]"
                        icon="pi pi-chevron-left" />
                    <h1 class="absolute left-[20%] w-full">Doação de Bem</h1>
                </div>

                <div class="mt-10 text-center flex flex-col gap-5 mx-20">
                    <FloatLabel variant="on">
                        <InputText v-model="doacaoBem" />
                        <label>Opções de Bem</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="descricao" />
                        <label>Descrição</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputNumber v-model="quantidade" mask="integer" />
                        <label>Quantidade</label>
                    </FloatLabel>

                    <!-- <div class="flex items-center gap-3">
                        <Checkbox v-model="eAnonimo" />
                        <label>Anônimo</label>
                    </div> -->

                    <FloatLabel variant="on">
                        <InputText v-model="nomeDoador" />
                        <label>Nome do Doador</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="nomeColaborador" />
                        <label>Nome do Colaborador</label>
                    </FloatLabel>

                    <Button @click="executarDoacaoBem" label="Registrar Doação"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl mt-5" icon="pi pi-check" />
                </div>
            </div>

            <!-- DOAÇÃO MONETÁRIA -->
            <div v-else-if="telaAtual === 'doacaoMonetaria'" class="flex flex-col w-full my-10">
                <div class="flex justify-center items-center relative">
                    <Button @click="telaAtual = 'opcoes'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl m-6 absolute right-[41%]"
                        icon="pi pi-chevron-left" />
                    <h1 class="absolute left-[15%] w-full">Doação Monetária</h1>
                </div>


                <div class="mt-10 text-center flex flex-col gap-5 mx-20">
                    <FloatLabel variant="on">
                        <InputNumber v-model="valor" mask="currency" />
                        <label>Valor</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="maneiraDoa" />
                        <label>Maneira de Doação</label>
                    </FloatLabel>

                    <!-- <div class="flex items-center gap-3">
                        <Checkbox v-model="eAnonimo" />
                        <label>Anônimo</label>
                    </div> -->

                    <FloatLabel variant="on">
                        <InputText v-model="nomeDoador" />
                        <label>Nome do Doador</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="nomeColaborador" />
                        <label>Nome do Colaborador</label>
                    </FloatLabel>

                    <Button label="Registrar Doação" class="!bg-[var(--p-primary-950)] !rounded-3xl mt-5"
                        icon="pi pi-check" @click="executarDoacaoMonetaria" />
                </div>
            </div>

        </section>
    </div>
</template>

<script setup lang="ts">
import { Button, Checkbox, FloatLabel, InputNumber, InputText } from 'primevue';
import { ref, type Ref } from "vue";
import { useRouter } from 'vue-router';
import { useToast } from "primevue/usetoast";
import { api, apiUrls } from "@/plugins/axios";

const toast = useToast();
const router = useRouter();

const endpoint = `${apiUrls.doacoes}/doacao`;
const doacaoBem: Ref<string> = ref("");
const descricao: Ref<string> = ref("");
const quantidade: Ref<number> = ref(0);
const valor: Ref<number> = ref(0);
const eAnonimo: Ref<boolean> = ref(false);
const nomeDoador: Ref<string> = ref("");
const nomeColaborador: Ref<string> = ref("");
const maneiraDoa: Ref<string> = ref("");
const data = new Date().toLocaleDateString('pt-BR')
const idOng = localStorage.getItem('idOng');

// controle de telas
const telaAtual = ref<'historico' | 'opcoes' | 'doacaoBem' | 'doacaoMonetaria'>('historico');

// dados fictícios
const doacoes = ref<any>([]);
executarBuscaDoacoes();

const executarDoacaoMonetaria = async () => {

  try {

    let doadorAno = nomeDoador.value == "" ? "Anônimo" : nomeDoador.value

    const response = await api.post(endpoint, {
      tipo: "FINANCEIRA",
      descricao: `${maneiraDoa.value} - valor:R$ ${valor.value} - recebido por: ${nomeColaborador.value}`,
      valor: valor.value,
      data_doacao: data,
      doador: doadorAno,
      colaborador_nome: nomeColaborador.value,
      ong_id: idOng
    });

    toast.add({
      severity: "success",
      summary: "Doação cadastrada com sucesso!",
      detail: "Você já pode encontrar a doação na plataforma.",
      life: 3000
    });

    console.log(response)

    router.push("/home");
  } catch (error) {
    toast.add({
      severity: "error",
      summary: "Erro ao tentar cadastrar doação.",
      detail: "Verifique se os dados inseridos estão corretos.",
      life: 3000
    });
    console.error(error);
  }
};

const executarDoacaoBem = async () => {
  try {

    let doadorAno = nomeDoador.value == "" ? "Anônimo" : nomeDoador.value

    const response = await api.post(endpoint, {
      tipo: "PRODUTO",
      descricao: `produto: ${doacaoBem.value} - quantidade: ${quantidade.value} - recebido por: ${nomeColaborador.value}`,
      valor: null,
      data_doacao: data,
      doador: doadorAno,
      colaborador_nome: nomeColaborador.value,
      ong_id: idOng
    });

    console.log(response);

    toast.add({
      severity: "success",
      summary: "Doação cadastrada com sucesso!",
      detail: "Você já pode encontrar a doação na plataforma.",
      life: 3000
    });

    router.push("/home");
  } catch (error) {
    console.error(error);

    toast.add({
      severity: "error",
      summary: "Erro ao tentar cadastrar doação.",
      detail: "Verifique se os dados inseridos estão corretos.",
      life: 3000
    });
  }
};

async function executarBuscaDoacoes() {
  try {
    const response = await api.get(`${endpoint}/byOng/${idOng}`);
    doacoes.value = response.data;
  } catch (error) {
    console.error(error);
  }
}



</script>

<style scoped lang="scss"></style>
