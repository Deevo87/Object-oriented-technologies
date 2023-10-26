package controller;


import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;
import util.PhotoDownloader;


public class GalleryController {

    @FXML
    private TextField imageNameField;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Photo> imagesListView;
    private Gallery galleryModel;

    @FXML
    public void initialize() {
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });
           imagesListView.getSelectionModel().selectedItemProperty().
                   addListener(((observable, oldValue, newValue) -> {
                       if (oldValue != null) {
                           imageNameField.textProperty().unbindBidirectional(oldValue.NameProperty());
                       }
                       bindSelectedPhoto(newValue);
           }));
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        this.imagesListView.getSelectionModel().select(0);
        this.imagesListView.setItems(gallery.getPhotos());
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        this.imageView.imageProperty().bind(selectedPhoto.ImageProperty());
        this.imageNameField.textProperty().bindBidirectional(selectedPhoto.NameProperty());
    }

    public void searchButtonClicked(ActionEvent event) {
        System.out.println("Search button is working!");
        PhotoDownloader photoDownloader = new PhotoDownloader();

        this.galleryModel.clear();
        System.out.println("halkol");
        if (!this.searchTextField.getText().equals("")) {
            photoDownloader.searchForPhotos(this.searchTextField.getText())
                    .subscribe(photo ->Platform.runLater(() -> galleryModel.addPhoto(photo)));
        }
    }
}

