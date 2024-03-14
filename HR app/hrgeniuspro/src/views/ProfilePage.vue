<template>
    <header>
        <div class="navigatie">

            <img class="logonav" src="/logo1.png">

            <router-link to="/dashboard">Dashboard</router-link>
            <router-link to='/profile'>Profil</router-link>
            <router-link to='/cerere'>Cerere</router-link>
            <router-link to="/jobs">Jobs</router-link>
            <router-link to="/contact">Contact us</router-link>
            <router-link to='/intrb'>Intrebari Frecvente</router-link>
            <router-link to="/org">Organigrama</router-link>
        </div>
    </header>

    <div class="profilcontainer">
        <img class="profil" src="../assets/profil.png">
        <div class="text">
            <h3>Nume: {{ nume }} </h3>
            <h3>Prenume: {{ prenume }}</h3>
            <h3>Email: {{ email }}</h3>
            <h3>Rol: {{ rol }}</h3>
            <h3>Nr de telefon: {{ nrtel }}</h3>
            <router-link to="/edit"><button class="login-button">Editati</button></router-link>
            <router-link to='/upload-personal-files'><button class="login-button">Incarcati cv-ul</button></router-link>

        </div>
    </div>
    <div>
        <div v-if="files.length !== 0" class="profilcontainer">
            <li v-for="item in files" :key="item.fileId">
                <div>{{ item.files }}</div>

                <button v-on:click="downloadFile(item.fileId)">Descarca fisierul</button>
            </li>
        </div>
    </div>
</template>
  
<script>
import { fetchV1WithAuth } from "../lib/fetchers.js"

export default {
    data() {
        return {
            prenume: '',
            nume: '',
            email: '',
            rol: '',
            nrtel: '',
            files: []
        }
    },
    methods: {
        async fetchUserData() {
            try {
                const res = await fetchV1WithAuth('users/me', { method: 'GET' });
                const data = await res.json();
                this.prenume = data.data.firstName;
                this.nume = data.data.lastName;
                this.email = data.data.email;
                this.rol = data.data.role;
                this.nrtel = data.data.contactNumber;
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        },
        async fetchDocumentsData() {
            const res = await fetchV1WithAuth('files/me', { method: 'GET' })
            const data = await res.json();
            console.log(data.data)
            this.files = data.data;
            console.log(data.data);
        },
        async downloadFile(fileId) {
            const res = await fetchV1WithAuth(`files/personal/${fileId}`)
            const data = await res.json()

            var a = document.createElement("a"); //Create <a>
            a.href = data.data.fileBase64Url //Image Base64 Goes here
            a.download = "CV.pdf"; //File name Here
            a.click();
            }
    },
    mounted() {
        this.fetchUserData();
        this.fetchDocumentsData();
    }
}
</script>
