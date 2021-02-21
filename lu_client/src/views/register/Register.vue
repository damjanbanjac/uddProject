<template>
  <v-row>
    <v-col align="center" justify="center">
      <v-card style="width: 40%; margin-top: 2.5%">
        <v-card-title justify="center">
          <v-col>
            <h3>Register</h3>
          </v-col>
        </v-card-title>
        <v-tabs fixed-tabs>
          <v-tab to="reader">Reader</v-tab>
          <v-tab to="writer">Writer</v-tab>
        </v-tabs>
        <v-card-text>
          <v-row>
            <v-col cols="12">
              <router-view />
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {};
</script>

<style>
</style>