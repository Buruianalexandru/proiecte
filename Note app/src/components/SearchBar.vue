<template>
  <div class="search-bar">
    <input
      v-model="query"
      type="text"
      placeholder="Search notes..."
      @input="updateQuery"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      query: '',
    };
  },
  methods: {
    updateQuery() {
      this.$emit('update:query', this.query);
    }, 
  },
};
</script>

<style scoped>
.search-bar {
  margin-bottom: 20px; 
  display: flex; 
  justify-content: center;
}


.search-bar input {
  width: 95%; 
  padding: 8px; 
  border: 1px solid #ddd; 
  border-radius: 4px; 
  font-size: 14px;
}

</style>
