/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplaylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.io.PrintWriter;

/**
 *
 * @author Ana
 */
class MyQueue extends LinkedList {

    LinkedList<String> list = new LinkedList<String>();

    MyQueue(String f) throws FileNotFoundException { //constructor
        File input = new File(f);
        Scanner sc = new Scanner(input);
        while (sc.hasNext()) {
            list.add(sc.nextLine());
        }
    }
    
   
//convert array to String 
    public String[] GetStringArray() {
        String str[] = new String[list.size()];
        for (int j = 0; j < list.size(); j++) {
            str[j] = list.get(j);
        }
        return str;
    }
}


public class MusicPlaylist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("output.txt");
        PrintWriter writer = new PrintWriter(file);

        ArrayList<LinkedList> allTheWeeks = new ArrayList<>();

        String[] myFiles = {"input1.txt", "input2.txt", "input3.txt", "input4.txt"};
        String[] position = new String[800];
        String[] trackName = new String[800];
        String[] artist = new String[800];
        String[] streams = new String[800];
        String[] URL = new String[800];

        
        for (int i = 0; i < myFiles.length; i++) {
            MyQueue dataExtract = new MyQueue(myFiles[i]);
            allTheWeeks.add(dataExtract.list);
            String[] data = new String[5];
            String[] temp = dataExtract.GetStringArray();

            
            for (int j = 0; j < temp.length; j++) {
                String row = temp[j];
                data = row.split(",");
                position[j] = data[0];
                trackName[j] = data[1];
                artist[j] = data[2];
                streams[j] = data[3];
                URL[j] = data[4];
            }
            for (int h = 0; h < temp.length; h++) {
                for (int m = h + 1; m < temp.length; m++) {
                    if (trackName[h].replace("\"", "").compareToIgnoreCase(trackName[m].replace("\"", "")) > 0) {
                        String temp3 = trackName[h];
                        trackName[h] = trackName[m];
                        trackName[m] = temp3;

                        String temp0 = artist[h];
                        artist[h] = artist[m];
                        artist[m] = temp0;

                        String temp2 = position[h];
                        position[h] = position[m];
                        position[m] = temp2;

                        String temp4 = streams[h];
                        streams[h] = streams[m];
                        streams[m] = temp4;

                        String temp5 = URL[h];
                        URL[h] = URL[m];
                        URL[m] = temp5;
                    }
                }
            }
            for (int k = 0; k < temp.length; k++) {
                writer.println(position[k] + "  " + trackName[k] + "  " + artist[k] + "   " + streams[k] + "  " + URL[k]);
            }
        }
        // build a new playList
        PlayList songList = new PlayList();
        songList.addSong(trackName[2]);
        songList.addSong(trackName[3]);
        songList.addSong(trackName[4]);
        songList.addSong(trackName[5]);
        songList.addSong(trackName[6]);
        songList.addSong(trackName[7]);
        songList.addSong(trackName[8]);
        songList.addSong(trackName[9]);
        songList.addSong(trackName[0]);
        songList.addSong(trackName[1]);
        songList.addSong(trackName[2]);
        songList.addSong(trackName[5]);
        writer.println("\nThis is the new PlayList");
        for (int m = 0; m <= songList.songList.size(); m++) {
            writer.println(songList.songList.pop());
        }

        SongHistoryList history = new SongHistoryList();

        history.addSong(trackName[0]);
        history.addSong(trackName[2]);
        history.addSong(trackName[5]);
        history.addSong(trackName[55]);
        history.addSong(trackName[40]);
        history.addSong(trackName[39]);
        history.addSong(trackName[33]);
        history.addSong(trackName[29]);
        history.addSong(trackName[5]);
        String last = "0";
        for (int m = 0; m <= history.history.size(); m++) {
            last = history.history.pop();
            writer.println(last);
        }

        writer.println("last list :" + last);

        writer.close();
    }
}

class Song {

    private String track;
    private Song next;
    //constructor
    public Song(String track, Song next) {
        this.track = track;

    }
}

class PlayList {

    private Song first;
    LinkedList<String> songList;

    public PlayList() {
        songList = new LinkedList();
    }

    public void PlayList() {

    }

    public void addSong(String s) {
        songList.push(s);
    }

}

class SongHistoryList {

    private Song first;
    public Stack<String> history;

    public SongHistoryList() {
        history = new Stack();
    }

    public void SongHistoryList() {
        // constructor for creating a new list
    }

    public void addSong(String s) {
        history.push(s);
    }

    public String lastListened() {
        // retrieves the next song to listen to
        return (history.pop());

    }
}
