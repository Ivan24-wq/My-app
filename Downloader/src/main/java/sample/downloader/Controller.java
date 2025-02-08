package sample.downloader;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.util.ResourceBundle;
public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OK;

    @FXML
    private Button instagram;

    @FXML
    private Button tiktok;

    @FXML
    private Button youtube;

    @FXML
    private TextField linkField;

    @FXML
    private HBox qualityButtonBox;
    // Выбор платформы
    private String selectedPlatform = null;

    @FXML
    void initialize() {
        // Обработчик кнопок соцсетей
        youtube.setOnAction(event -> selectPlatform("YouTube"));
        instagram.setOnAction(event -> selectPlatform("Instagram"));
        tiktok.setOnAction(event -> selectPlatform("Tik Tok"));

        //обработчик кнопки ОК

        OK.setOnAction(event -> processLink());

    }

    private void selectPlatform(String platform) {
        selectedPlatform = platform;
        System.out.println("Вы выбрали: " + selectedPlatform);
        showAlert("Выбранная платформа: ", "Вы выбрали: " + platform + ".");
    }

    private void processLink() {
        String videoLink = linkField.getText();

        if (selectedPlatform == null) {
            System.out.println("Сначала выберите соцсеть!");
            return;
        }
        if(videoLink.isEmpty()){
            showAlert("Ошибка!", "выберите платформу!");
            return;
        }
        System.out.println("Обработка ссылки: "+videoLink);
        showQualityOptions();
    }
    private void showQualityOptions(){
        //Очистка кпопок
        qualityButtonBox.getChildren().clear();
        qualityButtonBox.setVisible(true);

        String[] qualities = {"360", "720", "1080"};

        for(String quality : qualities){
            Button quailityButton = new Button(quality);
            quailityButton.setOnAction(events -> downloadVideo(quality));
            qualityButtonBox.getChildren().add(quailityButton);
        }
    }

    // Загрузка видео
    private void downloadVideo(String quality){
        String videoLink = linkField.getText();
        System.out.println("Скачивание: " + selectedPlatform + "Видео в качестве: " + quality);

        // Подключаем по Api
        String command = "yt-dlp-f" + quality + " " + videoLink;
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();
            showAlert("Загрузка", "Скачивание началось!");
        } catch (Exception e){
            e.printStackTrace();
            showAlert("Ошибка", "Не удалось скачать!");
        }
    }
    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}