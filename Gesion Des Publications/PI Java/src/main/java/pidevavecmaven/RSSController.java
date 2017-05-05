package pidevavecmaven;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import pidev.Entities.Feed;
import pidev.Entities.FeedMessage;
import pidev.Services.RSSService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class RSSController implements Initializable {
    
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    private URL url2;

    private String outputFile;
    private Feed rssfeed;
    
    @FXML
    private VBox vbRSS;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RSSService RSS = new RSSService();
        RSS.Write();
        try {
            RSS.Read();
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(RSSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            write();
            readFeed();
        } catch (Exception ex) {
            Logger.getLogger(RSSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RSSController(Feed rssfeed, String outputFile) {
        this.rssfeed = rssfeed;
        this.outputFile = outputFile;
    }
    
    public RSSController(String feedUrl) {
        try {
            this.url2 = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

   //Writing
    public void write() throws Exception {
        // create a XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // create a XMLEventWriter
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
        // create a EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        // create open tag
        eventWriter.add(end);
        StartElement rssStart = eventFactory.createStartElement("", "", "rss");
        eventWriter.add(rssStart);
        eventWriter.add(eventFactory.createAttribute("version", "2.0"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createStartElement("", "", "channel"));
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, "title", rssfeed.getTitle());
        createNode(eventWriter, "link", rssfeed.getLink());
        createNode(eventWriter, "description", rssfeed.getDescription());
        createNode(eventWriter, "language", rssfeed.getLanguage());
        createNode(eventWriter, "copyright", rssfeed.getCopyright());
        createNode(eventWriter, "pubdate", rssfeed.getPubDate());
        for (FeedMessage entry : rssfeed.getMessages()) {
            eventWriter.add(eventFactory.createStartElement("", "", "item"));
            eventWriter.add(end);
            createNode(eventWriter, "title", entry.getTitle());
            createNode(eventWriter, "description", entry.getDescription());
            createNode(eventWriter, "link", entry.getLink());
            createNode(eventWriter, "author", entry.getAuthor());
            createNode(eventWriter, "guid", entry.getGuid());
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndElement("", "", "item"));
            eventWriter.add(end);
        }
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "channel"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "rss"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name,
        String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

    //Reading
    public Feed readFeed() throws NoSuchFieldException {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            Label titleL = new Label();
            Label descriptionL = new Label();
            Label linkL = new Label();
            Label guidL = new Label();
            Label languageL = new Label();
            Label authorL = new Label();
            Label pubdateL = new Label();
            Label copyrightL = new Label();
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language, copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;

                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            titleL.setText(title);
                            break;

                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            descriptionL.setText(description);
                            break;

                        case LINK:
                            link = getCharacterData(event, eventReader);
                            linkL.setText(link);
                            break;

                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            guidL.setText(guid);
                            break;

                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            languageL.setText(guid);
                            break;

                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            authorL.setText(author);
                            break;

                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            pubdateL.setText(pubdate);
                            break;

                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            copyrightL.setText(copyright);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        Label sepL = new Label();
                        String sep = "";
                        sepL.setText(sep);
                        GridPane gp = new GridPane();
                        gp.getColumnConstraints().add(new ColumnConstraints(100, 300, 300, Priority.ALWAYS, HPos.LEFT, true));
                        gp.add(authorL, 0, 0);
                        gp.add(descriptionL, 0, 1);
                        gp.add(guidL, 0, 2);
                        gp.add(linkL, 0, 3);
                        gp.add(titleL, 0, 4);
                        VBox v = new VBox();
                        v.getChildren().add(gp);
                        VBox vv = new VBox();
                        vv.getChildren().add(sepL);

                        TimelineController t = new TimelineController();
                        vbRSS.getChildren().add(v);
                        vbRSS.getChildren().add(v);

                        FeedMessage message = new FeedMessage();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url2.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
