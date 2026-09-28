<template>
</template>

<script setup lang="ts">
import { onBeforeMount } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const logout = () => {
    localStorage.clear()
    router.push("/") // certifique-se de colocar o path da página de login aqui
}

onBeforeMount(() => {
    logout()
})
</script>
