package org.oop.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // EntityManager initialization
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        EntityManager em = emf.createEntityManager();

        List<Player_profile> players=new ArrayList<>();
        List<Games> gamesList=new ArrayList<>();
        List<Library> libraryList=new ArrayList<>();
        List<Market> markets=new ArrayList<>();
        List<Reviews>reviewsList=new ArrayList<>();
        List<Sales> salesList=new ArrayList<>();
        List<Skins>skinsList=new ArrayList<>();

        List<Player_profile> lstplayerPersistenti= em.createQuery("SELECT pp FROM Player_profile pp", Player_profile.class).getResultList();
        if(!lstplayerPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Player_profile pp:lstplayerPersistenti)
                em.remove(pp);
            em.getTransaction().commit();
        }
        Player_profile profile1=new Player_profile(1,"Juruburu");
        Player_profile profile2=new Player_profile(2,"Blrblrblr");
        Player_profile profile3=new Player_profile(3,"Bebino");
        Player_profile profile4=new Player_profile(4,"Progaming69");
        Player_profile profile5=new Player_profile(5,"Palash");

        em.getTransaction().begin();
        em.persist(profile1);
        em.persist(profile2);
        em.persist(profile3);
        em.persist(profile4);
        em.persist(profile5);
        em.getTransaction().commit();
        List<Player_profile> lstprofile=em.createQuery("SELECT pp FROM Player_profile pp", Player_profile.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Player_profile pp:lstprofile)
            System.out.println("Id: "+pp.getIdplayer()+"Username: "+pp.getUsername());
        List<Player_personal_data> lstplayerdataPersistenti= em.createQuery("SELECT ppp FROM Player_personal_data ppp", Player_personal_data.class).getResultList();
        if(!lstplayerdataPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Player_personal_data ppp:lstplayerdataPersistenti)
                em.remove(ppp);
            em.getTransaction().commit();
        }
        Player_personal_data ppp1=new Player_personal_data(1,"Juruburu","Alexandru Buruiana","aburuiana25@gmail.com","0733728156");
        Player_personal_data ppp2=new Player_personal_data(2,"Blrblrblr","Razvan Pletosu","pletosu@gmail.com","0733122122");
        Player_personal_data ppp3=new Player_personal_data(3,"Bebino","Stefan Bebino","bebino@gmail.com","0723312345");
        Player_personal_data ppp4=new Player_personal_data(4,"Progaming69","Tudor Pletosu","tudorp@gmail.com","0745221322");
        Player_personal_data ppp5=new Player_personal_data(5,"Palash","Dan Rares","palash@gmail.com","0743222544");

        em.getTransaction().begin();
        em.persist(ppp1);
        em.persist(ppp2);
        em.persist(ppp3);
        em.persist(ppp4);
        em.persist(ppp5);
        em.getTransaction().commit();
        List<Player_personal_data> lstppp=em.createQuery("SELECT ppp FROM Player_personal_data ppp", Player_personal_data.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Player_personal_data ppp:lstppp)
            System.out.println("Name: "+ppp.getName()+"Email: "+ppp.getEmail()+"Phone number: "+ppp.getPhone_number());
        List<Categories> lstcatPersistenti= em.createQuery("SELECT c FROM Categories c", Categories.class).getResultList();
        if(!lstcatPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Categories c:lstcatPersistenti)
                em.remove(c);
            em.getTransaction().commit();
        }
        Categories cat1=new Categories(1,"Sports");
        Categories cat2=new Categories(2,"Shooters");
        Categories cat3=new Categories(3,"Strategy");

        em.getTransaction().begin();
        em.persist(cat1);
        em.persist(cat2);
        em.persist(cat3);
        em.getTransaction().commit();
        List<Categories> lstcat=em.createQuery("SELECT c FROM Categories c", Categories.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Categories c:lstcat)
            System.out.println("Id: "+c.getIdcategory()+"Name "+c.getCategoryname());
        List<Library> lstlibPersistenti= em.createQuery("SELECT l FROM Library l", Library.class).getResultList();
        if(!lstlibPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Library l:lstlibPersistenti)
                em.remove(l);
            em.getTransaction().commit();
        }
        Library lib1=new Library(1,profile1);
        Library lib2=new Library(2,profile2);
        Library lib3=new Library(3,profile3);
        Library lib4=new Library(4,profile4);
        Library lib5=new Library(5,profile5);

        em.getTransaction().begin();
        em.persist(lib1);
        em.persist(lib2);
        em.persist(lib3);
        em.persist(lib4);
        em.persist(lib5);
        //  em.merge(g1);
        //    em.merge(g2);
        //    em.merge(g5);
        em.getTransaction().commit();
        List<Library> lstlib=em.createQuery("SELECT l FROM Library l", Library.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Library l:lstlib)
            System.out.println("Id: "+l.getIdlib());
        List<Games> lstgamesPersistenti= em.createQuery("SELECT g FROM Games g", Games.class).getResultList();
        if(!lstgamesPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Games g:lstgamesPersistenti)
                em.remove(g);
            em.getTransaction().commit();
        }
        Games g1=new Games(1,"FC24",299.00,cat1);
        Games g2=new Games(2,"CS2",0.00,cat2);
        Games g3=new Games(3,"Warzone",0.00,cat2);
        Games g4=new Games(4,"World of Tanks",20.00,cat3);
        Games g5=new Games(5,"NBA 2k24",369.00,cat1);

        em.getTransaction().begin();
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);
        em.persist(g4);
        em.persist(g5);
        em.getTransaction().commit();
        //creez mai multe liste random cu jocuri pentru a le putea adauga in librarii

        gamesList.add(g1);
        gamesList.add(g2);
        gamesList.add(g5);
        List<Games> gamesList1=new ArrayList<>();
        gamesList1.add(g2);
        gamesList1.add(g3);
        List<Games> gamesList2=new ArrayList<>();
        gamesList2.add(g1);
        gamesList2.add(g4);
        gamesList2.add(g5);
        gamesList2.add(g2);
        List<Games> gamesList3=new ArrayList<>();
        gamesList3.add(g1);
        gamesList3.add(g2);
        gamesList2.add(g5);
        gamesList2.add(g2);
        gamesList3.add(g3);
        gamesList3.add(g4);
        gamesList3.add(g5);
        List<Games> lstgames=em.createQuery("SELECT g FROM Games g", Games.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Games g:lstgames)
            System.out.println("Id: "+g.getIdgame()+"Game name: "+g.getGamename());



        List<Game_specs> lstgsPersistenti= em.createQuery("SELECT gs FROM Game_specs gs", Game_specs.class).getResultList();
        if(!lstgsPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Game_specs gs:lstgsPersistenti)
                em.remove(gs);
            em.getTransaction().commit();
        }
        Game_specs gs1= new Game_specs(1,"FC24",299.00,50,2023);
        Game_specs gs2= new Game_specs(2,"CS2",0.00,30,2023);
        Game_specs gs3= new Game_specs(3,"Warzone",299.00,120,2021);
        Game_specs gs4= new Game_specs(4,"World of Tanks",20.00,5,2015);
        Game_specs gs5= new Game_specs(5,"NBA 2k24",369.00,50,2023);

        em.getTransaction().begin();
        em.persist(gs1);
        em.persist(gs2);
        em.persist(gs3);
        em.persist(gs4);
        em.persist(gs5);
        em.getTransaction().commit();
        List<Game_specs> lstgs=em.createQuery("SELECT gs FROM Game_specs gs", Game_specs.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Game_specs gs:lstgs)
            System.out.println("Space req: "+gs.getSpace_required()+"Release date"+gs.getReleasedate());
        List<Sales> lstsalesPersistenti= em.createQuery("SELECT s FROM Sales s", Sales.class).getResultList();
        if(!lstsalesPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Sales s:lstsalesPersistenti)
                em.remove(s);
            em.getTransaction().commit();
        }
        Sales s1=new Sales(1,100000000,g1);
        Sales s2=new Sales(2,50000000,g2);
        Sales s3=new Sales(3,100000000,g3);
        Sales s4=new Sales(4,20000000,g4);
        Sales s5=new Sales(5,5000000,g5);

        em.getTransaction().begin();
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(s4);
        em.persist(s5);
        em.getTransaction().commit();
        List<Sales> lstsales=em.createQuery("SELECT s FROM Sales s", Sales.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Sales s:lstsales)
            System.out.println("Id: "+s.getIdsale()+"Number of sales: "+s.getNumberofsales());
        List<Reviews> lstreviewsPersistenti= em.createQuery("SELECT r FROM Reviews r", Reviews.class).getResultList();
        if(!lstreviewsPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Reviews r:lstreviewsPersistenti)
                em.remove(r);
            em.getTransaction().commit();
        }
        Reviews r1=new Reviews(1, "un joc foarte bun", g1,profile1);
        Reviews r2=new Reviews(2, "nu-mi place", g1,profile2);
        Reviews r3=new Reviews(3, "un joc foarte bun", g2,profile2);
        Reviews r4=new Reviews(4,"Excelent jocul",g3,profile2);

        em.getTransaction().begin();
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.getTransaction().commit();
        List<Reviews> lstreviews=em.createQuery("SELECT r FROM Reviews r", Reviews.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Reviews r:lstreviews)
            System.out.println("Id: "+r.getIdreview()+"Descriere: " +r.getReview());
        List<Skins> lstskinsPersistenti= em.createQuery("SELECT sk FROM Skins sk", Skins.class).getResultList();
        if(!lstskinsPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Skins sk:lstskinsPersistenti)
                em.remove(sk);
            em.getTransaction().commit();
        }
        Skins sk1=new Skins(1,"AWP | Dragon Lore", "Red",1000.00,g3);
        Skins sk2=new Skins(2,"AWP | Gungnir", "Red",3000.00,g3);
        Skins sk3=new Skins(3,"Karambit |  Lore", "Gold",900.00,g3);
        Skins sk4=new Skins(4,"Ak-47 | Redline", "Purple",15.00,g3);

        em.getTransaction().begin();
        em.persist(sk1);
        em.persist(sk2);
        em.persist(sk3);
        em.persist(sk4);
        em.getTransaction().commit();
        List<Skins> lstskins=em.createQuery("SELECT sk FROM Skins sk", Skins.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Skins sk:lstskins)
            System.out.println("Id: "+sk.getIdskin()+ "Name: "+sk.getSkinname());
        List<Market> lstmarketPersistenti= em.createQuery("SELECT m FROM Market m", Market.class).getResultList();
        if(!lstmarketPersistenti.isEmpty()){
            em.getTransaction().begin();
            for(Market m:lstmarketPersistenti)
                em.remove(m);
            em.getTransaction().commit();
        }

        Market m1=new Market(1,"Ak-47 | Redline",14.50);
        Market m2=new Market(2,"Awp | Gungnir",2999.00);
        em.getTransaction().begin();
        em.persist(m1);
        em.persist(m2);
        em.getTransaction().commit();
        List<Market> lstmarket=em.createQuery("SELECT m FROM Market m", Market.class).getResultList();
        System.out.println("Lista angajatilor persistenti: ");
        for(Market m:lstmarket)
            System.out.println("Id: "+m.getId());
    }
}