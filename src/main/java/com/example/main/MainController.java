package com.example.main;


import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import database.Database;
import disk.Disk;
import disk.DiskUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import music.Track;
import records.Records;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainController {
    @FXML
    private ImageView create, duration, sort, file, exit, add, delete, from, to, find, commit;

    @FXML
    private AnchorPane diskMenu, durMenu, sortMenu, fileMenu, exitMenu;
    @FXML
    private Label label, labelSort, labelSort1;
    @FXML
    private JFXListView listAll, listDisk, listDiskDur, listSort;
    @FXML
    private JFXTextArea fromText, toText, genre;

    @FXML
    private void handleButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException, InterruptedException {

        if(event.getTarget() == create) {
            if(!diskMenu.isVisible()) {
                List<Track> tracklist = Records.getTracklist();
                listAll.getItems().clear();
                listAll.setVisible(true);
                listDisk.setVisible(false);
                label.setText("Disk manager");
                int i = 1;
                for (Track track:tracklist) {
                    String st ="Id:"+ i++ +" , "+ track.toString();
                    listAll.getItems().add(st);
                }
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(true);
            } else {
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
            }
        }
        if(event.getTarget() == duration) {
            if(!durMenu.isVisible()) {
                fromText.setVisible(true);
                toText.setVisible(true);
                listDiskDur.setVisible(false);
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(true);
                diskMenu.setVisible(false);
            } else {
                fromText.setVisible(false);
                toText.setVisible(false);
                listDiskDur.setVisible(false);
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
            }
        }
        if(event.getTarget() == sort) {
            if(!sortMenu.isVisible()) {
                labelSort.setVisible(true);
                labelSort1.setVisible(true);
                listSort.setVisible(false);
                sortMenu.setVisible(true);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
            } else {
                labelSort.setVisible(false);
                labelSort1.setVisible(false);
                listSort.setVisible(false);
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
            }
        }
        if(event.getTarget() == file) {
            if(!fileMenu.isVisible()) {
                sortMenu.setVisible(false);
                fileMenu.setVisible(true);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
                Database.Database();
                Database.AddDB(Disk.getTracklist());
                Database.CloseDB();
            } else {
                sortMenu.setVisible(false);
                fileMenu.setVisible(false);
                durMenu.setVisible(false);
                diskMenu.setVisible(false);
            }
        }
        if(event.getTarget() == exit) {
            TimeUnit.SECONDS.sleep(1);
            System.exit(0);
        }
    }

    @FXML
    void handleDiskMenuAction(MouseEvent event) {
        if(event.getTarget().toString().startsWith("JFXListCell")) {
            String st = event.getPickResult().toString().substring(event.getPickResult().toString().indexOf("'") + 1, event.getPickResult().toString().lastIndexOf("'"));
            st = st.substring(st.indexOf(":") + 1, st.indexOf(",") - 1);
            System.out.println(st);
            if(listAll.isVisible()) {
                Disk.addRecordsOnDisk(Records.getTrack(Integer.parseInt(st) - 1));
            }
            if(listDisk.isVisible()) {
                label.setText("Disk manager");
                Disk.deleteRecordsFromDisk(Disk.getTrackOnDisk(Integer.parseInt(st) - 1));
                listDisk.getItems().clear();
                List<Track> tracklist = Disk.getTracklist();
                int i = 1;
                for (Track track:tracklist) {
                    String str ="Id:"+ i++ +" , "+ track.toString();
                    listDisk.getItems().add(str);
                }

            }
        }
        if(event.getTarget() == delete){
            if(Disk.getTracklist().isEmpty()){
                label.setText("Disk is empty");
                listAll.setVisible(false);
                listDisk.setVisible(true);
            } else {
                label.setText("Disk manager");
                listDisk.getItems().clear();
                List<Track> tracklist = Disk.getTracklist();
                int i = 1;
                for (Track track:tracklist) {
                    String st ="Id:"+ i++ +" , "+ track.toString();
                    listDisk.getItems().add(st);
                }
                listAll.setVisible(false);
                listDisk.setVisible(true);
            }
        }
        if(event.getTarget() == add){
            label.setText("Disk manager");
            listAll.setVisible(true);
            listDisk.setVisible(false);
        }
    }
    @FXML
    void handleDurMenuAction(MouseEvent event) {
        if (event.getTarget() == from){
            String text = fromText.getText().replaceAll("\n", System.getProperty("line.separator"));
            String min = text.substring(0,text.indexOf(":"));
            String sec = text.substring(text.indexOf(":")+1);
            Duration durationFrom;
            durationFrom = Duration.ofSeconds(Integer.parseInt(min)*60+Integer.parseInt(sec));
            Disk.setDurationFrom(durationFrom);
        }
        if (event.getTarget() == to){
            String text = toText.getText().replaceAll("\n", System.getProperty("line.separator"));
            String min = text.substring(0,text.indexOf(":"));
            String sec = text.substring(text.indexOf(":")+1);
            Duration durationTo;
            durationTo = Duration.ofSeconds(Integer.parseInt(min)*60+Integer.parseInt(sec));
            Disk.setDurationTo(durationTo);
        }
        if (event.getTarget() == find){
            listDiskDur.setVisible(true);
            fromText.setVisible(false);
            toText.setVisible(false);
            listDiskDur.getItems().clear();
            List<Track> tracklist = DiskUtils.findRecordsByDuration();
            int i = 1;
            for (Track track : tracklist) {
                String st = "Id:" + i++ + " , " + track.toString();
                listDiskDur.getItems().add(st);
            }
        }
    }
    @FXML
    void handleSortMenuAction(MouseEvent event) {
        if (event.getTarget() == commit){
            String text = genre.getText().replaceAll("\n", System.getProperty("line.separator"));
            labelSort.setVisible(false);
            labelSort1.setVisible(false);
            listSort.setVisible(true);
            listSort.getItems().clear();
            List<Track> tracklist = DiskUtils.sortRecordsByGenre(text);
            int i = 1;
            for (Track track : tracklist) {
                String st = "Id:" + i++ + " , " + track.toString();
                listSort.getItems().add(st);
            }
        }
    }
}