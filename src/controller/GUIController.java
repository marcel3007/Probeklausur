package controller;

import exception.NoFreeCapacityException;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Controller for the GUI
 * <p>
 * nicht testbar, da JAVA-FX Elemente enthalten
 *
 * @author Marcel Waldau
 */
public class GUIController {

//    @FXML
//    Label lblInfo, lblStorageLevelInPercent, lblFreeCapacity;
//    private StringBuilder msg = new StringBuilder();
//    private ListProperty<Hazard> listPropertyHazardsInStorage = new SimpleListProperty<>();
//    private ListProperty<Hazard> listPropertyHazardsNotInStorage = new SimpleListProperty<>();
//    private Storage storage;
//
//    @FXML
//    private TextField tfCustomerName;
//
//    @FXML
//    private CheckBox checkboxCargoHazardExplosive, checkboxCargoHazardFlammable, checkboxCargoHazardToxic, checkboxCargoHazardRadioactive, checkboxSolid, checkboxFragile, checkboxPressurized;
//
//    @FXML
//    private ComboBox<Customer> comboBox_addCargo_customers, comboBox_deleteCustomers;
//
//    @FXML
//    private ChoiceBox<Integer> choiceBoxCargoSize;
//
//    @FXML
//    private ChoiceBox<CargoType> choiceBoxCargoType_add, choiceBoxCargoType_select;
//
//    @FXML
//    private ListView<Hazard> lvHazardsInStorage, lvHazardsNotInStorage;
//
//    @FXML
//    private TableView<Map.Entry<Long, Customer>> tableViewCustomers;
//
//    @FXML
//    private TableColumn<Map.Entry<Long, Customer>, Number> customerIdColumn, customerAmountColumn;
//
//    @FXML
//    private TableColumn<Map.Entry<Long, Customer>, String> customerNameColumn;
//
//    @FXML
//    private TableView<StorageItem> tableViewStorageItems;
//
//    @FXML
//    private TableColumn<StorageItem, Number> posIdColumn, sizeColumn;
//
//    @FXML
//    private TableColumn<StorageItem, String> cargoColumn, hazardsColumn, ownerColumn, dateColumn;
//
//    @FXML
//    private ImageView imageViewTrashBin;
//
//    @FXML
//    private Button btnAddCargo, btnDeleteCustomer;
//    private SelectListenerFilterCargo selectListenerFilterCargo;
//
//    public GUIController() {
//        storage = new Storage(15);
//    }
//
//    /**
//     * Initialize the GUI
//     */
//    public void initialize() {
//
//        ObservableList<Integer> cargoSizeOptions = FXCollections.observableArrayList(CargoContainer.getAllAllowedSizes());
//        choiceBoxCargoSize.setValue(1);
//        choiceBoxCargoSize.setItems(cargoSizeOptions);
//
//        ObservableList<CargoType> cargoTypeOptions_add = FXCollections.observableArrayList(CargoType.getListWithoutCargo());
//        choiceBoxCargoType_add.setValue(CargoType.BOXED);
//        choiceBoxCargoType_add.setItems(cargoTypeOptions_add);
//
//        SelectListenerAddCargo selectListenerAddCargo = new SelectListenerAddCargo(checkboxFragile, checkboxSolid, checkboxPressurized);
//        choiceBoxCargoType_add.getSelectionModel().selectedItemProperty().addListener(selectListenerAddCargo);
//
//        checkboxFragile.disableProperty().setValue(false);
//        checkboxPressurized.disableProperty().setValue(true);
//        checkboxSolid.disableProperty().setValue(true);
//
//        ObservableList<CargoType> cargoTypeOptions_select = FXCollections.observableArrayList(CargoType.getList());
//        choiceBoxCargoType_select.setValue(CargoType.CARGO);
//        choiceBoxCargoType_select.setItems(cargoTypeOptions_select);
//
//        selectListenerFilterCargo = new SelectListenerFilterCargo(tableViewStorageItems, storage);
//        choiceBoxCargoType_select.getSelectionModel().selectedItemProperty().addListener(selectListenerFilterCargo);
//
//        lvHazardsInStorage.itemsProperty().bind(listPropertyHazardsInStorage);
//        lvHazardsNotInStorage.itemsProperty().bind(listPropertyHazardsNotInStorage);
//
//        tableViewCustomers.setItems(FXCollections.observableArrayList(storage.getCustomersMap().entrySet()));
//
//        customerIdColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getKey()));
//        customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getName()));
//        customerAmountColumn.setCellValueFactory(cellData -> {
//            try {
//                return new SimpleLongProperty(storage.getAmountOfCargoByCustomer(cellData.getValue().getValue()));
//            } catch (CustomerNotExistsException e) {
//                return new SimpleLongProperty(0);
//            }
//        });
//
//        updateTableStorageItems();
//
//        posIdColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getPosition()));
//        cargoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo().toString()));
//        sizeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCargo().getSize()));
//        hazardsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo().getHazards().toString()));
//        ownerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo().getOwner().getName()));
//        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault()))));
//
//
//        setupDragAndDrop();
//
//
//        btnAddCargo.disableProperty().bind(comboBox_addCargo_customers.valueProperty().isNull());
//        btnDeleteCustomer.disableProperty().bind(comboBox_deleteCustomers.valueProperty().isNull());
//
//
//        updateComboBoxCustomers();
//        updateHazards();
//        updateStorageCapacity();
//    }
//
//    /**
//     * adds a customer to the main.java.storage
//     */
//    public synchronized void addCustomer() {
//
//        try {
//            Customer customer = storage.addCustomer(tfCustomerName.getText());
//
//            updateTableCustomers();
//            updateComboBoxCustomers();
//
//            log("Added new customer " + customer.getName());
//        } catch (IllegalArgumentException e) {
//            log(e.toString());
//
//        }
//
//
//    }
//
//    /**
//     * deletes a customer from the main.java.storage
//     */
//    public synchronized void deleteCustomer() {
//
//        try {
//
//            Customer customer = comboBox_deleteCustomers.getSelectionModel().selectedItemProperty().get();
//
//            storage.removeCustomer(customer);
//
//            updateTableCustomers();
//            updateComboBoxCustomers();
//
//            log("Removed customer " + customer.toString());
//        } catch (CustomerNotExistsException | CustomerHasCargoException e) {
//            log(e.toString());
//        }
//
//    }
//
//    /**
//     * adds a cargo to the stprage
//     */
//    public synchronized void addCargo() {
//
//
//        try {
//            int size = choiceBoxCargoSize.getValue();
//
//            Customer owner = comboBox_addCargo_customers.getSelectionModel().selectedItemProperty().get();
//
//            boolean isFragile = checkboxFragile.isSelected();
//            boolean isSolid = checkboxSolid.isSelected();
//            boolean isPressurized = checkboxPressurized.isSelected();
//
//            List<Hazard> hazards = new LinkedList<>();
//            if (checkboxCargoHazardExplosive.isSelected())
//                hazards.add(Hazard.explosive);
//
//            if (checkboxCargoHazardFlammable.isSelected())
//                hazards.add(Hazard.flammable);
//
//            if (checkboxCargoHazardRadioactive.isSelected())
//                hazards.add(Hazard.radioactive);
//
//            if (checkboxCargoHazardToxic.isSelected())
//                hazards.add(Hazard.toxic);
//
//            CargoType cargoType = choiceBoxCargoType_add.getSelectionModel().selectedItemProperty().getValue();
//
//            storage.addCargo(CargoFactory.createCargo(cargoType, size, owner, hazards, isFragile, isSolid, isPressurized));
//
//            updateTableStorageItems();
//            updateHazards();
//            updateTableCustomers();
//            updateStorageCapacity();
//            resetCheckboxes();
//
//            log("New cargo added");
//
//        } catch (CustomerNotExistsException | CargoAlreadyExistsException | IllegalCargoSizeException | NoFreeCapacityException e) {
//            log(e.toString());
//        }
//
//
//    }
//
//    /**
//     * configures the drag and drop for switching table rows / positions of the cargos in the main.java.storage
//     * and for the drag and drop delete function
//     * <p>
//     * source: https://stackoverflow.com/a/36789180/6542041
//     */
//    private void setupDragAndDrop() {
//        tableViewStorageItems.setRowFactory(tableView -> {
//            final TableRow<StorageItem> row = new TableRow<>();
//
//            row.setOnDragDetected(event -> {
//                StorageItem selected = tableViewStorageItems.getSelectionModel().getSelectedItem();
//                if (selected != null) {
//
//                    Dragboard db = tableViewStorageItems.startDragAndDrop(TransferMode.ANY);
//                    ClipboardContent content = new ClipboardContent();
//                    content.putString(String.valueOf(selected.getPosition()));
//                    db.setContent(content);
//                    event.consume();
//                }
//            });
//
//            row.setOnDragOver(event -> {
//                if (event.getDragboard().hasString()) {
//                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }
//                event.consume();
//            });
//
//            row.setOnDragDropped(event -> {
//                Dragboard db = event.getDragboard();
//                if (db.hasString()) {
//                    long posFrom = Long.valueOf(db.getString());
//
//
//                    if (!row.isEmpty()) {
//                        long posTo = row.getItem().getPosition();
//                        storage.swapPosition(posFrom, posTo);
//
//                        tableViewStorageItems.refresh();
//                        posIdColumn.setSortType(TableColumn.SortType.ASCENDING);
//                        tableViewStorageItems.getSortOrder().add(posIdColumn);
//
//                        log("Swapped cargo position " + posFrom + " and " + posTo);
//
//                        event.setDropCompleted(true);
//                    }
//                }
//
//                event.consume();
//            });
//
//            return row;
//        });
//
//        imageViewTrashBin.setOnDragOver(event -> {
//            if (event.getDragboard().hasString()) {
//                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//            }
//            event.consume();
//        });
//
//        imageViewTrashBin.setOnDragDropped(event -> {
//            Dragboard db = event.getDragboard();
//            if (db.hasString()) {
//                long position = Long.valueOf(db.getString());
//
//
//                try {
//                    storage.removeCargo(storage.getCargoByPosition(position));
//
//                    updateTableStorageItems();
//                    updateTableCustomers();
//                    updateHazards();
//                    updateStorageCapacity();
//
//
//                    log("Removed cargo position " + position);
//
//                } catch (CargoNotExistsException e) {
//                    log(e.toString());
//                }
//
//
//                event.setDropCompleted(true);
//            }
//        });
//    }
//
//    /**
//     * logs a message on the console and in the info label
//     *
//     * @param msg teh message to be logged
//     */
//    private void log(String msg) {
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        this.msg.insert(0, System.lineSeparator());
//        this.msg.insert(0, localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ": " + msg);
//
//        lblInfo.setText(this.msg.toString());
//        System.out.println(msg);
//    }
//
//    /**
//     * resets all selected checkboxes
//     */
//    private void resetCheckboxes() {
//        checkboxCargoHazardExplosive.setSelected(false);
//        checkboxCargoHazardFlammable.setSelected(false);
//        checkboxCargoHazardRadioactive.setSelected(false);
//        checkboxCargoHazardToxic.setSelected(false);
//        checkboxSolid.setSelected(false);
//        checkboxFragile.setSelected(false);
//        checkboxPressurized.setSelected(false);
//    }
//
//    /**
//     * updates the customer table
//     */
//    private void updateTableCustomers() {
//        tableViewCustomers.setItems(FXCollections.observableArrayList(storage.getCustomersMap().entrySet()));
//        tableViewCustomers.refresh();
//    }
//
//    /**
//     * updates the main.java.storage items table
//     */
//    private void updateTableStorageItems() {
//        tableViewStorageItems.setItems(FXCollections.observableList(storage.getAllStorageItemsByType(selectListenerFilterCargo.getFilteredClass())));
//    }
//
//    /**
//     * updates the hazards lists
//     */
//    private void updateHazards() {
//        listPropertyHazardsInStorage.set(FXCollections.observableList(storage.getAllHazards()));
//        listPropertyHazardsNotInStorage.set(FXCollections.observableList(storage.getAllHazardsNotInStorage()));
//    }
//
//    /**
//     * updates the comboboxes for the customers
//     */
//    private void updateComboBoxCustomers() {
//        comboBox_addCargo_customers.setItems(FXCollections.observableArrayList(storage.getAllCustomersAsList()));
//        comboBox_addCargo_customers.getSelectionModel().selectFirst();
//
//        comboBox_deleteCustomers.setItems(FXCollections.observableArrayList(storage.getAllCustomersAsList()));
//        comboBox_deleteCustomers.getSelectionModel().selectFirst();
//    }
//
//    /**
//     * updates the main.java.storage capacity views
//     */
//    private void updateStorageCapacity() {
//        lblStorageLevelInPercent.setText(String.format("%d %%", storage.getStorageLevelInPercent()));
//        lblFreeCapacity.setText(String.format("%d", storage.getFreeCapacity()));
//    }


}
