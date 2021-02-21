<template>
  <v-row>
    <v-col>
      <v-row>
        <v-col align="center">
          <v-btn color="green" dark @click="getTodos">Test</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Todos</th>
                  <th class="text-left">Completed</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="todo in todos" :key="todo.id">
                  <td>{{ todo.title }}</td>
                  <td>{{ todo.completed }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "Home",
  computed: {
    ...mapGetters(["todos"]),
  },
  methods: {
    ...mapActions(["getTodos"]),
  },
};
</script>
