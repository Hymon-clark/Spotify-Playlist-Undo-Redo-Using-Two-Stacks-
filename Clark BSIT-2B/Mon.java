/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Mon {

    // Main class to manage the playlist with Undo/Redo functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> playlist = new ArrayList<>();
        Stack<ArrayList<String>> undoStack = new Stack<>();
        Stack<ArrayList<String>> redoStack = new Stack<>();

        while (true) {
            // Display the menu
            System.out.println("\nSpotify Playlist Menu:");
            System.out.println("1. Add song");
            System.out.println("2. Remove last song");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. View playlist");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Add song
                    System.out.print("Enter song name: ");
                    String songToAdd = scanner.nextLine();
                    // Push the current playlist state to the Undo stack
                    undoStack.push(new ArrayList<>(playlist));
                    // Clear the Redo stack
                    redoStack.clear();
                    playlist.add(songToAdd);
                    System.out.println("Song added: " + songToAdd);
                    break;

                case 2:
                    // Remove last song
                    if (!playlist.isEmpty()) {
                        // Push the current playlist state to the Undo stack
                        undoStack.push(new ArrayList<>(playlist));
                        // Clear the Redo stack
                        redoStack.clear();
                        String removedSong = playlist.remove(playlist.size() - 1);
                        System.out.println("Removed song: " + removedSong);
                    } else {
                        System.out.println("No songs to remove.");
                    }
                    break;

                case 3:
                    // Undo
                    if (!undoStack.isEmpty()) {
                        // Push the current playlist state to Redo stack
                        redoStack.push(new ArrayList<>(playlist));
                        // Pop from Undo stack to restore previous state
                        playlist = undoStack.pop();
                        System.out.println("Undo successful. Restored previous state.");
                    } else {
                        System.out.println("No actions to undo.");
                    }
                    break;

                case 4:
                    // Redo
                    if (!redoStack.isEmpty()) {
                        // Push the current playlist state to Undo stack
                        undoStack.push(new ArrayList<>(playlist));
                        // Pop from Redo stack to restore that state
                        playlist = redoStack.pop();
                        System.out.println("Redo successful. Restored next state.");
                    } else {
                        System.out.println("No actions to redo.");
                    }
                    break;

                case 5:
                    // View playlist
                    if (playlist.isEmpty()) {
                        System.out.println("The playlist is empty.");
                    } else {
                        System.out.println("Current playlist:");
                        for (String song : playlist) {
                            System.out.println("- " + song);
                        }
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

