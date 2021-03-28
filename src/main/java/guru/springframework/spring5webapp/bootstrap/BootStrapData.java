package guru.springframework.spring5webapp.bootstrap;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
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
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);

        Author rod = new Author("Rod", "Johnson");
        Book J2EE = new Book("J2EE Without EJB", "234234");

        authorRepository.save(rod);

        Publisher publisher1 = new Publisher("MW_PUBLISHER", "5459 Fair Oaks St", "San Francisco", "CA", "94808");
        Publisher publisher2 = new Publisher("MW_PUBLISHER", "4500 Centre Ave", "San Francisco", "CA", "94808");

        rod.getBooks().add(J2EE);
        J2EE.getAuthors().add(rod);

        J2EE.setPublisher(publisher1);
        ddd.setPublisher(publisher2);

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        bookRepository.save(J2EE);
        bookRepository.save(ddd);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository .count());

        publisher1.getBooks().add(ddd);
        publisher1.getBooks().add(J2EE);

        System.out.println("Number of publishers: " + publisherRepository .count());
        System.out.println("Publisher1 number of books: " + publisher1.getBooks().size());
    }
}
