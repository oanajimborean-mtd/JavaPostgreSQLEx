package option;

import insertData.*;
import queryData.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Options {
    public static void displayOptions() {

        System.out.println("In ce tabel vrei sa adaugi de la 1-6?:");
        System.out.println("1. Authors");
        System.out.println("2. Books");
        System.out.println("3. Members");
        System.out.println("4. Loans");
        System.out.println("5. Publishers");
        System.out.println("6. BookPublishers");
        System.out.println("7. Afiseaza cartile impreuna cu autorii lor.");
        System.out.println("8. Afiseaza membrii si cartile imprumutate de ei.");
        System.out.println("9. Afiseaza cartile si membrii care le-au imprumutat.");
        System.out.println("10. Afiseaza cartile impreuna cu editurile lor.");
        System.out.println("11. Afiseaza cartile care nu au fost imprumutate.");
        System.out.println("12. Exit");
    }

    public static void processOptions(Connection conn, Scanner scanner) throws SQLException {
        while (true) {
            displayOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 12) {
                System.out.println("Exiting program...");
                break;
            }

            if (choice < 1 || choice > 12) {
                System.err.println("Optiunea selectata este invalida. Alege un numar intre 1 si 12.");
                continue;
            }

            int entries = 0;
            if (choice <= 6) {
                System.out.println("Cate intrari doresti sa introduci?");
                entries = scanner.nextInt();
                scanner.nextLine();

                if (entries <= 0) {
                    System.err.println("Numarul de intrari trebuie sa fie mai mare decat 0.");
                    continue;
                }
            }

            handleChoice(conn, scanner, choice, entries);

            System.out.println("Efectuati alta operatie? (da/nu)");
            if (!scanner.nextLine().equalsIgnoreCase("da")) {
                System.out.println("Exiting program...");
                break;
            }
        }
    }

    private static void handleChoice(Connection conn, Scanner scanner, int choice, int entries) throws SQLException {
        switch (choice) {
            case 1:
                AuthorHandler.addAuthor(conn, scanner, entries);
                break;
            case 2:
                BookHandler.addBook(conn, scanner, entries);
                break;
            case 3:
                MemberHandler.addMember(conn, scanner, entries);
                break;
            case 4:
                LoanHandler.addLoan(conn, scanner, entries);
                break;
            case 5:
                PublisherHandler.addPublisher(conn, scanner, entries);
                break;
            case 6:
                BookPublisherHandler.addBookPublisher(conn, scanner, entries);
                break;
            case 7:
                BookAuthorQueryManager.queryBooksAndAuthors(conn);
                break;
            case 8:
                MemberBookQueryManager.queryMembersAndBooks(conn);
                break;
            case 9:
                BookMemberQueryManager.queryBooksAndTheirMember(conn);
                break;
            case 10:
                BookPublisherQueryManager.queryBooksAndPublishers(conn);
                break;
            case 11:
                UnloanedBookQuery.queryUnloanedBooks(conn);
                break;
            default:
                System.out.println("Optiunea selectata este invalida.");
                break;
        }
    }
}
