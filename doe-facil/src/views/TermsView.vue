<template>
  <main class="legal-document">
    <RouterLink to="/" aria-label="Voltar ao início">Voltar</RouterLink>
    <p class="eyebrow">Doe Fácil · versão 1.0</p>
    <h1>Termos de Uso</h1>
    <p class="notice">Modelo inicial para revisão pela organização responsável antes da publicação em produção.</p>

    <section>
      <h2>1. Finalidade</h2>
      <p>O Doe Fácil apoia o cadastro de organizações, a gestão de colaboradores e o registro e consulta de doações.</p>
    </section>
    <section>
      <h2>2. Uso da conta</h2>
      <p>O responsável deve fornecer informações corretas, manter suas credenciais em sigilo e usar a plataforma somente para atividades autorizadas pela organização.</p>
    </section>
    <section>
      <h2>3. Dados e registros</h2>
      <p>A organização é responsável por possuir base legal para inserir dados de colaboradores e doadores e por limitar o acesso às pessoas autorizadas.</p>
    </section>
    <section>
      <h2>4. Disponibilidade e contato</h2>
      <p>O serviço pode passar por manutenção. Antes de publicar estes termos, a organização deve informar aqui sua razão social, canal de contato e procedimento de suporte.</p>
    </section>
    <section>
      <h2>5. Alterações</h2>
      <p>Alterações relevantes devem ser comunicadas aos usuários, com registro da versão e da data de vigência.</p>
    </section>
  </main>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.legal-document { max-width: 760px; margin: 3rem auto; padding: 0 1.5rem 3rem; color: #202a2b; }
.eyebrow { margin-top: 2rem; color: #487568; font-size: .85rem; font-weight: 700; }
h1 { margin: .5rem 0 1rem; font-size: 2.4rem; }
h2 { margin: 2rem 0 .5rem; font-size: 1.2rem; }
p { line-height: 1.7; }
.notice { padding: 1rem; border-left: 4px solid #c1783c; background: #fff5e9; }
a { color: #176e60; }
</style>