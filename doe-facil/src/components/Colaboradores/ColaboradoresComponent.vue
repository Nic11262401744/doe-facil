<template>
    <div class="view">
        <section class="container glass w-auto !p-0 min-w-[30em]">

            <!-- 🧾 TELA INICIAL (Histórico) -->
            <div v-if="telaAtual === 'historico'">
                <div class="bg-black flex justify-center rounded-t-3xl py-10">
                    <Button label="Novo Colaborador" @click="telaAtual = 'opcoes'"
                        class="!bg-[var(--p-primary-50)] !text-black !rounded-3xl w-full mx-8" icon="pi pi-plus" />
                </div>

                <div class="px-10">
                    <Button @click="router.push('/home')" icon="pi pi-arrow-left"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl mt-5" />
                    <h1 class="text-center my-6 text-2xl font-semibold py-5">
                        Histórico de Colaboradores
                    </h1>

                    <ul v-for="colaborador in colaboradores" :key="colaborador.id" @click="abrirDetalhes(colaborador)"
                        class="cursor-pointer hover:bg-[var(--p-primary-100)] transition-all border-2 border-black m-6 p-4 rounded-xl grid grid-cols-2 gap-4 [&>div]:flex [&>div]:flex-col [&>div]:justify-center">
                        <!-- Coluna 1: Nome + E-mail -->
                        <div>
                            <li>{{ colaborador.nome }}</li>
                            <li><small>{{ colaborador.email }}</small></li>
                        </div>

                        <!-- Coluna 2: Cargo -->
                        <div class="items-end text-right">
                            <li>{{ colaborador.cargo }}</li>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- ➕ TELA DE CADASTRO -->
            <div v-else-if="telaAtual === 'opcoes'" class="flex flex-col w-full my-10">
                <div class="flex justify-center items-center relative">
                    <Button @click="telaAtual = 'historico'"
                        class="!bg-[var(--p-primary-950)] !rounded-3xl m-6 absolute right-[41%]"
                        icon="pi pi-chevron-left" />
                    <h1 class="absolute left-[15%] w-full">Novo Colaborador</h1>
                </div>

                <div class="flex flex-col gap-5 mt-10 mx-20">
                    <FloatLabel variant="on">
                        <InputText v-model="novoColaborador.nome" />
                        <label>Nome</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="novoColaborador.telefone" />
                        <label>Telefone</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="novoColaborador.email" />
                        <label>E-mail</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="novoColaborador.Documento" />
                        <label>Documento</label>
                    </FloatLabel>
                    <FloatLabel variant="on">
                        <InputText v-model="novoColaborador.cargo" />
                        <label>Cargo</label>
                    </FloatLabel>

                    <Button label="Cadastrar Colaborador" class="!bg-[var(--p-primary-950)] !rounded-3xl"
                        icon="pi pi-check" @click="adicionarColaborador" />
                </div>
            </div>

            <!-- 🪟 DIALOG DE DETALHES -->
            <Dialog v-model:visible="mostrarDialog" modal header="Detalhes do Colaborador" class="w-[90%] sm:w-[40rem]"
                dismissable-mask>
                <div v-if="colaboradorSelecionado" class="space-y-3 text-lg">
                    <p><strong>Nome:</strong> {{ colaboradorSelecionado.nome }}</p>
                    <p><strong>Telefone:</strong> {{ colaboradorSelecionado.telefone }}</p>
                    <p><strong>E-mail:</strong> {{ colaboradorSelecionado.email }}</p>
                    <p><strong>Documento:</strong> {{ colaboradorSelecionado.cpf }}</p>
                    <p><strong>Cargo:</strong> {{ colaboradorSelecionado.cargo }}</p>
                </div>
            </Dialog>

        </section>
    </div>
</template>

<script setup lang="ts">
import { Button, FloatLabel, InputText, Dialog } from 'primevue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { api, apiUrls } from "@/plugins/axios";

const router = useRouter();

// controle de telas
const telaAtual = ref<'historico' | 'opcoes'>('historico');

// controle do Dialog
const idOng = localStorage.getItem('idOng');
const endpoint = `${apiUrls.colaboradores}/Colaboradores`;
executarBuscaColaborador();
const mostrarDialog = ref(false);
const colaboradorSelecionado = ref<any>(null);

// dados fictícios ajustados
const colaboradores = ref<any>([]);

// formulário de novo colaborador
const novoColaborador = ref({
    nome: '',
    telefone: '',
    email: '',
    Documento: '',
    cargo: ''
});

// abre o Dialog
function abrirDetalhes(colaborador: any) {
    colaboradorSelecionado.value = colaborador;
    mostrarDialog.value = true;
}

// adiciona colaborador novo
function adicionarColaborador() {
    // colaboradores.value.push({
    //     id: Date.now(),
    //     ...novoColaborador.value
    // });

    api.post(endpoint, 
        {
            "nome": novoColaborador.value.nome,
            "cpf": novoColaborador.value.Documento,
            "email": novoColaborador.value.email,
            "telefone": novoColaborador.value.telefone,
            "cargo": novoColaborador.value.cargo,
            "idOng": idOng
        }
    )
    .then(function (response) {
            if(response.status == 201)
                router.push("/colaboradores");
        })
    .catch(function (error) {
        console.log(error)
    })


// limpa formulário
novoColaborador.value = {
    nome: '',
    telefone: '',
    email: '',
    Documento: '',
    cargo: ''
};

telaAtual.value = 'historico';
}

function executarBuscaColaborador() {

    api.get(endpoint + "/byOng/" + idOng)
        .then(function (response) {
            console.log(response.data)
            colaboradores.value = response.data
        })
        .catch(function (error) {
            console.log(error)
        })
}
</script>

<style scoped lang="scss"></style>
