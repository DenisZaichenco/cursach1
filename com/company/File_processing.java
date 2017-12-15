package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class File_processing {
    public static File_processing file_date = new File_processing();
    public static final String building_file = "src\\file\\building";
    public static final String type_from_relief = "src\\file\\Type from relief";
    public static final String recruit_file = "src\\file\\recruite";
    public static final String resource = "src\\file\\resource";
    public static final String RESOURCE_MILITARY = "src\\file\\resource_military";

    private final String DELIMITER = "**********************************";

    private BufferedReader readCost;
    private BufferedWriter writeData;
    private StringBuilder cost_defragmentation = new StringBuilder();
    private String line;
    private String address;

    private static int checkOnlyNumbers(String check_word) {
        final Pattern ONLY_NUMBERS = Pattern.compile("^[0-9]+$");//Matcher check_bar;
        if ((/*check_bar = */ONLY_NUMBERS.matcher(check_word)).matches())
            return Integer.parseInt(check_word);
        else
            return -1;
    }

    private static boolean duplication(int[] id_base, StringBuilder line) {
        for (int anId_base : id_base) {
            if (line.indexOf("/" + anId_base + ",") != line.lastIndexOf("/" + anId_base + ","))
                return true;
        }
        return false;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public void setSaveFile(String address) throws IOException {
        writeData = new BufferedWriter(new FileWriter(new File(address)));
    }

    public ArrayList<Element> getSetElement(StringBuilder cost_defragmentation) {//метод поветає набір id і кількості ресурсу відповідного цьому номеру
        int id, number;
        ArrayList<Element> resources = new ArrayList<>();
        while (cost_defragmentation.length() != 1) {
            //System.out.println(cost_defragmentation);
            if ((id = checkOnlyNumbers(cost_defragmentation.substring(1, cost_defragmentation.indexOf(",")))) == -1 |
                    (number = checkOnlyNumbers(cost_defragmentation.substring(cost_defragmentation.indexOf(",") + 1, cost_defragmentation.indexOf("/", 1)))) == -1) {
                BagDialogWindow.fileReadError("");//ошибка при чтении даных
                return null;
            }
            cost_defragmentation.delete(0, cost_defragmentation.indexOf("/", 1));//удаляет кусок с используимыми даными
           // System.out.println(cost_defragmentation);
            resources.add(new Element(id, number));
        }
        return resources;
    }
    public ArrayList<Manufacture> getSetManufacture(StringBuilder cost_defragmentation) {
        int id, number, time_to_end;
        int second_comma;
        ArrayList<Manufacture> manufactures = new ArrayList<>();
        while (cost_defragmentation.length() != 1) {
            if ((id = checkOnlyNumbers(cost_defragmentation.substring(1, cost_defragmentation.indexOf(",")))) == -1
                    || (number = checkOnlyNumbers((cost_defragmentation.substring(cost_defragmentation.indexOf(",") + 1, second_comma = cost_defragmentation.indexOf(",", cost_defragmentation.indexOf(",") + 1))))) == -1
                    || (time_to_end = checkOnlyNumbers(cost_defragmentation.substring(second_comma + 1, cost_defragmentation.indexOf("/", 1)))) == -1) {
                BagDialogWindow.fileReadError("");//ошибка при чтении даных
                return null;
            }
            cost_defragmentation.delete(0, cost_defragmentation.indexOf("/", 1));//удаляет кусок с используимыми даными
            manufactures.add(new Manufacture(id, number, time_to_end));
        }
        return manufactures;
    }
    public ArrayList<ResourceBase> getSetResourceBase(StringBuilder cost_defragmentation) {
        // System.out.println(cost_defragmentation);
        int id, number, store, income;
        int second_comma, third_comma;
        ArrayList<ResourceBase> manufactures = new ArrayList<>();
        while (cost_defragmentation.length() != 1) {
            if ((id = checkOnlyNumbers(cost_defragmentation.substring(1, cost_defragmentation.indexOf(",")))) == -1
                    || (number = checkOnlyNumbers(cost_defragmentation.substring(cost_defragmentation.indexOf(",") + 1, second_comma = cost_defragmentation.indexOf(",", cost_defragmentation.indexOf(",") + 1)))) == -1
                    || (store = checkOnlyNumbers(cost_defragmentation.substring(second_comma + 1, third_comma = cost_defragmentation.indexOf(",", (second_comma + 1))))) == -1
                    || (income = checkOnlyNumbers(cost_defragmentation.substring(third_comma + 1, cost_defragmentation.indexOf("/", 1)))) == -1) {
                BagDialogWindow.fileReadError("");//ошибка при чтении даных
                return null;
            }
            cost_defragmentation.delete(0, cost_defragmentation.indexOf("/", 1));//удаляет кусок с используимыми даными
            manufactures.add(new ResourceBase(id, number, store, income));
        }
        return manufactures;
    }
    public ArrayList<Element> getCost(int ID) throws IOException {
        itemSearch(ID);// повертає ціну будівлі
        cost_defragmentation = new StringBuilder(line.substring(line.indexOf("/"), (line.lastIndexOf("/") + 1)));
        //System.out.println(cost_defragmentation);
        return getSetElement(cost_defragmentation);
    }
    public String getInformation(int ID) throws IOException {
        itemSearch(ID);// читає короткий опис будівлі
        return readCost.readLine();
    }
    public String getType(int ID) throws IOException {
        itemSearch(ID);//відповідно до свого типу будівлі зрерігають ресурс, вироблють ресурс або навчають війська. Метод читає тип будівлі з файлу
        return line.substring((line.indexOf("|", 1) + 1), line.lastIndexOf("|"));
    }
    public ArrayList<Element> getSpecificResources(int ID) throws IOException {
        itemSearch(ID); //будівлі виробляють ресурс, зберігають ресурс або навчають війська. Вони записується після опису файла цей метод відповідає за читання цих даних
        readCost.readLine();
        cost_defragmentation.delete(0, cost_defragmentation.length());
        return getSetElement(cost_defragmentation.append(readCost.readLine()));
    }
    public int getRowFromUnit(int ID) throws IOException {
        int number;
        itemSearch(ID);
        readCost.readLine();
        line = readCost.readLine();
        if ((number = checkOnlyNumbers(line.substring(line.indexOf("/") + 1, line.lastIndexOf("/")))) != -1)
            return number;
        else {
            BagDialogWindow.fileReadError("");//System.out.println();// ошибка при чтении даних
            return -1;
        }
    }
    public Unite getInformationAboutUnite(int ID) throws IOException {
        int comma, min_damage, max_damage, attack, defense, initiative, health;
        itemSearch(ID);
        readCost.readLine();
        readCost.readLine();
        line = readCost.readLine();
        if ((min_damage = checkOnlyNumbers(line.substring(line.indexOf("/") + 1, comma = line.indexOf(",")))) == -1
                || (max_damage = checkOnlyNumbers(line.substring(comma + 1, comma = line.indexOf(",", comma + 1)))) == -1
                || (attack = checkOnlyNumbers(line.substring(comma + 1, comma = line.indexOf(",", comma + 1)))) == -1
                || (defense = checkOnlyNumbers(line.substring(comma + 1, comma = line.indexOf(",", comma + 1)))) == -1
                || (initiative = checkOnlyNumbers(line.substring(comma + 1, comma = line.indexOf(",", comma + 1)))) == -1
                || (health = checkOnlyNumbers(line.substring(comma + 1, line.indexOf("/", 1)))) == -1) {
            BagDialogWindow.fileReadError("");//ошибка при записи даних
            return new Unite();
        } else
            return new Unite(min_damage, max_damage, attack, defense, initiative, health);
    }
    public String getName(int ID) throws IOException {
        itemSearch(ID);
        return line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
    }

    public Map loadGame(String address) throws IOException {
        int max_x;
        int max_y;
        setFileReader(address);
        while (!(line = readCost.readLine()).isEmpty() && !line.contains("|")) {
        }
        if ((max_x = checkOnlyNumbers(line.substring(line.indexOf("|") + 1, line.indexOf("|", 1)))) != -1 &&
                (max_y = checkOnlyNumbers(line.substring(line.indexOf("|", 1) + 1, line.lastIndexOf("|")))) != -1) {
            Map map = new Map(max_x, max_y);
            for (int i = 0; i < max_x; i++) {
                for (int j = 0; j < max_y; j++) {
                    map.setHexagon(i, j, readHexagon());
                }
            }
            return map;
        } else
            System.out.println();//не правльно веленные даные
        return null;
    }
    public void saveGame(String address, Map map) throws IOException {
        setSaveFile(address);
        writeDateInNewLie("|" + map.getMAX_X() + "|" + map.getMAX_Y() + "|");
        for (int i = 0; i < map.getMAX_X(); i++) {
            for (int j = 0; j < map.getMAX_Y(); j++) {
                writeData.newLine();
                writeDateInNewLie("|" + i + "|" + j + "|");
                writeDateInNewLie("" + map.getHexagon(i, j).getType_from_relief());
                writeDateInNewLie(DELIMITER);
                if (map.getHexagon(i, j).getFort() != null) {
                    writeDateInNewLie("true");
                    writeDateInNewLie(DELIMITER);
                    for (ResourceBase resourceBase : map.getHexagon(i, j).getFort().getResourceBases())
                        writeData.write("/" + resourceBase);
                    writeDateInNewLie("/");
                    writeDateInNewLie(DELIMITER);
                    for (Element building : map.getHexagon(i, j).getFort().getBuildings())
                        writeData.write("/" + building);
                    writeDateInNewLie("/");
                    writeDateInNewLie(DELIMITER);
                    for (Manufacture manufacture : map.getHexagon(i, j).getFort().getManufactures())
                        writeData.write("/" + manufacture);
                    writeDateInNewLie("/");
                    writeDateInNewLie(DELIMITER);
                    for (Element recruite : map.getHexagon(i, j).getFort().getRecruit_limit())
                        writeData.write("/" + recruite);
                    writeDateInNewLie("/");
                    writeDateInNewLie(DELIMITER);
                } else
                    writeDateInNewLie("false");
                writeDateInNewLie(DELIMITER);
                if (map.getHexagon(i, j).getPlayerArmy() != null) {
                    for (Element playerArmy : map.getHexagon(i, j).getPlayerArmy().getUnits())
                        writeData.write("/" + playerArmy);
                    writeDateInNewLie("/");
                } else
                    writeDateInNewLie("null");
                writeDateInNewLie(DELIMITER);
                if (map.getHexagon(i, j).getEnemy() != null) {
                    for (Element enemy : map.getHexagon(i, j).getEnemy().getUnits())
                        writeData.write("/" + enemy);
                    writeDateInNewLie("/");
                } else
                    writeDateInNewLie("null");
                writeDateInNewLie(DELIMITER);
                writeData.newLine();
            }
        }
    }

    public Hexagon readHexagon() throws IOException {
        int number;
        Hexagon hexagon = new Hexagon(0,0,0,"");
        while (!(line = readCost.readLine()).contains("|")) {
        }
        if ((number = checkOnlyNumbers(line = readCost.readLine())) != -1) {
            hexagon.setType_from_relief(number);
            ArrayList<Element> element;
            while ((line = readCost.readLine()).equals(DELIMITER)) {
            }
            if ((line).contains("true")) {
                hexagon.createFort();
                ArrayList<Manufacture> manufactures;
                ArrayList<ResourceBase> resourceBases;
                while ((line = readCost.readLine()).contains(DELIMITER)) {
                }
                resourceBases = getSetResourceBase(new StringBuilder(line));
                for (ResourceBase resourceBase : resourceBases) {
                    hexagon.getFort().addResourceBase(resourceBase);
                }
                while ((line = readCost.readLine()).contains(DELIMITER)) {
                }
                element = getSetElement(new StringBuilder(line));
                for (Element anElement : element) {
                    hexagon.getFort().addBuilding(anElement);
                }
                while ((line = readCost.readLine()).contains(DELIMITER)) {
                }
                manufactures = getSetManufacture(new StringBuilder(line));
                for (Manufacture manufacture : manufactures) {
                    hexagon.getFort().addManufactures(manufacture);
                }
                while ((line = readCost.readLine()).contains(DELIMITER)) {
                }
                element = getSetElement(new StringBuilder(line));
                for (Element anElement : element) {
                    hexagon.getFort().addRecruit_Limit(anElement);
                }
            }
            while ((line = readCost.readLine()).contains(DELIMITER)) {
            }
            if (!line.contains("null")) {
                hexagon.createPlayerArmy();
                element = getSetElement(new StringBuilder(line));
                for (Element anElement : element) {
                    hexagon.getPlayerArmy().addUnit(anElement);
                }
            }
            while ((line = readCost.readLine()).contains(DELIMITER)) {
            }
            if (!line.contains("null")) {
                hexagon.createEnemy();
                element = getSetElement(new StringBuilder(line));
                for (Element anElement : element) {
                    hexagon.getEnemy().addUnit(anElement);
                }
            }
        }
        return hexagon;
    }
    private void itemSearch(int ID) throws IOException  {//доводе файл до лінії з потрібним id будівлі/найманця
        setFileReader(address);
        while (((line = readCost.readLine()) != null) && (!line.contains("|" + ID))) {
        }
    }
    private void setFileReader(String address)  {
        try {
            readCost = new BufferedReader(new FileReader(new File(address)));
        } catch (FileNotFoundException e) {
            BagDialogWindow.fileReadError(address);
        }
    }
    private void writeDateInNewLie(String line)  {
        try {
            writeData.write(line);
            writeData.newLine();
        } catch (IOException e) {
            BagDialogWindow.fileReadError("");
        }
    }
}