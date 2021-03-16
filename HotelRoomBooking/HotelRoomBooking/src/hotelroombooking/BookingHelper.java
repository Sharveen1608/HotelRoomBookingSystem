/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelroombooking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnuragPC
 */
public class BookingHelper {

    String fileName = "bookings.txt";

    public int AddBooking(BookingEntity bookingEntity) {
        Boolean isBookingAdded = false;
        int bookingIndex=GetLastIndex() + 1;
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(bookingIndex + "::" + bookingEntity.getBookingDate() + "::" + bookingEntity.getRoom() + "::" + bookingEntity.getFloor() + "::" + bookingEntity.getNumberOfDays() + "::" + bookingEntity.getCustomerName() + "::" + bookingEntity.getPassport() + "::" + bookingEntity.getEmail() + "::" + bookingEntity.getContactNo() + "\r\n" + System.getProperty("line.separator"));
            fw.close();
            isBookingAdded = true;
        } catch (IOException ex) {
            isBookingAdded = false;
        } catch (Exception ex) {
            isBookingAdded = false;
        }
        return bookingIndex;
    }

    public List<BookingEntity> GetBookings() throws IOException {
        List<BookingEntity> lstBookings = new ArrayList<>();
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String productData : allLines) {
            if (!productData.equals("")) {
                String[] arrOfStr = productData.split("::");
                BookingEntity bookingEntity = new BookingEntity();
                bookingEntity.setBookingID(Integer.parseInt(arrOfStr[0]));
                bookingEntity.setBookingDate(arrOfStr[1]);
                bookingEntity.setRoom(arrOfStr[2]);
                bookingEntity.setFloor(arrOfStr[3]);
                bookingEntity.setNumberOfDays(Integer.parseInt(arrOfStr[4]));
                bookingEntity.setCustomerName(arrOfStr[5]);
                bookingEntity.setPassport(arrOfStr[6]);
                bookingEntity.setEmail(arrOfStr[7]);
                bookingEntity.setContactNo(arrOfStr[8]);
                lstBookings.add(bookingEntity);
            }
        }
        return lstBookings;
    }

    public BookingEntity GetBookingByID(int bookingID) throws IOException {
        BookingEntity bookingEntity = new BookingEntity();
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String productData : allLines) {
            if (!productData.equals("")) {
                String[] arrOfStr = productData.split("::");
                if (Integer.parseInt(arrOfStr[0]) == bookingID) {
                    bookingEntity.setBookingID(Integer.parseInt(arrOfStr[0]));
                    bookingEntity.setBookingDate(arrOfStr[1]);
                    bookingEntity.setRoom(arrOfStr[2]);
                    bookingEntity.setFloor(arrOfStr[3]);
                    bookingEntity.setNumberOfDays(Integer.parseInt(arrOfStr[4]));
                    bookingEntity.setCustomerName(arrOfStr[5]);
                    bookingEntity.setPassport(arrOfStr[6]);
                    bookingEntity.setEmail(arrOfStr[7]);
                    bookingEntity.setContactNo(arrOfStr[8]);
                }
            }
        }
        return bookingEntity;
    }

    public Boolean DeleteBooking(int bookingID) {
        Boolean isDeleted = false;
        try {
            String fileContent = "";
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String data : allLines) {
                if (!data.equals("")) {
                    String[] arrOfStr = data.split("::");
                    if (Integer.parseInt(arrOfStr[0]) == bookingID) {
                    } else {
                        fileContent = fileContent + (arrOfStr[0] + "::" + arrOfStr[1] + "::" + arrOfStr[2] + "::" + arrOfStr[3] + "::" + arrOfStr[4] + "::" + arrOfStr[5] + "::" + arrOfStr[6] + "::" + arrOfStr[7] + "::" + arrOfStr[8] + System.getProperty("line.separator"));
                    }
                }
            }
            File file1 = new File(fileName);
            file1.delete();
            file1.createNewFile();
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(fileContent);
            fw.close();
            isDeleted = true;
        } catch (IOException ex) {
            isDeleted = false;
        }
        return isDeleted;
    }

    public Boolean UpdateBooking(BookingEntity bookingEntity) {
        Boolean isUpdated = false;
        try {
            String fileContent = "";
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String data : allLines) {
                if (!data.equals("")) {
                    String[] arrOfStr = data.split("::");
                    if (Integer.parseInt(arrOfStr[0]) == bookingEntity.getBookingID()) {
                        fileContent = fileContent + (bookingEntity.getBookingID() + "::" + bookingEntity.getBookingDate() + "::" + bookingEntity.getRoom() + "::" + bookingEntity.getFloor() + "::" + bookingEntity.getNumberOfDays() + "::" + bookingEntity.getCustomerName() + "::" + bookingEntity.getPassport() + "::" + bookingEntity.getEmail() + "::" + bookingEntity.getContactNo() + System.getProperty("line.separator"));
                    } else {
                        fileContent = fileContent + (arrOfStr[0] + "::" + arrOfStr[1] + "::" + arrOfStr[2] + "::" + arrOfStr[3] + "::" + arrOfStr[4] + "::" + arrOfStr[5] + "::" + arrOfStr[6] + "::" + arrOfStr[7] + "::" + arrOfStr[8] + System.getProperty("line.separator"));
                    }
                }
            }
            File file1 = new File(fileName);
            file1.delete();
            file1.createNewFile();
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(fileContent);
            fw.close();
            isUpdated = true;
        } catch (IOException ex) {
            isUpdated = false;
        }
        return isUpdated;
    }

    public List<String> GetBookedRooms(String bookingDate, String floor) {
        List<String> lstBookedRooms = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String data : allLines) {
                String[] arrOfStr = data.split("::");
                if (arrOfStr[1].equals(bookingDate) && arrOfStr[3].equals(floor)) {
                    lstBookedRooms.add(arrOfStr[2]);
                }
            }
        } catch (Exception ex) {

        }
        return lstBookedRooms;
    }

    public int GetLastIndex() {
        int lastID = 0;
        if (isDataExists()) {
            try {
                Path path = Paths.get(fileName);
                byte[] bytes = Files.readAllBytes(path);
                List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                String[] arrOfStr = allLines.get(allLines.size() - 1).split("::");
                lastID = Integer.parseInt(arrOfStr[0]);
            } catch (IOException ex) {
            }
        }
        return lastID;
    }

    public boolean isDataExists() {
        boolean isExists = false;
        try {
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            if (allLines.size() > 0 && !allLines.get(0).equals("")) {
                isExists = true;
            }
        } catch (IOException ex) {

        }
        return isExists;
    }
}
