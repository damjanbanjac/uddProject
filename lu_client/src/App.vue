<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <router-link to="/" tag="h4">Literalno udruzenje</router-link>
      <v-spacer></v-spacer>
       <v-btn icon to="/upload">
        <v-icon>mdi-account-plus</v-icon>
      </v-btn>
      <v-btn icon to="/search">
        <v-icon>mdi-account-search</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data: () => ({}),
};
</script>

<style scoped>

a:hover {
  text-decoration: none;
}
h4:hover {
  cursor: pointer;
}
</style>
