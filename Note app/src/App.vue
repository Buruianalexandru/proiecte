<template>
  <div class="container">
    <h1>Aplicatie de notite</h1>
    <SearchBar v-model:query="searchQuery" /> 
    <NoteForm @note-saved="refreshNotes" />
    <NoteList :searchQuery="searchQuery" ref="noteList" /> 
  </div>
</template>

<script>
import NoteForm from "./components/NoteForm.vue";
import NoteList from "./components/NoteList.vue";
import SearchBar from "./components/SearchBar.vue";

export default {
  components: {
    NoteForm,
    NoteList,
    SearchBar,
  },
  data() {
    return {
      searchQuery: '',
    };
  },
  methods: {
    refreshNotes() {
      this.$refs.noteList.fetchNotes();
    },// acceseaza componenta notelist si apeleaza functia fetchnotes
  },
};
</script>

<style>
@import './styles.css';
</style>
