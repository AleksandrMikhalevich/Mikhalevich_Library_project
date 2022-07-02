package actions.impl.constants;

import dto.AuthorDto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 16:39
 */
public class Constants {

    /*
    Book constants
     */
    public static final String BOOK_ID = "id";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_LANGUAGE ="language";
    public static final String BOOK_YEAR_OF_PUBLISHING = "year_of_publishing";
    public static final String BOOK_RECEIPT_DATE = "receipt_date";
    public static final String BOOKS_SORTING = "sorting";
    public static final String SEARCH_BOOK_BY_NAME_QUERY = "search_query";

    /*
    Author constants
     */
    public static final String AUTHOR_ID = "id";
    public static final String AUTHOR_IDS = "author_ids";
    public static final String AUTHOR_SURNAME = "surname";
    public static final String AUTHOR_FIRST_NAME = "first_name";
    public static final String AUTHOR_SECOND_NAME = "second_name";
    public static final String AUTHOR_COUNTRY = "country";
    public static final String SEARCH_AUTHOR_BY_NAME_QUERY = "search_query";

    public static final Set<AuthorDto> AUTHORS_SET = new HashSet<>();

    /*
    Genre constants
     */
    public static final String GENRE_ID = "id";
    public static final String GENRE_IDS = "genre_ids";
    public static final String GENRE_NAME = "name";
    public static final String GENRE_DESCRIPTION = "description";
    public static final String SEARCH_GENRE_BY_NAME_QUERY= "search_query";

    /*
    Publisher constants
     */
    public static final String PUBLISHER_ID = "publisher_id";
    public static final String PUBLISHERS_IDS = "publishers_ids";
    public static final String PUBLISHER_NAME = "name";
    public static final String PUBLISHER_COUNTRY = "country";
    public static final String PUBLISHER_CITY = "city";
    public static final String PUBLISHER_STREET = "street";
    public static final String PUBLISHER_HOUSE = "house";
    public static final String PUBLISHER_ZIPCODE = "zipcode";
    public static final String SEARCH_PUBLISHER_BY_NAME_QUERY = "search_query";

    /*
    User constants
     */



}
