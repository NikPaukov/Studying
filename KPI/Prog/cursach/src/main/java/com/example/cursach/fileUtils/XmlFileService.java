package com.example.cursach.fileUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class XmlFileService implements FileService {


    private String fileName;
    private final Path path;
    private final String usersList = "C:\\Users\\Elfar\\Desktop\\programming\\Studying\\KPI\\Prog\\cursach\\xmls\\Users.txt";


    public XmlFileService(String fileName) {
        this.fileName = fileName;
        path = Paths.get("C:\\Users\\Elfar\\Desktop\\programming\\Studying\\KPI\\Prog\\cursach\\xmls\\" + fileName + ".xml");
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private boolean containsTag(String name, String value) throws ParserConfigurationException, IOException, SAXException {
            File file = new File(String.valueOf(path));
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("task");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if(value.equals(eElement.getElementsByTagName(name).item(0).getTextContent())){
                        return true;
                    }
                }
            }
        return false;
    }
    public List<String[]> readEntitiesTags() throws ParserConfigurationException, IOException, SAXException {
        List<String[]> tasks = new LinkedList<>();
            File file = new File(String.valueOf(path));
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("task");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    tasks.add(new String[]{eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("priority").item(0).getTextContent(),
                            eElement.getElementsByTagName("duration").item(0).getTextContent()});

                }
            }

        return tasks;
    }

    public boolean write(String text) throws IOException, ParserConfigurationException, SAXException {
        List<String> lines = Files.readAllLines(path);
        lines.add(lines.size() - 2, text);
        Files.write(path, lines);
        return true;
    }

    public int  delete(String name) throws IOException, ParserConfigurationException, SAXException {
            File file = new File(String.valueOf(path));
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("task");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if(eElement.getElementsByTagName("name").item(0).getTextContent().toLowerCase(Locale.ROOT)
                            .equals(name.toLowerCase(Locale.ROOT))){
                        List<String> lines = Files.readAllLines(path);
                        for(int i = 0; i < 7; i++)
                        lines.remove(2+7*itr);
                        Files.write(path, lines);
                        return 1;
                    }
                }
            }
            return 0;
    }

    public int hasUser() {
        String users;
        try (BufferedReader reader = new BufferedReader(new FileReader(usersList));) {
            users = reader.readLine();
        } catch (IOException e) {
            return -1;
        }
        if (containsFile(users, fileName)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int create() {
        String users;
        try (BufferedReader reader = new BufferedReader(new FileReader(usersList));) {
            users = reader.readLine();
        } catch (IOException e) {
            return -1;
        }
        try {
            if (containsUsername(users, fileName.split("&")[0])) {
                System.out.println("User exists");
                return 0;
            } else {
                Files.createFile(path);
                Files.write(path, "<tasks>\n\n\n</tasks>".getBytes());
                Files.write(Paths.get(usersList), (fileName + " ").getBytes(), StandardOpenOption.APPEND);
                return 1;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(-2);
            return -2;
        }
    }


    private boolean containsUsername(String users, String username) {
        if (users == null) return false;
        String[] userArr = users.split(" +");
        for (String user : userArr) {
            if (user.split("&")[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsFile(String users, String file) {
        if (users == null) return false;
        String[] userArr = users.split(" +");
        for (String user : userArr) {
            if (user.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
