import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

private static final Charset CHARSET = StandardCharsets.UTF_8;

// I think static global data is appropriate for command-line argument values
private static boolean noCache = false;

/// @param page query parameter
/// @return url
private static String getPageUrl(int page) {
    return "https://www.goodreads.com/genres/list?utf8=%E2%9C%93&filter=top-level&page=" + page;
}

private static Document loadOrFetchPage(int page) throws IOException {
    var cacheDir = Files.createDirectories(Path.of(System.getProperty("user.dir"), "cache"));
    var cacheFile = cacheDir.resolve(String.format("genre_page_%d.html", page)).toFile();

    // Caching is a must for a respectful scraper.
    if (!noCache && cacheFile.exists()) {
        return Jsoup.parse(cacheFile, CHARSET.name());
    } else {
        var doc = Jsoup.connect(getPageUrl(page)).get();
        try (var writer = Files.newBufferedWriter(cacheFile.toPath(), CHARSET)) {
            writer.write(doc.root().outerHtml());
        }

        return doc;
    }
}

void main(String[] args) throws IOException {
    var pages = new ArrayList<Document>(4);

    noCache = Arrays.stream(args).anyMatch(a -> a.equalsIgnoreCase("no-cache"));

    {
        // We can use the first page to see how many pages exist,
        // though there is a case to be made that a race condition
        // is possible, I deem it very unlikely (and we can just run the script again).
        var firstPage = loadOrFetchPage(1);
        pages.add(firstPage);

        var parent = firstPage.select(".leftContainer").first();
        var nextPage = parent.select(".next_page").first();
        var lastPageHtml = nextPage.previousElementSibling().html();
        var lastPage = Integer.parseInt(lastPageHtml);

        for (int i = 2; i <= lastPage; i++) {
            pages.add(loadOrFetchPage(i));
        }
    }

    var rawGenres = new ArrayList<String>(pages.size() * 100);

    for (var page : pages) {
        var parent = page.select(".leftContainer").first();
        var columns = parent.select(".left");
        var cells = columns.select("a").stream().map(Element::html);
        rawGenres.addAll(cells.toList());
    }

    System.out.println("=== All scraped genres ===");
    for (int i = 0; i < rawGenres.size(); i++) {
        var genre = rawGenres.get(i);
        System.out.printf("%-40s ", genre);

        if ((i + 1) % 4 == 0) {
            System.out.println();
        }
    }

    System.out.printf("%n%n");

    var blacklist = Set.of(
            "holohoax",
            "ᛋᛋ-books"
    );
    {
        System.out.println("=== Ignored genres ===");
        var index = new int[]{0}; // I know this is stupid, but Java is stupid.
        // Not sure how to handle non-ASCII characters, but I should at least be aware of what I'm filtering out.
        rawGenres.stream()
                .filter(g -> g.chars().anyMatch(c -> c > 127) || blacklist.contains(g))
                .forEachOrdered(genre -> {
                    System.out.printf("%-40s ", genre);

                    if ((++index[0]) % 4 == 0) {
                        System.out.println();
                    }
                });
        System.out.printf("%n%n");
    }

    String[] chosenGenres = rawGenres.stream()
            .filter(g -> g.chars().noneMatch(c -> c > 127) && !blacklist.contains(g))
            .toArray(String[]::new);

    // Quite wasteful, but the process is short-lived anyway.
    var sanitizedGenres = Arrays.stream(chosenGenres)
            .map(g -> {
                var sanitized = g.toUpperCase(Locale.ROOT).replace("-", "_");
                return Character.isDigit(sanitized.charAt(0)) ? "_" + sanitized : sanitized;
            })
            .distinct()
            .toArray(String[]::new);

    {
        System.out.println("=== Sanitized genres ===");
        var index = new int[]{0};
        for (var genre : sanitizedGenres) {
            System.out.printf("%-40s ", genre);

            if ((++index[0]) % 4 == 0) {
                System.out.println();
            }
        }
    }

    var sourceDir = Files.createDirectories(Path.of(System.getProperty("user.dir"), "src/main/java/"));
    var sourceFile = sourceDir.resolve("Genre.java");
    try (var writer = Files.newBufferedWriter(sourceFile, CHARSET)) {
        var indent = " ".repeat(4);
        writer.write("@SuppressWarnings(\"unused\")");
        writer.newLine();
        writer.write("public enum Genre {");
        writer.newLine();

        for (var genre : sanitizedGenres) {
            writer.write(indent);
            writer.write(genre);
            writer.write(",");
            writer.newLine();
        }

        writer.append("}");
        writer.newLine();
    }
}
