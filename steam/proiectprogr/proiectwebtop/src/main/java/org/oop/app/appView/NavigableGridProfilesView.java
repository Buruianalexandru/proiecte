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
import org.oop.app.Player_profile;
import org.oop.app.MainView;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@PageTitle("profile")
@Route(value = "profile", layout = MainView.class)
public class NavigableGridProfilesView extends VerticalLayout implements HasUrlParameter<Integer> {

    @Serial
    private static final long serialVersionUID = 1L;

    private EntityManager em;
    private final List<Player_profile> profileList = new ArrayList<>();
    private Player_profile selectedProfile = null;

    private final H1 titleForm = new H1("Lista players");
    private final TextField filterText = new TextField();
    private final Button cmdEditPlayer = new Button("Editeaza player...");
    private final Button cmdAddPlayer = new Button("Adauga player...");
    private final Button cmdDeletePlayer = new Button("Sterge player");
    private final Grid<Player_profile> grid = new Grid<>(Player_profile.class);

    public NavigableGridProfilesView() {
        initDataModel();
        initViewLayout();
        initControllerActions();
    }
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
        if (id != null) {
            this.selectedProfile = em.find(Player_profile.class, id);
            System.out.println("Back game: " + selectedProfile);
            if (this.selectedProfile == null) {
                if (!this.profileList.isEmpty())
                    this.selectedProfile = this.profileList.get(0);
            }
        }
        this.refreshForm();
    }

    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        em = emf.createEntityManager();

        List<Player_profile> profiles = em.createQuery("SELECT pp FROM Player_profile pp ORDER BY pp.idplayer", Player_profile.class)
                .getResultList();
        this.profileList.addAll(profiles);

        if (!profiles.isEmpty()) {
            this.profileList.sort(Comparator.comparing(Player_profile::getIdplayer));
            this.selectedProfile = profileList.get(0);
            System.out.println("DEBUG: profiles init >>> " + selectedProfile.getIdplayer());
        }

        grid.setItems(this.profileList);
        grid.asSingleSelect().setValue(this.selectedProfile);
    }

    private void initViewLayout() {
        // Navigation layout -------------------------------------//
        // Navigation toolbar
        filterText.setPlaceholder("Filter by profile ID...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
                cmdEditPlayer, cmdAddPlayer, cmdDeletePlayer);
        // Navigation grid
        Grid<Player_profile> grid = new Grid<>(Player_profile.class);
        grid.addColumn(Player_profile::getIdplayer).setHeader("ID Player");
        grid.addColumn(Player_profile::getUsername).setHeader("Username");
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

    private Component createGridActionsButtons(Player_profile item) {
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
        this.selectedProfile = this.grid.asSingleSelect().getValue();
        System.out.println("Selected player: " + selectedProfile);
        if (this.selectedProfile != null) UI.getCurrent().navigate(FormProfilesView.class);
    }

    private void updateList() {
        try {
            List<Player_profile> filteredPlayers;

            if (filterText.getValue() != null) {
                String filter = filterText.getValue().toLowerCase();
                filteredPlayers = this.profileList.stream()
                        .filter(pp -> String.valueOf(pp.getIdplayer()).contains(filter))
                        .toList();

                grid.setItems(filteredPlayers);
            } else {
                grid.setItems(this.profileList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshForm() {
        System.out.println("Current profiles: " + this.selectedProfile);
        if (this.selectedProfile != null) {
            grid.setItems(this.profileList);
            grid.asSingleSelect().setValue(this.selectedProfile);
        }
    }

    private void addPlayer() {
        this.getUI().ifPresent(ui -> ui.navigate(FormProfilesView.class));
    }

    private void deletePlayer() {
        this.selectedProfile = this.grid.asSingleSelect().getValue();
        System.out.println("To remove: " + this.selectedProfile);

        if (this.em.contains(this.selectedProfile)) {
            this.em.getTransaction().begin();
            this.em.remove(this.selectedProfile);
            this.em.getTransaction().commit();
        }

        this.profileList.remove(this.selectedProfile);
        this.grid.asSingleSelect().clear();

        if (!this.profileList.isEmpty()) {
            this.selectedProfile = this.profileList.get(0);
            this.grid.asSingleSelect().setValue(this.selectedProfile);
        } else {
            this.selectedProfile = null;
        }
    }
}
