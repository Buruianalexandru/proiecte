x

const firebaseConfig = {
    apiKey: "AIzaSyD_2CO0NU9ON0kkmJOo80umZ5wzqjgeKSY",
    authDomain: "notes-31fd2.firebaseapp.com",
    projectId: "notes-31fd2",
    storageBucket: "notes-31fd2.appspot.com",
    messagingSenderId: "155542877863",
    appId: "1:155542877863:web:8ac0a66f7dc13d8ea94d82"
};

const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

export { db };
