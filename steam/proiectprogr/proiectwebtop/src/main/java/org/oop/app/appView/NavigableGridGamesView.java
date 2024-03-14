package org.oop.app.appView;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.oop.app.Games;
import org.oop.app.MainView;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@PageTitle("game")
@Route(value = "game", layout = MainView.class)
public class NavigableGridGamesView extends VerticalLayout implements HasUrlParameter<Integer> {

    @Serial
    private static final long serialVersionUID = 1L;

    private EntityManager em;
    private final List<Games> gamesList = new ArrayList<>();
    private Games selectedgame = null;

    private final H1 titleForm = new H1("Lista games");
    private final TextField filterText = new TextField();
    private final Button cmdEditPlayer = new Button("Editeaza games...");
    private final Button cmdAddPlayer = new Button("Adauga games...");
    private final Button cmdDeletePlayer = new Button("Sterge games");
    private final Grid<Games> grid = new Grid<>(Games.class);

    public NavigableGridGamesView() {
        initDataModel();
        initViewLayout();
        initControllerActions();
    }
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
        if (id != null) {
            this.selectedgame = em.find(Games.class, id);
            System.out.println("Back game: " + selectedgame);
            if (this.selectedgame == null) {
                if (!this.gamesList.isEmpty())
                    this.selectedgame = this.gamesList.get(0);
            }
        }
        this.refreshForm();
    }

    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        em = emf.createEntityManager();

        List<Games> games = em.createQuery("SELECT g FROM Games g ORDER BY g.idgame", Games.class)
                .getResultList();
        this.gamesList.addAll(games);

        if (!games.isEmpty()) {
            this.gamesList.sort(Comparator.comparing(Games::getIdgame));
            this.selectedgame = gamesList.get(0);
            System.out.println("DEBUG: games init >>> " + selectedgame.getIdgame());
        }

        grid.setItems(this.gamesList);
        grid.asSingleSelect().setValue(this.selectedgame);
    }

    private void initViewLayout() {
        // Navigation layout -------------------------------------//
        // Navigation toolbar
        filterText.setPlaceholder("Filter by game ID...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
                cmdEditPlayer, cmdAddPlayer, cmdDeletePlayer);
        // Navigation grid
        Grid<Games> grid = new Grid<>(Games.class);
        grid.addColumn(Games::getIdgame).setHeader("ID Player");
        grid.addColumn(Games::getGamename).setHeader("Gamename");
        grid.addColumn(Games::getPrice).setHeader("Price");
        grid.addComponentColumn(this::createGridActionsButtons).setHeader("Actions");
        // Initialize navigation layout
        this.add(titleForm, gridToolbar, grid);
    }

    private void initControllerActions() {
        // Navigation Actions
        filterText.addValueChangeListener(e -> updateList());
        cmdEditPlayer.addClickListener(e -> editPlayer());
        cmdAddPlayer.addClickListener(e -> addPlayer());
        cmdDeletePlayer.addClickListener(e -> {
            deletePlayer();
            refreshForm();
        });
    }

    private Component createGridActionsButtons(Games item) {
        //
        Button cmdEditItem = new Button("Edit");
        cmdEditItem.addClickListener(e -> {
            grid.asSingleSelect().setValue(item);
            editPlayer();
        });
        Button cmdDeleteItem = new Button("Delete");
        cmdDeleteItem.addClickListener(e -> {
            System.out.println("Delete item: " + item);
            grid.asSingleSelect().setValue(item);
            deletePlayer();
            refreshForm();
        });
        //
        return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
    }

    private void editPlayer() {
        this.selectedgame = this.grid.asSingleSelect().getValue();
        System.out.println("Selected game: " + selectedgame);
        if (this.selectedgame != null) UI.getCurrent().navigate(FormGamesView.class);
    }

    private void updateList() {
        try {
            List<Games> filteredgames;

            if (filterText.getValue() != null) {
                String filter = filterText.getValue().toLowerCase();
                filteredgames = this.gamesList.stream()
                        .filter(g -> String.valueOf(g.getIdgame()).contains(filter))
                        .toList();

                grid.setItems(filteredgames);
            } else {
                grid.setItems(this.gamesList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshForm() {
        System.out.println("Current pacient: " + this.selectedgame);
        if (this.selectedgame != null) {
            grid.setItems(this.gamesList);
            grid.asSingleSelect().setValue(this.selectedgame);
        }
    }

    private void addPlayer() {
        this.getUI().ifPresent(ui -> ui.navigate(FormGamesView.class));
    }

    private void deletePlayer() {
        this.selectedgame = this.grid.asSingleSelect().getValue();
        System.out.println("To remove: " + this.selectedgame);

        if (this.em.contains(this.selectedgame)) {
            this.em.getTransaction().begin();
            this.em.remove(this.selectedgame);
            this.em.getTransaction().commit();
        }

        this.gamesList.remove(this.selectedgame);
        this.grid.asSingleSelect().clear();

        if (!this.gamesList.isEmpty()) {
            this.selectedgame = this.gamesList.get(0);
            this.grid.asSingleSelect().setValue(this.selectedgame);
        } else {
            this.selectedgame = null;
        }
    }
}
