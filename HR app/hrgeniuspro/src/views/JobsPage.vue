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

    <br>
    <br>
    <br>
    <br><br><br><br><br>
    <div class="container">
        <h1 style="text-align: center;">Joburi disponibile</h1>
        <br>
        <br>

        <a href="/jobupload">Adauga job</a>

        <div class="news-section">
            <div v-for="item in jobs" :key="item.jobId" class="news-item">
                <h2>{{ item.name }}</h2>
                <br>
                <p>{{ item.description }}</p>
                <button :disabled="item.applicants.includes(userId)" v-on:click="applyToJob(item.jobId)"
                    class="login-button">Aplicati acum</button>
            </div>
        </div>
    </div>
</template>
<script>
import { fetchV1WithAuth } from '@/lib/fetchers';

// export default{
//     name:"HomeView",
//     mounted()
//     {
//         if(!user)
//         {
//         let user= localStorage.getItem("user-info");
//             this.$router.push('/signup')
//         }
//     }
// }
import store from '../store'
export default {
    data() {
        return {
            jobs: [],
            userId: ''
        }
    },
    async mounted() {
        await this.mount()
    },
    methods: {
        async applyToJob(jobId) {
            const res = await fetchV1WithAuth(`jobs/apply/${jobId}`)
            if (res.ok) {
                await this.mount()
            }
        },
        async mount() {
            const res = await fetchV1WithAuth('jobs')
            const data = await res.json()

            try {
                this.jobs = data.data
                this.userId = store.getters.getUser.userId ? store.getters.getUser.userId : ''
            } catch (error) {
                this.userId = ''
            }
        }
    }
}
</script>