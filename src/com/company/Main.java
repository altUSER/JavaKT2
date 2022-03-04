package com.company;

import com.company.config.CConfigHibernate;
import com.company.dao.CDAOOrders;
import com.company.dao.CDAOUsers;
import com.company.model.CGood;
import com.company.model.COrder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import com.company.model.CUser;
import org.apache.poi.xwpf.usermodel.*;
import org.hibernate.Session;

public class Main {
/*
    private static ArrayList loadGoods() {
        ArrayList<CGood> goods = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream("data/Magazin.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Товары");

            int rowNums = sheet.getLastRowNum();

            for (int i = 1; i <= rowNums; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                UUID id = UUID.fromString(row.getCell(0).toString());
                double pice = Double.valueOf(row.getCell(2).toString());
                goods.add(new CGood(id, row.getCell(1).toString(), pice, row.getCell(3).toString()));
            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goods;
    }

    private static void report(ArrayList goodNames, ArrayList counts) {
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph par1 = document.createParagraph();
        par1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun title = par1.createRun();
        title.setBold(true); //title
        title.setFontFamily("TimesNewRoman");
        title.setFontSize(16);
        title.setText("Report");

        XWPFParagraph par2 = document.createParagraph(); //description
        par2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun desc = par2.createRun();
        desc.setFontFamily("TimesNewRoman");
        desc.setFontSize(13);
        desc.setText("Number of orders per good");

        XWPFTable table = document.createTable();
        XWPFTableRow row;
        row = table.getRow(0); //title row
        row.getCell(0).setText("Name");
        row.addNewTableCell().setText("Count");

        for (int i = 0; i < goodNames.size(); i++) { //fill table
            row = table.createRow();
            row.getCell(0).setText((String) goodNames.get(i));
            row.getCell(1).setText(counts.get(i).toString());
        }
        table.setWidth("100%");

        try(FileOutputStream output = new FileOutputStream("report.docx")) { //write to file
            document.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    private static List loadOrders() {
        ArrayList<COrder> orders = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream("C:/dv/java/un1/data/Magazin.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Покупки");

            int rowNums = sheet.getLastRowNum();

            for (int i = 1; i <= rowNums; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                UUID uid = UUID.fromString(row.getCell(0).toString());
                UUID gid = UUID.fromString(row.getCell(1).toString());
                //orders.add(new COrder(uid, gid, row.getCell(2).getDateCellValue()));
            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<COrder> outpOrders = orders;
        return outpOrders;
    }

    private static List loadUsers() {

        ArrayList<CUser> users = new ArrayList<>();


        try {
            FileInputStream file = new FileInputStream("C:/dv/java/un1/data/Magazin.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Пользователи");

            int rowNums = sheet.getLastRowNum();

            for (int i = 1; i <= rowNums; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                UUID id = UUID.fromString(row.getCell(0).toString());
                Date date = row.getCell(4).getDateCellValue();
                users.add(new CUser(id, row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString().equals("ж"), date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CUser> outpUsers = users;
        return outpUsers;

    }
/*
    public static void count() {
        ArrayList goods = loadGoods();
        ArrayList orders = loadOrders();

        ArrayList<String> resGoods = new ArrayList<>(); //result arrays
        ArrayList<Integer> resCounts = new ArrayList<Integer>();

        for (int i=0; i<goods.size(); i++){
            CGood good = (CGood) goods.get(i);
            int count = 0;
            for (int k=0; k<orders.size(); k++) {
                COrder order = (COrder) orders.get(k);
                if (order.id.equals(good.id)) {
                    count++;
                }
            }
            resGoods.add(good.name);
            resCounts.add(count);
        }
        report(resGoods, resCounts);
    }
*/
    public static void main(String[] args) {
        //count();

        CDAOUsers daoUsers = new CDAOUsers(CConfigHibernate.getSessionFactory());
        CDAOOrders daoOrders = new CDAOOrders(CConfigHibernate.getSessionFactory());

        //List<CUser> users= loadUsers();
        //daoUsers.saveList(users);
        //CUser user = daoUsers.get(UUID.fromString("15724b7c-04d5-4b39-a18a-8991efe4ab91"));
        //daoUsers.delete(user);


        /*
        ArrayList<CUser> users = loadUsers();
        //CUser user = new CUser();
        for (CUser user:users){
            daoUsers.save(user);
        }*/


        CUser user = new CUser();
        user.id = UUID.fromString("6720a44c-af02-41e9-9d19-c4bb1c38c9a9");
        user.login = "Alexander";
        user.name = "Александр";
        user.dateOfBirth = LocalDate.now();
        daoUsers.save(user);


        }
}
