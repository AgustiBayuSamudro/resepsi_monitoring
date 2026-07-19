package com.agustibayusamudro.controllers;

import com.agustibayusamudro.dto.UndanganDTO;
import com.agustibayusamudro.services.UndanganService;
import com.agustibayusamudro.services.UserService;
import com.agustibayusamudro.services.impl.UndanganServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class UndanganController {

    private final UndanganService service;
    public UndanganController(UndanganService service) {
        this.service = service;
    }

    @FXML private TextField txtNama;
    @FXML private TextArea txtAlamat;
    @FXML private ComboBox<String> cbJenisKelamin;
    @FXML private TableView<UndanganDTO> tableView;
    @FXML private TableColumn<UndanganDTO, String> colKode, colNama, colAlamat, colJK;
    @FXML private TableColumn<UndanganDTO, Void> colAksi;

    private final ObservableList<UndanganDTO> listData = FXCollections.observableArrayList();
    private String kodeUndanganEdit = null;

    @FXML
    public void initialize() {
        cbJenisKelamin.getItems().addAll("Laki-laki", "Perempuan");
        
        // Setup Tabel
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeUndangan"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colJK.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
        

        colAksi.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button("Edit");
            private final Button btnHapus = new Button("Hapus");
            private final HBox pane = new HBox(5, btnEdit, btnHapus);

            {
                btnEdit.setOnAction(e -> isiForm(getTableView().getItems().get(getIndex())));
                btnHapus.setOnAction(e -> {
                    UndanganDTO data = getTableView().getItems().get(getIndex());
                    // Catatan: Pastikan service.hapusUndangan menggunakan ID yang benar
                    service.hapusUndangan(data.getKodeUndangan()); 
                    loadData();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
        
        loadData();
    }

    @FXML
    public void simpan() {
        UndanganDTO dto = new UndanganDTO(kodeUndanganEdit, txtNama.getText(), txtAlamat.getText(), cbJenisKelamin.getValue());
        if(kodeUndanganEdit == null) {
            service.tambahUndangan(dto);
        } else {
            service.updateUndangan(kodeUndanganEdit, dto);
        }
        riset();
        loadData();
    }

    @FXML
    public void riset() {
        txtNama.clear();
        txtAlamat.clear();
        cbJenisKelamin.setValue(null);
    }

    private void isiForm(UndanganDTO data) {
        kodeUndanganEdit = data.getKodeUndangan();
        txtNama.setText(data.getNama());
        txtAlamat.setText(data.getAlamat());
        cbJenisKelamin.setValue(data.getJenisKelamin());
    }

    private void loadData() {
        listData.setAll(service.ambilSemuaUndangan());
        tableView.setItems(listData);
    }
}