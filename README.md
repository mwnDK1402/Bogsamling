# Bogsamling

An interactive command-line interface for a transient library of books.

# Branches

- [extra](https://github.com/mwnDK1402/Bogsamling/tree/extra) shows an expanded version of the application riffing off the extra assignments.
- [main](https://github.com/mwnDK1402/Bogsamling/tree/main) shows my submission for assignments 1 through 15.

# How to run

## Interactive Menu

```
mvn compile exec:exec -Dexec.executable=java -Dexec.args="-cp %classpath Main"
```
## Genre Web Scraper
```
mvn compile exec:exec -Dexec.executable=java -Dexec.args="-cp %classpath GoodReadsGenreScraper"
```

# Compatibility

The project targets JDK 26+ and uses Maven 3.x.
