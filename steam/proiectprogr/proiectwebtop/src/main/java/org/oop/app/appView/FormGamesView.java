package org.oop.app.appView;


import com.vaadin.flow.component.textfield.NumberField;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.oop.app.Games;
import org.oop.app.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.oop.app.Player_profile;
import org.oop.app.appView.NavigableGridGamesView;

import java.util.List;

@PageTitle("Games")
@Route(value = "Games", layout = MainView.class)
public class FormGamesView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;
    private EntityManager em;
    private Games game = null;
    private Binder<Games> binder = new Binder<>(Games.class);
    private VerticalLayout formLayoutToolbar;
    private H1 titluForm = new H1("Form Games");
    private IntegerField idgame = new IntegerField("ID game:");
    private TextField gamename = new TextField("Game name:");
    private NumberField price = new NumberField("Price:");
    private Button cmdAddGame = new Button("Add");
    private Button cmdDeleteGame = new Button("Delete");
    private Button cmdAbandon = new Button("Abandon");
    private Button cmdSaveGame = new Button("Save");

    public FormGamesView() {
        initDataModel();
        initViewLayout();
        initControllerActions();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer idgame) {
        System.out.println("Game ID: " + idgame);
        if (idgame != null) {
            this.game = em.find(Games.class, idgame);
            System.out.println("Selected game to edit:: " + game);
            if (this.game == null) {
                System.out.println("ADD game:: " + game);
                this.adaugaPlayer();
                this.game.setIdgame(idgame);

                this.game.setGamename("game NOU " + idgame);
                this.game.setPrice(Double.parseDouble("price: " + idgame));
            }
        }
        this.refreshForm();
    }

    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        this.em = emf.createEntityManager();
        List<Games> games = em
                .createQuery("SELECT g FROM Games g ORDER BY g.idgame", Games.class)
                .getResultList();
        if (games != null && !games.isEmpty()) {
            this.game = games.get(0);
        }
        binder.forField(idgame).bind("idgame");
        binder.forField(gamename).bind("gamename");
        binder.forField(price).bind("price");

        refreshForm();
    }

    private void initViewLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(idgame, gamename,price);
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
        formLayout.setMaxWidth("400px");
        HorizontalLayout actionToolbar =
                new HorizontalLayout(cmdAddGame, cmdDeleteGame, cmdAbandon, cmdSaveGame);

        this.add(titluForm, formLayout, actionToolbar);
    }

    private void initControllerActions() {
        cmdAddGame.addClickListener(e -> {
            adaugaPlayer();
            refreshForm();
        });
        cmdDeleteGame.addClickListener(e -> {
            stergePlayer();
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridGamesView.class)
            );
        });
        cmdAbandon.addClickListener(e -> {
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridGamesView.class)
            );
        });
        cmdSaveGame.addClickListener(e -> {
            salveazaPlayer();
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridGamesView.class)
            );
        });
    }

    private void refreshForm() {
        System.out.println("Game curent: " + this.game);
        if (this.game != null) {
            binder.setBean(this.game);
        }
    }

    private void adaugaPlayer() {
        this.game = new Games();
        this.game.setIdgame(222);
        this.game.setGamename("new game");
        this.game.setPrice(222);
    }

    private void stergePlayer() {
        System.out.println("To remove: " + this.game);
        if (this.em.contains(this.game)) {
            try {
                this.em.getTransaction().begin();
                this.em.remove(this.game);
                this.em.getTransaction().commit();
            } catch (Exception ex) {
                if (this.em.getTransaction().isActive())
                    this.em.getTransaction().rollback();
                System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    private void salveazaPlayer() {
        try {
            this.em.getTransaction().begin();
            this.game = this.em.merge(this.game);
            this.em.getTransaction().commit();
            System.out.println("Player Salvat");
        } catch (Exception ex) {
            if (this.em.getTransaction().isActive())
                this.em.getTransaction().rollback();
            System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
