package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
//import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        Publisher bigBooks = new Publisher("Big books", "Chernobyl st' 1986", "New York City", "Texas", "13213");
        publisherRepository.save(bigBooks);
        Author eric = new Author("Eric", "Shlomtz");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(bigBooks);
        bigBooks.getBooks().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(bigBooks);

        Author victor = new Author("Victor", "Hugo");
        Book lesMiserables = new Book("Les Miserables", "213123124");
        victor.getBooks().add(lesMiserables);
        lesMiserables.getAuthors().add(victor);

        lesMiserables.setPublisher(bigBooks);
        bigBooks.getBooks().add(lesMiserables);
        authorRepository.save(victor);
        bookRepository.save(lesMiserables);
        publisherRepository.save(bigBooks);

        System.out.println("Started Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("num of publishers:" + publisherRepository.count());
        System.out.println("Publisher number of books" + bigBooks.getBooks().size());


    }



}
