/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import pidev.Entities.Feed;
import pidev.Entities.FeedMessage;
import pidevavecmaven.RSSController;

/**
 *
 * @author dell
 */
public class RSSService {

    public void Read() throws NoSuchFieldException {
        RSSController parser = new RSSController("http://rss.cnn.com/rss/edition.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }

    public void Write() {
        // create the rss feed
        String copyright = "Copyright hold by Lars Vogel";
        String title = "Eclipse and Java Information";
        String description = "Eclipse and Java Information";
        String language = "en";
        String link = "http://edition.cnn.com";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String pubdate = date_format.format(creationDate);
        Feed rssFeeder = new Feed(title, link, description, language, copyright, pubdate);

        // now add one example entry
        FeedMessage feed = new FeedMessage();
        feed.setTitle("RSSFeed");
        feed.setDescription("Top Stories of the world");
        feed.setAuthor("unknown@somewhere.com (Who Cares)");
        feed.setGuid("http://rss.cnn.com/rss/edition.rss");
        feed.setLink("http://rss.cnn.com/rss/edition.rss");
        rssFeeder.getMessages().add(feed);

        // now write the file
        RSSController writer = new RSSController(rssFeeder, "articles.rss");
        try {
            writer.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
