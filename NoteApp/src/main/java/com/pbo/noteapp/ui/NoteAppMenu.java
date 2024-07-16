/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdulloh
 */
package com.pbo.noteapp.ui;

import com.pbo.noteapp.storage.DataStorage;
import com.pbo.noteapp.storage.DatabaseStorage;

import java.util.List;
import java.util.Scanner;

public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    showNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nNote App Menu oleh: Abdulloh Mun'am NIM: 23201292");
        System.out.println("1. Tambah Catatan");
        System.out.println("2. Tampilkan Catatan");
        System.out.println("3. Hapus Catatan");
        System.out.println("4. Keluar");
        System.out.print("Pilih opsi: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Harap masukkan angka yang valid: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void addNote() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan catatan: ");
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("Catatan disimpan: " + note);
    }

    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("Catatan tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("Tidak ada catatan.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }
public class NoteService {
    private DataStorage storage;
    private List<String> notes;

    public NoteService(DataStorage storage) {
        this.storage = storage;
        this.notes = readNotes();
    }

    public void createNote(String note) {
        storage.writeData(note);
        notes.add(note);
    }

    public List<String> readNotes() {
        return storage.readData();
    }

    public void deleteNote(String note) {
        notes.remove(note);
        storage.deleteData(note);
    }

    public int getNoteCount() {
        return notes.size();
    }

    public String getNoteByIndex(int index) {
        return notes.get(index);
    }
}

    private void deleteNote() {
        showNotes();
        if (noteService.getNoteCount() > 0) {
            System.out.print("Pilih nomor catatan yang akan dihapus: ");
            int noteIndex = getUserChoice() - 1;
            if (noteIndex >= 0 && noteIndex < noteService.getNoteCount()) {
                String note = noteService.getNoteByIndex(noteIndex);
                noteService.deleteNote(note);
                System.out.println("Catatan dihapus: " + note);
            } else {
                System.out.println("Nomor catatan tidak valid.");
            }
        }
    }
}
