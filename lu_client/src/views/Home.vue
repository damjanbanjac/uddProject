<template>


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
