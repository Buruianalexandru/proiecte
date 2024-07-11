<template>
  <div class="notes-list">
    <h2>Notite:</h2>
    <ul>
      <li v-for="note in filteredNotes" :key="note.id">
        <div>{{ new Date(note.date).toLocaleDateString() }} - {{ note.content }}</div>
        <div v-if="note.tag">Tag: {{ note.tag }}</div> 
      </li>
    </ul>
  </div>
</template>

<script>
import { db } from "../firebase-config";
import { collection, query, orderBy, getDocs } from "firebase/firestore";

export default {
  props: ['searchQuery'],//asteapta date de la notelist
  data() {
    return {
      notes: [],
    };
  },
  computed: {
    filteredNotes() {//filtreaza si returneaza lista de note pe baza unui criteriu de cautare
      return this.notes.filter(note => 
        note.content.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        (note.tag && note.tag.toLowerCase().includes(this.searchQuery.toLowerCase())) 
      );
    }
  },
  methods: {
    async fetchNotes() {// face o cerere catre bd pentru a obtine lista de note
      const q = query(collection(db, "notes"), orderBy("date", "desc"));
      const querySnapshot = await getDocs(q);
      this.notes = querySnapshot.docs.map((doc) => ({
        id: doc.id,
        ...doc.data(),
      }));
    },
  },
  mounted() {//initializeaza si configureaza functia fetch notes
    this.fetchNotes();
  },
};
</script>

<style scoped>
.notes-list ul {
  list-style-type: none; /* Elimină stilul implicit al marcatorilor de listă */
  padding: 0; /* Elimină spațiul interior al listei */
}

/* Stilurile pentru fiecare element din lista de note */
.notes-list li {
  background-color: #f1f1f1; /* Stabilește culoarea de fundal a fiecărui element din listă */
  border: 1px solid #ddd; /* Adaugă o bordură cu grosimea de 1px și culoarea gri deschis */
  border-radius: 4px; /* Rotunjește colțurile elementului */
  padding: 10px; /* Adaugă spațiu intern în fiecare element pentru a-l face mai vizual plăcut */
  margin-bottom: 10px; /* Adaugă un spațiu între fiecare element din listă */
}
</style>
