<template>
    <div class="profilcontainer">
        <img class="profil" src="../assets/profil.png">
        <div class="text" submit:prevent="edit">
            <label for="nume">Nume:</label>
            <input type="text" id="nume" v-model="lastName" required>
            <br><br>
            <label for="prenume">Prenume:</label>
            <input type="text" id="prenume" v-model="firstName" required>
            <br><br>
            <label for="email">Email:</label>
            <input type="text" id="email" v-model="email" required>
            <br>
            <br>
            <label for="nrtelefon">Nr de telefon:</label>
            <input type="nrtelefon" id="nrtelefon" v-model="contactNumber" required>
            <br><br>
            <button type="submit" class="login-button" v-on:click="edit">Finalizare</button>
        </div>

    </div>
</template>
<script>
import { fetchV1WithAuth } from "../lib/fetchers.js"
//import store from "../store.js"
export default {
    name: "EdiT",
    data() {
        return {
            contactNumber: "",
            email: "",
            firstName: "",
            lastName: "",

        }
    },
    methods: {
        async edit() {
            const res = await fetchV1WithAuth('users/me', { method: "PUT", body: JSON.stringify({ lastName: this.lastName, firstName: this.firstName, email: this.email, contactNumber: this.contactNumber }) })
            if (res.ok) {
                const data = res.json();
                console.log(data);
                this.$router.push("/profile")
            }
        }
    }
}
</script>