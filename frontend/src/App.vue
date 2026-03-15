<template>
  <div class="app">
    <header>
      <h1>🌤️ Weather Dashboard</h1>
    </header>
    <main>
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(135deg, #1a1a2e, #16213e, #0f3460);
  min-height: 100vh;
  color: white;
}

.app {
  max-width: 900px;
  margin: 0 auto;
  padding: 2rem;
}

header {
  text-align: center;
  margin-bottom: 2rem;
}

header h1 {
  font-size: 2.5rem;
  letter-spacing: 2px;
}
</style>