# TargetParse
Targeted HTML tag parser and scraper.

[![LICENSE MIT](https://img.shields.io/badge/license-MIT-blue.svg)](hhttps://github.com/splitty/TargetParse/blob/master/LICENSE)
### Summary
TargetParse is an easy to use web scraper and HTML tag parser that functions on the JSoup repository.  It is set up out of the box to parse and scrape images from websites but can be changed and modified to scrape entire pages as well as specified tags from live websites.

This software requires the JSoup repository and any JRE over 1.4.0.

Because of the depth and complexity of the internet, the parser will need some customization depending on how oddly the image sources are linked, but most modern web pages are easily scraped with little to no modification.

### Features
* Scrape up to 10 addresses at the same time.
* Easy to use interface.
* Powerful tag parsing with the help of JSoup.
* Built in download function and file saver.
* Download function operates on all file types.
* Fully portable EXE and JAR runnable files.

### Changing the Parser
Out of the box, TargetParse is set up to work with the img tag's "src" attribute.  Additionally, there has been a full site download parser in the source code.  However, you can very easily alter and modify the parsed tags to achieve any number of scraping requests.

For instance, here is the code to grab all of the images from a given URL.
```java
Elements file = doc.select("img");
//iterate through each image file and grab the image source as well as downloading it.
for (Element el : file) {
     //attribute of the img tag - src is where the image file is found
     src = el.absUrl("src");
     //pass to download function
     grabMe();
}
```
Or in the example of parsing CloudFlare protected email addresses from Runestone, you can parse the tag for a different attribute to get plaintext.
```java
Elements email = doc.select("a[href][title]");
for (Element e : email) {
     emailsrc = e.attr("title");
}
```
Just by modifying the chosen parsed tags, you can also target specific image sources - like grabbing images from WordPress sites who try to deter typical scrapers by appending SSL certs to the end of image files.
```java
Elements wpPic = doc.select("img");
for (Element e : wpPic) {
     wpSource = e.attr("src");
}
...
//in the download function
String wpSource = wpReplace.replace("&ssl=1", ".png");
filePath = Path + wpReplace;
```
### Examples
#### Runestone.org
Runestone.org is home to the AFA, a white supremacist organization that was the inspiration for this tool.  Grabbing email addresses, pictures, and personal information from the website using TargetParse.

#### Cnet.com
Grabbing all of the news headline images at once.

### Shutterstock
Large image website.

### Downloads
TargetParse is available in both a portable EXE and JAR file in addition to the uncompiled source code.  
[The EXE can be found here.](https://github.com/splitty/TargetParse/tree/master/Executable)
and
[The JAR can be found here.](https://github.com/splitty/TargetParse/tree/master/JARBuild)
