package mocks;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-01 19:52
 */
public class MockConstants {

    /*
    Author's service test constants
     */
    public static final String AUTHOR_FIRST_NAME = "Yanka";
    public static final String AUTHOR_SECOND_NAME = "Daminikavich";
    public static final String AUTHOR_SURNAME = "Kupala";
    public static final String AUTHOR_COUNTRY = "Belarus";
    public static final String UPDATE_AUTHOR_FIRST_NAME = "Aaa";
    public static final String UPDATE_AUTHOR_SECOND_NAME = "Bbb";
    public static final String UPDATE_AUTHOR_SURNAME = "Ccc";
    public static final String UPDATE_AUTHOR_COUNTRY = "Ddd";
    public static final int[] AUTHOR_PUBLISHERS_IDS = {1 , 2};

    /*
    Book's service test constants
     */
    public static final String BOOK_TITLE = "Tuteyshia";
    public static final String BOOK_LANGUAGE = "belarusian";
    public static final String BOOK_YEAR_OF_PUBLISHING = "1953";
    public static final Date BOOK_RECEIPT_DATE = new Date(LocalDate.now().toEpochDay());
    public static final Date UPDATE_BOOK_RECEIPT_DATE = Date.valueOf("1980-01-09");
    public static final String UPDATE_BOOK_TITLE = "Aaa";
    public static final String UPDATE_BOOK_LANGUAGE = "Bbb";
    public static final String UPDATE_BOOK_YEAR_OF_PUBLISHING = "1900";
    public static final int[] BOOK_AUTHORS_IDS = {1 , 2};
    public static final int[] BOOK_GENRES_IDS = {1 , 2};
    public static final int BOOK_PUBLISHER_ID = 1;

    /*
    Genre's service test constants
    */
    public static final String GENRE_NAME = "Lyrics";
    public static final String GENRE_DESCRIPTION = "Description of Lyrics genre";
    public static final String UPDATE_GENRE_NAME = "Action";
    public static final String UPDATE_GENRE_DESCRIPTION = "Description of Action genre";
    /*
    Publisher's service test constants
    */
    public static final String PUBLISHER_NAME = "Batkauschyna";
    public static final String PUBLISHER_COUNTRY = "Germany";
    public static final String PUBLISHER_CITY = "Munich";
    public static final String PUBLISHER_STREET = "none";
    public static final String PUBLISHER_HOUSE = "none";
    public static final String PUBLISHER_ZIPCODE = "none";
    public static final String UPDATE_PUBLISHER_NAME = "Aaa";
    public static final String UPDATE_PUBLISHER_COUNTRY = "Egypt";

    /*
    User's service test constants
    */
    public static final String LOGIN = "user_33";
    public static final String PASSWORD = "qwerty_33";
    public static final String EMAIL = "user_33@library.org";
    public static final String UPDATE_USER_LOGIN = "user_22";
    public static final String UPDATE_USER_PASSWORD = "qwerty_22";
    public static final String UPDATE_USER_EMAIL = "user_22@library.org";
}
