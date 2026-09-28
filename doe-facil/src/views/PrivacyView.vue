<template>
  <main class="legal-document">
    <RouterLink to="/" aria-label="Voltar ao início">Voltar</RouterLink>
    <p class="eyebrow">Doe Fácil · versão 1.0</p>
    <h1>Política de Privacidade</h1>
    <p class="notice">Modelo inicial. A organização controladora deve revisar os dados, prazos, contatos e bases legais antes do uso real.</p>

    <section>
      <h2>1. Dados tratados</h2>
      <p>O sistema pode tratar dados de identificação e contato da ONG, do responsável, de colaboradores e de doadores, além dos registros de doações e dados técnicos necessários à autenticação.</p>
    </section>
    <section>
      <h2>2. Finalidades</h2>
      <p>Os dados são usados para autenticar responsáveis, administrar colaboradores, registrar doações e manter o funcionamento e a segurança da plataforma.</p>
    </section>
    <section>
      <h2>3. Compartilhamento e segurança</h2>
      <p>O acesso deve ser limitado às pessoas autorizadas e os dados não devem ser compartilhados para finalidades incompatíveis. A organização deve definir medidas de segurança e resposta a incidentes adequadas ao risco.</p>
    </section>
    <section>
      <h2>4. Retenção e direitos</h2>
      <p>A organização deve definir prazos de retenção e um canal para solicitações de confirmação, acesso, correção, eliminação ou outras medidas previstas na LGPD, observadas as obrigações legais aplicáveis.</p>
    </section>
    <section>
      <h2>5. Controlador e contato</h2>
      <p>Antes da publicação, informe a identificação da organização controladora, o contato do encarregado ou canal de privacidade e os procedimentos para exercer direitos.</p>
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