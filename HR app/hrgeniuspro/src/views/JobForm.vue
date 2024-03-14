<template>
    <form class="application-form" @submit.prevent="JobsUpload">

        <br>
        <br>
        <label for="name">Name:</label>

        <input type="text" id="name" v-model="name" required>
        <br>
        <br>
        <label for="description">description</label>
        <input type="text" id="description" v-model="description" required>

        <button type="submit">Submit Application</button>
    </form>
</template>
<script>
import {  fetchV1WithAuth } from '@/lib/fetchers';
export default {
    name :'JobUpload',
    data() {
        return {
            name: ' ',
            description: ' '
        }
    },
    methods: {
        async JobsUpload() {
            const res = await fetchV1WithAuth('jobs/new', { method: 'POST', body: JSON.stringify({ name: this.name, description: this.description }) })
            if (res.ok) {
                this.$router.push('/jobs')

            }
        }
    }
}
</script>