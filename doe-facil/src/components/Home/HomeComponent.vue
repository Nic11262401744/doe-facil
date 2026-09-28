<template>
  <div class="view">
    <section class="container glass w-auto">
      <div>
            <div class="py-5 relative">
                <img src="../../assets/img/doe_facil_log.png" alt="Logo Doar Fácil" class="w-[100px] mx-40"></img>
                <h1 class="text-center">Doar Fácil</h1>

                <Button class="!bg-[var(--p-primary-950)] !rounded-3xl absolute bottom-40 left-[90%]" icon="pi pi-sign-out" @click="router.push('/')" />
            </div>

            <div class="flex flex-col gap-3">
                <Button label="DOAÇÕES" class="!bg-[var(--p-primary-950)] !rounded-3xl" icon="pi pi-globe" @click="$router.push('/doacao')" />
                <Button label="COLABORADORES" class="!bg-[var(--p-primary-950)] !rounded-3xl" icon="pi pi-address-book" @click="$router.push('/colaboradores')" />
            </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import { Button } from 'primevue';
import { useRouter } from 'vue-router';

const router = useRouter();

</script>

<style scoped></style>
