<template>
  <div class="note-form">
    <input
      v-model="note"
      type="text"
      placeholder="Write the note..."
    />
    <input
      v-model="tag"
      type="text"
      placeholder="Add a tag"
    />
    <button @click="saveNote">Adauga notita</button> 
  </div>
</template>

<script>
import { db } from "../firebase-config";
import { collection, addDoc } from "firebase/firestore";

export default {
  data() {
    return {
      note: "",
      tag: "" 
    };
  },
  methods: {
    async saveNote() {
      if (this.note.trim() === "") {
        alert("Please, add a note.");
        return;
      }
      await addDoc(collection(db, "notes"), {
        content: this.note,
        tag: this.tag, 
        date: new Date().toISOString(),
      });
      this.note = "";
      this.tag = ""; 
      alert("The note has been saved.");
      this.$emit("note-saved");
    },
  },
};
</script>

<style scoped>
.note-form {
  display: flex;  /* Afiseaza elementele copil in modelul Flexbox */
  flex-direction: column;  /* Organizeaza elementele copil pe axa verticala */
  margin-bottom: 20px;  /* Adauga un spatiu de 20px dedesubtul formularului */
}

/* Stilurile pentru input-urile din interiorul formularului */
.note-form input {
  padding: 10px;  /* Adauga un spatiu intern de 10px in jurul input-ului */
  border: 1px solid #ddd;  /* Stilizeaza o bordura subtire de 1px cu culoarea #ddd */
  border-radius: 4px;  /* Rotunjeste colturile input-ului cu un raza de 4px */
  margin-bottom: 10px;  /* Adauga un spatiu de 10px dedesubtul input-ului */
}

/* Stilurile pentru butonul din interiorul formularului */
.note-form button {
  background-color: #4a90e2;  /* Stabileste culoarea de fundal a butonului */
  color: #fff;  /* Stabileste culoarea textului butonului la alb */
  border: none;  /* Elimina bordura butonului */
  padding: 10px;  /* Adauga un spatiu intern de 10px in jurul butonului */
  border-radius: 4px;  /* Rotunjeste colturile butonului cu un raza de 4px */
  cursor: pointer;  /* Schimba cursorul la forma unui pointer atunci cand este deasupra butonului */
  transition: background-color 0.3s;  /* Adauga o tranziție de 0.3s pentru schimbarea culorii de fundal */
}

/* Stilurile pentru butonul din interiorul formularului atunci când cursorul este deasupra */
.note-form button:hover {
  background-color: #357abd;  /* Schimba culoarea de fundal a butonului atunci cand cursorul este deasupra */
}

</style>
